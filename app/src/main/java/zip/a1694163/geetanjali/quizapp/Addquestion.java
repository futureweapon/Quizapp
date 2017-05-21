package zip.a1694163.geetanjali.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Addquestion extends AppCompatActivity {
    EditText editques,editchoice1,editchoice2,editchoice3,editchoice4,editcorrectans;
    Button buttonadd;

    int count,max;

    ArrayList<Integer> choice;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        editques=(EditText)findViewById(R.id.editques);

        editchoice1=(EditText)findViewById(R.id.editchoice1);
        editchoice2=(EditText)findViewById(R.id.editchoice2);
        editchoice3=(EditText)findViewById(R.id.editchoice3);
        editchoice4=(EditText)findViewById(R.id.editchoice4);
        editcorrectans=(EditText)findViewById(R.id.editcorrectans);

        buttonadd=(Button) findViewById(R.id.buttonadd);

        count = 0;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                choice  = new ArrayList<Integer>();
                for(DataSnapshot ques : dataSnapshot.getChildren())
                {
                    choice.add(Integer.parseInt(ques.getKey()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question,choice1,choice2,choice3,choice4,correctanswer;

                question = editques.getText().toString();
                choice1 = editchoice1.getText().toString();
                choice2 = editchoice2.getText().toString();
                choice3 = editchoice3.getText().toString();
                choice4 = editchoice4.getText().toString();
                correctanswer = editcorrectans.getText().toString();



                if(choice.isEmpty())
                {
                    max = 0;
                }else
                {
                    max = Collections.max(choice);
                    max++;
                }

                String s = String.valueOf(max);

                Map<String,AddQuestions> ques = new HashMap<String, AddQuestions>();
                ques.put(s,new AddQuestions(question,choice1,choice2,choice3,choice4,correctanswer));
                myRef.child(s).setValue(new AddQuestions(question,choice1,choice2,choice3,choice4,correctanswer));


                Toast.makeText(getApplicationContext(),"Question is added",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
