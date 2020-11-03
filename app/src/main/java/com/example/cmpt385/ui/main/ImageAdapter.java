/*********************************************************************************
 * ImageAdapter
 *
 * Description:
 * This class acts as an adapter for the image gallery
 *
 *Team Name: Team 10+10
 * Authors: Amy Campbell
 * Date: October 10 2020
 *
 * Input: none
 * Output: none
 *
 ********************************************************************************/

package com.example.cmpt385.ui.main;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cmpt385.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    public int[] imageList = {R.drawable.cup, R.drawable.candyapple, R.drawable.monster,R.drawable.pizza,R.drawable.skull,R.drawable.spider,R.drawable.werewolf,R.drawable.witch};

    public ImageAdapter(Context con){
        this.context = con;
    }

    @Override
    //returns length of image list
    public int getCount() {
        return imageList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    /**
     * this method is called to draw each image and scale it
     * @param position
     * @param convertView
     * @param parent acts as a constraint for the child
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);

        //scale the image
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(450,450));
        imageView.setPadding(8, 8, 8, 8);

        //set image
        imageView.setImageResource(imageList[position]);
        return imageView;
    }
}