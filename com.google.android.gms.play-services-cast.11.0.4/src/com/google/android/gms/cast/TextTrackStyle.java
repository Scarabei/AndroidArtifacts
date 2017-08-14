package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final float DEFAULT_FONT_SCALE = 1.0F;
   public static final int COLOR_UNSPECIFIED = 0;
   public static final int EDGE_TYPE_UNSPECIFIED = -1;
   public static final int EDGE_TYPE_NONE = 0;
   public static final int EDGE_TYPE_OUTLINE = 1;
   public static final int EDGE_TYPE_DROP_SHADOW = 2;
   public static final int EDGE_TYPE_RAISED = 3;
   public static final int EDGE_TYPE_DEPRESSED = 4;
   public static final int WINDOW_TYPE_UNSPECIFIED = -1;
   public static final int WINDOW_TYPE_NONE = 0;
   public static final int WINDOW_TYPE_NORMAL = 1;
   public static final int WINDOW_TYPE_ROUNDED = 2;
   public static final int FONT_FAMILY_UNSPECIFIED = -1;
   public static final int FONT_FAMILY_SANS_SERIF = 0;
   public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
   public static final int FONT_FAMILY_SERIF = 2;
   public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
   public static final int FONT_FAMILY_CASUAL = 4;
   public static final int FONT_FAMILY_CURSIVE = 5;
   public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
   public static final int FONT_STYLE_UNSPECIFIED = -1;
   public static final int FONT_STYLE_NORMAL = 0;
   public static final int FONT_STYLE_BOLD = 1;
   public static final int FONT_STYLE_ITALIC = 2;
   public static final int FONT_STYLE_BOLD_ITALIC = 3;
   public static final Creator CREATOR = new zzbi();
   private float zzary;
   private int zzarz;
   private int zzHs;
   private int zzarA;
   private int zzarB;
   private int zzarC;
   private int zzarD;
   private int zzarE;
   private String zzarF;
   private int zzarG;
   private int zzarH;
   private String zzaoC;
   private JSONObject zzaoD;

   TextTrackStyle(float var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, String var9, int var10, int var11, String var12) {
      this.zzary = var1;
      this.zzarz = var2;
      this.zzHs = var3;
      this.zzarA = var4;
      this.zzarB = var5;
      this.zzarC = var6;
      this.zzarD = var7;
      this.zzarE = var8;
      this.zzarF = var9;
      this.zzarG = var10;
      this.zzarH = var11;
      this.zzaoC = var12;
      if (this.zzaoC != null) {
         try {
            this.zzaoD = new JSONObject(this.zzaoC);
         } catch (JSONException var13) {
            this.zzaoD = null;
            this.zzaoC = null;
         }
      } else {
         this.zzaoD = null;
      }
   }

   public TextTrackStyle() {
      this(1.0F, 0, 0, -1, 0, -1, 0, 0, (String)null, -1, -1, (String)null);
   }

   public final void setFontScale(float var1) {
      this.zzary = var1;
   }

   public final float getFontScale() {
      return this.zzary;
   }

   public final void setForegroundColor(int var1) {
      this.zzarz = var1;
   }

   public final int getForegroundColor() {
      return this.zzarz;
   }

   public final void setBackgroundColor(int var1) {
      this.zzHs = var1;
   }

   public final int getBackgroundColor() {
      return this.zzHs;
   }

   public final void setEdgeType(int var1) {
      if (var1 >= 0 && var1 <= 4) {
         this.zzarA = var1;
      } else {
         throw new IllegalArgumentException("invalid edgeType");
      }
   }

   public final int getEdgeType() {
      return this.zzarA;
   }

   public final void setEdgeColor(int var1) {
      this.zzarB = var1;
   }

   public final int getEdgeColor() {
      return this.zzarB;
   }

   public final void setWindowType(int var1) {
      if (var1 >= 0 && var1 <= 2) {
         this.zzarC = var1;
      } else {
         throw new IllegalArgumentException("invalid windowType");
      }
   }

   public final int getWindowType() {
      return this.zzarC;
   }

   public final void setWindowColor(int var1) {
      this.zzarD = var1;
   }

   public final int getWindowColor() {
      return this.zzarD;
   }

   public final void setWindowCornerRadius(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("invalid windowCornerRadius");
      } else {
         this.zzarE = var1;
      }
   }

   public final int getWindowCornerRadius() {
      return this.zzarE;
   }

   public final void setFontFamily(String var1) {
      this.zzarF = var1;
   }

   public final String getFontFamily() {
      return this.zzarF;
   }

   public final void setFontGenericFamily(int var1) {
      if (var1 >= 0 && var1 <= 6) {
         this.zzarG = var1;
      } else {
         throw new IllegalArgumentException("invalid fontGenericFamily");
      }
   }

   public final int getFontGenericFamily() {
      return this.zzarG;
   }

   public final void setFontStyle(int var1) {
      if (var1 >= 0 && var1 <= 3) {
         this.zzarH = var1;
      } else {
         throw new IllegalArgumentException("invalid fontStyle");
      }
   }

   public final int getFontStyle() {
      return this.zzarH;
   }

   public final void setCustomData(JSONObject var1) {
      this.zzaoD = var1;
   }

   public final JSONObject getCustomData() {
      return this.zzaoD;
   }

   @TargetApi(19)
   public static TextTrackStyle fromSystemSettings(Context var0) {
      TextTrackStyle var1 = new TextTrackStyle();
      if (!com.google.android.gms.common.util.zzq.zzsc()) {
         return var1;
      } else {
         CaptioningManager var2 = (CaptioningManager)var0.getSystemService("captioning");
         var1.setFontScale(var2.getFontScale());
         CaptionStyle var3 = var2.getUserStyle();
         var1.setBackgroundColor(var3.backgroundColor);
         var1.setForegroundColor(var3.foregroundColor);
         switch(var3.edgeType) {
         case 1:
            var1.setEdgeType(1);
            break;
         case 2:
            var1.setEdgeType(2);
            break;
         default:
            var1.setEdgeType(0);
         }

         var1.setEdgeColor(var3.edgeColor);
         Typeface var4;
         if ((var4 = var3.getTypeface()) != null) {
            if (Typeface.MONOSPACE.equals(var4)) {
               var1.setFontGenericFamily(1);
            } else if (!Typeface.SANS_SERIF.equals(var4) && Typeface.SERIF.equals(var4)) {
               var1.setFontGenericFamily(2);
            } else {
               var1.setFontGenericFamily(0);
            }

            boolean var5 = var4.isBold();
            boolean var6 = var4.isItalic();
            if (var5 && var6) {
               var1.setFontStyle(3);
            } else if (var5) {
               var1.setFontStyle(1);
            } else if (var6) {
               var1.setFontStyle(2);
            } else {
               var1.setFontStyle(0);
            }
         }

         return var1;
      }
   }

   public final void zzl(JSONObject var1) throws JSONException {
      this.zzary = (float)var1.optDouble("fontScale", 1.0D);
      this.zzarz = zzcb(var1.optString("foregroundColor"));
      this.zzHs = zzcb(var1.optString("backgroundColor"));
      String var2;
      if (var1.has("edgeType")) {
         var2 = var1.getString("edgeType");
         if ("NONE".equals(var2)) {
            this.zzarA = 0;
         } else if ("OUTLINE".equals(var2)) {
            this.zzarA = 1;
         } else if ("DROP_SHADOW".equals(var2)) {
            this.zzarA = 2;
         } else if ("RAISED".equals(var2)) {
            this.zzarA = 3;
         } else if ("DEPRESSED".equals(var2)) {
            this.zzarA = 4;
         }
      }

      this.zzarB = zzcb(var1.optString("edgeColor"));
      if (var1.has("windowType")) {
         var2 = var1.getString("windowType");
         if ("NONE".equals(var2)) {
            this.zzarC = 0;
         } else if ("NORMAL".equals(var2)) {
            this.zzarC = 1;
         } else if ("ROUNDED_CORNERS".equals(var2)) {
            this.zzarC = 2;
         }
      }

      this.zzarD = zzcb(var1.optString("windowColor"));
      if (this.zzarC == 2) {
         this.zzarE = var1.optInt("windowRoundedCornerRadius", 0);
      }

      this.zzarF = var1.optString("fontFamily", (String)null);
      if (var1.has("fontGenericFamily")) {
         var2 = var1.getString("fontGenericFamily");
         if ("SANS_SERIF".equals(var2)) {
            this.zzarG = 0;
         } else if ("MONOSPACED_SANS_SERIF".equals(var2)) {
            this.zzarG = 1;
         } else if ("SERIF".equals(var2)) {
            this.zzarG = 2;
         } else if ("MONOSPACED_SERIF".equals(var2)) {
            this.zzarG = 3;
         } else if ("CASUAL".equals(var2)) {
            this.zzarG = 4;
         } else if ("CURSIVE".equals(var2)) {
            this.zzarG = 5;
         } else if ("SMALL_CAPITALS".equals(var2)) {
            this.zzarG = 6;
         }
      }

      if (var1.has("fontStyle")) {
         var2 = var1.getString("fontStyle");
         if ("NORMAL".equals(var2)) {
            this.zzarH = 0;
         } else if ("BOLD".equals(var2)) {
            this.zzarH = 1;
         } else if ("ITALIC".equals(var2)) {
            this.zzarH = 2;
         } else if ("BOLD_ITALIC".equals(var2)) {
            this.zzarH = 3;
         }
      }

      this.zzaoD = var1.optJSONObject("customData");
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("fontScale", (double)this.zzary);
         if (this.zzarz != 0) {
            var1.put("foregroundColor", zzu(this.zzarz));
         }

         if (this.zzHs != 0) {
            var1.put("backgroundColor", zzu(this.zzHs));
         }

         switch(this.zzarA) {
         case 0:
            var1.put("edgeType", "NONE");
            break;
         case 1:
            var1.put("edgeType", "OUTLINE");
            break;
         case 2:
            var1.put("edgeType", "DROP_SHADOW");
            break;
         case 3:
            var1.put("edgeType", "RAISED");
            break;
         case 4:
            var1.put("edgeType", "DEPRESSED");
         }

         if (this.zzarB != 0) {
            var1.put("edgeColor", zzu(this.zzarB));
         }

         switch(this.zzarC) {
         case 0:
            var1.put("windowType", "NONE");
            break;
         case 1:
            var1.put("windowType", "NORMAL");
            break;
         case 2:
            var1.put("windowType", "ROUNDED_CORNERS");
         }

         if (this.zzarD != 0) {
            var1.put("windowColor", zzu(this.zzarD));
         }

         if (this.zzarC == 2) {
            var1.put("windowRoundedCornerRadius", this.zzarE);
         }

         if (this.zzarF != null) {
            var1.put("fontFamily", this.zzarF);
         }

         switch(this.zzarG) {
         case 0:
            var1.put("fontGenericFamily", "SANS_SERIF");
            break;
         case 1:
            var1.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
            break;
         case 2:
            var1.put("fontGenericFamily", "SERIF");
            break;
         case 3:
            var1.put("fontGenericFamily", "MONOSPACED_SERIF");
            break;
         case 4:
            var1.put("fontGenericFamily", "CASUAL");
            break;
         case 5:
            var1.put("fontGenericFamily", "CURSIVE");
            break;
         case 6:
            var1.put("fontGenericFamily", "SMALL_CAPITALS");
         }

         switch(this.zzarH) {
         case 0:
            var1.put("fontStyle", "NORMAL");
            break;
         case 1:
            var1.put("fontStyle", "BOLD");
            break;
         case 2:
            var1.put("fontStyle", "ITALIC");
            break;
         case 3:
            var1.put("fontStyle", "BOLD_ITALIC");
         }

         if (this.zzaoD != null) {
            var1.put("customData", this.zzaoD);
         }
      } catch (JSONException var2) {
         ;
      }

      return var1;
   }

   private static String zzu(int var0) {
      return String.format("#%02X%02X%02X%02X", Color.red(var0), Color.green(var0), Color.blue(var0), Color.alpha(var0));
   }

   private static int zzcb(String var0) {
      if (var0 != null && var0.length() == 9 && var0.charAt(0) == '#') {
         try {
            int var1 = Integer.parseInt(var0.substring(1, 3), 16);
            int var2 = Integer.parseInt(var0.substring(3, 5), 16);
            int var3 = Integer.parseInt(var0.substring(5, 7), 16);
            return Color.argb(Integer.parseInt(var0.substring(7, 9), 16), var1, var2, var3);
         } catch (NumberFormatException var4) {
            ;
         }
      }

      return 0;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof TextTrackStyle)) {
         return false;
      } else {
         TextTrackStyle var2 = (TextTrackStyle)var1;
         if (this.zzaoD == null != (var2.zzaoD == null)) {
            return false;
         } else if (this.zzaoD != null && var2.zzaoD != null && !com.google.android.gms.common.util.zzo.zzc(this.zzaoD, var2.zzaoD)) {
            return false;
         } else {
            return this.zzary == var2.zzary && this.zzarz == var2.zzarz && this.zzHs == var2.zzHs && this.zzarA == var2.zzarA && this.zzarB == var2.zzarB && this.zzarC == var2.zzarC && this.zzarE == var2.zzarE && zzaye.zza(this.zzarF, var2.zzarF) && this.zzarG == var2.zzarG && this.zzarH == var2.zzarH;
         }
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzary, this.zzarz, this.zzHs, this.zzarA, this.zzarB, this.zzarC, this.zzarD, this.zzarE, this.zzarF, this.zzarG, this.zzarH, String.valueOf(this.zzaoD)});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      this.zzaoC = this.zzaoD == null ? null : this.zzaoD.toString();
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getFontScale());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getForegroundColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getBackgroundColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getEdgeType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getEdgeColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getWindowType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getWindowColor());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.getWindowCornerRadius());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getFontFamily(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getFontGenericFamily());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.getFontStyle());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
