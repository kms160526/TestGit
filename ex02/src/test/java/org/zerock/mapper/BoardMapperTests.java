package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));

    }

    // BoardMapper의 insert 테스트
    @Test
    public void testInsert(){

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }

    // selectKey 를 이용하는경우 테스트코드
    @Test
    public void testInsertSelectKey(){

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글 select Key");
        board.setContent("새로 작성하는 내용 select Key");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);

        log.info(board);
    }

    // 현재 테이블에 존재하는 데이터의 bno 칼럼의 값을 이용해서 테스트 코드를 확인
    @Test
    public void testRead(){
        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.read(5L);
        log.info(board);

    }

    // delete 테스트 코드 제대로 작동하는지 확인하기
    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + mapper.delete(3L));
    }

    // update 테스트 코드
    @Test
    public void testUpdate(){

        BoardVO board = new BoardVO();
        // 실행전 존재하는 번호인지 확인할 것
        board.setBno(5L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);
        log.info("UPDATE COUNT : " + count);
    }

    // 13.1.1 페이징 테스트와 수정
    @Test
    public void testPaging(){

        Criteria cri = new Criteria();
        // 10개씩 3페이지
        cri.setPageNum(2);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }

    // 15.3.1 테스트 실시
    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setKeyword("새로");
        cri.setType("");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));

    }


}
