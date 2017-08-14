package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.iid.InstanceID;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging {
   public static int zzbfL = 5000000;
   private static int zzbfM = 6500000;
   private static int zzbfN = 7000000;
   public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
   public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
   /** @deprecated */
   @Deprecated
   public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
   /** @deprecated */
   @Deprecated
   public static final String MESSAGE_TYPE_MESSAGE = "gcm";
   /** @deprecated */
   @Deprecated
   public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
   /** @deprecated */
   @Deprecated
   public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
   public static final String INSTANCE_ID_SCOPE = "GCM";
   private static GoogleCloudMessaging zzbfO;
   private Context zzqD;
   private PendingIntent zzbfP;
   private Map zzbfQ = Collections.synchronizedMap(new HashMap());
   private static final AtomicInteger zzbfR = new AtomicInteger(1);
   private final BlockingQueue zzbfS = new LinkedBlockingQueue();
   private Messenger zzbfT = new Messenger(new zzc(this, Looper.getMainLooper()));

   public static synchronized GoogleCloudMessaging getInstance(Context var0) {
      if (zzbfO == null) {
         (zzbfO = new GoogleCloudMessaging()).zzqD = var0.getApplicationContext();
      }

      return zzbfO;
   }

   public void close() {
      zzbfO = null;
      zza.zzbfw = null;
      this.zzvD();
   }

   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public void send(String var1, String var2, Bundle var3) throws IOException {
      this.send(var1, var2, -1L, var3);
   }

   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public void send(String var1, String var2, long var3, Bundle var5) throws IOException {
      Bundle var9 = var5;
      if (var1 == null) {
         throw new IllegalArgumentException("Missing 'to'");
      } else {
         String var10;
         if ((var10 = com.google.android.gms.iid.zze.zzbd(this.zzqD)) == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
         } else {
            Intent var11 = new Intent("com.google.android.gcm.intent.SEND");
            if (var5 != null) {
               var11.putExtras(var5);
            }

            this.zzf(var11);
            var11.setPackage(var10);
            var11.putExtra("google.to", var1);
            var11.putExtra("google.message_id", var2);
            var11.putExtra("google.ttl", Long.toString(var3));
            var11.putExtra("google.delay", Integer.toString(-1));
            String var18;
            int var19;
            if ((var19 = var1.indexOf(64)) > 0) {
               var18 = var1.substring(0, var19);
            } else {
               var18 = var1;
            }

            InstanceID.getInstance(this.zzqD);
            var11.putExtra("google.from", InstanceID.zzvM().zzf("", var18, "GCM"));
            if (var10.contains(".gsf")) {
               Bundle var12 = new Bundle();
               Iterator var13 = var5.keySet().iterator();

               while(var13.hasNext()) {
                  String var14 = (String)var13.next();
                  Object var15;
                  if ((var15 = var9.get(var14)) instanceof String) {
                     String var10002 = String.valueOf(var14);
                     String var10001;
                     if (var10002.length() != 0) {
                        var10001 = "gcm.".concat(var10002);
                     } else {
                        String var10003 = new String;
                        var10001 = var10003;
                        var10003.<init>("gcm.");
                     }

                     var12.putString(var10001, (String)var15);
                  }
               }

               var12.putString("google.to", var1);
               var12.putString("google.message_id", var2);
               InstanceID.getInstance(this.zzqD).zzc("GCM", "upstream", var12);
            } else {
               this.zzqD.sendOrderedBroadcast(var11, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            }
         }
      }
   }

   private final boolean zze(Intent var1) {
      String var2;
      if ((var2 = var1.getStringExtra("In-Reply-To")) == null && var1.hasExtra("error")) {
         var2 = var1.getStringExtra("google.message_id");
      }

      Handler var3;
      if (var2 != null && (var3 = (Handler)this.zzbfQ.remove(var2)) != null) {
         Message var4;
         (var4 = Message.obtain()).obj = var1;
         return var3.sendMessage(var4);
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public synchronized void unregister() throws IOException {
      if (Looper.getMainLooper() == Looper.myLooper()) {
         throw new IOException("MAIN_THREAD");
      } else {
         InstanceID.getInstance(this.zzqD).deleteInstanceID();
      }
   }

   /** @deprecated */
   @Deprecated
   @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
   public synchronized String register(String... var1) throws IOException {
      return this.zza(com.google.android.gms.iid.zze.zzbc(this.zzqD), var1);
   }

   /** @deprecated */
   @Deprecated
   private final synchronized String zza(boolean var1, String... var2) throws IOException {
      String var3;
      if ((var3 = com.google.android.gms.iid.zze.zzbd(this.zzqD)) == null) {
         throw new IOException("SERVICE_NOT_AVAILABLE");
      } else {
         String var4 = zzc(var2);
         Bundle var5 = new Bundle();
         if (var3.contains(".gsf")) {
            var5.putString("legacy.sender", var4);
            return InstanceID.getInstance(this.zzqD).getToken(var4, "GCM", var5);
         } else {
            var5.putString("sender", var4);
            Intent var10000 = this.zza(var5, var1);
            String var7 = "registration_id";
            Intent var6 = var10000;
            if (var10000 == null) {
               throw new IOException("SERVICE_NOT_AVAILABLE");
            } else {
               String var8;
               if ((var8 = var6.getStringExtra(var7)) != null) {
                  return var8;
               } else {
                  String var9;
                  if ((var9 = var6.getStringExtra("error")) != null) {
                     throw new IOException(var9);
                  } else {
                     throw new IOException("SERVICE_NOT_AVAILABLE");
                  }
               }
            }
         }
      }
   }

   /** @deprecated */
   @Deprecated
   private final Intent zza(Bundle var1, boolean var2) throws IOException {
      if (Looper.getMainLooper() == Looper.myLooper()) {
         throw new IOException("MAIN_THREAD");
      } else if (zzaZ(this.zzqD) < 0) {
         throw new IOException("Google Play Services missing");
      } else {
         Intent var3;
         (var3 = new Intent(var2 ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER")).setPackage(com.google.android.gms.iid.zze.zzbd(this.zzqD));
         this.zzf(var3);
         String var10000 = String.valueOf("google.rpc");
         String var10001 = String.valueOf(String.valueOf(zzbfR.getAndIncrement()));
         if (var10001.length() != 0) {
            var10000 = var10000.concat(var10001);
         } else {
            String var10002 = new String;
            var10001 = var10000;
            var10000 = var10002;
            var10002.<init>(var10001);
         }

         String var4 = var10000;
         var3.putExtra("google.message_id", var4);
         var3.putExtras(var1);
         var3.putExtra("google.messenger", this.zzbfT);
         if (var2) {
            this.zzqD.sendBroadcast(var3);
         } else {
            this.zzqD.startService(var3);
         }

         try {
            return (Intent)this.zzbfS.poll(30000L, TimeUnit.MILLISECONDS);
         } catch (InterruptedException var6) {
            throw new IOException(var6.getMessage());
         }
      }
   }

   private static String zzc(String... var0) {
      if (var0 != null && var0.length != 0) {
         StringBuilder var1 = new StringBuilder(var0[0]);

         for(int var2 = 1; var2 < var0.length; ++var2) {
            var1.append(',').append(var0[var2]);
         }

         return var1.toString();
      } else {
         throw new IllegalArgumentException("No senderIds");
      }
   }

   public String getMessageType(Intent var1) {
      String var2 = var1.getAction();
      if (!"com.google.android.c2dm.intent.RECEIVE".equals(var2)) {
         return null;
      } else {
         String var3;
         return (var3 = var1.getStringExtra("message_type")) != null ? var3 : "gcm";
      }
   }

   private final synchronized void zzf(Intent var1) {
      if (this.zzbfP == null) {
         Intent var2;
         (var2 = new Intent()).setPackage("com.google.example.invalidpackage");
         this.zzbfP = PendingIntent.getBroadcast(this.zzqD, 0, var2, 0);
      }

      var1.putExtra("app", this.zzbfP);
   }

   private final synchronized void zzvD() {
      if (this.zzbfP != null) {
         this.zzbfP.cancel();
         this.zzbfP = null;
      }

   }

   public static int zzaZ(Context var0) {
      String var1;
      if ((var1 = com.google.android.gms.iid.zze.zzbd(var0)) != null) {
         try {
            PackageInfo var2;
            if ((var2 = var0.getPackageManager().getPackageInfo(var1, 0)) != null) {
               return var2.versionCode;
            }
         } catch (NameNotFoundException var3) {
            ;
         }
      }

      return -1;
   }

   // $FF: synthetic method
   static BlockingQueue zza(GoogleCloudMessaging var0) {
      return var0.zzbfS;
   }

   // $FF: synthetic method
   static boolean zza(GoogleCloudMessaging var0, Intent var1) {
      return var0.zze(var1);
   }

   // $FF: synthetic method
   static Context zzb(GoogleCloudMessaging var0) {
      return var0.zzqD;
   }
}
