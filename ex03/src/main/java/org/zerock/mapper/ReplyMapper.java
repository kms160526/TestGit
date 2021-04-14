package org.zerock.mapper;

import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

    public int insert(ReplyVO vo);

    public ReplyVO read(Long bno);

    // 삭제
    public int delete (Long rno);

    // 수정
    public int update (ReplyVO reply);
}
