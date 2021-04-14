package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    public int insert(ReplyVO vo);

    public ReplyVO read(Long rno);

    // 삭제
    public int delete (Long rno);

    // 수정
    public int update (ReplyVO reply);

    // Criteria를 사용한다.
    public List<ReplyVO> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("bno") Long bno
    );
}
