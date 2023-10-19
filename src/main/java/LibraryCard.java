
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	   this.ExpiryDate = ExpiryDate;
	   this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    
/**
 * Issue a new book to the library card.
 *
 * @param book The book to borrow.
 * @return true if the book is successfully borrowed, false otherwise.
 * @throws IllegalBookIssueException if there's an issue with issuing the book.
 */

public boolean issueBook(Book book) throws IllegalBookIssueException {

    // Check the number of books already borrowed on this card.
    int numBooksBorrowed = borrowed.size();

    // If the maximum allowed books (4) are already borrowed, return false
    if (numBooksBorrowed > 4) {
        return false;
    }

    // If the book is already in the list of borrowed books, throw an exception
    if (borrowed.contains(book)) {
        throw new IllegalBookIssueException("The book is already issued on the library card!");
    }

    // Get the current date.
    Date currDate = new Date(System.currentTimeMillis());

    // Check if the card has expired. If so, return false
    if (currDate.after(ExpiryDate)) {
        return false;
    }

    // Check if the book is available
    if (!book.getStatus()) {
        return false;
    }

    // Check if there are any fines associated with the library card. If there are, return false
    if (fine > 0) {
        return false;
    }

    // Set the borrowing period based on book demand
    if (book.getDemand() == 0) {
        book.setDays(15);
    } else {
        book.setDays(3);
    }

    // Add the book to the list of borrowed books and return true.
    borrowed.add(book);
    return true;
}


}
