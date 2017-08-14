package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.CallSuper;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;

public class Task implements ReflectedParcelable {
   public static final int NETWORK_STATE_CONNECTED = 0;
   public static final int NETWORK_STATE_UNMETERED = 1;
   public static final int NETWORK_STATE_ANY = 2;
   public static final int EXTRAS_LIMIT_BYTES = 10240;
   private final String zzbgg;
   private final String mTag;
   private final boolean zzbgh;
   private final boolean zzbgi;
   private final int zzbgj;
   private final boolean zzbgk;
   private final boolean zzbgl;
   private final zzi zzbgm;
   private final Bundle mExtras;
   protected static final long UNINITIALIZED = -1L;

   Task(Task.Builder var1) {
      this.zzbgg = var1.gcmTaskService;
      this.mTag = var1.tag;
      this.zzbgh = var1.updateCurrent;
      this.zzbgi = var1.isPersisted;
      this.zzbgj = var1.requiredNetworkState;
      this.zzbgk = var1.requiresCharging;
      this.zzbgl = false;
      this.mExtras = var1.extras;
      this.zzbgm = var1.zzbgn != null ? var1.zzbgn : zzi.zzbgb;
   }

   /** @deprecated */
   @Deprecated
   Task(Parcel var1) {
      Log.e("Task", "Constructing a Task object using a parcel.");
      this.zzbgg = var1.readString();
      this.mTag = var1.readString();
      this.zzbgh = var1.readInt() == 1;
      this.zzbgi = var1.readInt() == 1;
      this.zzbgj = 2;
      this.zzbgk = false;
      this.zzbgl = false;
      this.zzbgm = zzi.zzbgb;
      this.mExtras = null;
   }

   public void toBundle(Bundle var1) {
      var1.putString("tag", this.mTag);
      var1.putBoolean("update_current", this.zzbgh);
      var1.putBoolean("persisted", this.zzbgi);
      var1.putString("service", this.zzbgg);
      var1.putInt("requiredNetwork", this.zzbgj);
      var1.putBoolean("requiresCharging", this.zzbgk);
      var1.putBoolean("requiresIdle", false);
      var1.putBundle("retryStrategy", this.zzbgm.zzx(new Bundle()));
      var1.putBundle("extras", this.mExtras);
   }

   public String getServiceName() {
      return this.zzbgg;
   }

   public String getTag() {
      return this.mTag;
   }

   public boolean isUpdateCurrent() {
      return this.zzbgh;
   }

   public boolean isPersisted() {
      return this.zzbgi;
   }

   public int getRequiredNetwork() {
      return this.zzbgj;
   }

   public boolean getRequiresCharging() {
      return this.zzbgk;
   }

   public Bundle getExtras() {
      return this.mExtras;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.zzbgg);
      var1.writeString(this.mTag);
      var1.writeInt(this.zzbgh ? 1 : 0);
      var1.writeInt(this.zzbgi ? 1 : 0);
   }

   public int describeContents() {
      return 0;
   }

   public static void zzy(Bundle var0) {
      if (var0 != null) {
         Parcel var1 = Parcel.obtain();
         var0.writeToParcel(var1, 0);
         int var2;
         if ((var2 = var1.dataSize()) > 10240) {
            var1.recycle();
            String var7 = String.valueOf("Extras exceeding maximum size(10240 bytes): ");
            throw new IllegalArgumentException((new StringBuilder(11 + String.valueOf(var7).length())).append(var7).append(var2).toString());
         }

         var1.recycle();
         Iterator var3 = var0.keySet().iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            Object var5;
            Object var6;
            if (!((var6 = var5 = var0.get(var4)) instanceof Integer) && !(var6 instanceof Long) && !(var6 instanceof Double) && !(var6 instanceof String) && !(var6 instanceof Boolean)) {
               if (!(var5 instanceof Bundle)) {
                  throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
               }

               zzy((Bundle)var5);
            }
         }
      }

   }

   public abstract static class Builder {
      protected int requiredNetworkState;
      protected String gcmTaskService;
      protected String tag;
      protected boolean updateCurrent;
      protected boolean isPersisted;
      protected boolean requiresCharging;
      protected zzi zzbgn;
      protected Bundle extras;

      public Builder() {
         this.zzbgn = zzi.zzbgb;
      }

      public abstract Task.Builder setService(Class var1);

      public abstract Task.Builder setRequiredNetwork(int var1);

      public abstract Task.Builder setRequiresCharging(boolean var1);

      public abstract Task.Builder setTag(String var1);

      public abstract Task.Builder setUpdateCurrent(boolean var1);

      public abstract Task.Builder setPersisted(boolean var1);

      public abstract Task.Builder setExtras(Bundle var1);

      public abstract Task build();

      @CallSuper
      protected void checkConditions() {
         zzbo.zzb(this.gcmTaskService != null, "Must provide an endpoint for this task by calling setService(ComponentName).");
         GcmNetworkManager.zzdn(this.tag);
         zzi var1 = this.zzbgn;
         if (this.zzbgn != null) {
            int var2;
            if ((var2 = var1.zzvE()) != 1 && var2 != 0) {
               throw new IllegalArgumentException((new StringBuilder(45)).append("Must provide a valid RetryPolicy: ").append(var2).toString());
            }

            int var3 = var1.zzvF();
            int var4 = var1.zzvG();
            if (var2 == 0 && var3 < 0) {
               throw new IllegalArgumentException((new StringBuilder(52)).append("InitialBackoffSeconds can't be negative: ").append(var3).toString());
            }

            if (var2 == 1 && var3 < 10) {
               throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
            }

            if (var4 < var3) {
               int var5 = var1.zzvG();
               throw new IllegalArgumentException((new StringBuilder(77)).append("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: ").append(var5).toString());
            }
         }

         if (this.isPersisted) {
            Task.zzy(this.extras);
         }

      }
   }
}
