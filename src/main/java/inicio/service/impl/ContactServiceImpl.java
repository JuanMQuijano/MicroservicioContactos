package inicio.service.impl;

import inicio.exceptions.DuplicatedEntryException;
import inicio.exceptions.NotFoundException;
import inicio.persistence.specifications.FindAllContactsSpecification;
import inicio.service.IContactService;
import inicio.service.dto.in.ContactSearchCriteria;
import inicio.service.dto.mapper.ContactMapper;
import inicio.service.dto.out.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import inicio.persistence.entities.Contact;
import inicio.persistence.repository.ContactRepository;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void addContact(Contact contact) {
        boolean exists = existsContactByData(contact.getEmail(), contact.getPhoneNumber());
        if (exists) {
            throw new DuplicatedEntryException(contact.getEmail() + " - " + contact.getPhoneNumber(), null);
        }

        contactRepository.save(contact);
    }

    @Override
    public ContactDTO getContactByPhoneNumber(String phoneNumber) {
        return contactRepository.findByEmailOrPhoneNumber(null, phoneNumber).map(ContactMapper::toDTO).orElseThrow(() -> new NotFoundException(phoneNumber, null));
    }

    private boolean existsContactByData(String email, String phoneNumber) {
        //Si hay registro true, false caso contrario
        return contactRepository.findByEmailOrPhoneNumber(email, phoneNumber).isPresent();
    }

    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) {
        this.getContactByPhoneNumber(phoneNumber);
        contactRepository.deleteByPhoneNumber(phoneNumber);
    }

    @Override
    public List<ContactDTO> getAllContacts(ContactSearchCriteria searchCriteria) {
        FindAllContactsSpecification findAllContactsSpecification = new FindAllContactsSpecification(searchCriteria);
        return ContactMapper.toDTOList(contactRepository.findAll(findAllContactsSpecification));
    }

    @Override
    public void updateContact(Long id, Contact contact) {
        Contact oldContact = contactRepository.findById(id).get();

        oldContact.setFirstName(contact.getFirstName());
        oldContact.setLastName(contact.getLastName());
        oldContact.setEmail(contact.getEmail());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        oldContact.setAddress(contact.getAddress());

        contactRepository.save(oldContact);
    }
}
