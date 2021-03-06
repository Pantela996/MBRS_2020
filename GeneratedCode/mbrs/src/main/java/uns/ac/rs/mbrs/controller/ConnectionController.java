package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Connection;
import uns.ac.rs.mbrs.service.ConnectionService;
import uns.ac.rs.mbrs.dto.ConnectionDTO;

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
@RequestMapping(value="/connection")
public class ConnectionController {  

	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getConnectionList (Model model) {

		List<Connection> connectionList = connectionService.findAll();
		
		List<ConnectionDTO> connectionDTOList =
			connectionList
			.stream()
            .map((element) -> modelMapper.map(element, ConnectionDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", connectionDTOList);
		
		return "connection/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ConnectionDTO> getConnection(@PathVariable Long id) {
		Connection connection = connectionService.findOne(id);
		if (connection == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(connection, ConnectionDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ConnectionDTO> add(@RequestBody @Valid ConnectionDTO newConnection) {

		Connection connection = new Connection(newConnection);
		Connection savedConnection = connectionService.save(connection);

		return new ResponseEntity<>(
			modelMapper.map(savedConnection, ConnectionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ConnectionDTO> edit(@RequestBody @Valid ConnectionDTO connection, @PathVariable Long id) {

		Connection foundConnection = connectionService.findOne(id);
		
		if (foundConnection == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundConnection.setActive(connection.getActive());

		Connection persisted = connectionService.save(foundConnection);

		return new ResponseEntity<>(
			modelMapper.map(persisted, ConnectionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ConnectionDTO> delete(@PathVariable Long id) {
		connectionService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
