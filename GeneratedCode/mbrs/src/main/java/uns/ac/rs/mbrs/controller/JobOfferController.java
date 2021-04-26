package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.JobOffer;
import uns.ac.rs.mbrs.service.JobOfferService;
import uns.ac.rs.mbrs.dto.JobOfferDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/jobOffer")
public class JobOfferController {  

	@Autowired
	private JobOfferService jobOfferService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<JobOfferDTO>> getJobOfferList () {

		List<JobOffer> jobOfferList = jobOfferService.findAll();
		
		List<JobOfferDTO> jobOfferDTOList =
			jobOfferList
			.stream()
            .map((element) -> modelMapper.map(element, JobOfferDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			jobOfferDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<JobOfferDTO> getJobOffer(@PathVariable Long id) {
		JobOffer jobOffer = jobOfferService.findOne(id);
		if (jobOffer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(jobOffer, JobOfferDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JobOfferDTO> add(@RequestBody @Valid JobOfferDTO newJobOffer) {

		JobOffer savedJobOffer = jobOfferService.save(toJobOffer.convert(newJobOffer));

		return new ResponseEntity<>(
			modelMapper.map(savedJobOffer, JobOfferDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<JobOfferDTO> edit(@RequestBody @Valid JobOfferDTO jobOffer, @PathVariable Long id) {

		if (id != jobOffer.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		JobOffer persisted = jobOfferService.save(toJobOffer.convert(jobOffer));

		return new ResponseEntity<>(
			modelMapper.map(persisted, JobOfferDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<JobOfferDTO> delete(@PathVariable Long id) {
		jobOfferService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
