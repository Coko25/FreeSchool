package com.example.android.freeschool;

public class Section {

    /**Title of the section**/
    private String mTitle;

    /**
     * Constructs a new (@link sections) object
     *
     * @param title is the title of the section
     */

    public Section(String title){
        mTitle = title;
    }

    /**Returns the title of the course**/
    public String getTitle() {
        return mTitle;
    }
}
