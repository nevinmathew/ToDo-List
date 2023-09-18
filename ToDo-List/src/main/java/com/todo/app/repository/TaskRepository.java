package com.todo.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todo.app.entity.Tasks;
import com.todo.app.repository.projections.ToDoProjection;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer>{
	
	@Query(value=" select t.id as taskId," + 
			"t.task_name as taskName, " + 
			"t.`description` as `description`, " + 
			"t.created_timestamp as createdTimestamp, " + 
			"t.updated_timestamp as updatedTimestamp, " + 
			"t.target_timestamp as targetTimestamp, " + 
			"t.`priority` as `priority`, " + 
			"t.`status` as `status`, " +
			"c.id as categoryId, " + 
			"c.`name` as categoryName " + 
			"from tasks t " + 
			"inner join category c on " + 
			"c.id = t.category_id ", nativeQuery=true)
	List<ToDoProjection> findAllTasks();
	
	@Query(value=" select t.id as taskId," + 
			"t.task_name as taskName, " + 
			"t.`description` as `description`, " + 
			"t.created_timestamp as createdTimestamp, " + 
			"t.updated_timestamp as updatedTimestamp, " + 
			"t.target_timestamp as targetTimestamp, " + 
			"t.`priority` as `priority`, " + 
			"t.`status` as `status`, " +
			"c.id as categoryId, " + 
			"c.`name` as categoryName " + 
			"from tasks t " + 
			"inner join category c on " + 
			"c.id = t.category_id and " + 
			" t.id = :id ", nativeQuery=true)
	Optional<ToDoProjection> findTodoById(@Param("id") int id);
	
}
