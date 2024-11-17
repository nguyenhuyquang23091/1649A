public class OrderStack<E> implements Stack<E> {
    // Node class to represent each element in the stack
    private class Node {
        E data; // Data stored in the node
        Node next; // Pointer to the next node

        // Initialize a new node
        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // Top of the stack
    private int size; // Number of elements in the stack

    // Initialize an empty stack
    public OrderStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Push a new element onto the stack.
     * @param element The element to push.
     */
    public void push(E element) {
        Node newNode = new Node(element); // Create a new node
        newNode.next = top; // Link the new node to the current top
        top = newNode; // Update the top to the new node
        size++;
    }

    /**
     * Remove and return the top element of the stack.
     * @return The top element, or null if the stack is empty.
     */
    public E pop() {
        if (isEmpty()) return null; // Empty stack
        E data = top.data; // Get the top element
        top = top.next; // Move the top to the next node
        size--;
        return data;
    }

    /**
     * Return the top element without removing it.
     * @return The top element, or null if the stack is empty.
     */
    public E peek() {
        return isEmpty() ? null : top.data;
    }

    /**
     * Search for an order in the stack by ID and status.
     * @param orderId The order ID to search for.
     * @param status The status of the order.
     * @return The matching order, or null if not found.
     */
    /**
     * Check if the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Get the number of elements in the stack.
     * @return The size of the stack.
     */
    public int size() {
        return size;
    }


}
