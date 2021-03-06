package team6.tacoma.uw.edu.hmproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import team6.tacoma.uw.edu.hmproject.book.Book;

/**
 * Created by Han on 5/24/16, This class is for search feature of APP
 */
public class MySearchRVAdapter extends RecyclerView.Adapter<MySearchRVAdapter.ViewHolder> {
    private final List<Book> mValues;
    private final SearchBookList.OnListFragmentInteractionListener mListener;

    /**
     * Constructor, attaches books and listener to variables
     * @param items
     * @param listener
     */
    public MySearchRVAdapter(List<Book> items, SearchBookList.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * Initializes fields to be used by View
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Sets variables to specified book
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mBookTitleView.setText(mValues.get(position).getmBook_title());
        holder.mIBSNView.setText(mValues.get(position).getmISBN());
        holder.mOwner.setText(mValues.get(position).getmOwner());
        //holder.mMajor.setText(mValues.get(position).getmMajor());
        //holder.mPhone.setText(mValues.get(position).getmPhone_no());
        //holder.mEmail.setText(mValues.get(position).getmEmail());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * Gets count of books
     * @return - count
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Class for dealing with book variables
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBookTitleView;
        public final TextView mIBSNView;
        public final TextView mOwner;
        public final TextView mMajor;
        public final TextView mPhone;
        public final TextView mEmail;
        public Book mItem;

        /**
         * Constructor
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBookTitleView = (TextView) view.findViewById(R.id.booktitle);
            mIBSNView = (TextView) view.findViewById(R.id.IBSN);
            mOwner = (TextView) view.findViewById(R.id.owner);
            mMajor = (TextView) view.findViewById(R.id.major);
            mPhone = (TextView) view.findViewById(R.id.phone);
            mEmail = (TextView) view.findViewById(R.id.email);
        }

        /**
         * Creates string
         * @return
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mIBSNView.getText() + "'";
        }
    }
}
