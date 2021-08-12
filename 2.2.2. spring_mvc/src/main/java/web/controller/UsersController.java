package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.models.User;

import javax.validation.Valid;

@Controller
public class UsersController {
    private UserDao userDao;
    @Autowired
    public void setUserDao (UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("list", userDao.index());
        return "index";
    }
    @GetMapping("/{count}")
    public String show(@PathVariable("count") int count, Model model) {
        model.addAttribute("user", userDao.show(count));
        return "/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        userDao.save(user);
        return "redirect:/";
    }
    @GetMapping("/{count}/edit")
    public String edit(@PathVariable("count") int count, Model model) {
        model.addAttribute("user", userDao.show(count));
        return "/edit";
    }
    @PostMapping("/{count}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("count") int count) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userDao.update(count, user);
        return "redirect:/";
    }
    @ PostMapping("/{count}/delete")
    public String delete(@PathVariable("count") int count) {
        userDao.delete(count);
        return "redirect:/";
    }

}