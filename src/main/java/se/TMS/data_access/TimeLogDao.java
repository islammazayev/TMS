package se.TMS.data_access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.TMS.model.TimeLog;

@Repository("timeLogDao")
public interface TimeLogDao extends JpaRepository<TimeLog, Long> {

}
