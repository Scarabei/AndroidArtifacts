package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.ft;
import java.util.ArrayList;
import java.util.List;

public class Element implements Text {
   private ft zzbNN;

   Element(ft var1) {
      this.zzbNN = var1;
   }

   public String getLanguage() {
      return this.zzbNN.zzbNS;
   }

   public String getValue() {
      return this.zzbNN.zzbOb;
   }

   public Rect getBoundingBox() {
      return zzc.zza((Text)this);
   }

   public Point[] getCornerPoints() {
      return zzc.zza(this.zzbNN.zzbNY);
   }

   public List getComponents() {
      return new ArrayList();
   }
}
