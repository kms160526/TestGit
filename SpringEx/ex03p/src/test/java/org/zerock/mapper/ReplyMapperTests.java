package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class ReplyMapperTests {
    // 댓글 등록 테스트 294934 //294933 //294931 //294930 //294929
    private Long[] bnoArr = {294934L, 294933L, 294931L, 294930L ,294929L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Test
    public void testMapper(){
        log.info(mapper);
    }

    // 댓글 등록 테스트
    @Test
    public void testCreate(){

        IntStream.rangeClosed(1,40).forEach(i -> {
           ReplyVO vo = new ReplyVO();

           // 게시물 번호
            vo.setBno(bnoArr[0]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer " + i);

            mapper.insert(vo);
        });
    }

    // read 테스트
    @Test
    public void testRead(){

        Long targetRno = 5L;

        ReplyVO vo = mapper.read(targetRno);

        log.info(vo);
    }

    // delete 테스트
    @Test
    public void testDelete(){

        Long targetRno = 5L;

        mapper.delete(targetRno);
    }

    @Test
    public void testUpdate(){

        Long targetRno = 10L;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("Update Reply");

        int count = mapper.update(vo);

        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testList(){

        Criteria cri = new Criteria();
        //294934L
        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(reply));

    }

    @Test
    public void testList2(){

        Criteria cri = new Criteria(1, 10);

        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(reply));
    }


}
