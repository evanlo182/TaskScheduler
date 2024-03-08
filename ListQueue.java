/*
Evan Lonczak
I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E> {
    private Node<E> front;
    private int size;
    public class Node<E>{
        private E data;
        private Node<E> next;
        private int priority;

        //Basic constructor for the Node class, takes in an E dataItem that is set to the variable data.
        //Initializes other variables.
        public Node(E dataItem){
            data = dataItem;
            priority = Integer.MAX_VALUE;
            next = null;
        }
        //Basic constructor for the Node class, takes in an E dataItem that is set to the variable data, and an int
        //priority. Initializes other variables.
        public Node(E dataItem, int priority){
            data = dataItem;
            this.priority = priority;
            next = null;
        }
        //Initializes all attributes of the Node class, based on the 3 input variables.
        public Node(E dataItem, Node<E> next, int priority){
            data = dataItem;
            this.next = next;
            this.priority = priority;
        }

        //Getter for data - returns the data of the node it is called on.
        public E getData(){return this.data;}
        //Getter for next - returns the next node of the current node.
        public Node<E> getNext() {return this.next;}

    }

    private class Iter implements Iterator<E>{
        private Node<E> next = front;

        //Returns true if the next attribute exists, false otherwise
        public boolean hasNext() { return next!=null; }

        //Returns the data of the next attribute, and updates it to the next node in the list.
        public E next() {
            if(next == null) {
                throw new NoSuchElementException("Next is null!");
            }
            E oldNext = next.data;
            next = next.next;
            return oldNext;
        }

        //Strictly throws UnsupportedOperationException
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    //start of ListQueue method code

    //Default constructor for ListQueue, intitalizes front and size.
    public ListQueue(){
        front = null;
        size = 0;
    }

    //Constructor that takes in a value for first, and initializes accordingly.
    public ListQueue(Node<E> first) {
        front = first;
        size = 1;
    }

    /*
    Following 4 methods are basic getters and setters for the front and size attributes.
     */
    public Node<E> getFront(){return front;}

    public void setFront(Node<E> newFront) {front = newFront;}

    public int getSize() { return size;}

    public void setSize(int newSize) { size = newSize;}

    //Returns the value stored in the first node of the list.
    public E peek(){ return front.data;}

    //Takes input variables E item and int priority, and adds an item to the queue with these attributes. Returns true
    // if successful, false otherwise.
    public boolean offer(E item, int priority) {
        if(item == null) { throw new NullPointerException();}
        Node<E> newNode = new Node<>(item, priority);
        if(front == null){
            front = newNode;
            size++;
            return true;
        }

        if(newNode.priority < front.priority){
            Node<E> temp = front;
            front = newNode;
            newNode.next = temp;
            size++;
            return true;
        }
        Node<E> current = front;
        while(current.next != null && priority >= current.next.priority){
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }

    //Takes input E item, and adds a node with this value to the end of the queue. Returns true if successful,
    //false otherwise.
    public boolean addRear(E item){
        if( item == null) { throw new NullPointerException();}
        Node<E> current = front;
        while(current.next != null){
            current = current.next;
        }
        current.next = new Node<>(item);
        size++;
        return true;
    }

    //Returns the data of the front node.
    public E poll(){
        E item = this.peek();
        if(item == null) { throw new NullPointerException();}
        if(size == 1) {
            front = null;
            size = 0;
        } else {
            front = front.next;
        }
        size--;
        return item;
    }

    //Takes input Node toBeRemoved, and removes it accordingly. Returns true if successful, false otherwise.
    public boolean remove(Node<E> toBeRemoved){
        if(toBeRemoved == null) { throw new NullPointerException();}

        if(front == toBeRemoved){
            front = front.next;
            size--;
            return true;
        }

        Node<E> current = front;
        while(current.next!=null){
            if(current.next == toBeRemoved){
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Iterator<E> iterator(){ return new Iter();}

}
