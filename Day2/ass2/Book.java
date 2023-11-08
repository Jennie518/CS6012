package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class Book {

  private long isbn;

  private String author;

  private String title;

  public Book(long isbn, String author, String title) {
    this.isbn = isbn;
    this.author = author;
    this.title = title;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * @return the ISBN
   */
  public long getIsbn() {
    return this.isbn;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Two books are considered equal if they have the same ISBN, author, and title.
   * 
   * @param other
   *          -- the object begin compared with "this"
   * @return true if "other" is a Book and is equal to "this", false otherwise
   */
  public boolean equals(Object other) {
    // FILL IN -- do not return false unless appropriate
    // 首先检查 other 是否为 null
    if (other == null) {
      return false;
    }

    // 检查 other 是否是 Book 类的实例
    if (this == other) {
      return true;
    }

    // 检查 other 的实际类型是否是 Book
    if (!(other instanceof Book)) {
      return false;
    }
    // 类型转换，因为我们已经检查了 other 的类型
    Book otherBook = (Book) other;

    // 比较 ISBN，作者和标题
    return this.isbn == otherBook.isbn &&
            this.author.equals(otherBook.author) &&
            this.title.equals(otherBook.title);
  }

  /**
   * Returns a string representation of the book.
   */
  public String toString() {
    return isbn + ", " + author + ", \"" + title + "\"";
  }

  @Override
  public int hashCode() {
    return (int) isbn + author.hashCode() + title.hashCode();
  }
}
