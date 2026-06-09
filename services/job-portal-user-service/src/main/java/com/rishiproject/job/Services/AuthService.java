package com.rishiproject.job.Services;

import com.rishiproject.job.Models.Mapper.UserMapper;
import com.rishiproject.job.Models.Payload.AuthResponse;
import com.rishiproject.job.Models.Payload.LoginRequest;
import com.rishiproject.job.Models.Payload.SignupRequest;
import com.rishiproject.job.Models.Users.User;
import com.rishiproject.job.Repositories.IUserRepository;
import com.rishiproject.job.Securities.CustomUserDetailService;
import com.rishiproject.job.Securities.JwtProvider;
import com.rishiproject.job.Services.Interfaces.IAuthService;
import com.rishiproject.job.domain.Enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    public AuthResponse signup(SignupRequest req) throws Exception{

        if(userRepository.existsByEmail(req.getEmail())){
            throw new Exception("Email already registered : " + req.getEmail());
        }

        if(req.getRole() == UserRole.ROLE_ADMIN){
            throw new Exception("Cannot Self registered as a Role Admin");
        }


        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLoggedIn(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        // generating the JWT-Token
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtProvider.generateToken(authentication, savedUser.getId());

        AuthResponse res = new AuthResponse();
        res.setTitle("Welcome " + savedUser.getFullName());
        res.setMessage("Registered Successfully");
        res.setJwt(jwtToken);
        res.setUser(UserMapper.mapToDTO(savedUser));

        return res;
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
       Authentication authentication = userAuthenticate(req.getEmail(), req.getPassword());

       SecurityContextHolder.getContext().setAuthentication(authentication);
       User user = userRepository.findByEmail(req.getEmail());
       String jwtToken = jwtProvider.generateToken(authentication, user.getId());
       user.setLastLoggedIn(LocalDateTime.now());
       userRepository.save(user);

        AuthResponse res = new AuthResponse();
        res.setTitle("Welcome, back " + user.getFullName());
        res.setMessage("LoggedIn Successfully");
        res.setJwt(jwtToken);
        res.setUser(UserMapper.mapToDTO(user));

        return res;
    }

    private Authentication userAuthenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("Password is invalid or incorrect");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
