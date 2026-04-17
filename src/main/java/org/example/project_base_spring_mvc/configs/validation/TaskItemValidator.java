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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.taskItem.title", "Tên không được để trống");
        if (task.getTitle() != null && task.getTitle().trim().length() > 0 && task.getTitle().trim().length() < 5) {
            errors.rejectValue("title", "Size.taskItem.title", "Tên phải có ít nhất 5 ký tự");
        }

        if (task.getDeadline() == null) {
            errors.rejectValue("deadline", "NotNull.taskItem.deadline", "Deadline bắt buộc phải có");
        } else if (!task.getDeadline().isAfter(LocalDate.now())) {
            errors.rejectValue("deadline", "Future.taskItem.deadline", "Deadline phải là ngày trong tương lai");
        }

        if (task.getPriority() == null || !task.getPriority().matches("^(HIGH|MEDIUM|LOW)$")) {
            errors.rejectValue("priority", "Pattern.taskItem.priority", "Priority phải là HIGH, MEDIUM hoặc LOW");
        }
    }
}
