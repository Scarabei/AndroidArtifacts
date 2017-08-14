package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.atomic.AtomicReference;

final class zzaxz extends zzaym {
   private final AtomicReference zzayh;
   private final Handler mHandler;

   public zzaxz(zzaxx var1) {
      this.zzayh = new AtomicReference(var1);
      this.mHandler = new Handler(var1.getLooper());
   }

   public final zzaxx zzoI() {
      zzaxx var1;
      if ((var1 = (zzaxx)this.zzayh.getAndSet((Object)null)) == null) {
         return null;
      } else {
         zzaxx.zzb(var1);
         return var1;
      }
   }

   public final boolean isDisposed() {
      return this.zzayh.get() == null;
   }

   public final void zzae(int var1) {
      zzaxx var2;
      if ((var2 = this.zzoI()) != null) {
         zzaxx.zzoF().zzb("ICastDeviceControllerListener.onDisconnected: %d", var1);
         if (var1 != 0) {
            var2.zzay(2);
         }

      }
   }

   public final void zza(ApplicationMetadata var1, String var2, String var3, boolean var4) {
      zzaxx var5;
      if ((var5 = (zzaxx)this.zzayh.get()) != null) {
         zzaxx.zza(var5, var1);
         zzaxx.zza(var5, var1.getApplicationId());
         zzaxx.zzb(var5, var3);
         zzaxx.zzc(var5, var2);
         synchronized(zzaxx.zzoG()) {
            if (zzaxx.zzc(var5) != null) {
               zzaxx.zzc(var5).setResult(new zzaxy(new Status(0), var1, var2, var3, var4));
               zzaxx.zza((zzaxx)var5, (zzbaz)null);
            }

         }
      }
   }

   public final void zzZ(int var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         synchronized(zzaxx.zzoG()) {
            if (zzaxx.zzc(var2) != null) {
               zzaxx.zzc(var2).setResult(new zzaxy(new Status(var1)));
               zzaxx.zza((zzaxx)var2, (zzbaz)null);
            }

         }
      }
   }

   public final void zzaf(int var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         zza(var2, var1);
      }
   }

   public final void zzag(int var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         zza(var2, var1);
      }
   }

   public final void onApplicationDisconnected(int var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         zzaxx.zza((zzaxx)var2, (String)null);
         zzaxx.zzb(var2, (String)null);
         zza(var2, var1);
         if (zzaxx.zzd(var2) != null) {
            this.mHandler.post(new zzaya(this, var2, var1));
         }

      }
   }

   public final void zza(String var1, double var2, boolean var4) {
      zzaxx.zzoF().zzb("Deprecated callback: \"onStatusreceived\"");
   }

   public final void zzb(zzayf var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         zzaxx.zzoF().zzb("onDeviceStatusChanged");
         this.mHandler.post(new zzayb(this, var2, var1));
      }
   }

   public final void zzb(zzaxq var1) {
      zzaxx var2;
      if ((var2 = (zzaxx)this.zzayh.get()) != null) {
         zzaxx.zzoF().zzb("onApplicationStatusChanged");
         this.mHandler.post(new zzayc(this, var2, var1));
      }
   }

   public final void zzu(String var1, String var2) {
      zzaxx var3;
      if ((var3 = (zzaxx)this.zzayh.get()) != null) {
         zzaxx.zzoF().zzb("Receive (type=text, ns=%s) %s", var1, var2);
         this.mHandler.post(new zzayd(this, var3, var1, var2));
      }
   }

   public final void zza(String var1, byte[] var2) {
      if ((zzaxx)this.zzayh.get() != null) {
         zzaxx.zzoF().zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", var1, var2.length);
      }
   }

   public final void zza(String var1, long var2, int var4) {
      zzaxx var5;
      if ((var5 = (zzaxx)this.zzayh.get()) != null) {
         zza(var5, var2, var4);
      }
   }

   public final void zzb(String var1, long var2) {
      zzaxx var4;
      if ((var4 = (zzaxx)this.zzayh.get()) != null) {
         zza((zzaxx)var4, var2, 0);
      }
   }

   private static void zza(zzaxx var0, long var1, int var3) {
      zzbaz var4;
      synchronized(zzaxx.zzg(var0)) {
         var4 = (zzbaz)zzaxx.zzg(var0).remove(var1);
      }

      if (var4 != null) {
         var4.setResult(new Status(var3));
      }

   }

   private static boolean zza(zzaxx var0, int var1) {
      synchronized(zzaxx.zzoH()) {
         if (zzaxx.zzh(var0) != null) {
            zzaxx.zzh(var0).setResult(new Status(var1));
            zzaxx.zzb(var0, (zzbaz)null);
            return true;
         } else {
            return false;
         }
      }
   }
}
