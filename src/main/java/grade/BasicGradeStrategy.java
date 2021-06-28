package grade;

public class BasicGradeStrategy implements GradeStrategy{

    @Override
    public String getGrade(int score) {
        if(score >=90 && score <=100) {
            return "A";
        }
        else if(score >=80 && score <=89) {
            return "B";
        }
        else if(score >=70 && score <=79) {
            return "C";
        }
        else if(score >=55 && score <=69) {
            return "D";
        }
        else {
            return "F";
        }
    }
}
