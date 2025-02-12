// specify the package
package model;

// system imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;

// project imports
import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import database.*;

import impresario.IView;

public class BookHolder extends EntityBase implements IView {
    private static final String myTableName = "BookHolder";

    public BookHolder(Properties props) {
        super(myTableName);
        String idToQuery = props.getProperty("ID");
        String query = "SELECT * FROM " + myTableName + "WHERE (ID = " + idToQuery + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple books matching book ID: " + idToQuery + " found.");
            } else {
                Properties retrievedCustomerData = (Properties)allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedBookData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String)allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No books matching book ID: " + idToQuery + " found.");
        }
    }

    public BookHolder(String idToQuery) throws InvalidPrimaryKeyException {
        super(myTableName);
        String query = "SELECT * FROM " + myTableName + " WHERE (ID = " + idToQuery + ")";
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();

            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple books matching book ID: " + idToQuery + " found.");
            } else {
                Properties retrievedBookData = (Properties)allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedBookData.propertyNames();
                while (allKeys.hasMoreElements()) {
                    String nextKey = (String)allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No books matching book ID: " + idToQuery + " found.");
        }
    }

    public Object getState(String key) {
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {
        persistentState.setProperty(key, (String)value);
        myRegistry.updateSubscribers(key, this);
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
}
