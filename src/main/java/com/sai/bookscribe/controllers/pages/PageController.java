package com.sai.bookscribe.controllers.pages;
import com.sai.bookscribe.constants.AppConstant;
import com.sai.bookscribe.controllers.auth.AuthenticationController;
import com.sai.bookscribe.messages.page.MultiPageCreateResponse;
import com.sai.bookscribe.messages.page.PageCreateRequest;
import com.sai.bookscribe.messages.page.PageCreateResponse;
import com.sai.bookscribe.services.pages.PageServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.VERSION+"/pages")
@CrossOrigin(origins="*")
public class PageController {
    private  final PageServices pageServices;

    public PageController(PageServices pageServices) {
        this.pageServices = pageServices;
    }

    @PostMapping("add")
    public ResponseEntity<?> addPage(@RequestBody PageCreateRequest request){
        try{
            PageCreateResponse response;
            response = pageServices.create(request, AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("add-multi")
    public ResponseEntity<?> addMultiPage(@RequestBody List<PageCreateRequest> request){
        try{
            MultiPageCreateResponse response;
            response = pageServices.createMulti(request, AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
