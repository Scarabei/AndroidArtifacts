package android.support.test.internal.runner.tracker;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.test.internal.util.Checks;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class AnalyticsBasedUsageTracker implements UsageTracker {
   private static final String TAG = "InfraTrack";
   private static final String UTF_8 = "UTF-8";
   private static final String APP_NAME_PARAM = "an=";
   private static final String CONTENT_DESCRIPT_PARAM = "&cd=";
   private static final String TRACKER_ID_PARAM = "&tid=";
   private static final String CLIENT_ID_PARAM = "&cid=";
   private static final String SCREEN_RESOLUTION_PARAM = "&sr=";
   private static final String API_LEVEL_PARAM = "&cd2=";
   private static final String MODEL_NAME_PARAM = "&cd3=";
   private final String trackingId;
   private final String targetPackage;
   private final URL analyticsURI;
   private final String screenResolution;
   private final String apiLevel;
   private final String model;
   private final String userId;
   private final List usages;

   private AnalyticsBasedUsageTracker(AnalyticsBasedUsageTracker.Builder builder) {
      this.usages = new ArrayList();
      this.trackingId = (String)Checks.checkNotNull(builder.trackingId);
      this.targetPackage = (String)Checks.checkNotNull(builder.targetPackage);
      this.analyticsURI = (URL)Checks.checkNotNull(builder.analyticsURI);
      this.apiLevel = (String)Checks.checkNotNull(builder.apiLevel);
      this.model = (String)Checks.checkNotNull(builder.model);
      this.screenResolution = (String)Checks.checkNotNull(builder.screenResolution);
      this.userId = (String)Checks.checkNotNull(builder.userId);
   }

   public void trackUsage(String usageType) {
      List var2 = this.usages;
      synchronized(this.usages) {
         this.usages.add(usageType);
      }
   }

   public void sendUsages() {
      List var2 = this.usages;
      ArrayList myUsages;
      synchronized(this.usages) {
         if (this.usages.isEmpty()) {
            return;
         }

         myUsages = new ArrayList(this.usages);
         this.usages.clear();
      }

      String baseBody = null;

      try {
         baseBody = "an=" + URLEncoder.encode(this.targetPackage, "UTF-8") + "&tid=" + URLEncoder.encode(this.trackingId, "UTF-8") + "&v=1" + "&z=" + SystemClock.uptimeMillis() + "&cid=" + URLEncoder.encode(this.userId, "UTF-8") + "&sr=" + URLEncoder.encode(this.screenResolution, "UTF-8") + "&cd2=" + URLEncoder.encode(this.apiLevel, "UTF-8") + "&cd3=" + URLEncoder.encode(this.model, "UTF-8") + "&t=appview";
      } catch (IOException var14) {
         Log.w("InfraTrack", "Impossible error happened. analytics disabled.", var14);
      }

      Iterator i$ = myUsages.iterator();

      while(i$.hasNext()) {
         String usage = (String)i$.next();
         HttpURLConnection analyticsConnection = null;

         try {
            analyticsConnection = (HttpURLConnection)this.analyticsURI.openConnection();
            byte[] body = (baseBody + "&cd=" + URLEncoder.encode(usage, "UTF-8")).getBytes();
            analyticsConnection.setConnectTimeout(3000);
            analyticsConnection.setReadTimeout(5000);
            analyticsConnection.setDoOutput(true);
            analyticsConnection.setFixedLengthStreamingMode(body.length);
            analyticsConnection.getOutputStream().write(body);
            int status = analyticsConnection.getResponseCode();
            if (status / 100 != 2) {
               Log.w("InfraTrack", "Analytics post: " + usage + " failed. code: " + analyticsConnection.getResponseCode() + " - " + analyticsConnection.getResponseMessage());
            }
         } catch (IOException var13) {
            Log.w("InfraTrack", "Analytics post: " + usage + " failed. ", var13);
         } finally {
            if (null != analyticsConnection) {
               analyticsConnection.disconnect();
            }

         }
      }

   }

   // $FF: synthetic method
   AnalyticsBasedUsageTracker(AnalyticsBasedUsageTracker.Builder x0, AnalyticsBasedUsageTracker.SyntheticClass_1 x1) {
      this(x0);
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   public static class Builder {
      private final Context targetContext;
      private Uri analyticsUri = (new android.net.Uri.Builder()).scheme("http").authority("www.google-analytics.com").path("collect").build();
      private String trackingId = "UA-36650409-3";
      private String apiLevel;
      private String model;
      private String targetPackage;
      private URL analyticsURI;
      private String screenResolution;
      private String userId;
      private boolean hashed;

      public Builder(Context targetContext) {
         this.apiLevel = String.valueOf(VERSION.SDK_INT);
         this.model = Build.MODEL;
         if (targetContext == null) {
            throw new NullPointerException("Context null!?");
         } else {
            this.targetContext = targetContext;
         }
      }

      public AnalyticsBasedUsageTracker.Builder withTrackingId(String trackingId) {
         this.trackingId = trackingId;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withAnalyticsUri(Uri analyticsUri) {
         Checks.checkNotNull(analyticsUri);
         this.analyticsUri = analyticsUri;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withApiLevel(String apiLevel) {
         this.apiLevel = apiLevel;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withScreenResolution(String resolutionVal) {
         this.screenResolution = resolutionVal;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withUserId(String userId) {
         this.userId = userId;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withModel(String model) {
         this.model = model;
         return this;
      }

      public AnalyticsBasedUsageTracker.Builder withTargetPackage(String targetPackage) {
         this.hashed = false;
         this.targetPackage = targetPackage;
         return this;
      }

      public UsageTracker buildIfPossible() {
         if (!this.hasInternetPermission()) {
            Log.d("InfraTrack", "Tracking disabled due to lack of internet permissions");
            return null;
         } else {
            if (null == this.targetPackage) {
               this.withTargetPackage(this.targetContext.getPackageName());
            }

            if (this.targetPackage.contains("com.google.analytics")) {
               Log.d("InfraTrack", "Refusing to use analytics while testing analytics.");
               return null;
            } else {
               try {
                  if (!this.targetPackage.startsWith("com.google.") && !this.targetPackage.startsWith("com.android.")) {
                     if (!this.hashed) {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        digest.reset();
                        digest.update(this.targetPackage.getBytes("UTF-8"));
                        BigInteger hashedPackage = new BigInteger(digest.digest());
                        this.targetPackage = "sha256-" + hashedPackage.toString(16);
                     }

                     this.hashed = true;
                  }
               } catch (NoSuchAlgorithmException var4) {
                  Log.d("InfraTrack", "Cannot hash package name.", var4);
                  return null;
               } catch (UnsupportedEncodingException var5) {
                  Log.d("InfraTrack", "Impossible - no utf-8 encoding?", var5);
                  return null;
               }

               try {
                  this.analyticsURI = new URL(this.analyticsUri.toString());
               } catch (MalformedURLException var3) {
                  Log.w("InfraTrack", "Tracking disabled bad url: " + this.analyticsUri.toString(), var3);
                  return null;
               }

               if (null == this.screenResolution) {
                  Display display = ((WindowManager)this.targetContext.getSystemService("window")).getDefaultDisplay();
                  this.screenResolution = display.getWidth() + "x" + display.getHeight();
               }

               if (null == this.userId) {
                  this.userId = UUID.randomUUID().toString();
               }

               return new AnalyticsBasedUsageTracker(this);
            }
         }
      }

      private boolean hasInternetPermission() {
         return 0 == this.targetContext.checkCallingOrSelfPermission("android.permission.INTERNET");
      }
   }
}
