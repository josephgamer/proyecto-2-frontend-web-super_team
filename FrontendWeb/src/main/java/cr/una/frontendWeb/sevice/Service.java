package cr.una.frontendWeb.sevice;

import cr.una.frontendWeb.Constants;
import cr.una.frontendWeb.model.Patient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:03 a. m.
 */
public class Service {
    private static final String REST_CITAS = Constants.WS_ENDPOINT.concat("citas");
    private Client client = null;

    public Service() {
        client = ClientBuilder.newClient();
    }

    public List<Patient> allAppointments() throws Exception {
        List<Patient> appointments = null;
        appointments = Arrays.asList(client.target(REST_CITAS).request(MediaType.APPLICATION_JSON)
                .get(Patient[].class));
        if (appointments.isEmpty()) {
            throw new Exception();
        }

        return appointments;
    }

    public List<Patient> searchByName(String name) throws Exception {
        List<Patient> result = new ArrayList<>();
        List<Patient> appointments = allAppointments();
        if (!appointments.isEmpty()) {
            for (Patient patient : appointments) {
                if (patient.getFirstName().equals(name)) {
                    result.add(patient);
                }
            }
        }
        return result;
    }

    public List<Patient> searchById(String id) throws Exception {
        List<Patient> result = new ArrayList<>();
        List<Patient> appointments = allAppointments();
        if (!appointments.isEmpty()) {
            for (Patient patient : appointments) {
                if (patient.getId().get$oid().equals(id)) {
                    result.add(patient);
                }
            }
        }
        return result;
    }

    public List<Patient> searchByNumber(int number) throws Exception {
        List<Patient> result = new ArrayList<>();
        List<Patient> appointments = allAppointments();
        if (!appointments.isEmpty()) {
            for (Patient patient : appointments) {
                if (patient.getAppointment().getNumCita() == number) {
                    result.add(patient);
                }
            }
        }
        return result;
    }
}
