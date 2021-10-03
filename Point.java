package fr.dauphine.javaavance.td1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* Exercice 1 :
 * 
 * 2. Eclypse écrit directement System.out.println() lorsque l'on écrit sysout et Ctrl + Space.
 * 3. Eclypse propose de faire un Override de la méthode toString().
 * 4. Eclypse propose de faire apparaître la main méthode directement.
 * 5. Eclypse propose d'écrire certaines méthodes comme equals(), hashcode(), toString(). Aprèsavoir écrit le set, et d'avoir pressé Ctrl + Space,
 * 		Eclypse propose de créer un Setter pourl'entier foo.
 * 6. Le Alt+Shift + R permet de renommer soit le nom de la class, soit le nom de la variable foo. Le renommage peut être effectué sur toutes les apparitions du nom de la class/variable.
 * 
 * Exercice 2 :
 * 1. It works because the two private fields and the method newly created are a part of the same class Point.
 * 2. It doesn't work because now the fields cannot be accessed by the new class TestPoint due to the "private".
 * 3. Because, we must be sure that value changes will be done by using a "setter method" and then that the new value will respect the will of the precedent programmer.
 * 4. The accessor allows a class to "access" to the value of an other class private field. Basically, it's called the "getter". Here, it's needed by the class TestPoint to access the fields x and y.
 * 5. The problem is that px and py do not exist as local variables.
 * 6. Now it's working.
 * 7. We could create a static variable number_of_points and increment it each time a new Object Point is created (add a "number_of_points++ in the constructor).
 * 8. It knows which one to call depending which arguments the constructor takes when we create an Object Point.
 * 
 * Exercice 3:
 * 1. It prints "true/nfalse". The true shows that the Objects p1 and p2 are the same by pointing on the same address.
 * 	  Even if p1 and p3 have the same coodinates, they stay two diferent obkjects with two different addresses.
 * 
 * Exercice 4 :
 *
 * 2. If we try to add more points to the array than its capacity, then it throws the exception "ArrayIndexOutOfBoundsException"
 * Then we should resize it.
 * 5. We get a "NullPointerException".In the case, we add a null value to point in the add methode, the Objects.requireNull throws an exception with a personnalidsed message
 * 6. (Voir modifs)
 * 
 * Exercice 5 :
 * 1. Les deux options sont les suivantes : faire une méthode translate qu'on appelle via un Point créé,
 * ou faire une méthode static avec le Point à translater en paramètre.
 * 5. La translation est appliquée au point qui est le centre des deux cercles. Lorsqu'on appelle la méthode c2.translate, la translation s'applique aussi à c1.
 * On doit faire en sorte que le point du centre du cercle ne soit pas mutable.
 * 
 * Exercice 6 :
 * 1. Oui, on peut faire hériter ring de la classe Cercle en rajoutant simplement un rayon externe.
 * 4. Le Sysout renvoie le hashcode de l'adresse de l'objet. Il est nécessaire de réécrire la fonction toString().
 * */

public class Point {
	private int x, y;
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
