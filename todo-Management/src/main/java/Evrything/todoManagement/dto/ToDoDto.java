package Evrything.todoManagement.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;

}
