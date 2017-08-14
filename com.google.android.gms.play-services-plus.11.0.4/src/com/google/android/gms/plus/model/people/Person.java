package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface Person extends Freezable {
   String getAboutMe();

   boolean hasAboutMe();

   Person.AgeRange getAgeRange();

   boolean hasAgeRange();

   String getBirthday();

   boolean hasBirthday();

   String getBraggingRights();

   boolean hasBraggingRights();

   int getCircledByCount();

   boolean hasCircledByCount();

   Person.Cover getCover();

   boolean hasCover();

   String getCurrentLocation();

   boolean hasCurrentLocation();

   String getDisplayName();

   boolean hasDisplayName();

   int getGender();

   boolean hasGender();

   String getId();

   boolean hasId();

   Person.Image getImage();

   boolean hasImage();

   boolean isPlusUser();

   boolean hasIsPlusUser();

   String getLanguage();

   boolean hasLanguage();

   Person.Name getName();

   boolean hasName();

   String getNickname();

   boolean hasNickname();

   int getObjectType();

   boolean hasObjectType();

   List getOrganizations();

   boolean hasOrganizations();

   List getPlacesLived();

   boolean hasPlacesLived();

   int getPlusOneCount();

   boolean hasPlusOneCount();

   int getRelationshipStatus();

   boolean hasRelationshipStatus();

   String getTagline();

   boolean hasTagline();

   String getUrl();

   boolean hasUrl();

   List getUrls();

   boolean hasUrls();

   boolean isVerified();

   boolean hasVerified();

   public interface Urls extends Freezable {
      String getLabel();

      boolean hasLabel();

      int getType();

      boolean hasType();

      String getValue();

      boolean hasValue();

      public static final class Type {
         public static final int OTHER = 4;
         public static final int OTHER_PROFILE = 5;
         public static final int CONTRIBUTOR = 6;
         public static final int WEBSITE = 7;
      }
   }

   public interface PlacesLived extends Freezable {
      boolean isPrimary();

      boolean hasPrimary();

      String getValue();

      boolean hasValue();
   }

   public interface Organizations extends Freezable {
      String getDepartment();

      boolean hasDepartment();

      String getDescription();

      boolean hasDescription();

      String getEndDate();

      boolean hasEndDate();

      String getLocation();

      boolean hasLocation();

      String getName();

      boolean hasName();

      boolean isPrimary();

      boolean hasPrimary();

      String getStartDate();

      boolean hasStartDate();

      String getTitle();

      boolean hasTitle();

      int getType();

      boolean hasType();

      public static final class Type {
         public static final int WORK = 0;
         public static final int SCHOOL = 1;
      }
   }

   public interface Name extends Freezable {
      String getFamilyName();

      boolean hasFamilyName();

      String getFormatted();

      boolean hasFormatted();

      String getGivenName();

      boolean hasGivenName();

      String getHonorificPrefix();

      boolean hasHonorificPrefix();

      String getHonorificSuffix();

      boolean hasHonorificSuffix();

      String getMiddleName();

      boolean hasMiddleName();
   }

   public interface Image extends Freezable {
      String getUrl();

      boolean hasUrl();
   }

   public interface Cover extends Freezable {
      Person.Cover.CoverInfo getCoverInfo();

      boolean hasCoverInfo();

      Person.Cover.CoverPhoto getCoverPhoto();

      boolean hasCoverPhoto();

      int getLayout();

      boolean hasLayout();

      public interface CoverPhoto extends Freezable {
         int getHeight();

         boolean hasHeight();

         String getUrl();

         boolean hasUrl();

         int getWidth();

         boolean hasWidth();
      }

      public interface CoverInfo extends Freezable {
         int getLeftImageOffset();

         boolean hasLeftImageOffset();

         int getTopImageOffset();

         boolean hasTopImageOffset();
      }

      public static final class Layout {
         public static final int BANNER = 0;
      }
   }

   public interface AgeRange extends Freezable {
      int getMax();

      boolean hasMax();

      int getMin();

      boolean hasMin();
   }

   public static final class RelationshipStatus {
      public static final int SINGLE = 0;
      public static final int IN_A_RELATIONSHIP = 1;
      public static final int ENGAGED = 2;
      public static final int MARRIED = 3;
      public static final int ITS_COMPLICATED = 4;
      public static final int OPEN_RELATIONSHIP = 5;
      public static final int WIDOWED = 6;
      public static final int IN_DOMESTIC_PARTNERSHIP = 7;
      public static final int IN_CIVIL_UNION = 8;
   }

   public static final class ObjectType {
      public static final int PERSON = 0;
      public static final int PAGE = 1;
   }

   public static final class Gender {
      public static final int MALE = 0;
      public static final int FEMALE = 1;
      public static final int OTHER = 2;
   }
}
