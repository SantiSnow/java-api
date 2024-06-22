package com.osaka_software.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Get all
     *
     * @return
     */
    @GetMapping("")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Find single record
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException();
        }
        return user.get();
    }


    /**
     * Find single record by email
     *
     * @param id
     * @return
     */
    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
        {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    /**
     * Create new record
     *
     * @param user
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody User user,@PathVariable Long id){
        Optional<User> tUser = userRepository.findById(id);
        if(tUser.isEmpty())
        {
            throw new UserNotFoundException();
        }
        User existingUser = tUser.get();
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);
    }

    /**
     * Delete a single record
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }
}
