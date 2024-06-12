package kafta.onyx.kafkaconsumerdatabases.repository;

import kafta.onyx.kafkaconsumerdatabases.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long>{
}
