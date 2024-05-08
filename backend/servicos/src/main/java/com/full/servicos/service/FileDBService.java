package com.full.servicos.service;

import com.full.servicos.domain.FileDB;
import com.full.servicos.repository.FileDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileDBService {
    private final FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

            return fileDBRepository.save(fileDB);
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao tentar salvar o arquivo");
        }
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível achar o arquivo"));
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
