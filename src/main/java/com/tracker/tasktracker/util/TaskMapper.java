package com.tracker.tasktracker.util;

import com.tracker.tasktracker.dto.TaskDTO;
import com.tracker.tasktracker.entity.TaskEntity;

public class TaskMapper {

	private TaskMapper() {

	}

	public static TaskEntity toEntity(TaskDTO taskDTO) {
		TaskEntity task = new TaskEntity();
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(taskDTO.getStatus());
		return task;
	}

	public static TaskDTO toDTO(TaskEntity task) {
		return new TaskDTO(task.getTitle(), task.getDescription(), task.getStatus());
	}
}
