package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.*;


/**
 * Class representation of a library (a collection of library books).
 * 
 */
public class Library<T> {

  private ArrayList<LibraryBookGeneric<T>>library;


  public Library() {
    library = new ArrayList<LibraryBookGeneric<T>>();
  }

  /**
   * Add the specified book to the library, assume no duplicates.
   * 
   * @param isbn
   *          -- ISBN of the book to be added
   * @param author
   *          -- author of the book to be added
   * @param title
   *          -- title of the book to be added
   */
  public void add(long isbn, String author, String title) {
    library.add(new LibraryBookGeneric<T>(isbn, author, title));
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   * 
   * @param list
   *          -- list of library books to be added
   */
  public void addAll(ArrayList<LibraryBookGeneric<T>> list) {
    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author,
   * and title separated by tabs.
   * 
   * If file does not exist or format is violated, do nothing.
   * 
   * @param filename
   */
  public void addAll(String filename) {
    ArrayList<LibraryBookGeneric<T>> toBeAdded = new ArrayList<LibraryBookGeneric<T>>();

    try (Scanner fileIn = new Scanner(new File(filename))) {

      int lineNum = 1;

      while (fileIn.hasNextLine()) {
        String line = fileIn.nextLine();

        try (Scanner lineIn = new Scanner(line)) {
          lineIn.useDelimiter("\\t");

          if (!lineIn.hasNextLong()) {
            throw new ParseException("ISBN", lineNum);
          }
          long isbn = lineIn.nextLong();

          if (!lineIn.hasNext()) {
            throw new ParseException("Author", lineNum);
          }
          String author = lineIn.next();

          if (!lineIn.hasNext()) {
            throw new ParseException("Title", lineNum);
          }
          String title = lineIn.next();
          toBeAdded.add(new LibraryBookGeneric(isbn, author, title));
        }
        lineNum++;
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage() + " Nothing added to the library.");
      return;
    } catch (ParseException e) {
      System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   * 
   * If no book with the specified ISBN is in the library, returns null.
   * 
   * @param isbn
   *          -- ISBN of the book to be looked up
   */
  public T lookup(long isbn) {
    for (LibraryBookGeneric book : library){
      if (book.getIsbn() == isbn) {
        return (T) book.getHolder();
      }//because of type erasure
    }
    return null;
  }

  /**
   * Returns the list of library books checked out to the specified holder.
   * 
   * If the specified holder has no books checked out, returns an empty list.
   * 
   * @param holder
   *          -- holder whose checked out books are returned
   */
  public ArrayList<LibraryBookGeneric<T>> lookup(T holder) {
    ArrayList<LibraryBookGeneric<T>> BooksbyHolder = new ArrayList<>();
    for (LibraryBookGeneric<T> book : library){
      if (holder.equals(book.getHolder())) {
        BooksbyHolder.add(book);
      }
    }
    return BooksbyHolder;
  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   * 
   * If no book with the specified ISBN is in the library, returns false.
   * 
   * If the book with the specified ISBN is already checked out, returns false.
   * 
   * Otherwise, returns true.
   * 
   * @param isbn
   *          -- ISBN of the library book to be checked out
   * @param holder
   *          -- new holder of the library book
   * @param month
   *          -- month of the new due date of the library book
   * @param day
   *          -- day of the new due date of the library book
   * @param year
   *          -- year of the new due date of the library book
   * 
   */
  public boolean checkout(long isbn, T holder, int month, int day, int year) {
    for (LibraryBookGeneric book : library) {
      if (book.getIsbn() == isbn) {
        if (book.getHolder() == null) {
          book.checkOut(holder, new GregorianCalendar(year, month, day));
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }


  /**
   * Unsets the holder and due date of the library book.
   *
   * If no book with the specified ISBN is in the library, returns false.
   *
   * If the book with the specified ISBN is already checked in, returns false.
   *
   * Otherwise, returns true.
   *
   * @param isbn
   *          -- ISBN of the library book to be checked in
   */
  public boolean checkin(long isbn) {
    for (LibraryBookGeneric book : library) {
      if (book.getIsbn() == isbn) {
        if (book.getHolder() != null) {
          book.checkIn();
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Unsets the holder and due date for all library books checked out be the
   * specified holder.
   * 
   * If no books with the specified holder are in the library, returns false;
   * 
   * Otherwise, returns true.
   * 
   * @param holder
   *          -- holder of the library books to be checked in
   */
  public boolean checkin(T holder) {
    boolean foundBooksToCheckin = false;
    for (LibraryBookGeneric<T> book : library) {
      if (book.getHolder() != null && book.getHolder().equals(holder)) {
        book.checkIn();
        foundBooksToCheckin = true;
      }
    }
    return foundBooksToCheckin;
  }
  /**
   * Returns the list of library books, sorted by ISBN (smallest ISBN
   first).
   */

  public ArrayList<LibraryBookGeneric<T>> getInventoryList() {
    ArrayList<LibraryBookGeneric<T>> libraryCopy = new ArrayList<LibraryBookGeneric<T>>();
    libraryCopy.addAll(library);
    OrderByIsbn comparator = new OrderByIsbn();
    sort(libraryCopy, comparator);
    return libraryCopy;
  }

  /**
   * Returns the list of library books, sorted by author
   */
  public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
    // FILL IN -- do not return null
    ArrayList<LibraryBookGeneric<Type>> sortedBooks = new ArrayList<>();
    // Sort the list using a comparator that compares the authors
    Collections.sort(sortedBooks, new Comparator<LibraryBookGeneric<Type>>() {
      public int compare(LibraryBookGeneric<Type> book1, LibraryBookGeneric<Type> book2) {
        // Assuming there's a method getAuthor() in LibraryBookGeneric that returns the author's name
        return book1.getAuthor().compareToIgnoreCase(book2.getAuthor());
      }
    });
    return sortedBooks;
  }

  /**
   * Returns the list of library books whose due date is older than the
   input
   * date. The list is sorted by date (oldest first).
   *
   * If no library books are overdue, returns an empty list.
   */
  public ArrayList<LibraryBookGeneric<T>> getOverdueList(int month, int day, int year) {
    ArrayList<LibraryBookGeneric<T>> overdueBooks = new ArrayList<>();
    GregorianCalendar inputDate = new GregorianCalendar(year, month, day);
    for (LibraryBookGeneric<T> book : library) {
      if (book.getDueDate() != null && book.getDueDate().before(inputDate)) {
        overdueBooks.add(book);
      }
    }
    // Sort the overdueBooks list by date
    Collections.sort(overdueBooks, new Comparator<LibraryBookGeneric<T>>() {
      public int compare(LibraryBookGeneric<T> book1, LibraryBookGeneric<T> book2) {
        return book1.getDueDate().compareTo(book2.getDueDate());
      }
    });
    return overdueBooks;
  }

  /**
   * Performs a SELECTION SORT on the input ArrayList.
   * 1. Find the smallest item in the list.
   * 2. Swap the smallest item with the first item in the list.
   * 3. Now let the list be the remaining unsorted portion
   * (second item to Nth item) and repeat steps 1, 2, and 3.
   */
  private static <T> void sort(ArrayList<T> list, Comparator<T> c) {
    for (int i = 0; i < list.size() - 1; i++) {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      T temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }

  /**
   * Comparator that defines an ordering among library books using the
   ISBN.
   */
  protected class OrderByIsbn implements Comparator<LibraryBookGeneric<T>> {
    /**
     * Returns a negative value if lhs is smaller than rhs. Returns a positive
     * value if lhs is larger than rhs. Returns 0 if lhs 	and rhs are equal.
     */
    public int compare(LibraryBookGeneric<T> lhs,
                       LibraryBookGeneric<T> rhs) {
      return (int) (lhs.getIsbn() - rhs.getIsbn());
    }
  }

  /**
   * Comparator that defines an ordering among library books using the
   author, and book title as a tie-breaker.
   */
  protected class OrderByAuthor implements Comparator<LibraryBookGeneric<T>> {
    @Override
    public int compare(LibraryBookGeneric<T> book1, LibraryBookGeneric<T> book2) {
      int authorComparison = book1.getAuthor().compareTo(book2.getAuthor());
      // If authors are the same, then compare by title
      if (authorComparison == 0) {
        return book1.getTitle().compareTo(book2.getTitle());
      }
      return authorComparison;
    }
  }

  /**
   * Comparator that defines an ordering among library books using the
   due date.
   */
  protected class OrderByDueDate implements Comparator<LibraryBookGeneric<T>> {
    @Override
    public int compare(LibraryBookGeneric<T> book1, LibraryBookGeneric<T> book2) {
      return book1.getDueDate().compareTo(book2.getDueDate());
    }
  }

}

