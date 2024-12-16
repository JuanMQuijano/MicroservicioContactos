package inicio.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import inicio.entities.Contact;
import inicio.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public Contact getContactByID(int id) {
        return contactRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Contact not found with id: " + id, id)
        );
    }

    @Override
    public void deleteContactByEmail(String email) {
        contactRepository.deleteByEmail(email);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteContactByID(int id) {
        contactRepository.deleteById(id);
    }

    @Override
    public void updateContact(int id, Contact contact) {
        Contact oldContact = contactRepository.findById(id).get();

        oldContact.setEmail(contact.getEmail());
        oldContact.setAge(contact.getAge());
        oldContact.setName(contact.getName());

        contactRepository.save(oldContact);
    }
}
