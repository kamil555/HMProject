package team6.tacoma.uw.edu.hmproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // Now get a handle to any View contained
        // within the main layout you are using
//        View view = findViewById(R.id.button_View);
//
//        View root = view.getRootView();
//        root.setBackgroundColor(getResources().getColor(R.color.mainBackground));

        Button button_View = (Button)findViewById(R.id.button_View);
        button_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });


    }


}
