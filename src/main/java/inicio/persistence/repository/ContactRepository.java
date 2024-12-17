package inicio.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import inicio.persistence.entities.Contact;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

    Optional<Contact> findByEmail(String email);

    @Query(value = "SELECT * FROM contacts c WHERE c.email = ?1 OR c.phone_number = ?2", nativeQuery = true)
    Optional<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contact c WHERE c.phoneNumber = ?1")
    void deleteByPhoneNumber(String phoneNumber);

}
