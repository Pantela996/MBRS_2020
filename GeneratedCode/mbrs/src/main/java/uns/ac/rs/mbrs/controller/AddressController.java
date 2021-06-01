package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.service.AddressService;
import uns.ac.rs.mbrs.dto.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/address")
public class AddressController {  

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	String getAddressList (Model model) {

		List<Address> addressList = addressService.findAll();
		
		List<AddressDTO> addressDTOList =
			addressList
			.stream()
            .map((element) -> modelMapper.map(element, AddressDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", addressDTOList);
		
		return "address/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
		Address address = addressService.findOne(id);
		if (address == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(address, AddressDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AddressDTO> add(@RequestBody @Valid AddressDTO newAddress) {

		Address savedAddress = addressService.save(toAddress.convert(newAddress));

		return new ResponseEntity<>(
			modelMapper.map(savedAddress, AddressDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AddressDTO> edit(@RequestBody @Valid AddressDTO address, @PathVariable Long id) {

		if (id != address.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Address persisted = addressService.save(toAddress.convert(address));

		return new ResponseEntity<>(
			modelMapper.map(persisted, AddressDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AddressDTO> delete(@PathVariable Long id) {
		addressService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
