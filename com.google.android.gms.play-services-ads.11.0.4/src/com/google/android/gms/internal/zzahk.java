package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.zzo;
import java.io.File;

@TargetApi(17)
public class zzahk extends zzahm {
   public final boolean zza(Context var1, WebSettings var2) {
      super.zza(var1, var2);
      var2.setMediaPlaybackRequiresUserGesture(false);
      return true;
   }

   public final String getDefaultUserAgent(Context var1) {
      zzaiu var2;
      zzaiu var4;
      if (TextUtils.isEmpty((var4 = var2 = zzaiu.zzif()).zzJP)) {
         Context var7 = zzo.getRemoteContext(var1);
         String var6 = (String)zzait.zzb(new zzaiv(var4, var7, var1));
         var4.zzJP = var6;
      }

      return var2.zzJP;
   }

   public final void zzR(Context var1) {
      zzaiu var2 = zzaiu.zzif();
      zzafr.v("Updating user agent.");
      String var4;
      if (!(var4 = WebSettings.getDefaultUserAgent(var1)).equals(var2.zzJP)) {
         Context var5;
         if ((var5 = zzo.getRemoteContext(var1)) == null) {
            String var6 = WebSettings.getDefaultUserAgent(var1);
            Editor var7 = var1.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", var6);
            if (var5 == null) {
               var7.apply();
            } else {
               String var10 = "admob_user_agent";
               File var11;
               File var12;
               if ((var12 = (var11 = new File(var1.getApplicationInfo().dataDir, "shared_prefs")).getParentFile()) != null) {
                  var12.setExecutable(true, false);
               }

               var11.setExecutable(true, false);
               var7.commit();
               (new File(var11, String.valueOf(var10).concat(".xml"))).setReadable(true, false);
            }
         }

         var2.zzJP = var4;
      }

      zzafr.v("User agent is updated.");
      com.google.android.gms.ads.internal.zzbs.zzbD().zzhH();
   }

   public final Drawable zza(Context var1, Bitmap var2, boolean var3, float var4) {
      if (var3 && var4 > 0.0F && var4 <= 25.0F) {
         try {
            Bitmap var5;
            Bitmap var6 = Bitmap.createBitmap(var5 = Bitmap.createScaledBitmap(var2, var2.getWidth(), var2.getHeight(), false));
            RenderScript var7;
            RenderScript var10000 = var7 = RenderScript.create(var1);
            ScriptIntrinsicBlur var8 = ScriptIntrinsicBlur.create(var10000, Element.U8_4(var10000));
            Allocation var9 = Allocation.createFromBitmap(var7, var5);
            Allocation var10 = Allocation.createFromBitmap(var7, var6);
            var8.setRadius(var4);
            var8.setInput(var9);
            var8.forEach(var10);
            var10.copyTo(var6);
            return new BitmapDrawable(var1.getResources(), var6);
         } catch (RuntimeException var11) {
            return new BitmapDrawable(var1.getResources(), var2);
         }
      } else {
         return new BitmapDrawable(var1.getResources(), var2);
      }
   }
}
