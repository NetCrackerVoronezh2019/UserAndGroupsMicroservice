package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.domen.Subject;
import ru.dto.SubjectDTO;
import ru.services.SubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("subjects/getAll")
    public List<SubjectDTO> getlAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectDTO> res = new ArrayList<>();
        for (Subject subject :
                subjects) {
            res.add(SubjectDTO.getSubjectDTO(subject));
        }
        return res;
    }
}
