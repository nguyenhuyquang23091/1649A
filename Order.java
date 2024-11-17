public class Order {
    private int orderId;
    private Customer customer;
    private BookStore<Book> books;
    private OrderStatus status;
    private static int nextId = 1;

    public enum OrderStatus {
        PROCESSING,
        COMPLETED
    }

    public Order(Customer customer, BookStore<Book> books) {
        this.orderId = generateId();
        this.customer = customer;
        this.books = books;
        this.status = OrderStatus.PROCESSING;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BookStore<Book> getBooks() {
        return books;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    private static int generateId() {
        return nextId++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n")
                .append("Customer: ").append(customer.getName()).append("\n")
                .append("Status: ").append(status).append("\n")
                .append("Books in Order:\n");
        BookStore<Book>.newIterator iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            sb.append("- ").append(book).append("\n");
        }

        return sb.toString();
    }
}
