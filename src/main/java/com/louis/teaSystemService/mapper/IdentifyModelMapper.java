package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.IdentifyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Repository
public interface IdentifyModelMapper {
    List<IdentifyModel> getModelAll();
}
