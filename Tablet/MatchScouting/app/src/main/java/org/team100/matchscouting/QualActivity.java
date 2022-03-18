package org.team100.matchscouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Team100 on 3/15/2018.
 */

public class QualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qual);
    }

    public void back(View v) {
        onBackPressed();
    }

    public void onBackPressed() {
        EditText text = findViewById(R.id.qualInput);
        String data1 = String.valueOf(text.getText());


        Intent resultIntent = new Intent();
        resultIntent.putExtra("data1", data1);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
