package com.example.guke.service.impl;

import com.example.guke.common.CommonService;
import com.example.guke.dao.OptionMapper;
import com.example.guke.entity.Option;
import com.example.guke.service.OptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class OptionServiceImpl implements OptionService {
    @Resource
    OptionMapper optionMapper;
    @Override
    public List<Option> getAll() {
        return optionMapper.selectAll();
    }

    @Override
    public String insert(Option option) {
        return optionMapper.insert(option)>0? CommonService.add_message_success:CommonService.add_message_error;
    }

    @Override
    public String deleteOption(Integer id) {
        return optionMapper.deleteById(id)>0?CommonService.del_message_success:CommonService.del_message_error;
    }

    @Override
    public String updateBOption(Option option) {
        return optionMapper.updateById(option)>0?CommonService.upd_message_success:CommonService.upd_message_error;
    }

    @Override
    public Option getOption(Integer id) {
        return optionMapper.getOption(id);
    }
}
