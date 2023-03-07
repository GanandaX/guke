package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.AppointmentMapper;
import com.example.guke.dao.DoctorMapper;
import com.example.guke.dao.PatientMapper;
import com.example.guke.entity.Appointment;
import com.example.guke.service.AppointmentService;
import com.example.guke.uitls.PatientDoctorutils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Resource
    AppointmentMapper appointmentMapper;
    @Resource
    PatientMapper patientMapper;
    @Resource
    DoctorMapper doctorMapper;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentMapper.findAll(null,null);
    }

    @Override
    public List<Appointment> getAllAppointments(String doctorname, String patientname) {
        Map map= PatientDoctorutils.getDoctorIdsAndPatientIds(doctorname, doctorMapper, patientname, patientMapper);
        return appointmentMapper.findAll((List)map.get("doctorids"),(List)map.get("patientids"));
    }

    @Override
    public String delAppointment(Integer id) {
        return appointmentMapper.deleteByPrimaryKey(id)>0? CommonService.del_message_success:CommonService.del_message_error;
    }

    @Override
    public Appointment getAppointment(Integer id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public String UpdateAppointment(Appointment appointment) {
        return appointmentMapper.updateByPrimaryKey(appointment)>0?CommonService.upd_message_success:CommonService.upd_message_error;
    }

    @Override
    public String addAppointment(Appointment appointment) {
        return appointmentMapper.insert(appointment)>0?CommonService.add_message_success:CommonService.add_message_error;
    }

    @Override
    public List<Appointment> getPatientMessage(Integer patientId) {
        return appointmentMapper.selectByPatientId(patientId);
    }

    @Override
    public List<Appointment> selectByDoctorId(Integer doctorId,String patientname,String time) {
        List<Integer> patientids=PatientDoctorutils.getPatientIds(patientname,patientMapper);
        return appointmentMapper.selectByDoctorId(doctorId,patientids,time);
    }

    @Override
    public Integer selectTheLastAppointment(Integer patientId) {
        return appointmentMapper.selectTheLast(patientId);
    }
}
