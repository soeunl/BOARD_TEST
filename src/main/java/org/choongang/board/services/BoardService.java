package org.choongang.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoard;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final PasswordEncoder passwordEncoder;
    private final BoardDataRepository boardDataRepository;

    public void save(RequestBoard form) { // 게시글 수정과 작성 서비스

        Long seq = Objects.requireNonNullElse(form.getSeq(), 0L);
        BoardData boardData = boardDataRepository.findById(seq).orElseGet(BoardData::new);
        /*
        if (seq == 0L) { // 추가
            boardData = new ModelMapper().map(form, BoardData.class);
        }
         */
        boardData.setEmail(form.getEmail());
        boardData.setTitle(form.getTitle());
        boardData.setContent(form.getContent());

        String hash = passwordEncoder.encode(form.getPassword()); // BCrypt 해시화
        boardData.setPassword(hash);

        boardDataRepository.saveAndFlush(boardData);
    }

    public RequestBoard getForm(Long seq) { // 검색한 폼 가지고 오기
        BoardData boardData = boardDataRepository.findById(seq).orElse(null);
        RequestBoard form = new ModelMapper().map(boardData, RequestBoard.class);
        return form;
    }
}

