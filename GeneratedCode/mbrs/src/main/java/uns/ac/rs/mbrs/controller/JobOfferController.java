package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.JobOffer;
import uns.ac.rs.mbrs.service.JobOfferService;
import uns.ac.rs.mbrs.dto.JobOfferDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@Controller
@RequestMapping(value="/jobOffer")
public class JobOfferController {  

	@Autowired
	private JobOfferService jobOfferService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getJobOfferList (Model model) {

		List<JobOffer> jobOfferList = jobOfferService.findAll();
		
		List<JobOfferDTO> jobOfferDTOList =
			jobOfferList
			.stream()
            .map((element) -> modelMapper.map(element, JobOfferDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", jobOfferDTOList);
		
		return "jobOffer/index";
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

		JobOffer jobOffer = new JobOffer(newJobOffer);
		JobOffer savedJobOffer = jobOfferService.save(jobOffer);

		return new ResponseEntity<>(
			modelMapper.map(savedJobOffer, JobOfferDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<JobOfferDTO> edit(@RequestBody @Valid JobOfferDTO jobOffer, @PathVariable Long id) {

		JobOffer foundJobOffer = jobOfferService.findOne(id);
		
		if (foundJobOffer == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		JobOffer persisted = jobOfferService.save(foundJobOffer);

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
