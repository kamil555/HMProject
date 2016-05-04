package team6.tacoma.uw.edu.hmproject.book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Book implements Serializable {

    private String mBook_title;
    private String mISBN;
    private String mOwner;
    private String mMajor;
    private String mPhone_no;
    private String mEmail;


    public static final String Book_title = "Book_title", ISBN = "ISBN", Owner = "Owner",
            Major = "Major", Phone_no = "Phone_no", Email = "Email";


    public Book(String mBook_title, String mISBN, String mOwner,
                String mMajor, String mPhone_no, String mEmail){
        setmBook_title(mBook_title);
        setmEmail(mEmail);
        setmISBN(mISBN);
        setmMajor(mMajor);
        setmOwner(mOwner);
        setmPhone_no(mPhone_no);

    }

    public String getmBook_title() {
        return mBook_title;
    }

    public String getmISBN() {
        return mISBN;
    }

    public String getmOwner() {
        return mOwner;
    }

    public String getmMajor() {
        return mMajor;
    }

    public String getmPhone_no() {
        return mPhone_no;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmBook_title(String mBook_title) {

        this.mBook_title = mBook_title;
    }

    public void setmISBN(String mISBN) {
        this.mISBN = mISBN;
    }

    public void setmOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public void setmMajor(String mMajor) {
        this.mMajor = mMajor;
    }

    public void setmPhone_no(String mPhone_no) {
        this.mPhone_no = mPhone_no;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
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
