import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Epic extends Task {
    Scanner scanner = new Scanner(System.in);
    public String description = "";
    public final ArrayList<SubTask> listOfSubTasks = new ArrayList<>();
    private StatusOfTasks epicStatus = StatusOfTasks.NEW;

    public void addSubTask(String descriptionOfSubTask) {
        SubTask subTask = new SubTask();
        subTask.description = descriptionOfSubTask;
        listOfSubTasks.add(subTask);
    }
    public void printSubTasks() {
        printSubTasksByStatus(StatusOfTasks.NEW);
        printSubTasksByStatus(StatusOfTasks.IN_PROGRESS);
        printSubTasksByStatus(StatusOfTasks.DONE);
    }
    public void isSubTasksInProgress() {
        for (SubTask subTask : listOfSubTasks) {
            if (subTask.subTaskStatus == StatusOfTasks.IN_PROGRESS) {
                epicStatus = StatusOfTasks.IN_PROGRESS;
                return;
            }
        }
        epicStatus = StatusOfTasks.NEW;
    }
    public void printSubTasksByStatus(StatusOfTasks statusOfTasks) {
        System.out.println(statusOfTasks + " :");
        for (SubTask subTask : listOfSubTasks) {
            if (subTask.subTaskStatus == statusOfTasks) {
                System.out.println(subTask);
            }
        }
    }
    public void isEpicDone() {
        for (SubTask subTask : listOfSubTasks) {
            if (subTask.subTaskStatus != StatusOfTasks.DONE) {
                return;
            }
        }
        epicStatus = StatusOfTasks.DONE;
    }
    public void setSubTasksStatus(int subTask, StatusOfTasks statusOfTasks) {
        listOfSubTasks.get(subTask).subTaskStatus = statusOfTasks;
    }
    @Override
    public String toString() {
        String result = epicStatus + " : " + "Задача - " + description;
        return result.trim();
    }
}
