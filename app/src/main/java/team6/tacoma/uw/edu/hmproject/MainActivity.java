package team6.tacoma.uw.edu.hmproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import team6.tacoma.uw.edu.hmproject.book.Book;

public class MainActivity extends AppCompatActivity {
    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
//        mBundle = new Bundle();
        setContentView(R.layout.activity_main);

        final SharedPreferences btnStatus = getSharedPreferences("BtnStatus", Context.MODE_PRIVATE);
        final SharedPreferences sp = getSharedPreferences("Users", Context.MODE_PRIVATE);
        key = sp.getString("username", null);

        Button button_Add =(Button) findViewById(R.id.Add);
        button_Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookAdd.class);
                startActivity(intent);
            }
        });

        Button button_myBook = (Button)findViewById(R.id.MyBook);
        button_myBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewMyBookActivity.class);
                startActivity(intent);

                SharedPreferences.Editor ed = btnStatus.edit();
                ed.putString("DeleteButton", "true");
                ed.commit();
            }
        });


        Button button_View = (Button) findViewById(R.id.button_View);
        button_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
                SharedPreferences.Editor ed = btnStatus.edit();
                ed.putString("DeleteButton", "false");
                ed.commit();
            }
        });

        Button button_Search = (Button) findViewById(R.id.button_search);
        button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

                SharedPreferences.Editor ed = btnStatus.edit();
                ed.putString("DeleteButton", "false");
                ed.commit();
            }
        });

        TextView tev = (TextView) findViewById(R.id.txthelp);
        tev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://docs.google.com/document/d/1bUkN_byWSj3grRf1ZD7o_fkj1t1ioetQAzclAwCpln8/edit?usp=sharing"));
                startActivity(intent);
            }
        });
    }
}
