package ThreadPools.PoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new WorkerThread(i));
        }

        executorService.shutdown();
    }

}


/**
 * 
 * Benefits of Thread Pools 🌟 
Resource Management: Limit the number of threads to prevent system overload. 🛑 
Performance Improvement: Reuse existing threads instead of creating new ones. ⚡ 
Predictability: Control thread creation and scheduling for better application behavior. 📊 
Task Management: Queuing, scheduling, and monitoring tasks becomes streamlined. 📋 
 */