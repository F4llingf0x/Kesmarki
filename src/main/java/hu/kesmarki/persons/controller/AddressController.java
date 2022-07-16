package hu.kesmarki.persons.controller;

import hu.kesmarki.persons.domain.Address;
import hu.kesmarki.persons.domain.Person;
import hu.kesmarki.persons.service.AddressService;
import org.springframework.stereotype.Component;

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

        dataRequest("county");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getCounty());
        }
        System.out.println();
        address.setCounty(askTextFromUser());

        dataRequest("postal code");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getPostalCode());
        }
        System.out.println();
        address.setPostalCode(askTextFromUser());

        dataRequest("street name");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getStreetName());
        }
        System.out.println();
        address.setStreetName(askTextFromUser());

        dataRequest("house number");
        if (addressIsPresent) {
            System.out.print(PREVIOUS_TEXT + address.getHouseNumber());
        }
        System.out.println();
        address.setHouseNumber(askTextFromUser());

        if (!addressIsPresent) {
            System.out.println(addressService.addAddress(address));
        } else {
            System.out.println(addressService.modifyAddress(address));
        }
    }

    private void dataRequest(String dataType) {
        System.out.println("Please enter the " + dataType + " of the address");
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
}
