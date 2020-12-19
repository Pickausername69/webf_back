package com.example.springboot.sqlconnection;

import com.atlassian.clover.reporters.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller 
@CrossOrigin
@RequestMapping(path="/demo") 
public class MainController {
    @Autowired 
    private UserRepository userRepository;

    @PostMapping(path="/add") 
    public @ResponseBody boolean addNewUser (@RequestBody String body) throws Exception {
        try {
            JSONObject t = new JSONObject(body);
            User n = new User();
            n.setName(t.getString("name"));
            n.setEmail(t.getString("email"));
            userRepository.save(n);
            System.out.println("/demo/add accessed");
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        System.out.println("/demo/all accessed");
        return userRepository.findAll();
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody boolean deleteUser (@RequestBody String body)
    {
        try {
            JSONObject t=new JSONObject(body);
            int id=t.getInt("id");
            userRepository.delete(userRepository.findById(id).get());
            System.out.println("/demo/delete accessed");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @CrossOrigin
    @PutMapping(path="/update")
    public @ResponseBody boolean updateUser(@RequestBody String body)
    {
        try {
        JSONObject t=new JSONObject(body);
        User n =userRepository.findById(t.getInt("id")).get();
        n.setName(t.getString("name"));
        n.setEmail(t.getString("email"));
        userRepository.save(n);
        System.out.println("/demo/update accessed");
        return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
