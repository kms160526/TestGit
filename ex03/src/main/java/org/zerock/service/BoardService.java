package org.zerock.service;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO board);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

    public BoardVO get(Long bno);

//    public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria cri);

    // 14.6 MyBatis에서 전체 데이터의 개수 처리
    public int getTotal(Criteria cri);

}
