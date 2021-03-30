package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

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

}
