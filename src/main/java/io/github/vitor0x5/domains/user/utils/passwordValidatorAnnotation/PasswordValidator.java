package io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()>=8){
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasLetter = letter.matcher(s);
            Matcher hasDigit = digit.matcher(s);
            Matcher hasSpecial = special.matcher(s);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();
        }
        return false;
    }
}
