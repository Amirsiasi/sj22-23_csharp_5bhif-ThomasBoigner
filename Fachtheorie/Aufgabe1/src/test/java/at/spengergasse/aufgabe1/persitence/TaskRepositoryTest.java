package at.spengergasse.aufgabe1.persitence;

import at.spengergasse.aufgabe1.domain.Task;
import at.spengergasse.aufgabe1.persistence.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void AddTaskSuccessTest(){
        //given
        Task task = Task.builder()
                .text("cool Task")
                .dateFrom(LocalDate.of(2021, 8, 26))
                .build();
        //when
        taskRepository.save(task);
        Task savedTask = taskRepository.getReferenceById(task.getId());

        //then
        assertThat(task).isEqualTo(savedTask);
    }
}
