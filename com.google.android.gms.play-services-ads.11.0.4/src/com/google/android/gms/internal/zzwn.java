package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R.string;
import java.util.Map;

@zzzn
public final class zzwn extends zzwu {
   private final Map zzHa;
   private final Context mContext;

   public zzwn(zzaka var1, Map var2) {
      super(var1, "storePicture");
      this.zzHa = var2;
      this.mContext = var1.zzis();
   }

   public final void execute() {
      if (this.mContext == null) {
         this.zzan("Activity context is not available");
      } else {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         if (!zzagz.zzH(this.mContext).zzdG()) {
            this.zzan("Feature is not supported by the device.");
         } else {
            String var1;
            if (TextUtils.isEmpty(var1 = (String)this.zzHa.get("iurl"))) {
               this.zzan("Image url cannot be empty.");
            } else {
               String var10001;
               String var10002;
               String var10003;
               if (!URLUtil.isValidUrl(var1)) {
                  var10002 = String.valueOf(var1);
                  if (var10002.length() != 0) {
                     var10001 = "Invalid image url: ".concat(var10002);
                  } else {
                     var10003 = new String;
                     var10001 = var10003;
                     var10003.<init>("Invalid image url: ");
                  }

                  this.zzan(var10001);
               } else {
                  String var2 = Uri.parse(var1).getLastPathSegment();
                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  if (!zzagz.zzaK(var2)) {
                     var10002 = String.valueOf(var2);
                     if (var10002.length() != 0) {
                        var10001 = "Image type not recognized: ".concat(var10002);
                     } else {
                        var10003 = new String;
                        var10001 = var10003;
                        var10003.<init>("Image type not recognized: ");
                     }

                     this.zzan(var10001);
                  } else {
                     Resources var3 = com.google.android.gms.ads.internal.zzbs.zzbD().getResources();
                     com.google.android.gms.ads.internal.zzbs.zzbz();
                     Builder var4;
                     (var4 = zzagz.zzG(this.mContext)).setTitle(var3 != null ? var3.getString(string.s1) : "Save image");
                     var4.setMessage(var3 != null ? var3.getString(string.s2) : "Allow Ad to store image in Picture gallery?");
                     var4.setPositiveButton(var3 != null ? var3.getString(string.s3) : "Accept", new zzwo(this, var1, var2));
                     var4.setNegativeButton(var3 != null ? var3.getString(string.s4) : "Decline", new zzwp(this));
                     var4.create().show();
                  }
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static Context zza(zzwn var0) {
      return var0.mContext;
   }
}
