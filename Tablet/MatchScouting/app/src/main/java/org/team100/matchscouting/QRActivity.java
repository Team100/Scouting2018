package org.team100.matchscouting;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import net.glxn.qrgen.android.QRCode;

public class QRActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        //Extract payload from intent
        Bundle extras = getIntent().getExtras();
        String payload = "";
        payload = extras.getString("payload", "null");

        //Link ImageView with Java object and set contents to a QR Code generated from payload
        ImageView imageView = findViewById(R.id.imageView);
        Bitmap bm = QRCode.from(payload).bitmap();
        imageView.setImageBitmap(bm);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {

        String team = getIntent().getExtras().getString("team", "null");
        String pdata = getIntent().getExtras().getString("payload", "null");
        Bitmap code = QRCode.from(pdata).bitmap();

        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    MediaStore.Images.Media.insertImage(getContentResolver(), code, team.toString() ,
                            "Scouting data for team " + team.toString());

                } else {
                }
                return;
            }
        }
    }

    public void onBackButton(View v){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onBackPressed(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
