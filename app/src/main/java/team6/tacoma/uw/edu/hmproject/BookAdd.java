package team6.tacoma.uw.edu.hmproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class BookAdd extends AppCompatActivity {
    // location of database
    private  final static String BOOK_ADD_URL =
            "http://cssgate.insttech.washington.edu/~hw29/hmproject/addBook.php?";
    // Book title
    private EditText text_addBookTitle;
    // Book ISBN
    private EditText text_addISBN;
    // Book owner name
    private EditText text_addOwner;
    // Book subject
    private EditText text_addMajor;
    // Book owner phone number
    private EditText text_addPh;
    // Book owner email address
    private EditText text_addEmail;

    /**
     * Creates book and assigns book to database, on a interactive level
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        text_addBookTitle =(EditText) findViewById(R.id.add_book_title);
        text_addISBN =(EditText) findViewById(R.id.add_book_isbn);
        text_addOwner =(EditText) findViewById(R.id.add_owner);
        text_addMajor =(EditText) findViewById(R.id.add_major);
        text_addPh =(EditText) findViewById(R.id.add_phone);

        Button btn_AddBook = (Button)findViewById(R.id.add_book_button);
        btn_AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Book_title = text_addBookTitle.getText().toString();
                final String ISBN = text_addISBN.getText().toString();
                final String Owner = text_addOwner.getText().toString();
                final String Major = text_addMajor.getText().toString();
                final String Phone_No = text_addPh.getText().toString();
//                final String Email = sp.getString("username", null);

                if (Book_title.length() < 1 || ISBN.length() < 1 || Owner.length() < 1 ||
                        Major.length() < 1 || Phone_No.length() < 1 ) {
                    Toast.makeText(v.getContext(), "Please inter a valid information", Toast.LENGTH_SHORT).show();
                    text_addBookTitle.requestFocus();
                    return;
                }

                String url = addBookURL(v);
                add(url);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Creates a string of Book info
     * @param v - book info
     * @return - string of book and info
     */
    private String addBookURL(View v) {
        StringBuilder sb = new StringBuilder(BOOK_ADD_URL);
        try{
            String addBook_title = text_addBookTitle.getText().toString();
            sb.append("Book_title=");
            sb.append(URLEncoder.encode(addBook_title,"UTF-8"));

            String addISBN = text_addISBN.getText().toString();
            sb.append("&ISBN=");
            sb.append(URLEncoder.encode(addISBN,"UTF-8"));

            String addOwner = text_addOwner.getText().toString();
            sb.append("&Owner=");
            sb.append(URLEncoder.encode(addOwner,"UTF-8"));

            String addMajor  = text_addMajor.getText().toString();
            sb.append("&Major=");
            sb.append(URLEncoder.encode(addMajor,"UTF-8"));

            String addPhone_no  = text_addPh.getText().toString();
            sb.append("&Phone_no=");
            sb.append(URLEncoder.encode(addPhone_no,"UTF-8"));

            final SharedPreferences sp = getApplication().getSharedPreferences("Users", 0);
            String addEmail = sp.getString("username", null);
            sb.append("&Email=");
            sb.append(URLEncoder.encode(addEmail,"UTF-8"));

        } catch (Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    /**
     * inner class for adding book to database
     */
    private class addTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creates a string response to action as book is added to database
         * @param urls
         * @return - string
         */
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
                    response = "Unable to add book, Reason: "
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
                    Toast.makeText(getApplicationContext(), "Add Book successfully!"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add: "
                                    + jsonObject.get("error")
                            , Toast.LENGTH_LONG)
                            .show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Method for adding string of book info
     * @param url
     */
    public void add(String url){
        addTask task = new addTask();
        task.execute(new String[]{url.toString()});
    }
}

