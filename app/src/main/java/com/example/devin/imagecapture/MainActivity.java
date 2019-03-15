package com.example.devin.imagecapture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView devinsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button devinsButton = (Button) findViewById(R.id.devinsButton);
        devinsImageView = (ImageView) findViewById(R.id.devinsImageView);

        //Disable the button if the user has no camera
        if (!hasCamera())
            devinsButton.setEnabled(true);
    }

        //Check if the user has a camera
        private boolean hasCamera(){
            return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        }

        public void launchCamera(View view){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Take a picture and pass results along to onActivityResult
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }

        //If you want to return the image taken

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == REQUEST_IMAGE_CAPTURE && requestCode == RESULT_OK){
                //Get the photo
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                devinsImageView.setImageBitmap(photo);
            }
        }

    }
