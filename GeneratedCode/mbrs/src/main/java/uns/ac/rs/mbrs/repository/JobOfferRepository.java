package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long>{
	JobOffer findById(Long id);
}

