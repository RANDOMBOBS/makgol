package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardSuggestionDao;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardSuggestionService {

    private final FileUpload fileUpload;

    private final BoardSuggestionDao boardDao;
    private BoardSuggestionDao boardService;

    /**
     * suggestion 게시판 가져오기
     **/
    public List<BoardVo> getSuggestionBoard() {
        return boardDao.selectAllSuggestionBoard();
    }

    /**
     * suggestion 글 쓰기 폼 제출
     **/
    public int createBoardConfirm(BoardCreateRequestVo boardCreateRequestVo) {
        List<MultipartFile> files = new ArrayList<MultipartFile>();

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
            List<FileInfo> fileList = fileUpload.fileListUpload(files);
            return boardDao.insertSuggestionBoard(boardCreateRequestVo, fileList);
        } else {
            return boardDao.insertSuggestionBoard(boardCreateRequestVo);
        }
    }
    이름 : 김치찌개
    파일 : org.mkwajdawohjdoawjdoawjoi @ WKKAWDOawkod

    들어왔나?
    파일 이름 : 공백
    용량 사이즈 : 0

    /**
     * suggestion 글 상세보기
     **/
    public BoardDetailResponseVo readSuggestionBoard(int id) {
        List<BoardDetailResponseVo> boardVos = boardDao.showDetailSuggestionBoard(id);
        List<String> images = new ArrayList<String>();
        if(boardVos.get(0).getBoard_photo_path() != null){
            for (int i = 0; i < boardVos.size(); i++) {
                images.add(boardVos.get(i).getBoard_photo_path());
            }
        }
        BoardDetailResponseVo boardVo = boardVos.get(0);
        boardVo.setImages(images);
        return boardVo;
    }

    /**
     * suggestion 조회수
     **/
    public int addHit(int id) {
        return boardDao.updateHit(id);
    }

    /**
     * suggestion 댓글 INSERT
     **/
    public int addComment(CommentRequestVo commentRequestVo) {

        return boardDao.insertComment(commentRequestVo);
    }

    /**
     * suggestion 댓글 SELECT
     **/
    public List<CommentResponseVo> getCommentList(int board_id) {
        return boardDao.selectCommentList(board_id);
    }

    /**
     * suggestion 댓글 수정 폼 제출
     **/
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        return boardDao.updateComment(commentResponseVo);
    }

    /**
     * suggestion 댓글 DELETE
     **/
    public int delComment(int id) {
        return boardDao.deleteComment(id);
    }

    /**
     * suggestion 글 수정하러가기 버튼
     **/
    public BoardVo modifyBoard(int b_id, String name) {
        List<BoardVo> boardVos = boardDao.selectBoard(b_id);
        List<String> images = new ArrayList<String>();
        if (boardVos.get(0).getPhoto_path() != null){
            for (int i = 0; i < boardVos.size(); i++) {
                images.add(boardVos.get(i).getPhoto_path());
            }
        }
        BoardVo boardVo = boardVos.get(0);
        boardVo.setImages(images);
        boardVo.setName(name);
        return boardVo;
    }

    /**
     * suggestion 글 수정 폼 제출
     **/
    public int modifyBoardConfirm(BoardCreateRequestVo boardCreateRequestVo, String oldImages) {
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

        // if(파일이 있을때), else(파일이 없을때)
        if (files.size() > 0) {
            List<FileInfo> fileList = fileUpload.fileListUpload(files);
            result = boardDao.updateSuggestionBoard(boardCreateRequestVo, fileList);
        } else {
            result = boardDao.updateSuggestionBoard(boardCreateRequestVo);
        }
        if (result > 0) {
            String currentDirectory = System.getProperty("user.dir");
            String[] oldImageList = null;
            List<String> oldImageNames = new ArrayList<String>();
            oldImages = oldImages.substring(1, oldImages.length() - 1);
            oldImageList = oldImages.split(",");
            for (int i = 0; i < oldImageList.length; i++) {
                String oldImage = oldImageList[i];
                String oldImageName = oldImage.substring(oldImage.lastIndexOf("/") + 1, oldImage.length());
                oldImageNames.add(oldImageName);
            }

            for (int i = 0; i < oldImageNames.size(); i++) {
                String image = oldImageNames.get(i);
                String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + image;
                File file = new File(deleteFile);
                file.delete();
            }
        }
        return result;
    }

    /**
     * suggestion 글 DELETE
     **/
    public int deleteBoard(int b_id, String images) {
        int result = boardDao.deleteBoard(b_id);

        if (result > 0) {
            String currentDirectory = System.getProperty("user.dir");
            String[] imageList = null;
            List<String> imageNames = new ArrayList<String>();
            if(images != null) {
                images = images.substring(1, images.length() - 1);
                imageList = images.split(",");
                for (int i = 0; i < imageList.length; i++) {
                    String image = imageList[i];
                    String imageName = image.substring(image.lastIndexOf("/") + 1, image.length());
                    imageNames.add(imageName);
                }
                for (int i = 0; i < imageNames.size(); i++) {
                    String image = imageNames.get(i);
                    String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + image;
                    File file = new File(deleteFile);
                    file.delete();
                }
            }
        }
        return result;
    }

    /**
     * suggestion 글 검색
     **/
    public List<BoardVo> searchBoard(String searchOption, String searchWord) {
        return boardDao.selectSearchBoard(searchOption, searchWord);
    }

    public int userLikeStatus(BoardVo boardVo) {
        return boardDao.selectUserLikeStatus(boardVo);
    }

    public int addLikeBoard(BoardVo boardVo) {
        return boardDao.insertBoardLike(boardVo);
    }

    public int removeLikeBoard(BoardVo boardVo) {
        return boardDao.deleteBoardLike(boardVo);

    }

    public int countLike(int b_id) {
        return boardDao.selectCountLike(b_id);
    }

    public void addBoardSympathy(Map<String, Integer> map) {
        boardDao.updateBoardSympathy(map);
    }

    public int deleteMyBoard(String ids){
        String[] id = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        imageList = boardDao.selectBoardImages(idList);
        int result =  boardDao.deleteHistoryBoard(idList);
        if(result>0){
            String currentDirectory = System.getProperty("user.dir");
            String imageName = null;
            List<String> imageNames = new ArrayList<>();
            for(String image : imageList){
                imageName = image.substring(image.lastIndexOf("/") + 1, image.length());
                imageNames.add(imageName);
            }
            for(int i= 0; i<imageNames.size(); i++) {
                String image = imageNames.get(i);
                String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + image;
                File file = new File(deleteFile);
                file.delete();
            }
        }
        return result;
    }


    public int deleteMyComment(String comids){
        String id[] = comids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        int result =  boardDao.deleteHistoryComment(idList);
        return result;
    }

    public int deleteMyLike(String likeids, String boardids){
        String[] id = likeids.split(",");
        String[] boardid = boardids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<Integer> boardidList = new ArrayList<>();
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        int result =  boardDao.deleteHistoryLike(idList);

        if(result>0){
            for(String item : boardid) {
                boardidList.add(Integer.parseInt(item));
            }
            boardDao.deleteLikes(boardidList);
        }
        return result;
    }

}
