package cr.una.frontendWeb.controller;

import cr.una.frontendWeb.model.Appointment;
import cr.una.frontendWeb.model.Patient;
import cr.una.frontendWeb.sevice.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:18 a. m.
 */
@Controller
public class AppointmentController {
    private Service service = new Service();

    @GetMapping("/appointments")
    public String appointments(Model model) {
        List<Patient> arrayAppointments = null;
        try {
            arrayAppointments = service.allAppointments();
            model.addAttribute("arrayAppointments", arrayAppointments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "appointments";
    }

    @PostMapping("/appointments")
    public String appointments(Model model, HttpServletRequest httpServletRequest) {
        String filterByName = httpServletRequest.getParameter("nameTxt");
        String filterByNumber = httpServletRequest.getParameter("numberTxt");
        String filterById = httpServletRequest.getParameter("idTxt");
        List<Patient> arrayAppointments = null;
        try {
            if (filterById != "") {
                arrayAppointments = service.searchById(filterById);
            } else {
                if (filterByName != "") {
                    arrayAppointments = service.searchByName(filterByName);
                } else {
                    if (filterByNumber != "") {
                        arrayAppointments = service.searchByNumber(Integer.parseInt(filterByNumber));
                    } else {
                        arrayAppointments = service.allAppointments();
                    }
                }
            }
            model.addAttribute("arrayAppointments", arrayAppointments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "appointments";
    }

    @GetMapping("/edit/{numCita}")
    public String confirm(@PathVariable("numCita") int numCita, Model model) {
        try {
            Patient patient = service.searchAppointment(numCita);
            model.addAttribute("patient", patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "confirm";
    }

    @GetMapping("/confirmar/{numCita}")
    public String confirm(@PathVariable("numCita") int numCita, Patient patient,
                          BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                List<Patient> appointment = service.searchByNumber(numCita);
                patient = appointment.get(0);
                /*Appointment appointment = new Appointment();
                appointment.setNumCita(numCita);
                patient.setAppointment(appointment);*/
                return "confirm";
            }

            service.updateAppointment(numCita);
            List<Patient> arrayAppointments = service.allAppointments();
            model.addAttribute("arrayAppointments", arrayAppointments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "appointments";
    }

    @GetMapping("/confirmar")
    public String confirm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "confirm";
    }
}
