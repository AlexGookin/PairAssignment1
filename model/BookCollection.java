// system imports
import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;

// project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

import impresario.IView;

import userinterface.View;
import userinterface.ViewFactory;


public class BookCollection extends EntityBase implements IView{

    private static final String myTableName = "Book";
    private Vector<Book> books;

    // Constructor
    public BookCollection(BookHolder books) throws Exception {
        super(myTableName);
        if (books == null) {
            new Event(Event.getLeafLevelClassName(this), "<init>", "Missing book holder information", Event.FATAL);
            throw new Exception("UNEXPECTED ERROR: BookCollection.<init>: book holder information is null");
        }
        // Unsure about key 
        String bookHolderId = (String)books.getState("ID");
        if (bookHolderId == null) {
            
        }
        

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
