package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardVentDao;
import com.org.makgol.boards.repository.BoardVentRepository;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;


@Service
@RequiredArgsConstructor
public class BoardVentService {

    private final FileUpload fileUpload;

    private final BoardVentDao boardDao;
    private final BoardVentRepository boardVentRepository;
    // 페이징 처리하기
    public List<BoardDetailResponseVo> boardVentAll(int pNum, int scale) {
        return boardDao.boardVentAll(pNum, scale);
    }

    public int boardVentAll() {
        return boardDao.boardVentAll();
    }

    /**
     * vent 글 쓰기 폼 제출
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
                boardVentRepository.insertVentBoard(boardCreateRequestVo);
                int board_id = boardCreateRequestVo.getId();
                Map<String, Object> map = new HashMap<>();
                map.put("id", board_id);
                map.put("fileList", fileList);
                boardVentRepository.insertVentBoardImages(map);
                return 1;
            } catch (Exception e) {
                throw e;
            }
        } else {
            return boardVentRepository.insertVentBoard(boardCreateRequestVo);
        }
    }


    /**
     * vent 글 상세보기
     **/
    public BoardDetailResponseVo readVentBoard(int id) {
        System.out.println("서비스id = " + id);
        List<BoardDetailResponseVo> boardVos = null;
        BoardDetailResponseVo boardVo = null;
        boardVos = boardVentRepository.showDetailImageBoard(id);
        if(boardVos.size() == 0){
            boardVos = boardVentRepository.showDetailBoard(id);
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
     * Vent 조회수
     **/
    public int addHit(int id) {
        int result = -1;
        result = boardVentRepository.updateHit(id);
        return result;    }

    /**
     * Vent 댓글 INSERT
     **/
    public int addComment(CommentRequestVo commentRequestVo) {
        int result = -1;
        result = boardVentRepository.insertComment(commentRequestVo);
        return result;
    }

    /**
     * Vent 댓글 SELECT
     **/
    public List<CommentResponseVo> getCommentList(int board_id) {
        List<CommentResponseVo> commentResponseVos = null;
        commentResponseVos = boardVentRepository.selectCommentList(board_id);
        return commentResponseVos.size() > 0 ? commentResponseVos : null;
    }

    /**
     * Vent 댓글 수정 폼 제출
     **/
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        int result = -1;
        result = boardVentRepository.updateComment(commentResponseVo);
        return result;
    }

    /**
     * Vent 댓글 DELETE
     **/
    public int delComment(int id) {
        int result = -1;
        result = boardVentRepository.deleteComment(id);
        return result;
    }

    /**
     * Vent 글 수정하러가기 버튼
     **/
    public BoardVo modifyBoard(int b_id, String name) {
        List<BoardVo> boardVos = null;
        BoardVo boardVo = null;
        List<String> images = new ArrayList<String>();
        boardVos = boardVentRepository.selectImageBoard(b_id);
        if (boardVos.size() == 0) {
            boardVos = boardVentRepository.selectBoard(b_id);
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
     * Vent 글 수정 폼 제출
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
            existingFileInfo = boardVentRepository.selectExistingFile(existingFile);
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
                boardVentRepository.updateBoard(boardCreateRequestVo);
                boardVentRepository.deleteBoardImage(board_id);
                Map<String, Object> map = new HashMap<>();
                map.put("id", board_id);
                map.put("fileList", fileList);
                boardVentRepository.insertVentBoardImages(map);
                result = 1;
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                int board_id = boardCreateRequestVo.getId();
                boardVentRepository.updateBoard(boardCreateRequestVo);
                boardVentRepository.deleteBoardImage(board_id);
                result = 1;
            }catch (Exception e){
                throw e;
            }
        }

        // 쿼리문 실행이 성공했다면
        if (result > 0) {
            if(oldImages.length() > 0){
                FileUpload.modifyFileList(oldImages,existingFileInfo);
            }
        }
        return result;
    }

    /**
     * Vent 글 DELETE
     **/
    public int deleteBoard(int b_id, String images) {
        int result = -1;
        result = boardVentRepository.deleteBoard(b_id);
        if (result > 0) {
            if(images.length() >0) {
                FileUpload.deleteFileList(images);
            }
        }
        return result;
    }


    /**
     * Vent 글 검색
     **/
    public List<BoardDetailResponseVo> searchBoard(String searchOption, String searchWord) {
        List<BoardDetailResponseVo> boardVos = null;
        Map<String, String> map = new HashMap<>();
        map.put("searchOption", searchOption);
        map.put("searchWord", searchWord);
        boardVos = boardVentRepository.selectSearchBoard(map);
        return boardVos.size() > 0 ? boardVos : null;
    }

    public int userLikeStatus(BoardVo boardVo) {
        int status = boardVentRepository.selectUserLikeStatus(boardVo);
        return status;
    }

    public int addLikeBoard(BoardVo boardVo) {
        int result = -1;
        result = boardVentRepository.insertBoardLike(boardVo);
        return result;
    }

    public int removeLikeBoard(BoardVo boardVo) {
        int result = -1;
        result = boardVentRepository.deleteBoardLike(boardVo);
        return result;
    }

    public int countLike(int b_id) {
        int totalLike = boardVentRepository.selectLikeCount(b_id);
        return totalLike;
    }

    public void addBoardSympathy(Map<String, Integer> map) {
        boardVentRepository.updateBoardSympathy(map);
    }

    public int deleteMyBoard(String ids){
        String[] id = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        int result = -1;

        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        imageList = boardVentRepository.selectBoardImages(idList);
        result = boardVentRepository.deleteHistoryBoard(idList);
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
        result = boardVentRepository.deleteHistoryComment(idList);
        return result;
    }

    public int deleteMyLike(Map<String, String> data){
        String boardids = data.get("boardids");
        String likeids = data.get("likeids");
        String[] id = likeids.split(",");
        String[] boardid = boardids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<Integer> boardidList = new ArrayList<>();
        int result = -1;
        for(String item : id) {
            idList.add(Integer.parseInt(item));
        }
        result = boardVentRepository.deleteHistoryLike(idList);
        if(result>0){
            for(String item : boardid) {
                boardidList.add(Integer.parseInt(item));
            }
            boardVentRepository.deleteLikes(boardidList);
        }
        return result;
    }


}