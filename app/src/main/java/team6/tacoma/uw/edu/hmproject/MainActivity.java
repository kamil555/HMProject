package team6.tacoma.uw.edu.hmproject;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String key;
    private ShareActionProvider mShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem item = menu.findItem(R.id.action_share);
//
//        mShare = (ShareActionProvider)item.getActionProvider();

        return true;
    }

//    private void setShareIntent (Intent shareIntent) {
//        if (mShare != null) {
//            mShare.setShareIntent(shareIntent);
//        }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

}
