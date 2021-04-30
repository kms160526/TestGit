package org.zerock.mapper;

import org.zerock.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {

    public void insert(BoardAttachVO vo);

    public void delete(String uuid);

    public List<BoardAttachVO> findByBno(Long bno);

    public void deleteAll(Long bno);

    // 첨부파일의 목록을 가져오는 메서드
    public List<BoardAttachVO> getOldFiles();

}
