package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.DrugsMapper;
import com.example.guke.entity.Drugs;
import com.example.guke.service.DrugsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DrugsServiceImpl implements DrugsService {
    @Resource
    DrugsMapper drugsMapper;

    @Override
    public List<Drugs> getAllDrugs() {
        return drugsMapper.findAll(null);
    }

    @Override
    public List<Drugs> getAllDrugs(Drugs drugs) {
        return drugsMapper.findAll(drugs);
    }

    @Override
    public String delDrug(Integer id) {
        return drugsMapper.deleteByPrimaryKey(id)>0? CommonService.del_message_success:CommonService.del_message_error;
    }

    @Override
    public String addDrug(Drugs drugs) {
        String message;
        if(drugsMapper.findByName(drugs.getName())!=null){
            message= CommonService.add_message_error2;
        }
        else{
            message=drugsMapper.insert(drugs)>0?CommonService.add_message_success:CommonService.add_message_error;
        }
        return message;
    }

    @Override
    public Drugs getDrug(Integer id) {
        return drugsMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateDrug(Drugs drugs) {
        return drugsMapper.updateByPrimaryKey(drugs)>0?CommonService.upd_message_success:CommonService.upd_message_error;
    }
}
