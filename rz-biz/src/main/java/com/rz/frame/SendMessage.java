package com.rz.frame;

import com.rz.frame.utils.SleepUtils;
import org.springframework.scheduling.annotation.Async;

public class SendMessage {

    @Async
    public  void sentMsg(){
        System.out.println(2);
        SleepUtils.SleepSecond(5);
        System.out.println(3);
    }
}
