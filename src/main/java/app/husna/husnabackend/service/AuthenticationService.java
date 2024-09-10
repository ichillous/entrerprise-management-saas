package app.husna.husnabackend.service;

import app.husna.husnabackend.dto.request.SignUpRequest;
import app.husna.husnabackend.dto.request.SigninRequest;
import app.husna.husnabackend.dto.response.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
