package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long>{
	Optional<JobOffer> findById(Long id);
	
	List<JobOffer> findAll();
}

