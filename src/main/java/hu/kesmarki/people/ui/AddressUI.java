package hu.kesmarki.people.ui;

import hu.kesmarki.people.controller.AddressController;
import hu.kesmarki.people.controller.CommonCommands;
import hu.kesmarki.people.domain.Address;
import hu.kesmarki.people.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddressUI extends CommonCommands {

    private Scanner scanner = new Scanner(System.in);
    private AddressController addressController;
    private PersonUI personUI;

    private static final String NEW_LINE = "\r\n";
    private static final String TAB = "\t";

    public static final String ADDRESS_MENU_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Add address" + NEW_LINE +
            TAB + "2. Modify address" + NEW_LINE +
            TAB + "3. Assign to or change person" + NEW_LINE +
            TAB + "4. Find address by id" + NEW_LINE +
            TAB + "5. List all addresses of person" + NEW_LINE +
            TAB + "6. List all not assigned addresses" + NEW_LINE +
            TAB + "7. Delete address" + NEW_LINE +
            TAB + "8. Back";

    public static final String FIND_ADDRESS_MESSAGE = "Please choose from the following options:" + NEW_LINE +
            TAB + "1. Find address by address id" + NEW_LINE +
            TAB + "2. Find address by person" + NEW_LINE +
            TAB + "3. Back";

    public AddressUI(AddressController addressController, PersonUI personUI) {
        this.addressController = addressController;
        this.personUI = personUI;
    }


    public boolean addressMenu(int value) {
        boolean isTerminated = false;
        Address foundAddress = null;
        switch (value) {
            case 1:
                addressController.addressBuilder(null);
                break;

            case 2:
                foundAddress = findAddress("find");
                if (foundAddress != null) {
                    addressController.addressBuilder(foundAddress);
                }
                break;

            case 3:
                foundAddress = findAddressById("assign");
                if (foundAddress == null) {
                    break;
                }
                //TODO hiba utan nem all vissza, vagy nem lép ki azonnal
                Person foundPerson = personUI.findPerson("assign");
                foundAddress.setPerson(foundPerson);
                addressController.modifyAddress(foundAddress);
                break;

            case 4:
                foundAddress = findAddressById("find");
                if (foundAddress != null) {
                    System.out.println(foundAddress);
                }
                break;

            case 5:
                List<Address> addresses = personUI.findPerson("find").getAddress();
                System.out.println(addresses.size() + " address has been found: ");
                System.out.println(addresses);
                break;

            case 6:
                List<Address> addressList = addressController.findNotAssignedAddresses();
                System.out.println(addressList.size() + " address has been found: ");
                System.out.println(addressList);
                break;

            case 7:
                Address addressToDelete = findAddress("delete");
                boolean deleteCascade = false;
                if (addressToDelete != null) {
                    System.out.println();
                    System.out.println("Would you like to delete the corresponding contacts? (Y/N)");
                    String text = null;
                    while (text == null) {
                        text = askYNFromUser();
                        if (text != null && text.equalsIgnoreCase("y")) {
                            deleteCascade = true;
                        }
                    }
                    Address deletedAddress = addressController.deleteAddress(addressToDelete, deleteCascade);
                    System.out.println();
                    System.out.print("Address has been deleted");
                    if (deleteCascade){
                        System.out.println(" with contacts");
                    }

                }
                break;

            case 8:
                isTerminated = true;
                break;
            default:
                System.out.println();
                System.out.print("Invalid option");
        }
        return isTerminated;
    }

    public Address findAddress(String purpose) {
        Address foundAddress = null;
        System.out.println(FIND_ADDRESS_MESSAGE);
        int x = askIntFromUser();
        switch (x) {
            case 2:
                Person foundPerson = personUI.findPerson("find");
                if (foundPerson == null) {
                    break;
                }
                List<Address> addressList = foundPerson.getAddress();
                System.out.println(addressList);
                if (addressList.size() == 1) {
                    foundAddress = addressList.get(0);
                    break;
                }
            case 1:
                foundAddress = findAddressById(purpose);
                break;
            case 3:
                break;
            default:
                System.out.println();
        }

        return foundAddress;
    }


    public Address findAddressById(String purpose) {
        System.out.println("Please enter the address' id to " + purpose);
        int x = askIntFromUser();
        Address foundAddress = addressController.findAddressById(x);
        return foundAddress;
    }


}
