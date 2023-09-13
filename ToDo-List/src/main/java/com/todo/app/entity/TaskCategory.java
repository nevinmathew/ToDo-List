//package com.todo.app.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import org.springframework.scheduling.config.Task;
//
//import lombok.Data;
//
//@Data
//@Entity
//public class TaskCategory implements Serializable {
//
//	/**
//	 * not necessary now. Might be needed if we use oauth and user
//	 */
//	private static final long serialVersionUID = 2534237556258569353L;
//
//	public TaskCategory() {}
//
//	@Id
//	private int id;
//	
//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    private Task task;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//}
