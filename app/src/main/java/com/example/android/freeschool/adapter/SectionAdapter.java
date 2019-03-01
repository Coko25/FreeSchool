package com.example.android.freeschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.freeschool.R;
import com.example.android.freeschool.pojo.Course;
import com.example.android.freeschool.pojo.Section;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SectionAdapter extends ArrayAdapter<Section> {

    /**
     * constructs a new (@link EarthquakeAdapter)
     *
     * @param context of the app
     * @param sections is the list of courses, which is the data source of the adapter
     */
    public SectionAdapter(Context context, List<Section> sections) {
        super(context, 0, sections);
    }

    /**
     * returns a list item view that displays information about the courses at the given position
     * in the list of courses
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if there is an existing listview (called convertView) that we can use
        //otherwise, if convertView is null, then inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.section_layout, parent, false);
        }

        //find the Course at the given position in the list of courses
        Section currentSection = getItem(position);

        //find the textView with view ID course
        TextView magnitudeView = listItemView.findViewById(R.id.section);
        //display the title of the current course in that textView
        magnitudeView.setText(currentSection.getName());
        //return the listItemView that is now showing the appropriate data
        return listItemView;
    }
}
