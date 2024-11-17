public class OrderQueue<E> implements Queue<E> {
    // Node class to represent each element in the queue
    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // Front of the queue
    private Node tail; // End of the queue
    private int size;  // Number of elements in the queue

    // Initialize an empty queue
    public OrderQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Add an element to the end of the queue.
     * @param e Element to add
     */
    @Override
    public void enqueue(E e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = tail = newNode; // First element in the queue
        } else {
            tail.next = newNode; // Append to end
            tail = newNode;
        }
        size++;
    }

    /**
     * Remove and return the front element of the queue.
     * @return Element at the front, or null if empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) return null; // Empty queue
        E data = head.data;
        head = head.next; // Move to the next element
        if (head == null) tail = null; // Queue becomes empty
        size--;
        return data;
    }

    /**
     * Get the front element without removing it.
     * @return Front element, or null if empty
     */
    @Override
    public E peek() {
        return isEmpty() ? null : head.data;
    }

    /**
     * Search for an order by ID and status.
     * @param orderId Order ID to search for
     * @return Matching order, or null if not found
     */
    public Order search(int orderId) {
        Node current = head;
        while (current != null) {
            Order order = (Order) current.data;
            if (order.getOrderId() == orderId) {
                return order; 
            }
            current = current.next;
        }
        return null; 
    }

    /**
     * Check if the queue is empty.
     * @return True if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Get the number of elements in the queue.
     * @return Queue size
     */
    @Override
    public int size() {
        return size;
    }
}
