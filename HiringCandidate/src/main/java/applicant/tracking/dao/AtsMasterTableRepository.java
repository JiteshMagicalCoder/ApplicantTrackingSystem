package applicant.tracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicant.tracking.model.AtsMasterTable;

@Repository
public interface AtsMasterTableRepository extends JpaRepository<AtsMasterTable, Long>{

}
