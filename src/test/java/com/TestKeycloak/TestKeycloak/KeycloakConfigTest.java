package com.TestKeycloak.TestKeycloak;

import jakarta.annotation.*;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class KeycloakConfigTest {


    Keycloak keycloak;
    private static final String serverUrl = "http://localhost:8080";
    public static final String realm = "KeyClock-INSY2S";
    private static final String clientId = "KeyCloakAuthService";
    private static final String clientSecret = "7eWrSii59Ye7xatevFZTQqeefq3sXkLy";
    private static final String userName = "insy2s";
    private static final String password = "insy2s";

    @PostConstruct
    public void initKeycloak() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientSecret(clientSecret)
                .username(userName)
                .password(password)
                .clientId(clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    @PreDestroy
    public void closeKeycloak() {
        keycloak.close();
    }
@Test
    public  void loginUser() {
        String username="azaiez";
        String password="123";

    // Configure Keycloak admin client
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .build();


        // Perform the login and obtain an access token
        try {
             keycloak.tokenManager().grantToken();
            System.out.println("AccessToken from login "+ keycloak.tokenManager().getAccessToken().getToken());
            System.out.println("refreshToken from login "+ keycloak.tokenManager().refreshToken().getToken());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testKeycloakConfiguration() {
        System.out.println("Testing Keycloak Configuration...");
        AccessTokenResponse token= keycloak.tokenManager().getAccessToken();
        System.out.println("token "+token.getToken());
         List<UserRepresentation> users=keycloak.realm(realm).users().list();
        for (UserRepresentation user : users) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Enabled: " + user.isEnabled());
            List<String> realmRoles = user.getRealmRoles();
            if (realmRoles != null && !realmRoles.isEmpty()) {
                System.out.println("Realm Roles: " + realmRoles);
            } else {
                System.out.println("No realm roles found for this user.");
            }

            System.out.println("-----------------------------------");
        }

        UserRepresentation newUser = new UserRepresentation();
        newUser.setUsername("azaiez");
        newUser.setEmail("azaiez@gmail.com");
        newUser.setFirstName("azaiez");
        newUser.setLastName("azaiez");
        newUser.setEnabled(true);

        // Set user credentials (password)
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue("123"); // Set the desired password
        newUser.setCredentials(List.of(credentials));
        Response response = keycloak.realm(realm).users().create(newUser);

        // Assign the ADMIN role to the user
        List<UserRepresentation> usersS = keycloak.realm(realm).users().search("azaiez");
        RoleRepresentation roleS = keycloak.realm(realm).roles().get("CONSULTANT").toRepresentation();
        System.out.println("roles details"+ roleS.getId());

        if (!usersS.isEmpty()) {
            System.out.println();
            String userId = users.get(0).getId();

            // Assign the ADMIN role to the user
            try{
                System.out.println("user"+users.get(0).getUsername());
                keycloak.realm(realm).users().get(userId).roles().realmLevel()
                        .add(Arrays.asList(roleS));
                System.out.println("User assigned CONSULTANT role.");

            }catch (Exception e)
            {
                System.out.println("error ");
            }

        } else {
            System.out.println("User with username " + "newuser" + " not found.");
        }        // Create the user

        System.out.println("User created successfully.");
    }
    private static String extractUserIdFromResponse(Response response) {
        String locationHeader = response.getHeaderString("Location");
        if (locationHeader != null) {
            String[] parts = locationHeader.split("/");
            return parts[parts.length - 1];
        }
        return null;
    }
    }
