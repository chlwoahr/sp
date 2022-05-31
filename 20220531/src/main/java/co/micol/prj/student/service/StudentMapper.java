package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

public interface StudentMapper { //mybatis에서 사용하는 interface(매개변수 받는 방법이 다르기 때문에 나누어줌)

	List<StudentVO> studentSelectList(); //전체학생
	StudentVO studentSelect(StudentVO vo); //한명 조회 or 로그인처리
	int StudentInsert(StudentVO vo); //학생추가
	int StudentUpdate(StudentVO vo); //학생수정
	int StudentDelete(StudentVO vo); //학생삭제
	
	boolean idCheck(String id); //아이디 중복체크
}
