package com.todo.app.repository.projections;

import java.time.LocalDateTime;

/**
 * The `ToDoProjection` interface defines a projection for querying ToDo tasks.
 * Projections are used to select specific fields from entities when querying the database.
 * This interface includes getter methods for retrieving task-related information.
 * By using projections, we can optimize database queries by selecting only the required fields.
 */
public interface ToDoProjection {

	public int getTaskId();

	public String getTaskName();
	
	public String getDescription();

	public LocalDateTime getCreatedTimestamp();

	public LocalDateTime getUpdatedTimestamp();

	public LocalDateTime getTargetTimestamp();

	public String getPriority();
	
	public String getStatus();

	public int getCategoryId();

	public String getCategoryName();
	
}
