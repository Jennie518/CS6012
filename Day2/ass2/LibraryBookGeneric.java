package assignment02;
import java.util.GregorianCalendar;
public class LibraryBookGeneric<Type> extends Book {
    private Type holder;
    private GregorianCalendar dueDate;
    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
    }
    public Type getHolder(){
        return this.holder;
    }
    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }

    // Method to check out the book
    public void checkOut(Type person, GregorianCalendar due) {
        this.holder = person;
        this.dueDate = due;
    }

    // Method to check the book in
    public void checkIn() {
        this.holder = null;
        this.dueDate = null;
    }
}
