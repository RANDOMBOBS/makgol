package com.org.makgol.boards.vo;

import com.org.makgol.util.file.FileInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
@Data
public class BoardCreateRequestVo implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    int user_id;
    String title;
    String date;
    String contents;
    String category;
    MultipartFile file1;
    MultipartFile file2;
    MultipartFile file3;
    MultipartFile file4;
    MultipartFile file5;
    String oldImage1;
    String oldImage2;
    String oldImage3;
    String oldImage4;
    String oldImage5;
    List<FileInfo> fileList;
    String photo;
    String photo_path;
}
