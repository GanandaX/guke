package com.example.guke.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.guke.entity.Medicalhistory;
import com.example.guke.service.DoctorService;
import com.example.guke.service.MedicalhistoryService;
import com.example.guke.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MedicalhistoryController {
    @Resource
    PatientService patientService;
    @Resource
    MedicalhistoryService medicalhistoryService;
    @Resource
    DoctorService doctorService;
    @RequestMapping("/admin/medicalhistoryManage")
    public String medicalhistoryManage(HttpServletRequest request,@RequestParam(value = "doctorname",required = false)String doctorname,@RequestParam(value = "patientname",required = false)String patientname){
        request.setAttribute("doctorname",doctorname);
        request.setAttribute("patientname",patientname);
        request.setAttribute("medicalhistorys",medicalhistoryService.getAllMedicalhistorys(doctorname,patientname));
        return "admin/medicalhistoryManage";
    }

    @RequestMapping("/admin/medicalhistoryAdd")
    public String medicalhistoryAddPage(HttpServletRequest request){
        request.setAttribute("doctors",doctorService.getAllDoctor());
        request.setAttribute("patients",patientService.getAllPatients());
        return"admin/add/medicalhistoryadd";
    }

    @RequestMapping(value = "/admin/medicalhistory/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delmedicalhistory(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",medicalhistoryService.delMedicalhistory(id));
        return json;
    }

    @RequestMapping(value = "/admin/medicalhistory/{id}",method = RequestMethod.GET)
    public String medicalhistoryInfo(@PathVariable Integer id,HttpServletRequest request){
        request.setAttribute("patients",patientService.getAllPatients());
        request.setAttribute("doctors",doctorService.getAllDoctor());
        request.setAttribute("medicalhistory",medicalhistoryService.getMedicalhistory(id));
        return "admin/info/medicalhistoryInfo";
    }

    @RequestMapping(value = "/admin/medicalhistory",method = RequestMethod.PUT)
    @ResponseBody
    public JSONObject medicalhistoryUpdate(@RequestBody Medicalhistory medicalhistory){
        JSONObject json=new JSONObject();
        json.put("message",medicalhistoryService.UpdateMedicalhistory(medicalhistory));
        return json;
    }

    @RequestMapping(value = "/admin/medicalhistory",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject medicalhistoryAdd(@RequestBody Medicalhistory medicalhistory){
        JSONObject json=new JSONObject();
        json.put("message",medicalhistoryService.addMedicalhistory(medicalhistory));
        return json;
    }
}
