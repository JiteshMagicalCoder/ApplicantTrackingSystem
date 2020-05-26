package applicant.tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AtsMasterTable")
public class AtsMasterTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CandidateId")
	private Long candidateid;

	@Column(name = "CandidateName")
	private String candidateName;

	@Column(name = "RecruiterId")
	private String recruiterId;

	@Column(name = "RecruiterName")
	private String recruiterName;

	@Column(name = "CandidateProfileStatus")
	@Enumerated(EnumType.STRING)
	private ProfileStatus profilestatus;

	@Column(name = "CandidateContactNumber")
	private String candidateContactNumber;

	@Column(name = "CandidateEmailId")
	private String candidateEmailId;

	public AtsMasterTable() {

	}

	public Long getCandidateId() {
		return candidateid;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateid = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public ProfileStatus getCandidateProfileStatus() {
		return profilestatus;
	}

	public void setCandidateProfileStatus(ProfileStatus profileStatus) {
		this.profilestatus = profileStatus;
	}

	public String getCandidateContactNumber() {
		return candidateContactNumber;
	}

	public void setCandidateContactNumber(String candidateContactNumber) {
		this.candidateContactNumber = candidateContactNumber;
	}

	public String getCandidateEmailId() {
		return candidateEmailId;
	}

	public void setCandidateEmailId(String candidateEmailId) {
		this.candidateEmailId = candidateEmailId;
	}

}
