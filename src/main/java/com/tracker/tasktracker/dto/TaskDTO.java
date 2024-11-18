package com.tracker.tasktracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	@NotBlank(message = "Title is required")
	private String title;

	private String description;

	@NotBlank(message = "Status is required")
	private String status;
}
