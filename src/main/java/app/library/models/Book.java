package app.library.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
        private Integer id;

        @NotNull
        @Size(min = 1, max = 255, message = "Title should be between 1 and 255 characters")
        private String title;

        @NotNull
        @Size(min = 2, max = 255, message = "Author name should be between 2 and 255 characters")
        private String author;

        @NotNull
        private int yearOfWriting;

        public Book(Integer id, String title, String author, int yearOfWriting) {
                this.id = id;
                this.title = title;
                this.author = author;
                this.yearOfWriting = yearOfWriting;
        }

        public Book() {}

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
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

        public int getYearOfWriting() {
                return yearOfWriting;
        }

        public void setYearOfWriting(int yearOfWriting) {
                this.yearOfWriting = yearOfWriting;
        }
}
