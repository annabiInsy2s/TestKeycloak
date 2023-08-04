package com.TestKeycloak.TestKeycloak.service;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
//    private final KeycloakConfig keycloak;
//    public static final String realm = "KeyClock-INSY2S";
//
//
//    public UserService( KeycloakConfig keycloak) {
//        this.keycloak = keycloak;
//    }
//    public List<UserRepresentation> getAllUsers() {
//        return keycloak.keycloakAdminClient().realm(realm) // Replace with your actual realm name
//                .users().list();
//    }
//

//public void addUser(User user) {
//        UsersResource usersResource = keycloakConfig.keycloakAdminClient().realm(keycloakConfig.realm).users();
//        System.out.println(("usersResource"+usersResource));
//        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());
//
//        UserRepresentation kcUser = new UserRepresentation();
//        kcUser.setUsername(user.getEmail());
//        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
//        kcUser.setFirstName(user.getFirstName());
//        kcUser.setLastName(user.getLastName());
//        kcUser.setEmail(user.getEmail());
//        kcUser.setEnabled(true);
//        kcUser.setEmailVerified(false);
//        usersResource.create(kcUser);

//    }

//    private static CredentialRepresentation createPasswordCredentials(String password) {
//        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
//        passwordCredentials.setTemporary(false);
//        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
//        passwordCredentials.setValue(password);
//        return passwordCredentials;
//    }
}
