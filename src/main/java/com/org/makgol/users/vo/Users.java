package com.org.makgol.users.vo;

import com.org.makgol.common.oauth2.entity.SocialAuth;
import com.org.makgol.common.oauth2.entity.type.AuthProvider;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    // ID
    int id;

    // 이름
    String name;

    // 이메일
    String email;

    // 비밀번호
    String password;

    // 전화번호
    String phone;

    //파일 이름
    String photo;

    // 프로필사진경로
    String photo_path;

    //경도
    double longitude;
    //위도
    double latitude;

    String grade;

    String date;

    String address;

    private SocialAuth socialAuth;

    public void updateSocialAuth(String name, String imageUrl, Map<String, Object> attributes) {
        if (this.socialAuth == null) {
            this.socialAuth = new SocialAuth();
        }
        this.socialAuth.update(name, imageUrl, attributes);
    }
}
