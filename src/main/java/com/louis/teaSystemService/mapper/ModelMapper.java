package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.Model;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Repository
public interface ModelMapper {
    List<Model> getModelAll();
}
