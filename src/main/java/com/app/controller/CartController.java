package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.AddToCartDTO;
import com.app.dto.ApiResponse;
import com.app.entities.Cart;
import com.app.service.ICartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;
    
   

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Cart cart, @RequestParam Long productId, @RequestParam Long userId) {
        try {
            Cart newCart = cartService.addToCart(cart, productId, userId);
            return new ResponseEntity<>(newCart, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFromCart(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse(cartService.deleteFromCart(id)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse("Invalid Cart ID: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartDetailsByUserId(@PathVariable Long userId) {
        List<CartItemDTO> cartDetails = cartService.getCartDetailsByUserId(userId);
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartDetails(@RequestBody Cart detachedCart, @RequestParam Long productId, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(cartService.updateCart(detachedCart, productId, userId));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
