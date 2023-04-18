package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService user = new UserServiceImpl();
        while (true) {
            System.out.println("" +
                    "\n1-> Create Table User: " +
                    "\n2-> Drop Table User: " +
                    "\n3-> Save User: " +
                    "\n4-> Remove User by id: " +
                    "\n5-> Get All Users: " +
                    "\n6-> Clean Users Table: " +
                    "\n7-> Connect Database!!!");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1:
                    user.createUsersTable();
                    break;
                case 2:
                    user.dropUsersTable();
                    break;
                case 3:
                    user.saveUser("Anyschema", "Balaclava", (byte) 19);
                    user.saveUser("Orality", "Digit", (byte) 20);
                    break;
                case 4:
                    user.removeUserById(2L);
                    break;
                case 5:
                    System.out.println(user.getAllUsers());
                    break;
                case 6:
                    user.cleanUsersTable();
                    break;
                case 7:
                    Util.getConnection();
                    break;
                default:
                    System.out.println(num + " No!!!");
            }
        }
    }
}
