package com.tracker.tasktracker.service;

import java.util.List;

import com.tracker.tasktracker.dto.TaskDTO;
import com.tracker.tasktracker.entity.TaskEntity;

public interface TaskService {

    TaskEntity getTaskById(Long id);

    TaskEntity createTask(TaskDTO taskDTO);

    List<TaskEntity> getAllTasks();

    TaskEntity updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    TaskEntity changeTaskStatus(Long id, String status);

    List<TaskEntity> searchTasksByTitle(String title);
}
