package com.manitaggarwal.bloom.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class BloomUtils {

    private final HashUtils hashUtils;

    public BloomUtils(HashUtils hashUtils) {
        this.hashUtils = hashUtils;
    }

    BloomFilter<Integer> sha256filter = BloomFilter.create(
            Funnels.integerFunnel(),
            1000,
            0.01);

    BloomFilter<Integer> adler32filter = BloomFilter.create(
            Funnels.integerFunnel(),
            1000,
            0.01);

    BloomFilter<Integer> crc32filter = BloomFilter.create(
            Funnels.integerFunnel(),
            1000,
            0.01);

    public boolean checkInBloomFilter(String originalString) {
        boolean sha = checkInSHA256Filter(originalString);
        boolean crc = checkInCRC32Filter(originalString);
        boolean adler = checkInAdler32Filter(originalString);
        return sha && crc && adler;
    }

    private boolean checkInAdler32Filter(String originalString) {
        return adler32filter.mightContain(Integer.parseInt(
                hashUtils.adler32hex(originalString)));
    }

    private boolean checkInCRC32Filter(String originalString) {
        return crc32filter.mightContain(Integer.parseInt(
                hashUtils.crc32hex(originalString)));
    }

    private boolean checkInSHA256Filter(String originalString) {
        return sha256filter.mightContain(Integer.parseInt(
                hashUtils.sha256hex(originalString)));
    }

    public void saveToBloomFilter(String originalString) {
        saveToSHA256Filter(originalString);
        saveToCRC32Filter(originalString);
        saveToAdler32Filter(originalString);
    }

    private void saveToAdler32Filter(String originalString) {
        adler32filter.put(Integer.parseInt(
                hashUtils.adler32hex(originalString)));
    }

    private void saveToCRC32Filter(String originalString) {
        crc32filter.put(Integer.parseInt(
                hashUtils.crc32hex(originalString)));
    }

    private void saveToSHA256Filter(String originalString) {
        sha256filter.put(Integer.parseInt(
                hashUtils.sha256hex(originalString)));
    }

}
