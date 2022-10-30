package at.spengergasse.aufgabe3.persistence;

import at.spengergasse.aufgabe3.domain.Lesson;
import at.spengergasse.aufgabe3.domain.Subject;
import at.spengergasse.aufgabe3.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> getLessonBy_class_NameAndTeacher_LastnameAndSubject_Longname(String className, String teacherLastName, String subjectName);
}
