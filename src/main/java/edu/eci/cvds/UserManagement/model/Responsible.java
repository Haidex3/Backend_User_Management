package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * The Responsible class represents a person responsible for a student.
 * This class holds information about the responsible person, such as their name,
 * contact details, and identification information.
 */


@Entity
public class Responsible {

    @Id
    private Long document;
    private String sitedocument;
    private String name;
    private String phonenumber;
    private String email;

    protected Responsible() {
    }

    /**
     * Constructs a new Responsible object with the specified details.
     *
     * @param document      the identification number of the responsible person.
     * @param siteDocument  the site of document.
     * @param name          the name of the responsible person.
     * @param phoneNumber   the phone number of the responsible person.
     * @param email         the email address of the responsible person.
     */
    public Responsible(Long document, String siteDocument, String name, String phoneNumber, String email) {
        this.document = document;
        this.sitedocument = siteDocument;
        this.name = name;
        this.phonenumber = phoneNumber;
        this.email = email;
    }

    /**
     * Gets the type of document used for identification.
     *
     * @return the type of document (e.g., ID, passport).
     */
    public String getSiteDocument() {
        return sitedocument;
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
        return phonenumber;
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
