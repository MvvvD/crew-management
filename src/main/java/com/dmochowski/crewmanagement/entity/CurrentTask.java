package com.dmochowski.crewmanagement.entity;

//workaround for json body. Probably there is a better way to do it :D
public class CurrentTask {
    String currentTask;

    public CurrentTask() {
    }

    public CurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }
}
