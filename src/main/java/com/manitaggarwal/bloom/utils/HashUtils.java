package com.manitaggarwal.bloom.utils;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class HashUtils {
    public String sha256hex(String originalString) {
        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString().replaceAll("[^0-9]", "").substring(0, 3);
    }

    public String adler32hex(String originalString) {
        return Hashing.adler32()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString().replaceAll("[^0-9]", "").substring(0, 3);
    }

    public String crc32hex(String originalString) {
        return Hashing.crc32()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString().replaceAll("[^0-9]", "").substring(0, 3);
    }
}
