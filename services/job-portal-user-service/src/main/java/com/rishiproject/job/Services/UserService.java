package com.rishiproject.job.Services;

import com.rishiproject.job.Modals.Mapper.UserMapper;
import com.rishiproject.job.Modals.Users.UpdatedUserProfileRequest;
import com.rishiproject.job.Modals.Users.User;
import com.rishiproject.job.Repositories.IUserRepository;
import com.rishiproject.job.Services.Interfaces.IUserService;
import com.rishiproject.job.domain.UserStatus;
import com.rishiproject.job.dto.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User with this email not found");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(
                ()->new Exception("User with this id not found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdatedUserProfileRequest req) throws Exception {
        User currentUser = getUserByEmail(email);

        currentUser.setFullName(req.getFullName());
        currentUser.setPhone(req.getPhone());
        currentUser.setProfileImage(req.getProfileImage());
        currentUser.setUpdatedAt(LocalDateTime.now()); // updating timestamp

        userRepository.save(currentUser);
        return UserMapper.mapToDTO(currentUser);
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());

        userRepository.save(user);
        return UserMapper.mapToDTO(user);
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);

        userRepository.save(user);
        return UserMapper.mapToDTO(user);
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
        return UserMapper.mapToDTO(user);
    }
}
