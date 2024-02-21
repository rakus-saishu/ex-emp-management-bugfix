package com.example.validation;

import org.springframework.dao.EmptyResultDataAccessException;

import com.example.repository.AdministratorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final AdministratorRepository administratorRepository;

    public UniqueEmailValidator(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String mailAddress, ConstraintValidatorContext context) {
        // メールアドレスが重複していないかを確認するロジックをここに実装

        if (mailAddress == null || mailAddress.isEmpty()) {
            return true;  // Nullまたは空文字列はバリデーションの対象外とする場合
        }

        try {
            String searchMailAddress = administratorRepository.searchByMailAddress(mailAddress);
            return searchMailAddress == null;  // 既存のメールアドレスが存在しない場合にtrueを返す
        } catch (EmptyResultDataAccessException ex) {
            return true;  // メールアドレスが存在しない場合にtrueを返す
        }
    }
}
