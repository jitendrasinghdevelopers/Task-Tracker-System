package com.tracker.tasktracker.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tracker.tasktracker.dto.TaskDTO;
import com.tracker.tasktracker.entity.TaskEntity;
import com.tracker.tasktracker.exception.TaskNotFoundException;
import com.tracker.tasktracker.repository.TaskRepository;
import com.tracker.tasktracker.service.TaskService;
import com.tracker.tasktracker.util.TaskMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TaskTrackerServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	public TaskEntity getTaskById(Long id) {
		log.debug("Fetching task with ID: {}", id);

		return taskRepository.findById(id).orElseThrow(() -> {
			log.error("Task with ID {} not found", id);
			return new TaskNotFoundException("Task with ID " + id + " not found");
		});
	}

	@Override
	public TaskEntity createTask(TaskDTO taskDTO) {
		log.debug("Creating task: {}", taskDTO);
		TaskEntity task = TaskMapper.toEntity(taskDTO);
		TaskEntity savedTask = taskRepository.save(task);
		log.info("Task created with ID: {}", savedTask.getId());
		return savedTask;
	}

	@Override
	public List<TaskEntity> getAllTasks() {
		List<TaskEntity> listOfTasks = taskRepository.findAll();
		log.debug("Fetching all tasks {}", listOfTasks.size());
		return listOfTasks;
	}

	@Override
	public TaskEntity updateTask(Long id, TaskDTO taskDTO) {
		log.debug("Updating task with ID: {}", id);
		TaskEntity task = getTaskById(id);
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(taskDTO.getStatus());
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		log.debug("Deleting task with ID: {}", id);

		TaskEntity task = getTaskById(id);
		taskRepository.delete(task);
	}

	@Override
	public TaskEntity changeTaskStatus(Long id, String status) {
		log.debug("Changing status of task with ID: {} to {}", id, status);

		TaskEntity task = getTaskById(id);
		task.setStatus(status);
		return taskRepository.save(task);
	}

	@Override
	public List<TaskEntity> searchTasksByTitle(String title) {
		log.debug("Searching tasks with title containing: {}", title);

		List<TaskEntity> tasks = taskRepository.findByTitleContainingIgnoreCase(title);

		if (tasks.isEmpty()) {
			log.error("No tasks found with title containing: {}", title);
			throw new TaskNotFoundException("No tasks found with title containing: " + title);
		}

		return tasks;
	}

}
