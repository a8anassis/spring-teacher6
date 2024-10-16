package gr.aueb.cf.teacherapp.controller;

import gr.aueb.cf.teacherapp.dto.UserInsertDTO;
import gr.aueb.cf.teacherapp.mapper.Mapper;
import gr.aueb.cf.teacherapp.model.User;
import gr.aueb.cf.teacherapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;  // Assuming you have a UserService to save the user
    private final Mapper mapper;

    // Display the user insertion form
    @GetMapping("/users/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        return "user-form";
    }

    // Process the form submission
    @PostMapping("/users/register")
    public String insertUser(
            @Valid @ModelAttribute("userInsertDTO") UserInsertDTO userInsertDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // If validation errors exist, return the form with error messages
            return "user-form";
        }

        // Convert DTO to User entity and save (here you also encrypt the password)
        User user = mapper.mapToUserEntity(userInsertDTO);

        userService.saveUser(user);  // Encrypt password and save the user

        return "redirect:/users/register?success";  // Redirect with success message
    }
}