package example.codeclan.com.sqlfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("INSERT", "Inserting instructors...");
        db.addInstructors(new Instructor("Ally", "Java"));
        db.addInstructors(new Instructor("Steve", "Java"));
        db.addInstructors(new Instructor("Alan", "JavaScript"));
        db.addInstructors(new Instructor("John", "ADA95"));

        Log.d("READING: ", "Reading all instructors...");
        ArrayList<Instructor> instructors = db.getAllInstructors();

        for (Instructor instructor : instructors){
            Log.d("INSTRUCTOR: ", "id: " + instructor.get_id() + ", Name: " + instructor.getName() + " - Fav Language: " + instructor.getFavourite_language());
        }
    }
}
