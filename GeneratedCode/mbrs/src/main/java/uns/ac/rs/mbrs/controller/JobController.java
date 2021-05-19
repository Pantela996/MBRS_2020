package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Job;
import uns.ac.rs.mbrs.service.JobService;
import uns.ac.rs.mbrs.dto.JobDTO;

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
@RequestMapping(value="/api/job")
public class JobController {  

	@Autowired
	private JobService jobService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<JobDTO>> getJobList () {

		List<Job> jobList = jobService.findAll();
		
		List<JobDTO> jobDTOList =
			jobList
			.stream()
            .map((element) -> modelMapper.map(element, JobDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			jobDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
		Job job = jobService.findOne(id);
		if (job == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(job, JobDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JobDTO> add(@RequestBody @Valid JobDTO newJob) {

		Job savedJob = jobService.save(toJob.convert(newJob));

		return new ResponseEntity<>(
			modelMapper.map(savedJob, JobDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<JobDTO> edit(@RequestBody @Valid JobDTO job, @PathVariable Long id) {

		if (id != job.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Job persisted = jobService.save(toJob.convert(job));

		return new ResponseEntity<>(
			modelMapper.map(persisted, JobDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<JobDTO> delete(@PathVariable Long id) {
		jobService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
