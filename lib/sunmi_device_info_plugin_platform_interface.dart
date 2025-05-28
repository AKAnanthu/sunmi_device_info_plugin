import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'sunmi_device_info_plugin_method_channel.dart';

abstract class SunmiDeviceInfoPluginPlatform extends PlatformInterface {
  /// Constructs a SunmiDeviceInfoPluginPlatform.
  SunmiDeviceInfoPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static SunmiDeviceInfoPluginPlatform _instance =
      MethodChannelSunmiDeviceInfoPlugin();

  /// The default instance of [SunmiDeviceInfoPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelSunmiDeviceInfoPlugin].
  static SunmiDeviceInfoPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [SunmiDeviceInfoPluginPlatform] when
  /// they register themselves.
  static set instance(SunmiDeviceInfoPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getSunmiSerialNo() {
    throw UnimplementedError('getSunmiSerialNo() has not been implemented.');
  }
}
