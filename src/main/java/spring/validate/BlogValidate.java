package spring.validate;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.constant.GlobalConstant;
import spring.service.CategoryService;
import spring.util.FileUtil;

@Component
public class BlogValidate implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {

		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {

	}

	public void validateCat(Integer catID, Errors errors) {
		if (categoryService.findById(catID) == null) {
			errors.rejectValue("cat", null, messageSource.getMessage("BlogCatError", null, Locale.getDefault()));
		}

	}

	public void validatePicture(String fileName, Errors errors) {
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			if (!FileUtil.checkFileExtension(fileName)) {
				errors.rejectValue("picture", null,
						messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
			}
		} else {
			errors.rejectValue("picture", null,
					messageSource.getMessage("formatPictureError", null, Locale.getDefault()));
		}

	}
}
