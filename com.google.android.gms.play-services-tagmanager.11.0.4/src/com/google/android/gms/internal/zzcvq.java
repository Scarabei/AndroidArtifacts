package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.R.string;

public final class zzcvq {
   private final Context zzapC;
   private final Context zzaSQ;
   private final Intent mIntent;
   private final zzcwn zzbGZ;

   public zzcvq(Intent var1, Context var2, Context var3, zzcwn var4) {
      this.zzapC = var2;
      this.zzaSQ = var3;
      this.mIntent = var1;
      this.zzbGZ = var4;
   }

   public final void zzCv() {
      Uri var1 = this.mIntent.getData();

      String var10001;
      String var10002;
      try {
         this.zzbGZ.zzs(var1);
         var10001 = this.zzaSQ.getResources().getString(string.tagmanager_preview_dialog_title);
         var10002 = this.zzaSQ.getResources().getString(string.tagmanager_preview_dialog_message);
         String var6 = this.zzaSQ.getResources().getString(string.tagmanager_preview_dialog_button);
         String var5 = var10002;
         String var4 = var10001;
         AlertDialog var7;
         (var7 = (new Builder(this.zzapC)).create()).setTitle(var4);
         var7.setMessage(var5);
         var7.setButton(-1, var6, new zzcvr(this));
         var7.show();
      } catch (Exception var8) {
         var10001 = String.valueOf(var8.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Calling preview threw an exception: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Calling preview threw an exception: ");
         }

         zzcvk.e(var10000);
      }
   }

   // $FF: synthetic method
   static Context zza(zzcvq var0) {
      return var0.zzapC;
   }
}
