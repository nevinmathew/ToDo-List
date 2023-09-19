package com.todo.app.repository.projections;

import java.time.LocalDateTime;

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
