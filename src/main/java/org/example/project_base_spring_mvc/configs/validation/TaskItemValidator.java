package org.example.project_base_spring_mvc.configs.validation;

import org.example.project_base_spring_mvc.configs.model.entity.TaskItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class TaskItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskItem.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaskItem task = (TaskItem) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.taskItem.title", "Title must not be empty");
        if (task.getTitle() != null && task.getTitle().trim().length() > 0 && task.getTitle().trim().length() < 5) {
            errors.rejectValue("title", "Size.taskItem.title", "Title must be at least 5 characters");
        }

        if (task.getDeadline() == null) {
            errors.rejectValue("deadline", "NotNull.taskItem.deadline", "Deadline is required");
        } else if (!task.getDeadline().isAfter(LocalDate.now())) {
            errors.rejectValue("deadline", "Future.taskItem.deadline", "Deadline must be in the future");
        }

        if (task.getPriority() == null || !task.getPriority().matches("^(HIGH|MEDIUM|LOW)$")) {
            errors.rejectValue("priority", "Pattern.taskItem.priority", "Priority must be HIGH, MEDIUM, or LOW");
        }
    }
}
