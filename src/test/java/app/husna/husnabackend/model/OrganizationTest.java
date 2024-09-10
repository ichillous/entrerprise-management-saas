package app.husna.husnabackend.model;

import app.husna.husnabackend.service.OrganizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // This ensures each test runs in its own transaction, which is rolled back after each test
public class OrganizationTest {

    @Autowired
    private OrganizationService organizationService;

    private Organization testOrganization;

    @BeforeEach
    void setUp() {
        testOrganization = Organization.builder()
                .name("Test Organization")
                .email("test@organization.com")
                .password("securepassword")
                .city("Test City")
                .state("Test State")
                .build();

        organizationService.createOrganization(testOrganization);
    }

    @Test
    void testSaveOrganization() {
        Organization savedOrganization = organizationService.createOrganization(testOrganization);

        assertThat(savedOrganization).isNotNull();
        assertThat(savedOrganization.getId()).isNotNull(); // Should have an auto-generated ID
        assertThat(savedOrganization.getName()).isEqualTo("Test Organization");
        assertThat(savedOrganization.getEmail()).isEqualTo("test@organization.com");
    }

    @Test
    void testFindOrganizationById() {
        Optional<Organization> foundOrganization = Optional.ofNullable(organizationService.findOrganizationById(testOrganization.getId()));

        assertThat(foundOrganization).isPresent();
        assertThat(foundOrganization.get().getName()).isEqualTo("Test Organization");
    }

    @Test
    void testUpdateOrganization() {
        testOrganization.setCity("Updated City");
        Organization updatedOrganization = organizationService.updateOrganization(testOrganization);

        assertThat(updatedOrganization.getCity()).isEqualTo("Updated City");
    }

    @Test
    void testDeleteOrganization() {
        organizationService.deleteOrganization(testOrganization.getId());

        Optional<Organization> deletedOrganization = Optional.ofNullable(organizationService.findOrganizationById(testOrganization.getId()));
        assertThat(deletedOrganization).isEmpty();
    }

    @Test
    void testFindOrganizationByEmail() {
        Optional<Organization> foundByEmail = organizationService.findOrganizationByEmail("test@organization.com");

        assertThat(foundByEmail).isPresent();
        assertThat(foundByEmail.get().getName()).isEqualTo("Test Organization");
    }
}