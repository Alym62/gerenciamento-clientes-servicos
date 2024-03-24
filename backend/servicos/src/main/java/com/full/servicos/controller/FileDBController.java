package com.full.servicos.controller;

import com.full.servicos.domain.FileDB;
import com.full.servicos.dto.responses.ResponseFile;
import com.full.servicos.dto.responses.ResponseMessage;
import com.full.servicos.service.FileDBService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/file")
@AllArgsConstructor
public class FileDBController {
    private final FileDBService fileDBService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) {
        String message = null;
        try {
            fileDBService.store(file);
            message = "Upload de imagem com sucesso! File: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Não foi possível fazer upload do arquivo! File: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileDBService.getAllFiles()
                .map(file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(file.getId())
                            .toUriString();

                    return new ResponseFile(
                            file.getName(),
                            fileDownloadUri,
                            file.getType(),
                            file.getData().length
                    );
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileDBService.getFile(id);

        MediaType mediaType = null;

        if (fileDB.getName().toLowerCase().endsWith(".jpg") || fileDB.getName().toLowerCase().endsWith(".jpeg"))
            mediaType = MediaType.IMAGE_JPEG;
        else if (fileDB.getName().toLowerCase().endsWith(".png"))
            mediaType = MediaType.IMAGE_PNG;
        else
            mediaType = MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .contentType(mediaType)
                .body(fileDB.getData());
    }
}
