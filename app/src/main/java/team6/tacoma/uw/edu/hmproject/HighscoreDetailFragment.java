package team6.tacoma.uw.edu.hmproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team6.tacoma.uw.edu.hmproject.highscore.HighScore;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreDetailFragment extends Fragment {


    private TextView mBookTitleTextView;
    private TextView mIBSNTextView;
    private TextView mOwnerTextView;
    private TextView mMajorTextView;
    private TextView mPhoneTitleTextView;
    private TextView mEmailTextView;
    public static String HIGHSCORE_ITEM_SELECTED = "highScoreItemSelected";



    public HighscoreDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_highscore_detail, container, false);
        mBookTitleTextView = (TextView) view.findViewById(R.id.highScoreFragment_booktitle);
        mIBSNTextView = (TextView) view.findViewById(R.id.highScoreFragment_ISBN);
        mOwnerTextView = (TextView) view.findViewById(R.id.highScoreFragment_owner);
        mEmailTextView = (TextView) view.findViewById(R.id.highScoreFragment_email);
        mPhoneTitleTextView = (TextView) view.findViewById(R.id.highScoreFragment_phone);
        mMajorTextView = (TextView) view.findViewById(R.id.highScoreFragment_major);


        return view;
    }

    public void updateView(HighScore highScore) {
        if (highScore != null) {
            mBookTitleTextView.setText(highScore.getmBook_title());
            mIBSNTextView.setText(highScore.getmISBN());
            mOwnerTextView.setText(highScore.getmOwner());
            mMajorTextView.setText(highScore.getmMajor());
            mPhoneTitleTextView.setText(highScore.getmPhone_no());
            mEmailTextView.setText(highScore.getmEmail());
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
            updateView((HighScore) args.getSerializable(HIGHSCORE_ITEM_SELECTED));
        }
    }


}
