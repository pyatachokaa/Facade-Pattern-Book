import java.util.HashMap;
import java.util.Map;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class BookInventory {
    private Map<String, Book> books;

    public BookInventory() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    public Book findBookByTitle(String title) {
        return books.get(title);
    }

    public Book findBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    public boolean isBookAvailable(String title) {
        Book book = findBookByTitle(title);
        return book != null && book.isAvailable();
    }

    public void borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book '" + title + "' borrowed successfully.");
        } else {
            System.out.println("Book '" + title + "' is not available for borrowing.");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.setAvailable(true);
            System.out.println("Book '" + title + "' returned successfully.");
        } else {
            System.out.println("Book '" + title + "' not found in inventory.");
        }
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class UserManagement {
    private Map<String, User> users;

    public UserManagement() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User findUserByUsername(String username) {
        return users.get(username);
    }
}

class LibraryFacade {
    private BookInventory bookInventory;
    private UserManagement userManagement;

    public LibraryFacade() {
        this.bookInventory = new BookInventory();
        this.userManagement = new UserManagement();
    }

    public void addBook(Book book) {
        bookInventory.addBook(book);
    }

    public void addUser(User user) {
        userManagement.addUser(user);
    }

    public void borrowBook(String title) {
        bookInventory.borrowBook(title);
    }

    public void returnBook(String title) {
        bookInventory.returnBook(title);
    }

    public boolean searchBookByTitle(String title) {
        return bookInventory.findBookByTitle(title) != null;
    }

    public boolean searchBookByAuthor(String author) {
        return bookInventory.findBookByAuthor(author) != null;
    }

    public boolean checkBookAvailability(String title) {
        return bookInventory.isBookAvailable(title);
    }

    public User findUserByUsername(String username) {
        return userManagement.findUserByUsername(username);
    }
}

class Main {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacade();

        Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling");
        Book book2 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        libraryFacade.addBook(book1);
        libraryFacade.addBook(book2);

        User user1 = new User("user1");
        User user2 = new User("user2");
        libraryFacade.addUser(user1);
        libraryFacade.addUser(user2);

        libraryFacade.borrowBook("Harry Potter and the Philosopher's Stone");
        libraryFacade.returnBook("Harry Potter and the Philosopher's Stone");

        System.out.println("Search for 'Harry Potter and the Philosopher's Stone': "
                + libraryFacade.searchBookByTitle("Harry Potter and the Philosopher's Stone"));
        System.out.println("Search for books by 'J.K. Rowling': "
                + libraryFacade.searchBookByAuthor("J.K. Rowling"));

        System.out.println("Is 'The Great Gatsby' available? "
                + libraryFacade.checkBookAvailability("The Great Gatsby"));

        System.out.println("Find user 'user1': " + libraryFacade.findUserByUsername("user1").getUsername());
    }
}
