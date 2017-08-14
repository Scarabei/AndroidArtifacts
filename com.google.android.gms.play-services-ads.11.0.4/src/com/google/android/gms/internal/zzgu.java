package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzzn
public final class zzgu {
   private final Object mLock = new Object();
   private int zzyl;
   private List zzym = new LinkedList();

   @Nullable
   public final zzgt zzcL() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzym.size() == 0) {
            zzafr.zzaC("Queue empty");
            return null;
         } else {
            zzgt var2;
            if (this.zzym.size() >= 2) {
               var2 = null;
               int var3 = Integer.MIN_VALUE;
               int var4 = 0;
               int var5 = 0;

               for(Iterator var6 = this.zzym.iterator(); var6.hasNext(); ++var4) {
                  zzgt var7;
                  int var8;
                  if ((var8 = (var7 = (zzgt)var6.next()).getScore()) > var3) {
                     var3 = var8;
                     var2 = var7;
                     var5 = var4;
                  }
               }

               this.zzym.remove(var5);
               return var2;
            } else {
               (var2 = (zzgt)this.zzym.get(0)).zzcG();
               return var2;
            }
         }
      }
   }

   public final boolean zza(zzgt var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         return this.zzym.contains(var1);
      }
   }

   public final boolean zzb(zzgt var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         Iterator var3 = this.zzym.iterator();

         zzgt var4;
         label42:
         do {
            zzme var6;
            do {
               if (!var3.hasNext()) {
                  return false;
               }

               var4 = (zzgt)var3.next();
               var6 = zzmo.zzCZ;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue() && !com.google.android.gms.ads.internal.zzbs.zzbD().zzhn()) {
                  continue label42;
               }

               var6 = zzmo.zzDb;
            } while(!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue() || com.google.android.gms.ads.internal.zzbs.zzbD().zzho() || var1 == var4 || !var4.zzcF().equals(var1.zzcF()));

            var3.remove();
            return true;
         } while(var1 == var4 || !var4.zzcD().equals(var1.zzcD()));

         var3.remove();
         return true;
      }
   }

   public final void zzc(zzgt var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzym.size() >= 10) {
            int var3 = this.zzym.size();
            zzafr.zzaC((new StringBuilder(41)).append("Queue is full, current size = ").append(var3).toString());
            this.zzym.remove(0);
         }

         var1.zzj(this.zzyl++);
         this.zzym.add(var1);
      }
   }
}
