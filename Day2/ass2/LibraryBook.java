package assignment02;
import java.util.GregorianCalendar;
public class LibraryBook extends Book {
    private String holder;
    private GregorianCalendar dueDate;
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
    }
    public String getHolder(){
        return this.holder;
    }
    public void setHolder(String holder) {
        this.holder = holder;
    }
    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }

    // Method to check out the book
    public void checkOut(String person, GregorianCalendar due) {
        this.holder = person;
        this.dueDate = due;
    }

    // Method to check the book in
    public void checkIn() {
        this.holder = null;
        this.dueDate = null;
    }
}
