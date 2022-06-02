package co.micol.prj.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/*
 * Program Name : 공지사항 VO
 * Write by : 홍길동
 * Date : 2022.05.31 최초작성
 * Version : 1.0
 */
@Setter
@Getter
public class NoticeVO {
	//@Data는 생성자부터 toString까지 모든 것들을 가져온다 다만 덩치가 너무 큼	
	
	private int noticeId; //순번
	private String noticeName; //작성자
	private String noticeTitle; //제목
	private String noticeContents; //내용
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date noticeDate; //작성일자
	private int noticeHit; //조회수
	private String noticeAttech; //첨부파일명
	private String noticeDir; //첨부파일 저장폴더 명(물리적위치)
	
}
