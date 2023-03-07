package com.example.guke.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.guke.entity.Hospitalization;
import com.example.guke.service.HospitalizationService;
import com.example.guke.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HospitalizationController {
    @Resource
    HospitalizationService hospitalizationService;
    @Resource
    PatientService patientService;
    @RequestMapping("/admin/hospitalizationManage")
    public String hospitalizationManage(HttpServletRequest request,@RequestParam(value = "patientname",required = false)String patientname,@RequestParam(value = "intime",required = false)String intime){
        request.setAttribute("patientname",patientname);
        request.setAttribute("intime",intime);
        request.setAttribute("hospitalizations",hospitalizationService.getAllHospitalizations(patientname,intime));
        return "admin/hospitalizationManage";
    }
    @RequestMapping("/admin/hospitalizationAdd")
    public String hospitalizationAddPage(HttpServletRequest request){
        request.setAttribute("patients",patientService.getAllPatients());
        return"admin/add/hospitalizationadd";
    }
    @RequestMapping(value = "/admin/hospitalization",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject hospitalizationAdd(@RequestBody Hospitalization hospitalization){
        JSONObject json=new JSONObject();
        json.put("message",hospitalizationService.AddHospitalization(hospitalization));
        return json;
    }
    @RequestMapping(value = "/admin/hospitalization/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delHospitalization(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",hospitalizationService.deleteHospitalization(id));
        return json;
    }
    @RequestMapping(value = "/admin/hospitalization/{id}",method = RequestMethod.GET)
    public String hospitalizationInfo(HttpServletRequest request,@PathVariable Integer id){
        request.setAttribute("h",hospitalizationService.getHospitalization(id));
        request.setAttribute("patients",patientService.getAllPatients());
        return"admin/info/hospitalizationinfo";
    }
    @RequestMapping(value = "/admin/hospitalization",method = RequestMethod.PUT)
    @ResponseBody
    public JSONObject delHospitalization(@RequestBody Hospitalization hospitalization){
        JSONObject json=new JSONObject();
        json.put("message",hospitalizationService.updateHospitalization(hospitalization));
        return json;
    }
}
