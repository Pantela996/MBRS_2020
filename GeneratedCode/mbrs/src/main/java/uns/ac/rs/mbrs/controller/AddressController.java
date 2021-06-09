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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@Controller
@RequestMapping(value="/address")
public class AddressController {  

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
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

		Address address = new Address(newAddress);
		Address savedAddress = addressService.save(address);

		return new ResponseEntity<>(
			modelMapper.map(savedAddress, AddressDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AddressDTO> edit(@RequestBody @Valid AddressDTO address, @PathVariable Long id) {

		Address foundAddress = addressService.findOne(id);
		
		if (foundAddress == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundAddress.setStreet(address.getStreet());
		foundAddress.setCity(address.getCity());
		foundAddress.setCountry(address.getCountry());

		Address persisted = addressService.save(foundAddress);

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
