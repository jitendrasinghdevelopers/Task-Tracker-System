package com.tracker.tasktracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.tasktracker.dto.TaskDTO;
import com.tracker.tasktracker.entity.TaskEntity;
import com.tracker.tasktracker.service.TaskService;
import com.tracker.tasktracker.util.TaskMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tasks")
@Slf4j
@AllArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@PostMapping("/create")
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
		log.info("Request to create task: {}", taskDTO);
		TaskEntity createdTask = taskService.createTask(taskDTO);
		log.info("Task created with ID: {}", createdTask.getId());

		return ResponseEntity.ok(TaskMapper.toDTO(createdTask));
	}

	@GetMapping("/fetch")
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		log.info("Fetching all tasks");

		List<TaskDTO> tasks = taskService.getAllTasks().stream().map(TaskMapper::toDTO).collect(Collectors.toList());
		log.info("Total tasks fetched: {}", tasks.size());

		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
		log.info("Fetching task with ID: {}", id);

		TaskEntity task = taskService.getTaskById(id);
		return ResponseEntity.ok(TaskMapper.toDTO(task));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
		log.info("Updating task with ID: {}", id);

		TaskEntity updatedTask = taskService.updateTask(id, taskDTO);
		log.info("Task with ID {} updated successfully", id);

		return ResponseEntity.ok(TaskMapper.toDTO(updatedTask));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		log.info("Deleting task with ID: {}", id);

		taskService.deleteTask(id);
		log.info("Task with ID {} deleted successfully", id);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<TaskDTO> changeTaskStatus(@PathVariable Long id, @RequestParam String status) {
		log.info("Changing status of task with ID: {} to {}", id, status);

		TaskEntity updatedTask = taskService.changeTaskStatus(id, status);
		return ResponseEntity.ok(TaskMapper.toDTO(updatedTask));
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<TaskEntity>> searchTasksByTitle(@RequestParam String title) {
        List<TaskEntity> tasks = taskService.searchTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

}
