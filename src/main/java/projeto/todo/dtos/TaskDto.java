package projeto.todo.dtos;

import jakarta.validation.constraints.NotNull;

public record TaskDto(
   @NotNull String title,
   String description
) {}
