package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzq;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public final class zze {
   private static String zzbgZ = null;
   private static boolean zzbha = false;
   private static int zzbhb = 0;
   private static int zzbhc = 0;
   private static int zzbhd = 0;
   private static BroadcastReceiver zzbhe = null;
   private Context zzqD;
   private Map zzbhf = new HashMap();
   private Messenger zzbfT;
   private Messenger zzbhg;
   private MessengerCompat zzbhh;
   private PendingIntent zzbfP;
   private long zzbhi;
   private long zzbhj;
   private int zzbhk;
   private int zzbhl;
   private long zzbhm;

   public zze(Context var1) {
      this.zzqD = var1;
   }

   public static boolean zzbc(Context var0) {
      if (zzbgZ != null) {
         zzbd(var0);
      }

      return zzbha;
   }

   public static String zzbd(Context var0) {
      if (zzbgZ != null) {
         return zzbgZ;
      } else {
         zzbhb = Process.myUid();
         PackageManager var1 = var0.getPackageManager();
         if (!zzq.isAtLeastO()) {
            PackageManager var2 = var1;
            Iterator var3 = var1.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();

            boolean var10000;
            while(true) {
               if (!var3.hasNext()) {
                  var10000 = false;
                  break;
               }

               String var4 = ((ResolveInfo)var3.next()).serviceInfo.packageName;
               if (zza(var2, var4, "com.google.android.c2dm.intent.REGISTER")) {
                  zzbha = false;
                  var10000 = true;
                  break;
               }
            }

            if (var10000) {
               return zzbgZ;
            }
         }

         if (zza(var1)) {
            return zzbgZ;
         } else {
            Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
            if (zzb(var1, "com.google.android.gms")) {
               zzbha = zzq.isAtLeastO();
               return zzbgZ;
            } else if (!zzq.zzse() && zzb(var1, "com.google.android.gsf")) {
               zzbha = false;
               return zzbgZ;
            } else {
               Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
               return null;
            }
         }
      }
   }

   private static boolean zza(PackageManager var0) {
      Iterator var1 = var0.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();

      String var2;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         var2 = ((ResolveInfo)var1.next()).activityInfo.packageName;
      } while(!zza(var0, var2, "com.google.iid.TOKEN_REQUEST"));

      zzbha = true;
      return true;
   }

   private static boolean zza(PackageManager var0, String var1, String var2) {
      if (0 == var0.checkPermission("com.google.android.c2dm.permission.SEND", var1)) {
         return zzb(var0, var1);
      } else {
         Log.w("InstanceID/Rpc", (new StringBuilder(56 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Possible malicious package ").append(var1).append(" declares ").append(var2).append(" without permission").toString());
         return false;
      }
   }

   private static boolean zzb(PackageManager var0, String var1) {
      try {
         ApplicationInfo var2;
         zzbgZ = (var2 = var0.getApplicationInfo(var1, 0)).packageName;
         zzbhc = var2.uid;
         return true;
      } catch (NameNotFoundException var3) {
         return false;
      }
   }

   private static int zzbe(Context var0) {
      PackageManager var1 = var0.getPackageManager();

      try {
         return var1.getPackageInfo(zzbd(var0), 0).versionCode;
      } catch (NameNotFoundException var2) {
         return -1;
      }
   }

   private static String zza(KeyPair var0, String... var1) {
      byte[] var2;
      try {
         var2 = TextUtils.join("\n", var1).getBytes("UTF-8");
      } catch (UnsupportedEncodingException var6) {
         Log.e("InstanceID/Rpc", "Unable to encode string", var6);
         return null;
      }

      try {
         PrivateKey var3;
         Signature var4;
         (var4 = Signature.getInstance((var3 = var0.getPrivate()) instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA")).initSign(var3);
         var4.update(var2);
         return InstanceID.zzj(var4.sign());
      } catch (GeneralSecurityException var5) {
         Log.e("InstanceID/Rpc", "Unable to sign registration request", var5);
         return null;
      }
   }

   private final void zzvN() {
      if (this.zzbfT == null) {
         zzbd(this.zzqD);
         this.zzbfT = new Messenger(new zzf(this, Looper.getMainLooper()));
      }
   }

   public final void zzc(Message var1) {
      if (var1 != null) {
         if (var1.obj instanceof Intent) {
            Intent var2;
            (var2 = (Intent)var1.obj).setExtrasClassLoader(MessengerCompat.class.getClassLoader());
            if (var2.hasExtra("google.messenger")) {
               Parcelable var3;
               if ((var3 = var2.getParcelableExtra("google.messenger")) instanceof MessengerCompat) {
                  this.zzbhh = (MessengerCompat)var3;
               }

               if (var3 instanceof Messenger) {
                  this.zzbhg = (Messenger)var3;
               }
            }

            this.zzi((Intent)var1.obj);
         } else {
            Log.w("InstanceID/Rpc", "Dropping invalid message");
         }
      }
   }

   private final synchronized void zzg(Intent var1) {
      if (this.zzbfP == null) {
         Intent var2;
         (var2 = new Intent()).setPackage("com.google.example.invalidpackage");
         this.zzbfP = PendingIntent.getBroadcast(this.zzqD, 0, var2, 0);
      }

      var1.putExtra("app", this.zzbfP);
   }

   static String zzh(Intent var0) throws IOException {
      if (var0 == null) {
         throw new IOException("SERVICE_NOT_AVAILABLE");
      } else {
         String var1;
         if ((var1 = var0.getStringExtra("registration_id")) == null) {
            var1 = var0.getStringExtra("unregistered");
         }

         var0.getLongExtra("Retry-After", 0L);
         if (var1 == null) {
            if ((var1 = var0.getStringExtra("error")) != null) {
               throw new IOException(var1);
            } else {
               String var2 = String.valueOf(var0.getExtras());
               Log.w("InstanceID/Rpc", (new StringBuilder(29 + String.valueOf(var2).length())).append("Unexpected response from GCM ").append(var2).toString(), new Throwable());
               throw new IOException("SERVICE_NOT_AVAILABLE");
            }
         } else {
            return var1;
         }
      }
   }

   private final void zzB(Object var1) {
      synchronized(this.getClass()) {
         Iterator var3 = this.zzbhf.keySet().iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            Object var5 = this.zzbhf.get(var4);
            this.zzbhf.put(var4, var1);
            zze(var5, var1);
         }

      }
   }

   private final void zzi(String var1, Object var2) {
      synchronized(this.getClass()) {
         Object var4 = this.zzbhf.get(var1);
         this.zzbhf.put(var1, var2);
         zze(var4, var2);
      }
   }

   private static void zze(Object var0, Object var1) {
      if (var0 instanceof ConditionVariable) {
         ((ConditionVariable)var0).open();
      }

      if (var0 instanceof Messenger) {
         Messenger var2 = (Messenger)var0;
         Message var3;
         (var3 = Message.obtain()).obj = var1;

         try {
            var2.send(var3);
            return;
         } catch (RemoteException var6) {
            String var5 = String.valueOf(var6);
            Log.w("InstanceID/Rpc", (new StringBuilder(24 + String.valueOf(var5).length())).append("Failed to send response ").append(var5).toString());
         }
      }

   }

   public final void zzi(Intent var1) {
      if (var1 == null) {
         if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
         }

      } else {
         String var2 = var1.getAction();
         String var10001;
         String var10002;
         String var10003;
         if (!"com.google.android.c2dm.intent.REGISTRATION".equals(var2) && !"com.google.android.gms.iid.InstanceID".equals(var2)) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
               var10002 = String.valueOf(var1.getAction());
               if (var10002.length() != 0) {
                  var10001 = "Unexpected response ".concat(var10002);
               } else {
                  var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Unexpected response ");
               }

               Log.d("InstanceID/Rpc", var10001);
            }

         } else {
            String var3;
            if ((var3 = var1.getStringExtra("registration_id")) == null) {
               var3 = var1.getStringExtra("unregistered");
            }

            if (var3 != null) {
               this.zzbhi = SystemClock.elapsedRealtime();
               this.zzbhm = 0L;
               this.zzbhk = 0;
               this.zzbhl = 0;
               String var4 = null;
               if (var3.startsWith("|")) {
                  String[] var5 = var3.split("\\|");
                  if (!"ID".equals(var5[1])) {
                     var10002 = String.valueOf(var3);
                     if (var10002.length() != 0) {
                        var10001 = "Unexpected structured response ".concat(var10002);
                     } else {
                        var10003 = new String;
                        var10001 = var10003;
                        var10003.<init>("Unexpected structured response ");
                     }

                     Log.w("InstanceID/Rpc", var10001);
                  }

                  var4 = var5[2];
                  if (var5.length > 4) {
                     if ("SYNC".equals(var5[3])) {
                        Context var6 = this.zzqD;
                        Intent var7;
                        (var7 = new Intent("com.google.android.gms.iid.InstanceID")).putExtra("CMD", "SYNC");
                        var7.setClassName(var6, "com.google.android.gms.gcm.GcmReceiver");
                        var6.sendBroadcast(var7);
                     } else if ("RST".equals(var5[3])) {
                        Context var10000 = this.zzqD;
                        InstanceID.getInstance(this.zzqD);
                        InstanceIDListenerService.zza(var10000, InstanceID.zzvM());
                        var1.removeExtra("registration_id");
                        this.zzi(var4, var1);
                        return;
                     }
                  }

                  if ((var3 = var5[var5.length - 1]).startsWith(":")) {
                     var3 = var3.substring(1);
                  }

                  var1.putExtra("registration_id", var3);
               }

               if (var4 == null) {
                  this.zzB(var1);
               } else {
                  this.zzi(var4, var1);
               }
            } else {
               String var8;
               String var9;
               if ((var8 = var1.getStringExtra("error")) == null) {
                  var9 = String.valueOf(var1.getExtras());
                  Log.w("InstanceID/Rpc", (new StringBuilder(49 + String.valueOf(var9).length())).append("Unexpected response, no error or registration id ").append(var9).toString());
               } else {
                  if (Log.isLoggable("InstanceID/Rpc", 3)) {
                     var10002 = String.valueOf(var8);
                     if (var10002.length() != 0) {
                        var10001 = "Received InstanceID error ".concat(var10002);
                     } else {
                        var10003 = new String;
                        var10001 = var10003;
                        var10003.<init>("Received InstanceID error ");
                     }

                     Log.d("InstanceID/Rpc", var10001);
                  }

                  var9 = null;
                  if (var8.startsWith("|")) {
                     String[] var10 = var8.split("\\|");
                     if (!"ID".equals(var10[1])) {
                        var10002 = String.valueOf(var8);
                        if (var10002.length() != 0) {
                           var10001 = "Unexpected structured response ".concat(var10002);
                        } else {
                           var10003 = new String;
                           var10001 = var10003;
                           var10003.<init>("Unexpected structured response ");
                        }

                        Log.w("InstanceID/Rpc", var10001);
                     }

                     if (var10.length > 2) {
                        var9 = var10[2];
                        if ((var8 = var10[3]).startsWith(":")) {
                           var8 = var8.substring(1);
                        }
                     } else {
                        var8 = "UNKNOWN";
                     }

                     var1.putExtra("error", var8);
                  }

                  if (var9 == null) {
                     this.zzB(var8);
                  } else {
                     this.zzi(var9, var8);
                  }

                  long var16;
                  if ((var16 = var1.getLongExtra("Retry-After", 0L)) > 0L) {
                     this.zzbhj = SystemClock.elapsedRealtime();
                     this.zzbhl = (int)var16 * 1000;
                     this.zzbhm = SystemClock.elapsedRealtime() + (long)this.zzbhl;
                     int var12 = this.zzbhl;
                     Log.w("InstanceID/Rpc", (new StringBuilder(52)).append("Explicit request from server to backoff: ").append(var12).toString());
                  } else {
                     if (("SERVICE_NOT_AVAILABLE".equals(var8) || "AUTHENTICATION_FAILED".equals(var8)) && "com.google.android.gsf".equals(zzbgZ)) {
                        ++this.zzbhk;
                        if (this.zzbhk >= 3) {
                           if (this.zzbhk == 3) {
                              this.zzbhl = 1000 + (new Random()).nextInt(1000);
                           }

                           this.zzbhl <<= 1;
                           this.zzbhm = SystemClock.elapsedRealtime() + (long)this.zzbhl;
                           int var15 = this.zzbhl;
                           Log.w("InstanceID/Rpc", (new StringBuilder(31 + String.valueOf(var8).length())).append("Backoff due to ").append(var8).append(" for ").append(var15).toString());
                        }
                     }

                  }
               }
            }
         }
      }
   }

   final Intent zza(Bundle var1, KeyPair var2) throws IOException {
      Intent var3;
      if ((var3 = this.zzb(var1, var2)) != null && var3.hasExtra("google.messenger") && (var3 = this.zzb(var1, var2)) != null && var3.hasExtra("google.messenger")) {
         var3 = null;
      }

      return var3;
   }

   private static synchronized String zzvO() {
      return Integer.toString(zzbhd++);
   }

   private final Intent zzb(Bundle var1, KeyPair var2) throws IOException {
      ConditionVariable var3 = new ConditionVariable();
      String var4 = zzvO();
      synchronized(this.getClass()) {
         this.zzbhf.put(var4, var3);
      }

      long var13 = SystemClock.elapsedRealtime();
      if (this.zzbhm != 0L && var13 <= this.zzbhm) {
         long var34 = this.zzbhm - var13;
         int var35 = this.zzbhl;
         Log.w("InstanceID/Rpc", (new StringBuilder(78)).append("Backoff mode, next request attempt: ").append(var34).append(" interval: ").append(var35).toString());
         throw new IOException("RETRY_LATER");
      } else {
         this.zzvN();
         if (zzbgZ == null) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
         } else {
            this.zzbhi = SystemClock.elapsedRealtime();
            Intent var15;
            (var15 = new Intent(zzbha ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER")).setPackage(zzbgZ);
            int var16 = zzbe(this.zzqD);
            var1.putString("gmsv", Integer.toString(var16));
            var1.putString("osv", Integer.toString(VERSION.SDK_INT));
            var1.putString("app_ver", Integer.toString(InstanceID.zzba(this.zzqD)));
            var1.putString("app_ver_name", InstanceID.zzbb(this.zzqD));
            var1.putString("cliv", "iid-11020000");
            var1.putString("appid", InstanceID.zza(var2));
            String var17 = InstanceID.zzj(var2.getPublic().getEncoded());
            var1.putString("pub2", var17);
            var1.putString("sig", zza(var2, this.zzqD.getPackageName(), var17));
            var15.putExtras(var1);
            this.zzg(var15);
            zze var18 = this;
            this.zzbhi = SystemClock.elapsedRealtime();
            var15.putExtra("kid", (new StringBuilder(5 + String.valueOf(var4).length())).append("|ID|").append(var4).append("|").toString());
            var15.putExtra("X-kid", (new StringBuilder(5 + String.valueOf(var4).length())).append("|ID|").append(var4).append("|").toString());
            boolean var21 = "com.google.android.gsf".equals(zzbgZ);
            String var22;
            if ((var22 = var15.getStringExtra("useGsf")) != null) {
               var21 = "1".equals(var22);
            }

            if (Log.isLoggable("InstanceID/Rpc", 3)) {
               String var23 = String.valueOf(var15.getExtras());
               Log.d("InstanceID/Rpc", (new StringBuilder(8 + String.valueOf(var23).length())).append("Sending ").append(var23).toString());
            }

            label104: {
               Message var33;
               if (this.zzbhg != null) {
                  var15.putExtra("google.messenger", this.zzbfT);
                  (var33 = Message.obtain()).obj = var15;

                  try {
                     var18.zzbhg.send(var33);
                     break label104;
                  } catch (RemoteException var32) {
                     if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                     }
                  }
               }

               if (var21) {
                  zze var24 = this;
                  synchronized(this) {
                     if (zzbhe == null) {
                        zzbhe = new zzg(var24);
                        if (Log.isLoggable("InstanceID/Rpc", 3)) {
                           Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
                        }

                        IntentFilter var26;
                        (var26 = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION")).addCategory(var24.zzqD.getPackageName());
                        var24.zzqD.registerReceiver(zzbhe, var26, "com.google.android.c2dm.permission.SEND", (Handler)null);
                     }
                  }

                  this.zzqD.sendBroadcast(var15);
               } else {
                  label87: {
                     var15.putExtra("google.messenger", this.zzbfT);
                     var15.putExtra("messenger2", "1");
                     if (this.zzbhh != null) {
                        (var33 = Message.obtain()).obj = var15;

                        try {
                           var18.zzbhh.send(var33);
                           break label87;
                        } catch (RemoteException var30) {
                           if (Log.isLoggable("InstanceID/Rpc", 3)) {
                              Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                           }
                        }
                     }

                     if (zzbha) {
                        this.zzqD.sendBroadcast(var15);
                     } else {
                        this.zzqD.startService(var15);
                     }
                  }
               }
            }

            var3.block(30000L);
            synchronized(this.getClass()) {
               Object var6;
               if ((var6 = this.zzbhf.remove(var4)) instanceof Intent) {
                  return (Intent)var6;
               } else if (var6 instanceof String) {
                  throw new IOException((String)var6);
               } else {
                  String var7 = String.valueOf(var6);
                  Log.w("InstanceID/Rpc", (new StringBuilder(12 + String.valueOf(var7).length())).append("No response ").append(var7).toString());
                  throw new IOException("TIMEOUT");
               }
            }
         }
      }
   }
}
