package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

import java.util.stream.Collectors;

@Controller
public class CarController {
    @GetMapping("/cars")
    public String getCars(@RequestParam(value ="count", defaultValue ="5") int count, Model model) {
        model.addAttribute("cars", new CarService().getAllList().stream().limit(count).collect(Collectors.toList()));

        return "cars";
    }
}
