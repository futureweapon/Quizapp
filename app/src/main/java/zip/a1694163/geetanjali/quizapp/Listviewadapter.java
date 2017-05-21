package zip.a1694163.geetanjali.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import zip.a1694163.geetanjali.quizapp.Addquestion;

/**
 * Created by Harsh on 5/12/2017.
 */



public class Listviewadapter extends BaseAdapter {

    ArrayList<AddQuestions>  qs;
    Context c;

    //   FirebaseDatabase database = FirebaseDatabase.getInstance();
    // DatabaseReference myRef = database.getReference("Que");


    public Listviewadapter(ArrayList<AddQuestions> qs, Context c) {
        this.qs = qs;
        this.c = c;
    }

    @Override
    public int getCount() {
        return qs.size();
    }

    @Override
    public Object getItem(int position) {
        return qs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = LayoutInflater.from(c).inflate(R.layout.listview_textview,parent,false);
        }

        TextView txt_que = (TextView)  convertView.findViewById(R.id.textview);


        final AddQuestions ques = (AddQuestions) this.getItem(position);

        txt_que.setText(ques.getQuestion());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,ques.getQuestion(),Toast.LENGTH_SHORT).show();


                deleteQue(ques.getQuestion());

            }
        });

        return convertView;
    }

    public  void deleteQue(String que)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference();
        myRef1.child("Que").orderByChild("question").equalTo(que).addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database.getReference();
                myRef1.child("Que").child(dataSnapshot.getKey()).setValue(null);

                //also work
                //dataSnapshot.getRef().setValue(null);
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
}
