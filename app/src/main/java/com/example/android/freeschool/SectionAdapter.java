package com.example.android.freeschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SectionAdapter extends ArrayAdapter<Section> {

    /**
     * constructs a new (@link EarthquakeAdapter)
     *
     * @param context of the app
     * @param sections is the list of sections, which is the data source of the adapter
     */
    public SectionAdapter (Context context, List<Section> sections){
        super(context, 0, sections);
    }

    /**
     * returns a list item view that displays information about the sections at the given position
     * in the list of courses
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if there is an existing listview (called convertView) that we can use
        //otherwise, if convertView is null, then inflate a new list item layout
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.main_list_item, parent, false);
        }

        //find the section at the given position in the list of sections
        Section currentSection = getItem(position);

        //find the textView with view ID course
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.course);
        //display the title of the current section in that textView
        magnitudeView.setText(currentSection.getTitle());

        //return the listItemView that is now showing the appropriate data
        return listItemView;
    }
}
