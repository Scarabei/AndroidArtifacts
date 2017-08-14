package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

public abstract class fb {
   private final Context mContext;
   private final Object mLock = new Object();
   private final String mTag;
   private boolean zzbNL = false;
   private Object zzbNM;

   public fb(Context var1, String var2) {
      this.mContext = var1;
      this.mTag = var2;
   }

   public final boolean isOperational() {
      return this.zzDR() != null;
   }

   public final void zzDQ() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbNM != null) {
            try {
               this.zzDO();
            } catch (RemoteException var4) {
               Log.e(this.mTag, "Could not finalize native handle", var4);
            }

         }
      }
   }

   protected final Object zzDR() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbNM != null) {
            return this.zzbNM;
         } else {
            try {
               DynamiteModule var2 = DynamiteModule.zza(this.mContext, DynamiteModule.zzaSO, "com.google.android.gms.vision.dynamite");
               this.zzbNM = this.zza(var2, this.mContext);
            } catch (RemoteException | zzc var4) {
               Log.e(this.mTag, "Error creating remote native handle", var4);
            }

            if (!this.zzbNL && this.zzbNM == null) {
               Log.w(this.mTag, "Native handle not yet available. Reverting to no-op handle.");
               this.zzbNL = true;
            } else if (this.zzbNL && this.zzbNM != null) {
               Log.w(this.mTag, "Native handle is now available.");
            }

            return this.zzbNM;
         }
      }
   }

   protected abstract Object zza(DynamiteModule var1, Context var2) throws RemoteException, zzc;

   protected abstract void zzDO() throws RemoteException;
}
