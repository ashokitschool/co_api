package in.ashokit.entity;

import javax.persistence.Entity;

@Entity
public class CoNoticeEntity {
	
	private String noticeStatus;
	
	private Long caseNum;
	
	private String noticeUrl;

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public Long getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Long caseNum) {
		this.caseNum = caseNum;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	
	
	

}
