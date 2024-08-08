package test.java.com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllBooks() throws Exception {
        List<BookDTO> books = Arrays.asList(
                new BookDTO(1L, "Title1", "Author1", "ISBN1"),
                new BookDTO(2L, "Title2", "Author2", "ISBN2")
        );
        given(bookService.getAllBooks()).willReturn(books);

        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

    @Test
    void addBook() throws Exception {
        BookDTO bookDTO = new BookDTO(null, "Title1", "Author1", "ISBN1");
        BookDTO savedBookDTO = new BookDTO(1L, "Title1", "Author1", "ISBN1");
        given(bookService.addBook(any(BookDTO.class))).willReturn(savedBookDTO);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Title1"))
                .andExpect(jsonPath("$.author").value("Author1"))
                .andExpect(jsonPath("$.isbn").value("ISBN1"));
    }

    @Test
    void updateBook() throws Exception {
        BookDTO bookDTO = new BookDTO(null, "UpdatedTitle", "UpdatedAuthor", "UpdatedISBN");
        BookDTO updatedBookDTO = new BookDTO(1L, "UpdatedTitle", "UpdatedAuthor", "UpdatedISBN");
        given(bookService.updateBook(anyLong(), any(BookDTO.class))).willReturn(updatedBookDTO);

        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("UpdatedTitle"))
                .andExpect(jsonPath("$.author").value("UpdatedAuthor"))
                .andExpect(jsonPath("$.isbn").value("UpdatedISBN"));
    }

    @Test
    void deleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        mockMvc.perform(delete("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
