package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public void list(Model model, Criteria cri){

        log.info("list: " + cri);



        // list 가 bno의 역순으로 view에서 출력되는 문제가 발생
        // list에 순서에 맞게 들어갔는지 점검
        List<BoardVO> list = service.getList(cri);
//        list.forEach(board -> log.info(board));
//      체크완료. 정상적으로 출력

        model.addAttribute("list", list);

        int total = service.getTotal(cri);

        log.info("total: " + total);

//         일단 123 이 total 이라고 가정
//        model.addAttribute("pageMaker", new PageDTO(cri, 123));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr){

        log.info("=================================");

        log.info("register: " + board);

        if(board.getAttachList() != null){

            board.getAttachList().forEach(attach -> log.info(attach));
        }

        log.info("=================================");

        service.register(board);

        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:/board/list";
    }

    // void 이므로 register.jsp 파일과 연동이 된다.
    @GetMapping("/register")
    public void register(){

    }

    // 원래의 get 메서드는 게시물의 번호만 받도록 처리 -> Criteria를 파라미터로 추가해서 전달
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){

        log.info("/get or /modify");

        // cri 값을 가져오는지 테스
//        log.info();트

        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info("modify: " + board);

        if(service.modify(board)){
            rttr.addFlashAttribute("result", "success");
        }

//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        log.info("remove..." + bno);
        if(service.remove(bno)){
            rttr.addFlashAttribute("result", "success");
        }

//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }

}
