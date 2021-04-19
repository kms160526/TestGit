package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

//    @Select("select * from tbl_board where bno < 40")
    public List<BoardVO> getList();

    // 8.1.2 메소드 추가
    public void insert(BoardVO board);

    public void insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    // update
    public int update(BoardVO board);
}
