package app.library.util;

import app.library.models.Reader;
import app.library.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReaderValidator implements Validator {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderValidator(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;

        if(readerRepository.findByFullName(reader.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Such person already exists");
        }
    }
}
