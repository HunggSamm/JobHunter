package vn.hoidanit.jobhunter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.hoidanit.jobhunter.util.SecurityUtil;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

import java.time.Instant;
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
    private int age;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String address;
    private String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    private Instant createAt;
    private Instant updateAt;
    private String createBy;
    private String updateBy;
    // hibernate sẽ tự động gán giá trị cho createAt
    @PrePersist
    public void handleBeforeCreate() {
        // lấy người tạo từ SecurityContextHolder
        this.createBy = SecurityUtil.getCurrentUserLogin().isPresent()==true?
                SecurityUtil.getCurrentUserLogin().get()
                :"";
        this.createAt = Instant.now();
    }
    @PreUpdate
    public void handleBeforeUpdate() {
        // lấy người cập nhật từ SecurityContextHolder
        this.updateBy = SecurityUtil.getCurrentUserLogin().isPresent()==true?
                SecurityUtil.getCurrentUserLogin().get()
                :"";
        this.updateAt = Instant.now();
    }


}
