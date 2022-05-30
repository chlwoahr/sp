package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

//mybatis 매개변수가 두개이상일경우 다름
public interface StudentMapper { //mybatis에서 사용하는 인터페이스
	List<StudentVO> studentSelectList(); //전체회원
	StudentVO studentSelect(StudentVO vo); //한명조회 or 로그인 처리
	int studentInsert(StudentVO vo); //입력
	int studentUpdate(StudentVO vo); //수정
	int studentDelete(StudentVO vo); //삭제
	
	boolean idCheck(String id); //아이디 중복체크
}
