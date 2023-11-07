package com.org.makgol.user.vo;

import com.org.makgol.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserVo extends CommonVo {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String grade;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
}
