package app.library.dao;

import app.library.models.Book;
import app.library.models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/reader")
public class ReaderDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ReaderDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Reader> getAllReaders() {
//        return jdbcTemplate.query("SELECT * FROM Reader", new BeanPropertyRowMapper<>(Reader.class));
//    }
//
//    public Reader getReader(int id) {
//        return jdbcTemplate.query("SELECT * FROM Reader where id=?", new Object[] {id},
//                new BeanPropertyRowMapper<>(Reader.class)).stream().findAny().orElse(null);
//    }
//
//    public void createReader(Reader reader) {
//        jdbcTemplate.update("INSERT INTO Reader(full_name, year_of_birth) VALUES (?, ?)",
//                reader.getFullName(), reader.getYearOfBirth());
//    }
//
//    public void updateReader(Reader reader, int id) {
//        jdbcTemplate.update("UPDATE Reader SET full_name=?, year_of_birth=? where id=?",
//                reader.getFullName(), reader.getYearOfBirth(), id);
//    }
//
//    public void deleteReader(int id) {
//        jdbcTemplate.update("DELETE FROM Reader where id=?", id);
//    }
//
//    public List<Book> getBooksByReaderId(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE reader=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
}
