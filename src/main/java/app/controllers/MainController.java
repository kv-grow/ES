package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Book;
import app.services.ESEngine;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ESEngine esEngine;

    @GetMapping("/hello")
    @ApiOperation(value = "greeting")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping()
    @ApiOperation(value = "register book")
    public void registerBook(
            @ApiParam(
                    name = "book",
                    value = "two fields - author and title",
                    required = true)
            @RequestBody Book book){
        esEngine.addObject(book);
    }


}
