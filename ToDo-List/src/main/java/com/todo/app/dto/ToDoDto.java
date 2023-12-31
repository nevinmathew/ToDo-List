package com.todo.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.util.HtmlUtils;

/**
 * The `ToDoDto` class represents a Data Transfer Object (DTO) for ToDo tasks in the application.
 * It is used to transfer task-related information between the client and the server.
 * This class includes fields such as task name, description, timestamps, priority, status, and category information.
 */
public class ToDoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8863390563653358670L;
	
	@NotNull(message = "Task ID must not be null") 
	private int taskId;

	@NotBlank(message = "Task name is required") 
    @Size(min = 1, max = 255, message = "Task name must be between 1 and 255 characters") 
    private String taskName;

	@Size(max = 1000, message = "Description can have a maximum of 1000 characters") 
    private String description;

	@NotNull(message = "Created timestamp must not be null") 
    private LocalDateTime createdTimestamp;

    private LocalDateTime updatedTimestamp;

    @NotNull(message = "Target timestamp must not be null") 
    private LocalDateTime targetTimestamp;

    @Pattern(regexp = "low|medium|high", message = "Priority must be 'low', 'medium', or 'high'") 
    private String priority;

    @Pattern(regexp = "pending|in_progress|completed", message = "Status must be 'pending', 'in progress', or 'completed'") 
    private String status;

    @NotNull(message = "Category ID must not be null") 
    private int categoryId;

    @NotBlank(message = "Category name is required") 
    @Size(min = 1, max = 255, message = "Category name must be between 1 and 255 characters") 
    private String categoryName;

    /**
     * Constructs a new instance of the {@code ToDoDto} class with the provided parameters.
     * This constructor is specifically designed for creating instances for the {@code ToDoProjection} interface.
     */
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
	    this.taskName = HtmlUtils.htmlEscape(taskName); 
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
	    this.description = HtmlUtils.htmlEscape(description); 
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
	    this.priority = HtmlUtils.htmlEscape(priority); 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
	    this.status = HtmlUtils.htmlEscape(status); 
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
	    this.categoryName = HtmlUtils.htmlEscape(categoryName); 
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
	
	public static class ToDoDtoBuilder {
		private int taskId;
	    private String taskName;
	    private String description;
	    private String priority;
	    private String status;
	    private LocalDateTime createdTimestamp;
	    private LocalDateTime updatedTimestamp;
	    private LocalDateTime targetTimestamp;
	    private int categoryId;
	    private String categoryName;

	    public ToDoDtoBuilder(String taskName, String description) {
	        this.taskName = taskName;
	        this.description = description;
	    }

	    public ToDoDtoBuilder withTaskId(int taskId) {
	        this.taskId = taskId;
	        return this;
	    }
	    
	    public ToDoDtoBuilder withPriority(String priority) {
	        this.priority = priority;
	        return this;
	    }

	    public ToDoDtoBuilder withStatus(String status) {
	        this.status = status;
	        return this;
	    }

	    public ToDoDtoBuilder withCreatedTimestamp(LocalDateTime createdTimestamp) {
	        this.createdTimestamp = createdTimestamp;
	        return this;
	    }

	    public ToDoDtoBuilder withUpdatedTimestamp(LocalDateTime updatedTimestamp) {
	        this.updatedTimestamp = updatedTimestamp;
	        return this;
	    }

	    public ToDoDtoBuilder withTargetTimestamp(LocalDateTime targetTimestamp) {
	        this.targetTimestamp = targetTimestamp;
	        return this;
	    }

	    public ToDoDtoBuilder withCategoryId(int categoryId) {
	        this.categoryId = categoryId;
	        return this;
	    }

	    public ToDoDtoBuilder withCategoryName(String categoryName) {
	        this.categoryName = categoryName;
	        return this;
	    }

	    public ToDoDto build() {
	        if (taskName == null || description == null) {
	            throw new IllegalStateException("TaskName and Description are required.");
	        }
	        return new ToDoDto(taskId, taskName, description, createdTimestamp, updatedTimestamp, targetTimestamp, priority, status, categoryId, categoryName);
	    }
	}

}