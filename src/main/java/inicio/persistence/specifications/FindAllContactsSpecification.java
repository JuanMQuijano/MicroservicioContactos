package inicio.persistence.specifications;

import inicio.persistence.entities.Contact;
import inicio.service.dto.in.ContactSearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAllContactsSpecification implements Specification<Contact> {

    private ContactSearchCriteria contactSearchCriteria;

    public FindAllContactsSpecification(ContactSearchCriteria contactSearchCriteria) {
        this.contactSearchCriteria = contactSearchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(contactSearchCriteria.email())) {
            Predicate emailLike = criteriaBuilder.like(root.get("email"), "%" + contactSearchCriteria.email() + "%");
            predicates.add(emailLike);
        }

        if (StringUtils.hasText(contactSearchCriteria.phoneNumber())) {
            Predicate phoneNumberLike = criteriaBuilder.like(root.get("phoneNumber"), "%" + contactSearchCriteria.phoneNumber() + "%");
            predicates.add(phoneNumberLike);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
