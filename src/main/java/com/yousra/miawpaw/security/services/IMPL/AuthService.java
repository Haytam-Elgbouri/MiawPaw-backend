    package com.yousra.miawpaw.security.services.IMPL;

    import com.yousra.miawpaw.security.exceptions.OldPasswordIncorrectException;
    import com.yousra.miawpaw.security.exceptions.UserIsActiveException;
    import com.yousra.miawpaw.security.exceptions.UserProfileNotFound;
    import com.yousra.miawpaw.security.mappers.UserMapper;
    import com.yousra.miawpaw.security.models.dtos.JwtResponseDto;
    import com.yousra.miawpaw.security.models.dtos.LoginRequestDto;
    import com.yousra.miawpaw.security.models.dtos.UserDto;
    import com.yousra.miawpaw.security.models.entities.User;
    import com.yousra.miawpaw.security.repositories.UserRepository;
    import com.yousra.miawpaw.security.services.IAuthService;
    import com.yousra.miawpaw.security.utils.JwtUtils;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    
    @Service
    @RequiredArgsConstructor
    public class AuthService implements IAuthService {
        private final UserRepository repository;
        private final UserMapper mapper;
        private final AuthenticationManager authenticationManager;
        private final JwtUtils jwtUtils;
        private final PasswordEncoder passwordEncoder;

        @Override
        public JwtResponseDto login(LoginRequestDto dto) {
            Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
            );

            User user = getUserByEmail(dto.email());

            if (!user.getIsActive()){
                throw new UserIsActiveException(dto.email());
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);
            return new JwtResponseDto(token, user.getId(), dto.email(), user.getRole());
        }

        @Override
        public UserDto getAuthenticatedUserProfile() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();

                if (principal instanceof UserDetailsImpl userDetails) {
                    User authenticatedUser = getUserByEmail(userDetails.getUsername());
                    return mapper.toDto(authenticatedUser);
                } else {
                    throw new UserProfileNotFound("Authenticated user not found or not recognized.");
                }
            } else {
                throw new UserProfileNotFound("No authenticated user found.");
            }
        }

        private User getAuthenticatedUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();

                if (principal instanceof UserDetailsImpl userDetails) {
                    return getUserByEmail(userDetails.getUsername());
                } else {
                    throw new UserProfileNotFound("Authenticated user not found or not recognized.");
                }
            } else {
                throw new UserProfileNotFound("No authenticated user found.");
            }
        }

        @Override
        public void changePassword(String oldPassword, String newPassword) {
            User user = getAuthenticatedUser();

            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new OldPasswordIncorrectException("Old password is incorrect");
            }

            if (oldPassword.matches(newPassword)){
                throw new OldPasswordIncorrectException("old password and new password are the same");
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
        }

        private User getUserByEmail(String email) {
            return repository.findByEmail(email)
                    .orElseThrow(() -> new UserProfileNotFound("This email <" + email + "> is incorrect."));
        }
    }