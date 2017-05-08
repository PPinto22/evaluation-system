package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class MyRestController {

    @Autowired
    public MyRestController(){

    }

    @RequestMapping(value = "/coisas", method = RequestMethod.GET)
    public String getCoisas(){
        return "Hello";
    }


}