package applicant.tracking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import applicant.tracking.model.CandidateProfile;

public interface CandidateProfileService {

	// Create
	public CandidateProfile saveCandidateProfile(CandidateProfile candidateprofile, MultipartFile file);

	// Update
	public CandidateProfile updateCandidateProfile(Long id, CandidateProfile candidateprofile, MultipartFile file);

	// Delete
	public String deleteCandidate(Long id);

	// Fetching Candidate Details Pagewise
	public Page<CandidateProfile> findAllByPage(Pageable pageable);

	public Optional<CandidateProfile> getCandidateById(Long id);

}
