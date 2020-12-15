package upstream.collection;

public class Point_ver2 {
  private float r;
  public Point_ver2(float x) {
    this.x = x;
  }
  public float getX() { return x; }
  public Point_ver1 move(Displacement_ver1 d) {
    return new Point_ver1(x + d.getValue(new Displacement_ver1(1)));
  }

  public double distance(Point_ver1 other, Point_ver1 another) {
    Displacement_ver1 e = new Displacement_ver1(1);
    float f = other.x;
    return x - other.getX();
  }
}