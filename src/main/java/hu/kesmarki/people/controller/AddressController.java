package hu.kesmarki.people.controller;

import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddressController {

    private static final String PREVIOUS_TEXT = ", previous value: ";

    private Scanner scanner = new Scanner(System.in);
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    public void addressBuilder(Address address) {

        boolean addressIsPresent = (address != null);
        if (!addressIsPresent) {
            address = new Address();
        }

        dataRequest("country");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getCountry());
        }
        System.out.println();
        address.setCountry(askTextFromUser());
        System.out.println();

        dataRequest("county");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getCounty());
        }
        System.out.println();
        address.setCounty(askTextFromUser());
        System.out.println();

        dataRequest("postal code");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getPostalCode());
        }
        System.out.println();
        address.setPostalCode(askTextFromUser());
        System.out.println();

        dataRequest("street name");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getStreetName());
        }
        System.out.println();
        address.setStreetName(askTextFromUser());
        System.out.println();

        dataRequest("house number");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getHouseNumber());
        }
        System.out.println();
        address.setHouseNumber(askTextFromUser());
        System.out.println();

        if (!addressIsPresent) {
            System.out.println(addressService.addAddress(address));
        } else {
            System.out.println(modifyAddress(address));
        }
    }

    private void dataRequest(String dataType) {
        System.out.print("Please enter the " + dataType + " of the address");
    }

    public int askIntFromUser() {
        int userInt = scanner.nextInt();
        scanner.nextLine();
        return userInt;
    }

    public String askTextFromUser() {
        return scanner.nextLine();
    }

    public Address findAddressById(int x) {
        return addressService.findAddressById(x);
    }

    public List<Address> findNotAssignedAddresses() {
        return addressService.findNotAssignedAddresses();
    }

    public Address deleteAddress(Address addressToDelete) {
        return addressService.deleteAddress(addressToDelete);
    }

    public Address modifyAddress(Address addressToModify){
        return addressService.modifyAddress(addressToModify);
    }
}
