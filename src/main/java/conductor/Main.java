package conductor;

import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        TaskClient taskClient = new TaskClient();
        taskClient.setRootURI("http://9.208.244.47:8080/api/");       //Point this to the server API
        int threadCount = 2;         //number of threads used to execute workers.  To avoid starvation, should be same or more than number of workers
        Worker worker1 = new LeaderRatifyWorker("leaderRatify");
        Worker worker2 = new ManagerRatifyWorker("managerRatify");
        List<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        TaskRunnerConfigurer.Builder builder = new TaskRunnerConfigurer.Builder(taskClient,workers);
        TaskRunnerConfigurer coordinator = builder.withThreadCount(threadCount).build();
        //Start for polling and execution of the tasks
        coordinator.init();
//        taskClient.updateTask(TaskResult.newTaskResult());
    }

}
