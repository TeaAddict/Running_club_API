package com.example.Running_club.service;

import com.example.Running_club.model.Registration;
import com.example.Running_club.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

  RegistrationRepository registrationRepository;

  @Autowired
  public RegistrationService(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  public Registration saveRegistration(Registration registration) {
    return registrationRepository.save(registration);
  }

  public List<Registration> getAllEventRegistrationsById(long eventId) {
    return registrationRepository.getAllRegistrationsByRunningEventId(eventId);
  }
}
