package team6.tacoma.uw.edu.hmproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
    private Button mDelete;
    private String btnStatus;
    public static String BOOK_ITEM_SELECTED = "bookItemSelected";
    private static String DELETE_URL
            = "http://cssgate.insttech.washington.edu/~hw29/hmproject/delete.php?";

    public BookDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_book_detail, container, false);
        mBookTitleTextView = (TextView) view.findViewById(R.id.bookFragment_booktitle);
        mIBSNTextView = (TextView) view.findViewById(R.id.bookFragment_ISBN);
        mOwnerTextView = (TextView) view.findViewById(R.id.bookFragment_owner);
        mEmailTextView = (TextView) view.findViewById(R.id.bookFragment_email);
        mPhoneTitleTextView = (TextView) view.findViewById(R.id.bookFragment_phone);
        mMajorTextView = (TextView) view.findViewById(R.id.bookFragment_major);
        mDelete =(Button) view.findViewById(R.id.btn_delete);

        final SharedPreferences sp = getActivity().getSharedPreferences("BtnStatus", Context.MODE_PRIVATE);
        btnStatus = sp.getString("DeleteButton", null);

        if(btnStatus == null) {
            Toast.makeText(getActivity(), "Error in get Button status!!! =)",
                    Toast.LENGTH_LONG).show();
        } else {
           if(btnStatus == "true") {
               mDelete.setVisibility(View.VISIBLE);
              mDelete.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String url = DeleteURL(v);
                      delete(url);

                      Intent intent = new Intent(getActivity(), ViewMyBookActivity.class);
                      startActivity(intent);
                  }
              });
           } else {
               mDelete.setVisibility(View.INVISIBLE);
           }
        }
        return view;
    }


    public void updateView(Book book) {
        if (book != null) {
            mBookTitleTextView.setText("Book Title: "+book.getmBook_title());
            mBookTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.font_size));
            mIBSNTextView.setText("ISBN Number: "+book.getmISBN());
            mOwnerTextView.setText("Owner Name: "+book.getmOwner());
            mMajorTextView.setText("Owner Major: "+book.getmMajor());
            mPhoneTitleTextView.setText("Owner Phone Number: "+book.getmPhone_no());
            mEmailTextView.setText("Owner Email: "+book.getmEmail());
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
            updateView((Book) args.getSerializable(BOOK_ITEM_SELECTED));
        }
    }

    private String DeleteURL(View v) {
        StringBuilder sb = new StringBuilder(DELETE_URL);
        try {
            String ISBN = mIBSNTextView.getText().toString();
            sb.append("ISBN=");
            sb.append(URLEncoder.encode(ISBN, "UTF-8"));

            String Email = mEmailTextView.getText().toString();
            sb.append("&Email=");
            sb.append(URLEncoder.encode(Email, "UTF-8"));
        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    private class DeleteTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    Log.i("111 String URL is: ",url);
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                        Log.i("123 String response: ",response);
                    }

                } catch (Exception e) {
                    response = "Unable to Delete, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }


        /**
         * It checks to see if there was a problem with the URL(Network) which is when an
         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
         * If not, it displays the exception.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {

                    Toast.makeText(getActivity(), "Delete successfully !"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getActivity(), "Failed to Delete: "
                                    + jsonObject.get("error")
                            , Toast.LENGTH_LONG)
                            .show();
                }
            } catch (JSONException e) {
                Toast.makeText(getActivity(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete(String url){
        DeleteTask task = new DeleteTask();
        task.execute(new String[]{url.toString()});
    }
}




