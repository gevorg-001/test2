package com.example.test.controler;

import com.example.test.jwt.JwtRequestModel;
import com.example.test.jwt.JwtResponseModel;
import com.example.test.jwt.TokenManager;
import com.example.test.model.Post;
import com.example.test.model.User;
import com.example.test.service.PostService;
import com.example.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Slf4j
@Controller
@RequestMapping()

public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PostService postService;

    User user;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel
                                                 request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        user = (User) userService.loadUserByUsername(request.getUsername());

        return ResponseEntity.ok(new JwtResponseModel(jwtToken));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/search_post")
    public ResponseEntity<List<Post>> save(@RequestParam(required = false) String word) {
        System.out.print(postService.search(word));
        return ResponseEntity.ok(postService.search(word));
    }

    @GetMapping("/user/post")
    public ResponseEntity<List<Post>> postsFromUser() {

        return ResponseEntity.ok(postService.findPost(user));

    }

    @PostMapping("/registration")
    public ResponseEntity<?> save(@RequestBody User user) {

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/save_post")
    public ResponseEntity<?> save(@RequestBody Post post) {
        post.setUser(user);
        postService.save(post);
        return ResponseEntity.ok(post);
    }


    @PutMapping("/update")
    public ResponseEntity<User> update(@Valid @RequestBody User user) throws InternalError {
        return ResponseEntity.ok(userService.update(user));

    }



































   /*

    @GetMapping("/login")
    public String login(Model model) {


        model.addAttribute("UserForm", user);

        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm) {

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {

            return "password_error";
        }
        if (!userService.saveUser(userForm)) {


            return "usersave_error";
        }

        return "redirect:/";
    }

    @GetMapping()
    public String userList(Model model) {

        List<User> user = userService.allUsers();
        User user1 = new User();


        model.addAttribute("users", user);

        model.addAttribute("user", user1);


        return "all_users";
    }


    @GetMapping("/admin")
    public String userList1(Model model) {

        List<User> user = userService.allUsers();
        User user1 = new User();


        model.addAttribute("users", user);

        model.addAttribute("user", user1);


        return "delete_user";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id") int id) {
        ModelAndView userForm = new ModelAndView("edit_user");

        User user = userService.userById(id);

        userForm.addObject("user", user);

        return userForm;
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.save(user);

        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {


        userService.deleteById(id);

        return "delete";

    }

    @RequestMapping("/activate/{id}")
    public String activateUser(@PathVariable(name = "id") int id) {


        userService.saveActivate(id);

        return "redirect:/";

    }
*/


}