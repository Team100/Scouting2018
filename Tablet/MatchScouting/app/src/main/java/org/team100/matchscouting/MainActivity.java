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
import android.widget.TextView;
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

    public void refreshInit(View v) {
        boolean done = true;

        done &= (findViewById(((RadioGroup)findViewById(R.id.allianceRadioGroup))
                .getCheckedRadioButtonId()) != null);
        done &= (findViewById(((RadioGroup)findViewById(R.id.stationRadioGroup))
                .getCheckedRadioButtonId()) != null);
        done &= (findViewById(((RadioGroup)findViewById(R.id.matchTypeRadioGroup))
                .getCheckedRadioButtonId()) != null);
        done &= (findViewById(((RadioGroup)findViewById(R.id.teleClimbRadioGroup))
                .getCheckedRadioButtonId()) != null);

        findViewById(R.id.finishButton).setEnabled(done);
    }

    public void count(View view) {
        boolean up;
        int target;

        int id = view.getId();
        if (id == R.id.autoHighGoalDec || id == R.id.autoHighGoalInc) {
            target = R.id.autoHighGoalCount;
        } else if (id == R.id.autoLowGoalDec || id == R.id.autoLowGoalInc) {
            target = R.id.autoLowGoalCount;
        } else if (id == R.id.teleHighGoalDec || id == R.id.teleHighGoalInc) {
            target = R.id.teleHighGoalCount;
        } else {
            target = R.id.teleLowGoalCount;
        }

        up = (id == R.id.autoHighGoalInc || id == R.id.autoLowGoalInc ||
              id == R.id.teleHighGoalInc || id == R.id.teleLowGoalInc);

        TextView textView = findViewById(target);
        int value = Integer.parseInt(textView.getText().toString());
        if (up || value > 0) {
            value += up ? 1 : -1;
            textView.setText(String.valueOf(value));
        }
    }

    public String getData(String qual){
        // Link XML objects to Java objects
        Spinner teamNumberSpinner = findViewById(R.id.teamNumberSpinner);
        EditText matchNumberInput = findViewById(R.id.matchNumberInput);
        RadioGroup allianceRadioGroup = findViewById(R.id.allianceRadioGroup);
        RadioGroup stationRadioGroup = findViewById(R.id.stationRadioGroup);
        RadioGroup matchTypeRadioGroup = findViewById(R.id.matchTypeRadioGroup);

        TextView autoHighGoalCount = findViewById(R.id.autoHighGoalCount);
        TextView autoLowGoalCount = findViewById(R.id.autoLowGoalCount);
        CheckBox autoExitCheckbox = findViewById(R.id.autoExitCheckbox);

        TextView teleHighGoalCount = findViewById(R.id.teleHighGoalCount);
        TextView teleLowGoalCount = findViewById(R.id.teleLowGoalCount);
        RadioGroup teleClimbRadioGroup = findViewById(R.id.teleClimbRadioGroup);

        Spinner foulSpinner = findViewById(R.id.foulSpinner);
        Spinner techFoulSpinner = findViewById(R.id.techFoulSpinner);
        CheckBox yellowCardCheckbox = findViewById(R.id.yellowCardCheckbox);
        CheckBox redCardCheckbox = findViewById(R.id.redCardCheckbox);
        CheckBox disabledCheckbox = findViewById(R.id.disabledCheckbox);
        CheckBox brokenCheckbox = findViewById(R.id.brokenCheckbox);

        // Radio Group Init
        RadioButton alliance = findViewById(allianceRadioGroup.getCheckedRadioButtonId());
        RadioButton station = findViewById(stationRadioGroup.getCheckedRadioButtonId());
        RadioButton matchType = findViewById(matchTypeRadioGroup.getCheckedRadioButtonId());
        RadioButton climbLevel = findViewById(teleClimbRadioGroup.getCheckedRadioButtonId());

        // Get object values
        String[] params = {
                teamNumberSpinner.getSelectedItem().toString(),
                matchNumberInput.getText().toString(),
                alliance.getText().toString(),
                station.getText().toString(),
                matchType.getText().toString(),

                autoHighGoalCount.getText().toString(),
                autoLowGoalCount.getText().toString(),
                String.valueOf(autoExitCheckbox.isChecked()),

                teleHighGoalCount.getText().toString(),
                teleLowGoalCount.getText().toString(),
                climbLevel.getText().toString(),

                foulSpinner.getSelectedItem().toString(),
                techFoulSpinner.getSelectedItem().toString(),
                String.valueOf(yellowCardCheckbox.isChecked()),
                String.valueOf(redCardCheckbox.isChecked()),
                String.valueOf(disabledCheckbox.isChecked()),
                String.valueOf(brokenCheckbox.isChecked()),
        };

        StringBuilder fin = new StringBuilder();
        for (String param : params) {
            fin.append(param);
            fin.append("^");
        }
        fin.append(qual);
        
        return fin.toString();
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
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // TODO Extract the data returned from the child Activity.
                returnValue = data.getStringExtra("data1");
            }
        }
        String s = returnValue;
        String fin1;
        if(s.isEmpty()){
            fin1 = getData(" ");
        }else {
            fin1 = getData(s);
        }

        // Check if payload is null, otherwise open QR activity and pass payload
        if (fin1.isEmpty()) {
            Toast.makeText(this, "Invalid Text", Toast.LENGTH_SHORT).show();
        } else {
            Spinner spinner4a = findViewById(R.id.teamNumberSpinner);
            Intent intent = new Intent(this, QRActivity.class);
            intent.putExtra("payload", fin1);
            intent.putExtra("team", spinner4a.getSelectedItem().toString());

            startActivity(intent);
            finish();
        }
    }
}