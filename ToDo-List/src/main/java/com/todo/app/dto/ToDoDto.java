package com.todo.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ToDoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8863390563653358670L;
	
	@JsonProperty
	private int taskId;

	private String taskName;

	private String description;

	private LocalDateTime createdTimestamp;

	private LocalDateTime updatedTimestamp;

	private LocalDateTime targetTimestamp; // for reminders

	private String priority; // enum: low, medium, high

	private String status;
	
	private int categoryId;

	private String categoryName;
	
	public ToDoDto(int taskId, 
			String taskName, 
			String description, 
			LocalDateTime createdTimestamp,
			LocalDateTime updatedTimestamp, 
			LocalDateTime targetTimestamp, 
			String priority, 
			String status,
			int categoryId, 
			String categoryName) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.createdTimestamp = createdTimestamp;
		this.updatedTimestamp = updatedTimestamp;
		this.targetTimestamp = targetTimestamp;
		this.priority = priority;
		this.status = status;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public LocalDateTime getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public LocalDateTime getTargetTimestamp() {
		return targetTimestamp;
	}

	public void setTargetTimestamp(LocalDateTime targetTimestamp) {
		this.targetTimestamp = targetTimestamp;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ToDoDto [taskId=" + taskId 
				+ ", taskName=" + taskName 
				+ ", description=" + description
				+ ", createdTimestamp=" + createdTimestamp 
				+ ", updatedTimestamp=" + updatedTimestamp
				+ ", targetTimestamp=" + targetTimestamp 
				+ ", priority=" + priority 
				+ ", status=" + status
				+ ", categoryId=" + categoryId 
				+ ", categoryName=" + categoryName 
				+ "]";
	}
	
	
	
}
