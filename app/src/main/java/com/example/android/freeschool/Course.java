package com.example.android.freeschool;

public class Course {

    /**Title of the course**/
    private String mTitle;

    /**
     * Constructs a new (@link courses) object
     *
     * @param title is the title of the course
     */

    public Course(String title){
        mTitle = title;
    }

    /**Returns the title of the course**/
    public String getTitle() {
        return mTitle;
    }
}
