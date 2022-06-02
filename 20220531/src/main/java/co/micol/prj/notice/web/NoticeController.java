package co.micol.prj.notice.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		if(fileName != null && !fileName.isEmpty() && fileName.length() != 0) {
			//UUID : 범용 고유 식별자
			//UUID 사용하여 파일 명 변경
			
			String uid = UUID.randomUUID().toString();
			String saveFileName = uid + fileName.substring(fileName.indexOf("."));
			
			//UUID로 만든 파일명 넣기
			File target = new File(saveDir, saveFileName);
			vo.setNoticeAttech(fileName); //파일이름을 담고
			vo.setNoticeDir(saveFileName); //실제 저장경로(물리적저장경로)를 담고
			
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		noticeDao.noticeInsert(vo); //파일이 있을 경우 파일 먼저 업로드하고 DB저장
		return "redirect:noticeList.do";
	}
	
	@PostMapping("/getContent.do")
	public String getContent(NoticeVO vo, Model model) {
	//public String getContent(@RequestParam int noticeId) {
		noticeDao.noticeHitUpdate(vo.getNoticeId());
		model.addAttribute("content", noticeDao.noticeSelect(vo));
		return "notice/noticeContent";
	}
	
	@RequestMapping("/downLoad.do")
	public void downLoad(@RequestParam("saveName") String saveName, @RequestParam("fileName") String fileName ,HttpServletResponse resp) {
				
		try {
		//file을 배열로 전환
			
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray
				(new File(saveDir + File.separator + saveName));
				//모든 타입의 데이터를 전송할때 사용
				resp.setContentType("application/octex-stream");
				//파일 길이만큼 사이즈 설정
				resp.setContentLength(fileByte.length);
				//파일을 다운 받기위해 설정
				resp.setHeader("Content-Disposition", "attachment; fileName=\""
				+ URLEncoder.encode(fileName, "UTF-8") + "\";");
				
				resp.getOutputStream().write(fileByte);
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
						
	}
	
	//게시글 삭제
	@RequestMapping("/noticeDelete.do")
	public String noticeDelete(NoticeVO vo) {
		noticeDao.noticeDelete(vo);
		return "redirect:noticeList.do";
	}
	//게시글 수정
	@RequestMapping("/noticeModifyForm.do")
	public String noticeModifyForm(NoticeVO vo, Model model) {
		model.addAttribute("notice", noticeDao.noticeSelect(vo));
		return "notice/noticeModifyForm";
	}
	@RequestMapping("/noticeModify.do")
	public String noticeModify(NoticeVO vo, MultipartFile file) {
			String fileName = file.getOriginalFilename();
		
		if(fileName != null && !fileName.isEmpty() && fileName.length() != 0) {
			//UUID : 범용 고유 식별자
			//UUID 사용하여 파일 명 변경
			
			String uid = UUID.randomUUID().toString();
			String saveFileName = uid + fileName.substring(fileName.indexOf("."));
			
			//UUID로 만든 파일명 넣기
			File target = new File(saveDir, saveFileName);
			vo.setNoticeAttech(fileName); //파일이름을 담고
			vo.setNoticeDir(saveFileName); //실제 저장경로(물리적저장경로)를 담고
			
			try {
				FileCopyUtils.copy(file.getBytes(), target); //실제 파일을 저장
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		noticeDao.noticeUpdate(vo);
		
		return "redirect:noticeList.do";
	}
	@PostMapping("/ajaxSearchList.do")
	@ResponseBody
	public List<NoticeVO> ajaxSearchList(@RequestParam("state") int state, @RequestParam("key") String key){
				
		return noticeDao.noticeSelectList(state, key) ;
	}
	
	
	
	
	
}