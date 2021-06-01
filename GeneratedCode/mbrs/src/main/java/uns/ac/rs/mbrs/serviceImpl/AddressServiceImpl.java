package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.repository.AddressRepository;
import uns.ac.rs.mbrs.service.AddressService;

import java.util.Date;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	
	@Override
	public Address findOne(Long id) {
		return addressRepository.findById(id).get();
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	@Override
	public Address remove(Long id) {
		Address address = addressRepository.findById(id).get();
		if(address == null){
			throw new IllegalArgumentException("Tried to delete :( Address");
		}
		addressRepository.delete(address);
		return address;
	}
	
}