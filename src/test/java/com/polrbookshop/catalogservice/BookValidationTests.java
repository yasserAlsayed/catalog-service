package com.polrbookshop.catalogservice;

import com.polrbookshop.catalogservice.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.AssertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds(){
       var book = new Book("1234567890", "Book 1", "Author 1", 9.90);
        Set<ConstraintViolation<Book>> validations = validator.validate(book);
        assertThat(validations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails(){
        var book = new Book("123456aas7890", "Book 1", "Author 1", 9.90);
        Set<ConstraintViolation<Book>> validations = validator.validate(book);
        assertThat(validations).hasSize(1);
        ConstraintViolation<Book> violation = validations.iterator().next();
        assertThat(violation.getMessage()).isEqualTo("The ISBN format must be valid.");

    }


}
