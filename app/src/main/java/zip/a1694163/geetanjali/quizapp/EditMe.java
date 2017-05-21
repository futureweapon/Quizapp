package zip.a1694163.geetanjali.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditMe extends AppCompatActivity {

    FirebaseDatabase fb = FirebaseDatabase.getInstance();
    DatabaseReference myref;

    EditText edt_que,edt_cha,edt_chb,edt_c,edt_d,edt_coorectans;

    Button btn_edt;

    String que,cha,chb,chc,chd,correctans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_me);


        edt_que =  (EditText) findViewById(R.id.que_edt);
        edt_cha =  (EditText) findViewById(R.id.ch1_edt);
        edt_chb =  (EditText) findViewById(R.id.ch2_edt);
        edt_c =  (EditText) findViewById(R.id.ch3_edt);
        edt_d =  (EditText) findViewById(R.id.ch4_edt);
        edt_coorectans =  (EditText) findViewById(R.id.corans_edt);

        btn_edt = (Button)  findViewById(R.id.edt_btn);


        Bundle bundle = getIntent().getExtras();

        String key = bundle.getString("key");

        myref = fb.getReferenceFromUrl(key);


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                edt_que.setText(dataSnapshot.child("question").getValue(String.class));
                edt_cha.setText(dataSnapshot.child("choice1").getValue(String.class));
                edt_chb.setText(dataSnapshot.child("choice2").getValue(String.class));
                edt_c.setText(dataSnapshot.child("choice3").getValue(String.class));
                edt_d.setText(dataSnapshot.child("choice4").getValue(String.class));
                edt_coorectans.setText(dataSnapshot.child("correctanswer").getValue(String.class));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que =  edt_que.getText().toString();
                cha =  edt_cha.getText().toString();
                chb =  edt_chb.getText().toString();
                chc =  edt_c.getText().toString();
                chd =  edt_d.getText().toString();
                correctans =  edt_coorectans.getText().toString();
                myref.setValue(new AddQuestions(que,cha,chb,chc,chd,correctans));

                Toast.makeText(EditMe.this,"Quiz is Edit!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
