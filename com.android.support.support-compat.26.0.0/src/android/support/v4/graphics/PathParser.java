package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class PathParser {
   private static final String LOGTAG = "PathParser";

   static float[] copyOfRange(float[] original, int start, int end) {
      if (start > end) {
         throw new IllegalArgumentException();
      } else {
         int originalLength = original.length;
         if (start >= 0 && start <= originalLength) {
            int resultLength = end - start;
            int copyLength = Math.min(resultLength, originalLength - start);
            float[] result = new float[resultLength];
            System.arraycopy(original, start, result, 0, copyLength);
            return result;
         } else {
            throw new ArrayIndexOutOfBoundsException();
         }
      }
   }

   public static Path createPathFromPathData(String pathData) {
      Path path = new Path();
      PathParser.PathDataNode[] nodes = createNodesFromPathData(pathData);
      if (nodes != null) {
         try {
            PathParser.PathDataNode.nodesToPath(nodes, path);
            return path;
         } catch (RuntimeException var4) {
            throw new RuntimeException("Error in parsing " + pathData, var4);
         }
      } else {
         return null;
      }
   }

   public static PathParser.PathDataNode[] createNodesFromPathData(String pathData) {
      if (pathData == null) {
         return null;
      } else {
         int start = 0;
         int end = 1;

         ArrayList list;
         for(list = new ArrayList(); end < pathData.length(); start = end++) {
            end = nextStart(pathData, end);
            String s = pathData.substring(start, end).trim();
            if (s.length() > 0) {
               float[] val = getFloats(s);
               addNode(list, s.charAt(0), val);
            }
         }

         if (end - start == 1 && start < pathData.length()) {
            addNode(list, pathData.charAt(start), new float[0]);
         }

         return (PathParser.PathDataNode[])list.toArray(new PathParser.PathDataNode[list.size()]);
      }
   }

   public static PathParser.PathDataNode[] deepCopyNodes(PathParser.PathDataNode[] source) {
      if (source == null) {
         return null;
      } else {
         PathParser.PathDataNode[] copy = new PathParser.PathDataNode[source.length];

         for(int i = 0; i < source.length; ++i) {
            copy[i] = new PathParser.PathDataNode(source[i]);
         }

         return copy;
      }
   }

   public static boolean canMorph(PathParser.PathDataNode[] nodesFrom, PathParser.PathDataNode[] nodesTo) {
      if (nodesFrom != null && nodesTo != null) {
         if (nodesFrom.length != nodesTo.length) {
            return false;
         } else {
            for(int i = 0; i < nodesFrom.length; ++i) {
               if (nodesFrom[i].mType != nodesTo[i].mType || nodesFrom[i].mParams.length != nodesTo[i].mParams.length) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static void updateNodes(PathParser.PathDataNode[] target, PathParser.PathDataNode[] source) {
      for(int i = 0; i < source.length; ++i) {
         target[i].mType = source[i].mType;

         for(int j = 0; j < source[i].mParams.length; ++j) {
            target[i].mParams[j] = source[i].mParams[j];
         }
      }

   }

   private static int nextStart(String s, int end) {
      while(end < s.length()) {
         char c = s.charAt(end);
         if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E') {
            return end;
         }

         ++end;
      }

      return end;
   }

   private static void addNode(ArrayList list, char cmd, float[] val) {
      list.add(new PathParser.PathDataNode(cmd, val));
   }

   private static float[] getFloats(String s) {
      if (s.charAt(0) != 'z' && s.charAt(0) != 'Z') {
         try {
            float[] results = new float[s.length()];
            int count = 0;
            int startPosition = 1;
            int endPosition = false;
            PathParser.ExtractFloatResult result = new PathParser.ExtractFloatResult();
            int totalLength = s.length();

            while(startPosition < totalLength) {
               extract(s, startPosition, result);
               int endPosition = result.mEndPosition;
               if (startPosition < endPosition) {
                  results[count++] = Float.parseFloat(s.substring(startPosition, endPosition));
               }

               if (result.mEndWithNegOrDot) {
                  startPosition = endPosition;
               } else {
                  startPosition = endPosition + 1;
               }
            }

            return copyOfRange(results, 0, count);
         } catch (NumberFormatException var7) {
            throw new RuntimeException("error in parsing \"" + s + "\"", var7);
         }
      } else {
         return new float[0];
      }
   }

   private static void extract(String s, int start, PathParser.ExtractFloatResult result) {
      int currentIndex = start;
      boolean foundSeparator = false;
      result.mEndWithNegOrDot = false;
      boolean secondDot = false;

      for(boolean isExponential = false; currentIndex < s.length(); ++currentIndex) {
         boolean isPrevExponential = isExponential;
         isExponential = false;
         char currentChar = s.charAt(currentIndex);
         switch(currentChar) {
         case ' ':
         case ',':
            foundSeparator = true;
            break;
         case '-':
            if (currentIndex != start && !isPrevExponential) {
               foundSeparator = true;
               result.mEndWithNegOrDot = true;
            }
            break;
         case '.':
            if (!secondDot) {
               secondDot = true;
            } else {
               foundSeparator = true;
               result.mEndWithNegOrDot = true;
            }
            break;
         case 'E':
         case 'e':
            isExponential = true;
         }

         if (foundSeparator) {
            break;
         }
      }

      result.mEndPosition = currentIndex;
   }

   public static class PathDataNode {
      @RestrictTo({Scope.LIBRARY_GROUP})
      public char mType;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public float[] mParams;

      PathDataNode(char type, float[] params) {
         this.mType = type;
         this.mParams = params;
      }

      PathDataNode(PathParser.PathDataNode n) {
         this.mType = n.mType;
         this.mParams = PathParser.copyOfRange(n.mParams, 0, n.mParams.length);
      }

      public static void nodesToPath(PathParser.PathDataNode[] node, Path path) {
         float[] current = new float[6];
         char previousCommand = 'm';

         for(int i = 0; i < node.length; ++i) {
            addCommand(path, current, previousCommand, node[i].mType, node[i].mParams);
            previousCommand = node[i].mType;
         }

      }

      public void interpolatePathDataNode(PathParser.PathDataNode nodeFrom, PathParser.PathDataNode nodeTo, float fraction) {
         for(int i = 0; i < nodeFrom.mParams.length; ++i) {
            this.mParams[i] = nodeFrom.mParams[i] * (1.0F - fraction) + nodeTo.mParams[i] * fraction;
         }

      }

      private static void addCommand(Path path, float[] current, char previousCmd, char cmd, float[] val) {
         int incr = 2;
         float currentX = current[0];
         float currentY = current[1];
         float ctrlPointX = current[2];
         float ctrlPointY = current[3];
         float currentSegmentStartX = current[4];
         float currentSegmentStartY = current[5];
         switch(cmd) {
         case 'A':
         case 'a':
            incr = 7;
         case 'B':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'I':
         case 'J':
         case 'K':
         case 'N':
         case 'O':
         case 'P':
         case 'R':
         case 'U':
         case 'W':
         case 'X':
         case 'Y':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'b':
         case 'd':
         case 'e':
         case 'f':
         case 'g':
         case 'i':
         case 'j':
         case 'k':
         case 'n':
         case 'o':
         case 'p':
         case 'r':
         case 'u':
         case 'w':
         case 'x':
         case 'y':
         default:
            break;
         case 'C':
         case 'c':
            incr = 6;
            break;
         case 'H':
         case 'V':
         case 'h':
         case 'v':
            incr = 1;
            break;
         case 'L':
         case 'M':
         case 'T':
         case 'l':
         case 'm':
         case 't':
            incr = 2;
            break;
         case 'Q':
         case 'S':
         case 'q':
         case 's':
            incr = 4;
            break;
         case 'Z':
         case 'z':
            path.close();
            currentX = currentSegmentStartX;
            currentY = currentSegmentStartY;
            ctrlPointX = currentSegmentStartX;
            ctrlPointY = currentSegmentStartY;
            path.moveTo(currentSegmentStartX, currentSegmentStartY);
         }

         for(int k = 0; k < val.length; k += incr) {
            float reflectiveCtrlPointX;
            float reflectiveCtrlPointY;
            switch(cmd) {
            case 'A':
               drawArc(path, currentX, currentY, val[k + 5], val[k + 6], val[k + 0], val[k + 1], val[k + 2], val[k + 3] != 0.0F, val[k + 4] != 0.0F);
               currentX = val[k + 5];
               currentY = val[k + 6];
               ctrlPointX = currentX;
               ctrlPointY = currentY;
            case 'B':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'I':
            case 'J':
            case 'K':
            case 'N':
            case 'O':
            case 'P':
            case 'R':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'i':
            case 'j':
            case 'k':
            case 'n':
            case 'o':
            case 'p':
            case 'r':
            case 'u':
            default:
               break;
            case 'C':
               path.cubicTo(val[k + 0], val[k + 1], val[k + 2], val[k + 3], val[k + 4], val[k + 5]);
               currentX = val[k + 4];
               currentY = val[k + 5];
               ctrlPointX = val[k + 2];
               ctrlPointY = val[k + 3];
               break;
            case 'H':
               path.lineTo(val[k + 0], currentY);
               currentX = val[k + 0];
               break;
            case 'L':
               path.lineTo(val[k + 0], val[k + 1]);
               currentX = val[k + 0];
               currentY = val[k + 1];
               break;
            case 'M':
               currentX = val[k + 0];
               currentY = val[k + 1];
               if (k > 0) {
                  path.lineTo(val[k + 0], val[k + 1]);
               } else {
                  path.moveTo(val[k + 0], val[k + 1]);
                  currentSegmentStartX = currentX;
                  currentSegmentStartY = currentY;
               }
               break;
            case 'Q':
               path.quadTo(val[k + 0], val[k + 1], val[k + 2], val[k + 3]);
               ctrlPointX = val[k + 0];
               ctrlPointY = val[k + 1];
               currentX = val[k + 2];
               currentY = val[k + 3];
               break;
            case 'S':
               reflectiveCtrlPointX = currentX;
               reflectiveCtrlPointY = currentY;
               if (previousCmd == 'c' || previousCmd == 's' || previousCmd == 'C' || previousCmd == 'S') {
                  reflectiveCtrlPointX = 2.0F * currentX - ctrlPointX;
                  reflectiveCtrlPointY = 2.0F * currentY - ctrlPointY;
               }

               path.cubicTo(reflectiveCtrlPointX, reflectiveCtrlPointY, val[k + 0], val[k + 1], val[k + 2], val[k + 3]);
               ctrlPointX = val[k + 0];
               ctrlPointY = val[k + 1];
               currentX = val[k + 2];
               currentY = val[k + 3];
               break;
            case 'T':
               reflectiveCtrlPointX = currentX;
               reflectiveCtrlPointY = currentY;
               if (previousCmd == 'q' || previousCmd == 't' || previousCmd == 'Q' || previousCmd == 'T') {
                  reflectiveCtrlPointX = 2.0F * currentX - ctrlPointX;
                  reflectiveCtrlPointY = 2.0F * currentY - ctrlPointY;
               }

               path.quadTo(reflectiveCtrlPointX, reflectiveCtrlPointY, val[k + 0], val[k + 1]);
               ctrlPointX = reflectiveCtrlPointX;
               ctrlPointY = reflectiveCtrlPointY;
               currentX = val[k + 0];
               currentY = val[k + 1];
               break;
            case 'V':
               path.lineTo(currentX, val[k + 0]);
               currentY = val[k + 0];
               break;
            case 'a':
               drawArc(path, currentX, currentY, val[k + 5] + currentX, val[k + 6] + currentY, val[k + 0], val[k + 1], val[k + 2], val[k + 3] != 0.0F, val[k + 4] != 0.0F);
               currentX += val[k + 5];
               currentY += val[k + 6];
               ctrlPointX = currentX;
               ctrlPointY = currentY;
               break;
            case 'c':
               path.rCubicTo(val[k + 0], val[k + 1], val[k + 2], val[k + 3], val[k + 4], val[k + 5]);
               ctrlPointX = currentX + val[k + 2];
               ctrlPointY = currentY + val[k + 3];
               currentX += val[k + 4];
               currentY += val[k + 5];
               break;
            case 'h':
               path.rLineTo(val[k + 0], 0.0F);
               currentX += val[k + 0];
               break;
            case 'l':
               path.rLineTo(val[k + 0], val[k + 1]);
               currentX += val[k + 0];
               currentY += val[k + 1];
               break;
            case 'm':
               currentX += val[k + 0];
               currentY += val[k + 1];
               if (k > 0) {
                  path.rLineTo(val[k + 0], val[k + 1]);
               } else {
                  path.rMoveTo(val[k + 0], val[k + 1]);
                  currentSegmentStartX = currentX;
                  currentSegmentStartY = currentY;
               }
               break;
            case 'q':
               path.rQuadTo(val[k + 0], val[k + 1], val[k + 2], val[k + 3]);
               ctrlPointX = currentX + val[k + 0];
               ctrlPointY = currentY + val[k + 1];
               currentX += val[k + 2];
               currentY += val[k + 3];
               break;
            case 's':
               reflectiveCtrlPointX = 0.0F;
               reflectiveCtrlPointY = 0.0F;
               if (previousCmd == 'c' || previousCmd == 's' || previousCmd == 'C' || previousCmd == 'S') {
                  reflectiveCtrlPointX = currentX - ctrlPointX;
                  reflectiveCtrlPointY = currentY - ctrlPointY;
               }

               path.rCubicTo(reflectiveCtrlPointX, reflectiveCtrlPointY, val[k + 0], val[k + 1], val[k + 2], val[k + 3]);
               ctrlPointX = currentX + val[k + 0];
               ctrlPointY = currentY + val[k + 1];
               currentX += val[k + 2];
               currentY += val[k + 3];
               break;
            case 't':
               reflectiveCtrlPointX = 0.0F;
               reflectiveCtrlPointY = 0.0F;
               if (previousCmd == 'q' || previousCmd == 't' || previousCmd == 'Q' || previousCmd == 'T') {
                  reflectiveCtrlPointX = currentX - ctrlPointX;
                  reflectiveCtrlPointY = currentY - ctrlPointY;
               }

               path.rQuadTo(reflectiveCtrlPointX, reflectiveCtrlPointY, val[k + 0], val[k + 1]);
               ctrlPointX = currentX + reflectiveCtrlPointX;
               ctrlPointY = currentY + reflectiveCtrlPointY;
               currentX += val[k + 0];
               currentY += val[k + 1];
               break;
            case 'v':
               path.rLineTo(0.0F, val[k + 0]);
               currentY += val[k + 0];
            }

            previousCmd = cmd;
         }

         current[0] = currentX;
         current[1] = currentY;
         current[2] = ctrlPointX;
         current[3] = ctrlPointY;
         current[4] = currentSegmentStartX;
         current[5] = currentSegmentStartY;
      }

      private static void drawArc(Path p, float x0, float y0, float x1, float y1, float a, float b, float theta, boolean isMoreThanHalf, boolean isPositiveArc) {
         double thetaD = Math.toRadians((double)theta);
         double cosTheta = Math.cos(thetaD);
         double sinTheta = Math.sin(thetaD);
         double x0p = ((double)x0 * cosTheta + (double)y0 * sinTheta) / (double)a;
         double y0p = ((double)(-x0) * sinTheta + (double)y0 * cosTheta) / (double)b;
         double x1p = ((double)x1 * cosTheta + (double)y1 * sinTheta) / (double)a;
         double y1p = ((double)(-x1) * sinTheta + (double)y1 * cosTheta) / (double)b;
         double dx = x0p - x1p;
         double dy = y0p - y1p;
         double xm = (x0p + x1p) / 2.0D;
         double ym = (y0p + y1p) / 2.0D;
         double dsq = dx * dx + dy * dy;
         if (dsq == 0.0D) {
            Log.w("PathParser", " Points are coincident");
         } else {
            double disc = 1.0D / dsq - 0.25D;
            if (disc < 0.0D) {
               Log.w("PathParser", "Points are too far apart " + dsq);
               float adjust = (float)(Math.sqrt(dsq) / 1.99999D);
               drawArc(p, x0, y0, x1, y1, a * adjust, b * adjust, theta, isMoreThanHalf, isPositiveArc);
            } else {
               double s = Math.sqrt(disc);
               double sdx = s * dx;
               double sdy = s * dy;
               double cx;
               double cy;
               if (isMoreThanHalf == isPositiveArc) {
                  cx = xm - sdy;
                  cy = ym + sdx;
               } else {
                  cx = xm + sdy;
                  cy = ym - sdx;
               }

               double eta0 = Math.atan2(y0p - cy, x0p - cx);
               double eta1 = Math.atan2(y1p - cy, x1p - cx);
               double sweep = eta1 - eta0;
               if (isPositiveArc != sweep >= 0.0D) {
                  if (sweep > 0.0D) {
                     sweep -= 6.283185307179586D;
                  } else {
                     sweep += 6.283185307179586D;
                  }
               }

               cx *= (double)a;
               cy *= (double)b;
               double tcx = cx;
               cx = cx * cosTheta - cy * sinTheta;
               cy = tcx * sinTheta + cy * cosTheta;
               arcToBezier(p, cx, cy, (double)a, (double)b, (double)x0, (double)y0, thetaD, eta0, sweep);
            }
         }
      }

      private static void arcToBezier(Path p, double cx, double cy, double a, double b, double e1x, double e1y, double theta, double start, double sweep) {
         int numSegments = (int)Math.ceil(Math.abs(sweep * 4.0D / 3.141592653589793D));
         double eta1 = start;
         double cosTheta = Math.cos(theta);
         double sinTheta = Math.sin(theta);
         double cosEta1 = Math.cos(start);
         double sinEta1 = Math.sin(start);
         double ep1x = -a * cosTheta * sinEta1 - b * sinTheta * cosEta1;
         double ep1y = -a * sinTheta * sinEta1 + b * cosTheta * cosEta1;
         double anglePerSegment = sweep / (double)numSegments;

         for(int i = 0; i < numSegments; ++i) {
            double eta2 = eta1 + anglePerSegment;
            double sinEta2 = Math.sin(eta2);
            double cosEta2 = Math.cos(eta2);
            double e2x = cx + a * cosTheta * cosEta2 - b * sinTheta * sinEta2;
            double e2y = cy + a * sinTheta * cosEta2 + b * cosTheta * sinEta2;
            double ep2x = -a * cosTheta * sinEta2 - b * sinTheta * cosEta2;
            double ep2y = -a * sinTheta * sinEta2 + b * cosTheta * cosEta2;
            double tanDiff2 = Math.tan((eta2 - eta1) / 2.0D);
            double alpha = Math.sin(eta2 - eta1) * (Math.sqrt(4.0D + 3.0D * tanDiff2 * tanDiff2) - 1.0D) / 3.0D;
            double q1x = e1x + alpha * ep1x;
            double q1y = e1y + alpha * ep1y;
            double q2x = e2x - alpha * ep2x;
            double q2y = e2y - alpha * ep2y;
            p.rLineTo(0.0F, 0.0F);
            p.cubicTo((float)q1x, (float)q1y, (float)q2x, (float)q2y, (float)e2x, (float)e2y);
            eta1 = eta2;
            e1x = e2x;
            e1y = e2y;
            ep1x = ep2x;
            ep1y = ep2y;
         }

      }
   }

   private static class ExtractFloatResult {
      int mEndPosition;
      boolean mEndWithNegOrDot;
   }
}
