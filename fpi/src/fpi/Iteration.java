package fpi;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jfree.data.xy.XYSeriesCollection;

public class Iteration {
	
	private ArrayList<Double> x=new ArrayList<Double>();
	private ArrayList<Double> y=new ArrayList<Double>();
	private Function function;
	private double x0, eps, x_min, x_max, y_min, y_max;
	
	
	public Iteration(String fx, double x0, double eps, double x_min, double x_max, double y_min, double y_max) {
		this.x0=x0;
		this.eps=eps;
		this.x_min=x_min;
		this.x_max=x_max;
		this.y_min=y_min;
		this.y_max=y_max;
		this.function=new Function(fx);
	}
	
	public boolean condition(double xi, double yi) {
		return Math.abs(xi-yi)>eps && xi>x_min && xi<x_max && yi>y_min && yi<y_max;
	}
	
	public void calculate() {
		x.add(x0); // points of first vertical movement 
		y.add((double) 0);
		x.add(x0);
		y.add(function.value(x0));
		int i=1;
		
		while(condition(x.get(i), y.get(i))) {
			x.add(y.get(i)); // points of vertical movement
			y.add(y.get(i));
			x.add(y.get(i));
			y.add(function.value(y.get(i)));
			i+=2;	
		}
		
		if(Math.abs(y.get(i)-x.get(i))<=eps) {
			JOptionPane.showMessageDialog(null, "Pevný bod byl nalezen v bodì "+x.get(i)+".");
		}else {
			fix_to_fit(x.get(i), y.get(i));
			JOptionPane.showMessageDialog(null, "Pevný bod nebyl nalezen.");
		}
	}
	
	public void fix_to_fit(double xi, double yi) {
		if(xi<x_min) {
			x.add(x_min);
			y.add(yi);
		}
		if(xi>x_max) {
			x.add(x_max);
			y.add(yi);
		}
		if(yi<y_min) {
			x.add(xi);
			y.add(y_min);
		}
		if(yi>y_max) {
			x.add(xi);
			y.add(y_max);
		}
	}
	
	public ArrayList<Double> get_x(){
		return x;
	}
	
	public ArrayList<Double> get_y(){
		return y;
	}
	 
}
