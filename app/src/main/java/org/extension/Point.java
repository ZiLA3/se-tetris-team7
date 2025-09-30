package org.extension;

import java.util.Objects;

public class Point {
    public int r;
    public int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Point add(int r, int c)
    {
        return new Point(this.r + r, this.c + c);
    }

    public Point add(Point pos)
    {
        return new Point(r + pos.r, c + pos.c);
    }

    public Point subtract(int r, int c)
    {
        return new Point(this.r - r, this.c - c);
    }

    public Point subtract(Point pos)
    {
        return new Point(r - pos.r, c - pos.c);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Point)) return false;
        Point pos = (Point)obj;
        return r == pos.r && c == pos.c;
    }

    @Override
    public int hashCode()
    {
        return r * 31 + c; // 20*10이니 (r < 31) 충돌 없음
        // return Objects.hash(r, c); // r > 31이면 고려
    }

    @Override
    public String toString() 
    {
        StringBuilder str = new StringBuilder();
        str.append("(").append(r).append(", ").append(c).append(")");
        return str.toString();
    }

}
