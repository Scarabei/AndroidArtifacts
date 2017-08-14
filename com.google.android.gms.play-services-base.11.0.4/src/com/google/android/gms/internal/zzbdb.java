package com.google.android.gms.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbdb implements Callback {
   public static final Status zzaEc = new Status(4, "Sign-out occurred while this API call was in progress.");
   private static final Status zzaEd = new Status(4, "The user must be signed in to make this API call.");
   private long zzaDC = 5000L;
   private long zzaDB = 120000L;
   private long zzaEe = 10000L;
   private static final Object zzuF = new Object();
   private static zzbdb zzaEf;
   private final Context mContext;
   private final GoogleApiAvailability zzaBd;
   private int zzaEg = -1;
   private final AtomicInteger zzaEh = new AtomicInteger(1);
   private final AtomicInteger zzaEi = new AtomicInteger(0);
   private final Map zzaCB = new ConcurrentHashMap(5, 0.75F, 1);
   private zzbbw zzaEj = null;
   private final Set zzaEk = new zza();
   private final Set zzaEl = new zza();
   private final Handler mHandler;

   public static zzbdb zzay(Context var0) {
      Object var1 = zzuF;
      synchronized(zzuF) {
         if (zzaEf == null) {
            HandlerThread var4;
            (var4 = new HandlerThread("GoogleApiHandler", 9)).start();
            Looper var2 = var4.getLooper();
            zzaEf = new zzbdb(var0.getApplicationContext(), var2, GoogleApiAvailability.getInstance());
         }

         return zzaEf;
      }
   }

   public static zzbdb zzqk() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         zzbo.zzb(zzaEf, "Must guarantee manager is non-null before using getInstance");
         return zzaEf;
      }
   }

   public static void zzql() {
      Object var0 = zzuF;
      synchronized(zzuF) {
         if (zzaEf != null) {
            zzbdb var2 = zzaEf;
            zzaEf.zzaEi.incrementAndGet();
            var2.mHandler.sendMessageAtFrontOfQueue(var2.mHandler.obtainMessage(10));
         }

      }
   }

   private zzbdb(Context var1, Looper var2, GoogleApiAvailability var3) {
      this.mContext = var1;
      this.mHandler = new Handler(var2, this);
      this.zzaBd = var3;
      this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
   }

   public final int zzqm() {
      return this.zzaEh.getAndIncrement();
   }

   public final void zzb(GoogleApi var1) {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(7, var1));
   }

   @WorkerThread
   private final void zzc(GoogleApi var1) {
      zzbat var2 = var1.zzph();
      zzbdd var3;
      if ((var3 = (zzbdd)this.zzaCB.get(var2)) == null) {
         var3 = new zzbdd(this, var1);
         this.zzaCB.put(var2, var3);
      }

      if (var3.zzmv()) {
         this.zzaEl.add(var2);
      }

      var3.connect();
   }

   public final void zza(@NonNull zzbbw var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         if (this.zzaEj != var1) {
            this.zzaEj = var1;
            this.zzaEk.clear();
            this.zzaEk.addAll(var1.zzpR());
         }

      }
   }

   final void zzb(@NonNull zzbbw var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         if (this.zzaEj == var1) {
            this.zzaEj = null;
            this.zzaEk.clear();
         }

      }
   }

   public final Task zza(Iterable var1) {
      zzbav var2 = new zzbav(var1);
      Iterator var3 = var1.iterator();

      GoogleApi var4;
      zzbdd var5;
      do {
         if (!var3.hasNext()) {
            var2.zzpu();
            return var2.getTask();
         }

         var4 = (GoogleApi)var3.next();
      } while((var5 = (zzbdd)this.zzaCB.get(var4.zzph())) != null && var5.isConnected());

      this.mHandler.sendMessage(this.mHandler.obtainMessage(2, var2));
      return var2.getTask();
   }

   public final void zzps() {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
   }

   final void zzpl() {
      this.zzaEi.incrementAndGet();
      this.mHandler.sendMessage(this.mHandler.obtainMessage(10));
   }

   @WorkerThread
   private final void zzqn() {
      Iterator var1 = this.zzaEl.iterator();

      while(var1.hasNext()) {
         zzbat var2 = (zzbat)var1.next();
         ((zzbdd)this.zzaCB.remove(var2)).signOut();
      }

      this.zzaEl.clear();
   }

   public final void zza(GoogleApi var1, int var2, zzbay var3) {
      zzbao var4 = new zzbao(var2, var3);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzbed(var4, this.zzaEi.get(), var1)));
   }

   public final void zza(GoogleApi var1, int var2, zzbeq var3, TaskCompletionSource var4, zzbem var5) {
      zzbaq var6 = new zzbaq(var2, var3, var4, var5);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzbed(var6, this.zzaEi.get(), var1)));
   }

   public final Task zza(@NonNull GoogleApi var1, @NonNull zzbee var2, @NonNull zzbey var3) {
      TaskCompletionSource var4 = new TaskCompletionSource();
      zzbap var5 = new zzbap(new zzbef(var2, var3), var4);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(8, new zzbed(var5, this.zzaEi.get(), var1)));
      return var4.getTask();
   }

   public final Task zza(@NonNull GoogleApi var1, @NonNull zzbdy var2) {
      TaskCompletionSource var3 = new TaskCompletionSource();
      zzbar var4 = new zzbar(var2, var3);
      this.mHandler.sendMessage(this.mHandler.obtainMessage(13, new zzbed(var4, this.zzaEi.get(), var1)));
      return var3.getTask();
   }

   @WorkerThread
   public final boolean handleMessage(Message var1) {
      zzbdb var3;
      zzbdd var8;
      zzbdd var14;
      switch(var1.what) {
      case 1:
         boolean var13 = ((Boolean)var1.obj).booleanValue();
         var3 = this;
         this.zzaEe = var13 ? 10000L : 300000L;
         this.mHandler.removeMessages(12);
         Iterator var15 = this.zzaCB.keySet().iterator();

         while(var15.hasNext()) {
            zzbat var17 = (zzbat)var15.next();
            var3.mHandler.sendMessageDelayed(var3.mHandler.obtainMessage(12, var17), var3.zzaEe);
         }

         return true;
      case 2:
         zzbav var12 = (zzbav)var1.obj;
         var3 = this;
         Iterator var16 = var12.zzpt().iterator();

         while(var16.hasNext()) {
            zzbat var19 = (zzbat)var16.next();
            if ((var8 = (zzbdd)var3.zzaCB.get(var19)) == null) {
               ConnectionResult var9 = new ConnectionResult(13);
               var12.zza(var19, var9);
               return true;
            }

            if (var8.isConnected()) {
               var12.zza(var19, ConnectionResult.zzazX);
            } else if (var8.zzqu() != null) {
               var12.zza(var19, var8.zzqu());
            } else {
               var8.zza(var12);
            }
         }

         return true;
      case 3:
         Iterator var11 = this.zzaCB.values().iterator();

         while(var11.hasNext()) {
            (var14 = (zzbdd)var11.next()).zzqt();
            var14.connect();
         }

         return true;
      case 4:
      case 8:
      case 13:
         zzbed var10 = (zzbed)var1.obj;
         if ((var14 = (zzbdd)this.zzaCB.get(var10.zzaET.zzph())) == null) {
            this.zzc(var10.zzaET);
            var14 = (zzbdd)this.zzaCB.get(var10.zzaET.zzph());
         }

         if (var14.zzmv() && this.zzaEi.get() != var10.zzaES) {
            var10.zzaER.zzp(zzaEc);
            var14.signOut();
         } else {
            var14.zza(var10.zzaER);
         }
         break;
      case 5:
         ConnectionResult var5 = (ConnectionResult)var1.obj;
         int var4 = var1.arg1;
         zzbdd var6 = null;
         Iterator var7 = this.zzaCB.values().iterator();

         while(var7.hasNext()) {
            if ((var8 = (zzbdd)var7.next()).getInstanceId() == var4) {
               var6 = var8;
               break;
            }
         }

         if (var6 != null) {
            String var18 = String.valueOf(this.zzaBd.getErrorString(var5.getErrorCode()));
            String var20 = String.valueOf(var5.getErrorMessage());
            var6.zzt(new Status(17, (new StringBuilder(69 + String.valueOf(var18).length() + String.valueOf(var20).length())).append("Error resolution was canceled by the user, original error message: ").append(var18).append(": ").append(var20).toString()));
         } else {
            Log.wtf("GoogleApiManager", (new StringBuilder(76)).append("Could not find API instance ").append(var4).append(" while trying to fail enqueued calls.").toString(), new Exception());
         }
         break;
      case 6:
         if (this.mContext.getApplicationContext() instanceof Application) {
            zzbaw.zza((Application)this.mContext.getApplicationContext());
            zzbaw.zzpv().zza(new zzbdc(this));
            if (!zzbaw.zzpv().zzab(true)) {
               this.zzaEe = 300000L;
            }
         }
         break;
      case 7:
         this.zzc((GoogleApi)var1.obj);
         break;
      case 9:
         if (this.zzaCB.containsKey(var1.obj)) {
            ((zzbdd)this.zzaCB.get(var1.obj)).resume();
         }
         break;
      case 10:
         this.zzqn();
         break;
      case 11:
         if (this.zzaCB.containsKey(var1.obj)) {
            ((zzbdd)this.zzaCB.get(var1.obj)).zzqd();
         }
         break;
      case 12:
         if (this.zzaCB.containsKey(var1.obj)) {
            ((zzbdd)this.zzaCB.get(var1.obj)).zzqx();
         }
         break;
      default:
         int var2 = var1.what;
         Log.w("GoogleApiManager", (new StringBuilder(31)).append("Unknown message id: ").append(var2).toString());
         return false;
      }

      return true;
   }

   final PendingIntent zza(zzbat var1, int var2) {
      zzbdd var3;
      if ((var3 = (zzbdd)this.zzaCB.get(var1)) == null) {
         return null;
      } else {
         zzctk var4;
         return (var4 = var3.zzqy()) == null ? null : PendingIntent.getActivity(this.mContext, var2, var4.zzmH(), 134217728);
      }
   }

   final boolean zzc(ConnectionResult var1, int var2) {
      return this.zzaBd.zza(this.mContext, var1, var2);
   }

   public final void zza(ConnectionResult var1, int var2) {
      if (!this.zzc(var1, var2)) {
         this.mHandler.sendMessage(this.mHandler.obtainMessage(5, var2, 0, var1));
      }

   }

   // $FF: synthetic method
   static Handler zza(zzbdb var0) {
      return var0.mHandler;
   }

   // $FF: synthetic method
   static Context zzb(zzbdb var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static long zzc(zzbdb var0) {
      return var0.zzaDC;
   }

   // $FF: synthetic method
   static long zzd(zzbdb var0) {
      return var0.zzaDB;
   }

   // $FF: synthetic method
   static int zza(zzbdb var0, int var1) {
      return var0.zzaEg = var1;
   }

   // $FF: synthetic method
   static Status zzqo() {
      return zzaEd;
   }

   // $FF: synthetic method
   static Object zzqp() {
      return zzuF;
   }

   // $FF: synthetic method
   static zzbbw zze(zzbdb var0) {
      return var0.zzaEj;
   }

   // $FF: synthetic method
   static Set zzf(zzbdb var0) {
      return var0.zzaEk;
   }

   // $FF: synthetic method
   static GoogleApiAvailability zzg(zzbdb var0) {
      return var0.zzaBd;
   }

   // $FF: synthetic method
   static long zzh(zzbdb var0) {
      return var0.zzaEe;
   }

   // $FF: synthetic method
   static int zzi(zzbdb var0) {
      return var0.zzaEg;
   }

   // $FF: synthetic method
   static Map zzj(zzbdb var0) {
      return var0.zzaCB;
   }
}
