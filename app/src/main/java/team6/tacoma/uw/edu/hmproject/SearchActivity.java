package team6.tacoma.uw.edu.hmproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team6.tacoma.uw.edu.hmproject.book.Book;

public class SearchActivity extends AppCompatActivity implements SearchBookList.OnListFragmentInteractionListener{

    public EditText editSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        editSearch = (EditText)findViewById(R.id.txtSearch);
        btnSearch = (Button)findViewById(R.id.button_search_fuction);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.search_list) == null) {
            SearchBookList searchBookList = new SearchBookList();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.SearchActivity_content, searchBookList)
                    .commit();
        }
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchBookList searchBookList = new SearchBookList();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.SearchActivity_content, searchBookList).commit();

            }
        });
    }

    @Override
    public void onListFragmentInteraction(Book item) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(BookDetailFragment.BOOK_ITEM_SELECTED, item);
        bookDetailFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.SearchActivity_content, bookDetailFragment)
                .addToBackStack(null)
                .commit();
    }

}
