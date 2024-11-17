public class BookStore<E extends Comparable<E>> implements BookStorage<E> {
    private Object[] data; // Array to store books
    private int size; // Current number of books
    private static final int DEFAULT_CAPACITY = 10; // Initial capacity

    // Initialize an empty bookstore with default capacity
    public BookStore() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Add a new book to the store.
     * @param element The book to add.
     */
    @Override
    public void add(E element) {
        if (size == data.length) {
            grow(); // Expand storage if capacity is reached
        }
        data[size] = element;
        size++;
    }

    /**
     * Retrieve a book by its index.
     * @param index The index of the book.
     * @return The book at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("No Id found");
        }
        return (E) data[index];
    }

    /**
     * Remove a book by its index.
     * @param index The index of the book to remove.
     * @return The removed book.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("No Id found");
        }
        E removedBook = (E) data[index];
        // Shift elements to fill the gap
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedBook;
    }

    /**
     * Update a book's information by its index.
     * @param index The index of the book.
     * @param newInfo The updated book information.
     * @return The old book information.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    @Override
    public E update(int index, E newInfo) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("No Id found");
        }
        E updatedBook = (E) data[index];
        data[index] = newInfo;
        return updatedBook;
    }

    /**
     * Sort books by their authors using bubble sort.
     */
    @Override
    public void authorSort() {
        if (size <= 1) return; // No sorting needed for 0 or 1 book
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                @SuppressWarnings("unchecked")
                Book current = (Book) data[j];
                @SuppressWarnings("unchecked")
                Book next = (Book) data[j + 1];
                if (current.getAuthor().compareTo(next.getAuthor()) > 0) {
                    // Swap books
                    Object temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sort books by their quantity using bubble sort.
     */
    @Override
    public void quanitySort() {
        if (size <= 1) return; // No sorting needed for 0 or 1 book
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                @SuppressWarnings("unchecked")
                Book current = (Book) data[j];
                @SuppressWarnings("unchecked")
                Book next = (Book) data[j + 1];
                if (current.getQuantity() > next.getQuantity()) {
                    // Swap books
                    Object temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void displayAll() {
        if (this.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + get(i));
            }
        }
    }

    /**
     * Get the number of books in the store.
     * @return The size of the store.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the bookstore is empty.
     * @return True if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Expand the storage capacity of the bookstore.
     */
    private void grow() {
        Object[] newData = new Object[data.length * 2]; // Double the capacity
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Custom iterator for iterating over books in the store.
     * @return A new iterator instance.
     */
    public newIterator iterator() {
        return new newIterator();
    }

    public class newIterator {
        private int currentIndex = 0; // Tracks the current index

        /**
         * Check if there are more elements to iterate over.
         * @return True if more elements exist, false otherwise.
         */
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Get the next element in the iteration.
         * @return The next book.
         * @throws RuntimeException If there are no more elements.
         */
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements");
            }
            return (E) data[currentIndex++];
        }

    }

}
