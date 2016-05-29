package team6.tacoma.uw.edu.hmproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import team6.tacoma.uw.edu.hmproject.book.Book;


public class BookActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener {

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_share) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
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

//    private class AddTask extends AsyncTask<String, Void, String> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... urls) {
//            String response = "";
//            HttpURLConnection urlConnection = null;
//            for (String url : urls) {
//                try {
//                    URL urlObject = new URL(url);
//                    urlConnection = (HttpURLConnection) urlObject.openConnection();
//
//                    InputStream content = urlConnection.getInputStream();
//
//                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
//                    String s = "";
//                    while ((s = buffer.readLine()) != null) {
//                        response += s;
//                    }
//
//                } catch (Exception e) {
//                    response = "Unable to add course, Reason: "
//                            + e.getMessage();
//                } finally {
//                    if (urlConnection != null)
//                        urlConnection.disconnect();
//                }
//            }
//            return response;
//        }
//
//
//        /**
//         * It checks to see if there was a problem with the URL(Network) which is when an
//         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
//         * If not, it displays the exception.
//         *
//         * @param result
//         */
//        @Override
//        protected void onPostExecute(String result) {
//            // Something wrong with the network or the URL.
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                String status = (String) jsonObject.get("result");
//                if (status.equals("success")) {
//                    Toast.makeText(getApplicationContext(), "Course successfully added!"
//                            , Toast.LENGTH_LONG)
//                            .show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Failed to add: "
//                                    + jsonObject.get("error")
//                            , Toast.LENGTH_LONG)
//                            .show();
//                }
//            } catch (JSONException e) {
//                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
//                        e.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }
//    }

//    public void addCourse(String url){
//        AddCourseTask task = new AddCourseTask();
//        task.execute(new String[]{url.toString()});
//
//        // Takes you back to the previous fragment by popping the current fragment out.
//        getSupportFragmentManager().popBackStackImmediate();
//
//    }
}
