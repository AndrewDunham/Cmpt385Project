/*********************************************************************************
 * Tab1: Search Function
 *
 * Description:
 * This class is responsible for managing the search functionality
 *
 *Team Name: Team 10+10
 * Authors: Amy Campbell
 * Date: October 10 2020
 *
 * Input: none
 * Output: none
 *
 ********************************************************************************/

package com.example.cmpt385;
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


public class Tab1 extends Fragment {

    public Tab1(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }
}