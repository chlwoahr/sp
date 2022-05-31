package co.micol.prj.student.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.micol.prj.student.service.StudentService;
import co.micol.prj.student.vo.StudentVO;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentDao;
	
	@RequestMapping("/studentList.do")
	public String StudentList(Model model) {
		model.addAttribute("students", studentDao.studentSelectList());
		return "student/studentList";
	}
	
	@RequestMapping("/studentJoinForm.do")
	public String studentJoinForm() {
		return "student/studentJoinForm";
	}
	
	@PostMapping("/studentJoin.do")
	public String studentJoin(StudentVO vo, Model model) { //form에서 가지고 있는 파라메터 값이 자동으로 vo에 담겨서 활용가능
		//이때, StudentVO의 변수명과 input태그의 name이 같아야한다.
		int n = studentDao.StudentInsert(vo);
		if (n != 0) {
			model.addAttribute("message", "회원가입성공!");
		} else {
			model.addAttribute("message", "회원가입실패!");
		}
		return "student/studentJoin";
	}
	
	@GetMapping("/ajaxIdCheck.do")
	@ResponseBody
	public String ajaxIdCheck(String id) {
		boolean b = studentDao.idCheck(id);
		String data = "N";  //이미 사용중인 아이디
		if (!b) {
			data = "Y";  //사용할 수 있는 아이디
		}
		return data;
	}
	
	@RequestMapping("/studentLoginForm.do")
	public String studentLoginForm(StudentVO vo, Model model) {
		return "student/studentLoginForm";
	}
	
	@RequestMapping("/studentLogin.do")
	public String studentLogin(StudentVO vo, Model model, HttpSession session) {
//		System.out.println(request.getParameter("id")); request객체에서 넘어온 값을 자동으로 vo 객체에 스프링이 자동으로 담아주므로 사용할 필요 없음 
		vo = studentDao.studentSelect(vo);
		if (vo != null) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			model.addAttribute("message", "님 환영!!");
		} else {
			model.addAttribute("message", "아이디 또는 패스워드를 확인하세요");
		}
		return "student/studentLogin";
	}
	
	@RequestMapping("/studentLogout.do")
	public String studentLogout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("message", "정상적으로 로그아웃되었습니다.");
		return "student/studentLogout";
	}
}
