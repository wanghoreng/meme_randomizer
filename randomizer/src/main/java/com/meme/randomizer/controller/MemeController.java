package com.meme.randomizer.controller;

import com.meme.randomizer.entity.Meme;
import com.meme.randomizer.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/meme")
@RequiredArgsConstructor
public class MemeController {

    private final MemeRepository memeRepository;


    // 1. 짤 등록
    @PostMapping
    public ResponseEntity<Meme> createMeme(@RequestBody Meme meme) {
        Meme saved = memeRepository.save(meme);
        return ResponseEntity.ok(saved);
    }

    // 2. 랜덤 짤 가져오기
    @GetMapping("/random")
    public ResponseEntity<Meme> getRandomMeme() {
        List<Meme> memes = memeRepository.findAll();
        if (memes.isEmpty()) return ResponseEntity.noContent().build();

        Random random = new Random();
        Meme meme = memes.get(random.nextInt(memes.size()));
        return ResponseEntity.ok(meme);
    }

    // 3. 좋아요 추가
    @PostMapping("/{id}/like")
    public ResponseEntity<Meme> likeMeme(@PathVariable Long id) {
        return memeRepository.findById(id)
                .map(meme -> {
                    meme.setLikeCount(meme.getLikeCount() + 1);
                    return ResponseEntity.ok(memeRepository.save(meme));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. 전체 목록 조회
    @GetMapping("/")
    public ResponseEntity<List<Meme>> getAllMemes() {
        return ResponseEntity.ok(memeRepository.findAll());
    }
}
