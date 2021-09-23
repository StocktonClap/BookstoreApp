package com.ms.controller;

import com.ms.entities.Book;
import com.ms.entities.CartItem;
import com.ms.entities.ShoppingCart;
import com.ms.entities.User;
import com.ms.exceptions.BookNotFoundException;
import com.ms.service.BookService;
import com.ms.service.CartITemService;
import com.ms.service.ShoppingCartService;
import com.ms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private static final Logger logger = LogManager.getLogger(CartController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartITemService cartITemService;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/cartItemList")
    public List<CartItem> getCartItems(User user) {
        User cartUser = userService.getUserByUsername(user.getUsername());
        ShoppingCart shoppingCart = cartUser.getShoppingCart();
        List<CartItem> cartItemList = cartITemService.getByShoppingCart(shoppingCart);
        shoppingCartService.update(shoppingCart);
        return cartItemList;
    }

    @GetMapping("/shoppingCart")
    public ShoppingCart getShoppingCart(User user) {
        User cartUser = userService.getUserByUsername(user.getUsername());
        ShoppingCart shoppingCart = cartUser.getShoppingCart();
        shoppingCartService.update(shoppingCart);
        return shoppingCart;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody HashMap<String, String> mapper, User user) throws BookNotFoundException {
        String bookId = mapper.get("id");
        String quantity = mapper.get("quantity");
        User cartUser = userService.getUserByUsername(user.getUsername());
        Book book = bookService.getBookById(Integer.parseInt(bookId));

        if (Integer.parseInt(quantity) > book.getStockNumber()) {
            return new ResponseEntity<>("Not enough books in stock", HttpStatus.BAD_REQUEST);
        }

        cartITemService.addBookToCartItem(book, cartUser, Integer.parseInt(quantity));
        return new ResponseEntity<>("Book added to cart.",HttpStatus.OK);
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<?> updateCartItem(@RequestBody HashMap<String, String> mapper) {
        String cartItemId = mapper.get("id");
        String quantity = mapper.get("quantity");
        CartItem cartItem = cartITemService.getCartItemById(Integer.parseInt(cartItemId));
        cartItem.setQuantity(Integer.parseInt(quantity));
        cartITemService.updateCartItem(cartItem);
        return new ResponseEntity<>("Cart updated.", HttpStatus.OK);
    }

    @DeleteMapping("/removeCartItem")
    public ResponseEntity<?> removeCartItem (@RequestBody String id) {
        CartItem cartItem = cartITemService.getCartItemById(Integer.parseInt(id));
        cartITemService.removeCartItem(cartItem);
        return new ResponseEntity<>("Cart item deleted.", HttpStatus.OK);
    }
}
