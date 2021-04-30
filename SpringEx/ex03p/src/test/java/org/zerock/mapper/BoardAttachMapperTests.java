package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardAttachVO;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class BoardAttachMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardAttachMapper mapper;

    @Test
    public void getOldFilesTest(){
        List<BoardAttachVO> list = mapper.getOldFiles();

        list.forEach(attach -> log.info(attach));

    }


}
