package zip.a1694163.geetanjali.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Ananya on 2017-05-19.
 */

public class Listviewadapteredit extends BaseAdapter {

    String string;


    ArrayList<AddQuestions> addquestions;
    Context c;


    public Listviewadapteredit(ArrayList<AddQuestions> addquestions, Context c) {
        this.addquestions = addquestions;
        this.c = c;
    }

    @Override
    public int getCount() {
        return addquestions.size();
    }

    @Override
    public Object getItem(int position) {
        return addquestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.listview_textview, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textview);


        final AddQuestions ques = (AddQuestions) this.getItem(position);

        textView.setText(ques.getQuestion());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database.getReference();


                myRef1.child("Que").orderByChild("question").equalTo(ques.getQuestion()).addChildEventListener(new ChildEventListener() {

                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef1 = database.getReference();

                        String ks = myRef1.child("Que").child(dataSnapshot.getKey()).toString();
                        Intent i = new Intent(c, EditMe.class);

                        i.putExtra("key", ks);
                        c.startActivity(i);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


            }
        });

        return convertView;
    }

}