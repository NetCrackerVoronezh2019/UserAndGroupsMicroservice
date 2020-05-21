package ru.dto;

import ru.domen.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private String image;

    public static UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.userId = user.getUserId();
        userDTO.birthday = user.getBirthday();
        userDTO.email = user.getEmail();
        userDTO.firstName = user.getFirstName();
        userDTO.middleName = user.getMiddleName();
        userDTO.lastName = user.getLastName();
        userDTO.image = user.getImage();
        return userDTO;
    }

    public static List<UserDTO> getUserDTO(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user :
                users) {
            userDTOS.add(UserDTO.getUserDTO(user));
        }
        return userDTOS;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
