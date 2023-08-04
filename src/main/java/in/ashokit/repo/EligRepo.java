package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.EligEntity;

public interface EligRepo extends JpaRepository<EligEntity, Integer> {
	
	public EligEntity findByCaseNum(Long caseNum);
}
