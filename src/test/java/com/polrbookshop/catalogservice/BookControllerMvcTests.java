package com.polrbookshop.catalogservice;

import com.polrbookshop.catalogservice.domain.BookNotFoundException;
import com.polrbookshop.catalogservice.domain.BookService;
import com.polrbookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.net.URI;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBooksNotExistingThenReturnNotFound() throws Exception {
        String isbn = "123";
        //Given
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        //When
        mockMvc.perform(get(new URI("/books/")+isbn))
        //Then
                .andExpect(status().isNotFound());

    }
}
