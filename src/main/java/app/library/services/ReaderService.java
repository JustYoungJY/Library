package app.library.services;

import app.library.models.Book;
import app.library.models.Reader;
import app.library.repositories.BookRepository;
import app.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public ReaderService(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReader(long id) {
        return readerRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public void createReader(Reader reader) {
        readerRepository.save(reader);
    }

    @Transactional(readOnly = false)
    public void updateReader(Reader reader, Long id) {
        reader.setId(id);
        readerRepository.save(reader);
    }

    @Transactional(readOnly = false)
    public void deleteReader(long id) {
        readerRepository.deleteById(id);
    }

    public Optional<Reader> getReaderByBookId(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return Optional.ofNullable(book.getReader());
        }
        return Optional.empty();
    }

}
