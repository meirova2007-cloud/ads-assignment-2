public class MyStack {
    class Node {
        String data;
        Node next;
        Node(String data) {
            this.data = data;
        }
    }
    Node top;
    public void push(String value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }
    public String pop() {
        if (top == null) return "Nothing to undo";
        String val = top.data;
        top = top.next;
        return val;
    }
    public String peek() {
        if (top == null) return "No history";
        return top.data;
    }
}