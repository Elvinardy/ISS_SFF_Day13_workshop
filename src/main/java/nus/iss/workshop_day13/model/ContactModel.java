package nus.iss.workshop_day13.model;

import java.io.Serializable;
import java.util.Random;

public class ContactModel implements Serializable {
    
    private String name;
    private String email;
    private int phoneNumber;
    private String id;

    public ContactModel() {
        this.id = this.generateContactID(8);
    }

    public ContactModel(String name, String email, int phoneNurmber) {
        this.id = this.generateContactID(8);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String generateContactID(int numChars) {
        Random random = new Random();  // calling the random method in java
        StringBuffer sb = new StringBuffer();  // calling the StringBuffer method
        while(sb.length() < numChars) {     //  ensure the id length does not exceed the required length of 8 characters
            //  this will loop and add in new values as long it does not exceed the numChars value which is 8.
            //  use StringBuffer with .append to add new values to the sequence 
            sb.append(Integer.toHexString(random.nextInt()));  // .append adds a new value to the current sequence
        }

        return sb.toString().substring(0, numChars); // converts the stringbuffer value to string
            // .substring indicates the beginning index and end index, the end index has to end with numChars = 8 for this case
    }

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
