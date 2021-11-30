package com.louis.teaSystemService.service.impl;

import com.louis.teaSystemService.mapper.ModelMapper;
import com.louis.teaSystemService.pojo.Model;
import com.louis.teaSystemService.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Service("modelService")
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Model> getModelAll() {
        return modelMapper.getModelAll();
    }
}
