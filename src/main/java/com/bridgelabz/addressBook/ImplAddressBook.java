import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ImplAddressBook {
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    String path = "E:/AddressBookInternetCode/Jsonfiles/AddressBookS1.json";        // just provided path of file,it doesnt create a file in this location.
    String statename = "";

    static AddressBookModel model = new AddressBookModel();
    static ArrayList<Person> persons = new ArrayList<Person>();

    String search;
    int indexOfPerson;


    public void createNewAddressBook() {
        System.out.println("-----------------------New Address Book-----------------------");
        System.out.println("Enter state name: ");

        statename = scanner.next();
        boolean isFoundState = false;

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getAddressObj().getState().equals(statename)) // get(i) is inbuilt fun of list
            {
                isFoundState = true;
                break;
            }
        }
        if (!isFoundState) {

            System.out.println("->State is added<-");
            boolean close = false;

            while (!close) {
                System.out.println("Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.close");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1:

                        addPerson();            // done

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
        } else {
            System.out.println("State exist please try again");
        }

        System.out.println("-----------------------New Address Book-----------------------");

    }

    public void deletePerson()
    {
        if (counter > 0)
        {
            System.out.println("Enter Persons mobile number you want to delete:");
            Long mobileSearch = scanner.nextLong();

            indexOfPerson = 0;
            boolean isFoundPerson = false;
            System.out.println("Before for loop");

            for (int i = 0; i < persons.size(); i++)
            {
                System.out.println("Inside for loop");

                if (mobileSearch == (persons.get(i).getMobile())) // can we use indexOfPerson instead of i
                {
                    System.out.println("Inside if");
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }

            if (isFoundPerson)
            {
                persons.remove(indexOfPerson);
                counter--;

                System.out.println();
                System.out.println("Delete completed");
            }
            else
            {
                System.out.println("No person found with this number");
            }
        }
        else
        {
            System.out.println("No records to delete");
        }
    }

    public void printByCity()
    {
        String cityName=" ";
        System.out.println("Open Address Book");

        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < persons.size(); i++)
        {
            map.put(persons.get(i).getAddressObj().getCity(), persons.get(i).getAddressObj().getCity());
        }

        System.out.println("city available " + map.keySet());	// this fun gives view(display) of all states already entered by user
        System.out.println("Enter city");

        cityName = scanner.next();
        boolean isFoundState = false;

        for (int i = 0; i < persons.size(); i++)
        {
            if (persons.get(i).getAddressObj().getCity().equals(cityName))
            {
                isFoundState = true;
                break;
            }
        }

        if (isFoundState)
        {
            System.out.println("->City is found<-");

            if (counter > 0)
            {
                System.out.println("Printing all records...");
                ImplAddressBookUtil.PrintPersonDetailsByCity(persons,cityName);

            } else
                System.out.println("There is no record to print...");


        } else
            System.out.println("Please create new city of that name\nelse try new city name");

    }

    public void sortByLastName()
    {
        if (counter > 1)
        {
            System.out.println("Sorting by Last name is selected");

            Collections.sort(persons, (e1, e2) -> e1.getLastname().compareTo(e2.getLastname()));	//check it


            try
            {
                Thread.sleep(2000);			// why to use
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Sorting completed");
        }
        else
        {
            System.out.println("Less records to sort");
        }
    }

}