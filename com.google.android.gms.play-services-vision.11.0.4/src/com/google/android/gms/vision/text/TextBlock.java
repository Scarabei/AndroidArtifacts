package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.fe;
import com.google.android.gms.internal.fk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
   private fk[] zzbNQ;
   private Point[] cornerPoints;
   private List zzbNR;
   private String zzbNS;
   private Rect zzbNT;

   TextBlock(SparseArray var1) {
      this.zzbNQ = new fk[var1.size()];

      for(int var2 = 0; var2 < this.zzbNQ.length; ++var2) {
         this.zzbNQ[var2] = (fk)var1.valueAt(var2);
      }

   }

   public String getLanguage() {
      if (this.zzbNS != null) {
         return this.zzbNS;
      } else {
         HashMap var1 = new HashMap();
         fk[] var2 = this.zzbNQ;
         int var3 = this.zzbNQ.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            fk var5 = var2[var4];
            int var6 = 0;
            if (var1.containsKey(var5.zzbNS)) {
               var6 = ((Integer)var1.get(var5.zzbNS)).intValue();
            }

            var1.put(var5.zzbNS, var6 + 1);
         }

         this.zzbNS = (String)((Entry)Collections.max(var1.entrySet(), new zza(this))).getKey();
         if (this.zzbNS == null || this.zzbNS.isEmpty()) {
            this.zzbNS = "und";
         }

         return this.zzbNS;
      }
   }

   public String getValue() {
      if (this.zzbNQ.length == 0) {
         return "";
      } else {
         StringBuilder var1 = new StringBuilder(this.zzbNQ[0].zzbOb);

         for(int var2 = 1; var2 < this.zzbNQ.length; ++var2) {
            var1.append("\n");
            var1.append(this.zzbNQ[var2].zzbOb);
         }

         return var1.toString();
      }
   }

   private static Point[] zza(int var0, int var1, int var2, int var3, fe var4) {
      int var5 = var4.left;
      int var6 = var4.top;
      double var7 = Math.sin(Math.toRadians((double)var4.zzbNW));
      double var9 = Math.cos(Math.toRadians((double)var4.zzbNW));
      Point[] var11;
      (var11 = new Point[4])[0] = new Point(var0, var1);
      var11[1] = new Point(var2, var1);
      var11[2] = new Point(var2, var3);
      var11[3] = new Point(var0, var3);

      for(int var12 = 0; var12 < 4; ++var12) {
         int var13 = (int)((double)var11[var12].x * var9 - (double)var11[var12].y * var7);
         int var14 = (int)((double)var11[var12].x * var7 + (double)var11[var12].y * var9);
         var11[var12].x = var13;
         var11[var12].y = var14;
         var11[var12].offset(var5, var6);
      }

      return var11;
   }

   public Point[] getCornerPoints() {
      if (this.cornerPoints == null) {
         TextBlock var1 = this;
         if (this.zzbNQ.length == 0) {
            this.cornerPoints = new Point[0];
         } else {
            int var2 = Integer.MAX_VALUE;
            int var3 = Integer.MIN_VALUE;
            int var4 = Integer.MAX_VALUE;
            int var5 = Integer.MIN_VALUE;

            for(int var6 = 0; var6 < var1.zzbNQ.length; ++var6) {
               fe var11 = var1.zzbNQ[0].zzbNY;
               fe var10 = var1.zzbNQ[var6].zzbNY;
               int var12 = -var11.left;
               int var13 = -var11.top;
               double var14 = Math.sin(Math.toRadians((double)var11.zzbNW));
               double var16 = Math.cos(Math.toRadians((double)var11.zzbNW));
               Point[] var18;
               (var18 = new Point[4])[0] = new Point(var10.left, var10.top);
               var18[0].offset(var12, var13);
               int var19 = (int)((double)var18[0].x * var16 + (double)var18[0].y * var14);
               int var20 = (int)((double)(-var18[0].x) * var14 + (double)var18[0].y * var16);
               var18[0].x = var19;
               var18[0].y = var20;
               var18[1] = new Point(var19 + var10.width, var20);
               var18[2] = new Point(var19 + var10.width, var20 + var10.height);
               var18[3] = new Point(var19, var20 + var10.height);
               Point[] var7 = var18;

               for(int var8 = 0; var8 < 4; ++var8) {
                  Point var9 = var7[var8];
                  var2 = Math.min(var2, var9.x);
                  var3 = Math.max(var3, var9.x);
                  var4 = Math.min(var4, var9.y);
                  var5 = Math.max(var5, var9.y);
               }
            }

            var1.cornerPoints = zza(var2, var4, var3, var5, var1.zzbNQ[0].zzbNY);
         }
      }

      return this.cornerPoints;
   }

   public List getComponents() {
      TextBlock var1 = this;
      if (this.zzbNQ.length == 0) {
         return new ArrayList(0);
      } else {
         if (this.zzbNR == null) {
            this.zzbNR = new ArrayList(this.zzbNQ.length);
            fk[] var2;
            int var3 = (var2 = this.zzbNQ).length;

            for(int var4 = 0; var4 < var3; ++var4) {
               fk var5 = var2[var4];
               var1.zzbNR.add(new Line(var5));
            }
         }

         return var1.zzbNR;
      }
   }

   public Rect getBoundingBox() {
      if (this.zzbNT == null) {
         this.zzbNT = zzc.zza((Text)this);
      }

      return this.zzbNT;
   }
}
