package com.green.springjpa.school;

import com.green.springjpa.school.model.SchoolGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private SchoolService schoolService;

    @GetMapping
    public List<SchoolGetRes> getAll() {
        return schoolService.getAll(); //service 내용을 똑같이 다 return
    }
}
