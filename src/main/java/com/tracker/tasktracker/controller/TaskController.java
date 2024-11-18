package com.tracker.tasktracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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

import com.tracker.tasktracker.constants.TaskTrackerConstants;
import com.tracker.tasktracker.dto.TaskDTO;
import com.tracker.tasktracker.entity.TaskEntity;
import com.tracker.tasktracker.response.ApiResponse;
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
	public ResponseEntity<ApiResponse<TaskDTO>> createTask(@Valid @RequestBody TaskDTO taskDTO) {
	    TaskEntity createdTask = taskService.createTask(taskDTO);
	    TaskDTO responseDTO = TaskMapper.toDTO(createdTask);
	    ApiResponse<TaskDTO> response = new ApiResponse<>(TaskTrackerConstants.CREATED_SUCCESSFULLY, responseDTO);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/fetch")
	public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks() {
	    // Log the request to fetch all tasks
	    log.info("Received request to fetch all tasks");

	    // Fetch tasks from the service layer and map entities to DTOs
	    List<TaskDTO> tasks = taskService.getAllTasks()
	                                     .stream()
	                                     .map(TaskMapper::toDTO)
	                                     .collect(Collectors.toList());

	    // Log the total number of tasks fetched
	    log.info("Total tasks fetched: {}", tasks.size());

	    // Build a standardized API response
	    ApiResponse<List<TaskDTO>> response = new ApiResponse<>(TaskTrackerConstants.FETCH_SUCCESS, tasks);

	    // Return ResponseEntity with HTTP status 200 (OK) explicitly
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TaskDTO>> getTaskById(@PathVariable Long id) {
	    // Log the request to fetch a task by ID
	    log.info("Fetching task with ID: {}", id);

	    // Fetch the task from the service layer
	    TaskEntity task = taskService.getTaskById(id);

	    // Map the TaskEntity to TaskDTO
	    TaskDTO taskDTO = TaskMapper.toDTO(task);

	    // Build a standardized API response
	    ApiResponse<TaskDTO> response = new ApiResponse<>(TaskTrackerConstants.FETCH_SUCCESS, taskDTO);

	    // Return ResponseEntity with HTTP status 200 (OK) and the response body
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TaskDTO>> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
	    // Log the request to update the task by ID
	    log.info("Received request to update task with ID: {}", id);

	    // Update the task through the service layer
	    TaskEntity updatedTask = taskService.updateTask(id, taskDTO);

	    // Log the successful update
	    log.info("Task with ID {} updated successfully", id);

	    // Map the updated TaskEntity to TaskDTO
	    TaskDTO updatedTaskDTO = TaskMapper.toDTO(updatedTask);

	    // Build a standardized API response
	    ApiResponse<TaskDTO> response = new ApiResponse<>(TaskTrackerConstants.UPDATE_SUCCESS, updatedTaskDTO);

	    // Return ResponseEntity with HTTP status 200 (OK) and the response body
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	    // Log the request to delete the task by ID
	    log.info("Received request to delete task with ID: {}", id);

	    // Delete the task through the service layer
	    taskService.deleteTask(id);

	    // Log the successful deletion
	    log.info("Task with ID {} deleted successfully", id);

	    // Return ResponseEntity with HTTP status 204 (No Content)
	    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}



	@PatchMapping("/{id}/status")
	public ResponseEntity<ApiResponse<TaskDTO>> changeTaskStatus(@PathVariable Long id, @RequestParam String status) {
	    log.info("Changing status of task with ID: {} to {}", id, status);

	    // Change the task status through the service layer
	    TaskEntity updatedTask = taskService.changeTaskStatus(id, status);

	    // Map the updated task entity to DTO
	    TaskDTO updatedTaskDTO = TaskMapper.toDTO(updatedTask);

	    // Build a standardized API response with a success message and updated task DTO
	    ApiResponse<TaskDTO> response = new ApiResponse<>(TaskTrackerConstants.STATUS_UPDATED_SUCCESSFULLY, updatedTaskDTO);

	    // Return ResponseEntity with HTTP status 200 (OK) and the response body
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<TaskDTO>>> searchTasksByTitle(@RequestParam String title) {
	    log.info("Searching for tasks with title containing: {}", title);

	    // Search tasks by title through the service layer
	    List<TaskEntity> tasks = taskService.searchTasksByTitle(title);

	    // Map the list of task entities to a list of task DTOs
	    List<TaskDTO> taskDTOs = tasks.stream()
	                                  .map(TaskMapper::toDTO)
	                                  .collect(Collectors.toList());

	    // Build a standardized API response
	    ApiResponse<List<TaskDTO>> response = new ApiResponse<>(TaskTrackerConstants.SEARCH_SUCCESS, taskDTOs);

	    // Return ResponseEntity with HTTP status 200 (OK) and the response body
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


}
