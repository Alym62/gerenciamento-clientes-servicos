package com.full.servicos.service;

import com.full.servicos.domain.FileDB;
import com.full.servicos.repository.FileDBRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class FileDBServiceTest {
    @InjectMocks
    private FileDBService service;

    @Mock
    private FileDBRepository repository;

    private FileDB file;

    @BeforeEach()
    void setUp() {
        file = new FileDB("test", ".png", "content".getBytes());
        file.setId("000003e8-0bf9-21ef-8600-325096b39f47");

        repository.save(file);
    }

    @Test
    @DisplayName("Teste unitário método - Store")
    void store() {
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), file.getType(), file.getData());

        when(repository.save(any(FileDB.class))).thenReturn(file);

        var response = service.store(multipartFile);

        assertNotNull(response);
        assertEquals(file.getName(), response.getName());
    }

    @Test
    @DisplayName("Teste unitário método - FindById")
    void getFile() {
        var id = file.getId();

        when(repository.findById(id)).thenReturn(Optional.of(file));

        var response = service.getFile(id);

        verify(repository, times(1)).findById(id);

        assertEquals(file.getName(), response.getName());
    }

    @Test
    @DisplayName("Teste unitário método - FindAll")
    void getAllFiles() {
        when(repository.findAll()).thenReturn(List.of(file));

        var response = service.getAllFiles();

        verify(repository, times(1)).findAll();

        assertEquals(file.getId(), response.toList().get(0).getId());
    }
}