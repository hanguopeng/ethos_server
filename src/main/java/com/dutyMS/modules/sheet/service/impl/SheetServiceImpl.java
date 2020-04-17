package com.dutyMS.modules.sheet.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.modules.sheet.bo.ProcessStatusBo;
import com.dutyMS.modules.sheet.bo.QuerySheetBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.bo.SheetQueryBo;
import com.dutyMS.modules.sheet.dao.SheetDao;
import com.dutyMS.modules.sheet.dao.SheetProcessDao;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import com.dutyMS.modules.sheet.service.SheetService;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("SheetService")
public class SheetServiceImpl extends ServiceImpl<SheetDao, SheetEntity> implements SheetService {
    @Resource
    private SheetDao sheetDao;
    @Resource
    private SheetProcessDao sheetProcessDao;

    @Override
    public Long createSheet(SheetInfoBo bo) {
        SheetEntity entity = new SheetEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            bo.setHappenTime(bo.getHappenTime().replace("Z", " UTC"));
            bo.setReflectionTime(bo.getReflectionTime().replace("Z", " UTC"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            entity.setHappenTime(format.parse(bo.getHappenTime()));
            entity.setReflectionTime(format.parse(bo.getReflectionTime()));
            entity.setCity(bo.getCity());
            entity.setEnclosureUrl(bo.getEnclosureUrl());
            entity.setRelationDepartment(bo.getRelationDepartment());
            entity.setRelationEvent(bo.getRelationEvent());
            entity.setRelationPerson(bo.getRelationPerson());
            entity.setReflectionProblem(bo.getRelationProblem());
            entity.setSendPerson(bo.getSendPerson().toString());
            entity.setSheetLink(bo.getSheetLink());
            entity.setSheetNo(bo.getSheetNo());
            entity.setSheetTheme(bo.getSheetTheme());
            entity.setSheetType(bo.getSheetType());
            entity.setCreateTime(new Date());
            entity.setCreatePersonId(bo.getUserId());
            entity.setCreatePersonName(bo.getUserName());
            entity.setCreatePersonCity(bo.getCreatePersonCity());
            entity.setCurrentStatus(bo.getCurrentStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer integer = sheetDao.insert(entity);
        System.err.println(integer);
        QuerySheetBo inb = new QuerySheetBo();
        inb.setSheetNo(bo.getSheetNo());
        SheetEntity sheet = sheetDao.queryBySheetNo(inb);
        return sheet.getId();
    }

    @Override
    public List<SheetQueryBo> querySheetInfo(QuerySheetBo bo) {
        List<SheetQueryBo> list = sheetDao.querySheetInfo(bo);
        return list;
    }

    @Override
    public List<Map<String,String>> queryCitys() {
        List<SysRegionEntity> list = sheetDao.queryCitys();
        List<Map<String,String>> jsonObj = new ArrayList<Map<String,String>>();
        for(SysRegionEntity entity:list){
            Map<String,String> map = new HashMap<String,String>();
            map.put("value",entity.getRegionId().toString());
            map.put("label", entity.getRegionName().toString());
            jsonObj.add(map);
        }
        return jsonObj;
    }

    @Override
    public String getSheetNo(String regionId) {
        String sheetNo = sheetDao.getSheetNoById(regionId);
        Long sheetCount = sheetDao.getSheetCountByCity(regionId) + 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sheetNo = "JL" + sheetNo + "_" + sdf.format(new Date()) + "_" + sheetCount.toString();
        return sheetNo;
    }

    @Override
    public Integer sendSheet(SheetInfoBo bo) {

        return null;
    }
}
