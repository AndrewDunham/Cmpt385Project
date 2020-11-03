/*********************************************************************************
 * Tab3: Camera
 *
 * Description:
 * This class is responsible for the camera feature
 *
 *Team Name: Team 10+10
 * Authors: Jimmy Kha, Amy Campbell
 * Date: October 10 2020
 *
 * Input: touch input, camera input
 * Output: none
 *
 ********************************************************************************/

package com.example.cmpt385;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Tab3 extends Fragment {
    Context context;
    Button btOpen;
    Activity activity;
    private ImageView imageView;

    //empty constructor
    public Tab3(){

    }

    @Override
    /**
     * set up inflater, get camera permissions
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get context from container
        context = container.getContext();
        final View rootView = inflater.inflate(R.layout.fragment3,container,false);


        //get id of image and button from View
        imageView = rootView.findViewById(R.id.imageView);
        btOpen = rootView.findViewById(R.id.bt_open);

        //check camera permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }

        //create listener for button
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        return rootView;
    }

    /**
     * THIS NEEDS A DESCRIPTION
     * @param requestCode
     * @param data
     * @param resultCode
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
