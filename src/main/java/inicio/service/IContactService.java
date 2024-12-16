package inicio.service;

import inicio.entities.Contact;

import java.util.List;

public interface IContactService {

    void addContact(Contact contact);

    Contact getContactByEmail(String email);

    Contact getContactByID(int id);

    void deleteContactByEmail(String email);

    List<Contact> getAllContacts();

    void deleteContactByID(int id);

    void updateContact(int id, Contact contact);
}
