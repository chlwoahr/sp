package co.micol.prj.student.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.micol.prj.student.service.StudentMapper;
import co.micol.prj.student.service.StudentService;
import co.micol.prj.student.vo.StudentVO;

@Repository("studentDao")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper map; //IoC컨테이너에서 Student-map.xml을 자동주입한다.
	
	@Override
	public List<StudentVO> studentSelectList() {
		
		return map.studentSelectList();
	}

	@Override
	public StudentVO studentSelect(StudentVO vo) {
		
		return map.studentSelect(vo);
	}

	@Override
	public int StudentInsert(StudentVO vo) {
		
		return map.StudentInsert(vo);
	}

	@Override
	public int StudentUpdate(StudentVO vo) {
		
		return map.StudentUpdate(vo); //이름만 변경할 수 있다.
	}

	@Override
	public int StudentDelete(StudentVO vo) {
		
		return map.StudentDelete(vo);
	}

	@Override
	public boolean idCheck(String id) {
		
		return map.idCheck(id);
	}
	
}
