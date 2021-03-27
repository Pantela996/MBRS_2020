package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long>{
	JobOffer findById(Long id);
}