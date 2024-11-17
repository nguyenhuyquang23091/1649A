public class OrderStore<E> implements OrderStorage<E> {
    private Object[] data; // Array to store orders
    private int size; // Current number of orders
    private static final int DEFAULT_CAPACITY = 10; // Initial capacity

    // Initialize an empty orderstore with default capacity
    public OrderStore() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    @Override
    public void add(E order) {
        if(size == data.length) {
            grow();
        }
        data[size++] = order;


    }
    @Override
    public E search(int orderId) {
        // Linear search through all elements
        for (int i = 0; i < size; i++) {
            Order order = (Order) data[i];
            // Compare with int since getOrderId() returns int
            if (order.getOrderId() == orderId) {
                return (E) order;
            }
        }
        return null; // No match found
    }

    @Override
    public E remove(int orderId) {
        for (int i = 0; i < size; i++) {
            Order order = (Order) data[i];
            if (order.getOrderId() == orderId) {
                E removedOrder = (E) order;

                // Shift remaining elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }

                // Clear the last element and decrease size
                data[size - 1] = null;
                size--;

                return removedOrder;
            }
        }
        return null;
    }

    
    @Override
    public void displayorder() {
    if (isEmpty()) {
        System.out.println("No orders available.");
        return;
    }
    System.out.println("\n=== Order List ===");
    for (int i = 0; i < size; i++) {
        Order order = (Order) data[i];
        System.out.println("\nOrder #" + (i + 1));
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Status: " + order.getStatus());
        System.out.println("-----------------");
    }
}

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }
    private void grow() {
        Object[] newData = new Object[data.length * 2]; // Double the capacity
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
