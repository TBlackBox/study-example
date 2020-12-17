package jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class VolatileTest3 {
    private static Config config = null;
    private static volatile boolean initialized = false;

    public static void main(String[] args) {
        // 线程1负责初始化配置信息
        new Thread(() -> {
        	System.out.println("开始初始化");
            config = new Config();
            config.name = "config";
            initialized = true;
            System.out.println("初始化完成");
        }).start();

        // 线程2检测到配置初始化完成后使用配置信息
        new Thread(() -> {
            while (!initialized) {
            	System.out.println("哈哈");
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
            }

            // do sth with config
            String name = config.name;
            System.out.println("赋值");
        }).start();
    }
}

class Config {
    String name;
}