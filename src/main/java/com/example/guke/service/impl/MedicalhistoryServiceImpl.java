package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.DoctorMapper;
import com.example.guke.dao.MedicalhistoryMapper;
import com.example.guke.dao.PatientMapper;
import com.example.guke.entity.Medicalhistory;
import com.example.guke.service.MedicalhistoryService;
import com.example.guke.uitls.PatientDoctorutils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MedicalhistoryServiceImpl implements MedicalhistoryService {
    @Resource
    MedicalhistoryMapper medicalhistoryMapper;
    @Resource
    DoctorMapper doctorMapper;
    @Resource
    PatientMapper patientMapper;
    @Override
    public List<Medicalhistory> getAllMedicalhistorys(String doctorname, String patientname) {
        Map map= PatientDoctorutils.getDoctorIdsAndPatientIds(doctorname,doctorMapper,patientname,patientMapper);
        return medicalhistoryMapper.findAll((List)map.get("doctorids"),(List)map.get("patientids"));
    }

    @Override
    public String delMedicalhistory(Integer id) {
        return medicalhistoryMapper.deleteByPrimaryKey(id)>0? CommonService.del_message_success:CommonService.del_message_error;
    }

    @Override
    public Medicalhistory getMedicalhistory(Integer id) {
        return medicalhistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public String UpdateMedicalhistory(Medicalhistory medicalhistory) {
        return medicalhistoryMapper.updateByPrimaryKey(medicalhistory)>0?CommonService.upd_message_success:CommonService.upd_message_error;
    }

    @Override
    public String addMedicalhistory(Medicalhistory medicalhistory) {
        return medicalhistoryMapper.insert(medicalhistory)>0?CommonService.add_message_success:CommonService.add_message_error;
    }

    @Override
    public List<Medicalhistory> getMedicalhistoryByPatientId(Integer id) {
        return medicalhistoryMapper.selectByPatientId(id);
    }
}
