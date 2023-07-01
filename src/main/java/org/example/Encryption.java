package org.example;

public class Encryption {
    //This class is used to encrypt and decrypt the password

    public static String Encrypt(String password) {
        
        char[] c = password.toCharArray();

        for (char d : c) {
            d++;
        }

        password = new String(c);
        
        return password;
    }

    public static String Decrypt(String password) {

        char[] c = password.toCharArray();

        for (char d : c) {
            d--;
        }

        password = new String(c);
        
        return password;
    }

}
