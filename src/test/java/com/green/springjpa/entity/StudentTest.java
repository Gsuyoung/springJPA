package com.green.springjpa.entity;

import com.green.springjpa.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

//JPA Test
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentTest {
    @Autowired //TDD에서 DI받을 때는 이 애노테이션으로 받아야 한다.
    private StudentRepository studentRepository;

    @Test
    @Rollback(false)
    public void insertStudent() {
        School school = School.builder()
                .schoolId(680594497758405299L)
                .build();

        Student student = Student.builder()
                .gradeTypeCode(StudentGradeTypeCode.GRADE_1)
                .name("테스트")
                .school(school)
                .build();
        studentRepository.save(student);
    }

    @Test
    @Transactional
    public void createStudents() {
        for (int i = 1; i <= 100; i++) {
            Student student = Student.builder()
                    .name(String.format("홍길동%03d", i))
                    .build();
            studentRepository.save(student);
        }
        studentRepository.flush(); //한번씩 for문을 돌아 출력하지않고 여러개를 한번에 날아가게끔하는 것(성능향상에 좋음)
    }
}