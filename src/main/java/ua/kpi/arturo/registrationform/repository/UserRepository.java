package ua.kpi.arturo.registrationform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.arturo.registrationform.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
