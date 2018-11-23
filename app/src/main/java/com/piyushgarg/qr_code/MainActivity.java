package com.piyushgarg.qr_code;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Activity activity = this;

        final Button scanbtn = (Button) findViewById(R.id.scanbtn);




            scanbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                }
            });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final TextView textout = (TextView) findViewById(R.id.output);
        final Button copy = (Button) findViewById(R.id.copy);

        Object clipboardService = getSystemService(CLIPBOARD_SERVICE);
       // final ClipboardManager clipboardManager = (ClipboardManager)clipboardService;
        final ClipboardManager clipboardManager = (ClipboardManager)clipboardService;


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                final String r = result.getContents();
                textout.setText(r);

                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create a new ClipData.
                        // ClipData clipData = ClipData.newPlainText("Source Text", srcText);
                        // Set it as primary clip data to copy text to system clipboard.
                        //  clipboardManager.setPrimaryClip(clipData);
                        // Popup a snackbar.
                        //  Snackbar snackbar = Snackbar.make(v, "Source text has been copied to system clipboard.", Snackbar.LENGTH_LONG);
                        // snackbar.show();

                        ClipData clipData = ClipData.newPlainText("Source Tex", r);
                        clipboardManager.setPrimaryClip(clipData);


                    }
                });


            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}

