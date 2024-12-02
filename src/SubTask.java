import java.util.ArrayList;
import java.util.Scanner;

public class SubTask extends Task {
    Scanner scanner = new Scanner(System.in);
    public String description;
    StatusOfTasks subTaskStatus = StatusOfTasks.NEW;

    @Override
    public String toString() {
        String result = description;
        return result;
    }
}
