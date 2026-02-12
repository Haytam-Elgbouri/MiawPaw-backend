package com.yousra.miawpaw.security.services.IMPL;

import com.yousra.miawpaw.security.exceptions.EntityNotFoundException;
import com.yousra.miawpaw.security.exceptions.UserAllReadyExistException;
import com.yousra.miawpaw.security.mappers.UserMapper;
import com.yousra.miawpaw.security.models.dtos.ChangeUserStatus;
import com.yousra.miawpaw.security.models.dtos.CreateUserDto;
import com.yousra.miawpaw.security.models.dtos.UpdateUserDto;
import com.yousra.miawpaw.security.models.dtos.UserDto;
import com.yousra.miawpaw.security.models.entities.DeletedUser;
import com.yousra.miawpaw.security.models.entities.User;
import com.yousra.miawpaw.security.models.enums.Role;
import com.yousra.miawpaw.security.repositories.DeletedUserRepository;
import com.yousra.miawpaw.security.repositories.UserRepository;
import com.yousra.miawpaw.security.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final DeletedUserRepository deletedUserRepository;

    @Override
    public void changeUserStatus(Long id, ChangeUserStatus changeUserStatus) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        user.setIsActive(changeUserStatus.isActive());
        repository.save(user);
    }

    private boolean checkIfUserExist(String email) {
        return repository.findByEmail(email).isPresent();
    }


    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User", email));
    }

    private void verifyUserAccess() {
        User currentUser = getCurrentAuthenticatedUser();
        if (!currentUser.getRole().equals(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("You can only manage your own account");
        }
    }

    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getAuthenticatedUser();
        }

        throw new AccessDeniedException("User not properly authenticated");
    }


    @Override
    public UserDto create(CreateUserDto dto) {
        if (checkIfUserExist(dto.email())) {
            throw new UserAllReadyExistException("User with this email " + dto.email() + " already exist");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(dto.password());
        User user = mapper.toEntity(dto);
        user.setPassword(hashedPassword);
        user.setIsActive(false);
        User createdUser = repository.save(user);
        return mapper.toDto(createdUser);
    }

    @Override
    @Transactional
    public UserDto update(UpdateUserDto updateProfileDto, Long id) {
        verifyUserAccess();
        User profile = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        Optional<User> existingUser = repository.findByEmail(updateProfileDto.email());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new UserAllReadyExistException("User with this email " + updateProfileDto.email() + " already exists");
        }

        if (updateProfileDto.phone() != null) profile.setPhone(updateProfileDto.phone());
        if (updateProfileDto.email() != null) profile.setEmail(updateProfileDto.email());
        if (updateProfileDto.role() != null) profile.setRole(updateProfileDto.role());
        if (updateProfileDto.CIN() != null) profile.setCIN(updateProfileDto.CIN());
        if (updateProfileDto.firstName() != null) profile.setFirstName(updateProfileDto.firstName());
        if (updateProfileDto.lastName() != null) profile.setLastName(updateProfileDto.lastName());

        return mapper.toDto(profile);
    }


    @Override
    public Page<UserDto> findAll(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return repository.findAllByIdNot(1L, pageable)
                .map(mapper::toDto);
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .filter(user -> user.getId() > 1)
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public UserDto findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        return mapper.toDto(user);
    }

//    @Override
//    public void delete(Long id) {
//        User user = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User", id));
//        repository.delete(user);
//    }

@Transactional
@Override
public void delete(Long id) {
    User user = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User", id));

    // Map User to DeletedUser
    DeletedUser deletedUser = new DeletedUser();
    deletedUser.setId(user.getId());
    deletedUser.setFirstName(user.getFirstName());
    deletedUser.setLastName(user.getLastName());
    deletedUser.setEmail(user.getEmail());
    deletedUser.setCIN(user.getCIN());
    deletedUser.setPhone(user.getPhone());
    deletedUser.setDeletedAt(LocalDateTime.now());

    // Save to deleted_users table
    deletedUserRepository.save(deletedUser);

    // Delete from users table
    repository.delete(user);
}

}
