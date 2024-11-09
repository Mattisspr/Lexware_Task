package project.Lexware_Task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.Lexware_Task.model.Iban;

public interface IbanRepository extends JpaRepository<Iban, Long> {
}
