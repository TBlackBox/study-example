package jdk.thread;
public class CreatingThread09 {

    public static void main(String[] args) {
        new Thread(()-> {
            System.out.println("Runnable: " + Thread.currentThread().getName());
        }) {
            @Override
            public void run() {
                System.out.println("Thread: " + getName());
            }
        }.start();
    }
}