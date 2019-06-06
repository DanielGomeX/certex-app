package com.certex.certexapp;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SignatureActivity extends AppCompatActivity {

    private SignaturePad signaturePad;
    private Button saveButton, clearButton;
    private String cnpj, ie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signaturePad = (SignaturePad) findViewById(R.id.signaturePad);
        saveButton = (Button) findViewById(R.id.bt_signature_save);
        clearButton = (Button) findViewById(R.id.bt_signature_clear);

        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to landscape mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setTitle("Assinatura");

        Intent it = getIntent();
        cnpj = it.getStringExtra("cnpj");
        ie = it.getStringExtra("ie");

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSignature();
                Intent intent = new Intent(SignatureActivity.this, CompaniesActivity.class);
                intent.putExtra("cnpj", cnpj);
                intent.putExtra("ie", ie);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(SignatureActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

    }

    private void createSignature() {
        Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
        if (addJpgSignatureToGallery(signatureBitmap)) {
//            Toast.makeText(this, "JPG format saved into Gallery", Toast.LENGTH_SHORT).show();
            Toast.makeText(SignatureActivity.this, "Assinatura Criada!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save jpg format to Gallery", Toast.LENGTH_SHORT).show();
        }

//        if (saveSvgSignatureToGallery(signaturePad.getSignatureSvg())) {
//            Toast.makeText(this, "SVG Format saved into Gallery", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to save SVG format to gallery", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * this method used to convert the signature into bitmap format
     *
     * @param bitmap
     * @param photo
     */
    private void saveBitmapToJPG(Bitmap bitmap, File photo) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        try {
            OutputStream stream = new FileOutputStream(photo);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method used to save the signature as jgp into gallery
     *
     * @param signature
     * @return
     */
    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        File photo = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");
        saveBitmapToJPG(signature, photo);
        scanMediaFile(photo);
        result = true;
        return result;
    }

    /**
     * this method used to the file
     *
     * @param photo
     */
    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        SignatureActivity.this.sendBroadcast(mediaScanIntent);
    }

    /**
     * this method used to save the signature as format svg into gallery
     *
     * @param signatureSvg
     * @return
     */
    private boolean saveSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;

        File svgFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");
        try {
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}
