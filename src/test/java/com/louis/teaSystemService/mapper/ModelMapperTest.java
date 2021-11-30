package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.Model;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:spring/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void getModelAll() {
        List<Model> modelList = modelMapper.getModelAll();
        for(Model model:modelList){
            System.out.println(model);
        }
    }
}