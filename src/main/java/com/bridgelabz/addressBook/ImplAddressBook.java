package com.bridgelabz.addressBook;

//// All funs

// Total 11 funs
// 1) 4 funs of Main class
// 2) 6 funs of operations
// 3) 1 fun of json
// 4) Initialization lines

package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ImplAddressBookUtil implements IAddressBookUtil
{
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    String path = "E:/AddressBookInternetCode/Jsonfiles/AddressBookS1.json";		// just provided path of file,it doesnt create a file in this location.
    String statename = "";

    static AddressBookModel model = new AddressBookModel();
    static ArrayList<Person> persons = new ArrayList<Person>();

    String search;
    int indexOfPerson;


    public static void PrintPersonDetailsByCity(ArrayList<Person> persons, String cityName)
    {
        String str = "";
        str = str + "Person detail\n";

        System.out.println(str);

        persons.forEach(i ->
        {
            if (!cityName.isEmpty() && cityName.equals(i.getAddressObj().getCity()))
            {
                System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
                        + " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
                        + i.getAddressObj().getZip() + " " + i.getMobile());
            }
        });
    }


    public static void PrintPersonDetails(ArrayList<Person> persons, String statename)
    {
        String str = "";
        str = str + "Person detail\n";

        System.out.println(str);

        persons.forEach(i ->
        {
            if (!statename.isEmpty() && statename.equals(i.getAddressObj().getState()))
            {
                System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
                        + " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
                        + i.getAddressObj().getZip() + " " + i.getMobile());
            }
        });
    }


    @Override
    public void readJson()
    {
        // checking whether it is empty or not
        File file = new File(path);				// doesnt create a file,just we are providing file name.

        if (file.exists() && file.length() != 0)	// checking file exists or not on given location
        {
            try
            {
                model = (AddressBookModel) JsonUtil.readMapper(path, model);	// model object gives access to list persons present in AddressBookModel class
            } catch (IOException e) {
                e.printStackTrace();
            }
            persons.addAll(model.getPersons());		// putting whole in persons object of ArrayList
            counter = persons.size();				// data is already present previously,so taking that data & storing it in persons object.
        }
    }


    @Override
    public void createNewAddressBook()
    {
        System.out.println("-----------------------New Address Book-----------------------");
        System.out.println("Enter state name: ");

        statename = scanner.next();
        boolean isFoundState = false;

        for (int i = 0; i < persons.size(); i++)
        {
            if (persons.get(i).getAddressObj().getState().equals(statename)) // get(i) is inbuilt fun of list
            {
                isFoundState = true;
                break;
            }
        }
        if (!isFoundState)
        {

            System.out.println("->State is added<-");
            boolean close = false;

            while (!close)
            {
                System.out.println("Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.close");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1:

                        addPerson();			// done

                        break;
                    case 2:

                        editPerson();
                        break;
                    case 3:

                        deletePerson();
                        break;
                    case 4:


                        sortByLastName();

                        break;
                    case 5:


                        sortByZip();

                        break;
                    case 6:


                        if (counter > 0) {
                            System.out.println("Printing all records...");

                            ImplAddressBookUtil.PrintPersonDetails(persons, statename);

                        } else
                            System.out.println("There is no record to print...");

                        break;
                    case 7:

                        close = true;
                        System.out.println("Closing...");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        }
        else
        {
            System.out.println("State exist please try again");
        }

        System.out.println("-----------------------New Address Book-----------------------");

    }


    @Override
    public void addPerson()
    {
        System.out.println("Add person details...");
        Person person = new Person();

        System.out.println("Enter mobile");
        Long mobile = scanner.nextLong();
        // validating mobile is not taken by anyone

        boolean isMobileTaken = false;

        for (int i = 0; i < persons.size(); i++) 		// persons.size() is 0 initially as no record is stored,
        {												// as 1st time addPerson() is called.Later it will incr
            if (mobile == persons.get(i).getMobile()) 	// like 0,1,2..etc
            {
                isMobileTaken = true;
                break;
            }
        }
        if (isMobileTaken)
        {
            System.out.println("This mobile number is already taken");
        }
        else
        {
            person.setMobile(mobile);

            System.out.println("Enter person first name: ");
            person.setFirstname(scanner.next().toLowerCase());

            System.out.println("Enter person last name: ");
            person.setLastname(scanner.next().toLowerCase());

            System.out.println("Enter address Details: ");
            Address address = new Address();

            System.out.println("Enter city: ");
            address.setCity(scanner.next());

            System.out.println("Enter address: ");
            address.setAddressLocal(scanner.next());

            System.out.println("Enter zip: ");
            address.setZip(scanner.nextInt());

            address.setState(statename);
            person.setAddressObj(address);	// whole data of person means 6 params value are in person object.

            persons.add(person);			// persons is user defined object of ArrayList inbuilt class
            // now all data is in persons object
            System.out.println();
            System.out.println("Person added");
            counter++;
        }
    }

}
