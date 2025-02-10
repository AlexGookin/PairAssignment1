import java.util.Vector;

public class BookCollection {
    // Constructor
    public BookCollection() {
        Vector<Book> bookList = new Vector<Book>();
    }

    // Find methods
    public Vector<Book> findBooksOlderThan(String year) {
        Vector<Book> oldBooks = new Vector<Book>();
        return oldBooks;
    }
    public Vector<Book> findBooksNewerThan(String year){
        Vector<Book> newBooks = new Vector<Book>();
        return newBooks;
    }
    public Vector<Book> findBooksWithTitleLike(String title){
        Vector<Book> titleBooks = new Vector<Book>();
        return titleBooks;
    }
    public Vector<Book> findBooksWithAuthorLike(String author){
        Vector<Book> authorBooks = new Vector<Book>();
        return authorBooks;
    }

    public String toString(){
        
    }
}
