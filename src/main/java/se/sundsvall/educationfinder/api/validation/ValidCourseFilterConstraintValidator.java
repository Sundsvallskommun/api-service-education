package se.sundsvall.educationfinder.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidCourseFilterConstraintValidator implements ConstraintValidator<ValidCourseFilter, String> {

	private static final String ERROR_MESSAGE_TEMPLATE = "Given value %s is not valid, valid values are %s";
	private static final List<String> VALID_VALUES = List.of("credits", "category", "subcategory", "provider", "level", "scope", "studyLocation");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		var valid = VALID_VALUES.contains(value);

		if (!valid) {
			useCustomMessageForValidation(context, ERROR_MESSAGE_TEMPLATE.formatted(value, VALID_VALUES));
		}

		return valid;
	}

	private void useCustomMessageForValidation(ConstraintValidatorContext constraintContext, String message) {
		constraintContext.disableDefaultConstraintViolation();
		constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}
}