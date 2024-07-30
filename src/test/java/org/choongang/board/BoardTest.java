package org.choongang.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Transactional
@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class BoardTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BoardDataRepository boardDataRepository;

    @BeforeEach
    void createList() { // 리스트 만들기
        List<BoardData> boardData = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> BoardData.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .title("제목" + i)
                        .content("내용" + i)
                        .build()).toList();

        boardDataRepository.saveAllAndFlush(boardData);
    }

    @Test
    void findTest() { // id 값으로 찾기 테스트
        Long seq = 1L;
        Optional<BoardData> boardDataView = boardDataRepository.findById(seq);
        System.out.println(boardDataView);
    }

    @Test
    void findTest2() { // id 값으로 찾기 테스트2
        BoardData item = boardDataRepository.findById(1L).orElse(null);
        if (item != null) {
            System.out.println("ID: " + item.getSeq());
            System.out.println("Email: " + item.getEmail());
            System.out.println("Title: " + item.getTitle());
            System.out.println("Content: " + item.getContent());
        } else {
            System.out.println("No BoardData found with ID 1");
        }
    }

    @Test
    void listTest() { // 리스트 출력 테스트
        List<BoardData> boardDataList = boardDataRepository.findAll();
        System.out.println(boardDataList);
    }
}