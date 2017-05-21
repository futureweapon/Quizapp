package zip.a1694163.geetanjali.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Playquiz extends AppCompatActivity {
    TextView ques,score;
    RadioButton choice1,choice2,choice3,choice4,select;
    Button nextquestion;

    private int index;

    int next;
    RadioGroup radioGroup;

    ArrayList<AddQuestions> question;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Que");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playquiz);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        ques = (TextView) findViewById(R.id.ques);
        score = (TextView) findViewById(R.id.score);

        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);


        nextquestion =(Button) findViewById(R.id.nextquestion);

        nextquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCorrect())
                {
                    Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_SHORT).show();
                    index++;
                    next++;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong answer!",Toast.LENGTH_SHORT).show();
                    index++;
                }

                score.setText("Your Score is:"+next);
                displayquestion(index);

            }
        });


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question = new ArrayList<AddQuestions>();

                for (DataSnapshot qs : dataSnapshot.getChildren()) {
                    question.add(qs.getValue(AddQuestions.class));
                }

                index = 0;
                displayquestion(index);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void displayquestion(int index)
    {

        if(index < question.size())
        {
            ques.setText(question.get(index).getQuestion());
            choice1.setText(question.get(index).getChoice1());
            choice2.setText(question.get(index).getChoice2());
            choice3.setText(question.get(index).getChoice3());
            choice4.setText(question.get(index).getChoice4());
        }
        else{
            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
        }


    }

    private boolean checkCorrect()
    {
        int selected = radioGroup.getCheckedRadioButtonId();

        select =(RadioButton) findViewById(selected);

        if(index < question.size()) {
            if (select.getText().equals(question.get(index).getCorrectanswer()))
            {
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Correct Done!",Toast.LENGTH_SHORT).show();
        }

        return false;
    }


}