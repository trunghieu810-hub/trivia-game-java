public class Question {
   private String questionText;
   private String answer;

public Question(String questionText, String answer) {
    this.questionText = questionText;
    this.answer = answer;
}
public boolean checkAnswer(String userAnswer) {
    if (userAnswer.equalsIgnoreCase(answer)) {
        return true; 
    }
    else {
        return false;
    }
}
public String getText() {
    return questionText;
}
public String getAnswer() {
    return answer;
} 
}