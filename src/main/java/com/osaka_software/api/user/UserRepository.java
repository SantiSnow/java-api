package com.osaka_software.api.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findById(Long id);

    public Optional<User> findByEmail(String email);

    public List<User> findAll();

    public User save(User user);

    public void deleteById(Long id);
}
