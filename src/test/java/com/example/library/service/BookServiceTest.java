package test.java.com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        List<Book> books = Arrays.asList(new Book(1L, "Title1", "Author1", "ISBN1"), new Book(2L, "Title2", "Author2", "ISBN2"));
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> bookDTOS = bookService.getAllBooks();

        assertEquals(2, bookDTOS.size());
        assertEquals("Title1", bookDTOS.get(0).getTitle());
        assertEquals("Title2", bookDTOS.get(1).getTitle());
    }

    @Test
    void addBook() {
        Book book = new Book(1L, "Title1", "Author1", "ISBN1");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO bookDTO = new BookDTO(null, "Title1", "Author1", "ISBN1");
        BookDTO savedBookDTO = bookService.addBook(bookDTO);

        assertEquals("Title1", savedBookDTO.getTitle());
        assertEquals("Author1", savedBookDTO.getAuthor());
        assertEquals("ISBN1", savedBookDTO.getIsbn());
    }

    @Test
    void updateBook() {
        Book book = new Book(1L, "Title1", "Author1", "ISBN1");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO bookDTO = new BookDTO(null, "UpdatedTitle", "UpdatedAuthor", "UpdatedISBN");
        BookDTO updatedBookDTO = bookService.updateBook(1L, bookDTO);

        assertEquals("UpdatedTitle", updatedBookDTO.getTitle());
        assertEquals("UpdatedAuthor", updatedBookDTO.getAuthor());
        assertEquals("UpdatedISBN", updatedBookDTO.getIsbn());
    }

    @Test
    void deleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
