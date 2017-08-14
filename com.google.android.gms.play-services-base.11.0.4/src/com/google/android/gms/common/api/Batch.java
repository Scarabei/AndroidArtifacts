package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzbbe;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzbbe {
   private int zzaAC;
   private boolean zzaAD;
   private boolean zzaAE;
   private final PendingResult[] zzaAF;
   private final Object mLock;

   private Batch(List var1, GoogleApiClient var2) {
      super(var2);
      this.mLock = new Object();
      this.zzaAC = var1.size();
      this.zzaAF = new PendingResult[this.zzaAC];
      if (var1.isEmpty()) {
         this.setResult(new BatchResult(Status.zzaBm, this.zzaAF));
      } else {
         for(int var3 = 0; var3 < var1.size(); ++var3) {
            PendingResult var4 = (PendingResult)var1.get(var3);
            this.zzaAF[var3] = var4;
            var4.zza(new zzb(this));
         }

      }
   }

   public final void cancel() {
      super.cancel();
      PendingResult[] var1 = this.zzaAF;
      int var2 = this.zzaAF.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         var1[var3].cancel();
      }

   }

   public final BatchResult createFailedResult(Status var1) {
      return new BatchResult(var1, this.zzaAF);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return this.createFailedResult(var1);
   }

   // $FF: synthetic method
   Batch(List var1, GoogleApiClient var2, zzb var3) {
      this(var1, var2);
   }

   // $FF: synthetic method
   static Object zza(Batch var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static boolean zza(Batch var0, boolean var1) {
      return var0.zzaAE = true;
   }

   // $FF: synthetic method
   static boolean zzb(Batch var0, boolean var1) {
      return var0.zzaAD = true;
   }

   // $FF: synthetic method
   static int zzb(Batch var0) {
      return var0.zzaAC--;
   }

   // $FF: synthetic method
   static int zzc(Batch var0) {
      return var0.zzaAC;
   }

   // $FF: synthetic method
   static boolean zzd(Batch var0) {
      return var0.zzaAE;
   }

   // $FF: synthetic method
   static void zze(Batch var0) {
      var0.cancel();
   }

   // $FF: synthetic method
   static boolean zzf(Batch var0) {
      return var0.zzaAD;
   }

   // $FF: synthetic method
   static PendingResult[] zzg(Batch var0) {
      return var0.zzaAF;
   }

   public static final class Builder {
      private List zzaAH = new ArrayList();
      private GoogleApiClient zzapu;

      public Builder(GoogleApiClient var1) {
         this.zzapu = var1;
      }

      public final BatchResultToken add(PendingResult var1) {
         BatchResultToken var2 = new BatchResultToken(this.zzaAH.size());
         this.zzaAH.add(var1);
         return var2;
      }

      public final Batch build() {
         return new Batch(this.zzaAH, this.zzapu, (zzb)null);
      }
   }
}
