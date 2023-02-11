package com.manitaggarwal.bloom.controller;

import com.manitaggarwal.bloom.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hashes")
@RequiredArgsConstructor
public class HashController {

    private final HashUtils hashUtils;

    @PostMapping("/{originalString}")
    public String getHash(@PathVariable String originalString) {
        return hashUtils.adler32hex(originalString);
    }
}
