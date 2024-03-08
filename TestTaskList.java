/*
Evan Lonczak
I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestTaskList<E> {

    private TaskList<E> toDoList = new TaskList<>();
    private Scanner scan = new Scanner(System.in);

    //Method to show the user menu and open a scanner so the user can interact with the program.
    public void printMenu() {
        System.out.println("~~~ TO-DO List Program ~~~");
        int input = 0;
        while(input != 8) {
            toDoList.showActiveTasks();
            System.out.println(
                    "To add a new task without priority information, press 1.\n" +
                    "To add a new task with a priority information, press 2.\n" +
                    "To cross off the task at the top of the list, press 3.\n" +
                    "To cross off a certain task in the list, press 4.\n" +
                    "To see the top 3 highest priority tasks, press 5.\n" +
                    "To see the completed tasks, press 6.\n" +
                    "To see the all tasks that has been completed or still active, press 7.\n" +
                    "To quit the program, press 8.");

            try {
                input = scan.nextInt();
                if (input > 8 || input < 1) {
                    System.out.println("Error! Please enter a number between 1 and 8 (included).");
                }
                processMenuItem(input);
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Please enter number between 1 and 8 (included).");
                scan.nextLine();
            }
        }
    }

    //Helper method that takes in a user input int menuItem, and calls the correct functions to make that menu option
    //correctly occur. Returns true if successful, false otherwise.
    private boolean processMenuItem(int menuItem) {
        if(menuItem == 1) {
            System.out.println("Please enter the task description:");
            scan.nextLine();
            String taskName = scan.nextLine();
            toDoList.createTask((E)taskName);
            System.out.println("Successfully entered the task to the to-do list!");
        }
        if(menuItem == 2){
            System.out.println("Please enter the task description:");
            scan.nextLine();
            String taskName = scan.nextLine();
            System.out.println("Please enter a priority number (1 indicates highest priority, increasing\n" +
                    "numbers show lower priority) :");
            int prio = scan.nextInt();
            toDoList.createTask((E)taskName,prio);
            System.out.println("Successfully entered the task to the to-do list!");
        }
        if(menuItem == 3) {
            if(toDoList.getActive().getSize() > 0) {
                System.out.println("Task is completed and removed from the list: " + toDoList.getActive().peek());
                toDoList.crossOffMostUrgent();
                System.out.println("Successfully removed the most urgent task/top of the list task!");
            }
            else System.out.println("There are no tasks to cross off!");
        }
        if(menuItem == 4) {
            System.out.println("Please enter the task number you would like to cross off the list :");
            int taskNum = scan.nextInt();
            if(!toDoList.crossOffTask(taskNum)){
                System.out.println("Unsuccessful operation! Please try again!");
            }
            else{
                System.out.println("Successfully removed the task number: " + taskNum);
            }
        }
        if(menuItem == 5){
            if(toDoList.getActive().getSize() == 0){
                System.out.println("There are no tasks to print!");
            }else {
                System.out.println("Top 3 highest priority tasks:");
                System.out.println("------------------------------");
                System.out.println("Printing Top Three Tasks...");
                toDoList.printTopThreeTasks();
            }
        }
        if(menuItem == 6){
            if(toDoList.getCompleted().getSize() == 0){
                System.out.println("There are no completed tasks to show!");
            }
            else {
                System.out.println("Completed Tasks\n" +
                        "--------------------");
                toDoList.showCompletedTasks();
            }
        }
        if(menuItem == 7){
            if(toDoList.getAll().getSize() == 0){
                System.out.println("There are no tasks to show!");
            }
            else
                toDoList.showAllTasks();
        }
        return true;
    }

    public static void main(String[] args) {
       TestTaskList t = new TestTaskList();
       t.printMenu();
    }

}
