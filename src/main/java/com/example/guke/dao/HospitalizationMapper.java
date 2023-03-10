package com.example.guke.dao;

import com.example.guke.entity.Hospitalization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospitalizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hospitalization record);

    int insertSelective(Hospitalization record);

    Hospitalization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hospitalization record);

    int updateByPrimaryKey(Hospitalization record);
    List<Hospitalization> findAll(List<Integer> patientids, String intime);
    List<Hospitalization> selectByPatientId(@Param("patientid")Integer patientId);
    Hospitalization findTheLastHospitalization(@Param("id")Integer id);
    List<Hospitalization> findOtherHospitalization(Hospitalization record);
}