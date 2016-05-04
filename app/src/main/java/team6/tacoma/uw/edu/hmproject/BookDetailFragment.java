package team6.tacoma.uw.edu.hmproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team6.tacoma.uw.edu.hmproject.book.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailFragment extends Fragment {
    private TextView mBookTitleTextView;
    private TextView mIBSNTextView;
    private TextView mOwnerTextView;
    private TextView mMajorTextView;
    private TextView mPhoneTitleTextView;
    private TextView mEmailTextView;
    public static String BOOKLIST_ITEM_SELECTED = "bookListItemSelected";

    public BookDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_book_detail, container, false);
        mBookTitleTextView = (TextView) view.findViewById(R.id.BookFragment_booktitle);
        mIBSNTextView = (TextView) view.findViewById(R.id.BookFragment_ISBN);
        mOwnerTextView = (TextView) view.findViewById(R.id.BookFragment_owner);
        mEmailTextView = (TextView) view.findViewById(R.id.BookFragment_email);
        mPhoneTitleTextView = (TextView) view.findViewById(R.id.BookFragment_phone);
        mMajorTextView = (TextView) view.findViewById(R.id.BookFragment_major);


        return view;
    }

    public void updateView(Book book) {
        if (book != null) {
            mBookTitleTextView.setText(book.getmBook_title());
            mIBSNTextView.setText(book.getmISBN());
            mOwnerTextView.setText(book.getmOwner());
            mMajorTextView.setText(book.getmMajor());
            mPhoneTitleTextView.setText(book.getmPhone_no());
            mEmailTextView.setText(book.getmEmail());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((Book) args.getSerializable(BOOKLIST_ITEM_SELECTED));
        }
    }


}
