package inicio.service.dto.mapper;

import inicio.persistence.entities.Contact;
import inicio.service.dto.out.ContactDTO;

import java.util.List;

public class ContactMapper {

    public static ContactDTO toDTO(Contact entity) {
        if (entity == null) return null;

        return new ContactDTO(entity.getFirstName() + " " + entity.getLastName(), entity.getEmail(), entity.getPhoneNumber());
    }

    public static List<ContactDTO> toDTOList(List<Contact> entities) {
        if (entities == null) return null;

        return entities.stream().map(ContactMapper::toDTO).toList();
    }

    public static Contact toEntity(ContactDTO entity) {
        if (entity == null) return null;

        Contact contact = new Contact();
        contact.setFirstName(entity.fullName().split(" ")[0]);
        contact.setLastName(entity.fullName().split(" ")[1]);
        contact.setPhoneNumber(entity.phoneNumber());

        return contact;
    }

}
