package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class co {
   private final Context mContext;
   private final ct zzbKL;
   private final ExecutorService zzbrV;

   public co(Context var1) {
      this(var1, Executors.newSingleThreadExecutor(), new cp(var1));
   }

   private co(Context var1, ExecutorService var2, ct var3) {
      this.mContext = var1;
      this.zzbrV = var2;
      this.zzbKL = var3;
   }

   public final void zza(String var1, cc var2) {
      this.zzbrV.execute(new cq(this, var1, var2));
   }

   final void zzb(String var1, cc var2) {
      zzcvk.v("Starting to load a saved resource file from Disk.");

      try {
         byte[] var3 = zzj(new FileInputStream(this.zzfS(var1)));
         var2.zzv(var3);
      } catch (FileNotFoundException var4) {
         String var10001 = String.valueOf(zzfT(var1));
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Saved resource not found: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Saved resource not found: ");
         }

         zzcvk.e(var10000);
         var2.zzk(0, 1);
      }
   }

   public final void zza(String var1, String var2, cc var3) {
      this.zzbrV.execute(new cr(this, var1, var2, var3));
   }

   final void zzb(String var1, String var2, cc var3) {
      zzcvk.v("Starting to load a default asset file from Disk.");
      if (var2 == null) {
         zzcvk.v("Default asset file is not specified. Not proceeding with the loading");
         var3.zzk(0, 2);
      } else {
         try {
            InputStream var4;
            if ((var4 = this.zzbKL.open(var2)) != null) {
               byte[] var5 = zzj(var4);
               var3.zzv(var5);
            } else {
               var3.zzk(0, 2);
            }
         } catch (IOException var6) {
            zzcvk.e((new StringBuilder(42 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Default asset file not found. ").append(var1).append(". Filename: ").append(var2).toString());
            var3.zzk(0, 2);
         }
      }
   }

   public final void zzd(String var1, byte[] var2) {
      this.zzbrV.execute(new cs(this, var1, var2));
   }

   final void zze(String var1, byte[] var2) {
      File var4 = this.zzfS(var1);

      FileOutputStream var3;
      try {
         var3 = new FileOutputStream(var4);
      } catch (FileNotFoundException var13) {
         zzcvk.e("Error opening resource file for writing");
         return;
      }

      try {
         var3.write(var2);
         return;
      } catch (IOException var14) {
         zzcvk.e("Error writing resource to disk. Removing resource from disk");
         var4.delete();
      } finally {
         try {
            var3.close();
            zzcvk.v((new StringBuilder(24 + String.valueOf(var1).length())).append("Resource ").append(var1).append(" saved on Disk.").toString());
         } catch (IOException var12) {
            zzcvk.e("Error closing stream for writing resource to disk");
         }

      }

   }

   public final long zzfR(String var1) {
      File var2;
      return (var2 = this.zzfS(var1)).exists() ? var2.lastModified() : 0L;
   }

   private final File zzfS(String var1) {
      File var2 = this.mContext.getDir("google_tagmanager", 0);
      return new File(var2, zzfT(var1));
   }

   private static String zzfT(String var0) {
      String var10000 = String.valueOf("resource_");
      String var10001 = String.valueOf(var0);
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }

   private static byte[] zzj(InputStream var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      try {
         zzn.zza(var0, var1, false);
      } catch (IOException var10) {
         zzcvk.zzaT("Failed to read the resource from disk");
      } finally {
         try {
            var0.close();
         } catch (IOException var9) {
            zzcvk.zzaT("Error closing stream for reading resource from disk");
            return null;
         }
      }

      return var1.toByteArray();
   }
}
