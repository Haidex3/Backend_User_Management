package edu.eci.cvds.UserManagement.model;
/**
 * The Responsible class represents a person responsible for a student.
 * This class holds information about the responsible person, such as their name,
 * contact details, and identification information.
 */

public class Responsible {
    private final String name;
    private final Long document;
    private final String siteDocument;
    private final String phoneNumber;
    private final String email;



    public Responsible(Long document, String siteDocument, String name, String phoneNumber, String email) {
        this.name = name;
        this.document = document;
        this.siteDocument = siteDocument;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

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
