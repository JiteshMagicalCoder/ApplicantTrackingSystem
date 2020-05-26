package applicant.tracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FileFolderPath")
public class FileFolderPathTable {

	@Id
	@Column(name="filefolderpath")
	private String filefolderpath;

	public String getFilefolderpath() {
		return filefolderpath;
	}

	public void setFilefolderpath(String filefolderpath) {
		this.filefolderpath = filefolderpath;
	}
	
	
}
