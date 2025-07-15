package com.seph_worker.worker.model.CoreSystem;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String newPassword;
    private String confirmPassword;
    private Integer userId;
}
