package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.request.UserRequest;
import com.hcl.flight.userservice.model.User;
import com.hcl.flight.userservice.repository.UserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class KeycloakService {
  @Value("${keycloak.realm}")
  private String realm;

  private final Keycloak keycloak;
  private final UserRepository userRepository;

  public KeycloakService(Keycloak keycloak, UserRepository userRepository) {
    this.keycloak = keycloak;
    this.userRepository = userRepository;
  }

  public void createUser(UserRequest user) {
    UserRepresentation userRepresentation = new UserRepresentation();
    userRepresentation.setUsername(user.getEmail());
    userRepresentation.setEmail(user.getEmail());
    userRepresentation.setFirstName(user.getFirstName());
    userRepresentation.setLastName(user.getLastName());
    userRepresentation.setEmail(user.getEmail());
    userRepresentation.setEnabled(true);
    userRepresentation.setEmailVerified(true);

    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
    credentialRepresentation.setType("password");
    credentialRepresentation.setValue(user.getPassword());
    credentialRepresentation.setTemporary(false);
    userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

    Response response = keycloak.realm(realm).users().create(userRepresentation);
  }
}
