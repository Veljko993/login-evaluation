package eco.login.evaluation.service.impl;

import eco.login.evaluation.dao.entity.Vehicle;
import eco.login.evaluation.dao.repository.VehicleDao;
import eco.login.evaluation.exception.ValidationException;
import eco.login.evaluation.model.CombineData;
import eco.login.evaluation.model.TractorData;
import eco.login.evaluation.service.PropertyDefinitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmptyTest {

    @Mock
    private PropertyDefinitionService definitionService;
    @Mock
    private VehicleDao vehicleDao;
    private TelemetryServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new TelemetryServiceImpl(vehicleDao, definitionService);
    }





}
