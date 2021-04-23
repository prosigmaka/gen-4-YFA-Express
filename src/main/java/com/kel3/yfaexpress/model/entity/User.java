package com.kel3.yfaexpress.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = User.TABLEUSER)
@Data
public class User {
    public static final String TABLEUSER = "t_user";

    @Id
    @GeneratedValue(generator = "sequence_user", strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "username", columnDefinition = "varchar(50)", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(255)", nullable = false)
    private String password;

    @Column(name = "full_name", length = 128, nullable = false)
    private String fullName;

    @Column(name = "telp", length = 14)
    private Integer telp;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    public Integer getUserId() {
        return idUser;
    }
    public void setUsername(String id) {
        this.userName = id;
    }
    public String getUserPswd() {
        return password;
    }

    public void setUserPswd(String userPswd) {
        this.password = userPswd == null ? null : userPswd.trim();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}
