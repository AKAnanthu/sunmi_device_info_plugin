import 'package:flutter_test/flutter_test.dart';
import 'package:sunmi_device_info_plugin/sunmi_device_info_plugin.dart';
import 'package:sunmi_device_info_plugin/sunmi_device_info_plugin_platform_interface.dart';
import 'package:sunmi_device_info_plugin/sunmi_device_info_plugin_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockSunmiDeviceInfoPluginPlatform
    with MockPlatformInterfaceMixin
    implements SunmiDeviceInfoPluginPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final SunmiDeviceInfoPluginPlatform initialPlatform = SunmiDeviceInfoPluginPlatform.instance;

  test('$MethodChannelSunmiDeviceInfoPlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelSunmiDeviceInfoPlugin>());
  });

  test('getPlatformVersion', () async {
    SunmiDeviceInfoPlugin sunmiDeviceInfoPlugin = SunmiDeviceInfoPlugin();
    MockSunmiDeviceInfoPluginPlatform fakePlatform = MockSunmiDeviceInfoPluginPlatform();
    SunmiDeviceInfoPluginPlatform.instance = fakePlatform;

    expect(await sunmiDeviceInfoPlugin.getPlatformVersion(), '42');
  });
}
