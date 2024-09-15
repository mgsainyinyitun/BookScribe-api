package com.sai.bookscribe.controllers.books;

import com.sai.bookscribe.constants.AppConstant;
import com.sai.bookscribe.controllers.auth.AuthenticationController;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.book.*;
import com.sai.bookscribe.services.books.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.VERSION+"/books")
@CrossOrigin(origins="*")
public class BookController {

    private  final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping("public")
    public ResponseEntity<?> getPublicBooks(@RequestParam(required = false) PublicBookRequest request) {
        List<PublicBookResponse> response =  bookServices.publicBookRequest();
        return  ResponseEntity.ok(response);
    }

    @GetMapping("private")
    public ResponseEntity<?> getPrivateBooks(@RequestParam(required = false)PrivateBookRequest request) {
        try {
            List<PrivateBookResponse> response = bookServices.privateBookRequest(request,AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteBook(@RequestParam Long id) {
        try {
            BookDeleteResponse response = bookServices.deleteBook(id,AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> addBook(@RequestBody BookCreateRequest request){
        try{
            BookCreateResponse response;
            response = bookServices.create(request,AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
