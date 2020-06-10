import cr.una.frontendWeb.model.Patient;
import cr.una.frontendWeb.sevice.Service;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Esteban
 * @created 05/06/2020 - 10:26 a. m.
 */
public class TestingDao {
    @Test
    public void listarCitas() throws Exception {
        Service service = new Service();
        List<Patient> citas = service.allAppointments();
        assertNotNull(citas);
        for (Patient patient : citas) {
            System.out.println(patient.toString());
        }
    }

    @Test
    public void searchAppointment() throws Exception {
        Service service = new Service();
        Patient patient = service.searchAppointment(1);
        assertNotNull(patient);
        System.out.println(patient.toString());
    }
}
