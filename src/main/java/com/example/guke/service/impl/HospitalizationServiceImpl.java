package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.HospitalizationMapper;
import com.example.guke.dao.PatientMapper;
import com.example.guke.entity.Hospitalization;
import com.example.guke.service.HospitalizationService;
import com.example.guke.uitls.PatientDoctorutils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HospitalizationServiceImpl implements HospitalizationService {
    @Resource
    HospitalizationMapper hospitalizationMapper;
    @Resource
    PatientMapper patientMapper;
    @Override
    public List<Hospitalization> getAllHospitalizations(String patientname, String intime) {
        List<Integer> patientIds= PatientDoctorutils.getPatientIds(patientname,patientMapper);
        return hospitalizationMapper.findAll(patientIds,intime);
    }

    @Override
    public List<Hospitalization> getAllHospitalizations() {
        return hospitalizationMapper.findAll(null,null);
    }

    @Override
    public String AddHospitalization(Hospitalization hospitalization) {
        return hospitalizationMapper.insert(hospitalization)>0? CommonService.add_message_success:CommonService.add_message_error;
    }

    @Override
    public String deleteHospitalization(Integer id) {
        return hospitalizationMapper.deleteByPrimaryKey(id)>0?CommonService.del_message_success:CommonService.add_message_error;
    }

    @Override
    public Hospitalization getHospitalization(Integer id) {
        return hospitalizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateHospitalization(Hospitalization hospitalization) {
        return hospitalizationMapper.updateByPrimaryKey(hospitalization)>0? CommonService.upd_message_success:CommonService.upd_message_error;
    }

    @Override
    public List<Hospitalization> getPatientMessage(Integer patientId) {
        return hospitalizationMapper.selectByPatientId(patientId);
    }

    @Override
    public Hospitalization findTheLastHospitalization(Integer id) {
        return hospitalizationMapper.findTheLastHospitalization(id);
    }

    @Override
    public List<Hospitalization> findOtherHospitalization(Hospitalization hospitalization) {
        return hospitalizationMapper.findOtherHospitalization(hospitalization);
    }
}
