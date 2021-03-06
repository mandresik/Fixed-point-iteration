package fpi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class Graph extends JFrame {
	
	private ArrayList<Double> x,y;
	private ArrayList<Double> x_f=new ArrayList<Double>();
	private ArrayList<Double> y_f=new ArrayList<Double>();
	private double xi, yi, x_min, x_max, y_min, y_max;
	private Function function;
	
	public Graph(ArrayList<Double> x, ArrayList<Double> y, String fx, 
			double x_min, double x_max, double y_min, double y_max) {
		super("Fixed point graphical interaction");
		this.x=x;
		this.y=y;
		this.x_min=x_min;
		this.x_max=x_max;
		this.y_min=y_min;
		this.y_max=y_max;
		this.function=new Function(fx);
	}
	
	public void create_fvalues() {
		int n=(int)(x_max-x_min)*100+1; //number of calculation of function values
		for(int i=0;i<n;i++) {
			xi=x_min+0.01*i;
			yi=function.value(xi);
			if(yi>y_min && yi<y_max) {  // check if function values are within graph 
				x_f.add(xi);
				y_f.add(yi);
			}
		}
	}
	
	public void draw_graph() {
		JFreeChart chart= ChartFactory.createXYLineChart("Iteration", "x", "y", create_dataset(), PlotOrientation.VERTICAL, false, true, false);
		ChartPanel panel=new ChartPanel(chart);
		panel.setPreferredSize( new java.awt.Dimension( 780 , 510 ) );
		final XYPlot plot=chart.getXYPlot();
		plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);
        plot.setBackgroundPaint(Color.white);
        NumberAxis domain =(NumberAxis) plot.getDomainAxis();
        domain.setRange(x_min, x_max);
        NumberAxis range=(NumberAxis) plot.getRangeAxis();
        range.setRange(y_min, y_max);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0,Color.yellow);
        renderer.setSeriesPaint(1,Color.yellow);
        renderer.setSeriesPaint(2,Color.gray);
        renderer.setSeriesPaint(3,Color.darkGray);
        for(int i=0;i<x.size();i++)
        	renderer.setSeriesPaint(i+4, Color.blue);
        plot.setRenderer(renderer);
        setContentPane(panel);
	}

	public XYDataset create_dataset() {	
		final XYSeries x_axis=new XYSeries("osa x");
	    x_axis.add(x_min,0);
	    x_axis.add(x_max,0);
	      
	    final XYSeries y_axis=new XYSeries("osa y");
	    y_axis.add(0,y_min);
	    y_axis.add(0,y_max);
	    
	    final XYSeries yx=new XYSeries("y=x");
	    yx.add(x_min, y_min);
	    yx.add(x_max, y_max);
	    
	    final XYSeries f=new XYSeries("f(x)");
	    for(int i=0;i<x_f.size();i++) {
	    	f.add(x_f.get(i),y_f.get(i));
	    }
	    
	    List<XYSeries> series_list=new ArrayList<XYSeries>();
	    XYSeries temp;
	    for(int i=0;i<x.size()-1;i++) {  // add every line individually to avoid lines rearrangement
	    	temp=new XYSeries(Integer.toString(i));
	    	temp.add(x.get(i), y.get(i));
	    	temp.add(x.get(i+1), y.get(i+1));
	    	series_list.add(temp);
	    }
	    
	    final XYSeriesCollection dataset = new XYSeriesCollection( );   
	    dataset.addSeries(x_axis);
	    dataset.addSeries(y_axis);
	    dataset.addSeries(yx);
	    dataset.addSeries(f);
	    for(XYSeries line:series_list)
	    	dataset.addSeries(line);
	    
	    return dataset;
	}
	
}
