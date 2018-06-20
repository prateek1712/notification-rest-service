package me.prateek.notificationservice.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM Client c where c.title = ?0 AND t.description = ?1", nativeQuery=true)
    List<String> getClients();

}
