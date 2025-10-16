package app.library.repositories;

import app.library.models.Book;
import app.library.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
