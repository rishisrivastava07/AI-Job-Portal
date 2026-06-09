package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Models.Payload.AuthResponse;
import com.rishiproject.job.Models.Payload.LoginRequest;
import com.rishiproject.job.Models.Payload.SignupRequest;

public interface IAuthService {
    AuthResponse signup(SignupRequest req) throws Exception;
    AuthResponse login(LoginRequest req) throws Exception;
}
