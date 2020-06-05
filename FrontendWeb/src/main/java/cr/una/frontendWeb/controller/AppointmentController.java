package cr.una.frontendWeb.controller;

import cr.una.frontendWeb.model.Patient;
import cr.una.frontendWeb.sevice.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:18 a. m.
 */
public class AppointmentController {
    private Service service = new Service();

    @GetMapping("/appointments")
    public String appointments(Model model)  {
        List<Patient> appointments = null;
        try {
            appointments = service.allAppointments();
            model.addAttribute("appointments", appointments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "appointments";
    }
}
