package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Log4j
@Service
// @AllArgsConstructor
// 2개의 Mapper를 주입받아야 하기 때문에 Setter 메서드를 이용하도록 수정한다.

public class BoardServiceImpl implements BoardService{
    @Setter(onMethod_ = {@Autowired})
    private BoardMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private BoardAttachMapper attachMapper;

    @Transactional
    @Override
    public void register(BoardVO board) {

        log.info("register........." + board);
        // 지금까지만해도 bno 는 null 이다.
        mapper.insertSelectKey(board);
        // bno 에 값이 생겼다.
        log.info("bno 값 갱신 ? : " + board.getBno());

        if(board.getAttachList() == null || board.getAttachList().size() <= 0){
            return;
        }

        board.getAttachList().forEach(attach -> {
            attach.setBno(board.getBno());
            attachMapper.insert(attach);
        });


    }

    @Override
    public BoardVO get(Long bno) {

        log.info("get............" + bno);

        return mapper.read(bno);
    }

    @Transactional
    @Override
    public boolean modify(BoardVO board) {

        log.info("modify........." + board);

        attachMapper.deleteAll(board.getBno());

        boolean modifyResult = mapper.update(board) == 1;

        if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0){

            board.getAttachList().forEach(attach -> {

                attach.setBno(board.getBno());
                attachMapper.insert(attach);
            });
        }

        return modifyResult;
    }

    @Override
    public boolean remove(Long bno) {

        log.info("remove......." + bno);

        // 첨부파일의 삭제가 같이 일어난다.
        attachMapper.deleteAll(bno);

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

    // 26.1 BoardService 수정
    @Override
    public List<BoardAttachVO> getAttachList(Long bno) {

        log.info("get Attach list by bno: " + bno);

        return attachMapper.findByBno(bno);
    }
}
