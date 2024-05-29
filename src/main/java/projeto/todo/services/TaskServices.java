package projeto.todo.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projeto.todo.entities.Task;
import projeto.todo.repositories.TaskRepository;
import projeto.todo.dtos.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;

    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    public Boolean taskTitleExists(String title) {
        return taskRepository.existsByTitle(title);
    }

    public List<Task> listAll() {
        var sort = Sort.by("completed").descending().and(
                Sort.by("title").descending());
        return taskRepository.findAll(sort);
    }

    public List<Task> create(TaskDto taskDto) {
        var newTask = new Task();
        BeanUtils.copyProperties(taskDto, newTask);
        taskRepository.save(newTask);
        return listAll();
    }

    public List<Task> toggleComplete(Task task) {
        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
        return listAll();
    }

    public List<Task> delete(UUID id) {
        taskRepository.deleteById(id);
        return listAll();
    }

    public List<Task> update(Task task) {
        taskRepository.save(task);
        return listAll();
    }
}
