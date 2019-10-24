package com.rz.frame.rzdal.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PooledConnection {

    private boolean isBusy = false;
    private Connection connection;
    private boolean isUsing = false;
    private ReentrantReadWriteLock reentrantReadWriteLock;

    public PooledConnection(Connection connection, boolean b) {
        this.isBusy = b;
        this.connection = connection;
        this.isUsing = false;
        reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

    public PooledConnection() {

        reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Connection getConnection() {
        this.isUsing = true;
        return connection;
    }

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public void close() {

        this.isUsing = false;
        this.setBusy(false);
    }

    public void shutDown() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void writeLock() {
        this.reentrantReadWriteLock.writeLock().lock();
    }

    public void unWriteLock() {
        this.reentrantReadWriteLock.writeLock().unlock();
    }

    public void readLock() {
        this.reentrantReadWriteLock.readLock().lock();
    }

    public void unReadLock() {
        this.reentrantReadWriteLock.readLock().unlock();
    }


}
