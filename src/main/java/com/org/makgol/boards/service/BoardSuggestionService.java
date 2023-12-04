package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardSuggestionDao;
import com.org.makgol.boards.repository.BoardSuggestionRepository;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardSuggestionService {

    private final FileUpload fileUpload;
    private final BoardSuggestionDao boardDao;
    private BoardSuggestionDao boardService;
    private final BoardSuggestionRepository boardSuggestionRepository;


    /**
     * suggestion 게시판 가져오기
     **/
    public List<BoardVo> getSuggestionBoard() {
        List<BoardVo> boardVos = null;
        boardVos = boardSuggestionRepository.selectAllSuggestionBoard();
        return boardVos.size() > 0 ? boardVos : null;
    }

    /**
     * suggestion 글 쓰기 폼 제출
     **/
    public int createBoardConfirm(BoardCreateRequestVo boardCreateRequestVo) {
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        int result = -1;

        if (boardCreateRequestVo.getFile1() != null && !boardCreateRequestVo.getFile1().isEmpty()) {
            files.add(boardCreateRequestVo.getFile1());
        }
        if (boardCreateRequestVo.getFile2() != null && !boardCreateRequestVo.getFile2().isEmpty()) {
            files.add(boardCreateRequestVo.getFile2());
        }
        if (boardCreateRequestVo.getFile3() != null && !boardCreateRequestVo.getFile3().isEmpty()) {
            files.add(boardCreateRequestVo.getFile3());
        }
        if (boardCreateRequestVo.getFile4() != null && !boardCreateRequestVo.getFile4().isEmpty()) {
            files.add(boardCreateRequestVo.getFile4());
        }
        if (boardCreateRequestVo.getFile5() != null && !boardCreateRequestVo.getFile5().isEmpty()) {
            files.add(boardCreateRequestVo.getFile5());
        }

        // if(파일이 1개이상일때), else(파일이 0개일때)
        if (files.size() > 0) {
            try {
                List<FileInfo> fileList = fileUpload.fileListUpload(files);
                int boardImageListResult = -1;
                boardSuggestionRepository.insertSuggestionBoard(boardCreateRequestVo);
                int board_id = boardCreateRequestVo.getId();
                Map<String, Object> map = new HashMap<>();
                map.put("id", board_id);
                map.put("fileList", fileList);
                boardSuggestionRepository.insertSuggestionBoardImages(map);
                return 1;
            } catch (Exception e) {
                throw e;
            }
        } else {
            return boardSuggestionRepository.insertSuggestionBoard(boardCreateRequestVo);
        }
    }


    /**
     * suggestion 글 상세보기
     **/
    public BoardDetailResponseVo readSuggestionBoard(int id) {
        List<BoardDetailResponseVo> boardVos = null;
        BoardDetailResponseVo boardVo = null;
        boardVos = boardSuggestionRepository.showDetailImageBoard(id);
        if(boardVos.size() == 0){
            boardVos = boardSuggestionRepository.showDetailBoard(id);
            boardVo = boardVos.get(0);
        } else {
            List<String> images = new ArrayList<String>();
            if (boardVos.get(0).getBoard_photo_path() != null) {
                for (int i = 0; i < boardVos.size(); i++) {
                    images.add(boardVos.get(i).getBoard_photo_path());
                }
            }
            boardVo = boardVos.get(0);
            boardVo.setImages(images);
        }
        return boardVo;
    }

    /**
     * suggestion 조회수
     **/
    public int addHit(int id) {
        int result = -1;
        result = boardSuggestionRepository.updateHit(id);
        return result;    }

    /**
     * suggestion 댓글 INSERT
     **/
    public int addComment(CommentRequestVo commentRequestVo) {
        int result = -1;
        result = boardSuggestionRepository.insertComment(commentRequestVo);
        return result;
    }

    /**
     * suggestion 댓글 SELECT
     **/
    public List<CommentResponseVo> getCommentList(int board_id) {
        List<CommentResponseVo> commentResponseVos = null;
        commentResponseVos = boardSuggestionRepository.selectCommentList(board_id);
        return commentResponseVos.size() > 0 ? commentResponseVos : null;
    }

    /**
     * suggestion 댓글 수정 폼 제출
     **/
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        int result = -1;
        result = boardSuggestionRepository.updateComment(commentResponseVo);
        return result;
    }

    /**
     * suggestion 댓글 DELETE
     **/
    public int delComment(int id) {
        int result = -1;
        result = boardSuggestionRepository.deleteComment(id);
        return result;
    }

    /**
     * suggestion 글 수정하러가기 버튼
     **/
    public BoardVo modifyBoard(int b_id, String name) {
        List<BoardVo> boardVos = null;
        BoardVo boardVo = null;
        List<String> images = new ArrayList<String>();
        boardVos = boardSuggestionRepository.selectImageBoard(b_id);
        if (boardVos.size() == 0) {
            boardVos = boardSuggestionRepository.selectBoard(b_id);
            boardVo = boardVos.get(0);
        } else{
            for (int i = 0; i < boardVos.size(); i++) {
                images.add(boardVos.get(i).getPhoto_path());
            }
            boardVo = boardVos.get(0);
            boardVo.setImages(images);
            boardVo.setName(name);
        }
        return boardVo;
    }



    /**
     * suggestion 글 수정 폼 제출
     **/
    @Transactional(rollbackFor = Exception.class)
    public int modifyBoardConfirm(BoardCreateRequestVo boardCreateRequestVo, String oldImages) {
        List<MultipartFile> files = new ArrayList<MultipartFile>(); // 새로 추가된 파일을 담을 변수
        List<String> existingFile = new ArrayList<String>();    // 기존에 있던 파일을 담을 변수
        List<FileInfo> existingFileInfo = new ArrayList<FileInfo>(); // 기존의 파일 정보를 담을 변수
        int result = -1;


        // 기존 파일이 있다면 배열에 추가함
        if (boardCreateRequestVo.getOldImage1() != null && !boardCreateRequestVo.getOldImage1().isEmpty()) {
            existingFile.add(boardCreateRequestVo.getOldImage1());
        }
        if (boardCreateRequestVo.getOldImage2() != null && !boardCreateRequestVo.getOldImage2().isEmpty()) {
            existingFile.add(boardCreateRequestVo.getOldImage2());
        }
        if (boardCreateRequestVo.getOldImage3() != null && !boardCreateRequestVo.getOldImage3().isEmpty()) {
            existingFile.add(boardCreateRequestVo.getOldImage3());
        }
        if (boardCreateRequestVo.getOldImage4() != null && !boardCreateRequestVo.getOldImage4().isEmpty()) {
            existingFile.add(boardCreateRequestVo.getOldImage4());
        }
        if (boardCreateRequestVo.getOldImage5() != null && !boardCreateRequestVo.getOldImage5().isEmpty()) {
            existingFile.add(boardCreateRequestVo.getOldImage5());
        }


        // 새로운 이미지가 있다면 파일을, 없다면 null값을 배열에 추가함
        if (boardCreateRequestVo.getFile1() != null && !boardCreateRequestVo.getFile1().isEmpty()) {
            files.add(boardCreateRequestVo.getFile1());
        } else {
            files.add(null);
        }
        if (boardCreateRequestVo.getFile2() != null && !boardCreateRequestVo.getFile2().isEmpty()) {
            files.add(boardCreateRequestVo.getFile2());
        } else {
            files.add(null);
        }
        if (boardCreateRequestVo.getFile3() != null && !boardCreateRequestVo.getFile3().isEmpty()) {
            files.add(boardCreateRequestVo.getFile3());
        } else {
            files.add(null);
        }
        if (boardCreateRequestVo.getFile4() != null && !boardCreateRequestVo.getFile4().isEmpty()) {
            files.add(boardCreateRequestVo.getFile4());
        }else {
            files.add(null);
        }
        if (boardCreateRequestVo.getFile5() != null && !boardCreateRequestVo.getFile5().isEmpty()) {
            files.add(boardCreateRequestVo.getFile5());
        } files.add(null);


        // 만약 기존 파일이 있다면 파일정보를 가져오기
        if(existingFile.size() >0 ) {
            existingFileInfo = boardSuggestionRepository.selectExistingFile(existingFile);
        }

        // 새로운 파일의 정보를 가져오기
        List<FileInfo> fileList = fileUpload.editFileListUpload(files);

        // fileList(새로운파일정보배열) 0번부터 접근하여 null값이라면 existingFileInfo(기존파일정보배열) i번값을 넣기
        // [ null, 새파일1, null, 새파일2, null ] ------> [ 기존파일1, 새파일1, null, 새파일2, null ]
        for(int i=0 ; i<existingFileInfo.size(); i++){
            for(int j=0; j<fileList.size(); j++){
                if(fileList.get(j) == null){
                    fileList.set(j, existingFileInfo.get(i));
                    break;
                }
            }
        }

        // fileList 역순으로 접근하여 null값이라면 그 값 삭제
        // [ 기존파일1, 새파일1, 기존파일2, 새파일2, null ] ------> [ 기존파일1, 새파일1, 새파일2 ]
        for (int i = fileList.size()-1; i >= 0; i--) {
            if (fileList.get(i) == null) {
                fileList.remove(i);
            }
        }

        // if(파일이 있을때), else(파일이 없을때) DB UPDATE
        if (fileList.size() > 0) {
            try {
                int board_id = boardCreateRequestVo.getId();
                boardSuggestionRepository.updateBoard(boardCreateRequestVo);
                boardSuggestionRepository.deleteBoardImage(board_id);
                Map<String, Object> map = new HashMap<>();
                map.put("id", board_id);
                map.put("fileList", fileList);
                boardSuggestionRepository.insertSuggestionBoardImages(map);
                result = 1;
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                int board_id = boardCreateRequestVo.getId();
                boardSuggestionRepository.updateBoard(boardCreateRequestVo);
                boardSuggestionRepository.deleteBoardImage(board_id);
                result = 1;
            }catch (Exception e){
                throw e;
            }
        }

        // 쿼리문 실행이 성공했다면
        if (result > 0) {
            if(oldImages.length() > 0){
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
        return result;
    }

    /**
     * suggestion 글 DELETE
     **/
    public int deleteBoard(int b_id, String images) {
        int result = -1;
        result = boardSuggestionRepository.deleteBoard(b_id);
        if (result > 0) {
            if(images.length() >0) {
                FileUpload.deleteFileList(images);
            }
        }
        return result;
    }


    /**
     * suggestion 글 검색
     **/
    public List<BoardVo> searchBoard(String searchOption, String searchWord) {
        List<BoardVo> boardVos = null;
        Map<String, String> map = new HashMap<>();
        map.put("searchOption", searchOption);
        map.put("searchWord", searchWord);
        boardVos = boardSuggestionRepository.selectSearchBoard(map);
        return boardVos.size() > 0 ? boardVos : null;
    }

    public int userLikeStatus(BoardVo boardVo) {
        int status = boardSuggestionRepository.selectUserLikeStatus(boardVo);
        return status;
    }

    public int addLikeBoard(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.insertBoardLike(boardVo);
        return result;
    }

    public int removeLikeBoard(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.deleteBoardLike(boardVo);
        return result;
    }

    public int countLike(int b_id) {
        int totalLike = boardSuggestionRepository.selectLikeCount(b_id);
        return totalLike;
    }

    public void addBoardSympathy(Map<String, Integer> map) {
        boardSuggestionRepository.updateBoardSympathy(map);
    }

    public int deleteMyBoard(String ids){
        String[] id = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        int result = -1;

        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        imageList = boardSuggestionRepository.selectBoardImages(idList);
        result = boardSuggestionRepository.deleteHistoryBoard(idList);
        if(result>0){
         FileUpload.deleteFileList(imageList.toString());
        }
        return result;
    }


    public int deleteMyComment(String comids){
        String id[] = comids.split(",");
        List<Integer> idList = new ArrayList<>();
        int result = -1;
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        result = boardSuggestionRepository.deleteHistoryComment(idList);
        return result;
    }

    public int deleteMyLike(String likeids, String boardids){
        String[] id = likeids.split(",");
        String[] boardid = boardids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<Integer> boardidList = new ArrayList<>();
        int result = -1;
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        result = boardSuggestionRepository.deleteHistoryLike(idList);
        if(result>0){
            for(String item : boardid) {
                boardidList.add(Integer.parseInt(item));
            }
            boardSuggestionRepository.deleteLikes(boardidList);
        }
        return result;
    }

}
