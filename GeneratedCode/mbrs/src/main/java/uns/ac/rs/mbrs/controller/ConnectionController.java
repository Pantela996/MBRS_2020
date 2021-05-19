package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Connection;
import uns.ac.rs.mbrs.service.ConnectionService;
import uns.ac.rs.mbrs.dto.ConnectionDTO;

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
@RequestMapping(value="/api/connection")
public class ConnectionController {  

	@Autowired
	private ConnectionService connectionService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ConnectionDTO>> getConnectionList () {

		List<Connection> connectionList = connectionService.findAll();
		
		List<ConnectionDTO> connectionDTOList =
			connectionList
			.stream()
            .map((element) -> modelMapper.map(element, ConnectionDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			connectionDTOList,
			HttpStatus.OK
		);
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

		Connection savedConnection = connectionService.save(toConnection.convert(newConnection));

		return new ResponseEntity<>(
			modelMapper.map(savedConnection, ConnectionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ConnectionDTO> edit(@RequestBody @Valid ConnectionDTO connection, @PathVariable Long id) {

		if (id != connection.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Connection persisted = connectionService.save(toConnection.convert(connection));

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
