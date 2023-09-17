package com.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.entity.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer>{

	/*
	  select t.task_name, t.`description`, c.`name`, t.`priority`, t.`status`,
	  t.created_timestamp, t.updated_timestamp, t.target_timestamp from tasks t
	  left join category c on c.id = t.category_id;
	 */
	
}
