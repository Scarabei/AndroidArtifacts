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

final class zzfy implements Runnable {
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private zzbaz zzbTq;
   // $FF: synthetic field
   private boolean zzbSl;
   // $FF: synthetic field
   private String zzakq;
   // $FF: synthetic field
   private zzfw zzbTr;

   zzfy(zzfw var1, Uri var2, zzbaz var3, boolean var4, String var5) {
      this.zzbTr = var1;
      this.zzbzR = var2;
      this.zzbTq = var3;
      this.zzbSl = var4;
      this.zzakq = var5;
      super();
   }

   public final void run() {
      if (Log.isLoggable("WearableClient", 2)) {
         Log.v("WearableClient", "Executing receiveFileFromChannelTask");
      }

      if (!"file".equals(this.zzbzR.getScheme())) {
         Log.w("WearableClient", "Channel.receiveFile used with non-file URI");
         this.zzbTq.zzr(new Status(10, "Channel.receiveFile used with non-file URI"));
      } else {
         File var1 = new File(this.zzbzR.getPath());
         int var2 = 671088640 | (this.zzbSl ? 33554432 : 0);

         ParcelFileDescriptor var3;
         try {
            var3 = ParcelFileDescriptor.open(var1, var2);
         } catch (FileNotFoundException var15) {
            String var5 = String.valueOf(var1);
            Log.w("WearableClient", (new StringBuilder(49 + String.valueOf(var5).length())).append("File couldn't be opened for Channel.receiveFile: ").append(var5).toString());
            this.zzbTq.zzr(new Status(13));
            return;
         }

         try {
            ((zzdn)this.zzbTr.zzrf()).zza(new zzfv(this.zzbTq), (String)this.zzakq, (ParcelFileDescriptor)var3);
            return;
         } catch (RemoteException var16) {
            Log.w("WearableClient", "Channel.receiveFile failed.", var16);
            this.zzbTq.zzr(new Status(8));
         } finally {
            try {
               var3.close();
            } catch (IOException var14) {
               Log.w("WearableClient", "Failed to close targetFd", var14);
            }

         }

      }
   }
}
