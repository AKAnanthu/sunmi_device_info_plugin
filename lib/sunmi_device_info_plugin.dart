import 'sunmi_device_info_plugin_platform_interface.dart';

class SunmiDeviceInfoPlugin {
  Future<String?> getPlatformVersion() {
    return SunmiDeviceInfoPluginPlatform.instance.getPlatformVersion();
  }

  Future<String?> getSunmiSerialNo() {
    return SunmiDeviceInfoPluginPlatform.instance.getSunmiSerialNo();
  }
}
