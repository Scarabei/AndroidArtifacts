package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzcri extends zzbgl implements Person {
   public static final Creator CREATOR = new zzcrj();
   private static final HashMap zzbAS;
   private Set zzbAT;
   private int zzaku;
   private String zzbAU;
   private zzcri.zza zzbAV;
   private String zzbAW;
   private String zzbAX;
   private int zzbAY;
   private zzcri.zzb zzbAZ;
   private String zzbBa;
   private String zzalP;
   private int zzAe;
   private String zzIi;
   private zzcri.zzc zzbBb;
   private boolean zzbBc;
   private String zzaeT;
   private zzcri.zzd zzbBd;
   private String zzbBe;
   private int zzbBf;
   private List zzbBg;
   private List zzbBh;
   private int zzbBi;
   private int zzbBj;
   private String zzbBk;
   private String zzD;
   private List zzbBl;
   private boolean zzbBm;

   public zzcri() {
      this.zzaku = 1;
      this.zzbAT = new HashSet();
   }

   zzcri(Set var1, int var2, String var3, zzcri.zza var4, String var5, String var6, int var7, zzcri.zzb var8, String var9, String var10, int var11, String var12, zzcri.zzc var13, boolean var14, String var15, zzcri.zzd var16, String var17, int var18, List var19, List var20, int var21, int var22, String var23, String var24, List var25, boolean var26) {
      this.zzbAT = var1;
      this.zzaku = var2;
      this.zzbAU = var3;
      this.zzbAV = var4;
      this.zzbAW = var5;
      this.zzbAX = var6;
      this.zzbAY = var7;
      this.zzbAZ = var8;
      this.zzbBa = var9;
      this.zzalP = var10;
      this.zzAe = var11;
      this.zzIi = var12;
      this.zzbBb = var13;
      this.zzbBc = var14;
      this.zzaeT = var15;
      this.zzbBd = var16;
      this.zzbBe = var17;
      this.zzbBf = var18;
      this.zzbBg = var19;
      this.zzbBh = var20;
      this.zzbBi = var21;
      this.zzbBj = var22;
      this.zzbBk = var23;
      this.zzD = var24;
      this.zzbBl = var25;
      this.zzbBm = var26;
   }

   public zzcri(String var1, String var2, zzcri.zzc var3, int var4, String var5) {
      this.zzaku = 1;
      this.zzbAT = new HashSet();
      this.zzalP = var1;
      this.zzbAT.add(Integer.valueOf(9));
      this.zzIi = var2;
      this.zzbAT.add(Integer.valueOf(14));
      this.zzbBb = var3;
      this.zzbAT.add(Integer.valueOf(15));
      this.zzbBf = var4;
      this.zzbAT.add(Integer.valueOf(21));
      this.zzD = var5;
      this.zzbAT.add(Integer.valueOf(27));
   }

   public final String getAboutMe() {
      return this.zzbAU;
   }

   public final boolean hasAboutMe() {
      return this.zzbAT.contains(Integer.valueOf(2));
   }

   public final Person.AgeRange getAgeRange() {
      return this.zzbAV;
   }

   public final boolean hasAgeRange() {
      return this.zzbAT.contains(Integer.valueOf(3));
   }

   public final String getBirthday() {
      return this.zzbAW;
   }

   public final boolean hasBirthday() {
      return this.zzbAT.contains(Integer.valueOf(4));
   }

   public final String getBraggingRights() {
      return this.zzbAX;
   }

   public final boolean hasBraggingRights() {
      return this.zzbAT.contains(Integer.valueOf(5));
   }

   public final int getCircledByCount() {
      return this.zzbAY;
   }

   public final boolean hasCircledByCount() {
      return this.zzbAT.contains(Integer.valueOf(6));
   }

   public final Person.Cover getCover() {
      return this.zzbAZ;
   }

   public final boolean hasCover() {
      return this.zzbAT.contains(Integer.valueOf(7));
   }

   public final String getCurrentLocation() {
      return this.zzbBa;
   }

   public final boolean hasCurrentLocation() {
      return this.zzbAT.contains(Integer.valueOf(8));
   }

   public final String getDisplayName() {
      return this.zzalP;
   }

   public final boolean hasDisplayName() {
      return this.zzbAT.contains(Integer.valueOf(9));
   }

   public final int getGender() {
      return this.zzAe;
   }

   public final boolean hasGender() {
      return this.zzbAT.contains(Integer.valueOf(12));
   }

   public final String getId() {
      return this.zzIi;
   }

   public final boolean hasId() {
      return this.zzbAT.contains(Integer.valueOf(14));
   }

   public final Person.Image getImage() {
      return this.zzbBb;
   }

   public final boolean hasImage() {
      return this.zzbAT.contains(Integer.valueOf(15));
   }

   public final boolean isPlusUser() {
      return this.zzbBc;
   }

   public final boolean hasIsPlusUser() {
      return this.zzbAT.contains(Integer.valueOf(16));
   }

   public final String getLanguage() {
      return this.zzaeT;
   }

   public final boolean hasLanguage() {
      return this.zzbAT.contains(Integer.valueOf(18));
   }

   public final Person.Name getName() {
      return this.zzbBd;
   }

   public final boolean hasName() {
      return this.zzbAT.contains(Integer.valueOf(19));
   }

   public final String getNickname() {
      return this.zzbBe;
   }

   public final boolean hasNickname() {
      return this.zzbAT.contains(Integer.valueOf(20));
   }

   public final int getObjectType() {
      return this.zzbBf;
   }

   public final boolean hasObjectType() {
      return this.zzbAT.contains(Integer.valueOf(21));
   }

   public final List getOrganizations() {
      return (ArrayList)this.zzbBg;
   }

   public final boolean hasOrganizations() {
      return this.zzbAT.contains(Integer.valueOf(22));
   }

   public final List getPlacesLived() {
      return (ArrayList)this.zzbBh;
   }

   public final boolean hasPlacesLived() {
      return this.zzbAT.contains(Integer.valueOf(23));
   }

   public final int getPlusOneCount() {
      return this.zzbBi;
   }

   public final boolean hasPlusOneCount() {
      return this.zzbAT.contains(Integer.valueOf(24));
   }

   public final int getRelationshipStatus() {
      return this.zzbBj;
   }

   public final boolean hasRelationshipStatus() {
      return this.zzbAT.contains(Integer.valueOf(25));
   }

   public final String getTagline() {
      return this.zzbBk;
   }

   public final boolean hasTagline() {
      return this.zzbAT.contains(Integer.valueOf(26));
   }

   public final String getUrl() {
      return this.zzD;
   }

   public final boolean hasUrl() {
      return this.zzbAT.contains(Integer.valueOf(27));
   }

   public final List getUrls() {
      return (ArrayList)this.zzbBl;
   }

   public final boolean hasUrls() {
      return this.zzbAT.contains(Integer.valueOf(28));
   }

   public final boolean isVerified() {
      return this.zzbBm;
   }

   public final boolean hasVerified() {
      return this.zzbAT.contains(Integer.valueOf(29));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      Set var6;
      if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      }

      if (var6.contains(Integer.valueOf(2))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbAU, true);
      }

      if (var6.contains(Integer.valueOf(3))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbAV, var2, true);
      }

      if (var6.contains(Integer.valueOf(4))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbAW, true);
      }

      if (var6.contains(Integer.valueOf(5))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbAX, true);
      }

      if (var6.contains(Integer.valueOf(6))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzbAY);
      }

      if (var6.contains(Integer.valueOf(7))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbAZ, var2, true);
      }

      if (var6.contains(Integer.valueOf(8))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbBa, true);
      }

      if (var6.contains(Integer.valueOf(9))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzalP, true);
      }

      if (var6.contains(Integer.valueOf(12))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.zzAe);
      }

      if (var6.contains(Integer.valueOf(14))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzIi, true);
      }

      if (var6.contains(Integer.valueOf(15))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzbBb, var2, true);
      }

      if (var6.contains(Integer.valueOf(16))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.zzbBc);
      }

      if (var6.contains(Integer.valueOf(18))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.zzaeT, true);
      }

      if (var6.contains(Integer.valueOf(19))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.zzbBd, var2, true);
      }

      if (var6.contains(Integer.valueOf(20))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.zzbBe, true);
      }

      if (var6.contains(Integer.valueOf(21))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 21, this.zzbBf);
      }

      if (var6.contains(Integer.valueOf(22))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 22, this.zzbBg, true);
      }

      if (var6.contains(Integer.valueOf(23))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 23, this.zzbBh, true);
      }

      if (var6.contains(Integer.valueOf(24))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 24, this.zzbBi);
      }

      if (var6.contains(Integer.valueOf(25))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 25, this.zzbBj);
      }

      if (var6.contains(Integer.valueOf(26))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 26, this.zzbBk, true);
      }

      if (var6.contains(Integer.valueOf(27))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 27, this.zzD, true);
      }

      if (var6.contains(Integer.valueOf(28))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 28, this.zzbBl, true);
      }

      if (var6.contains(Integer.valueOf(29))) {
         com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 29, this.zzbBm);
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   protected final boolean zza(zzbgj var1) {
      return this.zzbAT.contains(var1.zzrM());
   }

   protected final Object zzb(zzbgj var1) {
      switch(var1.zzrM()) {
      case 2:
         return this.zzbAU;
      case 3:
         return this.zzbAV;
      case 4:
         return this.zzbAW;
      case 5:
         return this.zzbAX;
      case 6:
         return this.zzbAY;
      case 7:
         return this.zzbAZ;
      case 8:
         return this.zzbBa;
      case 9:
         return this.zzalP;
      case 10:
      case 11:
      case 13:
      case 17:
      default:
         int var2 = var1.zzrM();
         throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
      case 12:
         return this.zzAe;
      case 14:
         return this.zzIi;
      case 15:
         return this.zzbBb;
      case 16:
         return this.zzbBc;
      case 18:
         return this.zzaeT;
      case 19:
         return this.zzbBd;
      case 20:
         return this.zzbBe;
      case 21:
         return this.zzbBf;
      case 22:
         return this.zzbBg;
      case 23:
         return this.zzbBh;
      case 24:
         return this.zzbBi;
      case 25:
         return this.zzbBj;
      case 26:
         return this.zzbBk;
      case 27:
         return this.zzD;
      case 28:
         return this.zzbBl;
      case 29:
         return this.zzbBm;
      }
   }

   public static zzcri zzp(byte[] var0) {
      Parcel var1;
      (var1 = Parcel.obtain()).unmarshall(var0, 0, var0.length);
      var1.setDataPosition(0);
      zzcri var2 = (zzcri)CREATOR.createFromParcel(var1);
      var1.recycle();
      return var2;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      int var1 = 0;
      Iterator var2 = zzbAS.values().iterator();

      while(var2.hasNext()) {
         zzbgj var3 = (zzbgj)var2.next();
         if (this.zza(var3)) {
            var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
         }
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzcri)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         zzcri var2 = (zzcri)var1;
         Iterator var3 = zzbAS.values().iterator();

         while(var3.hasNext()) {
            zzbgj var4 = (zzbgj)var3.next();
            if (this.zza(var4)) {
               if (!var2.zza(var4)) {
                  return false;
               }

               if (!this.zzb(var4).equals(var2.zzb(var4))) {
                  return false;
               }
            } else if (var2.zza(var4)) {
               return false;
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   public final Map zzrL() {
      return zzbAS;
   }

   static {
      (zzbAS = new HashMap()).put("aboutMe", zzbgj.zzl("aboutMe", 2));
      zzbAS.put("ageRange", zzbgj.zza("ageRange", 3, zzcri.zza.class));
      zzbAS.put("birthday", zzbgj.zzl("birthday", 4));
      zzbAS.put("braggingRights", zzbgj.zzl("braggingRights", 5));
      zzbAS.put("circledByCount", zzbgj.zzj("circledByCount", 6));
      zzbAS.put("cover", zzbgj.zza("cover", 7, zzcri.zzb.class));
      zzbAS.put("currentLocation", zzbgj.zzl("currentLocation", 8));
      zzbAS.put("displayName", zzbgj.zzl("displayName", 9));
      zzbAS.put("gender", zzbgj.zza("gender", 12, (new zzbge()).zzi("male", 0).zzi("female", 1).zzi("other", 2), false));
      zzbAS.put("id", zzbgj.zzl("id", 14));
      zzbAS.put("image", zzbgj.zza("image", 15, zzcri.zzc.class));
      zzbAS.put("isPlusUser", zzbgj.zzk("isPlusUser", 16));
      zzbAS.put("language", zzbgj.zzl("language", 18));
      zzbAS.put("name", zzbgj.zza("name", 19, zzcri.zzd.class));
      zzbAS.put("nickname", zzbgj.zzl("nickname", 20));
      zzbAS.put("objectType", zzbgj.zza("objectType", 21, (new zzbge()).zzi("person", 0).zzi("page", 1), false));
      zzbAS.put("organizations", zzbgj.zzb("organizations", 22, zzcri.zze.class));
      zzbAS.put("placesLived", zzbgj.zzb("placesLived", 23, zzcri.zzf.class));
      zzbAS.put("plusOneCount", zzbgj.zzj("plusOneCount", 24));
      zzbAS.put("relationshipStatus", zzbgj.zza("relationshipStatus", 25, (new zzbge()).zzi("single", 0).zzi("in_a_relationship", 1).zzi("engaged", 2).zzi("married", 3).zzi("its_complicated", 4).zzi("open_relationship", 5).zzi("widowed", 6).zzi("in_domestic_partnership", 7).zzi("in_civil_union", 8), false));
      zzbAS.put("tagline", zzbgj.zzl("tagline", 26));
      zzbAS.put("url", zzbgj.zzl("url", 27));
      zzbAS.put("urls", zzbgj.zzb("urls", 28, zzcri.zzg.class));
      zzbAS.put("verified", zzbgj.zzk("verified", 29));
   }

   public static final class zzg extends zzbgl implements Person.Urls {
      public static final Creator CREATOR = new zzcrs();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private String zzaeY;
      private final int zzbBD = 4;
      private int zzamr;
      private String mValue;

      public zzg() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zzg(Set var1, int var2, String var3, int var4, String var5, int var6) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzaeY = var3;
         this.zzamr = var4;
         this.mValue = var5;
      }

      public final String getLabel() {
         return this.zzaeY;
      }

      public final boolean hasLabel() {
         return this.zzbAT.contains(Integer.valueOf(5));
      }

      public final int getType() {
         return this.zzamr;
      }

      public final boolean hasType() {
         return this.zzbAT.contains(Integer.valueOf(6));
      }

      public final String getValue() {
         return this.mValue;
      }

      public final boolean hasValue() {
         return this.zzbAT.contains(Integer.valueOf(4));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, 4);
         }

         if (var6.contains(Integer.valueOf(4))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.mValue, true);
         }

         if (var6.contains(Integer.valueOf(5))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaeY, true);
         }

         if (var6.contains(Integer.valueOf(6))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzamr);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 4:
            return this.mValue;
         case 5:
            return this.zzaeY;
         case 6:
            return this.zzamr;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zzg)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zzg var2 = (zzcri.zzg)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("label", zzbgj.zzl("label", 5));
         zzbAS.put("type", zzbgj.zza("type", 6, (new zzbge()).zzi("home", 0).zzi("work", 1).zzi("blog", 2).zzi("profile", 3).zzi("other", 4).zzi("otherProfile", 5).zzi("contributor", 6).zzi("website", 7), false));
         zzbAS.put("value", zzbgj.zzl("value", 4));
      }
   }

   public static final class zzf extends zzbgl implements Person.PlacesLived {
      public static final Creator CREATOR = new zzcrr();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private boolean zzbBB;
      private String mValue;

      public zzf() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zzf(Set var1, int var2, boolean var3, String var4) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzbBB = var3;
         this.mValue = var4;
      }

      public final boolean isPrimary() {
         return this.zzbBB;
      }

      public final boolean hasPrimary() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final String getValue() {
         return this.mValue;
      }

      public final boolean hasValue() {
         return this.zzbAT.contains(Integer.valueOf(3));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbBB);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.mValue, true);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzbBB;
         case 3:
            return this.mValue;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zzf)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zzf var2 = (zzcri.zzf)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("primary", zzbgj.zzk("primary", 2));
         zzbAS.put("value", zzbgj.zzl("value", 3));
      }
   }

   public static final class zze extends zzbgl implements Person.Organizations {
      public static final Creator CREATOR = new zzcrq();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private String zzbBy;
      private String zzafa;
      private String zzbBz;
      private String zzbBA;
      private String mName;
      private boolean zzbBB;
      private String zzbBC;
      private String zzaoy;
      private int zzamr;

      public zze() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zze(Set var1, int var2, String var3, String var4, String var5, String var6, String var7, boolean var8, String var9, String var10, int var11) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzbBy = var3;
         this.zzafa = var4;
         this.zzbBz = var5;
         this.zzbBA = var6;
         this.mName = var7;
         this.zzbBB = var8;
         this.zzbBC = var9;
         this.zzaoy = var10;
         this.zzamr = var11;
      }

      public final String getDepartment() {
         return this.zzbBy;
      }

      public final boolean hasDepartment() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final String getDescription() {
         return this.zzafa;
      }

      public final boolean hasDescription() {
         return this.zzbAT.contains(Integer.valueOf(3));
      }

      public final String getEndDate() {
         return this.zzbBz;
      }

      public final boolean hasEndDate() {
         return this.zzbAT.contains(Integer.valueOf(4));
      }

      public final String getLocation() {
         return this.zzbBA;
      }

      public final boolean hasLocation() {
         return this.zzbAT.contains(Integer.valueOf(5));
      }

      public final String getName() {
         return this.mName;
      }

      public final boolean hasName() {
         return this.zzbAT.contains(Integer.valueOf(6));
      }

      public final boolean isPrimary() {
         return this.zzbBB;
      }

      public final boolean hasPrimary() {
         return this.zzbAT.contains(Integer.valueOf(7));
      }

      public final String getStartDate() {
         return this.zzbBC;
      }

      public final boolean hasStartDate() {
         return this.zzbAT.contains(Integer.valueOf(8));
      }

      public final String getTitle() {
         return this.zzaoy;
      }

      public final boolean hasTitle() {
         return this.zzbAT.contains(Integer.valueOf(9));
      }

      public final int getType() {
         return this.zzamr;
      }

      public final boolean hasType() {
         return this.zzbAT.contains(Integer.valueOf(10));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbBy, true);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzafa, true);
         }

         if (var6.contains(Integer.valueOf(4))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbBz, true);
         }

         if (var6.contains(Integer.valueOf(5))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbBA, true);
         }

         if (var6.contains(Integer.valueOf(6))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.mName, true);
         }

         if (var6.contains(Integer.valueOf(7))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbBB);
         }

         if (var6.contains(Integer.valueOf(8))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbBC, true);
         }

         if (var6.contains(Integer.valueOf(9))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaoy, true);
         }

         if (var6.contains(Integer.valueOf(10))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.zzamr);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzbBy;
         case 3:
            return this.zzafa;
         case 4:
            return this.zzbBz;
         case 5:
            return this.zzbBA;
         case 6:
            return this.mName;
         case 7:
            return this.zzbBB;
         case 8:
            return this.zzbBC;
         case 9:
            return this.zzaoy;
         case 10:
            return this.zzamr;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zze)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zze var2 = (zzcri.zze)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("department", zzbgj.zzl("department", 2));
         zzbAS.put("description", zzbgj.zzl("description", 3));
         zzbAS.put("endDate", zzbgj.zzl("endDate", 4));
         zzbAS.put("location", zzbgj.zzl("location", 5));
         zzbAS.put("name", zzbgj.zzl("name", 6));
         zzbAS.put("primary", zzbgj.zzk("primary", 7));
         zzbAS.put("startDate", zzbgj.zzl("startDate", 8));
         zzbAS.put("title", zzbgj.zzl("title", 9));
         zzbAS.put("type", zzbgj.zza("type", 10, (new zzbge()).zzi("work", 0).zzi("school", 1), false));
      }
   }

   public static final class zzd extends zzbgl implements Person.Name {
      public static final Creator CREATOR = new zzcrp();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private String zzakY;
      private String zzbBu;
      private String zzakX;
      private String zzbBv;
      private String zzbBw;
      private String zzbBx;

      public zzd() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zzd(Set var1, int var2, String var3, String var4, String var5, String var6, String var7, String var8) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzakY = var3;
         this.zzbBu = var4;
         this.zzakX = var5;
         this.zzbBv = var6;
         this.zzbBw = var7;
         this.zzbBx = var8;
      }

      public final String getFamilyName() {
         return this.zzakY;
      }

      public final boolean hasFamilyName() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final String getFormatted() {
         return this.zzbBu;
      }

      public final boolean hasFormatted() {
         return this.zzbAT.contains(Integer.valueOf(3));
      }

      public final String getGivenName() {
         return this.zzakX;
      }

      public final boolean hasGivenName() {
         return this.zzbAT.contains(Integer.valueOf(4));
      }

      public final String getHonorificPrefix() {
         return this.zzbBv;
      }

      public final boolean hasHonorificPrefix() {
         return this.zzbAT.contains(Integer.valueOf(5));
      }

      public final String getHonorificSuffix() {
         return this.zzbBw;
      }

      public final boolean hasHonorificSuffix() {
         return this.zzbAT.contains(Integer.valueOf(6));
      }

      public final String getMiddleName() {
         return this.zzbBx;
      }

      public final boolean hasMiddleName() {
         return this.zzbAT.contains(Integer.valueOf(7));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzakY, true);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbBu, true);
         }

         if (var6.contains(Integer.valueOf(4))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzakX, true);
         }

         if (var6.contains(Integer.valueOf(5))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbBv, true);
         }

         if (var6.contains(Integer.valueOf(6))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbBw, true);
         }

         if (var6.contains(Integer.valueOf(7))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbBx, true);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzakY;
         case 3:
            return this.zzbBu;
         case 4:
            return this.zzakX;
         case 5:
            return this.zzbBv;
         case 6:
            return this.zzbBw;
         case 7:
            return this.zzbBx;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zzd)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zzd var2 = (zzcri.zzd)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("familyName", zzbgj.zzl("familyName", 2));
         zzbAS.put("formatted", zzbgj.zzl("formatted", 3));
         zzbAS.put("givenName", zzbgj.zzl("givenName", 4));
         zzbAS.put("honorificPrefix", zzbgj.zzl("honorificPrefix", 5));
         zzbAS.put("honorificSuffix", zzbgj.zzl("honorificSuffix", 6));
         zzbAS.put("middleName", zzbgj.zzl("middleName", 7));
      }
   }

   public static final class zzc extends zzbgl implements Person.Image {
      public static final Creator CREATOR = new zzcro();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private String zzD;

      public zzc(String var1) {
         this.zzbAT = new HashSet();
         this.zzaku = 1;
         this.zzD = var1;
         this.zzbAT.add(Integer.valueOf(2));
      }

      public zzc() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zzc(Set var1, int var2, String var3) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzD = var3;
      }

      public final String getUrl() {
         return this.zzD;
      }

      public final boolean hasUrl() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzD, true);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzD;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zzc)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zzc var2 = (zzcri.zzc)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("url", zzbgj.zzl("url", 2));
      }
   }

   public static final class zzb extends zzbgl implements Person.Cover {
      public static final Creator CREATOR = new zzcrl();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private zzcri.zzb.zza zzbBp;
      private zzcri.zzb.zzb zzbBq;
      private int zzbBr;

      public zzb() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zzb(Set var1, int var2, zzcri.zzb.zza var3, zzcri.zzb.zzb var4, int var5) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzbBp = var3;
         this.zzbBq = var4;
         this.zzbBr = var5;
      }

      public final Person.Cover.CoverInfo getCoverInfo() {
         return this.zzbBp;
      }

      public final boolean hasCoverInfo() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final Person.Cover.CoverPhoto getCoverPhoto() {
         return this.zzbBq;
      }

      public final boolean hasCoverPhoto() {
         return this.zzbAT.contains(Integer.valueOf(3));
      }

      public final int getLayout() {
         return this.zzbBr;
      }

      public final boolean hasLayout() {
         return this.zzbAT.contains(Integer.valueOf(4));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbBp, var2, true);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbBq, var2, true);
         }

         if (var6.contains(Integer.valueOf(4))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbBr);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzbBp;
         case 3:
            return this.zzbBq;
         case 4:
            return this.zzbBr;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zzb)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zzb var2 = (zzcri.zzb)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("coverInfo", zzbgj.zza("coverInfo", 2, zzcri.zzb.zza.class));
         zzbAS.put("coverPhoto", zzbgj.zza("coverPhoto", 3, zzcri.zzb.zzb.class));
         zzbAS.put("layout", zzbgj.zza("layout", 4, (new zzbge()).zzi("banner", 0), false));
      }

      public static final class zzb extends zzbgl implements Person.Cover.CoverPhoto {
         public static final Creator CREATOR = new zzcrn();
         private static final HashMap zzbAS;
         private Set zzbAT;
         private int zzaku;
         private int zzrX;
         private String zzD;
         private int zzrW;

         public zzb() {
            this.zzaku = 1;
            this.zzbAT = new HashSet();
         }

         zzb(Set var1, int var2, int var3, String var4, int var5) {
            this.zzbAT = var1;
            this.zzaku = var2;
            this.zzrX = var3;
            this.zzD = var4;
            this.zzrW = var5;
         }

         public final int getHeight() {
            return this.zzrX;
         }

         public final boolean hasHeight() {
            return this.zzbAT.contains(Integer.valueOf(2));
         }

         public final String getUrl() {
            return this.zzD;
         }

         public final boolean hasUrl() {
            return this.zzbAT.contains(Integer.valueOf(3));
         }

         public final int getWidth() {
            return this.zzrW;
         }

         public final boolean hasWidth() {
            return this.zzbAT.contains(Integer.valueOf(4));
         }

         public final void writeToParcel(Parcel var1, int var2) {
            int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
            Set var6;
            if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
            }

            if (var6.contains(Integer.valueOf(2))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzrX);
            }

            if (var6.contains(Integer.valueOf(3))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzD, true);
            }

            if (var6.contains(Integer.valueOf(4))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzrW);
            }

            com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
         }

         protected final boolean zza(zzbgj var1) {
            return this.zzbAT.contains(var1.zzrM());
         }

         protected final Object zzb(zzbgj var1) {
            switch(var1.zzrM()) {
            case 2:
               return this.zzrX;
            case 3:
               return this.zzD;
            case 4:
               return this.zzrW;
            default:
               int var2 = var1.zzrM();
               throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
            }
         }

         public final boolean isDataValid() {
            return true;
         }

         public final int hashCode() {
            int var1 = 0;
            Iterator var2 = zzbAS.values().iterator();

            while(var2.hasNext()) {
               zzbgj var3 = (zzbgj)var2.next();
               if (this.zza(var3)) {
                  var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
               }
            }

            return var1;
         }

         public final boolean equals(Object var1) {
            if (!(var1 instanceof zzcri.zzb.zzb)) {
               return false;
            } else if (this == var1) {
               return true;
            } else {
               zzcri.zzb.zzb var2 = (zzcri.zzb.zzb)var1;
               Iterator var3 = zzbAS.values().iterator();

               while(var3.hasNext()) {
                  zzbgj var4 = (zzbgj)var3.next();
                  if (this.zza(var4)) {
                     if (!var2.zza(var4)) {
                        return false;
                     }

                     if (!this.zzb(var4).equals(var2.zzb(var4))) {
                        return false;
                     }
                  } else if (var2.zza(var4)) {
                     return false;
                  }
               }

               return true;
            }
         }

         // $FF: synthetic method
         public final Map zzrL() {
            return zzbAS;
         }

         static {
            (zzbAS = new HashMap()).put("height", zzbgj.zzj("height", 2));
            zzbAS.put("url", zzbgj.zzl("url", 3));
            zzbAS.put("width", zzbgj.zzj("width", 4));
         }
      }

      public static final class zza extends zzbgl implements Person.Cover.CoverInfo {
         public static final Creator CREATOR = new zzcrm();
         private static final HashMap zzbAS;
         private Set zzbAT;
         private int zzaku;
         private int zzbBs;
         private int zzbBt;

         public zza() {
            this.zzaku = 1;
            this.zzbAT = new HashSet();
         }

         zza(Set var1, int var2, int var3, int var4) {
            this.zzbAT = var1;
            this.zzaku = var2;
            this.zzbBs = var3;
            this.zzbBt = var4;
         }

         public final int getLeftImageOffset() {
            return this.zzbBs;
         }

         public final boolean hasLeftImageOffset() {
            return this.zzbAT.contains(Integer.valueOf(2));
         }

         public final int getTopImageOffset() {
            return this.zzbBt;
         }

         public final boolean hasTopImageOffset() {
            return this.zzbAT.contains(Integer.valueOf(3));
         }

         public final void writeToParcel(Parcel var1, int var2) {
            int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
            Set var6;
            if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
            }

            if (var6.contains(Integer.valueOf(2))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbBs);
            }

            if (var6.contains(Integer.valueOf(3))) {
               com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbBt);
            }

            com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
         }

         protected final boolean zza(zzbgj var1) {
            return this.zzbAT.contains(var1.zzrM());
         }

         protected final Object zzb(zzbgj var1) {
            switch(var1.zzrM()) {
            case 2:
               return this.zzbBs;
            case 3:
               return this.zzbBt;
            default:
               int var2 = var1.zzrM();
               throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
            }
         }

         public final boolean isDataValid() {
            return true;
         }

         public final int hashCode() {
            int var1 = 0;
            Iterator var2 = zzbAS.values().iterator();

            while(var2.hasNext()) {
               zzbgj var3 = (zzbgj)var2.next();
               if (this.zza(var3)) {
                  var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
               }
            }

            return var1;
         }

         public final boolean equals(Object var1) {
            if (!(var1 instanceof zzcri.zzb.zza)) {
               return false;
            } else if (this == var1) {
               return true;
            } else {
               zzcri.zzb.zza var2 = (zzcri.zzb.zza)var1;
               Iterator var3 = zzbAS.values().iterator();

               while(var3.hasNext()) {
                  zzbgj var4 = (zzbgj)var3.next();
                  if (this.zza(var4)) {
                     if (!var2.zza(var4)) {
                        return false;
                     }

                     if (!this.zzb(var4).equals(var2.zzb(var4))) {
                        return false;
                     }
                  } else if (var2.zza(var4)) {
                     return false;
                  }
               }

               return true;
            }
         }

         // $FF: synthetic method
         public final Map zzrL() {
            return zzbAS;
         }

         static {
            (zzbAS = new HashMap()).put("leftImageOffset", zzbgj.zzj("leftImageOffset", 2));
            zzbAS.put("topImageOffset", zzbgj.zzj("topImageOffset", 3));
         }
      }
   }

   public static final class zza extends zzbgl implements Person.AgeRange {
      public static final Creator CREATOR = new zzcrk();
      private static final HashMap zzbAS;
      private Set zzbAT;
      private int zzaku;
      private int zzbBn;
      private int zzbBo;

      public zza() {
         this.zzaku = 1;
         this.zzbAT = new HashSet();
      }

      zza(Set var1, int var2, int var3, int var4) {
         this.zzbAT = var1;
         this.zzaku = var2;
         this.zzbBn = var3;
         this.zzbBo = var4;
      }

      public final int getMax() {
         return this.zzbBn;
      }

      public final boolean hasMax() {
         return this.zzbAT.contains(Integer.valueOf(2));
      }

      public final int getMin() {
         return this.zzbBo;
      }

      public final boolean hasMin() {
         return this.zzbAT.contains(Integer.valueOf(3));
      }

      public final void writeToParcel(Parcel var1, int var2) {
         int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
         Set var6;
         if ((var6 = this.zzbAT).contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
         }

         if (var6.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbBn);
         }

         if (var6.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbBo);
         }

         com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
      }

      protected final boolean zza(zzbgj var1) {
         return this.zzbAT.contains(var1.zzrM());
      }

      protected final Object zzb(zzbgj var1) {
         switch(var1.zzrM()) {
         case 2:
            return this.zzbBn;
         case 3:
            return this.zzbBo;
         default:
            int var2 = var1.zzrM();
            throw new IllegalStateException((new StringBuilder(38)).append("Unknown safe parcelable id=").append(var2).toString());
         }
      }

      public final boolean isDataValid() {
         return true;
      }

      public final int hashCode() {
         int var1 = 0;
         Iterator var2 = zzbAS.values().iterator();

         while(var2.hasNext()) {
            zzbgj var3 = (zzbgj)var2.next();
            if (this.zza(var3)) {
               var1 = var1 + var3.zzrM() + this.zzb(var3).hashCode();
            }
         }

         return var1;
      }

      public final boolean equals(Object var1) {
         if (!(var1 instanceof zzcri.zza)) {
            return false;
         } else if (this == var1) {
            return true;
         } else {
            zzcri.zza var2 = (zzcri.zza)var1;
            Iterator var3 = zzbAS.values().iterator();

            while(var3.hasNext()) {
               zzbgj var4 = (zzbgj)var3.next();
               if (this.zza(var4)) {
                  if (!var2.zza(var4)) {
                     return false;
                  }

                  if (!this.zzb(var4).equals(var2.zzb(var4))) {
                     return false;
                  }
               } else if (var2.zza(var4)) {
                  return false;
               }
            }

            return true;
         }
      }

      // $FF: synthetic method
      public final Map zzrL() {
         return zzbAS;
      }

      static {
         (zzbAS = new HashMap()).put("max", zzbgj.zzj("max", 2));
         zzbAS.put("min", zzbgj.zzj("min", 3));
      }
   }
}
