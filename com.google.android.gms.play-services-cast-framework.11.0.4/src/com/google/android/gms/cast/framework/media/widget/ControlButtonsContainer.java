package com.google.android.gms.cast.framework.media.widget;

import android.widget.ImageView;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;

public interface ControlButtonsContainer {
   int getButtonSlotCount();

   int getButtonTypeAt(int var1) throws IndexOutOfBoundsException;

   ImageView getButtonImageViewAt(int var1) throws IndexOutOfBoundsException;

   UIMediaController getUIMediaController();
}
