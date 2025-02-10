import java.util.Vector;

public class PatronCollection {
    // Constructor
    public PatronCollection(){
        Vector<Patron> patronList = new Vector<Patron>();
    }

    // Find methods
    public Vector<Patron> findPatronsOlderThan(String year){
        Vector<Patron> oldPatrons = new Vector<Patron>();
        return oldPatrons;
    }
    public Vector<Patron> findPatronsYoungerThan(String year){
        Vector<Patron> youngPatrons = new Vector<Patron>();
        return youngPatrons;
    }
    public Vector<Patron> findPatronsAtZipCode(String zip){
        Vector<Patron> zipPatrons = new Vector<Patron>();
        return zipPatrons;
    }
    public Vector<Patron> findPatronsWithNameLike(String name){
        Vector<Patron> namePatrons = new Vector<Patron>();
        return namePatrons;
    }

    public String toString() {
        
    }
}
