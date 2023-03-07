package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.SeekMapper;
import com.example.guke.entity.Seek;
import com.example.guke.service.SeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeekServiceImpl implements SeekService {
    @Resource
    SeekMapper seekMapper;
    @Override
    public String addSeek(Seek seek) {
        return seekMapper.insert(seek)>0? CommonService.add_message_success:CommonService.add_message_error;
    }

    @Override
    public Seek getSeekByPatientId(Integer patientid) {
        return seekMapper.getSeekByPatientId(patientid);
    }
}
