package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import java.io.IOException;
import java.io.InputStream;

public final class zzbu implements DataApi.GetFdForAssetResult {
   private final Status mStatus;
   private volatile ParcelFileDescriptor zzbSD;
   private volatile InputStream zzbSo;
   private volatile boolean mClosed = false;

   public zzbu(Status var1, ParcelFileDescriptor var2) {
      this.mStatus = var1;
      this.zzbSD = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final ParcelFileDescriptor getFd() {
      if (this.mClosed) {
         throw new IllegalStateException("Cannot access the file descriptor after release().");
      } else {
         return this.zzbSD;
      }
   }

   public final InputStream getInputStream() {
      if (this.mClosed) {
         throw new IllegalStateException("Cannot access the input stream after release().");
      } else if (this.zzbSD == null) {
         return null;
      } else {
         if (this.zzbSo == null) {
            this.zzbSo = new AutoCloseInputStream(this.zzbSD);
         }

         return this.zzbSo;
      }
   }

   public final void release() {
      if (this.zzbSD != null) {
         if (this.mClosed) {
            throw new IllegalStateException("releasing an already released result.");
         } else {
            try {
               if (this.zzbSo != null) {
                  this.zzbSo.close();
               } else {
                  this.zzbSD.close();
               }

               this.mClosed = true;
               this.zzbSD = null;
               this.zzbSo = null;
            } catch (IOException var1) {
               ;
            }
         }
      }
   }
}
