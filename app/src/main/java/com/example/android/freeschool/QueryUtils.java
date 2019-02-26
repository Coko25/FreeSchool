package com.example.android.freeschool;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving Course data from DotLearn.
 */
public final class QueryUtils {

    /**
     * Sample JSON Course response for a DotLearn query
     */
    private static final String SAMPLE_JSON_RESPONSE = "[{\"id\":\"math\",\"name\":\"General Maths\",\"logo\":\"https://s3-eu-west-1.amazonaws.com/eu.storage.dotlearn.org/course_icons/math.png\"},\n"+
            "{\"id\":\"chemistry\",\"name\":\"Chemistry\",\"logo\":\"https://s3-eu-west-1.amazonaws.com/eu.storage.dotlearn.org/course_icons/chemistry.png\"},\n"+
            "{\"id\":\"physics\",\"name\":\"Physics\",\"logo\":\"https://s3-eu-west-1.amazonaws.com/eu.storage.dotlearn.org/course_icons/physics.png\"},\n"+
            "{\"id\":\"economics\",\"name\":\"Economics\",\"logo\":\"https://s3-eu-west-1.amazonaws.com/eu.storage.dotlearn.org/course_icons/economics.png\"},\n"+"" +
            "{\"id\":\"biology\",\"name\":\"Biology\",\"logo\":\"https://s3-eu-west-1.amazonaws.com/eu.storage.dotlearn.org/course_icons/biology.png\"}]";

    private static final String SAMPLE_JSON_RESPONSE_2 = "[{\"id\":\"numbers-and-numeration\",\"version\":1,\"name\":\"Numbers and numeration\",\"type\":\"section\",\"children\":[\"number-bases\",\"fractions\",\"decimals\",\"ratios-&-percentages\",\"financial-mathematics\",\"indices-and-logarithms\",\"arithmetic-progression\",\"geometric-progression\",\"surds\",\"matrices\",\"sets\"]}," +
            "{\"id\":\"algebraic-processes\",\"version\":1,\"name\":\"Algebraic processes\",\"type\":\"section\",\"children\":[\"algebraic-expressions\",\"algebraic-equations-and-fractions\",\"change-of-subject\",\"variation\",\"simultaneous-equations\",\"linear-inequalities\",\"quadratic-equations\"]}," +
            "{\"id\":\"mensuration\",\"version\":1,\"name\":\"Mensuration\",\"type\":\"section\",\"children\":[\"perimeters-and-areas-of-2d-shapes\",\"surface-areas-of-3d-shapes\",\"volumes-of-3d-shapes\",\"latitude-and-longitude\"]}," +
            "{\"id\":\"plane-geometry\",\"version\":1,\"name\":\"Plane geometry\",\"type\":\"section\",\"children\":[\"geometrical-ratios\",\"congruency-and-similarity\",\"triangles\",\"quadrilaterals\",\"polygons\",\"circle-theorems\",\"construction\",\"loci\",\"more-plane-geometry-problems\"]}," +
            "{\"id\":\"coordinate-geometry\",\"version\":1,\"name\":\"Coordinate geometry\",\"type\":\"section\",\"children\":[\"coordinates-of-points-on-the-x-y-plane\",\"equation-of-a-line\"]}," +
            "{\"id\":\"trigonometry\",\"version\":1,\"name\":\"Trigonometry\",\"type\":\"section\",\"children\":[\"trig.-ratios\",\"solving-triangles\",\"bearings\"]}," +
            "{\"id\":\"introductory-calculus\",\"version\":1,\"name\":\"Introductory calculus\",\"type\":\"section\",\"children\":[\"differentiation\",\"integration\"]}," +
            "{\"id\":\"statistics-and-probability\",\"version\":1,\"name\":\"Statistics and probability\",\"type\":\"section\",\"children\":[\"representation-of-data\",\"measures-of-central-tendency\",\"measures-of-dispersion\",\"basic-probability\",\"statistical-graphs-(charts)\",\"permutation-and-combination\"]}]";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Course} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Course> extractCourse() {

        // Create an empty ArrayList that we can start adding courses to
        ArrayList<Course> courses = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Course objects with the corresponding data.

            JSONArray baseJsonResponse = new JSONArray(SAMPLE_JSON_RESPONSE);

            for (int i = 0; i < baseJsonResponse.length(); i++) {
                JSONObject currentCourse = baseJsonResponse.getJSONObject(i);
                String name = currentCourse.getString("name");


                Course course = new Course(name);
                courses.add(course);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Courses JSON results", e);
        }

        // Return the list of earthquakes
        return courses;
    }

    /**
     * Return a list of {@link Section} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Section> extractSection() {

        // Create an empty ArrayList that we can start adding course sections to
        ArrayList<Section> sections = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Sections objects with the corresponding data.

            JSONArray baseJsonResponse2 = new JSONArray(SAMPLE_JSON_RESPONSE_2);

            for (int i = 0; i < baseJsonResponse2.length(); i++) {
                JSONObject currentSection = baseJsonResponse2.getJSONObject(i);
                String name = currentSection.getString("name");


                Section section = new Section(name);
                sections.add(section);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Courses JSON results", e);
        }

        // Return the list of earthquakes
        return sections;
    }

}