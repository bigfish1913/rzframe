package com.rz.frame.pool;



import com.rz.frame.utils.exception.OutofMaxCountException;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class  PoolImpl {

    private static String jdbcDriver = null;
    private static String jdbcUrl = null;
    private static String userName = null;
    private static String password = null;

    private static int initCount;
    private static int stepSize;
    private static int poolMaxSize;

    private static Lock lock;


    private static volatile Vector<PooledConnection> PoolsConnections = new Vector<PooledConnection>();

    public  PoolImpl() {
        init();
    }

    private void init() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbcPool.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            jdbcDriver = properties.getProperty("jdbcDriver");
            jdbcUrl = properties.getProperty("jdbcUrl");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            initCount = Integer.valueOf(properties.getProperty("initCount"));
            stepSize = Integer.valueOf(properties.getProperty("stepSize"));
            poolMaxSize = Integer.valueOf(properties.getProperty("poolMaxSize"));

            //注册
            Driver mysqlDriver = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(mysqlDriver);
            createConnections(initCount);

            //两个锁
            lock = new ReentrantLock();

        } catch (Exception e) {

        }
    }


    public PooledConnection getPooledConnection() throws RuntimeException, SQLException {
        if (poolMaxSize <= 0) {
            throw new IllegalArgumentException("创建管道对象失败，最大值参数错误");
        }
        PooledConnection realConnection = getRealConnection();
        while (realConnection == null) {
            if (lock.tryLock()) {//尝试获取锁
                createConnections(stepSize);//获得锁之后进行扩容
                lock.unlock();
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
            realConnection = getRealConnection();
            if (realConnection != null) {
                return realConnection;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
        }

        return realConnection;
    }

    private PooledConnection getRealConnection() {
        for (PooledConnection pooledConnection : PoolsConnections) {
            try {
                if (pooledConnection.isBusy())
                    continue;
                /*
                此处要保证写的时候不能被读取，不然会报ConcurrentModificationException异常
                 */
                pooledConnection.writeLock();//读写互斥，写写互斥
                Connection connection = pooledConnection.getConnection();
                if (!connection.isValid(200)) {//是否有效，200ms 没有被超时
                    Connection validConnect = DriverManager.getConnection(jdbcUrl, userName, password);
                    pooledConnection.setConnection(validConnect);
                }
                pooledConnection.setBusy(true);
                pooledConnection.unWriteLock();
                return pooledConnection;
            } catch (SQLException e) {

                return null;
            }
        }
        return null;
    }


    public void createConnections(int count) throws OutofMaxCountException, IllegalArgumentException {
        if (poolMaxSize <= 0) {
            throw new IllegalArgumentException("创建管道对象失败，最大值参数错误");
        }
        //判断是否有溢出
        boolean overFlow = isOverFlow(count);
        if (overFlow) {
            return;
        }
        for (int i = 0; i < count; i++) {
            try {
                overFlow = isOverFlow(count);
                if (overFlow)
                    return;
                Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
                PooledConnection pooledConnection = new PooledConnection(connection, false);
                PoolsConnections.add(pooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    private boolean isOverFlow(int count) {
        if (PoolsConnections.size() + count >= poolMaxSize) {
            return true;
        }
        return false;
    }

    public ResultSet querySql(String sql) {
        try {
            PooledConnection pooledConnection = getPooledConnection();
            /*
             此处要保证读的时候不能被修改，使用读锁
             */
            pooledConnection.readLock();
            Connection connection = pooledConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            pooledConnection.close();
            pooledConnection.unReadLock();
            return resultSet;
        } catch (SQLException e) {

        }
        return null;
    }

    public void shutDown() {
        for (PooledConnection pConnect : PoolsConnections) {
            pConnect.shutDown();
        }
    }
}