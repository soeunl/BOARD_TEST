package org.choongang.board.exceptions;

import org.choongang.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends CommonException {
    public BoardNotFoundException() {
        super("게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
