package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText(){

        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

        return "안녕하세요";

    }

    // SampleVO를 리턴하는 메서드
    @GetMapping(value = "/getSample", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample(){

        return new SampleVO(112, "스타", "로드");
    }

    @GetMapping(value = "/getSample2")
    public SampleVO getSample2(){
        return new SampleVO(113, "로켓", "라쿤");

    }


}
