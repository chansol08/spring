package org.spring.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.spring.example.model.MemberVO;

import java.util.List;

@Mapper
public interface MemberMapper {

    int memberInsert(MemberVO memberVO);

    List<MemberVO> memberList();

    MemberVO memberContent(int number);

    int memberUpdate(MemberVO member);

    int memberDelete(int number);
}
