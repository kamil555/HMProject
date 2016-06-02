package team6.tacoma.uw.edu.hmproject;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import team6.tacoma.uw.edu.hmproject.book.Book;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Mark on 5/30/2016.
 */
public class BookTest {

    @Test
    public void testConstructor() {
        Users user = new Users("User1@uw.edu", "testcase1");

        //assertNotNull(user);
    }

    @Test
    public void testParseCourseJSON() {
        String bookJSON = "[{\"Title\":\"Mark's Book\",\"IBSN\":\"1234567890123\",\"Owner\":\"Mark\",\"Major\":\"Education\",\"Phone Number\":\"888-123-1234\",\"Email\":\"User1@uw.edu\"}]";
        String message =  Book.parseBookJSON(bookJSON, new ArrayList<Book>());
        assertTrue("JSON With Valid String", message == null);
    }
    private Users mUser;
    private Book mBook;

    @Before
    public void setUp() {
        mUser = new Users("mUser@uw.edu","passwordTest");
        mBook = new Book("mBook_title","3210987654321","mOwner","mMajor","111-123-1234","mEmail@uw.com");
    }

    @Test
    public void testSetNullBookTitle(){
        try{
            mBook.setmBook_title(null);
            fail("Book title can be set to null");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testSetBookISBN() {
        try {
            mBook.setmISBN("15");
            fail("ISBN number is less than 13 digits long");
        }catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testSetNullBookOwner(){
        try{
            mBook.setmOwner(null);
            fail("Book Owner can be set to null");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testSetInvalidPhoneNumber(){
        try {
            mBook.setmPhone_no("123");
            fail("Phone number can be set to less than 12 digits");
        } catch (IllegalArgumentException e){

        }
        try {
            mBook.setmPhone_no(null);
            fail("Phone number can be set to null");
        } catch (IllegalArgumentException e){

        }
    }

    @Test
    public void testSetInvalidEmail() {
        try {
            mBook.setmEmail("testemail.com");
            fail("Email does not contain @");
        } catch (IllegalArgumentException e){

        }
        try {
            mBook.setmEmail(null);
            fail("Email can be set to null");
        } catch (IllegalArgumentException e){

        }
    }

    //Test get methods
    //("mBook_title","3210987654321","mOwner","mMajor","111-123-1234","mEmail@uw.com");
    @Test
    public void testGetBookTitle(){
        assertEquals("mBook_title", mBook.getmBook_title());
    }
    @Test
    public void testGetBookISBN(){
        assertEquals("3210987654321", mBook.getmISBN());
    }
    @Test
    public void testGetBookOwner(){
        assertEquals("mOwner", mBook.getmOwner());
    }
    @Test
    public void testGetBookMajor(){
        assertEquals("mMajor", mBook.getmMajor());
    }
    @Test
    public void testGetBookPhone(){
        assertEquals("111-123-1234", mBook.getmPhone_no());
    }
    @Test
    public void testGetBookEmail(){
        assertEquals("mEmail@uw.com", mBook.getmEmail());
    }

}
