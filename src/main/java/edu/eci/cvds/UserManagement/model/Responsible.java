package edu.eci.cvds.UserManagement.model;
/**
 * The Responsible class represents a person responsible for a student.
 * This class holds information about the responsible person, such as their name,
 * contact details, and identification information.
 */

public class Responsible {
    private final String name;
    private final String phoneNumber;
    private final String email;
    //private List<Student> students;
    private final String address;
    private final Long document;
    private final String siteDocument;

    /**
     * Constructs a new Responsible object with the specified details.
     *
     * @param document the identification number of the responsible person.
     * @param siteDocument the type of document (e.g., ID, passport) used for identification.
     * @param name the name of the responsible person.
     * @param phoneNumber the phone number of the responsible person.
     * @param email the email address of the responsible person.
     * @param address the residential address of the responsible person.
     */
    public Responsible(Long document, String siteDocument, String name, String phoneNumber, String email,  String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.document = document;
        this.siteDocument = siteDocument;
    }

    //public String getDocument() {
        //return document;
    //}

    /**
     * Gets the type of document used for identification.
     *
     * @return the type of document (e.g., ID, passport).
     */
    public String getSiteDocument() {
        return siteDocument;
    }
    /**
     * Gets the document number of the responsible person.
     *
     * @return the identification number.
     */

    public Long getDocument() {
        return document;
    }

    /**
     * Gets the address of the responsible person.
     *
     * @return the residential address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the name of the responsible person.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number of the responsible person.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the email address of the responsible person.
     *
     * @return the email address.
     */
    public String getEmail() {
        return email;
    }

}
