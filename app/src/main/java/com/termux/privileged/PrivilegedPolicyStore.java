package com.termux.privileged;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

/**
 * Centralized storage for privileged backend and endpoint policy toggles.
 */
public final class PrivilegedPolicyStore {

    private static final String PREFS_NAME = "termux_privileged_policy";

    public static final String KEY_MASTER_ENABLED = "priv_master_enabled";
    public static final String KEY_PREFER_SHIZUKU = "priv_prefer_shizuku";
    public static final String KEY_ALLOW_SHELL_FALLBACK = "priv_allow_shell_fallback";

    public static final String KEY_ENDPOINT_EXEC = "priv_endpoint_exec";

    private PrivilegedPolicyStore() {
    }

    public enum Endpoint {
        EXEC
    }

    public static boolean isMasterEnabled(Context context) {
        return getPrefs(context).getBoolean(KEY_MASTER_ENABLED, true);
    }

    public static void setMasterEnabled(Context context, boolean enabled) {
        getPrefs(context).edit().putBoolean(KEY_MASTER_ENABLED, enabled).apply();
    }

    public static boolean isPreferShizuku(Context context) {
        return getPrefs(context).getBoolean(KEY_PREFER_SHIZUKU, true);
    }

    public static void setPreferShizuku(Context context, boolean enabled) {
        getPrefs(context).edit().putBoolean(KEY_PREFER_SHIZUKU, enabled).apply();
    }

    public static boolean isShellFallbackEnabled(Context context) {
        return getPrefs(context).getBoolean(KEY_ALLOW_SHELL_FALLBACK, true);
    }

    public static void setShellFallbackEnabled(Context context, boolean enabled) {
        getPrefs(context).edit().putBoolean(KEY_ALLOW_SHELL_FALLBACK, enabled).apply();
    }

    public static boolean isEndpointEnabled(Context context, @NonNull Endpoint endpoint) {
        SharedPreferences prefs = getPrefs(context);
        switch (endpoint) {
            case EXEC:
                return prefs.getBoolean(KEY_ENDPOINT_EXEC, true);
            default:
                return true;
        }
    }

    public static void setEndpointEnabled(Context context, @NonNull Endpoint endpoint, boolean enabled) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        switch (endpoint) {
            case EXEC:
                editor.putBoolean(KEY_ENDPOINT_EXEC, enabled);
                break;
        }
        editor.apply();
    }

    private static SharedPreferences getPrefs(Context context) {
        if (context == null) {
            throw new IllegalStateException("Context is required for privileged policy access");
        }
        return context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
