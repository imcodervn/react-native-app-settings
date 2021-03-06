package com.krazylabs;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class OpenAppSettingsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public OpenAppSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "OpenAppSettings";
    }

    @ReactMethod
    public void open() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", reactContext.getPackageName(), null);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactContext.startActivity(intent);
    }
        @ReactMethod
    public void openNotificationSetting() {
        Intent settingsIntent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            settingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE, reactContext.getPackageName());
        } else {
            settingsIntent.putExtra("app_package", reactContext.getPackageName())
                    .putExtra("app_uid", reactContext.getApplicationInfo().uid);
        }
        reactContext.startActivity(settingsIntent);

    }
}
