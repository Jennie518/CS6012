package assignment02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library lib = new Library();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBookGeneric<Type>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe".getClass(), 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        var lib = new Library();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        ArrayList<LibraryBookGeneric<Type>> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        // test a medium library
        var lib = new Library();
        lib.addAll("Mushroom_Publishing.txt");
        var res = lib.checkout(9781843190004L, "Moyra Caldecott", 1, 1, 2023);
        assertTrue(res);
        ArrayList<LibraryBookGeneric<Type>> booksCheckedOut = lib.lookup("Moyra Caldecott");
        assertEquals(booksCheckedOut.get(0),new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2023, 1, 1));
        res = lib.checkin(9781843190004L);
        assertTrue(res);
        res = lib.checkin("Moyra Caldecott");
        assertFalse(res);
    }
    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        var lib = new Library();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));
        ArrayList<LibraryBookGeneric<Type>> booksCheckedOut1= lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(),patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(),new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }
//    @Test
////    public void sortAuthor(){
////
////    }

    @Test
    public void phoneNumberTest(){
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new Library<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(),patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }
    @Test
    public void testGetInventoryList() {
        var lib = new Library();
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        ArrayList<LibraryBookGeneric<Type>> inventory = lib.getInventoryList();

        // 检查列表是否按照ISBN排序
        for (int i = 0; i < inventory.size() - 1; i++) {
            long isbn1 = inventory.get(i).getIsbn();
            long isbn2 = inventory.get(i + 1).getIsbn();
            assertTrue(isbn1 < isbn2);
        }
    }
    @Test
    public void testGetOverdueList() {
        Library<String> lib = new Library<>();
        // 添加几本书
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");
        // 借出一些书，设置逾期日期
        lib.checkout(9780330351690L, "User1", 1, 1, 2022);  // This book have overdue
        lib.checkout(9780446580342L, "User2", 1, 1, 2023);  // This book not overdue

        // set due day 2023/6/1
        GregorianCalendar testDate = new GregorianCalendar(2023, GregorianCalendar.JUNE, 1);

        ArrayList<LibraryBookGeneric<String>> overdueBooks = lib.getOverdueList(GregorianCalendar.JUNE, 1, 2023);
        // 确认逾期列表包含正确的书
        assertTrue(overdueBooks.get(0).getIsbn() == 9780330351690L);
    }

    @Test
    public void testOrderByAuthor() {
        Library.OrderByAuthor authorComparator = new Library().new OrderByAuthor();

        LibraryBookGeneric<String> book1 = new LibraryBookGeneric<>(9780330351690L, "A", "Into the Wild");
        LibraryBookGeneric<String> book2 = new LibraryBookGeneric<>(9780446580342L, "D", "Simple Genius");

        assertTrue(authorComparator.compare(book1, book2) < 0, "A < J");

        // Testing tie-breaker (same author, different titles)
        LibraryBookGeneric<String> book3 = new LibraryBookGeneric<>(9780374292799L, "A", "Under the Banner of Heaven");
        assertTrue(authorComparator.compare(book1, book3) < 0);
    }

    @Test
    public void testOrderByDueDate() {
        Library.OrderByDueDate dueDateComparator = new Library().new OrderByDueDate();

        LibraryBookGeneric<String> book1 = new LibraryBookGeneric<>(9780330351690L, "Jon Krakauer", "Into the Wild");
        book1.checkOut("Patron1", new GregorianCalendar(2023, 1, 1));

        LibraryBookGeneric<String> book2 = new LibraryBookGeneric<>(9780446580342L, "David Baldacci", "Simple Genius");
        book2.checkOut("Patron2", new GregorianCalendar(2023, 1, 2));

        assertTrue(dueDateComparator.compare(book1, book2) < 0, "Book1 due date should be before Book2 due date");

        // Testing books with the same due date
        LibraryBookGeneric<String> book3 = new LibraryBookGeneric<>(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        book3.checkOut("Patron3", new GregorianCalendar(2023, 1, 1));

        assertTrue(dueDateComparator.compare(book1, book3) == 0, "Books with the same due date should be considered equal");
    }
}
