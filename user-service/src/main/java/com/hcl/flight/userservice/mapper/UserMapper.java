package com.hcl.flight.userservice.mapper;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserDTO entityToDTO(User user) {
    if (user == null) {
      return null;
    }
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setEmail(user.getEmail());
    userDTO.setAddress(user.getAddress());
    userDTO.setPassword(user.getPassword());
    userDTO.setIban(user.getIban());
    userDTO.setPhoneNumber(user.getPhoneNumber());
    return userDTO;
  }
}
