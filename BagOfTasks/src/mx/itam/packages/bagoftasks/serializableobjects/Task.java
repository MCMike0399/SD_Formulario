package mx.itam.packages.bagoftasks.serializableobjects;

import java.io.Serializable;

public class Task implements Serializable {

    private String taskId;
    private String requirementId;
    private long length; // in ms
    private String output;

    public Task(String taskId, String requirementId, long length) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public long getLength() {
        return length;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

}
