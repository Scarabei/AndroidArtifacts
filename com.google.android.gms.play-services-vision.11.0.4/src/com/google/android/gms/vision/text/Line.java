package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.ft;
import java.util.ArrayList;
import java.util.List;

public class Line implements Text {
   private fk zzbNO;
   private List zzbNP;

   Line(fk var1) {
      this.zzbNO = var1;
   }

   public String getLanguage() {
      return this.zzbNO.zzbNS;
   }

   public String getValue() {
      return this.zzbNO.zzbOb;
   }

   public Rect getBoundingBox() {
      return zzc.zza((Text)this);
   }

   public Point[] getCornerPoints() {
      return zzc.zza(this.zzbNO.zzbNY);
   }

   public List getComponents() {
      Line var1 = this;
      if (this.zzbNO.zzbNX.length == 0) {
         return new ArrayList(0);
      } else {
         if (this.zzbNP == null) {
            this.zzbNP = new ArrayList(this.zzbNO.zzbNX.length);
            ft[] var2;
            int var3 = (var2 = this.zzbNO.zzbNX).length;

            for(int var4 = 0; var4 < var3; ++var4) {
               ft var5 = var2[var4];
               var1.zzbNP.add(new Element(var5));
            }
         }

         return var1.zzbNP;
      }
   }

   public float getAngle() {
      return this.zzbNO.zzbNY.zzbNW;
   }

   public boolean isVertical() {
      return this.zzbNO.zzbOe;
   }
}
