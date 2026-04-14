public class MyQueue {
    class Node {
        String data;
        Node next;
        Node(String data) {
            this.data = data;
        }
    }
    Node front, rear;
    public void enqueue(String value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }
    public String dequeue() {
        if (front == null) return "EMPTY";
        String val = front.data;
        front = front.next;
        if (front == null) rear = null;
        return val;
    }
    public void display() {
        Node temp = front;
        if (temp == null) {
            System.out.println("Queue empty");
            return;
        }
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}