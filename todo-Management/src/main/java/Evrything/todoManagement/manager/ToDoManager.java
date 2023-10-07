package Evrything.todoManagement.manager;

import Evrything.todoManagement.dto.ToDoDto;
import Evrything.todoManagement.entity.ToDo;
import Evrything.todoManagement.exception.ResourceNotFounException;
import Evrything.todoManagement.repository.ToDoRepository;
import Evrything.todoManagement.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoManager implements ToDoService {
    private final ToDoRepository repository;
    private final ModelMapper mapper;


    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
        ToDo toDo = mapper.map(toDoDto,ToDo.class);
        ToDo savedToDo = repository.save(toDo);

        ToDoDto savedTODoDto = mapper.map(savedToDo,ToDoDto.class);
             return savedTODoDto;

    }

    @Override
    public ToDoDto getToDo(Long id) {
       ToDo toDo = repository.findById(id).get();
       return mapper.map(toDo,ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAll() {
        List<ToDo> toDoList = repository.findAll();
        return toDoList.stream().map((tooDo)->mapper.map(tooDo,ToDoDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
     ToDo toDo = repository.findById(id).orElseThrow(()-> new ResourceNotFounException("Todo not found with id: "  +id));
     toDo.setDescription(toDoDto.getDescription());
     toDo.setId(toDoDto.getId());
     toDo.setCompleted(toDoDto.getCompleted());
     toDo.setTitle(toDo.getTitle());
     ToDo updated = repository.save(toDo);
     return mapper.map(updated,ToDoDto.class);

    }

    @Override
    public void deleteToDo(Long id) {
      ToDo toDo =  repository.findById(id).orElseThrow(()->new ResourceNotFounException("ToDo not found with: " +id) );
      repository.deleteById(id);

    }

    @Override
    public ToDoDto completeToDo(Long id) {
        ToDo toDo = repository.findById(id).orElseThrow(()-> new ResourceNotFounException("todo not found with id: " + id));
         toDo.setCompleted(Boolean.TRUE);
         ToDo updated = repository.save(toDo);
         return mapper.map(updated,ToDoDto.class);
      }

    @Override
    public void deleteAllToDo() {
        repository.deleteAll();

    }

    @Override
    public ToDoDto inCompleteToDo(Long id) {
        ToDo toDo = repository.findById(id).orElseThrow(()-> new ResourceNotFounException("todo not found with id: " + id));
        toDo.setCompleted(Boolean.FALSE);
        ToDo savedComplete = repository.save(toDo);

        return mapper.map(savedComplete,ToDoDto.class) ;
    }
}
