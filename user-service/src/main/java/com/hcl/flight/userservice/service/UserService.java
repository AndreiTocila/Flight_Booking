package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.mapper.UserMapper;
import com.hcl.flight.userservice.model.User;
import com.hcl.flight.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserDTO findById(Long id) {
    Optional<User> user = userRepository.findById(id);
      return user.map(userMapper::entityToDTO).orElse(null);
  }
}
