public class OrderManager<E> {
    private OrderQueue<Order> orderQueue; // Queue to manage orders in PROCESSING
    private OrderStack<Order> orderHistory; // Stack to track last order
    private OrderStore<Order> orderStore;

    // Initialize the order manager
    public OrderManager() {
        this.orderQueue = new OrderQueue<>();
        this.orderHistory = new OrderStack<>();
        this.orderStore = new OrderStore<>();
    }
    public OrderStore<Order> getOrderStore() {
        return orderStore;
    }
    
    /**
     * Add a new order to the processing queue.
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orderQueue.enqueue(order); // Add order to the queue
        order.setStatus(Order.OrderStatus.PROCESSING); // Mark as processing
    }

    /**
     * Process the next order in the queue.
     * @return The processed order, or null if the queue is empty.
     */
    public Order processOrder() {
        if (!orderQueue.isEmpty()) {
            Order order = orderQueue.dequeue(); // Get the next order
            order.setStatus(Order.OrderStatus.COMPLETED); // Mark as completed
            orderHistory.push(order); // Add to history
            orderStore.add(order);
            return order;
        }
        return null; // No orders to process
    }

    /**
     * Get the last completed order from the history.
     * @return The last completed order, or null if history is empty.
     */
    public Order getLastCompletedOrder() {
        return orderHistory.peek();
    }

    /**
     * Search for an order by ID
     * @param orderId The order ID to search for.
     * @return The found order, or null if not found.
     */
    public Order searchOrder(int orderId) {
        // Search in the queue first
        Order foundOrder = orderQueue.search(orderId);
        if (foundOrder != null) {
            return foundOrder;
        }
        // Search in the order store if not found in the queue
        Order foundOrderStore = orderStore.search(orderId);
        if (foundOrderStore != null) {
            return foundOrderStore;
        }
    
        return null; // No match found in both queue and store
    }
    public Order removeOrder(int orderId) {
        // First try to remove from OrderStore
        Order removedOrder = orderStore.remove(orderId);

        if (removedOrder != null) {
            // If order was found and removed from store, also remove from history stack
            OrderStack<Order> tempStack = new OrderStack<>();

            // Pop and store orders until find the matching one
            while (!orderHistory.isEmpty()) {
                Order order = orderHistory.pop();
                if (order.getOrderId() != orderId) {
                    tempStack.push(order);
                }
            }
            // Restore remaining orders back to original stack
            while (!tempStack.isEmpty()) {
                orderHistory.push(tempStack.pop());
            }
            return removedOrder;
        }

        return null; // Order not found
    }
    
}
