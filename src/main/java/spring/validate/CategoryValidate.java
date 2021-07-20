package spring.validate;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.model.Category;
import spring.service.CategoryService;

@Component
public class CategoryValidate implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object object, Errors errors) {
		Category category = (Category) object;
		if (categoryService.findByName(category.getName()) != null) {
			errors.rejectValue("name", null, messageSource.getMessage("duplicateCategory", null, Locale.getDefault()));
		}
	}

}
