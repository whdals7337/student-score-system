package school;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;    		//학번
    private String studentName;		//이름
    private Subject majorSubject;	//중점 과목

    //학생의 성적 리스트
    //addSubjectSocre() 메서드가 호출되면 리스트에 추가 됨
    private List<Score> scoreList = new ArrayList<>();

    public Student( int studentId, String studentName, Subject majorSubject){
        this.studentId = studentId;
        this.studentName = studentName;
        this.majorSubject = majorSubject;
    }

    public void addSubjectScore(Score score){
        scoreList.add(score);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Subject getMajorSubject() {
        return majorSubject;
    }

    public void setMajorSubject(Subject majorSubject) {
        this.majorSubject = majorSubject;
    }

    public List<Score> getScoreList(){
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }
}
