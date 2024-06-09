package vn.hoidanit.jobhunter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import vn.hoidanit.jobhunter.util.SecurityUtil;

import java.time.Instant;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "name không được để trống")
    private String name;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private String address;
    private String logo;
    // không dùng controller
    // chuẩn ISO 8601 Standard
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
