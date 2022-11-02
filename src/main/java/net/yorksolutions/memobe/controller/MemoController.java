package net.yorksolutions.memobe.controller;

import net.yorksolutions.memobe.dto.NewMemoRequestDTO;
import net.yorksolutions.memobe.dto.UpdateMemoRequestDTO;
import net.yorksolutions.memobe.entity.Memo;
import net.yorksolutions.memobe.service.MemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memo")
@CrossOrigin
public class MemoController {

    MemoService memoService;
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public Memo create(@RequestBody NewMemoRequestDTO requestDTO) {
        return this.memoService.create(requestDTO);
    }

    @GetMapping
    public Iterable<Memo> getMemoList(@RequestParam Long accountId) {
        return this.memoService.getMemoList(accountId);
    }

    @DeleteMapping
    public void delete(@RequestParam Long memoId) {
        this.memoService.delete(memoId);
    }

    @PutMapping
    public Memo update(@RequestBody UpdateMemoRequestDTO requestDTO) {
        return this.memoService.update(requestDTO);
    }
}
