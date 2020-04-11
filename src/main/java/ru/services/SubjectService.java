package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Subject;
import ru.repos.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject getSubjectByTranslateName(String name) {
        return subjectRepository.findByTranslateName(name);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Subject subject) {
        subjectRepository.delete(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAllSubjects();
    }
}
