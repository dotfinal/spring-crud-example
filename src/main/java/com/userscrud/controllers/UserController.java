package com.userscrud.controllers;

import com.userscrud.models.User;
import com.userscrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    PagedListHolder<User> userList = null;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelMap model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");

        String type = request.getParameter("type");

        if (userList == null) {
            userList = new PagedListHolder<User>();
            userList.setPageSize(10);
        }

        if (type == null) {
            userList.setPage(0);
        } else if (type.equals("next")) {
            userList.nextPage();
        } else if (type.equals("prev")) {
            userList.previousPage();
        }
        addUserListToTheModel(model);
        modelAndView.getModelMap().addAttribute("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                          ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            if (userService.addUser(user)) {
                model.addAttribute(new User());
                model.addAttribute("message", "Successfully added.");
                model.addAttribute("isSuccess", true);
                addUserListToTheModel(model);
                return "index";
            } else {
                model.addAttribute("errorMessage", "This name already exists.");
                model.addAttribute("isSuccess", false);
            }
        } else {
            model.addAttribute("errorMessage", "Invalid data. Try again.");
            model.addAttribute("isSuccess", false);
        }
        return "single";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                             ModelMap model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            userService.updateUser(user);
            model.addAttribute("message", "Successfully updated.");
            model.addAttribute("isSuccess", true);
            addUserListToTheModel(model);
            return "index";
        } else {
            model.addAttribute("errorMessage", "Invalid data. Try again.");
            model.addAttribute("isSuccess", false);
        }
        return "single";
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public String deleteUser(ModelMap model, HttpServletRequest request) {
        User user = userService.getUserById(Integer.parseInt(request.getParameter("userid")));
        if (user != null) {
            userService.deleteUser(user);
            model.addAttribute("message", "Successfully deleted.");
            model.addAttribute("isSuccess", true);
        } else {
            model.addAttribute("message", "User with this ID doesn't exist.");
            model.addAttribute("isSuccess", false);
        }
        addUserListToTheModel(model);
        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchUser(ModelMap model, HttpServletRequest request) {
        List<User> result = userService.findByName(request.getParameter("query"));
        if (result == null || result.size() < 1) {
            model.addAttribute("message", "No matching results found. Try again.");
            model.addAttribute("isSuccess", false);
        } else {
            userList.setSource(result);
            userList.setPage(0);
            model.addAttribute("userList", userList);
        }
        return "index";
    }

    @RequestMapping(value = "single", method = RequestMethod.GET)
    public String single(ModelMap model, HttpServletRequest request) {
        User user = new User();
        if (request.getParameter("userid") != null &&
                Integer.parseInt(request.getParameter("userid")) > 0 &&
                userService.getUserById(Integer.parseInt(request.getParameter("userid"))) != null) {
            user = userService.getUserById(Integer.parseInt(request.getParameter("userid")));
        }
        model.addAttribute("user", user);
        return "single";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    private void addUserListToTheModel(ModelMap model) {
        // Re-setting source is needed to refresh current list, if something's changed.
        // There was RefreshablePagedListHolder in Spring 2.x, but in Spring 3.x it's deprecated and I don't know why.
        userList.setSource(userService.getAllUsers());
        model.addAttribute("userList", userList);
    }
}
