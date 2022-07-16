package hu.kesmarki.people.service;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Address findAddressById(int x) {
        Optional<Address> address = addressRepository.findAddressById(x);
        //TODO exceptionHandling
        //TODO not deleted
        return address.get();
    }

    public List<Address> findNotAssignedAddresses() {
        return addressRepository.findNotAssignedAddresses();
    }

    public Address deleteAddress(Address addressToDelete) {
        addressToDelete.setDeleted(true);
        return modifyAddress(addressToDelete);
    }
}
