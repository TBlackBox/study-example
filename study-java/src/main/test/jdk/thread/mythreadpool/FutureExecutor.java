package jdk.thread.mythreadpool;

import java.util.concurrent.Callable;

public interface FutureExecutor extends Executor {
    <T> Future<T> submit(Callable<T> command);
}
