package org.choongang.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.board.services.BoardService;
import org.choongang.board.validator.BoardValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardValidator boardValidator;
    private final BoardService boardService;
    private final BoardDataRepository boardDataRepository;

    @GetMapping("/") // 메인 화면
    public String boardList(@ModelAttribute RequestBoard form, Model model) {

        List<BoardData> boardDataList = boardDataRepository.findAll();
        model.addAttribute("boardDataList", boardDataList);
        model.addAttribute("addCss", new String[] {"list"});

        return "board/list";
    }

    @GetMapping("/view/{seq}")
    public String boardView(@PathVariable("seq") Long seq, Model model) {
        BoardData boardDataView = boardDataRepository.findById(seq).orElse(null);
        model.addAttribute("boardDataView", boardDataView);

            return "board/view";

    }

    @GetMapping("/create")
    public String createBoard(@ModelAttribute RequestBoard form) {

        return "board/create";
    }

    @PostMapping("/create")
    public String createBoardPs(@Valid RequestBoard form, Errors errors) {

        boardValidator.validate(form, errors);

        if (errors.hasErrors()) {

            return "board/create";
        }

        boardService.save(form);

        return "redirect:/board/";
    }

    @GetMapping("/modify/{seq}")
    public String modifyBoard(@PathVariable("seq") Long seq, Model model) {

        RequestBoard form = boardService.getForm(seq);
        model.addAttribute("requestBoard", form);

        return "board/modify";
    }

    @PostMapping("/modify/{seq}")
    public String modifyBoardPs(@Valid RequestBoard form, Errors errors) {

        boardValidator.validate(form, errors);

        if (errors.hasErrors()) {

            return "board/modify";
        }

        boardService.save(form);

        return "redirect:/board/";
    }
}
