package org.team100.matchscouting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Set Tabs
        setTabs();
    }

    public void setTabs(){

        TabHost tabHost1 = (TabHost)findViewById(R.id.tabhost1);
        tabHost1.setup();
        TabHost tabHost2 = (TabHost)findViewById(R.id.tabhost2);
        tabHost2.setup();

        TabHost.TabSpec tab1 = tabHost1.newTabSpec("Auto");
        TabHost.TabSpec tab2 = tabHost1.newTabSpec("Tele-Op");
        TabHost.TabSpec tab3 = tabHost2.newTabSpec("Init");
        TabHost.TabSpec tab4 = tabHost2.newTabSpec("Overall");

        tab1.setIndicator("Auto");
        tab1.setContent(R.id.Auto);

        tab2.setIndicator("Tele-Op");
        tab2.setContent(R.id.Teleop);

        tab3.setIndicator("Init");
        tab3.setContent(R.id.Init);

        tab4.setIndicator("Overall");
        tab4.setContent(R.id.Overall);

        tabHost1.addTab(tab1);
        tabHost1.addTab(tab2);
        tabHost2.addTab(tab3);
        tabHost2.addTab(tab4);
    }

    public void onButton(View v) {
        qual();
    }

    public String getData(String qual){
        //Link XML objects to Java objects\
        Spinner spinner4 = findViewById(R.id.spinner4);
        EditText editText = findViewById(R.id.editText5);
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBoxALine = findViewById(R.id.checkBoxALine);
        CheckBox checkBoxACube = findViewById(R.id.checkBoxACube);
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        Spinner spinner5 = findViewById(R.id.spinner5);
        Spinner spinner6 = findViewById(R.id.spinner6);
        Spinner spinner7 = findViewById(R.id.spinner7);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);
        CheckBox checkBox5 = findViewById(R.id.checkBox5);
        Spinner spinner8 = findViewById(R.id.spinner8);
        Spinner spinner9 = findViewById(R.id.spinner9);
        CheckBox checkBox6 = findViewById(R.id.checkBox6);
        CheckBox checkBox8 = findViewById(R.id.checkBox8);
        CheckBox checkBox7 = findViewById(R.id.checkBox7);
        CheckBox checkBox9 = findViewById(R.id.checkBox9);

        //Radio Group Init
        String rgb2=((RadioButton)this.findViewById(radioGroup2.getCheckedRadioButtonId())).getText().toString();
        String rgb3=((RadioButton)this.findViewById(radioGroup3.getCheckedRadioButtonId())).getText().toString();
        String rgb = ((RadioButton)this.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        //Get object values
        String sS4 = spinner4.getSelectedItem().toString();
        String sET4 = editText.getText().toString();
        String sRG3 = rgb3;
        String sRG2 = rgb2;
        String sRG = rgb;
        String sCBAL = String.valueOf(checkBoxALine.isChecked());
        String sCBAC = String.valueOf(checkBoxACube.isChecked());
        String sS = spinner.getSelectedItem().toString();
        String sS2 = spinner2.getSelectedItem().toString();
        String sCB2 = String.valueOf(checkBox2.isChecked());
        String sS5 = spinner5.getSelectedItem().toString();
        String sS6 = spinner6.getSelectedItem().toString();
        String sS7 = spinner7.getSelectedItem().toString();
        String sCB3 = String.valueOf(checkBox3.isChecked());
        String sCB4 = String.valueOf(checkBox4.isChecked());
        String sCB5 = String.valueOf(checkBox5.isChecked());
        String sS8 = spinner8.getSelectedItem().toString();
        String sS9 = spinner9.getSelectedItem().toString();
        String sCB6 = String.valueOf(checkBox6.isChecked());
        String sCB8 = String.valueOf(checkBox8.isChecked());
        String sCB7 = String.valueOf(checkBox7.isChecked());
        String sCB9 = String.valueOf(checkBox9.isChecked());

        String fin = sS4 + "^" + sET4 + "^" + sRG3 + "^" + sRG2 + "^" + sRG
                + "^" + sCBAL + "^" + sCBAC + "^" + sS + "^" + sS2 + "^" + sCB2
                + "^" + sS5 + "^" + sS6 + "^" + sS7 + "^" + sCB3 + "^" + sCB4
                + "^" + sCB5 + "^" + sS8 + "^" + sS9 + "^" + sCB6 + "^" + sCB8
                + "^" + sCB7 + "^" + sCB9 + "^" + qual;
        
        return fin;
    }

    public void q(View v){
        Intent intentqq = new Intent(this, QualActivity.class);
        startActivity(intentqq);
    }

    public void qual(){
        Intent intentQual = new Intent(this, QualActivity.class);
        startActivityForResult(intentQual, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        String returnValue = "";
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    returnValue = data.getStringExtra("data1");
                }
                break;
            }
        }
        String s = returnValue;
        String fin1 = "";
        if(s.isEmpty()){
            fin1 = getData(" ");
        }else {
            fin1 = getData(s);
        }

        //Check if payload is null, otherwise open QR activity and pass payload
        if (fin1.isEmpty()) {
            Toast.makeText(this, "Invalid Text", Toast.LENGTH_SHORT).show();
        } else {
            Spinner spinner4a = findViewById(R.id.spinner4);
            Intent intent = new Intent(this, QRActivity.class);
            intent.putExtra("payload", fin1);
            intent.putExtra("team", spinner4a.getSelectedItem().toString());

            startActivity(intent);
            finish();
        }
    }
}