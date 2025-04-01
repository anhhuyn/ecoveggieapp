package vn.iotstar.ecoveggieapp.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    private int user_id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String avatar;
    private Date created_at;
    private Date birthday;
    private String gender; // Thêm trường gender
    
    @Transient
    private Date updated_at;
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public Date getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() { // Getter for gender
        return gender;
    }

    public void setGender(String gender) { // Setter for gender
        this.gender = gender;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
    
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
