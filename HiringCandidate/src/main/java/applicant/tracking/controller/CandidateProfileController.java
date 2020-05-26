package applicant.tracking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import applicant.tracking.dao.AtsMasterTableRepository;
import applicant.tracking.dao.CandidateProfileRepository;
import applicant.tracking.model.CandidateProfile;
import applicant.tracking.service.CandidateProfileService;

@RestController("/candidateapi")
public class CandidateProfileController {

	@Autowired
	private CandidateProfileService candidateprofileservice;

	@Autowired
	private CandidateProfileRepository candidateRepository;

	@Autowired
	private AtsMasterTableRepository atsmastertablerepository;

	// Api for fetching candidate list page wise
	@RequestMapping(value = "candidateapi/candidateslist", method = RequestMethod.GET)
	public Page<CandidateProfile> fetchByPage(Pageable pageable) {

		if (candidateprofileservice.findAllByPage(pageable).isEmpty()) {
			throw new RuntimeException("No Candidate details is avilable, Please add candidate details first");
		} else {
			return candidateprofileservice.findAllByPage(pageable);
		}

	}

	@RequestMapping(value = "candidateapi/candidatedetails/{id}", method = RequestMethod.GET)
	public Optional<CandidateProfile> getCandid(@PathVariable Long id) {
		if (candidateRepository.findById(id).isPresent() == true) {
			return candidateprofileservice.getCandidateById(id);
		} else {
			throw new RuntimeException("Invalid Id");
		}
	}

	// Add candidate Profile api
	@RequestMapping(value = "candidateapi/addcandidateprofile", method = RequestMethod.POST)
	public CandidateProfile addCandidProfile(@Valid CandidateProfile candidateprofile, MultipartFile file) {
		candidateprofileservice.saveCandidateProfile(candidateprofile, file);
		return candidateprofile;

	}
    //Update candidate profile api 
	@RequestMapping(value = "candidateapi/updatecandidateprofile/{id}", method = RequestMethod.PUT)
	public CandidateProfile updateCandidateProfile(CandidateProfile candidateprofile, @PathVariable Long id,
			MultipartFile file) {

		if (candidateRepository.findById(id).isPresent() == true) {
			return candidateprofileservice.updateCandidateProfile(id, candidateprofile, file);
		} else {
			throw new RuntimeException("Invalid Id");
		}

	}
    //Delete candidate profile api
	@RequestMapping(value = "candidateapi/deletecandidate/{id}", method = RequestMethod.DELETE)
	public String deleteCandid(CandidateProfile candidateprofile, @PathVariable Long id) {

		if (candidateRepository.findById(id).isPresent() == true) {
			return candidateprofileservice.deleteCandidate(id);
		} else {
			throw new RuntimeException("Invalid Id");
		}
	}

}
