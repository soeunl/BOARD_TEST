package org.choongang.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.board.controllers.RequestBoard;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PasswordEncoder passwordEncoder;
    private final BoardDataRepository boardDataRepository;

    public void save(RequestBoard form) {
        BoardData boardData = new ModelMapper().map(form, BoardData.class);
        String hash = passwordEncoder.encode(form.getPassword()); // BCrypt 해시화
        boardData.setPassword(hash);
        boardDataRepository.saveAndFlush(boardData);
    }
}

