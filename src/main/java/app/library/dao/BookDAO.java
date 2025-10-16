package app.library.dao;

import org.springframework.stereotype.Controller;

@Controller
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> getALlBooks() {
//        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book getBook(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void createBook(Book book) {
//        jdbcTemplate.update("INSERT INTO book(title, author, year_of_writing) VALUES (?, ?, ?)",
//                book.getTitle(), book.getAuthor(), book.getYearOfWriting());
//    }
//
//    public void updateBook(Book book, int id) {
//        jdbcTemplate.update("UPDATE book SET title=?, author=?, year_of_writing=? where id=?",
//                book.getTitle(), book.getAuthor(), book.getYearOfWriting(), id);
//    }
//
//    public void deleteBook(int id) {
//        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
//    }
//
//    public Reader getBookReader(int id) {
//        return jdbcTemplate.query("SELECT R.* FROM Reader R JOIN Book B ON R.id = B.reader WHERE B.id = ?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Reader.class)).stream().findAny().orElse(null);
//    }
//
//    public void assignBook(int bookId, int readerId) {
//        jdbcTemplate.update("UPDATE book SET reader=? WHERE id=?", readerId, bookId);
//    }
//
//    public void releaseBook(int bookId) {
//        jdbcTemplate.update("UPDATE book SET reader=NULL WHERE id=?", bookId);
//    }
}
