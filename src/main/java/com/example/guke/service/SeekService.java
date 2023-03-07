package com.example.guke.service;

import com.example.guke.entity.Seek;

public interface SeekService {
    String addSeek(Seek seek);
    Seek getSeekByPatientId(Integer patientid);
}
