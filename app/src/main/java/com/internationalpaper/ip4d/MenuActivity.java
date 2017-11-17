package com.internationalpaper.ip4d;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private final static int CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button ar = (Button) findViewById(R.id.btn_ar);
        if (ar != null) {
            ar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkCameraPermission();
                }
            });
        }

        Button company = (Button) findViewById(R.id.btn_company);
        if (company != null) {
            company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this, CompanyActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button privacy = (Button) findViewById(R.id.btn_privacy);
        if (privacy != null) {
            privacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this, PrivacyActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void launchAR() {
        Intent intent = new Intent(MenuActivity.this, ARActivity.class);
        startActivity(intent);
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION);
        } else {
            launchAR();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    launchAR();
                } else {
                    Toast.makeText(this, R.string.camera_permission, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
