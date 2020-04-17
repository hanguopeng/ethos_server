package com.dutyMS.modules.file.controller;

import com.dutyMS.common.utils.R;
import com.dutyMS.modules.file.controller.service.FileStorageService;
import com.dutyMS.modules.file.controller.vo.FileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/file/storage")
@Api(tags = "附件-通用附件管理")
public class FileStorageController {

    @Value("${ethos.file.accessoryFilePath}")
    private String accessoryFilePath;

    @Resource
    private FileStorageService fileStorageService;

    @PostMapping("/accessoryUpload")
    @ApiOperation("附件上传")
    public R fileUpload(@RequestParam("files") MultipartFile[] files, @RequestParam Map<String,String> accessoryVo) {
        FileVo vo = new FileVo();
        /*if (StringUtils.isBlank(accessoryVo.get("tableId"))) return R.error("关联表Id不能为空！");
        if (StringUtils.isBlank(accessoryVo.get("tableName"))) return R.error("关联表名称不能为空！");
*/
        vo.setSheetInfoId(Long.parseLong(accessoryVo.get("sheetInfoNo")));
        vo.setUploadPerson(accessoryVo.get("uploadPerson"));
        vo.setUploadTime(new Date());

        String message = fileStorageService.fileUpload(files,vo);
        if (StringUtils.isBlank(message)) return R.ok();
        return R.error(message);
    }
}
