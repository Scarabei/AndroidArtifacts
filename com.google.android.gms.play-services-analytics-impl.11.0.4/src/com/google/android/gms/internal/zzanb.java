package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzanb extends zzamh {
   private volatile String zzafd;
   private Future zzagN;

   protected zzanb(zzamj var1) {
      super(var1);
   }

   protected final void zzjD() {
   }

   public final String zzli() {
      this.zzkD();
      synchronized(this) {
         if (this.zzafd == null) {
            this.zzagN = this.zzkt().zzd(new zzanc(this));
         }

         if (this.zzagN != null) {
            try {
               this.zzafd = (String)this.zzagN.get();
            } catch (InterruptedException var4) {
               this.zzd("ClientId loading or generation was interrupted", var4);
               this.zzafd = "0";
            } catch (ExecutionException var5) {
               this.zze("Failed to load or generate client id", var5);
               this.zzafd = "0";
            }

            if (this.zzafd == null) {
               this.zzafd = "0";
            }

            this.zza("Loaded clientId", this.zzafd);
            this.zzagN = null;
         }

         return this.zzafd;
      }
   }

   final String zzlj() {
      synchronized(this) {
         this.zzafd = null;
         this.zzagN = this.zzkt().zzd(new zzand(this));
      }

      return this.zzli();
   }

   final String zzlk() {
      String var1;
      if ((var1 = this.zzag(this.zzkt().getContext())) == null) {
         var1 = this.zzll();
      }

      return var1;
   }

   private final String zzll() {
      String var1 = UUID.randomUUID().toString().toLowerCase();

      try {
         return !this.zzv(this.zzkt().getContext(), var1) ? "0" : var1;
      } catch (Exception var3) {
         this.zze("Error saving clientId file", var3);
         return "0";
      }
   }

   private final String zzag(Context var1) {
      zzbo.zzcG("ClientId should be loaded from worker thread");
      FileInputStream var2 = null;

      try {
         var2 = var1.openFileInput("gaClientId");
         byte[] var3 = new byte[36];
         int var4 = var2.read(var3, 0, 36);
         if (var2.available() > 0) {
            this.zzbr("clientId file seems corrupted, deleting it.");
            var2.close();
            var1.deleteFile("gaClientId");
            return null;
         }

         if (var4 >= 14) {
            var2.close();
            String var5 = new String(var3, 0, var4);
            this.zza("Read client id from disk", var5);
            String var6 = var5;
            return var6;
         }

         this.zzbr("clientId file is empty, deleting it.");
         var2.close();
         var1.deleteFile("gaClientId");
      } catch (FileNotFoundException var20) {
         return null;
      } catch (IOException var21) {
         this.zze("Error reading client id file, deleting it", var21);
         var1.deleteFile("gaClientId");
         return null;
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var19) {
               this.zze("Failed to close client id reading stream", var19);
            }
         }

      }

      return null;
   }

   private final boolean zzv(Context var1, String var2) {
      zzbo.zzcF(var2);
      zzbo.zzcG("ClientId should be saved from worker thread");
      FileOutputStream var3 = null;

      try {
         this.zza("Storing clientId", var2);
         (var3 = var1.openFileOutput("gaClientId", 0)).write(var2.getBytes());
         return true;
      } catch (FileNotFoundException var17) {
         this.zze("Error creating clientId file", var17);
         return false;
      } catch (IOException var18) {
         this.zze("Error writing to clientId file", var18);
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (IOException var16) {
               this.zze("Failed to close clientId writing stream", var16);
            }
         }

      }

      return false;
   }

   // $FF: synthetic method
   static String zza(zzanb var0) {
      return var0.zzll();
   }
}
