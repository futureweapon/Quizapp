package zip.a1694163.geetanjali.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Secondpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
    }
    public void addquestion(View v)
    {
        Intent i=new Intent(this,Addquestion.class);
        startActivity(i);

    }
    public void playquiz(View v)
    {
        Intent i=new Intent(this,Playquiz.class);
        startActivity(i);
    }
    public void deletequestion(View v)
    {
        Intent i=new Intent(this,DeleteQuestion.class);
        startActivity(i);

    }
    public void editquiz(View v)
    {
        Intent i=new Intent(this,EditList.class);
        startActivity(i);
    }
}
