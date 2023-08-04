package in.ashokit.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CoNoticeEntity;
import in.ashokit.entity.EligEntity;
import in.ashokit.repo.CoNoticeRepo;
import in.ashokit.repo.EligRepo;
import in.ashokit.utils.EmailUtils;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private CoNoticeRepo noticeRepo;

	@Autowired
	private EligRepo eligRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public void processPendingTriggers() {
		// get all pending records from co_notices table
		List<CoNoticeEntity> records = noticeRepo.findByNoticeStatus("P");

		for (CoNoticeEntity entity : records) {
			processEachRecord(entity);
		}
	}

	private void processEachRecord(CoNoticeEntity entity) {

		Long caseNum = entity.getCaseNum();

		// get eligibility data

		EligEntity elig = eligRepo.findByCaseNum(caseNum);

		String planStatus = elig.getPlanStatus();

		File pdfFile = null;

		if ("AP".equals(planStatus)) {
			pdfFile = geneatedApprovedNotice(elig);
		} else if ("DN".equals(planStatus)) {
			pdfFile = geneatedDeniedNotice(elig);
		}

		String fileUrl = storePdfInS3(pdfFile);

		boolean isUpdated = updateProcessdRecord(entity, fileUrl);

		if (isUpdated) {
			emailUtils.sendEmail("", "", "", pdfFile);
		}
	}

	private boolean updateProcessdRecord(CoNoticeEntity entity, String fileUrl) {

		entity.setNoticeStatus("H");
		entity.setNoticeUrl(fileUrl);

		noticeRepo.save(entity);

		return true;
	}

	private String storePdfInS3(File pdfFile) {
		// logic store in s3
		return null;

	}

	private File geneatedDeniedNotice(EligEntity elig) {
		// generate pdf file
		return null;

	}

	private File geneatedApprovedNotice(EligEntity elig) {
		// generate pdf file
		return null;
	}

}
