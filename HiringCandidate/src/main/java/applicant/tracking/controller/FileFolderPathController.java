package applicant.tracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import applicant.tracking.dao.FileFolderPathRepository;
import applicant.tracking.model.FileFolderPathTable;

@RestController("/filefolderpath")
public class FileFolderPathController {

	@Autowired
	public FileFolderPathRepository filefolderpath;
	
	@RequestMapping(value="filefolderpath/setpath", method = RequestMethod.POST)
	public FileFolderPathTable setFileFolderPath(FileFolderPathTable path) {
		
		filefolderpath.deleteAll();
		return filefolderpath.save(path);
	}
}
