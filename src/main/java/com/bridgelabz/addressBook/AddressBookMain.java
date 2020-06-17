package com.bridgelabz.addressBook;

import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class AddressBookMain
{
    public static void main(String[] args) throws InterruptedException, IOException,JsonMappingException,JsonParseException
    {
        ImplAddressBookUtil util = new ImplAddressBookUtil();
        util.readJson();

        Scanner scanner = new Scanner(System.in);

        boolean isExitAddressBook = false;
        System.out.println("Address book manager\n");

        while (!isExitAddressBook)
        {
            System.out.println("Select menu \n");
            System.out.println("1. New Address Book\n ");

            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1: util.createNewAddressBook();
                    break;

                default: System.out.println("Invalid input");
                    break;
            }
        }
        scanner.close();
    }
}