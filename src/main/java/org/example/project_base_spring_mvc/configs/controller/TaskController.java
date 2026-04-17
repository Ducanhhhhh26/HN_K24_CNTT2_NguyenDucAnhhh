package org.example.project_base_spring_mvc.configs.controller;

import jakarta.validation.Valid;
import org.example.project_base_spring_mvc.configs.model.entity.TaskItem;
import org.example.project_base_spring_mvc.configs.validation.TaskItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskItemValidator taskItemValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(taskItemValidator);
    }

    private final List<TaskItem> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new TaskItem(LocalDate.now().plusDays(2), "1", "HIGH", "Task One"));
        tasks.add(new TaskItem(LocalDate.now().plusDays(5), "2", "MEDIUM", "Task Two"));
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "task-list";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("taskItem", new TaskItem());
        return "task-form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("taskItem") TaskItem taskItem, BindingResult result) {
        if (result.hasErrors()) {
            return "task-form";
        }
        taskItem.setId(String.valueOf(tasks.size() + 1));
        tasks.add(taskItem);
        return "redirect:/tasks";
    }
}
