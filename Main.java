import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static OrderManager<Order> orderManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        orderManager = new OrderManager<>();
        BookStore<Book> bookStore = new BookStore<>();

        while (running) {
            try {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        adminMenu(scanner, bookStore);
                        break;
                    case 2:
                        customerMenu(scanner, bookStore);
                        break;
                    case 3:
                        System.out.println("Exiting the system...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void adminMenu(Scanner scanner, BookStore<Book> bookStore) {
        boolean adminRunning = true;
        while (adminRunning) {
            try {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Manage Books");
                System.out.println("2. Manage Orders");
                System.out.println("3. Back to Main Menu");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        manageBooks(scanner, bookStore);
                        break;
                    case 2:
                        manageOrders(scanner);
                        break;
                    case 3:
                        adminRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred in Admin Menu: " + e.getMessage());
            }
        }
    }

    private static void manageBooks(Scanner scanner, BookStore<Book> bookList) {
        boolean bookRunning = true;

        while (bookRunning) {
            try {
                System.out.println("\n=== Managing Books ===");
                System.out.println("1.1 Add Book");
                System.out.println("1.2 Display All Books");
                System.out.println("1.3 Update Book");
                System.out.println("1.4 Sort Book By Quantity");
                System.out.println("1.5 Sort Book By Author");
                System.out.println("1.6 Delete Book");
                System.out.println("5. Back to Admin Menu");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1.1":
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        bookList.add(new Book(title, author, quantity));
                        System.out.println("Book added successfully.");
                        break;

                    case "1.2":
                        System.out.println("\n=== List of Books ===");
                        bookList.displayAll();
                        break;
                        

                    case "1.3":
                        System.out.print("Enter the id of the book to update: ");
                        int updateIndex = Integer.parseInt(scanner.nextLine());

                        if (updateIndex > 0 && updateIndex <= bookList.size()) {
                            System.out.print("Enter new name: ");
                            String newTitle = scanner.nextLine();
                            System.out.print("Enter new author: ");
                            String newAuthor = scanner.nextLine();
                            System.out.print("Enter quantity: ");
                            int newQuantity = Integer.parseInt(scanner.nextLine());

                            bookList.update(updateIndex - 1, new Book(newTitle, newAuthor, newQuantity));
                            System.out.println("Book updated successfully.");
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;

                    case "1.4":
                        System.out.println("Sorting books by quantity...");
                        bookList.quanitySort();
                        System.out.println("\n=== Books Sorted by Quantity ===");
                        for (int i = 0; i < bookList.size(); i++) {
                            System.out.println((i + 1) + ". " + bookList.get(i));
                        }
                        break;

                    case "1.5":
                        System.out.println("Sorting books by author...");
                        bookList.authorSort();
                        System.out.println("\n=== Books Sorted by Author ===");
                        for (int i = 0; i < bookList.size(); i++) {
                            System.out.println((i + 1) + ". " + bookList.get(i));
                        }
                        break;

                    case "1.6":
                        System.out.print("Enter the index of the book to delete: ");
                        int deleteIndex = Integer.parseInt(scanner.nextLine());
                        if (deleteIndex > 0 && deleteIndex <= bookList.size()) {
                            bookList.remove(deleteIndex - 1);
                            System.out.println("Book deleted successfully.");
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;

                    case "5":
                        bookRunning = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Book index out of range. Try again.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void manageOrders(Scanner scanner) {
        boolean orderRunning = true;
        while (orderRunning) {
            try {
                System.out.println("\n=== Order Management ===");
                System.out.println("1. Process Order");
                System.out.println("2. View Last Completed Order");
                System.out.println("3. Search Order");
                System.out.println("4. Display All Orders");
                System.out.println("5. Remove Order");
                System.out.println("6. Back to Admin Menu");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        Order processed = orderManager.processOrder();
                        if (processed != null) {
                            System.out.println("Processed order: " + processed);
                        } else {
                            System.out.println("No orders to process.");
                        }
                        break;

                    case 2:
                        Order lastCompleted = orderManager.getLastCompletedOrder();
                        if (lastCompleted != null) {
                            System.out.println("Last completed order: " + lastCompleted);
                        } else {
                            System.out.println("No completed orders.");
                        }
                        break;

                    case 3:
                        searchOrder(scanner);
                        break;

                    case 4:
                        displayAllOrders();
                        break;

                    case 5:
                        removeOrder(scanner);
                        break;

                    case 6:
                        orderRunning = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred in Order Management: " + e.getMessage());
            }
        }
    }

    private static void displayAllOrders() {
        try {
            OrderStore<Order> orderStore = orderManager.getOrderStore();
            if (orderStore.isEmpty()) {
                System.out.println("No orders available.");
                return;
            }
            System.out.println("\n=== All Orders ===");
            orderStore.displayorder();
        } catch (Exception e) {
            System.out.println("Error displaying orders: " + e.getMessage());
        }
    }

    private static void removeOrder(Scanner scanner) {
        System.out.print("Enter order ID to remove: ");
                int orderId = Integer.parseInt(scanner.nextLine());
                Order removed = orderManager.removeOrder(orderId);
                if (removed != null) {
                        System.out.println("Order removed successfully:");
                        System.out.println("Order ID: " + removed.getOrderId());
                        System.out.println("Status: " + removed.getStatus());
                        System.out.println("Customer: " + removed.getCustomer().getName());
                    } else {
                        System.out.println("Order not found with ID: " + orderId);
                    }
    }

    private static void customerMenu(Scanner scanner, BookStore<Book> bookStore) {
        boolean customerRunning = true;
        while (customerRunning) {
            try {
                System.out.println("\n=== Customer Menu ===");
                System.out.println("1. View Books");
                System.out.println("2. Place Order");
                System.out.println("3. View Last Order");
                System.out.println("4. Back to Main Menu");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayBooks(bookStore);
                        break;
                    case 2:
                        placeOrder(scanner, bookStore);
                        break;
                    case 3:
                        Order viewlastorder = orderManager.getLastCompletedOrder();
                        if ( viewlastorder != null){
                            System.out.println("Last order completed" + viewlastorder);
                        } else{
                            System.out.println("No order found.");
                        }
                        break;

                    case 4:
                        customerRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred in Customer Menu: " + e.getMessage());
            }
        }
    }

    private static void displayBooks(BookStore<Book> bookStore) {
        try {
            System.out.println("\n=== Available Books ===");
            if (bookStore.isEmpty()) {
                System.out.println("No books available.");
                return;
            }
            for (int i = 0; i < bookStore.size(); i++) {
                System.out.println((i + 1) + ". " + bookStore.get(i));
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying books: " + e.getMessage());
        }
    }


    private static void placeOrder(Scanner scanner, BookStore<Book> bookStore) {
        try {
            if (bookStore.isEmpty()) {
                System.out.println("No books available to order.");
                return;
            }

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            int phone = Integer.parseInt(scanner.nextLine());

            Customer customer = new Customer(name, address, phone);
            BookStore<Book> orderedBooks = new BookStore<>();

            displayBooks(bookStore);
            System.out.println("Enter Book Id:");
            int bookchoice = Integer.parseInt(scanner.nextLine()) - 1;

            if (bookchoice < 0 || bookchoice >= bookStore.size()) {
                System.out.println("Invalid Book Id.");
                return;
            }
            Book selectedBook = bookStore.get(bookchoice);

            System.out.print("Enter quantity for \"" + selectedBook.getTitle() + "\": ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity > selectedBook.getQuantity()) {
                System.out.println("Insufficient stock. Available: " + selectedBook.getQuantity());
                return;
            }
            selectedBook.setQuanity(selectedBook.getQuantity() - quantity);
            orderedBooks.add(new Book(selectedBook.getTitle(), selectedBook.getAuthor(), quantity));

            Order newOrder = new Order(customer, orderedBooks);
            orderManager.addOrder(newOrder);
            System.out.println("Order added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        } catch (Exception e) {
            System.out.println("AA Journey to RemembeA Journey to Rememben error occurred while placing order: " + e.getMessage());
        }
    }

    private static void searchOrder(Scanner scanner) {
        try {
            System.out.print("Enter order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine());

            Order found = orderManager.searchOrder(orderId);
            if (found != null) {
                 System.out.println("\n=== Order Details ===");
                 System.out.println(found.toString()); 
            } else {
                System.out.println("Order not found with ID: " + orderId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred while searching for order: " + e.getMessage());
        }
    }
}
