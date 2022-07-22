package conductor;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class ManagerRatifyWorker implements Worker {
    private String taskDefName;
    public ManagerRatifyWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }
    @Override
    public String getTaskDefName() {
        return taskDefName;
    }
    @Override
    public TaskResult execute(Task task) {
        System.out.printf("Executing %s\n", taskDefName);
        System.out.println("managerName:" + task.getInputData().get("managerName"));
        System.out.println("managerDepartment:" + task.getInputData().get("managerDepartment"));
        TaskResult result = new TaskResult(task);
        result.setStatus(TaskResult.Status.COMPLETED);
        //Register the output of the task
        result.getOutputData().put("managerAgree", String.valueOf(task.getInputData().get("managerName")));
        result.getOutputData().put("managerDisagree", String.valueOf(task.getInputData().get("managerDepartment")));

        return result;
    }
}