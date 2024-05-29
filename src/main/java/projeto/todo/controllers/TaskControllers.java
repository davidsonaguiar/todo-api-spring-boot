package projeto.todo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.todo.entities.Task;
import projeto.todo.services.TaskServices;
import projeto.todo.dtos.TaskDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskControllers {

    private final TaskServices taskServices;

    public TaskControllers(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping
    public ResponseEntity<List<Task>> ListAllTasks() {
        var tasks = taskServices.listAll();
        if(tasks.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity<List<Task>> createTask(@RequestBody @Valid TaskDto taskDto) {
        var taskAlreadyExists = taskServices.taskTitleExists(taskDto.title());
        if(taskAlreadyExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var tasks = taskServices.create(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Task>> updateTask(@PathVariable UUID id, @RequestBody @Valid TaskDto taskDto) {
        var taskExists = taskServices.getTaskById(id);
        if(taskExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var task = taskExists.get();
        BeanUtils.copyProperties(taskDto, task);
        var tasks = taskServices.update(task);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @PutMapping("/{id}/complete")
    public ResponseEntity<List<Task>> completeTask(@PathVariable("id") UUID id) {
        var optionalTask = taskServices.getTaskById(id);
        if(optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var tasks = taskServices.toggleComplete(optionalTask.get());
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable("id") UUID id) {
        var optionalTask = taskServices.getTaskById(id);
        if(optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var tasks = taskServices.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}
