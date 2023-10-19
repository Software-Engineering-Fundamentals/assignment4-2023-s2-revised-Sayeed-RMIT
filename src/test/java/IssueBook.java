
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {
	
    private LibraryCard libraryCard;
    private Book bookOne;
    private Book bookTwo;
    private Book bookThree;
    private Book bookFour;
    private Book bookFive;

    @BeforeEach

    void init(){

         // Create a Student instance with a name and student ID.
        Student student = new Student("Sayeed Mahmud Chowdhury",3961205);

       
        Date cardIssueDate = new Date(System.currentTimeMillis());
        //Expiry Date for Library card is set 30 days after card issuance.
        Date cardExpirtyDate = new Date(cardIssueDate.getTime() + 30L*24*60*60*1000);

         // Create a LibraryCard for the student, setting the issue date, expiry date, and card ID.
        libraryCard = new LibraryCard(student, cardIssueDate, cardExpirtyDate, 1);


        //Creating book objects for testing
        bookOne = new Book(1,"One",1);
        bookTwo = new Book(2,"Two",0);
        bookThree = new Book(3,"Three",0);
        bookFour = new Book(4,"Four",1);
        bookFive = new Book(5,"Five",0);
        
        
        libraryCard.getBooks().add(bookThree);

        // Setting different status for different books 
        bookOne.setStatus(true);
        bookTwo.setStatus(true);
        bookThree.setStatus(true);
        bookFour.setStatus(false);
        bookFive.setStatus(true);

    }

    @Test
    @DisplayName("Throws exception as the book has already been borrowed")
    void throwsException_whenBookAlreadyBorrowed() throws IllegalBookIssueException{
            assertThrows(IllegalBookIssueException.class,() -> libraryCard.issueBook(bookThree));
}

    @Test
    @DisplayName("Will return false as the library card has expired")
    void False__WhenLibraryCardGetsExpired() throws IllegalBookIssueException{

            libraryCard.setExpiryDate(new Date(System.currentTimeMillis() - 1000));

            assertFalse(libraryCard.issueBook(bookOne));
            assertFalse(libraryCard.issueBook(bookTwo));

    }

    @Test
    @DisplayName("Will return true as book issuance successful for available high demand  book")
    void True_WhenAvailableHighDemandBook() throws IllegalBookIssueException{       
        assertTrue(libraryCard.issueBook(bookOne));
        assertTrue(libraryCard.getBooks().contains(bookOne));

    }
    
    
    @Test
    @DisplayName("Will return true as book issuance successful for available low demand  book")
    void True_WhenAvailableLowDemandBook() throws IllegalBookIssueException{
        assertTrue(libraryCard.issueBook(bookTwo));
        assertTrue(libraryCard.getBooks().contains(bookTwo));
    }
    
    

    @Test
    @DisplayName("Will return false as there is fine due")
    void False__WhenFineExists() throws IllegalBookIssueException{
        // Fine is set for
        libraryCard.setFine(10);
        assertFalse(libraryCard.issueBook(bookFive));
    }



    @Test
    @DisplayName("Returns false as the book limit exceeds")
    void False_WhenBookLimitExceeds() throws IllegalBookIssueException{

        
        libraryCard.getBooks().add(new Book(6, "BookSix", 0));
        libraryCard.getBooks().add(new Book(7, "BookSeven", 0));
        libraryCard.getBooks().add(new Book(8, "BookEight", 0));
        libraryCard.getBooks().add(new Book(9, "BookNine", 0));

        assertFalse(libraryCard.issueBook(new Book(11, "bookEleven", 0)));
    }


    @Test
    @DisplayName("Returns false as the book is unavailable")
    void False__WhenBookUnavailable() throws IllegalBookIssueException{

             assertFalse(libraryCard.issueBook(bookFour));
}



}
