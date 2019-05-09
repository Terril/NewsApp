package com.test.testnewsapp.testnewsapp.utils;

import android.content.Intent;
import android.os.Bundle;

import com.test.testnewsapp.testnewsapp.ui.base.BaseActivity;

public class ActivityUtils {

    public static final String BUNDLE_KEY = "_bundle_key_";

    public static void launchActivity(BaseActivity sourceActivity, final Class<?> destinationActivityClass, boolean shouldClearTop) {
        Intent intent = new Intent(sourceActivity, destinationActivityClass);
        if (shouldClearTop) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        sourceActivity.startActivity(intent);
    }

    public static void launchActivity(BaseActivity sourceActivity, final Class<?> destinationActivityClass, boolean shouldClearTop, Bundle bundle) {
        Intent intent = new Intent(sourceActivity, destinationActivityClass);
        if (shouldClearTop) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (bundle != null) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        sourceActivity.startActivity(intent);
    }
}
