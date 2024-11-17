public interface BookStorage<E extends Comparable<E>> {
    void add(E element);

    E get(int index);

    E remove(int index);

    E update(int index, E element);
    void authorSort();
    void quanitySort();
    void displayAll();
    int size();

    boolean isEmpty();

}
