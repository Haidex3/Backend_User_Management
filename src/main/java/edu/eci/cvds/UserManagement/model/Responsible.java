package edu.eci.cvds.UserManagement.model;

public class Responsible {
    private final String name;
    private final String phoneNumber;
    private final String email;
    //private List<Student> students;
    private final String address;
    private final Long document;
    private final String typeDocument;


    public Responsible(Long document, String typeDocument, String name, String phoneNumber, String email,  String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.document = document;
        this.typeDocument = typeDocument;
    }

    //public String getDocument() {
        //return document;
    //}

    public String getTypeDocument() {
        return typeDocument;
    }

    public Long getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

}
