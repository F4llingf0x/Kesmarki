package hu.kesmarki.people.service;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Contact;
import hu.kesmarki.people.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    private AddressRepository addressRepository;
    private ContactService contactService;

    public AddressService(AddressRepository addressRepository, ContactService contactService) {
        this.addressRepository = addressRepository;
        this.contactService = contactService;
    }

    public Address addAddress(Address address) {
        return addressRepository.addAddress(address);
    }

    public Address modifyAddress(Address address) {
        return addressRepository.modifyAddress(address);
    }

    public Address findAddressById(int x) {
        Optional<Address> address = addressRepository.findAddressById(x);
        if (address.isPresent() && !address.get().isDeleted()) {
            return address.get();
        } else {
            System.out.println("Address not found");
            return null;
        }
    }

    public List<Address> findNotAssignedAddresses() {
        return addressRepository.findNotAssignedAddresses();
    }

    public Address deleteAddress(Address addressToDelete, boolean deleteCascade) {
        addressToDelete.setDeleted(true);
        for (Contact contact : addressToDelete.getContact()) {
            if (deleteCascade) {
                contactService.deleteContact(contact);
            } else {
                contact.setAddress(null);
                contactService.modifyContact(contact);
            }
        }

        return modifyAddress(addressToDelete);

    }
}
