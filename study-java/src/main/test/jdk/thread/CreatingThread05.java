package jdk.thread;

import java.util.Timer;
import java.util.TimerTask;

public class CreatingThread05 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 每隔1秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }, 0 , 1000);
        
    }
}