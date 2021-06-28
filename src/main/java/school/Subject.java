package school;

import utill.Define;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectName;  //과목 이름
    private int subjectId;      // 과목 고유번호
    private int gradeType;      // 과목 평가 방법 기본은 A,B 방식
    private List<Student> studentList = new ArrayList<>();

    public Subject(String subjectName, int subjectId){
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.gradeType = Define.AB_TYPE;   //기본적으로 A, B 타입
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getGradeType() {
        return gradeType;
    }

    public void setGradeType(int gradeType) {
        this.gradeType = gradeType;
    }

    public void register(Student student){  //수강신청
        studentList.add(student);
    }
}
