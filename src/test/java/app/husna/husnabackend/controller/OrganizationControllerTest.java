//package app.husna.husnabackend.controller;
//
//import app.husna.husnabackend.model.Organization;
//import app.husna.husnabackend.service.OrganizationService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class OrganizationControllerTest {
//    @InjectMocks
//    private OrganizationController organizationController;
//
//    @Mock
//    private OrganizationService organizationService;
//
//    private AutoCloseable closeable;
//
//    @BeforeEach
//    void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close();
//    }
//
//    @Test
//    void saveOrganization() {
//        Organization organization = new Organization();
//        organization.setName("Example Org");
//
//        when(organizationService.createOrganization(organization)).thenReturn(organization);
//
//        ResponseEntity<Organization> response = organizationController.createOrganization(organization);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void getOrganizationById() {
//        Long organizationId = 1L;
//        Organization organization = new Organization();
//        organization.setId(organizationId);
//        organization.setName("Example Org");
//
//        when(organizationService.findOrganizationById(organizationId));
//
//        Organization response = organizationController.findOrganizationById(organizationId);
//
//        assertEquals(HttpStatus.OK, response);
//        assertEquals(organization, response);
//    }
//
//    @Test
//    void getOrganizationByIdNotFound() {
//        Long organizationId = 1L;
//        when(organizationService.getOrganizationById(organizationId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Organization> response = organizationController.getOrganizationById(organizationId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void getAllOrganizations() {
//        List<Organization> organizations = Arrays.asList(
//                new Organization(),
//                new Organization()
//        );
//
//        when(organizationService.getAllOrganizations()).thenReturn(organizations);
//
//        ResponseEntity<List<Organization>> response = organizationController.getAllOrganizations();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organizations, response.getBody());
//    }
//
//    @Test
//    void updateOrganization() {
//        Long organizationId = 1L;
//        Organization organization = new Organization();
//        organization.setId(organizationId);
//        organization.setName("Updated Org");
//
//        when(organizationService.getOrganizationById(organizationId)).thenReturn(Optional.of(new Organization()));
//        when(organizationService.updateOrganization(organization)).thenReturn(organization);
//
//        ResponseEntity<Organization> response = organizationController.updateOrganization(organizationId, organization);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void updateOrganizationNotFound() {
//        Long organizationId = 1L;
//        Organization organization = new Organization();
//        organization.setId(organizationId);
//
//        when(organizationService.getOrganizationById(organizationId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Organization> response = organizationController.updateOrganization(organizationId, organization);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void deleteOrganization() {
//        Long organizationId = 1L;
//        when(organizationService.getOrganizationById(organizationId)).thenReturn(Optional.of(new Organization()));
//
//        ResponseEntity<Void> response = organizationController.deleteOrganization(organizationId);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(organizationService, times(1)).deleteOrganization(organizationId);
//    }
//
//    @Test
//    void deleteOrganizationNotFound() {
//        Long organizationId = 1L;
//        when(organizationService.getOrganizationById(organizationId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Void> response = organizationController.deleteOrganization(organizationId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void getOrganizationByEmail() {
//        String email = "test@example.com";
//        Organization organization = new Organization();
//        organization.setEmail(email);
//
//        when(organizationService.getOrganizationByEmail(email)).thenReturn(Optional.of(organization));
//
//        ResponseEntity<Organization> response = organizationController.getOrganizationByEmail(email);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void getOrganizationByCity() {
//        String city = "New York";
//        Organization organization = new Organization();
//        organization.setCity(city);
//
//        when(organizationService.getOrganizationByCity(city)).thenReturn(Optional.of(organization));
//
//        ResponseEntity<Organization> response = organizationController.getOrganizationByCity(city);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void getOrganizationByState() {
//        String state = "California";
//        Organization organization = new Organization();
//        organization.setState(state);
//
//        when(organizationService.getOrganizationByState(state)).thenReturn(Optional.of(organization));
//
//        ResponseEntity<Organization> response = organizationController.getOrganizationByState(state);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void getOrganizationByName() {
//        String name = "Example Org";
//        Organization organization = new Organization();
//        organization.setName(name);
//
//        when(organizationService.getOrganizationByName(name)).thenReturn(Optional.of(organization));
//
//        ResponseEntity<Organization> response = organizationController.getOrganizationByName(name);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organization, response.getBody());
//    }
//
//    @Test
//    void getAllOrganizationsOrderedByIdDesc() {
//        List<Organization> organizations = Arrays.asList(
//                new Organization(),
//                new Organization()
//        );
//
//        when(organizationService.findAllByOrderByIdDesc()).thenReturn(organizations);
//
//        ResponseEntity<List<Organization>> response = organizationController.getAllOrganizationsOrderedByIdDesc();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organizations, response.getBody());
//    }
//
//    @Test
//    void getAllOrganizationsOrderedByNameAsc() {
//        List<Organization> organizations = Arrays.asList(
//                new Organization(),
//                new Organization()
//        );
//
//        when(organizationService.getAllOrganizationsOrderedByNameAsc()).thenReturn(organizations);
//
//        ResponseEntity<List<Organization>> response = organizationController.getAllOrganizationsOrderedByNameAsc();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(organizations, response.getBody());
//    }
//
//    @Test
//    void existsByEmail() {
//        String email = "test@example.com";
//        when(organizationService.existsByEmail(email)).thenReturn(true);
//
//        ResponseEntity<Boolean> response = organizationController.existsByEmail(email);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody());
//    }
//}