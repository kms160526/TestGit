package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class ReplyMapperTests {

    // 테스트 할 게시글 번호 -> 294916, 294917, 294918, 294919, 294920, 294921 테스트
    private Long[] bnoArr = {294916L, 294917L, 294918L, 294919L, 294920L, 294921L};


    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Test
    public void testMapper(){

        log.info(mapper);
    }

    // 기존에 존재하는 게시물 일부의 bno 를 생성하는 ReplyVO를 작성
    // 17.2.3
    @Test
    public void testCreate(){

        IntStream.range(1,10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            // 게시물 번호
            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer " + i);
            mapper.insert(vo);
        });


    }

    // 댓글 조회 테스트
    @Test
    public void testRead(){

        Long targetRno = 5L;

        ReplyVO vo = mapper.read(targetRno);

        log.info(vo);
    }

    // 댓글 삭제 테스트
    @Test
    public void testDelete(){

        Long targetRno = 2L;

        mapper.delete(targetRno);

    }

    // 댓글 수정
    @Test
    public void testUpdate(){

        Long targetRno = 10L;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("Update Reply ");

        int count = mapper.update(vo);

        log.info("UPDATE COUNT: " + count);
    }

    // @Param어노테이션과 댓글 목록
    @Test
    public void testList(){

        Criteria cri = new Criteria();

        // bnoArr[0] == 294916L
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(reply));
    }

}
