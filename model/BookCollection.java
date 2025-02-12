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
            new Event(Event.getLeafLevelClassName(this), "<init>", "Missing book ID information.", Event.FATAL);
            throw new Exception("UNEXPECTED ERROR: BookCollection.<init>: Data corrupted: book holder had no ID in repository.");
        }
        
        String query = "SELECT * FROM " + myTableName + " WHERE(bookID = " + bookHolderId + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            books = new Vector<Book>();
            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
                Properties nextBookData = (Properties)allDataRetrieved.elementAt(cnt);
                Book book = new Book(nextBookData);
                if (book != null) {
                    addBook(book);
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No books for book holder ID: " + bookHolderId);
        }
    }

    private void addBook(Book a) {
        int index = findIndexToAdd(a);
        books.insertElementAt(a, index);
    }

    private int findIndexToAdd(Book a) {
        int low = 0;
        int high = books.size() - 1;
        int middle;

        while (low <= high){
            middle = (low + high) / 2;
            Book midSession = books.elementAt(middle);
            int result = Book.compare(a, midSession);
            if (result == 0){
                return middle;
            } else if (result<0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return low;
    }

     public Object getState(String key) {
        if (key.equals("Books")) {
            return books;
        } else {
            if ( key.equals("BookList")) {
                return this;
            }
            return null;
        }
     }

     public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
     }

     public Book retrieve(String bookId) {
        Book retValue = null;
        for (int cnt = 0; cnt < books.size(); cnt++) {
            Book nextBook = books.elementAt(cnt);
            String nextBookId = (String)nextBook.getState("ID"); // This kkey im also unsure about
            if (nextBookId.equals(bookId) == true) {
                retValue = nextBook;
                return retValue;
            }
        }
        return retValue;
     }

     public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
     }

     protected void createAndShowView() {
        Scene localScene = myViews.get("BookCollectionView");
        if (localScene == null) {
            View newView = ViewFactory.createView("BookCollectionView", this);
            localScene = new Scene(newView);
            myViews.put("BookCollectionView", localScene);
        }
        swapToView(localScene);
     }

     protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
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
        return "";
    }
}
