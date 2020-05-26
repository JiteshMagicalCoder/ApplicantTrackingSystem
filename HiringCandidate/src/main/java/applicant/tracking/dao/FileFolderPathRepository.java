package applicant.tracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicant.tracking.model.FileFolderPathTable;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface FileFolderPathRepository extends JpaRepository<FileFolderPathTable, ID>{

}
