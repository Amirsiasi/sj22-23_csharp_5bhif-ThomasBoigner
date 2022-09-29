package at.spengergasse.aufgabe1.persitence;

import at.spengergasse.aufgabe1.domain.Department;
import at.spengergasse.aufgabe1.domain.Task;
import at.spengergasse.aufgabe1.persistence.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void AddDepartmentSuccessTest(){
        //given
        Department department = Department.builder()
                .name("Hohere Informatik")
                .tasks(List.of(Task.builder()
                        .text("cool Task")
                        .dateFrom(LocalDate.of(2021, 8, 26))
                        .build()))
                .build();

        //when
        departmentRepository.save(department);
        Department savedDepartment = departmentRepository.getReferenceById(department.getId());

        //then
        assertThat(department).isEqualTo(savedDepartment);
    }
}
