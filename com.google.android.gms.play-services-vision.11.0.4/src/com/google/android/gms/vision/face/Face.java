package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

public class Face {
   public static final float UNCOMPUTED_PROBABILITY = -1.0F;
   private int mId;
   private PointF zzbNj;
   private float zzbnr;
   private float zzbns;
   private float zzbNk;
   private float zzbNl;
   private List zzbNm;
   private float zzbNn;
   private float zzbNo;
   private float zzbNp;

   public PointF getPosition() {
      return new PointF(this.zzbNj.x - this.zzbnr / 2.0F, this.zzbNj.y - this.zzbns / 2.0F);
   }

   public float getWidth() {
      return this.zzbnr;
   }

   public float getHeight() {
      return this.zzbns;
   }

   public float getEulerY() {
      return this.zzbNk;
   }

   public float getEulerZ() {
      return this.zzbNl;
   }

   public List getLandmarks() {
      return this.zzbNm;
   }

   public float getIsLeftEyeOpenProbability() {
      return this.zzbNn;
   }

   public float getIsRightEyeOpenProbability() {
      return this.zzbNo;
   }

   public float getIsSmilingProbability() {
      return this.zzbNp;
   }

   public int getId() {
      return this.mId;
   }

   public Face(int var1, PointF var2, float var3, float var4, float var5, float var6, Landmark[] var7, float var8, float var9, float var10) {
      this.mId = var1;
      this.zzbNj = var2;
      this.zzbnr = var3;
      this.zzbns = var4;
      this.zzbNk = var5;
      this.zzbNl = var6;
      this.zzbNm = Arrays.asList(var7);
      if (var8 >= 0.0F && var8 <= 1.0F) {
         this.zzbNn = var8;
      } else {
         this.zzbNn = -1.0F;
      }

      if (var9 >= 0.0F && var9 <= 1.0F) {
         this.zzbNo = var9;
      } else {
         this.zzbNo = -1.0F;
      }

      if (var10 >= 0.0F && var10 <= 1.0F) {
         this.zzbNp = var10;
      } else {
         this.zzbNp = -1.0F;
      }
   }
}
