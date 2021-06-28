package grade;

public class PassFailGradeStrategy implements GradeStrategy{

    @Override
    public String getGrade(int point) {
        if (point >= 70) {
            return "P";
        }
        else {
            return "F";
        }
    }
}
