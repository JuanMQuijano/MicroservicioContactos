package inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import inicio.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contact c WHERE c.email = ?1")
    void deleteByEmail(String email);

}
