package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.constant.MenuID;
import com.fall.smarthouse.mapper.UserMapper;
import com.fall.smarthouse.service.IUserService;
import com.fall.smarthouse.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author FAll
 * @date 2022/12/2 17:21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * @author FAll
     * @description 网页获取侧边栏路由信息
     * @return: java.util.ArrayList<com.fall.smarthouse.bean.MenuItem>
     * @date 2022/12/3 10:17
     */
    @Override
    public ArrayList<MenuItem> getMenu() {
        ArrayList<MenuItem> list = new ArrayList<>();
        // 1.电控系统
        MenuItem powerCtrl = new MenuItem(MenuID.POWER_CTRL.getId(), "/power_ctrl", "电控系统", null);

        // 照明
        MenuItem lightCtrl = new MenuItem(MenuID.LIGHT_CTRL.getId(), "/light_ctrl", "灯光控制", null);
        // 开关
        MenuItem switchCtrl = new MenuItem(MenuID.SWITCH_CTRL.getId(), "/switch_ctrl", "开关控制", null);
        // 窗帘
        MenuItem curtainCtrl = new MenuItem(MenuID.CURTAIN_CTRL.getId(), "/curtain_ctrl", "窗帘控制", null);

        MenuItem[] powerChildren = {lightCtrl, switchCtrl, curtainCtrl};
        powerCtrl.setChildren(powerChildren);
        list.add(powerCtrl);

        // 2.安防系统
        MenuItem defenceCtrl = new MenuItem(MenuID.DEFENCE.getId(), "/defence_ctrl", "安防系统", null);

        // 燃气
        MenuItem gasDetect = new MenuItem(MenuID.GAS_DETECT.getId(), "/gas_detect", "燃气检测", null);
        // 烟雾
        MenuItem smogDetect = new MenuItem(MenuID.SMOG_DETECT.getId(), "/smog_detect", "烟雾检测", null);
        // 震动
        MenuItem shakeDetect = new MenuItem(MenuID.SHAKE_DETECT.getId(), "/shake_detect", "震动检测", null);
        // 报警
        MenuItem warnLight = new MenuItem(MenuID.WARN_LIGHT.getId(), "/warn_light", "警鸣灯光", null);

        MenuItem[] defenceChildren = {gasDetect, smogDetect, shakeDetect, warnLight};
        defenceCtrl.setChildren(defenceChildren);
        list.add(defenceCtrl);

        // 3.场景选择
        MenuItem modeChange = new MenuItem(MenuID.MODE_CHANGE.getId(), "/mode_change", "场景选择", null);
        list.add(modeChange);

        return list;

    }

    @Override
    public String userLogin(String account, String password) {

        // MD5加盐
        String s = userMapper.userLogin(account, JWTUtil.SALT_BEFORE+password + JWTUtil.SALT_AFTER);
        if (s == null || s.trim().equals("")) {
            return null;
        }
        // 如果存在该用户，执行JWT签发
        String token = JWTUtil.createToken(s);
        return token;
    }

    @Override
    public Boolean checkLogin(String token) {
        boolean isNeedUpdate = JWTUtil.isNeedUpdate(token);
        if(isNeedUpdate) {
            return false;
        }
        return true;
    }

}
