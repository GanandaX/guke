package com.example.guke.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.guke.entity.Appointment;
import com.example.guke.entity.Hospitalization;
import com.example.guke.entity.Login;
import com.example.guke.entity.Patient;
import com.example.guke.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PatientController {
    @Resource
    PatientService patientService;
    @Resource
    DoctorService doctorService;
    @Resource
    AppointmentService appointmentService;
    @Resource
    HospitalizationService hospitalizationService;
    @Resource
    MedicalhistoryService medicalhistoryService;


    @RequestMapping("/admin/patientManage")
    public String patientlist(HttpServletRequest request, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "certId", required = false) String certId) {
        request.setAttribute("name", name);
        request.setAttribute("certId", certId);
        request.setAttribute("patients", patientService.getAllPatients(name, certId));
        return "admin/patientManage";
    }

    @RequestMapping(value = "/admin/patient/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delPatient(@PathVariable Integer id) {
        JSONObject json = new JSONObject();
        json.put("message", patientService.delPatient(id));
        return json;
    }

    @RequestMapping(value = "/admin/patient/{id}", method = RequestMethod.GET)
    public String patientInfo(@PathVariable Integer id, HttpServletRequest request) {
        request.setAttribute("patient", patientService.getPatient(id));
        request.setAttribute("appointments", appointmentService.getPatientMessage(id));
        request.setAttribute("hospitalizations", hospitalizationService.getPatientMessage(id));
        request.setAttribute("doctors", doctorService.getAllDoctor());
        return "admin/info/patientinfo";
    }

    @RequestMapping(value = "/admin/patientAdd", method = RequestMethod.GET)
    public String patientAddPage() {
        return "admin/add/patientadd";
    }

    @RequestMapping(value = "/admin/patient", method = RequestMethod.PUT)
    @ResponseBody
    public JSONObject patientInfo(@RequestBody Patient patient) {
        JSONObject json = new JSONObject();
        json.put("message", patientService.updatePatient(patient));
        return json;
    }

    @RequestMapping(value = "/admin/patient", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject delPatient(@RequestBody Patient patient) {
        JSONObject json = new JSONObject();
        json.put("message", patientService.addPatient(patient));
        return json;
    }

    @RequestMapping(value = "/patient/appointment")
    public String appointmentInfo(HttpServletRequest request, HttpSession session) {
        Login login = (Login) session.getAttribute("login");
        Patient patient = patientService.findPatientByLoginId(login.getId());
        request.setAttribute("patientid", patient.getId());
        request.setAttribute("doctors", doctorService.getAllDoctor());
        return "patient/appointment";
    }

    @ResponseBody
    @RequestMapping("/patient/addAppointment")
    public JSONObject patientAddAppointment(@RequestBody Appointment appointment) {
        String str = appointmentService.addAppointment(appointment);
        JSONObject json = new JSONObject();
        json.put("message", str);
        return json;
    }

    @RequestMapping(value = "/patient/search", method = RequestMethod.GET)
    public String search() {
        return "/patient/search";
    }

    @RequestMapping(value = "/patient/searchinfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchinfo(@RequestParam("name") String name, @RequestParam("type") String type) {
        JSONObject json = new JSONObject();
        json.put("map", patientService.serrchInfo(name, type));
        return json;
    }

    @RequestMapping(value = "/patient/medicalhistory")
    public String medicalhistory(HttpSession session, HttpServletRequest request) {
        Login login = (Login) session.getAttribute("login");
        Patient patient = patientService.findPatientByLoginId(login.getId());
        request.setAttribute("medicalhistorys", medicalhistoryService.getMedicalhistoryByPatientId(patient.getId()));
        return "patient/medicalhistory";
    }


    @RequestMapping(value = "/patient/hospitalization")
    public String hospitalization(HttpSession session, HttpServletRequest request) {
        Login login = (Login) session.getAttribute("login");
        Patient patient = patientService.findPatientByLoginId(login.getId());
        request.setAttribute("theLast", hospitalizationService.findTheLastHospitalization(patient.getHospitalizationid()));
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setPatientid(patient.getId());
        hospitalization.setId(patient.getHospitalizationid());
        request.setAttribute("others", hospitalizationService.findOtherHospitalization(hospitalization));
        return "patient/hospitalization";
    }

    @RequestMapping(value = "/patient/getBookingFee/{doctorId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBookingFee(@PathVariable Integer doctorId) {
        JSONObject json = new JSONObject();
        Integer message = doctorService.getBookingFee(doctorId);
        json.put("message", message);
        return json;
    }

    @RequestMapping(value = "/hospital/{view}")
    public String test(@PathVariable String view) {
        return "patient/" + view;
    }
}
