package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @RequestMapping("")
    public void basic(){

        log.info("basic.............");
    }

    @RequestMapping(value= "/basic", method={ RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){

        log.info("basic get..............");
    }

    @RequestMapping("/basicOnlyGet")
    public void basicGet2(){

        log.info("basic get only get..............");

    }

    // 파라미터의 수집
    @GetMapping("/ex01")
    public String ex01(SampleDTO dto){

        log.info("" + dto);

        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){

        log.info("name: " + name);
        log.info("age: " + age);

        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids){

        log.info("ids: " + ids);

        return "ex02List";
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids){

        log.info("array ids: " + Arrays.toString(ids));

        return "ex02Array";
    }

    // 객체리스트
    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list){

        log.info("list dtods: " + list);

        return "ex02Bean";
    }

}
