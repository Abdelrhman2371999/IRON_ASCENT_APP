package com.ironascent.app;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.net.Uri;
import android.graphics.Color;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Calendar;
import java.util.Map;
import java.net.URLEncoder;

public class MainActivity extends Activity {
    public static final String CHANNEL_ID = "meal_reminders";
    private static final String PREFS = "meal_reminders";
    private WebView webView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(9,13,18));
        getWindow().setNavigationBarColor(Color.rgb(9,13,18));
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 7001);
        }

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(9,13,18));
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);
        s.setAllowUniversalAccessFromFileURLs(false);
        s.setAllowFileAccessFromFileURLs(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setLoadWithOverviewMode(false);
        s.setUseWideViewPort(false);
        webView.addJavascriptInterface(new AndroidBridge(this), "AndroidBridge");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/index.html");
        setContentView(webView);
    }

    public static class AndroidBridge {
        private final Context context;
        AndroidBridge(Context context) { this.context = context.getApplicationContext(); }

        @JavascriptInterface public void scheduleMealReminder(int id, String time, String title, String text) {
            try {
                int[] hm = parseTime(time);
                SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                sp.edit().putString("time_"+id, time).putString("title_"+id, title).putString("text_"+id, text).putBoolean("enabled_"+id, true).apply();
                schedule(context, id, hm[0], hm[1], title, text);
            } catch (Exception ignored) { }
        }

        @JavascriptInterface public void cancelMealReminder(int id) {
            SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            sp.edit().putBoolean("enabled_"+id, false).apply();
            cancel(context, id);
        }

        @JavascriptInterface public void openAiAssistant(String question) {
            try {
                String q = URLEncoder.encode(question, "UTF-8");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chatgpt.com/?q=" + q));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (Exception ignored) { }
        }
    }

    private static int[] parseTime(String time) {
        String[] p = time.split(":");
        return new int[]{Integer.parseInt(p[0]), Integer.parseInt(p[1])};
    }

    public static void rescheduleSavedReminders(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        for (Map.Entry<String, ?> e : sp.getAll().entrySet()) {
            String k = e.getKey();
            if (k.startsWith("enabled_") && Boolean.TRUE.equals(e.getValue())) {
                int id;
                try { id = Integer.parseInt(k.substring(8)); } catch (Exception ex) { continue; }
                String time = sp.getString("time_"+id, "08:00");
                String title = sp.getString("title_"+id, "IRON ASCENT");
                String text = sp.getString("text_"+id, "موعد الوجبة");
                try { int[] hm = parseTime(time); schedule(context, id, hm[0], hm[1], title, text); } catch (Exception ignored) { }
            }
        }
    }

    private static void schedule(Context context, int id, int hour, int minute, String title, String text) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (cal.getTimeInMillis() <= System.currentTimeMillis()) cal.add(Calendar.DAY_OF_YEAR, 1);

        Intent i = new Intent(context, MealReminderReceiver.class);
        i.putExtra("id", id); i.putExtra("title", title); i.putExtra("text", text);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, i,
                PendingIntent.FLAG_UPDATE_CURRENT | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0));
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    private static void cancel(Context context, int id) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, MealReminderReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, i,
                PendingIntent.FLAG_UPDATE_CURRENT | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0));
        am.cancel(pi);
        pi.cancel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Meal Reminders", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("تذكيرات مواعيد الأكل في IRON ASCENT");
            nm.createNotificationChannel(channel);
        }
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }
}
