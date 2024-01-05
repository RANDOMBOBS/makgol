package com.org.makgol.boards.service;

import com.org.makgol.boards.repository.BoardSuggestionRepository;
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
public class BoardSuggestionService {

    private final FileUpload fileUpload;
    private final BoardSuggestionRepository boardSuggestionRepository;


    /**
     * suggestion 게시판의 모든 글을 가져오는 메서드입니다.
     *
     * @return suggestion 게시판의 모든 글을 담은 List 객체를 반환합니다. 글이 없을 경우 null을 반환합니다.
     */
    public List<BoardVo> getSuggestionBoard() {
        List<BoardVo> boardVos = boardSuggestionRepository.selectAllSuggestionBoard();
        return boardVos.size() > 0 ? boardVos : null;
    }

    /**
     * suggestion 게시판에 새로운 글을 등록하는 메서드입니다.
     *
     * @param boardCreateRequestVo 새로 등록할 글의 정보를 담은 BoardCreateRequestVo 객체
     * @return 등록 결과를 나타내는 정수를 반환합니다.
     */
    public int createBoardConfirm(BoardCreateRequestVo boardCreateRequestVo) {
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        int result = -1;

        // 업로드된 파일들을 리스트에 추가
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

        // 파일이 1개 이상일 때
        if (files.size() > 0) {
            try {
                // 파일 업로드 후 정보를 리스트에 담음
                List<FileInfo> fileList = fileUpload.fileListUpload(files);
                int boardImageListResult = -1;

                // 글 등록 후 등록된 글의 ID를 받아와 이미지를 등록
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
        } else {  // 파일이 없을 때
            return boardSuggestionRepository.insertSuggestionBoard(boardCreateRequestVo);
        }
    }

    /**
     * suggestion 게시판에서 글을 상세히 조회하는 메서드입니다.
     *
     * @param id 조회할 글의 식별자
     * @return 조회된 글의 상세 정보를 담은 BoardDetailResponseVo 객체를 반환합니다.
     */
    public BoardDetailResponseVo readSuggestionBoard(int id) {
        List<BoardDetailResponseVo> boardVos = boardSuggestionRepository.showDetailImageBoard(id);
        BoardDetailResponseVo boardVo = null;

        // 이미지 글인 경우
        if (boardVos.size() == 0) {
            boardVos = boardSuggestionRepository.showDetailBoard(id);
            boardVo = boardVos.get(0);
        } else {
            List<String> images = new ArrayList<String>();

            // 이미지 URL을 리스트에 추가
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
     * suggestion 게시판에서 글의 조회수를 증가시키는 메서드입니다.
     *
     * @param id 조회수를 증가시킬 글의 식별자
     * @return 조회수 증가 결과를 나타내는 정수를 반환합니다.
     */
    public int addHit(int id) {
        int result = boardSuggestionRepository.updateHit(id);
        return result;
    }


    /**
     * suggestion 글에 댓글을 추가하는 메서드입니다.
     *
     * @param commentRequestVo 추가할 댓글 정보를 담은 CommentRequestVo 객체
     * @return 댓글 추가 결과를 나타내는 정수를 반환합니다.
     */
    public int addComment(CommentRequestVo commentRequestVo) {
        int result = boardSuggestionRepository.insertComment(commentRequestVo);
        return result;
    }

    /**
     * suggestion 글에 달린 댓글을 조회하는 메서드입니다.
     *
     * @param board_id 조회할 글의 식별자
     * @return 조회된 댓글의 목록을 담은 List<CommentResponseVo> 객체를 반환합니다. 댓글이 없을 경우 null을 반환합니다.
     */
    public List<CommentResponseVo> getCommentList(int board_id) {
        List<CommentResponseVo> commentResponseVos = boardSuggestionRepository.selectCommentList(board_id);
        return commentResponseVos.size() > 0 ? commentResponseVos : null;
    }

    /**
     * suggestion 글에 댓글을 수정하는 메서드입니다.
     *
     * @param commentResponseVo 수정할 댓글 정보를 담은 CommentResponseVo 객체
     * @return 댓글 수정 결과를 나타내는 정수를 반환합니다.
     */
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        int result = boardSuggestionRepository.updateComment(commentResponseVo);
        return result;
    }

    /**
     * suggestion 글에 달린 댓글을 삭제하는 메서드입니다.
     *
     * @param id 삭제할 댓글의 식별자
     * @return 댓글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    public int delComment(int id) {
        int result = boardSuggestionRepository.deleteComment(id);
        return result;
    }

    /**
     * suggestion 글 수정 페이지로 이동하기 위해 해당 글의 정보를 조회하는 메서드입니다.
     *
     * @param b_id   조회할 글의 식별자
     * @param name   수정을 요청한 사용자의 이름
     * @return 수정 페이지에 필요한 글 정보를 담은 BoardVo 객체를 반환합니다.
     */
    public BoardVo modifyBoard(int b_id, String name) {
        List<BoardVo> boardVos = null;
        BoardVo boardVo = null;
        List<String> images = new ArrayList<String>();

        // 이미지 글인 경우
        boardVos = boardSuggestionRepository.selectImageBoard(b_id);
        if (boardVos.size() == 0) {
            // 일반 글인 경우
            boardVos = boardSuggestionRepository.selectBoard(b_id);
            boardVo = boardVos.get(0);
        } else {
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
     * suggestion 글 수정 폼에서 제출된 내용을 처리하는 메서드입니다.
     * 파일 업로드 및 DB 업데이트를 수행합니다.
     *
     * @param boardCreateRequestVo 수정된 글 정보를 담은 BoardCreateRequestVo 객체
     * @param oldImages            수정 전 기존 이미지 파일 경로들을 담은 문자열
     * @return 수정 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     * @throws Exception 파일 업로드 및 DB 업데이트 중 발생한 예외를 던집니다.
     */
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
                FileUpload.modifyFileList(oldImages,existingFileInfo);
                }
            }
        return result;
    }

    /**
     * suggestion 글을 삭제하는 메서드입니다.
     * 게시글 ID와 함께 삭제를 수행하며, 연결된 이미지 파일도 삭제합니다.
     *
     * @param b_id    삭제할 글의 ID
     * @param images  삭제할 글에 연결된 이미지 파일들의 경로를 담은 문자열
     * @return 삭제 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int deleteBoard(int b_id, String images) {
        int result = -1;
        result = boardSuggestionRepository.deleteBoard(b_id);
        // 쿼리문 실행이 성공했을 경우, 연결된 이미지 파일 삭제 수행
        if (result > 0) {
            if (images.length() > 0) {
                FileUpload.deleteFileList(images);
            }
        }
        return result;
    }


    /**
     * suggestion 글을 검색하는 메서드입니다.
     * 검색 옵션과 검색어를 매개변수로 받아 검색을 수행합니다.
     *
     * @param searchOption 검색 옵션
     * @param searchWord   검색어
     * @return 검색 결과를 담은 BoardVo 리스트를 반환합니다. 결과가 없으면 null을 반환합니다.
     */
    public List<BoardVo> searchBoard(String searchOption, String searchWord) {
        List<BoardVo> boardVos = null;
        Map<String, String> map = new HashMap<>();
        map.put("searchOption", searchOption);
        map.put("searchWord", searchWord);
        boardVos = boardSuggestionRepository.selectSearchBoard(map);
        return boardVos.size() > 0 ? boardVos : null;
    }

    /**
     * 사용자가 특정 글에 대한 좋아요 상태를 조회하는 메서드입니다.
     *
     * @param boardVo 좋아요 상태를 조회할 글의 정보를 담은 BoardVo 객체
     * @return 사용자의 좋아요 상태를 나타내는 정수를 반환합니다. 좋아요가 눌려있으면 1, 없으면 0을 반환합니다.
     */
    public int userLikeStatus(BoardVo boardVo) {
        return boardSuggestionRepository.selectUserLikeStatus(boardVo);
    }

    /**
     * suggestion 글에 좋아요를 추가하는 메서드입니다.
     *
     * @param boardVo 좋아요를 추가할 글의 정보를 담은 BoardVo 객체
     * @return 좋아요 추가 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int addLikeBoard(BoardVo boardVo) {
        return boardSuggestionRepository.insertBoardLike(boardVo);
    }

    /**
     * suggestion 글에 좋아요를 취소하는 메서드입니다.
     *
     * @param boardVo 좋아요를 취소할 글의 정보를 담은 BoardVo 객체
     * @return 좋아요 취소 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int removeLikeBoard(BoardVo boardVo) {
        return boardSuggestionRepository.deleteBoardLike(boardVo);
    }

    /**
     * suggestion 글의 전체 좋아요 수를 조회하는 메서드입니다.
     *
     * @param b_id 좋아요 수를 조회할 글의 ID
     * @return 좋아요 수를 나타내는 정수를 반환합니다.
     */
    public int countLike(int b_id) {
        return boardSuggestionRepository.selectLikeCount(b_id);
    }

    /**
     * suggestion 글에 대한 공감(좋아요) 정보를 업데이트하는 메서드입니다.
     *
     * @param map 공감 정보를 담은 Map 객체
     */
    public void addBoardSympathy(Map<String, Integer> map) {
        boardSuggestionRepository.updateBoardSympathy(map);
    }

    /**
     * 사용자가 작성한 suggestion 글들을 삭제하는 메서드입니다.
     * 글 ID 목록을 받아와 해당 글들을 삭제하며, 연결된 이미지 파일도 삭제합니다.
     *
     * @param ids 삭제할 글들의 ID 목록을 쉼표로 구분하여 전달
     * @return 삭제 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int deleteMyBoard(String ids) {
        String[] id = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<String> imageList = new ArrayList<>();
        int result = -1;

        for (String item : id) {
            idList.add(Integer.parseInt(item));
        }
        imageList = boardSuggestionRepository.selectBoardImages(idList);
        result = boardSuggestionRepository.deleteHistoryBoard(idList);
        // 쿼리문 실행이 성공했을 경우, 연결된 이미지 파일 삭제 수행
        if (result > 0) {
            FileUpload.deleteFileList(imageList.toString());
        }
        return result;
    }

    /**
     * 사용자가 작성한 suggestion 댓글들을 삭제하는 메서드입니다.
     * 댓글 ID 목록을 받아와 해당 댓글들을 삭제합니다.
     *
     * @param comids 삭제할 댓글들의 ID 목록을 쉼표로 구분하여 전달
     * @return 삭제 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int deleteMyComment(String comids) {
        String id[] = comids.split(",");
        List<Integer> idList = new ArrayList<>();
        int result = -1;
        for (String item : id) {
            idList.add(Integer.parseInt(item));
        }
        result = boardSuggestionRepository.deleteHistoryComment(idList);
        return result;
    }

    /**
     * 사용자가 작성한 suggestion 좋아요 기록을 삭제하는 메서드입니다.
     * 좋아요 기록 ID 목록과 해당되는 글들의 ID 목록을 받아와 해당 기록들을 삭제합니다.
     *
     * @param data 삭제할 좋아요 기록들의 ID 목록과 해당되는 글들의 ID 목록을 포함한 Map 객체
     * @return 삭제 결과를 나타내는 정수를 반환합니다. 성공 시 1, 실패 시 -1을 반환합니다.
     */
    public int deleteMyLike(Map<String, String> data) {
        String boardids = data.get("boardids");
        String likeids = data.get("likeids");
        String[] id = likeids.split(",");
        String[] boardid = boardids.split(",");
        List<Integer> idList = new ArrayList<>();
        List<Integer> boardidList = new ArrayList<>();
        int result = -1;
        for (String item : id) {
            idList.add(Integer.parseInt(item));
        }
        result = boardSuggestionRepository.deleteHistoryLike(idList);
        // 쿼리문 실행이 성공했을 경우, 해당되는 글들의 좋아요 기록도 삭제
        if (result > 0) {
            for (String item : boardid) {
                boardidList.add(Integer.parseInt(item));
            }
            boardSuggestionRepository.deleteLikes(boardidList);
        }
        return result;
    }


}
