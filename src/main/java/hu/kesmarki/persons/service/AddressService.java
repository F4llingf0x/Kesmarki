package hu.kesmarki.persons.service;

import hu.kesmarki.persons.domain.Address;
import hu.kesmarki.persons.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.addAddress(address);
    }

    public Address modifyAddress(Address address) {
        return addressRepository.modifyAddress(address);
    }
}
