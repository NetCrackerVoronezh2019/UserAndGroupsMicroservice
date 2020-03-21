package ru.dto;

import ru.domen.User;

import java.util.Date;

public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;

    public static UserDTO getUserDTO(User user){
        UserDTO newDTO = new UserDTO();
        newDTO.birthday = user.getBirthday();
        newDTO.email = user.getEmail();
        newDTO.firstName = user.getFirstName();
        newDTO.middleName = user.getMiddleName();
        newDTO.lastName = user.getLastName();
        return newDTO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
