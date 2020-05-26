package applicant.tracking.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import applicant.tracking.dao.AtsMasterTableRepository;
import applicant.tracking.dao.CandidateProfileRepository;
import applicant.tracking.dao.FileFolderPathRepository;
import applicant.tracking.exception.FileStorageException;
import applicant.tracking.exception.ValidationException;
import applicant.tracking.model.AtsMasterTable;
import applicant.tracking.model.CandidateProfile;
import applicant.tracking.model.FileFolderPathTable;
import applicant.tracking.service.CandidateProfileService;

@Service
public class CandidateProfileServiceImpl implements CandidateProfileService {

	@Autowired
	private CandidateProfileRepository candidateRepository;
	@Autowired
	private AtsMasterTableRepository atsmastertablerepository;
	@Autowired
	private FileFolderPathRepository filefolderpathrepository;

	private Path fileStorageLocation;

	// Fetching Candidate Details Pagewise
	@Override
	public Page<CandidateProfile> findAllByPage(Pageable pageable) {

		return candidateRepository.findAll(pageable);
	}

	// Create
	public CandidateProfile saveCandidateProfile(CandidateProfile candidateprofile, MultipartFile file) {
		List<FileFolderPathTable> pathfromdb=filefolderpathrepository.findAll();
		if(pathfromdb.isEmpty()) {
			throw new FileStorageException("Sorry! please set file folder path");
		}
		
	for (FileFolderPathTable path : pathfromdb) {
	 fileStorageLocation= Paths.get(path.getFilefolderpath());
	}
		
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileNo = UUID.randomUUID().toString();
	//	System.out.println(fileStorageLocation);
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileNo + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			candidateprofile.setResumestoragepath(fileStorageLocation + "\\" + fileNo + file.getOriginalFilename());

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		
		AtsMasterTable atsmastertable = new AtsMasterTable();
		atsmastertable.setCandidateId(candidateprofile.getCandidateid());
		atsmastertable.setCandidateName(candidateprofile.getCandidatename());
		atsmastertable.setCandidateEmailId(candidateprofile.getEmailid());
		atsmastertable.setCandidateContactNumber(candidateprofile.getContactno());
		atsmastertable.setCandidateProfileStatus(candidateprofile.getProfilestatus());
		atsmastertable.setRecruiterId(candidateprofile.getRecruiterid());
		atsmastertable.setRecruiterName(candidateprofile.getRecruitername());
		candidateprofile.setAtsmastertable(atsmastertable);
		return candidateRepository.save(candidateprofile);
		 
	}

	// Update
	@Override
	public CandidateProfile updateCandidateProfile(Long id, CandidateProfile candidateprofile, MultipartFile file) {

		List<FileFolderPathTable> pathfromdb = filefolderpathrepository.findAll();
		if(pathfromdb.isEmpty()) {
			throw new FileStorageException("Sorry! please set file folder path");
		}
		
		for (FileFolderPathTable path : pathfromdb) {
			fileStorageLocation = Paths.get(path.getFilefolderpath());
		}

		File oldfile = new File(candidateRepository.findById(id).get().getResumestoragepath());
		oldfile.delete();

		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileNo = UUID.randomUUID().toString();
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileNo + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			candidateprofile.setResumestoragepath(fileStorageLocation + "\\" + fileNo + file.getOriginalFilename());

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}

		if (candidateprofile.getCandidatename() == null || candidateprofile.getFathername() == null
				|| candidateprofile.getDob() == null || candidateprofile.getEmailid() == null
				|| candidateprofile.getContactno() == null || candidateprofile.getTechnology() == null
				|| candidateprofile.getLocation() == null || candidateprofile.getDesignation() == null
				|| candidateprofile.getExperience() == 0 || candidateprofile.getRelevantexperience() == 0
				|| candidateprofile.getProfilestatus() == null || candidateprofile.getCurrentsalaryinlpa() == 0
				|| candidateprofile.getExpectedsalaryinlpa() == 0 || candidateprofile.getNoticeperiodindays() == 0
				|| candidateprofile.getRecruiterid() == null || candidateprofile.getRecruitername() == null
				|| candidateprofile.getResumestoragepath() == null
				|| candidateprofile.getCandidateprofilecomments() == null
				|| candidateprofile.getTechnicalskill() == null) {
			throw new ValidationException("Please insert the required fields");
		} else {
			AtsMasterTable atsmastertable = new AtsMasterTable();
			atsmastertable.setCandidateId(candidateprofile.getCandidateid());
			atsmastertable.setCandidateName(candidateprofile.getCandidatename());
			atsmastertable.setCandidateEmailId(candidateprofile.getEmailid());
			atsmastertable.setCandidateContactNumber(candidateprofile.getContactno());
			atsmastertable.setCandidateProfileStatus(candidateprofile.getProfilestatus());
			atsmastertable.setRecruiterId(candidateprofile.getRecruiterid());
			atsmastertable.setRecruiterName(candidateprofile.getRecruitername());
			candidateprofile.setAtsmastertable(atsmastertable);

			candidateRepository.findById(id).map(newcandidate -> {
				newcandidate.setCandidatename(candidateprofile.getCandidatename());
				newcandidate.setFathername(candidateprofile.getFathername());
				newcandidate.setDob(candidateprofile.getDob());
				newcandidate.setEmailid(candidateprofile.getEmailid());
				newcandidate.setContactno(candidateprofile.getContactno());
				newcandidate.setTechnology(candidateprofile.getTechnology());
				newcandidate.setTechnicalskill(candidateprofile.getTechnicalskill());
				newcandidate.setLocation(candidateprofile.getLocation());
				newcandidate.setDesignation(candidateprofile.getDesignation());
				newcandidate.setExperience(candidateprofile.getExperience());
				newcandidate.setRelevantexperience(candidateprofile.getRelevantexperience());
				newcandidate.setCurrentsalaryinlpa(candidateprofile.getCurrentsalaryinlpa());
				newcandidate.setExpectedsalaryinlpa(candidateprofile.getExpectedsalaryinlpa());
				newcandidate.setNoticeperiodindays(candidateprofile.getNoticeperiodindays());
				newcandidate.setRecruiterid(candidateprofile.getRecruiterid());
				newcandidate.setRecruitername(candidateprofile.getRecruitername());
				newcandidate.setResumestoragepath(candidateprofile.getResumestoragepath());
				newcandidate.setProfilestatus(candidateprofile.getProfilestatus());
				newcandidate.setCandidateprofilecomments(candidateprofile.getCandidateprofilecomments());
				return candidateRepository.save(newcandidate);

			});
			return candidateprofile;
		}
	}

	// Delete
	public String deleteCandidate(Long id) {
		candidateRepository.deleteById(id);
		atsmastertablerepository.deleteById(id);
		return "Candidate profile deleted successfully";
	}

	// Get Candidate By Id
	@Override
	public Optional<CandidateProfile> getCandidateById(Long id) {

		return candidateRepository.findById(id);

	}

}
