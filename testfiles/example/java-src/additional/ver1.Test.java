package additional.ver1;
import upstream.collection.ver1.Point;
import upstream.collection.ver2.Point;
import upstream.collection.ver3.Point;
/**
 *  upstream.collection.Point is imported when the overview looks as follow:
 *  overview of class Point {
 *    Point(float, float) in version 1,2,3;
 *    float getX() in version 1;
 *    float getY() in version 1;
 *    float distance(Point) in version 1,2,3;
 *    float getR() in version 2,3;
 *    float getAngle() in version 2,3;
 *    boolean returnTrue() in version 3;
 *  }
 */
import mid.empty.ver1.Empty;
import mid.empty.ver2.Empty;
/**
 *  mid.empty.Empty is imported when the overview looks as follow:
 *  overview of class Empty {
 *    Empty() in version 1,2;
 *  }
 */
import downstream.application.ver1.Main;
import downstream.application.ver2.Main;
/**
 *  downstream.application.Main is imported when the overview looks as follow:
 *  overview of class Main {
 *    version 1 requires upstream.collection.Point version 1;
 *    version 1 requires mid.utility.* version 1;
 *    version 1 requires mid.empty.Empty version 1,2;
 *    static Point createPoint(Point, Empty) in version 1,2;
 *    static Empty createEmpty(Point) in version 1,2;
 *    static void main(String[]) in version 1,2;
 *    version 2 requires upstream.collection.Point version 1,2;
 *    version 2 requires mid.utility.* version 1;
 *    version 2 requires mid.empty.Empty version 1,2;
 *  }
 */

public class Test {
  public static void main(String[] args) {

    ver?.Point p = ver?.Main.createPoint(new ver?.Point(0f,0f), new ver?.Empty());
    /*             down::1|2  down::1|2
       up::1       down::1 -> up::1 mid::1|2     up::1                mid::1|2
       up::1|2     down::2 -> up::1|2 mid::1|2   up::1|2              mid::1|2
     */
    ver?.Empty e = ver?.Main.createEmpty(new ver?.Point(0f,0f));
    /*             down::1|2  down::1|2
       mid::1|2    down::1 -> up::1 mid::1|2     up::1                mid::1|2
       mid::1|2    down::2 -> up::1|2 mid::1|2   up::1|2              mid::1|2
     */

    /*
        Since both p and e are not invoking any other methods afterwards,
        we can safely choose the largest version. Of course this could be
        solved by building constraints then throwing them to a constraint
        solver to solve.
     */
    ver2.Point p = ver2.Main.createPoint(new ver2.Point(0f,0f), new ver2.Empty());
    ver2.Empty e = ver2.Main.createEmpty(new ver2.Point(0f,0f));

  }
}