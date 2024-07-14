package com.nutrimeal.nutrimeal.config.security;

import com.nutrimeal.nutrimeal.model.Role;
import com.nutrimeal.nutrimeal.model.RoleName;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.RoleRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import com.nutrimeal.nutrimeal.service.CustomOAuth2User;
import com.nutrimeal.nutrimeal.service.UserDetailsImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if ("google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email", "").toString();
            String name = attributes.getOrDefault("name", "").toString();
            String picture = attributes.getOrDefault("picture", "").toString();

            User checkUser = userRepository.findByEmail(email).orElse(null);
            if(checkUser != null && checkUser.getPassword() != null){
                SecurityContextHolder.getContext().setAuthentication(null);
                response.sendRedirect("/login?oauth2_error=true");
                return;
            }

            userRepository.findByEmail(email)
                    .ifPresentOrElse(user -> {
                        UserDetailsImpl userDetails = UserDetailsImpl.build(user);
                        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDetails, attributes);
                        Authentication securityAuth = new OAuth2AuthenticationToken(customOAuth2User, customOAuth2User.getAuthorities(),
                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    }, () -> {
                        User user = new User();
                        Set<Role> roles = new HashSet<>();
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Error: Role is not found")));
                        user.setFullName(name);
                        user.setPoint(0);
                        user.setRoles(roles);
                        user.setActive(true);
                        user.setEmail(email);
                        user.setImage(picture);
                        userRepository.save(user);
                        UserDetailsImpl userDetails = UserDetailsImpl.build(user);
                        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDetails, attributes);
                        Authentication securityAuth = new OAuth2AuthenticationToken(customOAuth2User, customOAuth2User.getAuthorities(),
                                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);
                    });
        }
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("http://localhost:8080");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
