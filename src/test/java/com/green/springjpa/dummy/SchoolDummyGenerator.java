package com.green.springjpa.dummy;

import com.green.springjpa.entity.School;
import com.green.springjpa.entity.SchoolTypeCode;
import com.green.springjpa.school.SchoolRepository;
import com.green.springjpa.student.StudentRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Locale;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SchoolDummyGenerator {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    Faker faker = new Faker(new Locale("ko"));

    RandomEnumGenerator<SchoolTypeCode> schoolTypeCodeGenerator = new RandomEnumGenerator<>(SchoolTypeCode.class);

    @Test
    @Rollback(false)
    void generate() {
        studentRepository.deleteAll();

        schoolRepository.deleteAll();

        for (int i = 1; i <= 100; i++) {

//            int typeCodeIdx = 0; //랜덤값으로 하셔도되고, 순차적으로 하나씩 들어가지게끔
//            int typeCodeIdx = (int)(Math.random() * 3.0); //0~2랜덤값
//            int typeCodeIdx = 1 % 3; //1 > 1, 2 > 2, 3 > 0, 4 > 1, 5 > 2, 6 > 0
//            SchoolTypeCode schoolTypeCode = switch (typeCodeIdx) {
//                case 0 -> SchoolTypeCode.ELEMENTARY;
//                case 1 -> SchoolTypeCode.MIDDLE;
//                case 2 -> SchoolTypeCode.HIGH;
//                default -> SchoolTypeCode.ELEMENTARY;
//            };

            School school = School.builder()
                    .name(faker.educator().secondarySchool())
                    .schoolTypeCode(schoolTypeCodeGenerator.getRandomEnum())
                    .build();
            schoolRepository.save(school);
        }
        schoolRepository.flush();
    }
}
