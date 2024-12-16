package inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import inicio.entities.Contact;
import inicio.service.IContactService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAll() {
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Contact> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(contactService.getContactByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOne(@RequestBody Contact contact) {
        try {
            contactService.addContact(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
