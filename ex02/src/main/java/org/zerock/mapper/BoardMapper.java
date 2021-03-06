package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {
    // XML 에서 SQL문을 처리하였으므로 인터페이스의 SQL문을 제거
    // 제거 이후에는 테스트코드를 통해서 기존과 동일하게 작동하는지 확인해야함.
    public List<BoardVO> getList();

    // 8.1.2 메소드 추가
    public void insert(BoardVO board);

    public void insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    // 8.2.3 delete 처리
    public int delete(Long bno);

    // 8.2.4 update 처리
    public int update(BoardVO board);

    // 13.1 MyBatis 처리와 테스트
    public List<BoardVO> getListWithPaging(Criteria cri);

    // 14.6 MyBatis에서 전체 데이터의 개수 처리
    public int getTotalCount(Criteria cri);
}
