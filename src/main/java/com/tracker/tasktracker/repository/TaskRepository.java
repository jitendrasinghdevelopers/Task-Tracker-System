package com.tracker.tasktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.tasktracker.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	List<TaskEntity> findByTitleContainingIgnoreCase(String title);
}
