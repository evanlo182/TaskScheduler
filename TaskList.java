/*
Evan Lonczak
I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.Iterator;

public class TaskList<E> {
    private ListQueue<E> all;
    private ListQueue<E> completed;
    private ListQueue<E> active;
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    private int HIGH_PRIORITY = 1;

    //Basic constructor for a TaskList object, intializes all queues.
    public TaskList(){
        all = new ListQueue<>();
        completed = new ListQueue<>();
        active = new ListQueue<>();
    }

    //takes in E item and creates a task with that description. Returns true if successful, false otherwise
    public boolean createTask(E item){
        if(item == null) {return false;}
        active.offer(item,LOW_PRIORITY);
        all.offer(item,LOW_PRIORITY);
        return true;
    }

    //Same as previous create task, this time takes in an int priority that replaces the default priority in the
    //above function. Same return conditions.
    public boolean createTask(E item, int priority) {
        if(item == null) {return false;}
        active.offer(item,priority);
        all.offer(item,priority);
        return true;
    }

    /*
    The 6 functions below are all basic getters and setters for the 3 queues named active, all, and completed
     */
    public ListQueue<E> getAll() {
        return all;
    }

    public void setAll(ListQueue<E> all) {
        this.all = all;
    }

    public ListQueue<E> getCompleted() {
        return completed;
    }

    public void setCompleted(ListQueue<E> completed) {
        this.completed = completed;
    }

    public ListQueue<E> getActive() {
        return active;
    }

    public void setActive(ListQueue<E> active) {
        this.active = active;
    }

    //Prints out the top three tasks. If there are less than 3 tasks, it will just print out however many tasks are in
    //the queue.
    public void printTopThreeTasks() {
        Iterator<E> q = all.iterator();
        int count = 1;
        while(q.hasNext() && count < 4){
            System.out.println(count + ". " + q.next());
            count++;
        }
    }

    //Helper function that will print all tasks in a given queue "queue". Does nothing if the queue is null.
    private void printTasks(ListQueue<E> queue){
        if(queue.getFront() == null){
            return;
        }
        Iterator<E> q = queue.iterator();
        int count = 1;
        while(q.hasNext()){
            System.out.println(count + ". " + q.next());
            count++;
        }
    }
    //Calls the printTasks() method to print all tasks in the Active queue. If it is empty, it prints a statement saying so.
    public void showActiveTasks() {
        if(active.getFront() == null) {
            System.out.println("==> Currently there are NO items in the To-Do List");
        }
        else {
            System.out.println("Current TO-DO List: \n" + "------------------");
            printTasks(active);
        }
    }
    //Calls printTasks method to print tasks in the "All" queue. If no tasks, it does nothing.
    public void showAllTasks() {
        System.out.println("All of the Tasks - Both completed and active \n" +
                "---------------------------------------------");
        printTasks(all);
    }
    //Calls printTasks to print all tasks in the "Completed" queue. If empty, does nothing.
    public void showCompletedTasks(){
        printTasks(completed);
    }

    //Removes the top task from the "Active" queue. Returns true if successful, false otherwise.
    public boolean crossOffMostUrgent() {
        if(active == null) {
            return false;
        }
        E mostUrgent = active.poll();
        completed.offer(mostUrgent, HIGH_PRIORITY);
        return true;
    }

    //Removes the task at the input position int taskNumber. If the number is invalid, returns false, otherwise,
    // it removes it and returns true.
    public boolean crossOffTask(int taskNumber) {
        if (taskNumber > active.getSize() || taskNumber < 1) {
            return false;
        }

        if(taskNumber == 1) {
            return crossOffMostUrgent();
        }
        ListQueue<E> temp = getActive();
        for (int i = 0; i < taskNumber-1; i++) {
            temp.poll();
        }
        completed.offer(temp.peek(), HIGH_PRIORITY);
        active.remove(temp.getFront());
        return true;
    }

}
