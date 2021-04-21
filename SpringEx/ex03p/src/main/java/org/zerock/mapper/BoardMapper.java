package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {

//    @Select("select * from tbl_board where bno < 40")
    public List<BoardVO> getList();

    // 페이징 처리 관련
    public List<BoardVO> getListWithPaging(Criteria cri);

    // 8.1.2 메소드 추가
    public void insert(BoardVO board);

    public void insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    // update
    public int update(BoardVO board);

    // total
    public int getTotalCount(Criteria cri);

    // 20.1.1 replyCnt를 업데이트 하는 메서드 추가
    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
