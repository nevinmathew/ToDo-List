package com.todo.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(indexes = @Index(name = "idx_category_tasks", columnList = "id"))
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -802175483926845356L;

	public Category() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;

    @OneToMany(mappedBy = "category")
    private List<Tasks> task  = new ArrayList<>();
    
}
