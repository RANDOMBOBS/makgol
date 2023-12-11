package com.org.makgol.util.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@PropertySource("classpath:/application.properties")
public class FileUpload {

    @Value("${file.path.matcher}")
    private String filePathMatcher;

    public List<FileInfo> fileListUpload(List<MultipartFile> fileList) {
        List<FileInfo> fileInfoList = new ArrayList<>();

        for(int index=0; index < fileList.size(); index++){
            FileInfo fileInfo = new FileInfo();

            String fileRealName = fileList.get(index).getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
            long size = fileList.get(index).getSize(); //파일 사이즈

            System.out.println("파일명 : "  + fileRealName);
            System.out.println("용량크기(byte) : " + size);
            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());

            String currentDirectory = System.getProperty("user.dir");
            System.out.println("현재 디렉토리: " + currentDirectory);

            String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image";

		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());
            String[] uuids = uuid.toString().split("-");

            String uniqueName = uuids[0];
            System.out.println("생성된 고유문자열" + uniqueName);
            System.out.println("확장자명" + fileExtension);


            // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

            File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후

            try {
                fileList.get(index).transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
                fileInfo.setPhotoPath(filePathMatcher+uniqueName+fileExtension);
                fileInfo.setPhotoName(fileRealName);
                fileInfoList.add(fileInfo);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileInfoList;
    }

    public FileInfo fileUpload(MultipartFile file) {

        FileInfo fileInfo = new FileInfo();

            String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!

            long size = file.getSize(); //파일 사이즈

            System.out.println("파일명 : "  + fileRealName);
            System.out.println("용량크기(byte) : " + size);
            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());

            String currentDirectory = System.getProperty("user.dir");
            System.out.println("현재 디렉토리: " + currentDirectory);

            //mac 경로
            //String uploadFolder = currentDirectory+"/src/main/resources/static/image";
            //windows 경로
            String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image";

		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());
            String[] uuids = uuid.toString().split("-");

            String uniqueName = uuids[0];
            System.out.println("생성된 고유문자열" + uniqueName);
            System.out.println("확장자명" + fileExtension);



            // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

            //windows
            //File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
            //mac
            File saveFile = new File(uploadFolder+"/"+uniqueName + fileExtension);  // 적용 후

            try {
                file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
                fileInfo.setPhotoPath(filePathMatcher+uniqueName+fileExtension);
                fileInfo.setPhotoName(uniqueName + fileExtension);

                byte[] fileData = file.getBytes();  // 파일의 바이트 데이터를 얻음
                // 여기에서 fileData를 사용하여 필요한 처리 수행

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println(filePathMatcher+uniqueName+fileExtension);
        return fileInfo;

    }

    public List<FileInfo> editFileListUpload(List<MultipartFile> fileList) {
        List<FileInfo> fileInfoList = new ArrayList<>();

        for(int index=0; index < fileList.size(); index++){
            FileInfo fileInfo = new FileInfo();
            if(fileList.get(index) != null){
                String fileRealName = fileList.get(index).getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
                long size = fileList.get(index).getSize(); //파일 사이즈

                System.out.println("파일명 : "  + fileRealName);
                System.out.println("용량크기(byte) : " + size);
                //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
                String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());

                String currentDirectory = System.getProperty("user.dir");
                System.out.println("현재 디렉토리: " + currentDirectory);

                String uploadFolder = currentDirectory+"\\src\\main\\resources\\static\\image";

		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

                UUID uuid = UUID.randomUUID();
                System.out.println(uuid.toString());
                String[] uuids = uuid.toString().split("-");

                String uniqueName = uuids[0];
                System.out.println("생성된 고유문자열" + uniqueName);
                System.out.println("확장자명" + fileExtension);


                // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

                File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후

                try {
                    fileList.get(index).transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
                    fileInfo.setPhotoPath(filePathMatcher+uniqueName+fileExtension);
                    fileInfo.setPhotoName(fileRealName);
                    fileInfoList.add(fileInfo);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                fileInfoList.add(null);
            }

        }
        return fileInfoList;
    }


    public static void deleteFileList(String oldImages) {
        System.out.println("oldImages = " + oldImages);
        String currentDirectory = System.getProperty("user.dir"); // 현재 이미지 경로 찾기
        String[] oldImageList = null; // 삭제될 이미지들을 받을 배열
        List<String> oldImageNames = new ArrayList<String>();   // 삭제된 이미지들의 이름을 받을 배열
        oldImages = oldImages.substring(1, oldImages.length() - 1); // [ 123, 456, 789 ] -----> 123, 456, 789
        oldImages = oldImages.replace(" ","");  // 공백 제거 123,456,789
        oldImageList = oldImages.split(","); // 삭제될 이미지들을 ,기준으로 잘라서 넣음 [123, 456, 789]

        for (int i = 0; i < oldImageList.length; i++) {
            String oldImageName = oldImageList[i].substring(oldImageList[i].lastIndexOf("/") + 1);
            oldImageNames.add(oldImageName);
        }
        // oldImageNames배열의 값들에 하나씩 접근해서 파일을 삭제함
        for (int i = 0; i < oldImageNames.size(); i++) {
            String image = oldImageNames.get(i);
            String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + image;
            File file = new File(deleteFile);
            file.delete();
        }

    }

    public static void modifyFileList(String oldImages,List<FileInfo> existingFileInfo){
        String currentDirectory = System.getProperty("user.dir"); // 현재 이미지 경로 찾기
        String[] oldImageList = null; // 삭제될 이미지들을 받을 배열
        List<String> oldImageNames = new ArrayList<String>();   // 삭제된 이미지들의 이름을 받을 배열
        oldImages = oldImages.substring(1, oldImages.length() - 1); // [ 123, 456, 789 ] -----> 123, 456, 789
        oldImages = oldImages.replace(" ","");  // 공백 제거 123,456,789
        oldImageList = oldImages.split(","); // 삭제될 이미지들을 ,기준으로 잘라서 넣음 [123, 456, 789]

        // 삭제될 이미지들을 하나씩 순서대로 접근해서 수정후에도 남는 사진과 일치하다면 그 자리를 공백으로 바꿈 [123,,789]
        for(int i=0; i<existingFileInfo.size(); i++){
            for (int j=0; j<oldImageList.length; j++){
                if(oldImageList[j].equals(existingFileInfo.get(i).getPhotoPath())){
                    oldImageList[j]= "";
                }
            }
        }
        // 이미지 이름 찾아내서 oldImageNames배열에 담음 (/fileUpload/01b8c34c.jpg ----> 01b8c34c.jpg)
        for (int i = 0; i < oldImageList.length; i++) {
            String oldImageName = oldImageList[i].substring(oldImageList[i].lastIndexOf("/") + 1);
            oldImageNames.add(oldImageName);
        }
        // oldImageNames배열의 값들에 하나씩 접근해서 파일을 삭제함
        for (int i = 0; i < oldImageNames.size(); i++) {
            String image = oldImageNames.get(i);
            String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + image;
            File file = new File(deleteFile);
            file.delete();
        }

    }

}
