package spring.validate;

import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.constant.GlobalConstant;
import spring.constant.RegexConstant;
import spring.model.User;
import spring.util.FileUtil;

@Component
public class UserValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object object, Errors errors) {

	}

	public void validatePassword(User user, Errors errors, User oldUser, String confirmPassword, Model model) {
		if (!user.getPassword().equals(GlobalConstant.EMPTY)) {
			if (!Pattern.matches(RegexConstant.REGEX_PASSWORD, user.getPassword())) {
				errors.rejectValue("password", null,
						messageSource.getMessage("formatPassError", null, Locale.getDefault()));
			}
			if (oldUser != null) {
				if (bCryptPasswordEncoder.matches(user.getPassword(), oldUser.getPassword())) {
					errors.rejectValue("password", null,
							messageSource.getMessage("duplicatePasswordError", null, Locale.getDefault()));
				}
			}
			if (!user.getPassword().equals(confirmPassword)) {
				errors.rejectValue("password", null, GlobalConstant.EMPTY);
				model.addAttribute("confirmPasswordError",
						messageSource.getMessage("confirmPasswordError", null, Locale.getDefault()));
			}
		}
	}

	public void validatePhone(User user, Errors errors) {
		if (!user.getPhone().equals(GlobalConstant.EMPTY)) {
			if (!Pattern.matches(RegexConstant.REGEX_PHONE, user.getPhone())) {
				errors.rejectValue("phone", null,
						messageSource.getMessage("formatPhoneError", null, Locale.getDefault()));
			}
		}
	}

	public void validatePicture(String fileName, Errors errors) {
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			if (!FileUtil.checkFileExtension(fileName)) {
				errors.rejectValue("avatar", null,
						messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
			}
		}
	}

}
