package com.bridgelabz.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ImplAddressBookUtil
{
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    String path = "E:/AddressBookInternetCode/Jsonfiles/AddressBookS1.json";                // just provided path of file,it doesnt create a file in this location.
    String statename = "";

    static AddressBookModel model = new AddressBookModel();
    static ArrayList<Person> persons = new ArrayList<Person>();

    String search;
    int indexOfPerson;


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

                        addPerson();                    // done

                        break;

                    default:
                        System.out.println("Invalid option");
                }
            }




