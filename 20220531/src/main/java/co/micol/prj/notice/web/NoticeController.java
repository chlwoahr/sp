package co.micol.prj.notice.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	
	@Autowired private NoticeService noticeDao;
	@Autowired private String saveDir; //IoC에서 저장된 것을 가져온다
	
	
	@RequestMapping("/noticeList.do")
	public String noticeList(Model model) {
		model.addAttribute("notices", noticeDao.noticeSelectList(1, "전체"));
		return "notice/noticeList";
	}
	
	@RequestMapping("/noticeInsertForm.do")
	public String noticeInsertForm() {
		return "notice/noticeInsertForm";
	}
	
	@RequestMapping("/noticeInsert.do")
	public String noticeInsert(NoticeVO vo, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		
		if(fileName != null) {
			
			File target = new File(saveDir, fileName);
			vo.setNoticeAttech(fileName); //파일이름을 담고
			vo.setNoticeDir(target.toString()); //실제 저장경로(물리적저장경로)를 담고
			
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		noticeDao.noticeInsert(vo); //파일이 있을 경우 파일 먼저 업로드하고 DB저장
		return "redirect:noticeList.do";
	}
}
