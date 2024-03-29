package com.fall.smarthouse.service.impl;

import com.fall.smarthouse.bean.MenuItem;
import com.fall.smarthouse.constant.MenuID;
import com.fall.smarthouse.mapper.SmartMapper;
import com.fall.smarthouse.service.ISmartService;
import com.fall.smarthouse.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/1/6 20:03
 */
@Service
public class SmartServiceImpl implements ISmartService {

    // 家居mapper
    private final SmartMapper smartMapper;

    @Autowired
    public SmartServiceImpl(SmartMapper smartMapper){
        this.smartMapper = smartMapper;
    }

    // 首页菜单数据
    private static final ArrayList<MenuItem> menu = new ArrayList<>();

    /**
     * @author FAll
     * @description 初始化侧边栏路由信息
     * @return: java.util.ArrayList<com.fall.smarthouse.bean.MenuItem>
     * @date 2022/12/20 19:07
     */
    @PostConstruct
    public void initMenuList() {
        if(!menu.isEmpty()) {
            return;
        }
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
        menu.add(powerCtrl);

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
        menu.add(defenceCtrl);

        // 3.场景选择
        MenuItem modeChange = new MenuItem(MenuID.MODE_CHANGE.getId(), "/mode_change", "场景选择", null);

        // 场景切换
        MenuItem changeMode = new MenuItem(MenuID.CHANGE_MODE.getId(), "/change_mode", "场景切换", null);

        MenuItem[] modeChildren = {changeMode};
        modeChange.setChildren(modeChildren);
        menu.add(modeChange);
    }


    /**
     * @author FAll
     * @description 网页获取侧边栏路由信息
     * @return: java.util.ArrayList<com.fall.smarthouse.bean.MenuItem>
     * @date 2022/12/3 10:17
     */
    @Override
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    @Override
    public String userLogin(String account, String password) {

        // MD5加盐
        String s = smartMapper.userLogin(account, JWTUtil.SALT_BEFORE+password + JWTUtil.SALT_AFTER);
        if (s == null || s.trim().equals("")) {
            return null;
        }
        // 如果存在该用户，执行JWT签发
        return JWTUtil.createToken(s);
    }

    @Override
    public Boolean checkLogin(String token) {
        boolean isNeedUpdate = JWTUtil.isNeedUpdate(token);
        return !isNeedUpdate;
    }
}
