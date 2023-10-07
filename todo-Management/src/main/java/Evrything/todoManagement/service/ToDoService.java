package Evrything.todoManagement.service;

import Evrything.todoManagement.dto.ToDoDto;
import Evrything.todoManagement.entity.ToDo;

import java.util.List;

public interface ToDoService {
    ToDoDto addToDo(ToDoDto toDoDto);

    ToDoDto  getToDo(Long id);
    List<ToDoDto> getAll();
    ToDoDto updateToDo(ToDoDto toDoDto,Long id);
    void deleteToDo(Long id);
    ToDoDto completeToDo(Long id);
    void deleteAllToDo();
    ToDoDto inCompleteToDo(Long id);



}
