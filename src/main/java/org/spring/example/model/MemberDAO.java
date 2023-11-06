package org.spring.example.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAO {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 저장된 회원의 목록을 조회
     *
     * @return 회원 리스트
     */
    public List<MemberVO> memberList() {
        SqlSession session = sqlSessionFactory.openSession();
        List<MemberVO> members = session.selectList("memberList");
        session.close();

        return members;
    }

    /**
     * memberVO 필드에 filename 이 없는 경우 저장
     *
     * @param member memberVO
     * @return 저장된 행의 숫자
     */
    public int memberInsert(MemberVO member) {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.insert("memberInsert", member);
        session.commit();
        session.close();

        return count;
    }

    /**
     * 저장된 회원을 삭제
     *
     * @param number 회원의 기본키
     * @return 삭제된 행의 숫자
     */
    public int memberDelete(int number) {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.delete("memberDelete", number);
        session.commit();
        session.close();

        return count;
    }

    /**
     * 저장된 한 명의 회원 정보를 조회
     *
     * @param number 회원의 기본키
     * @return 회원 객체
     */
    public MemberVO memberContent(int number) {
        SqlSession session = sqlSessionFactory.openSession();
        MemberVO member = session.selectOne("memberContent", number);
        session.close();

        return member;
    }

    /**
     * 회원의 정보를 수정
     *
     * @param member 회원 객체
     * @return 업데이트된 행의 숫자
     */
    public int memberUpdate(MemberVO member) {
        SqlSession session = sqlSessionFactory.openSession();
        int count = session.update("memberUpdate", member);
        session.commit();
        session.close();

        return count;
    }
}
