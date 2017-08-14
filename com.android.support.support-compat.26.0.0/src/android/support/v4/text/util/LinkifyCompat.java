package android.support.v4.text.util;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
   private static final String[] EMPTY_STRING = new String[0];
   private static final Comparator COMPARATOR = new Comparator() {
      public final int compare(LinkifyCompat.LinkSpec a, LinkifyCompat.LinkSpec b) {
         if (a.start < b.start) {
            return -1;
         } else if (a.start > b.start) {
            return 1;
         } else if (a.end < b.end) {
            return 1;
         } else {
            return a.end > b.end ? -1 : 0;
         }
      }
   };

   public static final boolean addLinks(@NonNull Spannable text, int mask) {
      if (VERSION.SDK_INT >= 26) {
         return Linkify.addLinks(text, mask);
      } else if (mask == 0) {
         return false;
      } else {
         URLSpan[] old = (URLSpan[])text.getSpans(0, text.length(), URLSpan.class);

         for(int i = old.length - 1; i >= 0; --i) {
            text.removeSpan(old[i]);
         }

         boolean frameworkReturn = false;
         if ((mask & 4) != 0) {
            frameworkReturn = Linkify.addLinks(text, 4);
         }

         ArrayList links = new ArrayList();
         if ((mask & 1) != 0) {
            gatherLinks(links, text, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, (TransformFilter)null);
         }

         if ((mask & 2) != 0) {
            gatherLinks(links, text, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, (MatchFilter)null, (TransformFilter)null);
         }

         if ((mask & 8) != 0) {
            gatherMapLinks(links, text);
         }

         pruneOverlaps(links, text);
         if (links.size() == 0) {
            return false;
         } else {
            Iterator var5 = links.iterator();

            while(var5.hasNext()) {
               LinkifyCompat.LinkSpec link = (LinkifyCompat.LinkSpec)var5.next();
               if (link.frameworkAddedSpan == null) {
                  applyLink(link.url, link.start, link.end, text);
               }
            }

            return true;
         }
      }
   }

   public static final boolean addLinks(@NonNull TextView text, int mask) {
      if (VERSION.SDK_INT >= 26) {
         return Linkify.addLinks(text, mask);
      } else if (mask == 0) {
         return false;
      } else {
         CharSequence t = text.getText();
         if (t instanceof Spannable) {
            if (addLinks((Spannable)t, mask)) {
               addLinkMovementMethod(text);
               return true;
            } else {
               return false;
            }
         } else {
            SpannableString s = SpannableString.valueOf(t);
            if (addLinks((Spannable)s, mask)) {
               addLinkMovementMethod(text);
               text.setText(s);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public static final void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String scheme) {
      if (VERSION.SDK_INT >= 26) {
         Linkify.addLinks(text, pattern, scheme);
      } else {
         addLinks((TextView)text, pattern, scheme, (String[])null, (MatchFilter)null, (TransformFilter)null);
      }
   }

   public static final void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String scheme, @Nullable MatchFilter matchFilter, @Nullable TransformFilter transformFilter) {
      if (VERSION.SDK_INT >= 26) {
         Linkify.addLinks(text, pattern, scheme, matchFilter, transformFilter);
      } else {
         addLinks((TextView)text, pattern, scheme, (String[])null, matchFilter, transformFilter);
      }
   }

   public static final void addLinks(@NonNull TextView text, @NonNull Pattern pattern, @Nullable String defaultScheme, @Nullable String[] schemes, @Nullable MatchFilter matchFilter, @Nullable TransformFilter transformFilter) {
      if (VERSION.SDK_INT >= 26) {
         Linkify.addLinks(text, pattern, defaultScheme, schemes, matchFilter, transformFilter);
      } else {
         SpannableString spannable = SpannableString.valueOf(text.getText());
         boolean linksAdded = addLinks((Spannable)spannable, pattern, defaultScheme, schemes, matchFilter, transformFilter);
         if (linksAdded) {
            text.setText(spannable);
            addLinkMovementMethod(text);
         }

      }
   }

   public static final boolean addLinks(@NonNull Spannable text, @NonNull Pattern pattern, @Nullable String scheme) {
      return VERSION.SDK_INT >= 26 ? Linkify.addLinks(text, pattern, scheme) : addLinks((Spannable)text, pattern, scheme, (String[])null, (MatchFilter)null, (TransformFilter)null);
   }

   public static final boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String scheme, @Nullable MatchFilter matchFilter, @Nullable TransformFilter transformFilter) {
      return VERSION.SDK_INT >= 26 ? Linkify.addLinks(spannable, pattern, scheme, matchFilter, transformFilter) : addLinks((Spannable)spannable, pattern, scheme, (String[])null, matchFilter, transformFilter);
   }

   public static final boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String defaultScheme, @Nullable String[] schemes, @Nullable MatchFilter matchFilter, @Nullable TransformFilter transformFilter) {
      if (VERSION.SDK_INT >= 26) {
         return Linkify.addLinks(spannable, pattern, defaultScheme, schemes, matchFilter, transformFilter);
      } else {
         if (defaultScheme == null) {
            defaultScheme = "";
         }

         if (schemes == null || schemes.length < 1) {
            schemes = EMPTY_STRING;
         }

         String[] schemesCopy = new String[schemes.length + 1];
         schemesCopy[0] = defaultScheme.toLowerCase(Locale.ROOT);

         for(int index = 0; index < schemes.length; ++index) {
            String scheme = schemes[index];
            schemesCopy[index + 1] = scheme == null ? "" : scheme.toLowerCase(Locale.ROOT);
         }

         boolean hasMatches = false;
         Matcher m = pattern.matcher(spannable);

         while(m.find()) {
            int start = m.start();
            int end = m.end();
            boolean allowed = true;
            if (matchFilter != null) {
               allowed = matchFilter.acceptMatch(spannable, start, end);
            }

            if (allowed) {
               String url = makeUrl(m.group(0), schemesCopy, m, transformFilter);
               applyLink(url, start, end, spannable);
               hasMatches = true;
            }
         }

         return hasMatches;
      }
   }

   private static void addLinkMovementMethod(@NonNull TextView t) {
      MovementMethod m = t.getMovementMethod();
      if ((m == null || !(m instanceof LinkMovementMethod)) && t.getLinksClickable()) {
         t.setMovementMethod(LinkMovementMethod.getInstance());
      }

   }

   private static String makeUrl(@NonNull String url, @NonNull String[] prefixes, Matcher matcher, @Nullable TransformFilter filter) {
      if (filter != null) {
         url = filter.transformUrl(matcher, url);
      }

      boolean hasPrefix = false;

      for(int i = 0; i < prefixes.length; ++i) {
         if (url.regionMatches(true, 0, prefixes[i], 0, prefixes[i].length())) {
            hasPrefix = true;
            if (!url.regionMatches(false, 0, prefixes[i], 0, prefixes[i].length())) {
               url = prefixes[i] + url.substring(prefixes[i].length());
            }
            break;
         }
      }

      if (!hasPrefix && prefixes.length > 0) {
         url = prefixes[0] + url;
      }

      return url;
   }

   private static void gatherLinks(ArrayList links, Spannable s, Pattern pattern, String[] schemes, MatchFilter matchFilter, TransformFilter transformFilter) {
      Matcher m = pattern.matcher(s);

      while(true) {
         int start;
         int end;
         do {
            if (!m.find()) {
               return;
            }

            start = m.start();
            end = m.end();
         } while(matchFilter != null && !matchFilter.acceptMatch(s, start, end));

         LinkifyCompat.LinkSpec spec = new LinkifyCompat.LinkSpec();
         String url = makeUrl(m.group(0), schemes, m, transformFilter);
         spec.url = url;
         spec.start = start;
         spec.end = end;
         links.add(spec);
      }
   }

   private static void applyLink(String url, int start, int end, Spannable text) {
      URLSpan span = new URLSpan(url);
      text.setSpan(span, start, end, 33);
   }

   private static final void gatherMapLinks(ArrayList links, Spannable s) {
      String string = s.toString();
      int base = 0;

      try {
         String address;
         while((address = WebView.findAddress(string)) != null) {
            int start = string.indexOf(address);
            if (start < 0) {
               break;
            }

            LinkifyCompat.LinkSpec spec = new LinkifyCompat.LinkSpec();
            int length = address.length();
            int end = start + length;
            spec.start = base + start;
            spec.end = base + end;
            string = string.substring(end);
            base += end;
            String encodedAddress = null;

            try {
               encodedAddress = URLEncoder.encode(address, "UTF-8");
            } catch (UnsupportedEncodingException var11) {
               continue;
            }

            spec.url = "geo:0,0?q=" + encodedAddress;
            links.add(spec);
         }

      } catch (UnsupportedOperationException var12) {
         ;
      }
   }

   private static final void pruneOverlaps(ArrayList links, Spannable text) {
      URLSpan[] urlSpans = (URLSpan[])text.getSpans(0, text.length(), URLSpan.class);

      int len;
      for(len = 0; len < urlSpans.length; ++len) {
         LinkifyCompat.LinkSpec spec = new LinkifyCompat.LinkSpec();
         spec.frameworkAddedSpan = urlSpans[len];
         spec.start = text.getSpanStart(urlSpans[len]);
         spec.end = text.getSpanEnd(urlSpans[len]);
         links.add(spec);
      }

      Collections.sort(links, COMPARATOR);
      len = links.size();
      int i = 0;

      while(true) {
         while(i < len - 1) {
            LinkifyCompat.LinkSpec a = (LinkifyCompat.LinkSpec)links.get(i);
            LinkifyCompat.LinkSpec b = (LinkifyCompat.LinkSpec)links.get(i + 1);
            int remove = -1;
            if (a.start <= b.start && a.end > b.start) {
               if (b.end <= a.end) {
                  remove = i + 1;
               } else if (a.end - a.start > b.end - b.start) {
                  remove = i + 1;
               } else if (a.end - a.start < b.end - b.start) {
                  remove = i;
               }

               if (remove != -1) {
                  URLSpan span = ((LinkifyCompat.LinkSpec)links.get(remove)).frameworkAddedSpan;
                  if (span != null) {
                     text.removeSpan(span);
                  }

                  links.remove(remove);
                  --len;
                  continue;
               }
            }

            ++i;
         }

         return;
      }
   }

   private static class LinkSpec {
      URLSpan frameworkAddedSpan;
      String url;
      int start;
      int end;
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface LinkifyMask {
   }
}
