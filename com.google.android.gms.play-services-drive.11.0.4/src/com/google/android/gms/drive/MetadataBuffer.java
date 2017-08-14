package com.google.android.gms.drive;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzblj;
import com.google.android.gms.internal.zzbrc;
import java.util.Iterator;

public final class MetadataBuffer extends AbstractDataBuffer {
   private MetadataBuffer.zza zzaMx;

   public MetadataBuffer(DataHolder var1) {
      super(var1);
      var1.zzqN().setClassLoader(MetadataBuffer.class.getClassLoader());
   }

   public final Metadata get(int var1) {
      MetadataBuffer.zza var2 = this.zzaMx;
      if (this.zzaMx == null || var2.zzaMy != var1) {
         var2 = new MetadataBuffer.zza(this.zzaCX, var1);
         this.zzaMx = var2;
      }

      return var2;
   }

   /** @deprecated */
   @Deprecated
   public final String getNextPageToken() {
      return null;
   }

   public final void release() {
      if (this.zzaCX != null) {
         com.google.android.gms.drive.metadata.internal.zzf.zzb(this.zzaCX);
      }

      super.release();
   }

   static class zza extends Metadata {
      private final DataHolder zzaCX;
      private final int zzaMy;
      private final int zzaFy;

      public zza(DataHolder var1, int var2) {
         this.zzaCX = var1;
         this.zzaMy = var2;
         this.zzaFy = var1.zzat(var2);
      }

      public final Object zza(MetadataField var1) {
         return var1.zza(this.zzaCX, this.zzaMy, this.zzaFy);
      }

      public final boolean isDataValid() {
         return !this.zzaCX.isClosed();
      }

      // $FF: synthetic method
      public final Object freeze() {
         MetadataBuffer.zza var1 = this;
         MetadataBundle var2 = MetadataBundle.zztp();
         Iterator var3 = com.google.android.gms.drive.metadata.internal.zzf.zztn().iterator();

         while(var3.hasNext()) {
            MetadataField var4;
            if ((var4 = (MetadataField)var3.next()) != zzbrc.zzaQv) {
               var4.zza(var1.zzaCX, var2, var1.zzaMy, var1.zzaFy);
            }
         }

         return new zzblj(var2);
      }
   }
}
