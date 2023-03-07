package com.example.guke.service;

import com.example.guke.entity.Option;

import java.util.List;

public interface OptionService {
    List<Option> getAll();
    Option getOption(Integer id);
    String insert(Option option);
    String deleteOption(Integer id);
    String updateBOption(Option option);
}
