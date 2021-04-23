package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Admin.TABLEADMIN)

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEADMIN)
    @SequenceGenerator(name = TABLEADMIN, sequenceName = "t_admin_seq")
    public static final String TABLEADMIN = "t_admin";

    private Integer id;
    private String loginAcct;
    private String userPswd;
    private String userName;

    public Admin() {
    }
    public Admin(Integer id, String loginAcct, String userPswd, String userName) {
        super();
        this.id = id;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", loginAcct=" + loginAcct + ", userPswd=" + userPswd + ", userName=" + userName
                + "]";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLoginAcct() {
        return loginAcct;
    }
    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct == null ? null : loginAcct.trim();
    }
    public String getUserPswd() {
        return userPswd;
    }
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd == null ? null : userPswd.trim();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}
