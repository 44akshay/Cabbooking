package com.example.user.cabbooking;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contactus extends AppCompatActivity {
    Button button;
    private  static final int call=1;
    private static String[] PERMISSION={
            android.Manifest.permission.CALL_PHONE,
    };
    public static void check(Activity activity)
    {
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE);
        if(permission!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(activity,PERMISSION,call);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        check(contactus.this);
        button=(Button)findViewById(R.id.call);
        final int permission = ActivityCompat.checkSelfPermission(contactus.this, Manifest.permission.CALL_PHONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(permission!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(contactus.this,PERMISSION,call);
                }
                else
                {
                    Intent intent=new Intent();
                    intent.setData(Uri.parse("tel:9824496797"));
                    startActivity(intent);
                }


            }
        });

    }
}
