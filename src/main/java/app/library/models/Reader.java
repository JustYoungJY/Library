package app.library.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class Reader {
        private Integer id;

        @Pattern(regexp = "^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$",
        message = "Full name should be capitalized (e.g., Ivan Ivanovich Ivanov)")
        @NotEmpty(message = "Full name should not be empty")
        @Size(min = 3, max = 255, message = "Full name should be between 3 and 255 characters")
        private String fullName;

        @NotNull(message = "Year of birth should not be empty")
        @Range(min = 1900, max = 2025, message = "Year of birth should be between 1900 and 2025")
        private Integer yearOfBirth;

        public Reader() {
        }

        public Reader(Integer id, String fullName, Integer yearOfBirth) {
                this.id = id;
                this.fullName = fullName;
                this.yearOfBirth = yearOfBirth;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public Integer getYearOfBirth() {
                return yearOfBirth;
        }

        public void setYearOfBirth(Integer yearOfBirth) {
                this.yearOfBirth = yearOfBirth;
        }
}