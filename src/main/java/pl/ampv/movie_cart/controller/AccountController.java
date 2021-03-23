package pl.ampv.movie_cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ampv.registration.model.User;
import pl.ampv.registration.repository.UserRepository;
import pl.ampv.registration.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @GetMapping("/my_account")
    public String showAccountPage(Model model) {
        String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("users", userService.findUserByUsername(emailUser));
        return "account_page";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("edited",userService.findById(id));
        return "edit_page";
    }

    @PostMapping("/user/edit/update")
    public String update(@Valid @ModelAttribute User user, Errors errors){
        if (errors.hasErrors()) {
            log.error("Error occurred in front: " + errors.getFieldError());
            return "edit_page";
        }
        userService.update(user);
        log.info("User updated");

        return "redirect:/my_account";
    }


}
