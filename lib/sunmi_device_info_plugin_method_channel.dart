import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'sunmi_device_info_plugin_platform_interface.dart';

/// An implementation of [SunmiDeviceInfoPluginPlatform] that uses method channels.
class MethodChannelSunmiDeviceInfoPlugin extends SunmiDeviceInfoPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('sunmi_device_info_plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>(
      'getPlatformVersion',
    );
    return version;
  }

  @override
  Future<String?> getSunmiSerialNo() async {
    final version = await methodChannel.invokeMethod<String>(
      'getSunmiSerialNo',
    );
    return version;
  }
}
