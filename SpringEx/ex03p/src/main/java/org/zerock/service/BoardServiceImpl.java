package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {

        log.info("register........." + board);
        // 지금까지만해도 bno 는 null 이다.
        mapper.insertSelectKey(board);
        // bno 에 값이 생겼다.
        log.info("bno 값 갱신 ? : " + board.getBno());
    }

    @Override
    public BoardVO get(Long bno) {

        log.info("get............" + bno);

        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO board) {

        log.info("modify........." + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {

        log.info("remove......." + bno);

        return mapper.delete(bno) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//
//        log.info("getList............");
//        return mapper.getList();
//    }
    // getList -> paging 처리를한 list로 수정

    @Override
    public List<BoardVO> getList(Criteria cri) {

        log.info("get List... from paging");

        return mapper.getListWithPaging(cri);

    }

    @Override
    public int getTotal(Criteria cri) {

        log.info("get total count");

        return mapper.getTotalCount(cri);
    }
}
