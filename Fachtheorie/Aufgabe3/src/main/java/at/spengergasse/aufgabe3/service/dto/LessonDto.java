package at.spengergasse.aufgabe3.service.dto;

import at.spengergasse.aufgabe3.domain.Lesson;

import java.time.LocalDateTime;

public record LessonDto(SubjectDto subject, ClassDto _class, TeacherDto teacher, String token, LocalDateTime creationTS) {
    public LessonDto(Lesson lesson){
        this(new SubjectDto(lesson.getSubject()), new ClassDto(lesson.get_class()), new TeacherDto(lesson.getTeacher()), lesson.getToken(), lesson.getCreationTS());
    }
}
