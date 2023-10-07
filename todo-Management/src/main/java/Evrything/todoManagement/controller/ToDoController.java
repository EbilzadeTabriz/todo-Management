package Evrything.todoManagement.controller;

import Evrything.todoManagement.dto.ToDoDto;
import Evrything.todoManagement.manager.ToDoManager;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/toDo")
public class ToDoController {
    private final ToDoManager manager;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDo = manager.addToDo(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<ToDoDto> getToDo(@PathVariable("id") Long id) {
        ToDoDto toDoDto = manager.getToDo(id);
        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }
     @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAll() {
        List<ToDoDto> toDoDtoList = manager.getAll();
        return new ResponseEntity<>(toDoDtoList, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}/update")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @PathVariable("id") Long id) {

        ToDoDto updateToDoDto = manager.updateToDo(toDoDto, id);
        return ResponseEntity.ok(updateToDoDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable("id") Long id) {
        manager.deleteToDo(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);


    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable("id") Long id) {
        ToDoDto toDoDto = manager.completeToDo(id);
        return ResponseEntity.ok(toDoDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteAllToDo() {
        manager.deleteAllToDo();
        return ResponseEntity.ok("Deleted all info successfully");

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable("id") Long id) {
        ToDoDto updateToDoDto = manager.inCompleteToDo(id);
        return  ResponseEntity.ok(updateToDoDto);


    }


}
