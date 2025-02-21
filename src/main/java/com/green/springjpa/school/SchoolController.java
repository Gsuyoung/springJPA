package com.green.springjpa.school;

import com.green.springjpa.school.model.SchoolGetRes;
import com.green.springjpa.school.model.SchoolPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolGetRes> getAll() {
        return schoolService.getAll(); //service 내용을 똑같이 다 return
    }

    @PostMapping
    public String save(@RequestBody SchoolPostReq req) {
        schoolService.save(req);
        log.info("req: {}", req);
        return "등록완료";
    }
}
