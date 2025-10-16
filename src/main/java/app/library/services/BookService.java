package app.library.services;

import app.library.models.Book;
import app.library.models.Reader;
import app.library.repositories.BookRepository;
import app.library.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public List<Book> getAllBooks(Integer page, Integer booksPerPage, boolean sortByYear) {
        Sort sort = Sort.unsorted();

        if(sortByYear) {
            sort = Sort.by("yearOfWriting");
        }

        if(page == null || booksPerPage == null || page < 0 || booksPerPage < 1 ) {
            return bookRepository.findAll(sort);
        }

        Pageable pageable = PageRequest.of(page, booksPerPage, sort);
        return bookRepository.findAll(pageable).getContent();
    }

    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public void assignBook(long bookId, long readerId) {
        Reader reader = readerRepository.findById(readerId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if(reader != null && book != null) {
            reader.getBooks().add(book);
            book.setReader(reader);
            book.setTimeOfTaking(OffsetDateTime.now());
            book.setExpired(false);
            bookRepository.save(book);
        }
    }

    @Transactional(readOnly = false)
    public void releaseBook(long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book != null && book.getReader() != null) {
            book.getReader().getBooks().remove(book);
            book.setReader(null);
            bookRepository.save(book);
        }
    }

    public List<Book> getBooksByReaderId(long id) {
        List<Book> books = bookRepository.findByReaderId(id);
        for(Book book : books) {
            if(OffsetDateTime.now().minusDays(10).isAfter(book.getTimeOfTaking())) {
                book.setExpired(true);
            }
        }
        return books;
    }

    public Optional<Book> getBookByPattern(String pattern) {
        return bookRepository.findFirstByTitleStartingWith(pattern);
    }
}
