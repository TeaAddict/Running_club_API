package com.example.Running_club.repository;

import com.example.Running_club.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {
}
