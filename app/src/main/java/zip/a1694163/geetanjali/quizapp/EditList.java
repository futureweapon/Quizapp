package zip.a1694163.geetanjali.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditList extends AppCompatActivity {
    DatabaseReference databaseReference;
    Firebasehelper firebasehelper;

    ListView listView;
    Listviewadapteredit listviewadapteredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        listView = (ListView) findViewById(R.id.editlist);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebasehelper = new Firebasehelper(databaseReference);

        listviewadapteredit  = new Listviewadapteredit(firebasehelper.retrive(),this);
        listView.setAdapter(listviewadapteredit);
    }
}
