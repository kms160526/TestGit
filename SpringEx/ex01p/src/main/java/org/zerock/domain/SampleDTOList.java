package org.zerock.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {

    // 객체리스트
    private List<SampleDTO> list;

    public SampleDTOList(){
        list = new ArrayList<>();
    }
}
