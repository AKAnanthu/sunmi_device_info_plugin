package com.ak.sunmi_device_info_plugin;

import android.util.Log;
import com.ak.sunmi_device_info_plugin.apidemo.TMSMaster;
import com.sunmi.tmsmaster.aidl.deviceinfo.IDeviceInfo;

public class DeviceInfoUtil {

    private static final String TAG = DeviceInfoUtil.class.getName();
    
    private static DeviceInfoUtil instance;
    
    private DeviceInfoUtil() {}
    
    public static DeviceInfoUtil getInstance() {
        if (instance == null) {
            instance = new DeviceInfoUtil();
        }
        return instance;
    }

    private void log(String text) {
        Log.d(TAG, text);
    }

    private IDeviceInfo getData() {
        TMSMaster tmsMaster = TMSMaster.getInstance();
        IDeviceInfo info = tmsMaster.getDeviceInfo();
        if (info == null) {
            log("DeviceInfo is null - service may not be properly initialized");
        }
        return info;
    }

    public String getSerialNo() {
        try {
            IDeviceInfo info = getData();
            if (info == null) {
                log("Unable to get serial number - service not ready");
                return null;
            }
            String result = info.getSerialNo();
            log("getSerialNo: result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getSerialNo error: " + e.getMessage());
            return null;
        }
    }

    public String getIMSI() {
        try {
            String result = getData().getIMSI();
            log("getIMSI: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getIMSI error!");
            return null;
        }
    }

    public String getIMEI() {
        try {
            String result = getData().getIMEI();
            log("getIMEI: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getIMEI error!");
            return null;
        }
    }

    public String getICCID() {
        try {
            String result = getData().getICCID();
            log("getICCID: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getICCID error!");
            return null;
        }
    }

    public String getManufacture() {
        try {
            String result = getData().getManufacture();
            log("getManufacture: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getManufacture error!");
            return null;
        }
    }

    public String getModel() {
        try {
            String result = getData().getModel();
            log("getModel: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getModel error!");
            return null;
        }
    }

    public String getAndroidOSVersion() {
        try {
            String result = getData().getAndroidOSVersion();
            log("getAndroidOSVersion: , result=" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log("getAndroidOSVersion error!");
            return null;
        }
    }
}