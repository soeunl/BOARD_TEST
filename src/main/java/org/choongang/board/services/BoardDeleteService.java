package org.choongang.board.services;


import lombok.RequiredArgsConstructor;
import org.choongang.board.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardDeleteService {
    private final BoardService boardService;
    private final BoardDataRepository boardDataRepository;

    public void delete(Long seq) {
        boardDataRepository.deleteById(seq);
        boardDataRepository.flush();
    }
}
