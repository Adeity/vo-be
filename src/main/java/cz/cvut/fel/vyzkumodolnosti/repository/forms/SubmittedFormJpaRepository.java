package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedFormJpaRepository<T> extends JpaRepository<T, Integer> {
}
