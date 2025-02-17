package com.example.Running_club.repository;

import com.example.Running_club.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

  public List<Registration> getAllRegistrationsByRunningEventId(long id);

}
