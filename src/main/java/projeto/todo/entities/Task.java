package projeto.todo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="tasks")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String title;

    private String description;
    private Boolean completed = false;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(String title) {
        this.title = title;
    }
}
