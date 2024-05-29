package projeto.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import projeto.todo.dtos.TaskDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreateTaskOneArgSuccess() {
		var task = new TaskDto("Estudar CSS", null);
		webClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(2);
	}

	@Test
	void testCreateTaskTwoArgSuccess() {
		var task = new TaskDto("Estudar HTML", "Tags: header, main e footer");
		webClient
			.post()
			.uri("/tasks")
			.bodyValue(task)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1);
	}

	@Test
	void testCreateTaskTitleNullFail() {
		var taskDto = new TaskDto(null, null);
		webClient
			.post()
				.uri("/tasks")
				.bodyValue(taskDto)
				.exchange()
				.expectStatus().isBadRequest();
	}
}