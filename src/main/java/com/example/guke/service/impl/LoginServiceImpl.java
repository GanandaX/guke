package com.example.guke.service.impl;

import cn.hutool.core.lang.RegexPool;
import cn.hutool.core.util.StrUtil;
import com.example.guke.common.CommonService;
import com.example.guke.dao.DoctorMapper;
import com.example.guke.dao.LoginMapper;
import com.example.guke.dao.PatientMapper;
import com.example.guke.entity.Doctor;
import com.example.guke.entity.Login;
import com.example.guke.entity.Patient;
import com.example.guke.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginMapper loginMapper;

    @Resource
    PatientMapper patientMapper;
    @Resource
    DoctorMapper doctorMapper;

    @Override
    public List<Login> findAllAdmin(String username) {
        return loginMapper.findAllAdmin(username);
    }

    @Override
    public String addAmin(Login login) {
        String message;
        if(loginMapper.findByUsername(login.getUsername())!=null){
            message= CommonService.add_message_error2;
        }
        else{
            message=loginMapper.insertAdmin(login)>0? CommonService.add_message_success:CommonService.add_message_error;
        }
        return message;
    }

    @Override
    public String updateAdmin(Login login) {
        String message;
        if(loginMapper.findByUsername(login.getUsername())!=null){
            message=CommonService.add_message_error2;
        }
        else{
            message=loginMapper.updateByPrimaryKeySelective(login)>0? CommonService.upd_message_success:CommonService.upd_message_error;
        }
        return message;
    }

    @Override
    public String delAdmin(Integer id) {
        return loginMapper.deleteByPrimaryKey(id)>0?CommonService.del_message_success:CommonService.del_message_error;
    }

    @Override
    public Login getAdmin(Integer id) {
        return loginMapper.selectByPrimaryKey(id);
    }


    @Override
    public String login(Login  login) {
        String message="";
        if(StrUtil.isBlank(login.getUsername())) {
            return "??????????????????";
        }
        Login login2=loginMapper.findByUsername(login.getUsername());
        if(login2!=null){
            String pswd = login2.getPassword();

            if (StrUtil.isBlank(pswd)) {
                return "?????????????????????";
            }
            else{
                login.setId(login2.getId());
                login.setRole(login2.getRole());
                message="????????????"+login2.getRole();
            }
        }
        else{
            message="??????????????????";
        }
        return message;
    }

    @Override
    public String regist(Login login) {
        if (StrUtil.isBlank(login.getUsername())) {
            return "????????????";
        }
        if (StrUtil.isBlank(login.getPassword())) {
            return "????????????";
        }


        String message;
        Doctor doctor=doctorMapper.getDoctorByCertId(login.getCertId());
        Patient patient=patientMapper.findPatientByCertId(login.getCertId());
        if(doctor!=null){
            if(doctor.getLoginid()==null){
                login.setRole(2);
                loginMapper.insert(login);
                doctor.setLoginid(loginMapper.findByUsername(login.getUsername()).getId());
                doctorMapper.updateByPrimaryKeySelective(doctor);
                message="????????????";
            }
            else {
                message="????????????????????????";
            }

        }
        else if(patient!=null){
            if(patient.getLoginid()==null){
                login.setRole(3);
                loginMapper.insert(login);
                patient.setLoginid(loginMapper.findByUsername(login.getUsername()).getId());
                patientMapper.updateByPrimaryKeySelective(patient);
                message="????????????";
            }
            else {
                message="????????????????????????";
            }
        }
        else if(loginMapper.findByUsername(login.getUsername())!=null){
            message="????????????????????????";
        }
        else if(loginMapper.findByUsername(login.getUsername())==null&&(login.getCertId()==null||login.getCertId().trim().equals(""))){
            login.setRole(1);
            loginMapper.insert(login);
            message="????????????";
        }
        else {
            message="????????????????????????????????????????????????????????????";
        }
        return message;
    }

}
