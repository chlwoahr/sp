package co.micol.prj.student.service;

import java.util.List;

import co.micol.prj.student.vo.StudentVO;

public interface StudentService { //dao에서 사용하는 interface

	List<StudentVO> studentSelectList(); //전체학생
	StudentVO studentSelect(StudentVO vo); //한명 조회 or 로그인처리
	int StudentInsert(StudentVO vo); //학생추가
	int StudentUpdate(StudentVO vo); //학생수정
	int StudentDelete(StudentVO vo); //학생삭제
	
	boolean idCheck(String id); //id 중복체크
}
