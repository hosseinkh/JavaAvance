package fr.dauphine.javaavance.td1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class Point {
	private int x, y; /* pour etre immutable definir toutes les attribus comme final private et redefinir la methode translate.*/
	static int number_of_points;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		number_of_points++;
	}

	public Point(Point p2) {
		this.x = p2.x;
		this.y = p2.y;
	}

	public int getterX() {
		return this.x;
	}

	public int getterY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	@Override
	public boolean equals(Object o) {
		Point p = (Point) o;
		if (this.x == p.x && this.y == p.y) {
			return true;
		} else {
			return false;
		}
	}

	public void translate(int dx, int dy) {
		this.x = this.x + dx;
		this.y = this.y + dy;
	}

	public static void main(String[] args) {
		Point p1 = new Point(1, 2);
		Point p2 = p1;
		Point p3 = new Point(1, 2);

		ArrayList<Point> list = new ArrayList<>();
		list.add(p1);
		// System.out.println(list.indexOf(p2));
		// System.out.println(list.indexOf(p3));

		PolyLine pol = new PolyLine();
		for (int i = 0; i < 10; i++) {
			pol.add(p1);
			;
		}
		for (int i = 0; i < 15; i++) {
			// System.out.println(pol.get_point(i));
		}
		// pol.add(null);
		System.out.println(pol.contains(new Point(1, 2)));
		System.out.println(pol.contains(new Point(2, 2)));
		// System.out.println(pol.contains(null));

		Point p4 = new Point(0, 0);
		Circle c = new Circle(p4, 2);
//		Circle c2=new Circle(p,2);
//		c2.translate(1,1);
//		System.out.println(c+" "+c2);

		Point p5 = new Point(-3, 0);
		System.out.println(c.contains(p5));
	}
}

class PolyLine {
	// static private int maximum_number_of_points = 10;
	// private Point[] liste_de_point = new Point[maximum_number_of_points];
	LinkedList<Point> liste_de_point = new LinkedList<Point>();
	static private int number_of_points;

	public PolyLine() {
		number_of_points = getNumber_of_points() + 1; 
	}

//	public PolyLine(int maximum_number_of_points) {
//		this.liste_de_point = new Point[maximum_number_of_points];
//		number_of_points = getNumber_of_points() + 1;
//	}

	public Point get_point(int i) {
		// return liste_de_point[i];
		return liste_de_point.get(i);
	}

	public void add(Point p) {
		Objects.requireNonNull(p, "The point must not be null"); 
		// liste_de_point[i] = p;
		liste_de_point.add(p);
	}

	public boolean contains(Point p) {
		for (Point ptest : liste_de_point) {
			if (ptest.getterX() == p.getterX() && ptest.getterY() == p.getterY()) {
				return true;
			}
		}
		return false;
	}

//	public static int getMax_capacity() {
//		return maximum_number_of_points;
//	}

	public static int getNumber_of_points() {
		return number_of_points;
	}
}

class Circle {
	Point center = new Point(0, 0);
	int radius;

	public Circle(Point center, int radius) {
		this.center = new Point(center);
		this.radius = radius;
	}

	public Point getterCenter() {
		return this.center;
	}

	@Override
	public String toString() {
		return "(" + this.getterCenter().getterX() + ", " + this.getterCenter().getterY() + "), Area = " + this.area();
	}

	public void translate(int dx, int dy) {
		this.center.translate(dx, dy);
	}

	public double area() {
		return this.radius * this.radius * Math.PI;
	}

	public boolean contains(Point p) {
		double distance_au_centre;
		int x_centre = center.getterX();
		int y_centre = center.getterX();
		int x_point = p.getterX();
		int y_point = p.getterY();
		distance_au_centre = Math.sqrt(Math.pow(x_centre - x_point, 2) + Math.pow(y_centre - y_point, 2));
		if (distance_au_centre <= this.radius) {
			return true;
		}
		return false;
	}
}

class Ring extends Circle {

	int rayon_externe;

	public Ring(Point center, int rayon_interne, int rayon_externe) {
		super(center, rayon_interne);
		this.rayon_externe = rayon_externe;
	}
	
	@Override
	public boolean equals(Object o) {
		Ring r = (Ring) o;
		if (this.center == r.center && this.radius == r.radius && this.rayon_externe == r.rayon_externe) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "centre : " + this.center + ", rayon externe : " + this.rayon_externe + ", rayon interne : "
				+ this.radius;
	}
	
	@Override
	public boolean contains(Point p) {
		double distance_au_centre;
		int x_centre = this.getterCenter().getterX();
		int y_centre = this.getterCenter().getterX();
		int x_point = p.getterX();
		int y_point = p.getterY();
		distance_au_centre = Math.sqrt(Math.pow(x_centre - x_point, 2) + Math.pow(y_centre - y_point, 2));
		
		if(super.contains(p) && distance_au_centre < rayon_externe) {
			return true;
		}
		return false;
	}
}
