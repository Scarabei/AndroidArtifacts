package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd {
   private int zzaGH;
   private long zzaGI;
   private long zzaGJ;
   private int zzaGK;
   private long zzaGL;
   private zzak zzaGM;
   private final Context mContext;
   private final Looper zzrM;
   private final zzae zzaGN;
   private final com.google.android.gms.common.zze zzaCF;
   final Handler mHandler;
   private final Object mLock;
   private final Object zzaGO;
   private zzaw zzaGP;
   protected zzj zzaGQ;
   private IInterface zzaGR;
   private final ArrayList zzaGS;
   private zzl zzaGT;
   private int zzaGU;
   private final zzf zzaGV;
   private final zzg zzaGW;
   private final int zzaGX;
   private final String zzaGY;
   private ConnectionResult zzaGZ;
   private boolean zzaHa;
   protected AtomicInteger zzaHb;
   private static String[] zzaHc = new String[]{"service_esmobile", "service_googleme"};

   protected zzd(Context var1, Looper var2, int var3, zzf var4, zzg var5, String var6) {
      this(var1, var2, zzae.zzaC(var1), com.google.android.gms.common.zze.zzoW(), var3, (zzf)zzbo.zzu(var4), (zzg)zzbo.zzu(var5), (String)null);
   }

   protected zzd(Context var1, Looper var2, zzae var3, com.google.android.gms.common.zze var4, int var5, zzf var6, zzg var7, String var8) {
      this.mLock = new Object();
      this.zzaGO = new Object();
      this.zzaGS = new ArrayList();
      this.zzaGU = 1;
      this.zzaGZ = null;
      this.zzaHa = false;
      this.zzaHb = new AtomicInteger(0);
      this.mContext = (Context)zzbo.zzb(var1, "Context must not be null");
      this.zzrM = (Looper)zzbo.zzb(var2, "Looper must not be null");
      this.zzaGN = (zzae)zzbo.zzb(var3, "Supervisor must not be null");
      this.zzaCF = (com.google.android.gms.common.zze)zzbo.zzb(var4, "API availability must not be null");
      this.mHandler = new zzh(this, var2);
      this.zzaGX = var5;
      this.zzaGV = var6;
      this.zzaGW = var7;
      this.zzaGY = var8;
   }

   @NonNull
   protected abstract String zzdb();

   protected String zzqZ() {
      return "com.google.android.gms";
   }

   @NonNull
   protected abstract String zzdc();

   @Nullable
   private final String zzra() {
      return this.zzaGY == null ? this.mContext.getClass().getName() : this.zzaGY;
   }

   @Nullable
   protected abstract IInterface zzd(IBinder var1);

   @CallSuper
   protected void zza(@NonNull IInterface var1) {
      this.zzaGJ = System.currentTimeMillis();
   }

   @CallSuper
   protected final void onConnectionSuspended(int var1) {
      this.zzaGH = var1;
      this.zzaGI = System.currentTimeMillis();
   }

   @CallSuper
   protected void onConnectionFailed(ConnectionResult var1) {
      this.zzaGK = var1.getErrorCode();
      this.zzaGL = System.currentTimeMillis();
   }

   private final void zza(int var1, IInterface var2) {
      zzbo.zzaf(var1 == 4 == (var2 != null));
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzaGU = var1;
         this.zzaGR = var2;
         switch(var1) {
         case 1:
            if (this.zzaGT != null) {
               this.zzaGN.zza(this.zzdb(), this.zzqZ(), this.zzaGT, this.zzra());
               this.zzaGT = null;
            }
            break;
         case 2:
         case 3:
            String var7;
            if (this.zzaGT != null && this.zzaGM != null) {
               String var6 = String.valueOf(this.zzaGM.zzrE());
               var7 = String.valueOf(this.zzaGM.getPackageName());
               Log.e("GmsClient", (new StringBuilder(70 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("Calling connect() while still connected, missing disconnect() for ").append(var6).append(" on ").append(var7).toString());
               this.zzaGN.zza(this.zzaGM.zzrE(), this.zzaGM.getPackageName(), this.zzaGT, this.zzra());
               this.zzaHb.incrementAndGet();
            }

            this.zzaGT = new zzl(this, this.zzaHb.get());
            this.zzaGM = new zzak(this.zzqZ(), this.zzdb(), false);
            zzae var10000 = this.zzaGN;
            String var10001 = this.zzaGM.zzrE();
            String var10002 = this.zzaGM.getPackageName();
            zzl var10003 = this.zzaGT;
            String var13 = this.zzra();
            zzl var12 = var10003;
            String var11 = var10002;
            String var10 = var10001;
            if (!var10000.zza((zzaf)(new zzaf(var10, var11)), var12, var13)) {
               var7 = String.valueOf(this.zzaGM.zzrE());
               String var8 = String.valueOf(this.zzaGM.getPackageName());
               Log.e("GmsClient", (new StringBuilder(34 + String.valueOf(var7).length() + String.valueOf(var8).length())).append("unable to connect to service: ").append(var7).append(" on ").append(var8).toString());
               this.zza(16, (Bundle)null, this.zzaHb.get());
            }
            break;
         case 4:
            this.zza(var2);
         }

      }
   }

   private final boolean zza(int var1, int var2, IInterface var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzaGU != var1) {
            return false;
         } else {
            this.zza(var2, var3);
            return true;
         }
      }
   }

   public final void zzrb() {
      int var1;
      if ((var1 = this.zzaCF.isGooglePlayServicesAvailable(this.mContext)) != 0) {
         this.zza(1, (IInterface)null);
         this.zza((zzj)(new zzm(this)), var1, (PendingIntent)null);
      } else {
         this.zza((zzj)(new zzm(this)));
      }
   }

   public void zza(@NonNull zzj var1) {
      this.zzaGQ = (zzj)zzbo.zzb(var1, "Connection progress callbacks cannot be null.");
      this.zza(2, (IInterface)null);
   }

   public final boolean isConnected() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaGU == 4;
      }
   }

   public final boolean isConnecting() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaGU == 2 || this.zzaGU == 3;
      }
   }

   private final boolean zzrc() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaGU == 3;
      }
   }

   public void disconnect() {
      this.zzaHb.incrementAndGet();
      ArrayList var1 = this.zzaGS;
      synchronized(this.zzaGS) {
         int var2 = this.zzaGS.size();
         int var3 = 0;

         while(true) {
            if (var3 >= var2) {
               this.zzaGS.clear();
               break;
            }

            ((zzi)this.zzaGS.get(var3)).removeListener();
            ++var3;
         }
      }

      Object var8 = this.zzaGO;
      synchronized(this.zzaGO) {
         this.zzaGP = null;
      }

      this.zza(1, (IInterface)null);
   }

   public final void zzay(int var1) {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzaHb.get(), var1));
   }

   private final void zzaz(int var1) {
      byte var2;
      if (this.zzrc()) {
         var2 = 5;
         this.zzaHa = true;
      } else {
         var2 = 4;
      }

      this.mHandler.sendMessage(this.mHandler.obtainMessage(var2, this.zzaHb.get(), 16));
   }

   protected final void zza(@NonNull zzj var1, int var2, @Nullable PendingIntent var3) {
      this.zzaGQ = (zzj)zzbo.zzb(var1, "Connection progress callbacks cannot be null.");
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaHb.get(), var2, var3));
   }

   public final Context getContext() {
      return this.mContext;
   }

   public final Looper getLooper() {
      return this.zzrM;
   }

   public Account getAccount() {
      return null;
   }

   public com.google.android.gms.common.zzc[] zzrd() {
      return new com.google.android.gms.common.zzc[0];
   }

   protected Bundle zzmo() {
      return new Bundle();
   }

   protected void zza(int var1, IBinder var2, Bundle var3, int var4) {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(1, var4, -1, new zzn(this, var1, var2, var3)));
   }

   protected final void zza(int var1, @Nullable Bundle var2, int var3) {
      this.mHandler.sendMessage(this.mHandler.obtainMessage(7, var3, -1, new zzo(this, var1, (Bundle)null)));
   }

   protected final void zzre() {
      if (!this.isConnected()) {
         throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
      }
   }

   public Bundle zzoC() {
      return null;
   }

   public final IInterface zzrf() throws DeadObjectException {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzaGU == 5) {
            throw new DeadObjectException();
         } else {
            this.zzre();
            zzbo.zza(this.zzaGR != null, "Client is connected but service is null");
            return this.zzaGR;
         }
      }
   }

   @WorkerThread
   public final void zza(zzal var1, Set var2) {
      Bundle var3 = this.zzmo();
      zzx var10000 = new zzx(this.zzaGX);
      String var8 = this.mContext.getPackageName();
      zzx var7 = var10000;
      var10000.zzaHw = var8;
      (var7 = var7).zzaHz = var3;
      zzx var4 = var7;
      if (var2 != null) {
         var7.zzaHy = (Scope[])var2.toArray(new Scope[var2.size()]);
      }

      if (this.zzmv()) {
         var10000 = var7;
         Account var13 = this.getAccount() != null ? this.getAccount() : new Account("<<default account>>", "com.google");
         var7 = var7;
         var10000.zzaHA = var13;
         if (var1 != null) {
            var7.zzaHx = var1.asBinder();
         }
      } else if (this.zzrg()) {
         var7.zzaHA = this.getAccount();
      }

      var7.zzaHB = this.zzrd();

      try {
         Object var5 = this.zzaGO;
         synchronized(this.zzaGO) {
            if (this.zzaGP != null) {
               this.zzaGP.zza(new zzk(this, this.zzaHb.get()), var4);
            } else {
               Log.w("GmsClient", "mServiceBroker is null, client disconnected");
            }

         }
      } catch (DeadObjectException var10) {
         Log.w("GmsClient", "IGmsServiceBroker.getService failed", var10);
         this.zzay(1);
      } catch (SecurityException var11) {
         throw var11;
      } catch (RuntimeException | RemoteException var12) {
         Log.w("GmsClient", "IGmsServiceBroker.getService failed", var12);
         this.zza(8, (IBinder)null, (Bundle)null, this.zzaHb.get());
      }
   }

   public boolean zzmv() {
      return false;
   }

   public boolean zzrg() {
      return false;
   }

   public boolean zzpe() {
      return true;
   }

   public boolean zzmG() {
      return false;
   }

   public Intent zzmH() {
      throw new UnsupportedOperationException("Not a sign in API");
   }

   protected Set zzrh() {
      return Collections.EMPTY_SET;
   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      Object var8 = this.mLock;
      int var5;
      IInterface var6;
      synchronized(this.mLock) {
         var5 = this.zzaGU;
         var6 = this.zzaGR;
      }

      var8 = this.zzaGO;
      zzaw var7;
      synchronized(this.zzaGO) {
         var7 = this.zzaGP;
      }

      var3.append(var1).append("mConnectState=");
      switch(var5) {
      case 1:
         var3.print("DISCONNECTED");
         break;
      case 2:
         var3.print("REMOTE_CONNECTING");
         break;
      case 3:
         var3.print("LOCAL_CONNECTING");
         break;
      case 4:
         var3.print("CONNECTED");
         break;
      case 5:
         var3.print("DISCONNECTING");
         break;
      default:
         var3.print("UNKNOWN");
      }

      var3.append(" mService=");
      if (var6 == null) {
         var3.append("null");
      } else {
         var3.append(this.zzdc()).append("@").append(Integer.toHexString(System.identityHashCode(var6.asBinder())));
      }

      var3.append(" mServiceBroker=");
      if (var7 == null) {
         var3.println("null");
      } else {
         var3.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(var7.asBinder())));
      }

      SimpleDateFormat var14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
      PrintWriter var10000;
      long var9;
      String var11;
      if (this.zzaGJ > 0L) {
         var10000 = var3.append(var1).append("lastConnectedTime=");
         var9 = this.zzaGJ;
         var11 = String.valueOf(var14.format(new Date(this.zzaGJ)));
         var10000.println((new StringBuilder(21 + String.valueOf(var11).length())).append(var9).append(" ").append(var11).toString());
      }

      if (this.zzaGI > 0L) {
         var3.append(var1).append("lastSuspendedCause=");
         switch(this.zzaGH) {
         case 1:
            var3.append("CAUSE_SERVICE_DISCONNECTED");
            break;
         case 2:
            var3.append("CAUSE_NETWORK_LOST");
            break;
         default:
            var3.append(String.valueOf(this.zzaGH));
         }

         var10000 = var3.append(" lastSuspendedTime=");
         var9 = this.zzaGI;
         var11 = String.valueOf(var14.format(new Date(this.zzaGI)));
         var10000.println((new StringBuilder(21 + String.valueOf(var11).length())).append(var9).append(" ").append(var11).toString());
      }

      if (this.zzaGL > 0L) {
         var3.append(var1).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaGK));
         var10000 = var3.append(" lastFailedTime=");
         var9 = this.zzaGL;
         var11 = String.valueOf(var14.format(new Date(this.zzaGL)));
         var10000.println((new StringBuilder(21 + String.valueOf(var11).length())).append(var9).append(" ").append(var11).toString());
      }

   }

   private final boolean zzri() {
      if (this.zzaHa) {
         return false;
      } else if (TextUtils.isEmpty(this.zzdc())) {
         return false;
      } else if (TextUtils.isEmpty((CharSequence)null)) {
         return false;
      } else {
         try {
            Class.forName(this.zzdc());
            return true;
         } catch (ClassNotFoundException var1) {
            return false;
         }
      }
   }

   // $FF: synthetic method
   static void zza(zzd var0, int var1) {
      var0.zzaz(16);
   }

   // $FF: synthetic method
   static Object zza(zzd var0) {
      return var0.zzaGO;
   }

   // $FF: synthetic method
   static zzaw zza(zzd var0, zzaw var1) {
      return var0.zzaGP = var1;
   }

   // $FF: synthetic method
   static ConnectionResult zza(zzd var0, ConnectionResult var1) {
      return var0.zzaGZ = var1;
   }

   // $FF: synthetic method
   static boolean zzb(zzd var0) {
      return var0.zzri();
   }

   // $FF: synthetic method
   static boolean zzc(zzd var0) {
      return var0.zzaHa;
   }

   // $FF: synthetic method
   static void zza(zzd var0, int var1, IInterface var2) {
      var0.zza(var1, (IInterface)null);
   }

   // $FF: synthetic method
   static ConnectionResult zzd(zzd var0) {
      return var0.zzaGZ;
   }

   // $FF: synthetic method
   static zzf zze(zzd var0) {
      return var0.zzaGV;
   }

   // $FF: synthetic method
   static boolean zza(zzd var0, int var1, int var2, IInterface var3) {
      return var0.zza(var1, var2, var3);
   }

   // $FF: synthetic method
   static ArrayList zzf(zzd var0) {
      return var0.zzaGS;
   }

   // $FF: synthetic method
   static zzg zzg(zzd var0) {
      return var0.zzaGW;
   }
}
