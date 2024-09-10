package app.husna.husnabackend.service;

import app.husna.husnabackend.dto.request.SignUpRequest;
import app.husna.husnabackend.dto.request.SigninRequest;
import app.husna.husnabackend.dto.response.JwtAuthenticationResponse;
import app.husna.husnabackend.model.Organization;
import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var organization = Organization.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .adminRole(Role.ORG_ADMIN)
                .build();
        organizationRepository.save(organization);
        var jwt = jwtService.generateToken(organization);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var organization = organizationRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(organization);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
