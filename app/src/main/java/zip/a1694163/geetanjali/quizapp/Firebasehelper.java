package zip.a1694163.geetanjali.quizapp;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Ananya on 2017-05-19.
 */

class Firebasehelper {


    DatabaseReference ref;

    ArrayList<AddQuestions> addquestions = new ArrayList<>();

    public Firebasehelper(DatabaseReference ref)
    {
        this.ref = ref;
    }

    public void fetchData (DataSnapshot dataSnapshot)
    {
        addquestions.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            AddQuestions addquestion = ds.getValue(AddQuestions.class);
            addquestions.add(addquestion);
        }
    }

    public  ArrayList<AddQuestions> retrive()
    {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return addquestions;
    }
}
