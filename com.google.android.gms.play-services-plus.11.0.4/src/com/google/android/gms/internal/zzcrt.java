package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.plus.model.people.Person;

public final class zzcrt extends zzc implements Person {
   public zzcrt(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getDisplayName() {
      return this.getString("displayName");
   }

   public final boolean hasDisplayName() {
      return true;
   }

   public final String getId() {
      return this.getString("personId");
   }

   public final boolean hasId() {
      return true;
   }

   public final Person.Image getImage() {
      return new zzcri.zzc(this.getString("image"));
   }

   public final boolean hasImage() {
      return true;
   }

   public final int getObjectType() {
      String var1;
      if ((var1 = this.getString("objectType")).equals("person")) {
         return 0;
      } else if (var1.equals("page")) {
         return 1;
      } else {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var1);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Unknown objectType string: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unknown objectType string: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   public final boolean hasObjectType() {
      return true;
   }

   public final String getUrl() {
      return this.getString("url");
   }

   public final boolean hasUrl() {
      return true;
   }

   public final String getAboutMe() {
      return null;
   }

   public final boolean hasAboutMe() {
      return false;
   }

   public final Person.AgeRange getAgeRange() {
      return null;
   }

   public final boolean hasAgeRange() {
      return false;
   }

   public final String getBirthday() {
      return null;
   }

   public final boolean hasBirthday() {
      return false;
   }

   public final String getBraggingRights() {
      return null;
   }

   public final boolean hasBraggingRights() {
      return false;
   }

   public final int getCircledByCount() {
      return 0;
   }

   public final boolean hasCircledByCount() {
      return false;
   }

   public final Person.Cover getCover() {
      return null;
   }

   public final boolean hasCover() {
      return false;
   }

   public final String getCurrentLocation() {
      return null;
   }

   public final boolean hasCurrentLocation() {
      return false;
   }

   public final int getGender() {
      return 0;
   }

   public final boolean hasGender() {
      return false;
   }

   public final Person.Name getName() {
      return null;
   }

   public final boolean hasName() {
      return false;
   }

   public final String getNickname() {
      return null;
   }

   public final boolean hasNickname() {
      return false;
   }

   public final boolean isPlusUser() {
      return false;
   }

   public final boolean hasIsPlusUser() {
      return false;
   }

   public final String getLanguage() {
      return null;
   }

   public final boolean hasLanguage() {
      return false;
   }

   public final boolean hasOrganizations() {
      return false;
   }

   public final boolean hasPlacesLived() {
      return false;
   }

   public final int getPlusOneCount() {
      return 0;
   }

   public final boolean hasPlusOneCount() {
      return false;
   }

   public final int getRelationshipStatus() {
      return 0;
   }

   public final boolean hasRelationshipStatus() {
      return false;
   }

   public final String getTagline() {
      return null;
   }

   public final boolean hasTagline() {
      return false;
   }

   public final boolean hasUrls() {
      return false;
   }

   public final boolean isVerified() {
      return false;
   }

   public final boolean hasVerified() {
      return false;
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzcri(this.getDisplayName(), this.getId(), (zzcri.zzc)this.getImage(), this.getObjectType(), this.getUrl());
   }
}
