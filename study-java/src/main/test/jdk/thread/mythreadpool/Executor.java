package jdk.thread.mythreadpool;

public interface Executor {
    void execute(Runnable command);
}
