package applicant.tracking.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "CandidateProfile")
public class CandidateProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CandidateId")
	private Long candidateid;

	@Size(max = 50)
	@NotNull(message = "Please enter candidatename")
	@Column(name = "CandidateName")
	private String candidatename;

	@NotNull(message = "Please enter fathername")
	@Size(max = 50)
	@Column(name = "FatherName")
	private String fathername;

	@NotNull(message = "Please provide date of birth")
	@Column(name = "Dob")
	private Date dob;

	@NotNull(message = "Please provide valid email id")
	@Email
	@Column(name = "EmailId")
	private String emailid;

	@NotNull(message = "Please provide valid contact number")
	@Size(min = 10, max = 10)
	@Column(name = "ContactNo")
	private String contactno;

	@NotNull(message = "Please enter location")
	@Column(name = "Location")
	private String location;

	@NotNull(message = "Please provide technology details")
	@Column(name = "Technology")
	private String technology;

	@NotNull(message = "Please enter designation")
	@Column(name = "Designation")
	private String designation;

	@NotNull(message = "Please enter technical skill")
	@Column(name = "TechnicalSkill")
	private String technicalskill;

	@NotNull(message = "Please enter experience")
	@Column(name = "Experience")
	private float experience;

	@NotNull(message = "Please enter relevant experience")
	@Column(name = "RelevantExperience")
	private float relevantexperience;

	@NotNull(message = "Please provide current salary details per annum")
	@Column(name = "CurrentSalaryInLpa")
	private float currentsalaryinlpa;

	@NotNull(message = "Please provide expected salary details per annum")
	@Column(name = "ExpectedSalaryInLpa")
	private float expectedsalaryinlpa;

	@NotNull(message = "Please enter notice period details")
	@Column(name = "NoticePeriodInDays")
	private int noticeperiodindays;

	@NotNull(message = "Please enter recruiterid")
	@Column(name = "RecruiterId")
	private String recruiterid;

	@NotNull(message = "Please enter recruitername")
	@Column(name = "RecruiterName")
	private String recruitername;

	@Column(name = "ResumeStoragePath")
	private String resumestoragepath;

	@Column(name = "CandidateProfileComments")
	private String candidateprofilecomments;

	@NotNull(message = "Please enter profilestatus")
	@Column(name = "ProfileStatus")
	@Enumerated(EnumType.STRING)
	private ProfileStatus profilestatus;

	@Column(name = "CandidateProfileCreatedAt")
	@CreationTimestamp
	private Timestamp candidateprofilecreatedat;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CandidateId", referencedColumnName = "CandidateId")
	private AtsMasterTable atsmastertable;

	public CandidateProfile() {

	}

	public Long getCandidateid() {
		return candidateid;
	}

	public void setCandidateid(Long candidateid) {
		this.candidateid = candidateid;
	}

	public String getCandidatename() {
		return candidatename;
	}

	public void setCandidatename(String candidatename) {
		this.candidatename = candidatename;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTechnicalskill() {
		return technicalskill;
	}

	public void setTechnicalskill(String technicalskill) {
		this.technicalskill = technicalskill;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public float getRelevantexperience() {
		return relevantexperience;
	}

	public void setRelevantexperience(float relevantexperience) {
		this.relevantexperience = relevantexperience;
	}

	public float getCurrentsalaryinlpa() {
		return currentsalaryinlpa;
	}

	public void setCurrentsalaryinlpa(float currentsalaryinlpa) {
		this.currentsalaryinlpa = currentsalaryinlpa;
	}

	public float getExpectedsalaryinlpa() {
		return expectedsalaryinlpa;
	}

	public void setExpectedsalaryinlpa(float expectedsalaryinlpa) {
		this.expectedsalaryinlpa = expectedsalaryinlpa;
	}

	public int getNoticeperiodindays() {
		return noticeperiodindays;
	}

	public void setNoticeperiodindays(int noticeperiodindays) {
		this.noticeperiodindays = noticeperiodindays;
	}

	public String getRecruiterid() {
		return recruiterid;
	}

	public void setRecruiterid(String recruiterid) {
		this.recruiterid = recruiterid;
	}

	public String getRecruitername() {
		return recruitername;
	}

	public void setRecruitername(String recruitername) {
		this.recruitername = recruitername;
	}

	public String getResumestoragepath() {
		return resumestoragepath;
	}

	public void setResumestoragepath(String resumestoragepath) {
		this.resumestoragepath = resumestoragepath;
	}

	public String getCandidateprofilecomments() {
		return candidateprofilecomments;
	}

	public void setCandidateprofilecomments(String candidateprofilecomments) {
		this.candidateprofilecomments = candidateprofilecomments;
	}

	public ProfileStatus getProfilestatus() {
		return profilestatus;
	}

	public void setProfilestatus(ProfileStatus profilestatus) {
		this.profilestatus = profilestatus;
	}

	public Timestamp getCandidateprofilecreatedat() {
		return candidateprofilecreatedat;
	}

	public void setCandidateprofilecreatedat(Timestamp candidateprofilecreatedat) {
		this.candidateprofilecreatedat = candidateprofilecreatedat;
	}

	public AtsMasterTable getAtsmastertable() {
		return atsmastertable;
	}

	public void setAtsmastertable(AtsMasterTable atsmastertable) {
		this.atsmastertable = atsmastertable;
	}

}
