package android.support.v7.app;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.MediaControllerCompat.Callback;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.Builder;
import android.support.v7.graphics.Palette.Swatch;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R.attr;
import android.support.v7.mediarouter.R.dimen;
import android.support.v7.mediarouter.R.id;
import android.support.v7.mediarouter.R.integer;
import android.support.v7.mediarouter.R.interpolator;
import android.support.v7.mediarouter.R.layout;
import android.support.v7.mediarouter.R.string;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class MediaRouteControllerDialog extends AlertDialog {
   static final String TAG = "MediaRouteCtrlDialog";
   static final boolean DEBUG = Log.isLoggable("MediaRouteCtrlDialog", 3);
   static final int VOLUME_UPDATE_DELAY_MILLIS = 500;
   static final int CONNECTION_TIMEOUT_MILLIS;
   private static final int BUTTON_NEUTRAL_RES_ID = 16908315;
   static final int BUTTON_DISCONNECT_RES_ID = 16908314;
   static final int BUTTON_STOP_RES_ID = 16908313;
   final MediaRouter mRouter;
   private final MediaRouteControllerDialog.MediaRouterCallback mCallback;
   final MediaRouter.RouteInfo mRoute;
   Context mContext;
   private boolean mCreated;
   private boolean mAttachedToWindow;
   private int mDialogContentWidth;
   private View mCustomControlView;
   private Button mDisconnectButton;
   private Button mStopCastingButton;
   private ImageButton mPlayPauseButton;
   private ImageButton mCloseButton;
   private MediaRouteExpandCollapseButton mGroupExpandCollapseButton;
   private FrameLayout mExpandableAreaLayout;
   private LinearLayout mDialogAreaLayout;
   FrameLayout mDefaultControlLayout;
   private FrameLayout mCustomControlLayout;
   private ImageView mArtView;
   private TextView mTitleView;
   private TextView mSubtitleView;
   private TextView mRouteNameTextView;
   private boolean mVolumeControlEnabled;
   private LinearLayout mMediaMainControlLayout;
   private RelativeLayout mPlaybackControlLayout;
   private LinearLayout mVolumeControlLayout;
   private View mDividerView;
   OverlayListView mVolumeGroupList;
   MediaRouteControllerDialog.VolumeGroupAdapter mVolumeGroupAdapter;
   private List mGroupMemberRoutes;
   Set mGroupMemberRoutesAdded;
   private Set mGroupMemberRoutesRemoved;
   Set mGroupMemberRoutesAnimatingWithBitmap;
   SeekBar mVolumeSlider;
   MediaRouteControllerDialog.VolumeChangeListener mVolumeChangeListener;
   MediaRouter.RouteInfo mRouteInVolumeSliderTouched;
   private int mVolumeGroupListItemIconSize;
   private int mVolumeGroupListItemHeight;
   private int mVolumeGroupListMaxHeight;
   private final int mVolumeGroupListPaddingTop;
   Map mVolumeSliderMap;
   MediaControllerCompat mMediaController;
   MediaRouteControllerDialog.MediaControllerCallback mControllerCallback;
   PlaybackStateCompat mState;
   MediaDescriptionCompat mDescription;
   MediaRouteControllerDialog.FetchArtTask mFetchArtTask;
   Bitmap mArtIconBitmap;
   Uri mArtIconUri;
   boolean mArtIconIsLoaded;
   Bitmap mArtIconLoadedBitmap;
   int mArtIconBackgroundColor;
   boolean mHasPendingUpdate;
   boolean mPendingUpdateAnimationNeeded;
   boolean mIsGroupExpanded;
   boolean mIsGroupListAnimating;
   boolean mIsGroupListAnimationPending;
   int mGroupListAnimationDurationMs;
   private int mGroupListFadeInDurationMs;
   private int mGroupListFadeOutDurationMs;
   private Interpolator mInterpolator;
   private Interpolator mLinearOutSlowInInterpolator;
   private Interpolator mFastOutSlowInInterpolator;
   private Interpolator mAccelerateDecelerateInterpolator;
   final AccessibilityManager mAccessibilityManager;
   Runnable mGroupListFadeInAnimation;

   public MediaRouteControllerDialog(Context context) {
      this(context, 0);
   }

   public MediaRouteControllerDialog(Context context, int theme) {
      super(MediaRouterThemeHelper.createThemedContext(context, MediaRouterThemeHelper.getAlertDialogResolvedTheme(context, theme)), theme);
      this.mVolumeControlEnabled = true;
      this.mGroupListFadeInAnimation = new Runnable() {
         public void run() {
            MediaRouteControllerDialog.this.startGroupListFadeInAnimation();
         }
      };
      this.mContext = this.getContext();
      this.mControllerCallback = new MediaRouteControllerDialog.MediaControllerCallback();
      this.mRouter = MediaRouter.getInstance(this.mContext);
      this.mCallback = new MediaRouteControllerDialog.MediaRouterCallback();
      this.mRoute = this.mRouter.getSelectedRoute();
      this.setMediaSession(this.mRouter.getMediaSessionToken());
      this.mVolumeGroupListPaddingTop = this.mContext.getResources().getDimensionPixelSize(dimen.mr_controller_volume_group_list_padding_top);
      this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
      if (VERSION.SDK_INT >= 21) {
         this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, interpolator.mr_linear_out_slow_in);
         this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, interpolator.mr_fast_out_slow_in);
      }

      this.mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
   }

   public MediaRouter.RouteInfo getRoute() {
      return this.mRoute;
   }

   private MediaRouter.RouteGroup getGroup() {
      return this.mRoute instanceof MediaRouter.RouteGroup ? (MediaRouter.RouteGroup)this.mRoute : null;
   }

   public View onCreateMediaControlView(Bundle savedInstanceState) {
      return null;
   }

   public View getMediaControlView() {
      return this.mCustomControlView;
   }

   public void setVolumeControlEnabled(boolean enable) {
      if (this.mVolumeControlEnabled != enable) {
         this.mVolumeControlEnabled = enable;
         if (this.mCreated) {
            this.update(false);
         }
      }

   }

   public boolean isVolumeControlEnabled() {
      return this.mVolumeControlEnabled;
   }

   private void setMediaSession(Token sessionToken) {
      if (this.mMediaController != null) {
         this.mMediaController.unregisterCallback(this.mControllerCallback);
         this.mMediaController = null;
      }

      if (sessionToken != null) {
         if (this.mAttachedToWindow) {
            try {
               this.mMediaController = new MediaControllerCompat(this.mContext, sessionToken);
            } catch (RemoteException var3) {
               Log.e("MediaRouteCtrlDialog", "Error creating media controller in setMediaSession.", var3);
            }

            if (this.mMediaController != null) {
               this.mMediaController.registerCallback(this.mControllerCallback);
            }

            MediaMetadataCompat metadata = this.mMediaController == null ? null : this.mMediaController.getMetadata();
            this.mDescription = metadata == null ? null : metadata.getDescription();
            this.mState = this.mMediaController == null ? null : this.mMediaController.getPlaybackState();
            this.updateArtIconIfNeeded();
            this.update(false);
         }
      }
   }

   public Token getMediaSession() {
      return this.mMediaController == null ? null : this.mMediaController.getSessionToken();
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.getWindow().setBackgroundDrawableResource(17170445);
      this.setContentView(layout.mr_controller_material_dialog_b);
      this.findViewById(16908315).setVisibility(8);
      MediaRouteControllerDialog.ClickListener listener = new MediaRouteControllerDialog.ClickListener();
      this.mExpandableAreaLayout = (FrameLayout)this.findViewById(id.mr_expandable_area);
      this.mExpandableAreaLayout.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            MediaRouteControllerDialog.this.dismiss();
         }
      });
      this.mDialogAreaLayout = (LinearLayout)this.findViewById(id.mr_dialog_area);
      this.mDialogAreaLayout.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
         }
      });
      int color = MediaRouterThemeHelper.getButtonTextColor(this.mContext);
      this.mDisconnectButton = (Button)this.findViewById(16908314);
      this.mDisconnectButton.setText(string.mr_controller_disconnect);
      this.mDisconnectButton.setTextColor(color);
      this.mDisconnectButton.setOnClickListener(listener);
      this.mStopCastingButton = (Button)this.findViewById(16908313);
      this.mStopCastingButton.setText(string.mr_controller_stop);
      this.mStopCastingButton.setTextColor(color);
      this.mStopCastingButton.setOnClickListener(listener);
      this.mRouteNameTextView = (TextView)this.findViewById(id.mr_name);
      this.mCloseButton = (ImageButton)this.findViewById(id.mr_close);
      this.mCloseButton.setOnClickListener(listener);
      this.mCustomControlLayout = (FrameLayout)this.findViewById(id.mr_custom_control);
      this.mDefaultControlLayout = (FrameLayout)this.findViewById(id.mr_default_control);
      OnClickListener onClickListener = new OnClickListener() {
         public void onClick(View v) {
            if (MediaRouteControllerDialog.this.mMediaController != null) {
               PendingIntent pi = MediaRouteControllerDialog.this.mMediaController.getSessionActivity();
               if (pi != null) {
                  try {
                     pi.send();
                     MediaRouteControllerDialog.this.dismiss();
                  } catch (CanceledException var4) {
                     Log.e("MediaRouteCtrlDialog", pi + " was not sent, it had been canceled.");
                  }
               }
            }

         }
      };
      this.mArtView = (ImageView)this.findViewById(id.mr_art);
      this.mArtView.setOnClickListener(onClickListener);
      this.findViewById(id.mr_control_title_container).setOnClickListener(onClickListener);
      this.mMediaMainControlLayout = (LinearLayout)this.findViewById(id.mr_media_main_control);
      this.mDividerView = this.findViewById(id.mr_control_divider);
      this.mPlaybackControlLayout = (RelativeLayout)this.findViewById(id.mr_playback_control);
      this.mTitleView = (TextView)this.findViewById(id.mr_control_title);
      this.mSubtitleView = (TextView)this.findViewById(id.mr_control_subtitle);
      this.mPlayPauseButton = (ImageButton)this.findViewById(id.mr_control_play_pause);
      this.mPlayPauseButton.setOnClickListener(listener);
      this.mVolumeControlLayout = (LinearLayout)this.findViewById(id.mr_volume_control);
      this.mVolumeControlLayout.setVisibility(8);
      this.mVolumeSlider = (SeekBar)this.findViewById(id.mr_volume_slider);
      this.mVolumeSlider.setTag(this.mRoute);
      this.mVolumeChangeListener = new MediaRouteControllerDialog.VolumeChangeListener();
      this.mVolumeSlider.setOnSeekBarChangeListener(this.mVolumeChangeListener);
      this.mVolumeGroupList = (OverlayListView)this.findViewById(id.mr_volume_group_list);
      this.mGroupMemberRoutes = new ArrayList();
      this.mVolumeGroupAdapter = new MediaRouteControllerDialog.VolumeGroupAdapter(this.mVolumeGroupList.getContext(), this.mGroupMemberRoutes);
      this.mVolumeGroupList.setAdapter(this.mVolumeGroupAdapter);
      this.mGroupMemberRoutesAnimatingWithBitmap = new HashSet();
      MediaRouterThemeHelper.setMediaControlsBackgroundColor(this.mContext, this.mMediaMainControlLayout, this.mVolumeGroupList, this.getGroup() != null);
      MediaRouterThemeHelper.setVolumeSliderColor(this.mContext, (MediaRouteVolumeSlider)this.mVolumeSlider, this.mMediaMainControlLayout);
      this.mVolumeSliderMap = new HashMap();
      this.mVolumeSliderMap.put(this.mRoute, this.mVolumeSlider);
      this.mGroupExpandCollapseButton = (MediaRouteExpandCollapseButton)this.findViewById(id.mr_group_expand_collapse);
      this.mGroupExpandCollapseButton.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            MediaRouteControllerDialog.this.mIsGroupExpanded = !MediaRouteControllerDialog.this.mIsGroupExpanded;
            if (MediaRouteControllerDialog.this.mIsGroupExpanded) {
               MediaRouteControllerDialog.this.mVolumeGroupList.setVisibility(0);
            }

            MediaRouteControllerDialog.this.loadInterpolator();
            MediaRouteControllerDialog.this.updateLayoutHeight(true);
         }
      });
      this.loadInterpolator();
      this.mGroupListAnimationDurationMs = this.mContext.getResources().getInteger(integer.mr_controller_volume_group_list_animation_duration_ms);
      this.mGroupListFadeInDurationMs = this.mContext.getResources().getInteger(integer.mr_controller_volume_group_list_fade_in_duration_ms);
      this.mGroupListFadeOutDurationMs = this.mContext.getResources().getInteger(integer.mr_controller_volume_group_list_fade_out_duration_ms);
      this.mCustomControlView = this.onCreateMediaControlView(savedInstanceState);
      if (this.mCustomControlView != null) {
         this.mCustomControlLayout.addView(this.mCustomControlView);
         this.mCustomControlLayout.setVisibility(0);
      }

      this.mCreated = true;
      this.updateLayout();
   }

   void updateLayout() {
      int width = MediaRouteDialogHelper.getDialogWidth(this.mContext);
      this.getWindow().setLayout(width, -2);
      View decorView = this.getWindow().getDecorView();
      this.mDialogContentWidth = width - decorView.getPaddingLeft() - decorView.getPaddingRight();
      Resources res = this.mContext.getResources();
      this.mVolumeGroupListItemIconSize = res.getDimensionPixelSize(dimen.mr_controller_volume_group_list_item_icon_size);
      this.mVolumeGroupListItemHeight = res.getDimensionPixelSize(dimen.mr_controller_volume_group_list_item_height);
      this.mVolumeGroupListMaxHeight = res.getDimensionPixelSize(dimen.mr_controller_volume_group_list_max_height);
      this.mArtIconBitmap = null;
      this.mArtIconUri = null;
      this.updateArtIconIfNeeded();
      this.update(false);
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mAttachedToWindow = true;
      this.mRouter.addCallback(MediaRouteSelector.EMPTY, this.mCallback, 2);
      this.setMediaSession(this.mRouter.getMediaSessionToken());
   }

   public void onDetachedFromWindow() {
      this.mRouter.removeCallback(this.mCallback);
      this.setMediaSession((Token)null);
      this.mAttachedToWindow = false;
      super.onDetachedFromWindow();
   }

   public boolean onKeyDown(int keyCode, KeyEvent event) {
      if (keyCode != 25 && keyCode != 24) {
         return super.onKeyDown(keyCode, event);
      } else {
         this.mRoute.requestUpdateVolume(keyCode == 25 ? -1 : 1);
         return true;
      }
   }

   public boolean onKeyUp(int keyCode, KeyEvent event) {
      return keyCode != 25 && keyCode != 24 ? super.onKeyUp(keyCode, event) : true;
   }

   void update(boolean animate) {
      if (this.mRouteInVolumeSliderTouched != null) {
         this.mHasPendingUpdate = true;
         this.mPendingUpdateAnimationNeeded |= animate;
      } else {
         this.mHasPendingUpdate = false;
         this.mPendingUpdateAnimationNeeded = false;
         if (this.mRoute.isSelected() && !this.mRoute.isDefaultOrBluetooth()) {
            if (this.mCreated) {
               this.mRouteNameTextView.setText(this.mRoute.getName());
               this.mDisconnectButton.setVisibility(this.mRoute.canDisconnect() ? 0 : 8);
               if (this.mCustomControlView == null && this.mArtIconIsLoaded) {
                  if (this.isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                     Log.w("MediaRouteCtrlDialog", "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
                  } else {
                     this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
                     this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
                  }

                  this.clearLoadedBitmap();
               }

               this.updateVolumeControlLayout();
               this.updatePlaybackControlLayout();
               this.updateLayoutHeight(animate);
            }
         } else {
            this.dismiss();
         }
      }
   }

   private boolean isBitmapRecycled(Bitmap bitmap) {
      return bitmap != null && bitmap.isRecycled();
   }

   private boolean canShowPlaybackControlLayout() {
      return this.mCustomControlView == null && (this.mDescription != null || this.mState != null);
   }

   private int getMainControllerHeight(boolean showPlaybackControl) {
      int height = 0;
      if (showPlaybackControl || this.mVolumeControlLayout.getVisibility() == 0) {
         height += this.mMediaMainControlLayout.getPaddingTop() + this.mMediaMainControlLayout.getPaddingBottom();
         if (showPlaybackControl) {
            height += this.mPlaybackControlLayout.getMeasuredHeight();
         }

         if (this.mVolumeControlLayout.getVisibility() == 0) {
            height += this.mVolumeControlLayout.getMeasuredHeight();
         }

         if (showPlaybackControl && this.mVolumeControlLayout.getVisibility() == 0) {
            height += this.mDividerView.getMeasuredHeight();
         }
      }

      return height;
   }

   private void updateMediaControlVisibility(boolean canShowPlaybackControlLayout) {
      this.mDividerView.setVisibility(this.mVolumeControlLayout.getVisibility() == 0 && canShowPlaybackControlLayout ? 0 : 8);
      this.mMediaMainControlLayout.setVisibility(this.mVolumeControlLayout.getVisibility() == 8 && !canShowPlaybackControlLayout ? 8 : 0);
   }

   void updateLayoutHeight(final boolean animate) {
      this.mDefaultControlLayout.requestLayout();
      ViewTreeObserver observer = this.mDefaultControlLayout.getViewTreeObserver();
      observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
         public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mDefaultControlLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            if (MediaRouteControllerDialog.this.mIsGroupListAnimating) {
               MediaRouteControllerDialog.this.mIsGroupListAnimationPending = true;
            } else {
               MediaRouteControllerDialog.this.updateLayoutHeightInternal(animate);
            }

         }
      });
   }

   void updateLayoutHeightInternal(boolean animate) {
      int oldHeight = getLayoutHeight(this.mMediaMainControlLayout);
      setLayoutHeight(this.mMediaMainControlLayout, -1);
      this.updateMediaControlVisibility(this.canShowPlaybackControlLayout());
      View decorView = this.getWindow().getDecorView();
      decorView.measure(MeasureSpec.makeMeasureSpec(this.getWindow().getAttributes().width, 1073741824), 0);
      setLayoutHeight(this.mMediaMainControlLayout, oldHeight);
      int artViewHeight = 0;
      if (this.mCustomControlView == null && this.mArtView.getDrawable() instanceof BitmapDrawable) {
         Bitmap art = ((BitmapDrawable)this.mArtView.getDrawable()).getBitmap();
         if (art != null) {
            artViewHeight = this.getDesiredArtHeight(art.getWidth(), art.getHeight());
            this.mArtView.setScaleType(art.getWidth() >= art.getHeight() ? ScaleType.FIT_XY : ScaleType.FIT_CENTER);
         }
      }

      int mainControllerHeight = this.getMainControllerHeight(this.canShowPlaybackControlLayout());
      int volumeGroupListCount = this.mGroupMemberRoutes.size();
      int expandedGroupListHeight = this.getGroup() == null ? 0 : this.mVolumeGroupListItemHeight * this.getGroup().getRoutes().size();
      if (volumeGroupListCount > 0) {
         expandedGroupListHeight += this.mVolumeGroupListPaddingTop;
      }

      expandedGroupListHeight = Math.min(expandedGroupListHeight, this.mVolumeGroupListMaxHeight);
      int visibleGroupListHeight = this.mIsGroupExpanded ? expandedGroupListHeight : 0;
      int desiredControlLayoutHeight = Math.max(artViewHeight, visibleGroupListHeight) + mainControllerHeight;
      Rect visibleRect = new Rect();
      decorView.getWindowVisibleDisplayFrame(visibleRect);
      int nonControlViewHeight = this.mDialogAreaLayout.getMeasuredHeight() - this.mDefaultControlLayout.getMeasuredHeight();
      int maximumControlViewHeight = visibleRect.height() - nonControlViewHeight;
      if (this.mCustomControlView == null && artViewHeight > 0 && desiredControlLayoutHeight <= maximumControlViewHeight) {
         this.mArtView.setVisibility(0);
         setLayoutHeight(this.mArtView, artViewHeight);
      } else {
         if (getLayoutHeight(this.mVolumeGroupList) + this.mMediaMainControlLayout.getMeasuredHeight() >= this.mDefaultControlLayout.getMeasuredHeight()) {
            this.mArtView.setVisibility(8);
         }

         artViewHeight = 0;
         desiredControlLayoutHeight = visibleGroupListHeight + mainControllerHeight;
      }

      if (this.canShowPlaybackControlLayout() && desiredControlLayoutHeight <= maximumControlViewHeight) {
         this.mPlaybackControlLayout.setVisibility(0);
      } else {
         this.mPlaybackControlLayout.setVisibility(8);
      }

      this.updateMediaControlVisibility(this.mPlaybackControlLayout.getVisibility() == 0);
      mainControllerHeight = this.getMainControllerHeight(this.mPlaybackControlLayout.getVisibility() == 0);
      desiredControlLayoutHeight = Math.max(artViewHeight, visibleGroupListHeight) + mainControllerHeight;
      if (desiredControlLayoutHeight > maximumControlViewHeight) {
         visibleGroupListHeight -= desiredControlLayoutHeight - maximumControlViewHeight;
         desiredControlLayoutHeight = maximumControlViewHeight;
      }

      this.mMediaMainControlLayout.clearAnimation();
      this.mVolumeGroupList.clearAnimation();
      this.mDefaultControlLayout.clearAnimation();
      if (animate) {
         this.animateLayoutHeight(this.mMediaMainControlLayout, mainControllerHeight);
         this.animateLayoutHeight(this.mVolumeGroupList, visibleGroupListHeight);
         this.animateLayoutHeight(this.mDefaultControlLayout, desiredControlLayoutHeight);
      } else {
         setLayoutHeight(this.mMediaMainControlLayout, mainControllerHeight);
         setLayoutHeight(this.mVolumeGroupList, visibleGroupListHeight);
         setLayoutHeight(this.mDefaultControlLayout, desiredControlLayoutHeight);
      }

      setLayoutHeight(this.mExpandableAreaLayout, visibleRect.height());
      this.rebuildVolumeGroupList(animate);
   }

   void updateVolumeGroupItemHeight(View item) {
      LinearLayout container = (LinearLayout)item.findViewById(id.volume_item_container);
      setLayoutHeight(container, this.mVolumeGroupListItemHeight);
      View icon = item.findViewById(id.mr_volume_item_icon);
      LayoutParams lp = icon.getLayoutParams();
      lp.width = this.mVolumeGroupListItemIconSize;
      lp.height = this.mVolumeGroupListItemIconSize;
      icon.setLayoutParams(lp);
   }

   private void animateLayoutHeight(final View view, final int targetHeight) {
      final int startValue = getLayoutHeight(view);
      Animation anim = new Animation() {
         protected void applyTransformation(float interpolatedTime, Transformation t) {
            int height = startValue - (int)((float)(startValue - targetHeight) * interpolatedTime);
            MediaRouteControllerDialog.setLayoutHeight(view, height);
         }
      };
      anim.setDuration((long)this.mGroupListAnimationDurationMs);
      if (VERSION.SDK_INT >= 21) {
         anim.setInterpolator(this.mInterpolator);
      }

      view.startAnimation(anim);
   }

   void loadInterpolator() {
      if (VERSION.SDK_INT >= 21) {
         this.mInterpolator = this.mIsGroupExpanded ? this.mLinearOutSlowInInterpolator : this.mFastOutSlowInInterpolator;
      } else {
         this.mInterpolator = this.mAccelerateDecelerateInterpolator;
      }

   }

   private void updateVolumeControlLayout() {
      if (this.isVolumeControlAvailable(this.mRoute)) {
         if (this.mVolumeControlLayout.getVisibility() == 8) {
            this.mVolumeControlLayout.setVisibility(0);
            this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
            this.mVolumeSlider.setProgress(this.mRoute.getVolume());
            this.mGroupExpandCollapseButton.setVisibility(this.getGroup() == null ? 8 : 0);
         }
      } else {
         this.mVolumeControlLayout.setVisibility(8);
      }

   }

   private void rebuildVolumeGroupList(boolean animate) {
      List routes = this.getGroup() == null ? null : this.getGroup().getRoutes();
      if (routes == null) {
         this.mGroupMemberRoutes.clear();
         this.mVolumeGroupAdapter.notifyDataSetChanged();
      } else if (MediaRouteDialogHelper.listUnorderedEquals(this.mGroupMemberRoutes, routes)) {
         this.mVolumeGroupAdapter.notifyDataSetChanged();
      } else {
         HashMap previousRouteBoundMap = animate ? MediaRouteDialogHelper.getItemBoundMap(this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
         HashMap previousRouteBitmapMap = animate ? MediaRouteDialogHelper.getItemBitmapMap(this.mContext, this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
         this.mGroupMemberRoutesAdded = MediaRouteDialogHelper.getItemsAdded(this.mGroupMemberRoutes, routes);
         this.mGroupMemberRoutesRemoved = MediaRouteDialogHelper.getItemsRemoved(this.mGroupMemberRoutes, routes);
         this.mGroupMemberRoutes.addAll(0, this.mGroupMemberRoutesAdded);
         this.mGroupMemberRoutes.removeAll(this.mGroupMemberRoutesRemoved);
         this.mVolumeGroupAdapter.notifyDataSetChanged();
         if (animate && this.mIsGroupExpanded && this.mGroupMemberRoutesAdded.size() + this.mGroupMemberRoutesRemoved.size() > 0) {
            this.animateGroupListItems(previousRouteBoundMap, previousRouteBitmapMap);
         } else {
            this.mGroupMemberRoutesAdded = null;
            this.mGroupMemberRoutesRemoved = null;
         }
      }

   }

   private void animateGroupListItems(final Map previousRouteBoundMap, final Map previousRouteBitmapMap) {
      this.mVolumeGroupList.setEnabled(false);
      this.mVolumeGroupList.requestLayout();
      this.mIsGroupListAnimating = true;
      ViewTreeObserver observer = this.mVolumeGroupList.getViewTreeObserver();
      observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
         public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog.this.animateGroupListItemsInternal(previousRouteBoundMap, previousRouteBitmapMap);
         }
      });
   }

   void animateGroupListItemsInternal(Map previousRouteBoundMap, Map previousRouteBitmapMap) {
      if (this.mGroupMemberRoutesAdded != null && this.mGroupMemberRoutesRemoved != null) {
         int groupSizeDelta = this.mGroupMemberRoutesAdded.size() - this.mGroupMemberRoutesRemoved.size();
         boolean listenerRegistered = false;
         AnimationListener listener = new AnimationListener() {
            public void onAnimationStart(Animation animation) {
               MediaRouteControllerDialog.this.mVolumeGroupList.startAnimationAll();
               MediaRouteControllerDialog.this.mVolumeGroupList.postDelayed(MediaRouteControllerDialog.this.mGroupListFadeInAnimation, (long)MediaRouteControllerDialog.this.mGroupListAnimationDurationMs);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
         };
         int first = this.mVolumeGroupList.getFirstVisiblePosition();

         Rect bounds;
         int deltaY;
         for(int i = 0; i < this.mVolumeGroupList.getChildCount(); ++i) {
            View view = this.mVolumeGroupList.getChildAt(i);
            int position = first + i;
            MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(position);
            bounds = (Rect)previousRouteBoundMap.get(route);
            int currentTop = view.getTop();
            deltaY = bounds != null ? bounds.top : currentTop + this.mVolumeGroupListItemHeight * groupSizeDelta;
            AnimationSet animSet = new AnimationSet(true);
            if (this.mGroupMemberRoutesAdded != null && this.mGroupMemberRoutesAdded.contains(route)) {
               deltaY = currentTop;
               Animation alphaAnim = new AlphaAnimation(0.0F, 0.0F);
               alphaAnim.setDuration((long)this.mGroupListFadeInDurationMs);
               animSet.addAnimation(alphaAnim);
            }

            Animation translationAnim = new TranslateAnimation(0.0F, 0.0F, (float)(deltaY - currentTop), 0.0F);
            translationAnim.setDuration((long)this.mGroupListAnimationDurationMs);
            animSet.addAnimation(translationAnim);
            animSet.setFillAfter(true);
            animSet.setFillEnabled(true);
            animSet.setInterpolator(this.mInterpolator);
            if (!listenerRegistered) {
               listenerRegistered = true;
               animSet.setAnimationListener(listener);
            }

            view.clearAnimation();
            view.startAnimation(animSet);
            previousRouteBoundMap.remove(route);
            previousRouteBitmapMap.remove(route);
         }

         OverlayListView.OverlayObject object;
         for(Iterator var16 = previousRouteBitmapMap.entrySet().iterator(); var16.hasNext(); this.mVolumeGroupList.addOverlayObject(object)) {
            Entry item = (Entry)var16.next();
            final MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)item.getKey();
            BitmapDrawable bitmap = (BitmapDrawable)item.getValue();
            bounds = (Rect)previousRouteBoundMap.get(route);
            object = null;
            if (this.mGroupMemberRoutesRemoved.contains(route)) {
               object = (new OverlayListView.OverlayObject(bitmap, bounds)).setAlphaAnimation(1.0F, 0.0F).setDuration((long)this.mGroupListFadeOutDurationMs).setInterpolator(this.mInterpolator);
            } else {
               deltaY = groupSizeDelta * this.mVolumeGroupListItemHeight;
               object = (new OverlayListView.OverlayObject(bitmap, bounds)).setTranslateYAnimation(deltaY).setDuration((long)this.mGroupListAnimationDurationMs).setInterpolator(this.mInterpolator).setAnimationEndListener(new OverlayListView.OverlayObject.OnAnimationEndListener() {
                  public void onAnimationEnd() {
                     MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.remove(route);
                     MediaRouteControllerDialog.this.mVolumeGroupAdapter.notifyDataSetChanged();
                  }
               });
               this.mGroupMemberRoutesAnimatingWithBitmap.add(route);
            }
         }

      }
   }

   void startGroupListFadeInAnimation() {
      this.clearGroupListAnimation(true);
      this.mVolumeGroupList.requestLayout();
      ViewTreeObserver observer = this.mVolumeGroupList.getViewTreeObserver();
      observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
         public void onGlobalLayout() {
            MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog.this.startGroupListFadeInAnimationInternal();
         }
      });
   }

   void startGroupListFadeInAnimationInternal() {
      if (this.mGroupMemberRoutesAdded != null && this.mGroupMemberRoutesAdded.size() != 0) {
         this.fadeInAddedRoutes();
      } else {
         this.finishAnimation(true);
      }

   }

   void finishAnimation(boolean animate) {
      this.mGroupMemberRoutesAdded = null;
      this.mGroupMemberRoutesRemoved = null;
      this.mIsGroupListAnimating = false;
      if (this.mIsGroupListAnimationPending) {
         this.mIsGroupListAnimationPending = false;
         this.updateLayoutHeight(animate);
      }

      this.mVolumeGroupList.setEnabled(true);
   }

   private void fadeInAddedRoutes() {
      AnimationListener listener = new AnimationListener() {
         public void onAnimationStart(Animation animation) {
         }

         public void onAnimationEnd(Animation animation) {
            MediaRouteControllerDialog.this.finishAnimation(true);
         }

         public void onAnimationRepeat(Animation animation) {
         }
      };
      boolean listenerRegistered = false;
      int first = this.mVolumeGroupList.getFirstVisiblePosition();

      for(int i = 0; i < this.mVolumeGroupList.getChildCount(); ++i) {
         View view = this.mVolumeGroupList.getChildAt(i);
         int position = first + i;
         MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(position);
         if (this.mGroupMemberRoutesAdded.contains(route)) {
            Animation alphaAnim = new AlphaAnimation(0.0F, 1.0F);
            alphaAnim.setDuration((long)this.mGroupListFadeInDurationMs);
            alphaAnim.setFillEnabled(true);
            alphaAnim.setFillAfter(true);
            if (!listenerRegistered) {
               listenerRegistered = true;
               alphaAnim.setAnimationListener(listener);
            }

            view.clearAnimation();
            view.startAnimation(alphaAnim);
         }
      }

   }

   void clearGroupListAnimation(boolean exceptAddedRoutes) {
      int first = this.mVolumeGroupList.getFirstVisiblePosition();

      for(int i = 0; i < this.mVolumeGroupList.getChildCount(); ++i) {
         View view = this.mVolumeGroupList.getChildAt(i);
         int position = first + i;
         MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.mVolumeGroupAdapter.getItem(position);
         if (!exceptAddedRoutes || this.mGroupMemberRoutesAdded == null || !this.mGroupMemberRoutesAdded.contains(route)) {
            LinearLayout container = (LinearLayout)view.findViewById(id.volume_item_container);
            container.setVisibility(0);
            AnimationSet animSet = new AnimationSet(true);
            Animation alphaAnim = new AlphaAnimation(1.0F, 1.0F);
            alphaAnim.setDuration(0L);
            animSet.addAnimation(alphaAnim);
            Animation translationAnim = new TranslateAnimation(0.0F, 0.0F, 0.0F, 0.0F);
            translationAnim.setDuration(0L);
            animSet.setFillAfter(true);
            animSet.setFillEnabled(true);
            view.clearAnimation();
            view.startAnimation(animSet);
         }
      }

      this.mVolumeGroupList.stopAnimationAll();
      if (!exceptAddedRoutes) {
         this.finishAnimation(false);
      }

   }

   private void updatePlaybackControlLayout() {
      if (this.canShowPlaybackControlLayout()) {
         CharSequence title = this.mDescription == null ? null : this.mDescription.getTitle();
         boolean hasTitle = !TextUtils.isEmpty(title);
         CharSequence subtitle = this.mDescription == null ? null : this.mDescription.getSubtitle();
         boolean hasSubtitle = !TextUtils.isEmpty(subtitle);
         boolean showTitle = false;
         boolean showSubtitle = false;
         if (this.mRoute.getPresentationDisplayId() != -1) {
            this.mTitleView.setText(string.mr_controller_casting_screen);
            showTitle = true;
         } else if (this.mState != null && this.mState.getState() != 0) {
            if (!hasTitle && !hasSubtitle) {
               this.mTitleView.setText(string.mr_controller_no_info_available);
               showTitle = true;
            } else {
               if (hasTitle) {
                  this.mTitleView.setText(title);
                  showTitle = true;
               }

               if (hasSubtitle) {
                  this.mSubtitleView.setText(subtitle);
                  showSubtitle = true;
               }
            }
         } else {
            this.mTitleView.setText(string.mr_controller_no_media_selected);
            showTitle = true;
         }

         this.mTitleView.setVisibility(showTitle ? 0 : 8);
         this.mSubtitleView.setVisibility(showSubtitle ? 0 : 8);
         if (this.mState != null) {
            boolean isPlaying = this.mState.getState() == 6 || this.mState.getState() == 3;
            boolean supportsPlay = (this.mState.getActions() & 516L) != 0L;
            boolean supportsPause = (this.mState.getActions() & 514L) != 0L;
            Context playPauseButtonContext = this.mPlayPauseButton.getContext();
            if (isPlaying && supportsPause) {
               this.mPlayPauseButton.setVisibility(0);
               this.mPlayPauseButton.setImageResource(MediaRouterThemeHelper.getThemeResource(playPauseButtonContext, attr.mediaRoutePauseDrawable));
               this.mPlayPauseButton.setContentDescription(playPauseButtonContext.getResources().getText(string.mr_controller_pause));
            } else if (!isPlaying && supportsPlay) {
               this.mPlayPauseButton.setVisibility(0);
               this.mPlayPauseButton.setImageResource(MediaRouterThemeHelper.getThemeResource(playPauseButtonContext, attr.mediaRoutePlayDrawable));
               this.mPlayPauseButton.setContentDescription(playPauseButtonContext.getResources().getText(string.mr_controller_play));
            } else {
               this.mPlayPauseButton.setVisibility(8);
            }
         }
      }

   }

   boolean isVolumeControlAvailable(MediaRouter.RouteInfo route) {
      return this.mVolumeControlEnabled && route.getVolumeHandling() == 1;
   }

   private static int getLayoutHeight(View view) {
      return view.getLayoutParams().height;
   }

   static void setLayoutHeight(View view, int height) {
      LayoutParams lp = view.getLayoutParams();
      lp.height = height;
      view.setLayoutParams(lp);
   }

   private static boolean uriEquals(Uri uri1, Uri uri2) {
      if (uri1 != null && uri1.equals(uri2)) {
         return true;
      } else {
         return uri1 == null && uri2 == null;
      }
   }

   int getDesiredArtHeight(int originalWidth, int originalHeight) {
      return originalWidth >= originalHeight ? (int)((float)this.mDialogContentWidth * (float)originalHeight / (float)originalWidth + 0.5F) : (int)((float)this.mDialogContentWidth * 9.0F / 16.0F + 0.5F);
   }

   void updateArtIconIfNeeded() {
      if (this.mCustomControlView == null && this.isIconChanged()) {
         if (this.mFetchArtTask != null) {
            this.mFetchArtTask.cancel(true);
         }

         this.mFetchArtTask = new MediaRouteControllerDialog.FetchArtTask();
         this.mFetchArtTask.execute(new Void[0]);
      }
   }

   void clearLoadedBitmap() {
      this.mArtIconIsLoaded = false;
      this.mArtIconLoadedBitmap = null;
      this.mArtIconBackgroundColor = 0;
   }

   private boolean isIconChanged() {
      Bitmap newBitmap = this.mDescription == null ? null : this.mDescription.getIconBitmap();
      Uri newUri = this.mDescription == null ? null : this.mDescription.getIconUri();
      Bitmap oldBitmap = this.mFetchArtTask == null ? this.mArtIconBitmap : this.mFetchArtTask.getIconBitmap();
      Uri oldUri = this.mFetchArtTask == null ? this.mArtIconUri : this.mFetchArtTask.getIconUri();
      if (oldBitmap != newBitmap) {
         return true;
      } else {
         return oldBitmap == null && !uriEquals(oldUri, newUri);
      }
   }

   static {
      CONNECTION_TIMEOUT_MILLIS = (int)TimeUnit.SECONDS.toMillis(30L);
   }

   private class FetchArtTask extends AsyncTask {
      private static final long SHOW_ANIM_TIME_THRESHOLD_MILLIS = 120L;
      private final Bitmap mIconBitmap;
      private final Uri mIconUri;
      private int mBackgroundColor;
      private long mStartTimeMillis;

      FetchArtTask() {
         Bitmap bitmap = MediaRouteControllerDialog.this.mDescription == null ? null : MediaRouteControllerDialog.this.mDescription.getIconBitmap();
         if (MediaRouteControllerDialog.this.isBitmapRecycled(bitmap)) {
            Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
            bitmap = null;
         }

         this.mIconBitmap = bitmap;
         this.mIconUri = MediaRouteControllerDialog.this.mDescription == null ? null : MediaRouteControllerDialog.this.mDescription.getIconUri();
      }

      public Bitmap getIconBitmap() {
         return this.mIconBitmap;
      }

      public Uri getIconUri() {
         return this.mIconUri;
      }

      protected void onPreExecute() {
         this.mStartTimeMillis = SystemClock.uptimeMillis();
         MediaRouteControllerDialog.this.clearLoadedBitmap();
      }

      protected Bitmap doInBackground(Void... arg) {
         Bitmap art = null;
         if (this.mIconBitmap != null) {
            art = this.mIconBitmap;
         } else if (this.mIconUri != null) {
            InputStream stream = null;

            try {
               Options options;
               if ((stream = this.openInputStreamByScheme(this.mIconUri)) == null) {
                  Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri);
                  options = null;
                  return options;
               }

               options = new Options();
               options.inJustDecodeBounds = true;
               BitmapFactory.decodeStream(stream, (Rect)null, options);
               if (options.outWidth == 0 || options.outHeight == 0) {
                  Object var26 = null;
                  return (Bitmap)var26;
               }

               try {
                  stream.reset();
               } catch (IOException var22) {
                  stream.close();
                  if ((stream = this.openInputStreamByScheme(this.mIconUri)) == null) {
                     Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri);
                     Object var6 = null;
                     return (Bitmap)var6;
                  }
               }

               options.inJustDecodeBounds = false;
               int reqHeight = MediaRouteControllerDialog.this.getDesiredArtHeight(options.outWidth, options.outHeight);
               int ratio = options.outHeight / reqHeight;
               options.inSampleSize = Math.max(1, Integer.highestOneBit(ratio));
               if (this.isCancelled()) {
                  Object var7 = null;
                  return (Bitmap)var7;
               }

               art = BitmapFactory.decodeStream(stream, (Rect)null, options);
            } catch (IOException var23) {
               Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri, var23);
            } finally {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (IOException var21) {
                     ;
                  }
               }

            }
         }

         if (MediaRouteControllerDialog.this.isBitmapRecycled(art)) {
            Log.w("MediaRouteCtrlDialog", "Can't use recycled bitmap: " + art);
            return null;
         } else {
            if (art != null && art.getWidth() < art.getHeight()) {
               Palette palette = (new Builder(art)).maximumColorCount(1).generate();
               this.mBackgroundColor = palette.getSwatches().isEmpty() ? 0 : ((Swatch)palette.getSwatches().get(0)).getRgb();
            }

            return art;
         }
      }

      protected void onPostExecute(Bitmap art) {
         MediaRouteControllerDialog.this.mFetchArtTask = null;
         if (MediaRouteControllerDialog.this.mArtIconBitmap != this.mIconBitmap || MediaRouteControllerDialog.this.mArtIconUri != this.mIconUri) {
            MediaRouteControllerDialog.this.mArtIconBitmap = this.mIconBitmap;
            MediaRouteControllerDialog.this.mArtIconLoadedBitmap = art;
            MediaRouteControllerDialog.this.mArtIconUri = this.mIconUri;
            MediaRouteControllerDialog.this.mArtIconBackgroundColor = this.mBackgroundColor;
            MediaRouteControllerDialog.this.mArtIconIsLoaded = true;
            long elapsedTimeMillis = SystemClock.uptimeMillis() - this.mStartTimeMillis;
            MediaRouteControllerDialog.this.update(elapsedTimeMillis > 120L);
         }

      }

      private InputStream openInputStreamByScheme(Uri uri) throws IOException {
         String scheme = uri.getScheme().toLowerCase();
         InputStream stream = null;
         if (!"android.resource".equals(scheme) && !"content".equals(scheme) && !"file".equals(scheme)) {
            URL url = new URL(uri.toString());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS);
            conn.setReadTimeout(MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS);
            stream = conn.getInputStream();
         } else {
            stream = MediaRouteControllerDialog.this.mContext.getContentResolver().openInputStream(uri);
         }

         return stream == null ? null : new BufferedInputStream(stream);
      }
   }

   private class VolumeGroupAdapter extends ArrayAdapter {
      final float mDisabledAlpha;

      public VolumeGroupAdapter(Context context, List objects) {
         super(context, 0, objects);
         this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         View v = convertView;
         if (convertView == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(layout.mr_controller_volume_item, parent, false);
         } else {
            MediaRouteControllerDialog.this.updateVolumeGroupItemHeight(convertView);
         }

         MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.getItem(position);
         if (route != null) {
            boolean isEnabled = route.isEnabled();
            TextView routeName = (TextView)v.findViewById(id.mr_name);
            routeName.setEnabled(isEnabled);
            routeName.setText(route.getName());
            MediaRouteVolumeSlider volumeSlider = (MediaRouteVolumeSlider)v.findViewById(id.mr_volume_slider);
            MediaRouterThemeHelper.setVolumeSliderColor(parent.getContext(), volumeSlider, MediaRouteControllerDialog.this.mVolumeGroupList);
            volumeSlider.setTag(route);
            MediaRouteControllerDialog.this.mVolumeSliderMap.put(route, volumeSlider);
            volumeSlider.setHideThumb(!isEnabled);
            volumeSlider.setEnabled(isEnabled);
            if (isEnabled) {
               if (MediaRouteControllerDialog.this.isVolumeControlAvailable(route)) {
                  volumeSlider.setMax(route.getVolumeMax());
                  volumeSlider.setProgress(route.getVolume());
                  volumeSlider.setOnSeekBarChangeListener(MediaRouteControllerDialog.this.mVolumeChangeListener);
               } else {
                  volumeSlider.setMax(100);
                  volumeSlider.setProgress(100);
                  volumeSlider.setEnabled(false);
               }
            }

            ImageView volumeItemIcon = (ImageView)v.findViewById(id.mr_volume_item_icon);
            volumeItemIcon.setAlpha(isEnabled ? 255 : (int)(255.0F * this.mDisabledAlpha));
            LinearLayout container = (LinearLayout)v.findViewById(id.volume_item_container);
            container.setVisibility(MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.contains(route) ? 4 : 0);
            if (MediaRouteControllerDialog.this.mGroupMemberRoutesAdded != null && MediaRouteControllerDialog.this.mGroupMemberRoutesAdded.contains(route)) {
               Animation alphaAnim = new AlphaAnimation(0.0F, 0.0F);
               alphaAnim.setDuration(0L);
               alphaAnim.setFillEnabled(true);
               alphaAnim.setFillAfter(true);
               v.clearAnimation();
               v.startAnimation(alphaAnim);
            }
         }

         return v;
      }
   }

   private class VolumeChangeListener implements OnSeekBarChangeListener {
      private final Runnable mStopTrackingTouch = new Runnable() {
         public void run() {
            if (MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != null) {
               MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = null;
               if (MediaRouteControllerDialog.this.mHasPendingUpdate) {
                  MediaRouteControllerDialog.this.update(MediaRouteControllerDialog.this.mPendingUpdateAnimationNeeded);
               }
            }

         }
      };

      public void onStartTrackingTouch(SeekBar seekBar) {
         if (MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != null) {
            MediaRouteControllerDialog.this.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
         }

         MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = (MediaRouter.RouteInfo)seekBar.getTag();
      }

      public void onStopTrackingTouch(SeekBar seekBar) {
         MediaRouteControllerDialog.this.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 500L);
      }

      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
         if (fromUser) {
            MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)seekBar.getTag();
            if (MediaRouteControllerDialog.DEBUG) {
               Log.d("MediaRouteCtrlDialog", "onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(" + progress + ")");
            }

            route.requestSetVolume(progress);
         }

      }
   }

   private final class ClickListener implements OnClickListener {
      public void onClick(View v) {
         int id = v.getId();
         if (id != 16908313 && id != 16908314) {
            if (id == id.mr_control_play_pause) {
               if (MediaRouteControllerDialog.this.mMediaController != null && MediaRouteControllerDialog.this.mState != null) {
                  boolean isPlaying = MediaRouteControllerDialog.this.mState.getState() == 3;
                  if (isPlaying) {
                     MediaRouteControllerDialog.this.mMediaController.getTransportControls().pause();
                  } else {
                     MediaRouteControllerDialog.this.mMediaController.getTransportControls().play();
                  }

                  if (MediaRouteControllerDialog.this.mAccessibilityManager != null && MediaRouteControllerDialog.this.mAccessibilityManager.isEnabled()) {
                     AccessibilityEvent event = AccessibilityEvent.obtain(16384);
                     event.setPackageName(MediaRouteControllerDialog.this.mContext.getPackageName());
                     event.setClassName(this.getClass().getName());
                     int resId = isPlaying ? string.mr_controller_pause : string.mr_controller_play;
                     event.getText().add(MediaRouteControllerDialog.this.mContext.getString(resId));
                     MediaRouteControllerDialog.this.mAccessibilityManager.sendAccessibilityEvent(event);
                  }
               }
            } else if (id == id.mr_close) {
               MediaRouteControllerDialog.this.dismiss();
            }
         } else {
            if (MediaRouteControllerDialog.this.mRoute.isSelected()) {
               MediaRouteControllerDialog.this.mRouter.unselect(id == 16908313 ? 2 : 1);
            }

            MediaRouteControllerDialog.this.dismiss();
         }

      }
   }

   private final class MediaControllerCallback extends Callback {
      public void onSessionDestroyed() {
         if (MediaRouteControllerDialog.this.mMediaController != null) {
            MediaRouteControllerDialog.this.mMediaController.unregisterCallback(MediaRouteControllerDialog.this.mControllerCallback);
            MediaRouteControllerDialog.this.mMediaController = null;
         }

      }

      public void onPlaybackStateChanged(PlaybackStateCompat state) {
         MediaRouteControllerDialog.this.mState = state;
         MediaRouteControllerDialog.this.update(false);
      }

      public void onMetadataChanged(MediaMetadataCompat metadata) {
         MediaRouteControllerDialog.this.mDescription = metadata == null ? null : metadata.getDescription();
         MediaRouteControllerDialog.this.updateArtIconIfNeeded();
         MediaRouteControllerDialog.this.update(false);
      }
   }

   private final class MediaRouterCallback extends MediaRouter.Callback {
      public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route) {
         MediaRouteControllerDialog.this.update(false);
      }

      public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo route) {
         MediaRouteControllerDialog.this.update(true);
      }

      public void onRouteVolumeChanged(MediaRouter router, MediaRouter.RouteInfo route) {
         SeekBar volumeSlider = (SeekBar)MediaRouteControllerDialog.this.mVolumeSliderMap.get(route);
         int volume = route.getVolume();
         if (MediaRouteControllerDialog.DEBUG) {
            Log.d("MediaRouteCtrlDialog", "onRouteVolumeChanged(), route.getVolume:" + volume);
         }

         if (volumeSlider != null && MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != route) {
            volumeSlider.setProgress(volume);
         }

      }
   }
}
