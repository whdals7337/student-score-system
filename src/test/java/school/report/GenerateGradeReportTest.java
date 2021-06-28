package school.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import utill.Define;

import java.util.List;

class GenerateGradeReportTest {

    private static final int TEST_STUDENT_SCORE = 95;
    private static final String TEST_GRADE = "S";

    School goodSchool = School.getInstance();
    Subject korean;
    Subject math;
    Subject dance;
    Student testStudent;
    GenerateGradeReport gradeReport = new GenerateGradeReport();


    @BeforeEach()
    public void setUp() {
        createSubject();
        createStudent();
    }

    @Test
    void getScoresAndGrades_MethodTest() {
        List<String[]> scoresAndGrades = gradeReport.getScoresAndGrades(testStudent, korean); // Student student, Subject subject
        Assertions.assertEquals(1, scoresAndGrades.size());
        Assertions.assertEquals(TEST_STUDENT_SCORE, Integer.parseInt(scoresAndGrades.get(0)[0]));
        Assertions.assertEquals(TEST_GRADE, scoresAndGrades.get(0)[1]);
    }

    //테스트 과목 생성
    public void createSubject(){
        korean = new Subject("국어", Define.KOREAN);
        math = new Subject("수학", Define.MATH);
        dance = new Subject("방송댄스", Define.DANCE);

        dance.setGradeType(Define.PF_TYPE);

        goodSchool.addSubject(korean);
        goodSchool.addSubject(math);
        goodSchool.addSubject(dance);

    }

    //테스트 학생 생성
    public void createStudent(){

        testStudent = new Student(211213, "강감찬", korean);
        Student student2 = new Student(212330, "김유신", math);
        Student student3 = new Student(201518, "신사임당", korean);
        Student student4 = new Student(202360, "이순신", korean);
        Student student5 = new Student(201290, "홍길동", math);

        goodSchool.addStudent(testStudent);
        goodSchool.addStudent(student2);
        goodSchool.addStudent(student3);
        goodSchool.addStudent(student4);
        goodSchool.addStudent(student5);

        korean.register(testStudent);
        korean.register(student2);
        korean.register(student3);
        korean.register(student4);
        korean.register(student5);

        math.register(testStudent);
        math.register(student2);
        math.register(student3);
        math.register(student4);
        math.register(student5);

        //세 명만 등록
        dance.register(testStudent);
        dance.register(student2);
        dance.register(student3);

        addScoreForStudent(testStudent, korean, TEST_STUDENT_SCORE);
        addScoreForStudent(testStudent, math, 56);

        addScoreForStudent(student2, korean, 95);
        addScoreForStudent(student2, math, 95);

        addScoreForStudent(student3, korean, 100);
        addScoreForStudent(student3, math, 88);

        addScoreForStudent(student4, korean, 89);
        addScoreForStudent(student4, math, 95);

        addScoreForStudent(student5, korean, 85);
        addScoreForStudent(student5, math, 56);

        addScoreForStudent(testStudent, dance, 95);
        addScoreForStudent(student2, dance, 85);
        addScoreForStudent(student3, dance, 55);

    }

    //과목별 성적 입력
    public void addScoreForStudent(Student student, Subject subject, int point){

        Score score = new Score(student.getStudentId(), subject, point);
        student.addSubjectScore(score);

    }
}