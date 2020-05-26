package applicant.tracking.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import applicant.tracking.model.CandidateProfile;

@Repository
public interface CandidateProfileRepository
		extends JpaRepository<CandidateProfile, Long>, PagingAndSortingRepository<CandidateProfile, Long> {

	Page<CandidateProfile> findAll(Pageable pageable);



}
