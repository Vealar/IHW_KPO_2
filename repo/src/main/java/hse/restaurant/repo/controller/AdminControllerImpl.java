package hse.restaurant.repo.controller;

import hse.restaurant.repo.model.Dish;
import hse.restaurant.repo.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    private final DishService dishService;

    @Autowired
    public AdminControllerImpl(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/add")
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish,@RequestParam String email) {
        Dish addedDish = dishService.addDishItem(dish,email);
        return new ResponseEntity<>(addedDish, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeDish(@RequestParam UUID id,@RequestParam String email) {
        Dish dishToRemove = dishService.getById(id,email);
        if (dishToRemove != null) {
            dishService.removeDishItem(dishToRemove,email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Dish> updateDish(@RequestBody Dish updatedDish, @RequestParam UUID id,@RequestParam String email) {
        Dish existingDish = dishService.getById(id,email);
        if (existingDish != null) {
            updatedDish.setId(existingDish.getId());
            Dish updatedDishItem = dishService.updateDishItem(updatedDish, existingDish,email);
            return new ResponseEntity<>(updatedDishItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable UUID id,@RequestParam String email) {
        Dish dish = dishService.getById(id,email);
        if (dish != null) {
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dish>> getAllDishes(@RequestParam String email) {
        List<Dish> dishes = dishService.getAll(email);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}

