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

import lombok.Data;

@Data
@Entity
@Table(indexes = @Index(name = "idx_task_category", columnList = "id"))
public class Tasks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4258414978174620302L;

	public Tasks() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String taskName;
	
	@Column
	private String description;
	
//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column
	private LocalDateTime createdTimestamp;

//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column
	private LocalDateTime updatedTimestamp;
	
//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column
	private LocalDateTime targetTimestamp; //for reminders
	
	@Column
	private String priority; //enum: low, medium, high
	
	@Column
	private String status; //pending, in progress, completed
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
}
