package com.todo.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;

/**
 * The `Tasks` class represents a task entity in the application.
 */
@Data
@Entity
@Table(indexes = @Index(name = "idx_task_category", columnList = "id"))
public class Tasks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4258414978174620302L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private final String taskName;
	
	@Column
	private String description;
	
	@Column
	private final LocalDateTime createdTimestamp;

	@Column
	private LocalDateTime updatedTimestamp;
	
	@Column
	private LocalDateTime targetTimestamp;
	
	@Column
	private String priority; 
	
	@Column
	private String status; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;

	public Tasks(int id, String name, String description, LocalDateTime createdTime, LocalDateTime updatedTime, LocalDateTime targetTime,
			String priority, String status, Category category) {
		super();
		this.id = id;
		this.taskName = name;
		this.description = description;
		this.createdTimestamp = createdTime;
		this.updatedTimestamp = updatedTime;
		this.targetTimestamp = targetTime;
		this.priority = priority;
		this.status = status;
		this.category = category;
	}
	
	public Tasks(String taskName, LocalDateTime createdTimestamp) {
		this.taskName = taskName;
		this.createdTimestamp = createdTimestamp;
	}
	
	//copy constructor
	public Tasks(Tasks original, String newTaskName, LocalDateTime newCreatedTimestamp) {
        this(newTaskName, newCreatedTimestamp);
        BeanUtils.copyProperties(original, this);
    }
	
}
