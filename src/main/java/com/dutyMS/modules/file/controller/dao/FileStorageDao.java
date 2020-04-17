package com.dutyMS.modules.file.controller.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.file.controller.entity.FileStorageEntity;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileStorageDao extends BaseMapper<FileStorageEntity> {

}
