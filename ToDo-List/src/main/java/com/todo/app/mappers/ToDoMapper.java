package com.todo.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.todo.app.dto.ToDoDto;
import com.todo.app.entity.Tasks;

@Mapper
public interface ToDoMapper {

    ToDoMapper mapper = Mappers.getMapper(ToDoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId")
    Tasks toEntity(ToDoDto dto);

    ToDoDto toDto(Tasks entity);
}
