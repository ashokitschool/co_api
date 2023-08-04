package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CoNoticeEntity;

public interface CoNoticeRepo extends JpaRepository<CoNoticeEntity, Integer>{
	
	
	public List<CoNoticeEntity> findByNoticeStatus(String status);

}
