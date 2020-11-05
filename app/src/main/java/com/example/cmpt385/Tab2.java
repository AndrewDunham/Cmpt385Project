/*********************************************************************************
 * Tab2: Image Gallery
 *
 * Description:
 * This class is responsible for displaying the image gallery and allowing
 * user interactivity
 *
 *Team Name: Team 10+10
 * Authors: Amy Campbell
 * Date: October 10 2020
 *
 * Input: touch input
 * Output: image title and description
 *
 ********************************************************************************/

package com.example.cmpt385;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cmpt385.ui.main.ImageAdapter;

public class Tab2 extends Fragment {
    Context context;

    //list of names
    public String[] titles = {"Name1","Name2","Name3","Name4","Name5","Name6","Name7","Name8","Name9"};

    public Tab2(){

    }



    @Override
    /**
     * set up gridview and inflater, along with adapter. Create toast which displays image information
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get context from container
        context = container.getContext();
        final View rootView = inflater.inflate(R.layout.fragment2,container,false);

        //initialize grid
        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(context));

        //create listener for each image
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(context, "Image Title: " + titles[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}