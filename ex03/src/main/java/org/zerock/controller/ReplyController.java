package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import java.util.List;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;

    @PostMapping(value = "/new",
    consumes = "application/json",
    produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){

        log.info("ReplyVO: " + vo);

        int insertCount = service.register(vo);

        log.info("Reply INSERT COUNT: " + insertCount);

        return insertCount == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)    //  참
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 거짓
        // 삼항 연산자 처리
    }

    // 17.3.3 특정 게시물의 댓글 목록 확인
    @GetMapping(value = "/pages/{bno}/{page}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
        })
    public ResponseEntity<List<ReplyVO>> getList(
            @PathVariable("page") int page,
            @PathVariable("bno") Long bno){

        log.info("getList......");
        Criteria cri = new Criteria(page, 10);
        log.info(cri);

        return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
    }


}
