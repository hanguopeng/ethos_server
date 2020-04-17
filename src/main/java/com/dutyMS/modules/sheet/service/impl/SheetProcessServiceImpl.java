package com.dutyMS.modules.sheet.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.modules.sheet.bo.ProcessInfoBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.dao.SheetDao;
import com.dutyMS.modules.sheet.dao.SheetProcessDao;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import com.dutyMS.modules.sheet.entity.SheetProcessEntity;
import com.dutyMS.modules.sheet.service.SheetProcessService;
import com.dutyMS.modules.sheet.service.SheetService;
import com.dutyMS.modules.sheet.vo.SheetProcessVo;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.user.dao.SysUserDao;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("SheetProcessService")
public class SheetProcessServiceImpl extends ServiceImpl<SheetProcessDao, SheetProcessEntity> implements SheetProcessService {

    @Resource
    private SheetProcessDao sheetProcessDao;
    @Resource
    private SheetDao sheetDao;
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public Integer sendSheet(SheetInfoBo bo) {
        SheetProcessEntity entity = new SheetProcessEntity();
        entity.setCreateTime(new Date());
        entity.setCurrentStatus(Long.parseLong(bo.getCurrentStatus()));
        entity.setNodePersonId(bo.getSendPerson());
        SysUserEntity user = sysUserDao.queryByUserId(bo.getSendPerson());
        SysRoleEntity role = sysUserDao.queryRoleByUserId(bo.getSendPerson());
        entity.setNodePersonName(user.getRealName());
        entity.setNodePersonRole(role.getRoleName());
        entity.setSheetId(bo.getSheetId());
        entity.setResolveSuggestion(bo.getSuggestion());
        Integer integer = sheetProcessDao.insert(entity);
        entity = sheetProcessDao.selectBySheetId(bo.getSheetId());
        Integer uInteger = sheetProcessDao.updateProcessStartId(entity);
        return integer;
    }

    @Override
    public Integer orderTask(Long sheetId) {
        Integer integer = sheetDao.updateStatusBySheetId(Long.parseLong("102"), sheetId);
        SheetProcessEntity process = sheetProcessDao.queryProcessBySheetNo(sheetId);
        process.setCurrentStatus(102L);
        process.setCreateTime(new Date());
        Integer pinteger = sheetProcessDao.insert(process);
        if (integer == 1 && pinteger == 1) {
            return 1;
        } else {
            return 2;
        }

    }

    @Override
    public List<ProcessInfoBo> queryProcess(String sheetId) {
        List<ProcessInfoBo> list = sheetProcessDao.queryProcess(sheetId);
        ProcessInfoBo infoBo = new ProcessInfoBo();
        SheetEntity sheetEntity = new SheetEntity();
        SysRoleEntity sysRoleEntity;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sheetEntity = sheetDao.selectById(list.get(i).getSheetId());
                list.get(i).setNodePersonName(sheetEntity.getCreatePersonName());
                list.get(i).setNodePersonId(sheetEntity.getCreatePersonId());
                list.get(i).setNodePersonContact(sheetEntity.getCreatePersonContact());
                sysRoleEntity = sysUserDao.queryRoleByUserId(sheetEntity.getCreatePersonId());
                list.get(i).setNodePersonRole(sysRoleEntity.getRoleName());
            } else if ((i == list.size() - 1)) {
                sheetEntity = sheetDao.querySheetById(list.get(i).getSheetId());
                infoBo.setNodePersonName(list.get(i).getNodePersonName());
                infoBo.setNodePersonId(list.get(i).getNodePersonId());
                infoBo.setNodePersonContact(list.get(i).getNodePersonContact());
                sysRoleEntity = sysUserDao.queryRoleByUserId(list.get(i).getNodePersonId());
                infoBo.setNodePersonRole(sysRoleEntity.getRoleName());
                if (list.get(i).getCurrentStatus() == 103L) {
                    sheetEntity = sheetDao.selectById(list.get(i).getSheetId());
                    infoBo.setNodePersonName(sheetEntity.getCreatePersonName());
                    infoBo.setNodePersonId(sheetEntity.getCreatePersonId());
                    infoBo.setNodePersonContact(sheetEntity.getCreatePersonContact());
                    sysRoleEntity = sysUserDao.queryRoleByUserId(sheetEntity.getCreatePersonId());
                    infoBo.setNodePersonRole(sysRoleEntity.getRoleName());
                    infoBo.setCurrentStatus(104L);
                    list.add(infoBo);
                    break;
                } else if (list.get(i).getCurrentStatus() == 101L) {
                    infoBo.setCurrentStatus(102L);
                    list.add(infoBo);
                    break;
                } else if (list.get(i).getCurrentStatus() == 102L) {
                    infoBo.setCurrentStatus(103L);
                    list.add(infoBo);
                    break;
                } else if (list.get(i).getCurrentStatus() == 104L) {
                    return list;
                }

            }
        }
        return list;
    }

    @Override
    public Integer resolveTask(SheetProcessVo vo) {
        SheetProcessEntity processEntity = sheetProcessDao.selectBySheetId(vo.getSheetId());
        processEntity.setOperationTime(new Date());
        processEntity.setResolveSuggestion(vo.getRemarks());
        processEntity.setCurrentStatus(103L);
        Integer integer = sheetProcessDao.insert(processEntity);
        return integer;
    }

}
