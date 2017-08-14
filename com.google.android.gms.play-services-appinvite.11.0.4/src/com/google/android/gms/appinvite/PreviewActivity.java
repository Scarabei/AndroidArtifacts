package com.google.android.gms.appinvite;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import java.util.ArrayList;
import java.util.Iterator;

@KeepForSdkWithMembers
public class PreviewActivity extends Activity {
   public static final String ACTION_PREVIEW = "com.google.android.gms.appinvite.ACTION_PREVIEW";
   public static final String EXTRA_LAYOUT_RES_ID = "com.google.android.gms.appinvite.LAYOUT_RES_ID";
   public static final String EXTRA_TABS = "com.google.android.gms.appinvite.TABS";
   public static final String KEY_TAB_TAG = "tabTag";
   public static final String KEY_TAB_CONTENT_ID = "tabContentId";
   public static final String EXTRA_VIEWS = "com.google.android.gms.appinvite.VIEWS";
   public static final String KEY_VIEW_ID = "View_id";
   public static final String KEY_VIEW_BACKGROUND_COLOR = "View_backgroundColor";
   public static final String KEY_VIEW_MIN_HEIGHT = "View_minHeight";
   public static final String KEY_VIEW_ON_CLICK_LISTENER = "View_onClickListener";
   public static final String KEY_TEXT_VIEW_TEXT = "TextView_text";
   public static final String KEY_TEXT_VIEW_IS_TITLE = "TextView_isTitle";
   public static final String KEY_TEXT_VIEW_TEXT_COLOR = "TextView_textColor";
   public static final String KEY_WEB_VIEW_DATA = "WebView_data";
   public static final String ON_CLICK_LISTENER_CLOSE = "close";

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      if (this.getCallingActivity() != null && "com.google.android.gms".equals(this.getCallingActivity().getPackageName())) {
         Context var2;
         try {
            var2 = this.createPackageContext("com.google.android.gms", 0);
         } catch (NameNotFoundException var14) {
            this.finish();
            return;
         }

         Bundle var3 = this.getIntent().getExtras();
         View var4;
         if ((var4 = this.zza(var2, (ViewGroup)null, var3)) == null) {
            this.finish();
         } else {
            TabHost var5 = (TabHost)var4.findViewById(16908306);
            TabWidget var6 = (TabWidget)var4.findViewById(16908307);
            ArrayList var7 = var3.getParcelableArrayList("com.google.android.gms.appinvite.TABS");
            if (var5 != null && var6 != null && var7 != null) {
               var5.setup();
               ArrayList var11;
               int var12 = (var11 = (ArrayList)var7).size();
               int var13 = 0;

               while(var13 < var12) {
                  Object var10000 = var11.get(var13);
                  ++var13;
                  Bundle var8 = (Bundle)var10000;
                  TabSpec var9;
                  (var9 = var5.newTabSpec(var8.getString("tabTag"))).setContent(var8.getInt("tabContentId"));
                  View var10 = this.zza(var2, var6, var8);
                  var9.setIndicator(var10);
                  var5.addTab(var9);
               }
            }

            this.setContentView(var4);
         }
      } else {
         this.finish();
      }
   }

   private final View zza(Context var1, ViewGroup var2, Bundle var3) {
      int var4 = var3.getInt("com.google.android.gms.appinvite.LAYOUT_RES_ID");
      View var5 = LayoutInflater.from(var1).inflate(var4, var2, false);
      ArrayList var6;
      if ((var6 = var3.getParcelableArrayList("com.google.android.gms.appinvite.VIEWS")) != null) {
         ArrayList var18;
         int var19 = (var18 = (ArrayList)var6).size();
         int var20 = 0;

         while(var20 < var19) {
            Object var10000 = var18.get(var20);
            ++var20;
            Bundle var7 = (Bundle)var10000;
            Bundle var10 = var7;
            PreviewActivity var8 = this;
            View var11 = var5.findViewById(var7.getInt("View_id"));
            Iterator var12 = var7.keySet().iterator();

            while(var12.hasNext()) {
               String var13;
               String var14 = var13 = (String)var12.next();
               byte var15 = -1;
               switch(var14.hashCode()) {
               case -1829808865:
                  if (var14.equals("View_minHeight")) {
                     var15 = 1;
                  }
                  break;
               case -499175494:
                  if (var14.equals("TextView_text")) {
                     var15 = 3;
                  }
                  break;
               case -111184848:
                  if (var14.equals("WebView_data")) {
                     var15 = 6;
                  }
                  break;
               case 573559753:
                  if (var14.equals("TextView_textColor")) {
                     var15 = 4;
                  }
                  break;
               case 966644059:
                  if (var14.equals("View_backgroundColor")) {
                     var15 = 0;
                  }
                  break;
               case 1729346977:
                  if (var14.equals("TextView_isTitle")) {
                     var15 = 5;
                  }
                  break;
               case 1920443715:
                  if (var14.equals("View_onClickListener")) {
                     var15 = 2;
                  }
               }

               switch(var15) {
               case 0:
                  var11.setBackgroundColor(var10.getInt(var13));
                  break;
               case 1:
                  var11.setMinimumHeight(var10.getInt(var13));
                  break;
               case 2:
                  String var21 = var10.getString(var13);
                  byte var17 = -1;
                  switch(var21.hashCode()) {
                  case 94756344:
                     if (var21.equals("close")) {
                        var17 = 0;
                     }
                  default:
                     switch(var17) {
                     case 0:
                        var11.setOnClickListener(new zzb(var8));
                     default:
                        continue;
                     }
                  }
               case 3:
                  if (var11 instanceof TextView) {
                     ((TextView)var11).setText(var10.getCharSequence(var13));
                  }
                  break;
               case 4:
                  if (var11 instanceof TextView) {
                     ((TextView)var11).setTextColor(var10.getInt(var13));
                  }
                  break;
               case 5:
                  if (var11 instanceof TextView && var10.getBoolean(var13)) {
                     var8.setTitle(((TextView)var11).getText());
                  }
                  break;
               case 6:
                  if (var11 instanceof ViewGroup) {
                     WebView var16;
                     (var16 = new WebView(var8)).loadData(var10.getString(var13), "text/html; charset=utf-8", "UTF-8");
                     ((ViewGroup)var11).addView(var16, new LayoutParams(-1, -1));
                  }
               }
            }
         }
      }

      return var5;
   }
}
