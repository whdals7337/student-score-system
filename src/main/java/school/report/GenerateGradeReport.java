package school.report;

import grade.*;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import utill.Define;

import java.util.ArrayList;
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
        buffer.append(GenerateGradeReport.TITLE);
        buffer.append(GenerateGradeReport.HEADER);
        buffer.append(GenerateGradeReport.LINE);
    }

    public void makeBody(Subject subject){

        List<Student> studentOfSubject = subject.getStudentList();  // 각 과목의 학생들

        for (Student student : studentOfSubject) {
            buffer.append(student.getStudentName());
            buffer.append(" | ");
            buffer.append(student.getStudentId());
            buffer.append(" | ");
            buffer.append(student.getMajorSubject().getSubjectName()).append("\t");
            buffer.append(" | ");

            List<String[]> scoreAndGrades = getScoresAndGrades(student, subject);//학생별 해당과목 학점 계산
            for (String[] scoreAndGrade : scoreAndGrades) {
                buffer.append(scoreAndGrade[0]);
                buffer.append(":");
                buffer.append(scoreAndGrade[1]);
                buffer.append(" | ");
            }
            buffer.append("\n");
            buffer.append(LINE);
        }
    }

    public List<String[]> getScoresAndGrades(Student student, Subject subject){
        List<Score> scoresOfStudent = student.getScoreList();
        int majorId = student.getMajorSubject().getSubjectId();

        List<String[]> temp = new ArrayList<>();
        for (Score score : scoresOfStudent) {

            if (isTargetSubject(score.getSubject(), subject)) {
                String grade = calculateGrade(subject, score, majorId);
                temp.add(new String[]{String.valueOf(score.getPoint()), grade});
            }
        }
        return temp;
    }
    public boolean isTargetSubject(Subject scoreSubject, Subject subject) {
        return scoreSubject.getSubjectId() == subject.getSubjectId();
    }

    public String calculateGrade(Subject subject, Score score, int majorId) {
        GradeStrategyFactory gradeStrategyFactory = new GradeStrategyFactory();

        if(isPFSubject(subject)) {
            return gradeStrategyFactory.getGradeStrategy(Define.PF_TYPE).getGrade(score.getPoint());
        }
        if(isMajorSubject(score, majorId)) {
            return gradeStrategyFactory.getGradeStrategy(Define.SAB_TYPE).getGrade(score.getPoint());
        }
        return gradeStrategyFactory.getGradeStrategy(Define.AB_TYPE).getGrade(score.getPoint());
    }

    public boolean isPFSubject(Subject subject) {
        return subject.getGradeType() == Define.PF_TYPE;
    }

    public boolean isMajorSubject(Score score, int majorId) {
        return score.getSubject().getSubjectId() == majorId;
    }

    public void makeFooter(){
        buffer.append("\n");
    }
}
