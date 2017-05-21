package zip.a1694163.geetanjali.quizapp;

/**
 * Created by Ananya on 2017-05-12.
 */

class AddQuestions {

    private String question,choice1,choice2,choice3,choice4,correctanswer;


    public AddQuestions()
    {}

    public AddQuestions(String question,String choice1,String choice2,String choice3,String choice4,String correctanswer)
    {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctanswer = correctanswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestions(String questions) {
        this.question = questions;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }

}
