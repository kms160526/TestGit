package org.zerock.service;

import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {

    public void register(BoardVO board);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

    public List<BoardVO> getList();

    public BoardVO get(Long bno);
}
