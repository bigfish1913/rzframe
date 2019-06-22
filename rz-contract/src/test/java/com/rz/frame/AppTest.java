package com.rz.frame;

import static org.junit.Assert.assertTrue;

import com.rz.frame.contract.RzClient;
import com.rz.frame.contract.RzRequset;
import com.rz.frame.contract.RzResponse;
import com.rz.frame.utils.JsonUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public static void main(String[] args) {
        RzRequset requset=new RzRequset();
        requset.setName("frz");
        while (true){
            long start = System.currentTimeMillis();
            RzResponse response = RzClient.getInstance().getRzList(requset);
            long end =System.currentTimeMillis();
            System.out.println(String.format("Response Msg:%s Cost time:%s ms",response.getRetMsg(),(end-start)));
        }
    
       
    }
}
