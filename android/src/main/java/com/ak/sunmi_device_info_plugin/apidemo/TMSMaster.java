package com.ak.sunmi_device_info_plugin.apidemo;

import android.content.Context;


import com.sunmi.tmsmaster.aidl.deviceinfo.IDeviceInfo;
import com.sunmi.tmsmaster.aidl.IDeviceService;

public class TMSMaster {

    private static TMSMaster instance = null;

    private TMSMaster() {
    }

    private Context applicationContext;
    private IDeviceInfo deviceInfo;

    private IDeviceService deviceService;
    public static TMSMaster getInstance() {
        if (instance == null) {
            synchronized (TMSMaster.class) {
                if (instance == null) {
                    instance = new TMSMaster();
                }
            }
        }
        return instance;
    }

    public void init(Context context, IDeviceService deviceService) {
        if (context == null) {
            throw new NullPointerException("Context = null!");
        }
        applicationContext = context.getApplicationContext();
        this.deviceService = deviceService;

        try {
            deviceInfo = deviceService.getDeviceInfoBinder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        deviceInfo = null;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
    public IDeviceInfo getDeviceInfo() {
        return deviceInfo;
    }
}
