package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardSuggestionDao;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
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

        if(boardCreateRequestVo.getFile1() != null && !boardCreateRequestVo.getFile1().isEmpty()){
            files.add(boardCreateRequestVo.getFile1());
        }
        if(boardCreateRequestVo.getFile2() != null && !boardCreateRequestVo.getFile2().isEmpty()){
            files.add(boardCreateRequestVo.getFile2());
        }
        if(boardCreateRequestVo.getFile3() != null && !boardCreateRequestVo.getFile3().isEmpty()){
            files.add(boardCreateRequestVo.getFile3());
        }
        if(boardCreateRequestVo.getFile4() != null && !boardCreateRequestVo.getFile4().isEmpty()){
            files.add(boardCreateRequestVo.getFile4());
        }
        if(boardCreateRequestVo.getFile5() != null && !boardCreateRequestVo.getFile5().isEmpty()){
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

    /**
     * suggestion 글 상세보기
     **/
    public List<BoardVo> readSuggestionBoard(int id) {
        List<BoardVo> boardVos = boardDao.showDetailSuggestionBoard(id);
        return boardVos;
    }

    /**
     * suggestion 조회수
     **/
    public int addHit(int b_id) {
        return boardDao.updateHit(b_id);
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
     * suggestion 글 수정
     **/
//    public BoardVo modifyBoard(int b_id) {
//        return boardDao.selectBoard(b_id);
//    }

    /**
     * suggestion 글 수정 폼 제출
     **/
//    public int modifyBoardConfirm(BoardVo boardVo, String oldFile) {
//        MultipartFile file = boardVo.getFile();
//        String oldFileName = oldFile.substring(oldFile.lastIndexOf("/")+1, oldFile.length());
//        String currentDirectory = System.getProperty("user.dir");
//        if (!file.isEmpty()) {
//            FileInfo fileInfo = fileUpload.fileUpload(file);
//            boardVo.setAttachment(fileInfo.getPhotoPath());
//        }
//
//        int result = boardDao.updateBoard(boardVo);
//
//        if (result > 0) {
//            String deleteFile = currentDirectory+"\\src\\main\\resources\\static\\image\\"+oldFileName;
//            File oldfile= new File(deleteFile);
//            oldfile.delete();
//        }
//        return result;
//    }

    /**
     * suggestion 글 DELETE
     **/
//    public int deleteBoard(int b_id, String attachment) {
//        String currentDirectory = System.getProperty("user.dir");
//        String attachmentName = attachment.substring(attachment.lastIndexOf("/") + 1, attachment.length());
//        String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + attachmentName;
//        int result = boardDao.deleteBoard(b_id);
//        if (result > 0) {
//            File file = new File(deleteFile);
//            file.delete();
//        }
//        return result;
//    }

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
        String id[] = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        int result =  boardDao.deleteHistoryBoard(idList);
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
