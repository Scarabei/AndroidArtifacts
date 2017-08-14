package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;

public final class zzbdu extends Fragment implements zzbdt {
   private static WeakHashMap zzaEH = new WeakHashMap();
   private Map zzaEI = new ArrayMap();
   private int zzLe = 0;
   private Bundle zzaEJ;

   public static zzbdu zzo(Activity var0) {
      zzbdu var1;
      WeakReference var2;
      if ((var2 = (WeakReference)zzaEH.get(var0)) != null && (var1 = (zzbdu)var2.get()) != null) {
         return var1;
      } else {
         try {
            var1 = (zzbdu)var0.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
         } catch (ClassCastException var4) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", var4);
         }

         if (var1 == null || var1.isRemoving()) {
            var1 = new zzbdu();
            var0.getFragmentManager().beginTransaction().add(var1, "LifecycleFragmentImpl").commitAllowingStateLoss();
         }

         zzaEH.put(var0, new WeakReference(var1));
         return var1;
      }
   }

   public final zzbds zza(String var1, Class var2) {
      return (zzbds)var2.cast(this.zzaEI.get(var1));
   }

   public final void zza(String var1, @NonNull zzbds var2) {
      if (!this.zzaEI.containsKey(var1)) {
         this.zzaEI.put(var1, var2);
         if (this.zzLe > 0) {
            (new Handler(Looper.getMainLooper())).post(new zzbdv(this, var2, var1));
         }

      } else {
         throw new IllegalArgumentException((new StringBuilder(59 + String.valueOf(var1).length())).append("LifecycleCallback with tag ").append(var1).append(" already added to this fragment.").toString());
      }
   }

   public final Activity zzqF() {
      return this.getActivity();
   }

   public final void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.zzLe = 1;
      this.zzaEJ = var1;
      Iterator var2 = this.zzaEI.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3;
         ((zzbds)(var3 = (Entry)var2.next()).getValue()).onCreate(var1 != null ? var1.getBundle((String)var3.getKey()) : null);
      }

   }

   public final void onStart() {
      super.onStart();
      this.zzLe = 2;
      Iterator var1 = this.zzaEI.values().iterator();

      while(var1.hasNext()) {
         ((zzbds)var1.next()).onStart();
      }

   }

   public final void onResume() {
      super.onResume();
      this.zzLe = 3;
      Iterator var1 = this.zzaEI.values().iterator();

      while(var1.hasNext()) {
         ((zzbds)var1.next()).onResume();
      }

   }

   public final void onActivityResult(int var1, int var2, Intent var3) {
      super.onActivityResult(var1, var2, var3);
      Iterator var4 = this.zzaEI.values().iterator();

      while(var4.hasNext()) {
         ((zzbds)var4.next()).onActivityResult(var1, var2, var3);
      }

   }

   public final void onSaveInstanceState(Bundle var1) {
      super.onSaveInstanceState(var1);
      if (var1 != null) {
         Iterator var2 = this.zzaEI.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            Bundle var4 = new Bundle();
            ((zzbds)var3.getValue()).onSaveInstanceState(var4);
            var1.putBundle((String)var3.getKey(), var4);
         }

      }
   }

   public final void onStop() {
      super.onStop();
      this.zzLe = 4;
      Iterator var1 = this.zzaEI.values().iterator();

      while(var1.hasNext()) {
         ((zzbds)var1.next()).onStop();
      }

   }

   public final void onDestroy() {
      super.onDestroy();
      this.zzLe = 5;
      Iterator var1 = this.zzaEI.values().iterator();

      while(var1.hasNext()) {
         ((zzbds)var1.next()).onDestroy();
      }

   }

   public final void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      super.dump(var1, var2, var3, var4);
      Iterator var5 = this.zzaEI.values().iterator();

      while(var5.hasNext()) {
         ((zzbds)var5.next()).dump(var1, var2, var3, var4);
      }

   }

   // $FF: synthetic method
   static int zza(zzbdu var0) {
      return var0.zzLe;
   }

   // $FF: synthetic method
   static Bundle zzb(zzbdu var0) {
      return var0.zzaEJ;
   }
}
