package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbw;
import com.google.android.gms.maps.internal.zzbx;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupportStreetViewPanoramaFragment extends Fragment {
   private final SupportStreetViewPanoramaFragment.zzb zzbmX = new SupportStreetViewPanoramaFragment.zzb(this);

   public static SupportStreetViewPanoramaFragment newInstance() {
      return new SupportStreetViewPanoramaFragment();
   }

   public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions var0) {
      SupportStreetViewPanoramaFragment var1 = new SupportStreetViewPanoramaFragment();
      Bundle var2;
      (var2 = new Bundle()).putParcelable("StreetViewPanoramaOptions", var0);
      var1.setArguments(var2);
      return var1;
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);
      this.zzbmX.setActivity(var1);
   }

   public void onInflate(Activity var1, AttributeSet var2, Bundle var3) {
      super.onInflate(var1, var2, var3);
      this.zzbmX.setActivity(var1);
      Bundle var4 = new Bundle();
      this.zzbmX.onInflate(var1, var4, var3);
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.zzbmX.onCreate(var1);
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      return this.zzbmX.onCreateView(var1, var2, var3);
   }

   public void onResume() {
      super.onResume();
      this.zzbmX.onResume();
   }

   public void onPause() {
      this.zzbmX.onPause();
      super.onPause();
   }

   public void onDestroyView() {
      this.zzbmX.onDestroyView();
      super.onDestroyView();
   }

   public void onDestroy() {
      this.zzbmX.onDestroy();
      super.onDestroy();
   }

   public void onLowMemory() {
      this.zzbmX.onLowMemory();
      super.onLowMemory();
   }

   public void onActivityCreated(Bundle var1) {
      if (var1 != null) {
         var1.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
      }

      super.onActivityCreated(var1);
   }

   public void onSaveInstanceState(Bundle var1) {
      if (var1 != null) {
         var1.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
      }

      super.onSaveInstanceState(var1);
      this.zzbmX.onSaveInstanceState(var1);
   }

   public void setArguments(Bundle var1) {
      super.setArguments(var1);
   }

   public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
      zzbo.zzcz("getStreetViewPanoramaAsync() must be called on the main thread");
      this.zzbmX.getStreetViewPanoramaAsync(var1);
   }

   static class zzb extends com.google.android.gms.dynamic.zza {
      private final Fragment zzaSE;
      private com.google.android.gms.dynamic.zzo zzbms;
      private Activity mActivity;
      private final List zzbmK = new ArrayList();

      zzb(Fragment var1) {
         this.zzaSE = var1;
      }

      protected final void zza(com.google.android.gms.dynamic.zzo var1) {
         this.zzbms = var1;
         this.zzwg();
      }

      private final void zzwg() {
         if (this.mActivity != null && this.zzbms != null && this.zztx() == null) {
            try {
               MapsInitializer.initialize(this.mActivity);
               IStreetViewPanoramaFragmentDelegate var1 = zzbx.zzbh(this.mActivity).zzI(com.google.android.gms.dynamic.zzn.zzw(this.mActivity));
               this.zzbms.zza(new SupportStreetViewPanoramaFragment.zza(this.zzaSE, var1));
               Iterator var2 = this.zzbmK.iterator();

               while(var2.hasNext()) {
                  OnStreetViewPanoramaReadyCallback var3 = (OnStreetViewPanoramaReadyCallback)var2.next();
                  ((SupportStreetViewPanoramaFragment.zza)this.zztx()).getStreetViewPanoramaAsync(var3);
               }

               this.zzbmK.clear();
               return;
            } catch (RemoteException var4) {
               throw new RuntimeRemoteException(var4);
            } catch (GooglePlayServicesNotAvailableException var5) {
               ;
            }
         }

      }

      private final void setActivity(Activity var1) {
         this.mActivity = var1;
         this.zzwg();
      }

      public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
         if (this.zztx() != null) {
            ((SupportStreetViewPanoramaFragment.zza)this.zztx()).getStreetViewPanoramaAsync(var1);
         } else {
            this.zzbmK.add(var1);
         }
      }
   }

   static class zza implements StreetViewLifecycleDelegate {
      private final Fragment zzaSE;
      private final IStreetViewPanoramaFragmentDelegate zzbmI;

      public zza(Fragment var1, IStreetViewPanoramaFragmentDelegate var2) {
         this.zzbmI = (IStreetViewPanoramaFragmentDelegate)zzbo.zzu(var2);
         this.zzaSE = (Fragment)zzbo.zzu(var1);
      }

      public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
         try {
            Bundle var4 = new Bundle();
            zzbw.zzd(var3, var4);
            this.zzbmI.onInflate(com.google.android.gms.dynamic.zzn.zzw(var1), (StreetViewPanoramaOptions)null, var4);
            zzbw.zzd(var4, var3);
         } catch (RemoteException var5) {
            throw new RuntimeRemoteException(var5);
         }
      }

      public final void onCreate(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            Bundle var3;
            if ((var3 = this.zzaSE.getArguments()) != null && var3.containsKey("StreetViewPanoramaOptions")) {
               zzbw.zza(var2, "StreetViewPanoramaOptions", var3.getParcelable("StreetViewPanoramaOptions"));
            }

            this.zzbmI.onCreate(var2);
            zzbw.zzd(var2, var1);
         } catch (RemoteException var4) {
            throw new RuntimeRemoteException(var4);
         }
      }

      public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
         IObjectWrapper var4;
         try {
            Bundle var5 = new Bundle();
            zzbw.zzd(var3, var5);
            var4 = this.zzbmI.onCreateView(com.google.android.gms.dynamic.zzn.zzw(var1), com.google.android.gms.dynamic.zzn.zzw(var2), var5);
            zzbw.zzd(var5, var3);
         } catch (RemoteException var6) {
            throw new RuntimeRemoteException(var6);
         }

         return (View)com.google.android.gms.dynamic.zzn.zzE(var4);
      }

      public final void onStart() {
      }

      public final void onResume() {
         try {
            this.zzbmI.onResume();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onPause() {
         try {
            this.zzbmI.onPause();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onStop() {
      }

      public final void onDestroyView() {
         try {
            this.zzbmI.onDestroyView();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onDestroy() {
         try {
            this.zzbmI.onDestroy();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onLowMemory() {
         try {
            this.zzbmI.onLowMemory();
         } catch (RemoteException var2) {
            throw new RuntimeRemoteException(var2);
         }
      }

      public final void onSaveInstanceState(Bundle var1) {
         try {
            Bundle var2 = new Bundle();
            zzbw.zzd(var1, var2);
            this.zzbmI.onSaveInstanceState(var2);
            zzbw.zzd(var2, var1);
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }

      public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback var1) {
         try {
            this.zzbmI.getStreetViewPanoramaAsync(new zzak(this, var1));
         } catch (RemoteException var3) {
            throw new RuntimeRemoteException(var3);
         }
      }
   }
}
