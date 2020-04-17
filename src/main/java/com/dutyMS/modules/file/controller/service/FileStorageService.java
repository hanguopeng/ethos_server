package com.dutyMS.modules.file.controller.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.modules.file.controller.dao.FileStorageDao;
import com.dutyMS.modules.file.controller.entity.FileStorageEntity;
import com.dutyMS.modules.file.controller.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService extends IService<FileStorageEntity> {

    String fileUpload(MultipartFile[] files, FileVo vo);
}
