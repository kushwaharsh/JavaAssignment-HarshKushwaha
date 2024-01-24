package net.sunbase.assignment.repository;
import net.sunbase.assignment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findByUuid(String uuid);

    @Query("SELECT c FROM Customer c WHERE " +
            "c.first_name LIKE CONCAT('%', :query, '%') OR " +
            "c.email LIKE CONCAT('%', :query, '%') OR " +
            "c.phone LIKE CONCAT('%', :query, '%') OR " +
            "c.city LIKE CONCAT('%', :query, '%')")
    List<Customer> searchCustomer(@Param("query") String query);



}
