package co.micol.prj.notice.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.notice.vo.NoticeVO;

public interface NoticeMapper {
	//MapperInterface에서 두개 이상의 매개변수를 받을 경우 @Param 선언해줘야한다
	List<NoticeVO> noticeSelectList(@Param("state") int state, @Param("key") String key);
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	int noticeHitUpdate(int id); //조회수 변경
}
