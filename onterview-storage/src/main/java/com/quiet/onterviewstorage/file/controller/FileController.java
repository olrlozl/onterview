package com.quiet.onterviewstorage.file.controller;

import com.quiet.onterviewstorage.util.FileUtils;
import com.quiet.onterviewstorage.file.dto.FileDto;
import com.quiet.onterviewstorage.file.dto.FileDto.FileResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileUtils fileUtils;

    @PostMapping("/file")
    public ResponseEntity<FileDto.FileResponse> imageUpload(
            @RequestPart("file") MultipartFile image
    ) throws IOException {
        // TODO: 확장자명에 따라 영상/이미지로 나눠 저장
        log.info(image.getOriginalFilename());
        String uuidFileName = fileUtils.createUUIDFileName(
                image.getOriginalFilename().split("\\.")[1]);
        image.transferTo(Path.of(fileUtils.IMAGE_PATH + "/" + uuidFileName));
        return ResponseEntity.ok(new FileResponse(fileUtils.IMAGE_PATH, uuidFileName));
    }

    @GetMapping("/file")
    public ResponseEntity<?> sendFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) throws IOException {
        File file = new File(filePath + "/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment" + file.getName());
        String path = file.getPath();
        byte[] body = Files.readAllBytes(Path.of(path));
        return ResponseEntity.ok()
                .headers(headers)
                .body(body);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) {
        log.info(filePath);
        File file = new File(filePath + "/" + fileName);

        if (file.exists()) {
            file.delete();
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}