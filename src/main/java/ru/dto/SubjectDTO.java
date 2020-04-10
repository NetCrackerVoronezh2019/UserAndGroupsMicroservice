package ru.dto;

import ru.domen.Subject;

public class SubjectDTO {
    private Long subjectId;
    private String name;
    private String translateName;

    public static SubjectDTO getSubjectDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.name = subject.getName();
        subjectDTO.subjectId = subject.getSubjectId();
        subjectDTO.translateName = subject.getTranslateName();
        return subjectDTO;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }
}
