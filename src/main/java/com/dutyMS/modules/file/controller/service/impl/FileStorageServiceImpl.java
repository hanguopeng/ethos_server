package com.dutyMS.modules.file.controller.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.utils.DUFileUtil;
import com.dutyMS.modules.file.controller.dao.FileStorageDao;
import com.dutyMS.modules.file.controller.entity.FileStorageEntity;
import com.dutyMS.modules.file.controller.service.FileStorageService;
import com.dutyMS.modules.file.controller.vo.FileVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("fileStorageService")
public class FileStorageServiceImpl extends ServiceImpl<FileStorageDao, FileStorageEntity> implements FileStorageService{
    @Value("${ethos.file.accessoryFilePath}")
    private String accessoryFilePath;
    @Resource
    private FileStorageDao fileStorageDao;

    @Override
    public String fileUpload(MultipartFile[] files, FileVo vo) {
        // 循环上传
        String message = "";
        for (MultipartFile file: files){
            String fileName = file.getOriginalFilename();
            String[] ary= fileName.split("\\.");
            if (ary.length<2) {
                message += fileName+": 格式不符合要求，";
                continue;
            }
            // 先保存一个附件
            FileStorageEntity accessory = new FileStorageEntity();
            accessory.setUploadPerson(vo.getUploadPerson());
            accessory.setSheetInfoId(vo.getSheetInfoId());
            accessory.setUploadTime(new Date());
            fileStorageDao.insert(accessory);
            String name = accessory.getId()+"."+ary[ary.length-1];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(new Date());
            String result = DUFileUtil.uploadingFile(file,accessoryFilePath + "/" + date,name);
            if (StringUtils.isNotBlank(result)){
                message += fileName+": 保存失败，";
                continue;
            }
            accessory.setStoragePath(name);
            fileStorageDao.updateById(accessory);
            /*AccessoryRelationshipEntity accessoryRelationship = new AccessoryRelationshipEntity();
            accessoryRelationship.setAccessoryId(accessory.getAccessoryId());
            accessoryRelationship.setCreateUserId(vo.getUserId());
            accessoryRelationship.setDisplayName(fileName);
            accessoryRelationship.setTableId(vo.getTableId());
            accessoryRelationship.setTableName(vo.getTableName());
            accessoryRelationshipService.insert(accessoryRelationship);*/
        }
        return message;
    }
}
