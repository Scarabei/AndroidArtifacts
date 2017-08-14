package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;

final class zzwo implements OnClickListener {
   // $FF: synthetic field
   private String zzNO;
   // $FF: synthetic field
   private String zzNP;
   // $FF: synthetic field
   private zzwn zzNQ;

   zzwo(zzwn var1, String var2, String var3) {
      this.zzNQ = var1;
      this.zzNO = var2;
      this.zzNP = var3;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      DownloadManager var3 = (DownloadManager)zzwn.zza(this.zzNQ).getSystemService("download");

      try {
         String var6 = this.zzNP;
         String var5 = this.zzNO;
         Request var7;
         (var7 = new Request(Uri.parse(var5))).setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, var6);
         com.google.android.gms.ads.internal.zzbs.zzbB().zza(var7);
         var3.enqueue(var7);
      } catch (IllegalStateException var8) {
         this.zzNQ.zzan("Could not store picture.");
      }
   }
}
