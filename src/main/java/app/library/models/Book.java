package app.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @NotNull
        @Size(min = 1, max = 255, message = "Title should be between 1 and 255 characters")
        @Column(name = "title")
        private String title;

        @NotNull
        @Size(min = 2, max = 255, message = "Author name should be between 2 and 255 characters")
        @Column(name = "author")
        private String author;

        @NotNull
        @Column(name = "year_of_writing")
        @Max(value = 2025, message = "The year of writing should be less than 2026")
        private Integer yearOfWriting;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "reader", referencedColumnName = "id")
        private Reader reader;

        @Column(name = "time_of_taking")
        private OffsetDateTime timeOfTaking;

        @Transient
        private boolean isExpired;

        public Book(Long id, String title, String author, Integer yearOfWriting,
                    Reader reader, OffsetDateTime timeOfTaking, boolean isExpired) {
                this.id = id;
                this.title = title;
                this.author = author;
                this.yearOfWriting = yearOfWriting;
                this.reader = reader;
                this.timeOfTaking = timeOfTaking;
                this.isExpired = isExpired;
        }

        public Book() {}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public Integer getYearOfWriting() {
                return yearOfWriting;
        }

        public void setYearOfWriting(Integer yearOfWriting) {
                this.yearOfWriting = yearOfWriting;
        }

        public Reader getReader() {
                return reader;
        }

        public void setReader(Reader reader) {
                this.reader = reader;
        }

        public OffsetDateTime getTimeOfTaking() {
                return timeOfTaking;
        }

        public void setTimeOfTaking(OffsetDateTime timeOfTaking) {
                this.timeOfTaking = timeOfTaking;
        }

        public boolean isExpired() {
                return isExpired;
        }

        public void setExpired(boolean expired) {
                isExpired = expired;
        }
}
