package com.example.Running_club.service;

import com.example.Running_club.model.RunningEvent;
import com.example.Running_club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventService {
  RunningEventRepository runningEventRepository;

  @Autowired
  public RunningEventService(RunningEventRepository runningEventRepository) {
    this.runningEventRepository = runningEventRepository;
  }

  public RunningEvent saveRunningEvent(RunningEvent runningEvent) {
    return runningEventRepository.save(runningEvent);
  }

  public Optional<RunningEvent> findRunningEventById(long id) {
    return runningEventRepository.findById(id);
  }

  public void deleteRunningEventById(long id) {
    runningEventRepository.deleteById(id);
  }

  public List<RunningEvent> getAllRunningEvents() {
    return runningEventRepository.findAll();
  }
}
