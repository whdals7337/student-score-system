package school.report;

import grade.BasicGradeStrategy;
import grade.GradeStrategy;
import grade.MajorGradeStrategy;
import grade.PassFailGradeStrategy;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import utill.Define;

import java.util.List;

public class GenerateGradeReport {

    School school = School.getInstance();
    public static final String TITLE = " 수강생 학점 \t\t\n";
    public static final String HEADER = " 이름  |  학번  |중점과목| 점수   \n ";
    public static final String LINE = "-------------------------------------\n";
    private StringBuffer buffer = new StringBuffer();

    public String getReport(){
        List<Subject> subjectList = school.getSubjectList();  // 모든 과목에 대한 학점 산출
        for( Subject subject : subjectList) {
            makeHeader(subject);
            makeBody(subject);
            makeFooter();
        }
        return buffer.toString();  // String 으로 반환
    }

    public void makeHeader(Subject subject){
        buffer.append(GenerateGradeReport.LINE);
        buffer.append("\t").append(subject.getSubjectName());
        buffer.append(GenerateGradeReport.TITLE );
        buffer.append(GenerateGradeReport.HEADER );
        buffer.append(GenerateGradeReport.LINE);
    }

    public void makeBody(Subject subject){

        List<Student> studentList = subject.getStudentList();  // 각 과목의 학생들

        for (Student student : studentList) {
            buffer.append(student.getStudentName());
            buffer.append(" | ");
            buffer.append(student.getStudentId());
            buffer.append(" | ");
            buffer.append(student.getMajorSubject().getSubjectName()).append("\t");
            buffer.append(" | ");

            getScoreGrade(student, subject);  //학생별 해당과목 학점 계산
            buffer.append("\n");
            buffer.append(LINE);
        }
    }

    public void getScoreGrade(Student student, Subject subject){

        List<Score> scoreList = student.getScoreList();
        int majorId = student.getMajorSubject().getSubjectId();

        GradeStrategy[] gradeStrategies = {new BasicGradeStrategy(), new MajorGradeStrategy(), new PassFailGradeStrategy()};  //학점 평가 클래스들

        for (Score score : scoreList) {  // 학생이 가진 점수들

            if (score.getSubject().getSubjectId() == subject.getSubjectId()) {  // 현재 학점을 산출할 과목
                String grade;

                if (subject.getGradeType() == Define.PF_TYPE) {
                    grade = gradeStrategies[Define.PF_TYPE].getGrade(score.getPoint());
                } else {
                    if (score.getSubject().getSubjectId() == majorId)  // 중점 과목인 경우
                        grade = gradeStrategies[Define.SAB_TYPE].getGrade(score.getPoint());//중점 과목 학점 평가 방법
                    else
                        grade = gradeStrategies[Define.AB_TYPE].getGrade(score.getPoint()); // 중점 과목이 아닌 경우
                }
                buffer.append(score.getPoint());
                buffer.append(":");
                buffer.append(grade);
                buffer.append(" | ");
            }
        }
    }

    public void makeFooter(){
        buffer.append("\n");
    }
}
