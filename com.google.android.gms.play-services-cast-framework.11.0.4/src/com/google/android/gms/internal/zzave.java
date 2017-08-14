package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;

public final class zzave extends AsyncTask {
   private static final zzayo zzarK = new zzayo("FetchBitmapTask");
   private final zzavj zzauV;
   private final zzavg zzauW;

   public zzave(Context var1, zzavg var2) {
      this(var1, 0, 0, false, 2097152L, 5, 333, 10000, var2);
   }

   public zzave(Context var1, int var2, int var3, boolean var4, zzavg var5) {
      this(var1, var2, var3, false, 2097152L, 5, 333, 10000, var5);
   }

   private zzave(Context var1, int var2, int var3, boolean var4, long var5, int var7, int var8, int var9, zzavg var10) {
      this.zzauV = zzauj.zza(var1.getApplicationContext(), this, new zzavi(this, (zzavf)null), var2, var3, var4, 2097152L, 5, 333, 10000);
      this.zzauW = var10;
   }

   private final Bitmap doInBackground(Uri... var1) {
      if (var1.length == 1 && var1[0] != null) {
         try {
            return this.zzauV.zzn(var1[0]);
         } catch (RemoteException var3) {
            zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"doFetch", zzavj.class.getSimpleName()});
            return null;
         }
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   protected final void onPostExecute(Object var1) {
      Bitmap var3 = (Bitmap)var1;
      if (this.zzauW != null) {
         this.zzauW.onPostExecute(var3);
      }

   }

   // $FF: synthetic method
   static void zza(zzave var0, Object[] var1) {
      var0.publishProgress(var1);
   }
}
