package inicio.controller;

import inicio.service.dto.in.ContactSearchCriteria;
import inicio.service.dto.out.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import inicio.persistence.entities.Contact;
import inicio.service.IContactService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactDTO>> getAll(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "phone_number", required = false) String phoneNumber) {
        ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria(email, phoneNumber);
        return new ResponseEntity<>(contactService.getAllContacts(contactSearchCriteria), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOne(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{phone_number}")
    public ResponseEntity<ContactDTO> getUserByData(@PathVariable(required = true, value = "phone_number") String phoneNumber) {
        return new ResponseEntity<>(contactService.getContactByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{phone_number}")
    public ResponseEntity<Void> deleteByPhoneNumber(@PathVariable(required = true, value = "phone_number") String phoneNumber) {
        contactService.deleteContactByPhoneNumber(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}
