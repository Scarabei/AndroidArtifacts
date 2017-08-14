package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.ado;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ek;
import com.google.android.gms.internal.eo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

final class zzey implements zzah {
   private final Context mContext;
   private final String zzbDw;
   private final ExecutorService zzbrV;
   private zzdi zzbFV;

   zzey(Context var1, String var2) {
      this.mContext = var1;
      this.zzbDw = var2;
      this.zzbrV = Executors.newSingleThreadExecutor();
   }

   public final void zza(zzdi var1) {
      this.zzbFV = var1;
   }

   public final void zzAR() {
      this.zzbrV.execute(new zzez(this));
   }

   final void zzBI() {
      if (this.zzbFV == null) {
         throw new IllegalStateException("Callback must be set before execute");
      } else {
         zzdj.v("Attempting to load resource from disk");
         if ((zzei.zzBD().zzBE() == zzei.zza.zzbFK || zzei.zzBD().zzBE() == zzei.zza.zzbFL) && this.zzbDw.equals(zzei.zzBD().getContainerId())) {
            this.zzbFV.zzbw(zzda.zzbFh);
         } else {
            FileInputStream var1;
            try {
               var1 = new FileInputStream(this.zzBJ());
            } catch (FileNotFoundException var18) {
               zzdj.zzaC("Failed to find the resource in the disk");
               this.zzbFV.zzbw(zzda.zzbFh);
               return;
            }

            try {
               ByteArrayOutputStream var2 = new ByteArrayOutputStream();
               eg.zzb(var1, var2);
               byte[] var5 = var2.toByteArray();
               ee var19;
               ee var3;
               if ((var19 = var3 = (ee)adp.zza(new ee(), var5)).zzlB == null && var19.zzbLH == null) {
                  throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
               }

               this.zzbFV.onSuccess(var3);
            } catch (IOException var15) {
               this.zzbFV.zzbw(zzda.zzbFi);
               zzdj.zzaT("Failed to read the resource from disk");
            } catch (IllegalArgumentException var16) {
               this.zzbFV.zzbw(zzda.zzbFi);
               zzdj.zzaT("Failed to read the resource from disk. The resource is inconsistent");
            } finally {
               try {
                  var1.close();
               } catch (IOException var14) {
                  zzdj.zzaT("Error closing stream for reading resource from disk");
               }

            }

            zzdj.v("The Disk resource was successfully read.");
         }
      }
   }

   public final void zza(ee var1) {
      this.zzbrV.execute(new zzfa(this, var1));
   }

   public final ek zzbx(int var1) {
      InputStream var2;
      try {
         var2 = this.mContext.getResources().openRawResource(var1);
      } catch (NotFoundException var6) {
         zzdj.zzaT((new StringBuilder(98)).append("Failed to load the container. No default container resource found with the resource ID ").append(var1).toString());
         return null;
      }

      String var3 = String.valueOf(this.mContext.getResources().getResourceName(var1));
      zzdj.v((new StringBuilder(66 + String.valueOf(var3).length())).append("Attempting to load a container from the resource ID ").append(var1).append(" (").append(var3).append(")").toString());

      try {
         ByteArrayOutputStream var7 = new ByteArrayOutputStream();
         eg.zzb(var2, var7);
         ek var8;
         if ((var8 = zza(var7)) != null) {
            zzdj.v("The container was successfully loaded from the resource (using JSON file format)");
            return var8;
         } else {
            return zzu(var7.toByteArray());
         }
      } catch (IOException var5) {
         String var4 = String.valueOf(this.mContext.getResources().getResourceName(var1));
         zzdj.zzaT((new StringBuilder(67 + String.valueOf(var4).length())).append("Error reading the default container with resource ID ").append(var1).append(" (").append(var4).append(")").toString());
         return null;
      }
   }

   private static ek zza(ByteArrayOutputStream var0) {
      try {
         return zzdb.zzfo(var0.toString("UTF-8"));
      } catch (UnsupportedEncodingException var1) {
         zzdj.zzaC("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
         return null;
      } catch (JSONException var2) {
         zzdj.zzaT("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
         return null;
      }
   }

   private static ek zzu(byte[] var0) {
      try {
         ek var1;
         if ((var1 = eg.zza((com.google.android.gms.internal.zzbn)adp.zza(new com.google.android.gms.internal.zzbn(), var0))) != null) {
            zzdj.v("The container was successfully loaded from the resource (using binary file)");
         }

         return var1;
      } catch (ado var3) {
         zzdj.e("The resource file is corrupted. The container cannot be extracted from the binary file");
         return null;
      } catch (eo var4) {
         zzdj.zzaT("The resource file is invalid. The container from the binary file is invalid");
         return null;
      }
   }

   public final synchronized void release() {
      this.zzbrV.shutdown();
   }

   final boolean zzb(ee var1) {
      File var3 = this.zzBJ();

      FileOutputStream var2;
      try {
         var2 = new FileOutputStream(var3);
      } catch (FileNotFoundException var12) {
         zzdj.e("Error opening resource file for writing");
         return false;
      }

      try {
         var2.write(adp.zzc(var1));
         return true;
      } catch (IOException var13) {
         zzdj.zzaT("Error writing resource to disk. Removing resource from disk.");
         var3.delete();
      } finally {
         try {
            var2.close();
         } catch (IOException var11) {
            zzdj.zzaT("error closing stream for writing resource to disk");
         }

      }

      return false;
   }

   private final File zzBJ() {
      String var10000 = String.valueOf("resource_");
      String var10001 = String.valueOf(this.zzbDw);
      if (var10001.length() != 0) {
         var10000 = var10000.concat(var10001);
      } else {
         String var10002 = new String;
         var10001 = var10000;
         var10000 = var10002;
         var10002.<init>(var10001);
      }

      String var1 = var10000;
      File var2 = this.mContext.getDir("google_tagmanager", 0);
      return new File(var2, var1);
   }
}
