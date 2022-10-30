package at.spengergasse.aufgabe3.service;

import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.service.ClassService;
import at.spengergasse.aufgabe3.service.dto.command.MutateClassCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClassServiceTest {

    @Autowired
    private ClassService classService;

    @Test
    void ensureSaveClassWorksProperly(){
        // given
        MutateClassCommand classCommand = new MutateClassCommand("5BHIF");

        // when
        Class savedClass = classService.createClass(classCommand);

        // then
        assertThat(savedClass.getName()).isEqualTo(classCommand.getName());
    }

    @Test
    void ensureGetClassesWorksProperly(){
        // given
        MutateClassCommand classCommand1 = new MutateClassCommand("5BHIF");
        MutateClassCommand classCommand2 = new MutateClassCommand("5AHIF");
        Class class1 = classService.createClass(classCommand1);
        Class class2 = classService.createClass(classCommand2);

        // when
        List<Class> classes = classService.getClasses();

        //then
        assertThat(classes.size()).isEqualTo(2);
        assertThat(classes.get(0).getName()).isEqualTo(class1.getName());
        assertThat(classes.get(1).getName()).isEqualTo(class2.getName());
    }
}
