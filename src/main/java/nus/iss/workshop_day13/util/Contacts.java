package nus.iss.workshop_day13.util;

import java.io.Serializable;
import java.util.Random;

public class Contacts implements Serializable {
    
    private String name;
    private String email;
    private int phoneNumber;
    private String id;

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public String generateContactID() {
        Random random = new Random();
        int val = random.nextInt();
        String Hexadecimal = Integer.toHexString(val);

        return Hexadecimal;
    }
}
