package app.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Reader {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Pattern(regexp = "^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$",
        message = "Full name should be capitalized (e.g., Ivan Ivanovich Ivanov)")
        @NotEmpty(message = "Full name should not be empty")
        @Size(min = 3, max = 255, message = "Full name should be between 3 and 255 characters")
        @Column(name = "full_name")
        private String fullName;

        @NotNull(message = "Date of birth should not be empty")
        @Column(name = "date_of_birth")
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date dateOfBirth;

        @OneToMany(mappedBy = "reader")
        private List<Book> books;

        public Reader() {
        }

        public Reader(Long id, String fullName, Date dateOfBirth) {
                this.id = id;
                this.fullName = fullName;
                this.dateOfBirth = dateOfBirth;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public Date getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public List<Book> getBooks() {
                return books;
        }

        public void setBooks(List<Book> books) {
                this.books = books;
        }
}