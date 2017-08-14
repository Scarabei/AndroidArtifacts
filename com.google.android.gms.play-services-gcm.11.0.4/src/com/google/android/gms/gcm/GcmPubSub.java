package com.google.android.gms.gcm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.iid.InstanceID;
import java.io.IOException;
import java.util.regex.Pattern;

public class GcmPubSub {
   private static GcmPubSub zzbfx;
   private InstanceID zzbfy;
   private static final Pattern zzbfz = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");

   private GcmPubSub(Context var1) {
      this.zzbfy = InstanceID.getInstance(var1);
   }

   public static synchronized GcmPubSub getInstance(Context var0) {
      if (zzbfx == null) {
         zzbfx = new GcmPubSub(var0);
      }

      return zzbfx;
   }

   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public void subscribe(String var1, String var2, Bundle var3) throws IOException {
      IllegalArgumentException var10000;
      String var10002;
      String var10003;
      String var10004;
      if (var1 != null && !var1.isEmpty()) {
         if (var2 != null && zzbfz.matcher(var2).matches()) {
            if (var3 == null) {
               var3 = new Bundle();
            }

            var3.putString("gcm.topic", var2);
            this.zzbfy.getToken(var1, var2, var3);
         } else {
            var10000 = new IllegalArgumentException;
            var10003 = String.valueOf(var2);
            if (var10003.length() != 0) {
               var10002 = "Invalid topic name: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Invalid topic name: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      } else {
         var10000 = new IllegalArgumentException;
         var10003 = String.valueOf(var1);
         if (var10003.length() != 0) {
            var10002 = "Invalid appInstanceToken: ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Invalid appInstanceToken: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public void unsubscribe(String var1, String var2) throws IOException {
      Bundle var3;
      (var3 = new Bundle()).putString("gcm.topic", var2);
      this.zzbfy.zzb(var1, var2, var3);
   }
}
