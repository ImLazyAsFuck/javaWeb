package com.ss8.controller;

import com.ss8.model.Seed;
import com.ss8.model.game.Seeds;
import com.ss8.model.game.User;
import com.ss8.model.game.WareHouseSeeds;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController{
    private static int idSequence = 0;
    private static final List<User> USERS = new ArrayList<>();
    private static final List<Seeds> SEEDS_LIST = Arrays.asList(
            new Seeds(1, "Đậu bắn (Peashooter)", 50000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/c/ca/Peashooter2.png/revision/latest?cb=20221126065143"),
            new Seeds(2, "Hướng dương (Sunflower)", 40000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/d/de/Sunflower2.png/revision/latest?cb=20221126064943"),
            new Seeds(3, "Bom anh đào (Cherry Bomb)", 75000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/2/2d/Cherry_BombO.png/revision/latest?cb=20230310011145"),
            new Seeds(4, "Quả óc chó (Wall-nut)", 30000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/c/c0/Wall-nut1.png/revision/latest?cb=20090521221605"),
            new Seeds(5, "Đậu băng (Snow Pea)", 60000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/c/cd/Snow_Pea1.png/revision/latest?cb=20090521220859"),
            new Seeds(6, "Cây ăn thịt (Chomper)", 80000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/a/a3/Chomper1.png/revision/latest?cb=20090521220057"),
            new Seeds(7, "Đậu đôi (Repeater)", 65000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/9/9c/Repeater1.png/revision/latest?cb=20090521220801"),
            new Seeds(8, "Nấm hơi (Puff-shroom)", 20000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/d/d7/Puff-shroom1.png/revision/latest?cb=20090521220734"),
            new Seeds(9, "Mìn khoai tây (Potato Mine)", 35000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/f/f2/Potato_Mine1.png/revision/latest?cb=20090521220546"),
            new Seeds(10, "Đậu 4 nòng (Gatling Pea)", 90000.0, "https://static.wikia.nocookie.net/plantsvszombies/images/f/ff/Gatling_Pea1.png/revision/latest?cb=20100722161218")
    );
    private static final List<WareHouseSeeds> WAREHOUSE_SEEDS = new ArrayList<>();

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user, HttpSession session) {
        user.setId(++idSequence);
        user.setBalance(1000000.0);
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/game/home");
    }


    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("game/login");
    }

    @PostMapping("/shop")
    public ModelAndView buySeeds(HttpSession session, Seeds seed) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mv.addObject("message", "Please login first!");
            mv.setViewName("game/login");
            return mv;
        }
        if (user.getBalance() < seed.getPrice()) {
            mv.addObject("message", "Insufficient balance!");
            mv.setViewName("game/home");
        }else {
            user.setBalance(user.getBalance() - seed.getPrice());
            mv.addObject("message", "You have successfully bought " + seed.getSeedsName() + "!");
            mv.addObject("seed", seed);
        }
        mv.setViewName("game/home");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("game/register");
    }

    @PostMapping("/register")
    public ModelAndView register(User user){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("game/login");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/game/register");
        }

        ModelAndView mv = new ModelAndView("game/home");
        mv.addObject("user", user);
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("user");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/shop")
    public ModelAndView shop(){
        ModelAndView mv = new ModelAndView("game/shop");
        mv.addObject("seeds", SEEDS_LIST);
        return mv;
    }

    @GetMapping("/warehouse")
    public ModelAndView warehouse(){
        ModelAndView mv = new ModelAndView("game/warehouse");
        mv.addObject("warehouseSeeds", WAREHOUSE_SEEDS);
        return mv;
    }
}
