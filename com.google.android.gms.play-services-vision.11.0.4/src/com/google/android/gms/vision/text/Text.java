package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.List;

public interface Text {
   String getValue();

   Rect getBoundingBox();

   Point[] getCornerPoints();

   List getComponents();
}
