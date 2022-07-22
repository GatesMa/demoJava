package conductor;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class LeaderRatifyWorker implements Worker {
    private String taskDefName;
    public LeaderRatifyWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }
    @Override
    public String getTaskDefName() {
        return taskDefName;
    }
    @Override
    public TaskResult execute(Task task) {
        System.out.printf("Executing %s%n", taskDefName);
        System.out.println("staffName:" + task.getInputData().get("staffName"));
        System.out.println("staffDepartment:" + task.getInputData().get("staffDepartment"));
        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);
        //Register the output of the task
        result.getOutputData().put("outputKey1", "value");
        result.getOutputData().put("oddEven", 1);
        result.getOutputData().put("mod", 4);
        result.getOutputData().put("leaderAgree", "yes");
        result.getOutputData().put("leaderDisagree", "no");
        return result;
    }
}