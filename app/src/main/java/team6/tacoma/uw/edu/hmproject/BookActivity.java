package team6.tacoma.uw.edu.hmproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import team6.tacoma.uw.edu.hmproject.book.Book;

/**
 * Created by Mark on 5/12/16
 * Associated with BookAdd file
 */
public class BookActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener {

    /**
     * Adds list of books to to app
     * @param savedInstanceState - list of books
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            BookListFragment bf = new BookListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.BookActivity_container, bf)
                    .commit();
        }
    }

    /**
     * Makes all details of book object interactable
     * @param item - book
     */
    @Override
    public void onListFragmentInteraction(Book item) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(BookDetailFragment.BOOK_ITEM_SELECTED, item);
        bookDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.BookActivity_container, bookDetailFragment)
                .addToBackStack(null)
                .commit();
    }

}
