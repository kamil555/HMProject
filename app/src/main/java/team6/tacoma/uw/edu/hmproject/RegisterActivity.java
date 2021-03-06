package team6.tacoma.uw.edu.hmproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    private final static String USER_ADD_URL
            = "http://cssgate.insttech.washington.edu/~hw29/hmproject/register.php?";
    private EditText editText_username;
    private EditText editText_password;
    private Button button_register;

    /**
     * Goes through process of registering user, everything from having
     * user enter info, to pointing out errors, to clicking button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);
        final ImageView image = (ImageView)findViewById(R.id.imageView);

        editText_username.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    image.setImageResource(R.drawable.username);
                return false;
            }
        });

        editText_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    image.setImageResource(R.drawable.password);
                return false;
            }
        });

        final Button button_backToLogin = (Button) findViewById(R.id.button_backToLogin);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editText_username.getText().toString();
                final String password = editText_password.getText().toString();

                if (TextUtils.isEmpty(username))  {
                    Toast.makeText(v.getContext(), "Enter username", Toast.LENGTH_SHORT).show();
                    editText_username.requestFocus();
                    return;
                }
                if (!username.contains("@") || !username.contains(".")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address", Toast.LENGTH_SHORT).show();
                    editText_username.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))  {
                    Toast.makeText(v.getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    editText_password.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter password of at least 6 characters", Toast.LENGTH_SHORT).show();
                    editText_password.requestFocus();
                    return;
                }

                String url = registerURL(v);
                register(url);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * Checks to see if what was entered by user is expected
     * @param v
     * @return - string of username and password
     */
    private String registerURL(View v) {
        StringBuilder sb = new StringBuilder(USER_ADD_URL);
        try {
            String username = editText_username.getText().toString();
            sb.append("username=");
            sb.append(URLEncoder.encode(username, "UTF-8"));

            String password = editText_password.getText().toString();
            sb.append("&password=");
            sb.append(URLEncoder.encode(password, "UTF-8"));
        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    private class RegisterTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Trys to register user, checks against database
         * @param urls
         * @return - string saying success or no
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
                    response = "Unable to add users, Reason: "
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

                    SharedPreferences sp = getSharedPreferences("Users", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", editText_username.getText().toString());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Register successfully added!"
                            , Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to register: "
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
     * Initializes register process
     * @param url
     */
    public void register(String url){
        RegisterTask task = new RegisterTask();
        task.execute(new String[]{url.toString()});
    }
}
