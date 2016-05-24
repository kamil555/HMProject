package team6.tacoma.uw.edu.hmproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import team6.tacoma.uw.edu.hmproject.book.Book;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Book} and makes a call to the
 * specified {@link BookListFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MybookRecyclerViewAdapter extends RecyclerView.Adapter<MybookRecyclerViewAdapter.ViewHolder> {

    private final List<Book> mValues;
    private final BookListFragment.OnListFragmentInteractionListener mListener;
//    public final SearchBookList.OnListFragmentInteractionListener nListener;

    public MybookRecyclerViewAdapter(List<Book> items, BookListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        return new ViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBookTitleView;
        public final TextView mIBSNView;
        public final TextView mOwner;
        public final TextView mMajor;
        public final TextView mPhone;
        public final TextView mEmail;
        public Book mItem;

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

        @Override
        public String toString() {
            return super.toString() + " '" + mIBSNView.getText() + "'";
        }
    }
}
