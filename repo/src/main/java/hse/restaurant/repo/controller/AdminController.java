package hse.restaurant.repo.controller;

import hse.restaurant.repo.model.Dish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin")
public interface AdminController {

    @PostMapping("/add")
    ResponseEntity<Dish> addDish(@RequestBody Dish dish,@RequestParam String email);

    @DeleteMapping("/remove")
    ResponseEntity<Void> removeDish(@RequestParam UUID id,@RequestParam String email);

    @PutMapping("/update")
    ResponseEntity<Dish> updateDish(@RequestBody Dish updatedDish, @RequestParam UUID id,@RequestParam String email);

    @GetMapping("/{id}")
    ResponseEntity<Dish> getDishById(@PathVariable UUID id,@RequestParam String email);

    @GetMapping("/all")
    ResponseEntity<List<Dish>> getAllDishes(@RequestParam String email);
}

