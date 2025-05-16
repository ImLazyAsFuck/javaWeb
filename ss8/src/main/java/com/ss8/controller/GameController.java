package com.ss8.controller;

import com.ss8.model.Seed;
import com.ss8.model.game.Plot;
import com.ss8.model.game.Seeds;
import com.ss8.model.game.User;
import com.ss8.model.game.WareHouseSeeds;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/game")
public class GameController{
    private static int idSequence = 0;
    private static int wareHouseIdSequence = 0;
    private static final List<User> USERS = new ArrayList<>();
    private static User currentUser = null;
    private static final List<Seeds> SEEDS_LIST = Arrays.asList(
            new Seeds(1, "Đậu bắn (Peashooter)", 50000.0, "https://i.pinimg.com/736x/a2/ee/ad/a2eead4eaf98cf88d6505a34a228bf44.jpg"),
            new Seeds(2, "Hướng dương (Sunflower)", 40000.0, "https://i.pinimg.com/736x/be/d8/c4/bed8c4d3478a8747d56e603673a9f2d5.jpg"),
            new Seeds(3, "Bom anh đào (Cherry Bomb)", 75000.0, "https://i.pinimg.com/736x/39/a4/a3/39a4a31b2d08b72f4125e0e47fcf584a.jpg"),
            new Seeds(4, "Quả óc chó (Wall-nut)", 30000.0, "https://i.pinimg.com/736x/fd/6a/ee/fd6aeebbe2e16efccccf717b1ee4b8ee.jpg"),
            new Seeds(5, "Đậu băng (Snow Pea)", 60000.0, "https://i.pinimg.com/736x/92/bc/ec/92bcec561bad37153e15678fdba88af2.jpg"),
            new Seeds(6, "Cây ăn thịt (Chomper)", 80000.0, "https://i.pinimg.com/736x/3e/ae/38/3eae388d65c8e1b0261791a75a55c5fc.jpg"),
            new Seeds(7, "Đậu đôi (Repeater)", 65000.0, "https://i.pinimg.com/736x/ba/85/99/ba85990c41628164c2c95b6cb324c9ba.jpg"),
            new Seeds(8, "Nấm hơi (Puff-shroom)", 20000.0, "https://i.pinimg.com/736x/f4/c1/f8/f4c1f8851099e48e4e21e3ee710c25c9.jpg"),
            new Seeds(9, "Mìn khoai tây (Potato Mine)", 35000.0, "https://i.pinimg.com/736x/fc/5b/a4/fc5ba427f84b2de91c9653682ba4e018.jpg"),
            new Seeds(10, "Đậu 4 nòng (Gatling Pea)", 90000.0, "https://i.pinimg.com/736x/f7/75/39/f7753930fc3d1a042dc70d94a9f37539.jpg")
    );
    private static final List<WareHouseSeeds> WAREHOUSE_SEEDS = new ArrayList<>();
    private static final List<Plot> PLOTS = IntStream.rangeClosed(1, 20)
            .mapToObj(id -> new Plot(id, null))
            .toList();

    @PostMapping("/login")
    public ModelAndView login(User user) {
        currentUser = USERS.stream().filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())).findFirst().orElse(null);
        if(currentUser == null){
            return new ModelAndView("/game/login");
        }
        return new ModelAndView("redirect:/game/home");
    }


    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/game/login");
    }

    @PostMapping("/shop")
    public ModelAndView buySeeds(HttpSession session, Seeds seed) {
        ModelAndView mv = new ModelAndView();
        if (currentUser == null) {
            mv.setViewName("redirect:/game/login");
            return mv;
        }
        if (currentUser.getBalance() < seed.getPrice()) {
            mv.setViewName("redirect:/game/shop");
            return mv;
        }else {
            currentUser.setBalance(currentUser.getBalance() - seed.getPrice());
            mv.addObject("bought", "You have successfully bought " + seed.getSeedsName() + "!");
            if(WAREHOUSE_SEEDS.stream().noneMatch(ws -> ws.getSeeds().getId() == seed.getId())){
                WAREHOUSE_SEEDS.add(new WareHouseSeeds(++wareHouseIdSequence,1, seed));
            }else{
                Optional<WareHouseSeeds> existingSeed = WAREHOUSE_SEEDS.stream()
                        .filter(ws -> ws.getSeeds().getId() == seed.getId())
                        .findFirst();
                existingSeed.ifPresent(wareHouseSeeds -> wareHouseSeeds.setQuantity(wareHouseSeeds.getQuantity() + 1));
            }
        }
        mv.setViewName("redirect:/game/home");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("/game/register");
    }

    @PostMapping("/register")
    public ModelAndView register(User user){
        ModelAndView mv = new ModelAndView();
        if(USERS.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()) && u.getEmail().equals(user.getEmail()))){
            return new ModelAndView("redirect:/game/register");
        }
        user.setId(++idSequence);
        user.setBalance(1000000.0);
        USERS.add(user);
        mv.setViewName("redirect:/game/login");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {
        if(currentUser == null){
            return new ModelAndView("redirect:/game/register");
        }

        ModelAndView mv = new ModelAndView("/game/home");
        mv.addObject("user", currentUser);
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        currentUser = null;
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/shop")
    public ModelAndView shop(){
        ModelAndView mv = new ModelAndView("/game/shop");
        mv.addObject("seeds", SEEDS_LIST);
        return mv;
    }

    @GetMapping("/warehouse")
    public ModelAndView warehouse(){
        ModelAndView mv = new ModelAndView("/game/warehouse");
        mv.addObject("warehouseSeeds", WAREHOUSE_SEEDS);
        return mv;
    }

    @GetMapping("/farm")
    public ModelAndView farm(){
        ModelAndView mv = new ModelAndView("/game/farm");
        mv.addObject("plots", PLOTS);
        mv.addObject("seeds", WAREHOUSE_SEEDS);
        return mv;
    }

    @PostMapping("/farm")
    public ModelAndView farm(@RequestParam("plotId") int plotId, @RequestParam("seedName") String seedId){
        ModelAndView mv = new ModelAndView();
        if(currentUser == null){
            mv.setViewName("redirect:/game/login");
            return mv;
        }
        Optional<Plot> plot = PLOTS.stream().filter(p -> p.getId() == plotId).findFirst();
        if(plot.isEmpty()){
            mv.setViewName("redirect:/game/farm");
            return mv;
        }
        Optional<WareHouseSeeds> seed = WAREHOUSE_SEEDS.stream().filter(w -> w.getSeeds().getId() == Integer.parseInt(seedId)).findFirst();
        if(seed.isEmpty()){
            mv.setViewName("redirect:/game/farm");
            return mv;
        }
        if(plot.get().getSeeds() != null){
            mv.addObject("message", "This plot is already occupied!");
            mv.setViewName("redirect:/game/farm");
            return mv;
        }
        if(seedId.isEmpty()){
            mv.addObject("message", "Please select a seed!");
            mv.setViewName("redirect:/game/farm");
            return mv;
        }
        plot.get().setSeeds(seed.get().getSeeds());
        seed.get().setQuantity(seed.get().getQuantity() - 1);
        if(seed.get().getQuantity() == 0){
            WAREHOUSE_SEEDS.remove(seed.get());
        }
        mv.setViewName("redirect:/game/farm");
        return mv;
    }

}
