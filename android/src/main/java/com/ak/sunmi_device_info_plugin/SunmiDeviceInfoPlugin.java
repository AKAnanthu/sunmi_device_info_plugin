package com.ak.sunmi_device_info_plugin;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import com.sunmi.tmsmaster.aidl.IDeviceService;
import com.ak.sunmi_device_info_plugin.DeviceInfoUtil;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.ak.sunmi_device_info_plugin.apidemo.TMSMaster;

/** SunmiDeviceInfoPlugin */
public class SunmiDeviceInfoPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private static final String ACTION_TMS_SERVICE = "com.sunmi.tms_service";
  private static final String PACKAGE_NAME_TMS_SERVICE = "com.sunmi.tmservice";
  Context context;
  private static final String TAG = "Sunmi Device Info Logs";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "sunmi_device_info_plugin");
    channel.setMethodCallHandler(this);
    bindService();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
    else if(call.method.equals("getSunmiSerialNo")) {
      result.success(serialNumber());
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }


  private String serialNumber() {
    String serialNo = "";
    if (TMSMaster.getInstance().getDeviceInfo() != null) {
      DeviceInfoUtil deviceInfo = DeviceInfoUtil.getInstance();
      serialNo = deviceInfo.getSerialNo();
    }
    return serialNo;
  }

  private void bindService() {
    try {
      Intent intent = new Intent(ACTION_TMS_SERVICE);
      intent.setPackage(PACKAGE_NAME_TMS_SERVICE);

      boolean bindResult = context.bindService(intent, serviceConnection, context.BIND_AUTO_CREATE);
      log("Service bind attempt result: " + bindResult);
      if (!bindResult) {
        log("Failed to bind to Sunmi service - check if service is installed");
      }
    } catch (Exception e) {
      log("Error binding service: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private final ServiceConnection serviceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      log("onServiceConnected");
      IDeviceService iDeviceService = IDeviceService.Stub.asInterface(service);
      TMSMaster.getInstance().init(context, iDeviceService);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      log("onServiceDisconnected");
      TMSMaster.getInstance().clear();
      bindService();
    }
  };

  private void log(String message) {
    Log.d(TAG, message);
  }
}
