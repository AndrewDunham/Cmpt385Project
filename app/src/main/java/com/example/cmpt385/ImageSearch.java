package com.example.cmpt385;

//Andrew Dunham with help from https://www.youtube.com/watch?v=xNT1p9b_wks&ab_channel=ProgrammingWizardsTV
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.CheckBox;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.model.Progress;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.os.Bundle;

public class ImageSearch extends AppCompatActivity {

    //Setting up the object
    public class Image {
        private int id;
        private String name;
        private String description;
        private String imageURL;
        private String[] tags;
        private String[] people;
        private String date;
        private int viewCount;

        //Getters and Setters
        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getDescription(){
            return description;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getImageURL(){
            return imageURL;
        }
        public void setImageURL(String imageURL){
            this.imageURL = imageURL;
        }
        public String[] getTags(){
            return tags;
        }
        public void setTags(String[] tags){
            this.tags = tags;
        }
        public String[] getPeople(){
            return people;
        }
        public void setPeople(String[] people){
            this.people = people;
        }
        public String getDate(){
            return date;
        }
        public void setDate(String date){
            this.date = date;
        }
        public int getViewCount(){
            return viewCount;
        }
        public void setViewCount(int viewCount){
            this.viewCount = viewCount;
        }
        //Will have to change this factor for search to work correctly
        @Override
        public String toString() {
            return name;
        }
    }

    class FilterHelper extends Filter{
        ArrayList<Image> currentList;
        ListViewAdapter adapter;
        Context c;

        public FilterHelper(ArrayList<Image> currentList, ListViewAdapter adapter, Context c){
            this.currentList = currentList;
            this.adapter = adapter;
            this.c = c;
        }
        //Do the sorting:
        @Override
        protected FilterResults performFiltering (CharSequence constraint){
            FilterResults filterResults = new FilterResults();

            if(constraint != null && constraint.length()>0){
                //Change search to upper case
                constraint=constraint.toString().toUpperCase();

                ArrayList<Image> foundFilters=new ArrayList<>();

                Image image=null;

                for (int i=0; i<currentList.size();i++){
                    image = currentList.get(i);

                    //Search
                    if(image.getName().toUpperCase().contains(constraint)){
                        //Add to list of items
                        foundFilters.add(image);
                    }
                }

                filterResults.count = foundFilters.size();
                filterResults.values = foundFilters;
            }else{
                //If no item is found don't change the list of items
                filterResults.count = currentList.size();
                filterResults.values = currentList;
            }

            //return the results
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults){
            adapter.setImages ((ArrayList<Image>) filterResults.values);
            adapter.refresh();
        }
    }

    public class ListViewAdapter extends BaseAdapter implements Filterable{
        Context c;
        ArrayList<Image> images;
        public ArrayList<Image> currentList;
        FilterHelper filterHelper;

        public ListViewAdapter (Context c, ArrayList<Image> images){
            this.c = c;
            this.images = images;
            this.currentList = images;
        }

        @Override
        public int getCount(){
            return images.size();
        }
        @Override
        public Object getItem(int i){
            return images.get(i);
        }
        @Override
        public long getItemId(int i){
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup){
            if(view==null){
                view=LayoutInflater.from(c).inflate(R.layout.model,viewGroup, false);
            }

            TextView txtName = view.findViewById(R.id.nameTextView);
            ImageView theImageView = view.findViewById(R.id.theImageView);

            final Image s = (Image) this.getItem(i);

            txtName.setText(s.getName());

            if(s.getImageURL() != null && s.getImageURL().length()>0){
                Picasso.get().load(s.getImageURL()).placeholder(R.drawable.placeholder).into(theImageView);
            }else{
                Toast.makeText(c, "Empty Image URL",Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.placeholder).into(theImageView);
            }
            //If someone clicks an individual image show this:
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(c, s.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        public void setImages (ArrayList<Image> filteredImages){
            this.images = filteredImages;
        }

        @Override
        public Filter getFilter(){
            if(filterHelper == null){
                filterHelper = new FilterHelper(currentList,this,c);
            }
            return filterHelper;
        }
        public void refresh(){
            notifyDataSetChanged();
        }
    }

    public class JSONDownloader{
        //Location of Json file
        private static final String JSON_DATA_URL="asdf";

        private final Context c;

        public JSONDownloader(Context c){
            this.c = c;
        }

        //get the JSON data
        public ArrayList<Image> retrieve (final ListView mListView, final ProgressBar myProgressBar){
            final ArrayList<Image> downloadedData = new ArrayList<>();
            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.get(JSON_DATA_URL)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            Image s;
                            try {
                                for (int i=0;i<response.length();i++){
                                    jo=response.getJSONObject(i);
                                    int id=jo.getInt("id");
                                }
                            }catch (JSONException e){

                            }
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
            return downloadedData;
        }
    }
}
