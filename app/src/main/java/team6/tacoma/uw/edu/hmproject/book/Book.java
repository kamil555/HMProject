package team6.tacoma.uw.edu.hmproject.book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mark on 5/1/16
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class Book implements Serializable {

    // title of book
    private String mBook_title;
    // ISBN number associated with book
    private String mISBN;
    // Owner name of book
    private String mOwner;
    // College major book associated with
    private String mMajor;
    // Phone number of book owner
    private String mPhone_no;
    // Email of book owner
    private String mEmail;
    // Key word used for book search
    private String mKeyWord;

    /**
     * Strings for all details of book
     */
    public static final String Book_title = "Book_title", ISBN = "ISBN", Owner = "Owner",
            Major = "Major", Phone_no = "Phone_no", Email = "Email";

    /**
     * Constructor for creation of book object
     * @param mBook_title - Title of Book (cannot be null
     * @param mISBN - ISBN number of book (must be 13 digits
     * @param mOwner - Book Owner name (cannot be null
     * @param mMajor - College Major book is associated with
     * @param mPhone_no - Phone number of book owner (in xxx-xxx-xxxx format
     * @param mEmail - email address of book owner (requires @ symbol
     */
    public Book(String mBook_title, String mISBN, String mOwner,
                String mMajor, String mPhone_no, String mEmail){
        setmBook_title(mBook_title);
        setmEmail(mEmail);
        setmISBN(mISBN);
        setmMajor(mMajor);
        setmOwner(mOwner);
        setmPhone_no(mPhone_no);
    }

    /**
     * Returns book title
     * @return - string
     */
    public String getmBook_title() {
        return mBook_title;
    }

    /**
     * Returns ISBN number
     * @return - string
     */
    public String getmISBN() {
        return mISBN;
    }

    /**
     * Returns Owner name of book
     * @return - string
     */
    public String getmOwner() {
        return mOwner;
    }

    /**
     * Returns college Major book is associated with
     * @return - string
     */
    public String getmMajor() {
        return mMajor;
    }

    /**
     * Retuns phone number of book owner
     * @return - string
     */
    public String getmPhone_no() {
        return mPhone_no;
    }

    /**
     * Returns email of book owner
     * @return - string
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * Returns key word or string for search engine
     * @return - string
     */
    public String getmKeyWord() {
        return mKeyWord;
    }

    /**
     * Enters name of book, will not accept null
     * @param mBook_title - string
     */
    public void setmBook_title(String mBook_title) {
        if (mBook_title == null){
            throw new IllegalArgumentException("Title cannot be Null");
        } else {
            this.mBook_title = mBook_title;
        }
    }

    /**
     * Enters ISBN number of book. must be 13 characters
     * @param mISBN - string
     */
    public void setmISBN(String mISBN) {
        if (mISBN.length() < 6){
            throw new IllegalArgumentException("ISBN must be 6 digits long");
        } else {
            this.mISBN = mISBN;
        }
    }

    /**
     * Enters owner name of book. Will not accept null value
     * @param mOwner - string
     */
    public void setmOwner(String mOwner) {
        if (mOwner == null){
            throw new IllegalArgumentException("Owner cannot be Null");
        } else {
            this.mOwner = mOwner;
        }
    }

    /**
     * Enters college major associated with book. Will not accept null value
     * @param mMajor - string
     */
    public void setmMajor(String mMajor) {
        if (mMajor == null) {
            throw new IllegalArgumentException("Major cannot be Null");
        } else {
            this.mMajor = mMajor;
        }
    }

    /**
     * Enters phone number of book owner. Will not accept null value and must be in particular format
     * @param mPhone_no - string
     */
    public void setmPhone_no(String mPhone_no) {
        if (mPhone_no == null) {
            throw new IllegalArgumentException("Phone number must be entered");
        } if(mPhone_no.length() < 10) {
            throw new IllegalArgumentException("Phone number must be entered in format xxxxxxxxxx");

        } else {
            this.mPhone_no = mPhone_no;
        }
    }

    /**
     * Enters email of book owner. Will not accept null value and must contain '@'
     * @param mEmail - string
     */
    public void setmEmail(String mEmail) {
        if (mEmail == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (!mEmail.contains("@")){
            throw new IllegalArgumentException("Email must be valid");

        } else {
            this.mEmail = mEmail;
        }
    }

    /**
     * Enters key word for search engine. Will accept null value
     * @param mKeyWord - string
     */
    public void setmKeyWord(String mKeyWord) {
        this.mKeyWord = mKeyWord;
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns book list if success.
     * @param bookJSON
     * @return reason or null if successful.
     */
    public static String parseBookJSON(String bookJSON, List<Book> bookList) {
        String reason = null;
        if (bookJSON != null) {
            try {
                JSONArray arr = new JSONArray(bookJSON);

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Book book = new Book(obj.getString(Book.Book_title), obj.getString(Book.ISBN),
                            obj.getString(Book.Owner), obj.getString(Book.Major),
                            obj.getString(Book.Phone_no), obj.getString(Book.Email));
                    bookList.add(book);
                }
            } catch (JSONException e) {
                reason =  "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        System.out.print(reason);
        return reason;
    }
}
