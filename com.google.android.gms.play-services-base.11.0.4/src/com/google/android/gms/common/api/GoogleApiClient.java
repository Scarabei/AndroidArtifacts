package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbau;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzbbi;
import com.google.android.gms.internal.zzbcp;
import com.google.android.gms.internal.zzbdr;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbei;
import com.google.android.gms.internal.zzbes;
import com.google.android.gms.internal.zzctg;
import com.google.android.gms.internal.zzctl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
   private static final Set zzaAS = Collections.newSetFromMap(new WeakHashMap());
   public static final int SIGN_IN_MODE_REQUIRED = 1;
   public static final int SIGN_IN_MODE_OPTIONAL = 2;

   public static void dumpAll(String var0, FileDescriptor var1, PrintWriter var2, String[] var3) {
      Set var4 = zzaAS;
      synchronized(zzaAS) {
         int var5 = 0;
         String var6 = String.valueOf(var0).concat("  ");
         Iterator var7 = zzaAS.iterator();

         while(var7.hasNext()) {
            GoogleApiClient var8 = (GoogleApiClient)var7.next();
            var2.append(var0).append("GoogleApiClient#").println(var5++);
            var8.dump(var6, var1, var2, var3);
         }

      }
   }

   public static Set zzpk() {
      Set var0 = zzaAS;
      synchronized(zzaAS) {
         return zzaAS;
      }
   }

   public zzbay zzd(@NonNull zzbay var1) {
      throw new UnsupportedOperationException();
   }

   public zzbay zze(@NonNull zzbay var1) {
      throw new UnsupportedOperationException();
   }

   public zzbdw zzp(@NonNull Object var1) {
      throw new UnsupportedOperationException();
   }

   @NonNull
   public Api.zze zza(@NonNull Api.zzc var1) {
      throw new UnsupportedOperationException();
   }

   public boolean zza(@NonNull Api var1) {
      throw new UnsupportedOperationException();
   }

   public abstract boolean hasConnectedApi(@NonNull Api var1);

   @NonNull
   public abstract ConnectionResult getConnectionResult(@NonNull Api var1);

   public Context getContext() {
      throw new UnsupportedOperationException();
   }

   public Looper getLooper() {
      throw new UnsupportedOperationException();
   }

   public boolean zza(zzbei var1) {
      throw new UnsupportedOperationException();
   }

   public void zzpl() {
      throw new UnsupportedOperationException();
   }

   public abstract void connect();

   public void connect(int var1) {
      throw new UnsupportedOperationException();
   }

   public abstract ConnectionResult blockingConnect();

   public abstract ConnectionResult blockingConnect(long var1, @NonNull TimeUnit var3);

   public abstract void disconnect();

   public abstract void reconnect();

   public abstract PendingResult clearDefaultAccountAndReconnect();

   public abstract void stopAutoManage(@NonNull FragmentActivity var1);

   public abstract boolean isConnected();

   public abstract boolean isConnecting();

   public abstract void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1);

   public abstract boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks var1);

   public abstract void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1);

   public abstract void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1);

   public abstract boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener var1);

   public abstract void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1);

   public abstract void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4);

   public void zza(zzbes var1) {
      throw new UnsupportedOperationException();
   }

   public void zzb(zzbes var1) {
      throw new UnsupportedOperationException();
   }

   public static final class Builder {
      private Account zzajb;
      private final Set zzaAT;
      private final Set zzaAU;
      private int zzaAV;
      private View zzaAW;
      private String zzake;
      private String zzaAX;
      private final Map zzaAY;
      private final Context mContext;
      private final Map zzaAZ;
      private zzbdr zzaBa;
      private int zzaBb;
      private GoogleApiClient.OnConnectionFailedListener zzaBc;
      private Looper zzrM;
      private GoogleApiAvailability zzaBd;
      private Api.zza zzaBe;
      private final ArrayList zzaBf;
      private final ArrayList zzaBg;
      private boolean zzaBh;

      public Builder(@NonNull Context var1) {
         this.zzaAT = new HashSet();
         this.zzaAU = new HashSet();
         this.zzaAY = new ArrayMap();
         this.zzaAZ = new ArrayMap();
         this.zzaBb = -1;
         this.zzaBd = GoogleApiAvailability.getInstance();
         this.zzaBe = zzctg.zzajS;
         this.zzaBf = new ArrayList();
         this.zzaBg = new ArrayList();
         this.zzaBh = false;
         this.mContext = var1;
         this.zzrM = var1.getMainLooper();
         this.zzake = var1.getPackageName();
         this.zzaAX = var1.getClass().getName();
      }

      public Builder(@NonNull Context var1, @NonNull GoogleApiClient.ConnectionCallbacks var2, @NonNull GoogleApiClient.OnConnectionFailedListener var3) {
         this(var1);
         zzbo.zzb(var2, "Must provide a connected listener");
         this.zzaBf.add(var2);
         zzbo.zzb(var3, "Must provide a connection failed listener");
         this.zzaBg.add(var3);
      }

      public final GoogleApiClient.Builder setHandler(@NonNull Handler var1) {
         zzbo.zzb(var1, "Handler must not be null");
         this.zzrM = var1.getLooper();
         return this;
      }

      public final GoogleApiClient.Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
         zzbo.zzb(var1, "Listener must not be null");
         this.zzaBf.add(var1);
         return this;
      }

      public final GoogleApiClient.Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
         zzbo.zzb(var1, "Listener must not be null");
         this.zzaBg.add(var1);
         return this;
      }

      public final GoogleApiClient.Builder setViewForPopups(@NonNull View var1) {
         zzbo.zzb(var1, "View must not be null");
         this.zzaAW = var1;
         return this;
      }

      public final GoogleApiClient.Builder addScope(@NonNull Scope var1) {
         zzbo.zzb(var1, "Scope must not be null");
         this.zzaAT.add(var1);
         return this;
      }

      public final GoogleApiClient.Builder addApi(@NonNull Api var1) {
         zzbo.zzb(var1, "Api must not be null");
         this.zzaAZ.put(var1, (Object)null);
         List var2 = var1.zzpb().zzn((Object)null);
         this.zzaAU.addAll(var2);
         this.zzaAT.addAll(var2);
         return this;
      }

      public final GoogleApiClient.Builder addApiIfAvailable(@NonNull Api var1, Scope... var2) {
         zzbo.zzb(var1, "Api must not be null");
         this.zzaAZ.put(var1, (Object)null);
         this.zza(var1, (Api.ApiOptions)null, var2);
         return this;
      }

      public final GoogleApiClient.Builder addApi(@NonNull Api var1, @NonNull Api.ApiOptions.HasOptions var2) {
         zzbo.zzb(var1, "Api must not be null");
         zzbo.zzb(var2, "Null options are not permitted for this Api");
         this.zzaAZ.put(var1, var2);
         List var3 = var1.zzpb().zzn(var2);
         this.zzaAU.addAll(var3);
         this.zzaAT.addAll(var3);
         return this;
      }

      public final GoogleApiClient.Builder addApiIfAvailable(@NonNull Api var1, @NonNull Api.ApiOptions.HasOptions var2, Scope... var3) {
         zzbo.zzb(var1, "Api must not be null");
         zzbo.zzb(var2, "Null options are not permitted for this Api");
         this.zzaAZ.put(var1, var2);
         this.zza(var1, var2, var3);
         return this;
      }

      public final GoogleApiClient.Builder setAccountName(String var1) {
         this.zzajb = var1 == null ? null : new Account(var1, "com.google");
         return this;
      }

      public final GoogleApiClient.Builder zze(Account var1) {
         this.zzajb = var1;
         return this;
      }

      public final GoogleApiClient.Builder useDefaultAccount() {
         return this.setAccountName("<<default account>>");
      }

      public final GoogleApiClient.Builder setGravityForPopups(int var1) {
         this.zzaAV = var1;
         return this;
      }

      public final GoogleApiClient.Builder enableAutoManage(@NonNull FragmentActivity var1, int var2, @Nullable GoogleApiClient.OnConnectionFailedListener var3) {
         zzbdr var5 = new zzbdr(var1);
         zzbo.zzb(var2 >= 0, "clientId must be non-negative");
         this.zzaBb = var2;
         this.zzaBc = var3;
         this.zzaBa = var5;
         return this;
      }

      public final GoogleApiClient.Builder enableAutoManage(@NonNull FragmentActivity var1, @Nullable GoogleApiClient.OnConnectionFailedListener var2) {
         return this.enableAutoManage(var1, 0, var2);
      }

      public final zzq zzpn() {
         zzctl var1 = zzctl.zzbCM;
         if (this.zzaAZ.containsKey(zzctg.API)) {
            var1 = (zzctl)this.zzaAZ.get(zzctg.API);
         }

         return new zzq(this.zzajb, this.zzaAT, this.zzaAY, this.zzaAV, this.zzaAW, this.zzake, this.zzaAX, var1);
      }

      public final GoogleApiClient build() {
         zzbo.zzb(!this.zzaAZ.isEmpty(), "must call addApi() to add at least one API");
         GoogleApiClient.Builder var4 = this;
         zzq var5 = this.zzpn();
         Api var6 = null;
         boolean var7 = false;
         Map var8 = var5.zzrp();
         ArrayMap var9 = new ArrayMap();
         ArrayMap var10 = new ArrayMap();
         ArrayList var11 = new ArrayList();
         Iterator var12 = this.zzaAZ.keySet().iterator();

         while(var12.hasNext()) {
            Api var13 = (Api)var12.next();
            Object var14 = var4.zzaAZ.get(var13);
            boolean var15 = var8.get(var13) != null;
            var9.put(var13, var15);
            zzbbi var16 = new zzbbi(var13, var15);
            var11.add(var16);
            Api.zza var19;
            Api.zza var18 = var19 = var13.zzpc();
            Looper var23 = var4.zzrM;
            Context var22 = var4.mContext;
            Api.zze var17 = var19.zza(var22, var23, var5, var14, var16, var16);
            var10.put(var13.zzpd(), var17);
            if (var18.getPriority() == 1) {
               var7 = var14 != null;
            }

            if (var17.zzmG()) {
               if (var6 != null) {
                  String var30 = String.valueOf(var13.getName());
                  String var20 = String.valueOf(var6.getName());
                  throw new IllegalStateException((new StringBuilder(21 + String.valueOf(var30).length() + String.valueOf(var20).length())).append(var30).append(" cannot be used with ").append(var20).toString());
               }

               var6 = var13;
            }
         }

         if (var6 != null) {
            if (var7) {
               String var29 = String.valueOf(var6.getName());
               throw new IllegalStateException((new StringBuilder(82 + String.valueOf(var29).length())).append("With using ").append(var29).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
            }

            zzbo.zza(var4.zzajb == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[]{var6.getName()});
            zzbo.zza(var4.zzaAT.equals(var4.zzaAU), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[]{var6.getName()});
         }

         int var28 = zzbcp.zza(var10.values(), true);
         zzbcp var1 = new zzbcp(var4.mContext, new ReentrantLock(), var4.zzrM, var5, var4.zzaBd, var4.zzaBe, var9, var4.zzaBf, var4.zzaBg, var10, var4.zzaBb, var28, var11, false);
         synchronized(GoogleApiClient.zzaAS) {
            GoogleApiClient.zzaAS.add(var1);
         }

         if (this.zzaBb >= 0) {
            zzbau.zza(this.zzaBa).zza(this.zzaBb, var1, this.zzaBc);
         }

         return var1;
      }

      private final void zza(Api var1, Api.ApiOptions var2, Scope... var3) {
         HashSet var4 = new HashSet(var1.zzpb().zzn(var2));
         Scope[] var5 = var3;
         int var6 = var3.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            Scope var8 = var5[var7];
            var4.add(var8);
         }

         this.zzaAY.put(var1, new zzr(var4));
      }
   }

   public interface OnConnectionFailedListener {
      void onConnectionFailed(@NonNull ConnectionResult var1);
   }

   public interface ConnectionCallbacks {
      int CAUSE_SERVICE_DISCONNECTED = 1;
      int CAUSE_NETWORK_LOST = 2;

      void onConnected(@Nullable Bundle var1);

      void onConnectionSuspended(int var1);
   }
}
