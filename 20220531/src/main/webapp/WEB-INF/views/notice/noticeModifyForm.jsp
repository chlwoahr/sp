<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>게시글 등록</h1></div>
	<div>
		<form action="noticeModify.do" method="post" enctype="multipart/form-data">
			<div><input type="hidden" id="noticeId" name="noticeId" value="${notice.noticeId }">
				<table border="1">
					<tr>
						<th width="100">작성자</th>
						
						<td width="150"> ${notice.noticeName }
						<th width="100">작성일자</th>
						<td width="150">${notice.noticeDate }
					</tr>
					<tr>
						<th width="100">제목</th>
						<td colspan="3" width="150"><input type="text" id="noticeTitle" name="noticeTitle" size="58" value=${notice.noticeTitle }></td>
					</tr>
					<tr>
						<th width="100">내용</th>
						<td colspan="3">
						<textarea rows="10" cols="60" id="noticeContents" name="noticeContents">
						${notice.noticeContents }
						</textarea></td>
					</tr>
					<tr>
						<th width="100">첨부파일</th>
						<td colspan="3">
						<input type="file" id="file" name="file" multiple="multiple"></td>
					</tr>	
				</table>
			</div><br/>
			<div>
				<input type="submit" value="수정">&nbsp;&nbsp;
				<button type="button" onclick="removeCheck()">삭제</button>
				<input type="button" value="목록" onclick="location.href='noticeList.do'">
			</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">

function removeCheck(){
	if(confirm("해달 글을 지우겠습니까?") == true){
		location.href="noticeDelete.do?noticeId="+${notice.noticeId};
		
	}else
		false;

}
</script>
</html>