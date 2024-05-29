package projeto.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.todo.entities.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Boolean existsByTitle(String title);
}
