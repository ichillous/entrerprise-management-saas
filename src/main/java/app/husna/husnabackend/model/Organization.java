package app.husna.husnabackend.model;

import app.husna.husnabackend.security.UserDetailsBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization implements UserDetailsBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email Required")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(name = "zip_code", length = 20)
    private String zipcode;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50)
    private String timezone;


    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role")
    private Role adminRole;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (adminRole != null) {
            return Collections.singletonList(new SimpleGrantedAuthority(adminRole.getAuthority()));
        }
        return Collections.emptyList(); // Return an empty list if adminRole is null
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
