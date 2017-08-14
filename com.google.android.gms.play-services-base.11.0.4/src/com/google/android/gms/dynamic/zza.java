package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzs;
import java.util.LinkedList;

public abstract class zza {
   private LifecycleDelegate zzaSr;
   private Bundle zzaSs;
   private LinkedList zzaSt;
   private final zzo zzaSu = new zzb(this);

   public final LifecycleDelegate zztx() {
      return this.zzaSr;
   }

   private final void zzaR(int var1) {
      while(!this.zzaSt.isEmpty() && ((zzi)this.zzaSt.getLast()).getState() >= var1) {
         this.zzaSt.removeLast();
      }

   }

   private final void zza(Bundle var1, zzi var2) {
      if (this.zzaSr != null) {
         var2.zzb(this.zzaSr);
      } else {
         if (this.zzaSt == null) {
            this.zzaSt = new LinkedList();
         }

         this.zzaSt.add(var2);
         if (var1 != null) {
            if (this.zzaSs == null) {
               this.zzaSs = (Bundle)var1.clone();
            } else {
               this.zzaSs.putAll(var1);
            }
         }

         this.zza(this.zzaSu);
      }
   }

   protected abstract void zza(zzo var1);

   public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
      this.zza((Bundle)var3, (zzi)(new zzc(this, var1, var2, var3)));
   }

   public final void onCreate(Bundle var1) {
      this.zza((Bundle)var1, (zzi)(new zzd(this, var1)));
   }

   public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      FrameLayout var4 = new FrameLayout(var1.getContext());
      this.zza((Bundle)var3, (zzi)(new zze(this, var4, var1, var2, var3)));
      if (this.zzaSr == null) {
         this.zza(var4);
      }

      return var4;
   }

   protected void zza(FrameLayout var1) {
      GoogleApiAvailability var3 = GoogleApiAvailability.getInstance();
      Context var4 = var1.getContext();
      int var5 = var3.isGooglePlayServicesAvailable(var4);
      String var6 = zzs.zzi(var4, var5);
      String var7 = zzs.zzk(var4, var5);
      LinearLayout var8;
      (var8 = new LinearLayout(var1.getContext())).setOrientation(1);
      var8.setLayoutParams(new LayoutParams(-2, -2));
      var1.addView(var8);
      TextView var9;
      (var9 = new TextView(var1.getContext())).setLayoutParams(new LayoutParams(-2, -2));
      var9.setText(var6);
      var8.addView(var9);
      Intent var10;
      if ((var10 = com.google.android.gms.common.zze.zza(var4, var5, (String)null)) != null) {
         Button var11;
         (var11 = new Button(var4)).setId(16908313);
         var11.setLayoutParams(new LayoutParams(-2, -2));
         var11.setText(var7);
         var8.addView(var11);
         var11.setOnClickListener(new zzf(var4, var10));
      }

   }

   public static void zzb(FrameLayout var0) {
      GoogleApiAvailability var2 = GoogleApiAvailability.getInstance();
      Context var3 = var0.getContext();
      int var4 = var2.isGooglePlayServicesAvailable(var3);
      String var5 = zzs.zzi(var3, var4);
      String var6 = zzs.zzk(var3, var4);
      LinearLayout var7;
      (var7 = new LinearLayout(var0.getContext())).setOrientation(1);
      var7.setLayoutParams(new LayoutParams(-2, -2));
      var0.addView(var7);
      TextView var8;
      (var8 = new TextView(var0.getContext())).setLayoutParams(new LayoutParams(-2, -2));
      var8.setText(var5);
      var7.addView(var8);
      Intent var9;
      if ((var9 = com.google.android.gms.common.zze.zza(var3, var4, (String)null)) != null) {
         Button var10;
         (var10 = new Button(var3)).setId(16908313);
         var10.setLayoutParams(new LayoutParams(-2, -2));
         var10.setText(var6);
         var7.addView(var10);
         var10.setOnClickListener(new zzf(var3, var9));
      }

   }

   public final void onStart() {
      this.zza((Bundle)null, (zzi)(new zzg(this)));
   }

   public final void onResume() {
      this.zza((Bundle)null, (zzi)(new zzh(this)));
   }

   public final void onPause() {
      if (this.zzaSr != null) {
         this.zzaSr.onPause();
      } else {
         this.zzaR(5);
      }
   }

   public final void onStop() {
      if (this.zzaSr != null) {
         this.zzaSr.onStop();
      } else {
         this.zzaR(4);
      }
   }

   public final void onDestroyView() {
      if (this.zzaSr != null) {
         this.zzaSr.onDestroyView();
      } else {
         this.zzaR(2);
      }
   }

   public final void onDestroy() {
      if (this.zzaSr != null) {
         this.zzaSr.onDestroy();
      } else {
         this.zzaR(1);
      }
   }

   public final void onSaveInstanceState(Bundle var1) {
      if (this.zzaSr != null) {
         this.zzaSr.onSaveInstanceState(var1);
      } else {
         if (this.zzaSs != null) {
            var1.putAll(this.zzaSs);
         }

      }
   }

   public final void onLowMemory() {
      if (this.zzaSr != null) {
         this.zzaSr.onLowMemory();
      }

   }

   // $FF: synthetic method
   static LifecycleDelegate zza(zza var0, LifecycleDelegate var1) {
      return var0.zzaSr = var1;
   }

   // $FF: synthetic method
   static LinkedList zza(zza var0) {
      return var0.zzaSt;
   }

   // $FF: synthetic method
   static LifecycleDelegate zzb(zza var0) {
      return var0.zzaSr;
   }

   // $FF: synthetic method
   static Bundle zza(zza var0, Bundle var1) {
      return var0.zzaSs = null;
   }
}
