package com.louis.teaSystemService.service.impl;

import com.louis.teaSystemService.mapper.IdentifyModelMapper;
import com.louis.teaSystemService.pojo.IdentifyModel;
import com.louis.teaSystemService.service.IdentifyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Service("identifyModelService")
public class IdentifyModelServiceImpl implements IdentifyModelService {
    @Autowired
    private IdentifyModelMapper identifyModelMapper;
    @Override
    public List<IdentifyModel> getModelAll() {
        return identifyModelMapper.getModelAll();
    }
}
