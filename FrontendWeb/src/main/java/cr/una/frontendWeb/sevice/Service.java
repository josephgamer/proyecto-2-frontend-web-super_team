package cr.una.frontendWeb.sevice;

import cr.una.frontendWeb.Constants;
import cr.una.frontendWeb.model.Patient;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:03 a. m.
 */
public class Service {
    private static final String REST_CITAS = Constants.WS_ENDPOINT.concat("citas");
    private static final String REST_CITAS_CONFIRM = Constants.WS_ENDPOINT.concat("citas/confirmar");
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

    public Patient searchAppointment(int numCita) throws Exception {
        List<Patient> appointments = allAppointments();
        for (Patient patient : appointments) {
            if (patient.getAppointment().getNumCita() == numCita) {
                return patient;
            }
        }
        return null;
    }

    public void updateAppointment(int numCita) {
        boolean result = false;
        result = client.target(REST_CITAS_CONFIRM).path(Integer.toString(numCita)).request(MediaType.APPLICATION_JSON)
                .get(boolean.class);
    }
}
