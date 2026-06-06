package com.rishiproject.job.Services.Interfaces;

import com.rishiproject.job.Modals.Payload.AuthResponse;
import com.rishiproject.job.Modals.Payload.LoginRequest;
import com.rishiproject.job.Modals.Payload.SignupRequest;

public interface IAuthService {
    AuthResponse signup(SignupRequest req) throws Exception;
    AuthResponse login(LoginRequest req) throws Exception;
}
