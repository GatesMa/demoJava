package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * testThread
 *
 * @author by gatesma.
 */
public class TestThread {



    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                10,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100),
                new ThreadFactoryBuilder().setNameFormat("q15").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 0;i < 3;i++) {
                int finalI = i;
                threadPoolExecutor.submit(() -> {
                    System.out.println("start " + finalI);
                    if (finalI == 2) {
                        int j = 1 / 0;
                    }
                    System.out.println("end " + finalI);
                });
            }
        } catch (Exception e) {
            System.out.println("exception catch:" + e.getMessage());
        }




    }


}
