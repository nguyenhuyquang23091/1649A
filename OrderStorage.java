public interface OrderStorage<E> {
    void add(E order);
    E search(int orderId);
    E remove(int orderId);
    void displayorder();
    int size();
    boolean isEmpty();
}
