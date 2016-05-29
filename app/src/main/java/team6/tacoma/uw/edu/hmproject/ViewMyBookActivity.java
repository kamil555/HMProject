package team6.tacoma.uw.edu.hmproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import team6.tacoma.uw.edu.hmproject.book.Book;

public class ViewMyBookActivity extends AppCompatActivity implements SearchBookList.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_book);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchBookList searchBookList = new SearchBookList();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ViewMyBookContent, searchBookList)
                .commit();


    }


    @Override
    public void onListFragmentInteraction(Book item) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(BookDetailFragment.BOOK_ITEM_SELECTED, item);
        bookDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ViewMyBookContent, bookDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
