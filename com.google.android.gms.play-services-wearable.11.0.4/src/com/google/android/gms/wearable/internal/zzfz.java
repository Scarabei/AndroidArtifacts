package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

final class zzfz implements Runnable {
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private zzbaz zzbTq;
   // $FF: synthetic field
   private String zzakq;
   // $FF: synthetic field
   private long zzbSm;
   // $FF: synthetic field
   private long zzbSn;
   // $FF: synthetic field
   private zzfw zzbTr;

   zzfz(zzfw var1, Uri var2, zzbaz var3, String var4, long var5, long var7) {
      this.zzbTr = var1;
      this.zzbzR = var2;
      this.zzbTq = var3;
      this.zzakq = var4;
      this.zzbSm = var5;
      this.zzbSn = var7;
      super();
   }

   public final void run() {
      if (Log.isLoggable("WearableClient", 2)) {
         Log.v("WearableClient", "Executing sendFileToChannelTask");
      }

      if (!"file".equals(this.zzbzR.getScheme())) {
         Log.w("WearableClient", "Channel.sendFile used with non-file URI");
         this.zzbTq.zzr(new Status(10, "Channel.sendFile used with non-file URI"));
      } else {
         File var1 = new File(this.zzbzR.getPath());

         ParcelFileDescriptor var2;
         try {
            var2 = ParcelFileDescriptor.open(var1, 268435456);
         } catch (FileNotFoundException var14) {
            String var4 = String.valueOf(var1);
            Log.w("WearableClient", (new StringBuilder(46 + String.valueOf(var4).length())).append("File couldn't be opened for Channel.sendFile: ").append(var4).toString());
            this.zzbTq.zzr(new Status(13));
            return;
         }

         try {
            ((zzdn)this.zzbTr.zzrf()).zza(new zzfs(this.zzbTq), this.zzakq, var2, this.zzbSm, this.zzbSn);
            return;
         } catch (RemoteException var15) {
            Log.w("WearableClient", "Channel.sendFile failed.", var15);
            this.zzbTq.zzr(new Status(8));
         } finally {
            try {
               var2.close();
            } catch (IOException var13) {
               Log.w("WearableClient", "Failed to close sourceFd", var13);
            }

         }

      }
   }
}
