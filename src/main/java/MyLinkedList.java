public class MyLinkedList {
    class Node {
        BankAccount data;
        Node next;
        Node(BankAccount data) {
            this.data = data;
        }
    }
    Node head;
    public void add(BankAccount acc) {
        Node newNode = new Node(acc);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }
    public void displayAll() {
        Node temp = head;

        while (temp != null) {
            temp.data.display();
            temp = temp.next;
        }
    }
    public BankAccount find(String username) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.username.equalsIgnoreCase(username)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}