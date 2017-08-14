package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzaye;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApplicationMetadata extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzd();
   private String zzaoM;
   private String mName;
   private List zzHC;
   private List zzaoN;
   private String zzaoO;
   private Uri zzaoP;

   ApplicationMetadata(String var1, String var2, List var3, List var4, String var5, Uri var6) {
      this.zzaoM = var1;
      this.mName = var2;
      this.zzHC = var3;
      this.zzaoN = var4;
      this.zzaoO = var5;
      this.zzaoP = var6;
   }

   private ApplicationMetadata() {
      this.zzHC = new ArrayList();
      this.zzaoN = new ArrayList();
   }

   public String getApplicationId() {
      return this.zzaoM;
   }

   public String getName() {
      return this.mName;
   }

   public boolean isNamespaceSupported(String var1) {
      return this.zzaoN != null && this.zzaoN.contains(var1);
   }

   public List getSupportedNamespaces() {
      return Collections.unmodifiableList(this.zzaoN);
   }

   public boolean areNamespacesSupported(List var1) {
      return this.zzaoN != null && this.zzaoN.containsAll(var1);
   }

   public String getSenderAppIdentifier() {
      return this.zzaoO;
   }

   public List getImages() {
      return this.zzHC;
   }

   public String toString() {
      return "applicationId: " + this.zzaoM + ", name: " + this.mName + ", images.count: " + (this.zzHC == null ? 0 : this.zzHC.size()) + ", namespaces.count: " + (this.zzaoN == null ? 0 : this.zzaoN.size()) + ", senderAppIdentifier: " + this.zzaoO + ", senderAppLaunchUrl: " + this.zzaoP;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getApplicationId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getImages(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 5, this.getSupportedNamespaces(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getSenderAppIdentifier(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaoP, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaoM, this.mName, this.zzHC, this.zzaoN, this.zzaoO, this.zzaoP});
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ApplicationMetadata)) {
         return false;
      } else {
         ApplicationMetadata var2 = (ApplicationMetadata)var1;
         return zzaye.zza(this.zzaoM, var2.zzaoM) && zzaye.zza(this.zzHC, var2.zzHC) && zzaye.zza(this.mName, var2.mName) && zzaye.zza(this.zzaoN, var2.zzaoN) && zzaye.zza(this.zzaoO, var2.zzaoO) && zzaye.zza(this.zzaoP, var2.zzaoP);
      }
   }
}
