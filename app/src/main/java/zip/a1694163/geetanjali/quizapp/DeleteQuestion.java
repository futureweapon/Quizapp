package zip.a1694163.geetanjali.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteQuestion extends AppCompatActivity {

    DatabaseReference ref;
    Firebasehelper firebasehelper;

    ListView list;
    Listviewadapter listviewadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);
        list = (ListView) findViewById(R.id.listview);

        ref = FirebaseDatabase.getInstance().getReference();
        firebasehelper = new Firebasehelper(ref);

        listviewadapter = new Listviewadapter(firebasehelper.retrive(),this);
        list.setAdapter(listviewadapter);


    }
}
