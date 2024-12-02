import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerForTasks {
    Scanner scanner = new Scanner(System.in);
    private final HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private Integer id = 1;

    public void getCommand() {
        while (true) {
            System.out.println("Что бы вы хотели сделать ?");
            printMenu();
            String command = scanner.nextLine().trim();
                switch (command) {
                    case "1":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            continue;
                        }
                        printAllTasks();
                        break;
                    case "2":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            return;
                        }
                        deleteAllTasks();
                        break;
                    case "3":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            return;
                        }
                        printTaskById();
                        break;
                    case "4":
                        addTask();
                        break;
                    case "5":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            return;
                        }
                        setStatus();
                        break;
                    case "6":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            return;
                        }
                        deleteTaskById();
                        break;
                    case "7":
                        if(isAnyTask()) {
                            System.out.println("Сейчас нет задач.");
                            return;
                        }
                        printSubTasksByTask();
                        break;
                    case "8":
                        System.out.println("Завершаем программу.");
                        return;
                    default:
                        System.out.println("Введена неправильная команда, попробуйте еще раз!");
            }
        }
    }

    public void printAllTasks() {
        for (Integer id : epicHashMap.keySet()) {
            System.out.println(id + "." + epicHashMap.get(id));
        }
    }
    public void deleteAllTasks() {
        epicHashMap.clear();
        id = 1;
    }
    public void addTask() {
        System.out.println("Введите описание задачи.");
        String descriptionOfTask = scanner.nextLine();
        if (stringIsEmpty(descriptionOfTask)) {
            System.out.println("Вы ввели пустую строку, попробуйте еще раз!");
            return;
        }
        Epic epic = new Epic();
        epic.description = descriptionOfTask;
        System.out.println("Вводите задачи подряд нажимая enter, в случае если вы хотите закончить ничего не вводите.");
        while (true) {
            String descriptionOfSubTask = scanner.nextLine();
            if (stringIsEmpty(descriptionOfSubTask)) {
                break;
            }
            epic.addSubTask(descriptionOfSubTask);
        }
        epicHashMap.put(id, epic);
        id++;
    }
    public void deleteTaskById() {
        System.out.println("Какую задачу вы хотите удалить ?");
        printAllTasks();
        System.out.println("Выберите id от " + 1 + " до " + epicHashMap.size());
        Integer idNum = scanner.nextInt();
        scanner.nextLine();
        epicHashMap.remove(idNum);
    }
    public void printTaskById() {
        System.out.println("Какую задачу вы хотите вывести ?");
        printAllTasks();
        System.out.println("Выберите id от " + 1 + " до " + epicHashMap.size());
        Integer idNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println(idNum + "." + epicHashMap.get(idNum).description);
    }
    public void setStatus() {
        System.out.println("Какую задачу вы хотите обновить ?");
        printAllTasks();
        System.out.println("Выберите id от " + 1 + " до " + epicHashMap.size());
        Integer idNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Какую из подзадач вы хотите обновить ?");
        epicHashMap.get(idNum).printSubTasks();
        System.out.println("Выберите от " + 1 + " до " + epicHashMap.get(idNum).listOfSubTasks.size());
        int subTaskNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Выберите от 1 до 3");
        printStatusList();
        int statusNum = scanner.nextInt();
        scanner.nextLine();
        epicHashMap.get(idNum).setSubTasksStatus(subTaskNum, statusToSet(statusNum));
        epicHashMap.get(idNum).isEpicDone();
        epicHashMap.get(idNum).isSubTasksInProgress();
    }
    public void printSubTasksByTask() {
        System.out.println("Какие подзадачи вы хотите вывести ?");
        printAllTasks();
        System.out.println("Выберите id от " + 1 + " до " + epicHashMap.size());
        Integer idNum = scanner.nextInt();
        scanner.nextLine();
        epicHashMap.get(idNum).printSubTasks();
    }
    public void printMenu() {
        System.out.println("1 - Вывести все задачи.");
        System.out.println("2 - Удалить все задачи.");
        System.out.println("3 - Вывести задачу по индефекатору.");
        System.out.println("4 - Добавить задачу и подзадачи.");
        System.out.println("5 - Обновить задачу по индефикатору.");
        System.out.println("6 - Удалить задачу по индефекатору.");
        System.out.println("7 - Вывести все подзадачи определенной задачи.");
        System.out.println("8 - Завершить программу");
    }
    public void printStatusList() {
        System.out.println("1. Done");
        System.out.println("2. In progress");
        System.out.println("3. New");
    }
    public StatusOfTasks statusToSet(int numOfStatus) {
        switch (numOfStatus) {
            case 1:
                return StatusOfTasks.DONE;
            case 2:
                return StatusOfTasks.IN_PROGRESS;
            default:
                return StatusOfTasks.NEW;
        }
    }
    private boolean stringIsEmpty(String string) {
        return string.isEmpty();
    }
    public boolean isAnyTask() {
        return epicHashMap.isEmpty();
    }
}
