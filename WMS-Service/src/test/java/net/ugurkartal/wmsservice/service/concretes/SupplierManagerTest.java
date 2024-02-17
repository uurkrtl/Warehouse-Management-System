package net.ugurkartal.wmsservice.service.concretes;

import net.ugurkartal.wmsservice.core.utilities.mappers.ModelMapperManager;
import net.ugurkartal.wmsservice.models.Supplier;
import net.ugurkartal.wmsservice.repositories.SupplierRepository;
import net.ugurkartal.wmsservice.service.dtos.SupplierDto;
import net.ugurkartal.wmsservice.service.requests.SupplierCreateRequest;
import net.ugurkartal.wmsservice.service.requests.SupplierUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierManagerTest {
    private SupplierRepository supplierRepository;
    private ModelMapperManager modelMapperManager;
    private ModelMapper modelMapper;
    private SupplierManager supplierManager;
    private GenerateIDManager generateIDManager;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        modelMapperManager = mock(ModelMapperManager.class);
        supplierRepository = mock(SupplierRepository.class);
        generateIDManager = mock(GenerateIDManager.class);
        supplierManager = new SupplierManager(supplierRepository, modelMapperManager, generateIDManager);

        when(modelMapperManager.forDto()).thenReturn(modelMapper);
        when(modelMapperManager.forRequest()).thenReturn(modelMapper);
    }

    @Test
    void getAllSuppliersReturnsExpectedList() {
        // GIVEN
        Supplier supplier1 = new Supplier("1", "Supplier 1", "Contact 1", "Email 1", "Phone 1", null, null, true);
        Supplier supplier2 = new Supplier("2", "Supplier 2", "Contact 2", "Email 2", "Phone 2", null, null, true);
        List<Supplier> expected = List.of(supplier1, supplier2);
        when(supplierRepository.findAll()).thenReturn(expected);

        // WHEN
        List<SupplierDto> actual = supplierManager.getAll();

        // THEN
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void getByIdSuppliersReturnsExpectedSupplier() {
        // GIVEN
        Supplier expected = new Supplier("1", "Supplier 1", "Contact 1", "Email 1", "Phone 1", null, null, true);
        when(supplierRepository.findById("1")).thenReturn(Optional.of(expected));

        // WHEN
        SupplierDto actual = supplierManager.getById("1");

        // THEN
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        verify(supplierRepository, times(1)).findById("1");
    }

    @Test
    void addSupplierReturnsNewSupplier() {
        // GIVEN
        Supplier expected = new Supplier("1", "Supplier 1", "Contact 1", "Email 1", "Phone 1", null, null, true);
        SupplierCreateRequest supplierCreateRequest = SupplierCreateRequest.builder().name("Supplier 1").contactName("Contact 1").email("Email 1").phone("Phone 1").build();
        when(generateIDManager.generateSupplierId()).thenReturn("1");
        when(supplierRepository.save(any(Supplier.class))).thenReturn(expected);

        //WHEN
        Supplier actual = supplierManager.add(supplierCreateRequest);

        // THEN
        assertEquals(expected, actual);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    void updateSupplierReturnsUpdatedSupplier() {
        // GIVEN
        Supplier expected = new Supplier("1", "Supplier 1", "Contact 1", "Email 1", "Phone 1", null, null, true);
        SupplierUpdateRequest supplierUpdateRequest = SupplierUpdateRequest.builder().name("Supplier 1").contactName("Contact 1").email("Email 1").phone("Phone 1").build();
        when(supplierRepository.findById("1")).thenReturn(Optional.of(expected));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(expected);

        //WHEN
        Supplier actual = supplierManager.update("1", supplierUpdateRequest);

        // THEN
        assertEquals(expected, actual);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    void deleteCharacterByIdReturnsTrue() {
        // GIVEN
        Supplier supplier = new Supplier("1", "Supplier 1", "Contact 1", "Email 1", "Phone 1", null, null, true);
        when(supplierRepository.findById("1")).thenReturn(Optional.of(supplier));
        doNothing().when(supplierRepository).deleteById("1");

        // WHEN
        boolean actual = supplierManager.deleteById("1");

        // THEN
        assertTrue(actual);
        verify(supplierRepository, times(1)).deleteById("1");
    }
}