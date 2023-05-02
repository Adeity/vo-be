package cz.cvut.fel.vyzkumodolnosti.repository;

import cz.cvut.fel.vyzkumodolnosti.model.entities.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends JpaRepository<Method, Integer> {
    Method findByName(String name);

}
