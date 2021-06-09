package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.service.JobService;
import uns.ac.rs.mbrs.dto.JobDTO;

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
@RequestMapping(value="/job")
public class JobController {  

	@Autowired
	private JobService jobService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getJobList (Model model) {

		List<Job> jobList = jobService.findAll();
		
		List<JobDTO> jobDTOList =
			jobList
			.stream()
            .map((element) -> modelMapper.map(element, JobDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", jobDTOList);
		
		return "job/index";
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

		Job job = new Job(newJob);
		Job savedJob = jobService.save(job);

		return new ResponseEntity<>(
			modelMapper.map(savedJob, JobDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<JobDTO> edit(@RequestBody @Valid JobDTO job, @PathVariable Long id) {

		Job foundJob = jobService.findOne(id);
		
		if (foundJob == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundJob.setNameOfPosition(job.getNameOfPosition());

		Job persisted = jobService.save(foundJob);

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
