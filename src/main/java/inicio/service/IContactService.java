package inicio.service;

import inicio.persistence.entities.Contact;
import inicio.service.dto.in.ContactSearchCriteria;
import inicio.service.dto.out.ContactDTO;

import java.util.List;

public interface IContactService {

    void addContact(Contact contact);

    ContactDTO getContactByPhoneNumber(String phoneNumber);

    void deleteContactByPhoneNumber(String phoneNumber);

    List<ContactDTO> getAllContacts(ContactSearchCriteria searchCriteria);

    void updateContact(Long id, Contact contact);
}
