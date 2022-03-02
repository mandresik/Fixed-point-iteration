package fpi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


@SuppressWarnings({ "serial", "unused" })
public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_fx;
	private JTextField txt_x0;
	private JTextField txt_prec;
	private JTextField txt_xmax;
	private JTextField txt_xmin;
	private JTextField txt_ymin;
	private JTextField txt_ymax;
	private String fx;
	private double x0, eps, x_min, x_max, y_min, y_max;
	private Iteration iteration;
	private Graph graph;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean is_double(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public void set_inputs() {
		fx=txt_fx.getText();
		if(!is_double(txt_x0.getText()) || !is_double(txt_prec.getText()) || !is_double(txt_xmin.getText()) ||
				!is_double(txt_xmax.getText()) || !is_double(txt_ymin.getText()) || !is_double(txt_ymax.getText())) {
			JOptionPane.showMessageDialog(null, "Except for f(x), all variables must be type double.");
			return;
		}else {
			x0=Double.parseDouble(txt_x0.getText());
			eps=Double.parseDouble(txt_prec.getText());
			x_min=Double.parseDouble(txt_xmin.getText());
			x_max=Double.parseDouble(txt_xmax.getText());
			y_min=Double.parseDouble(txt_ymin.getText());
			y_max=Double.parseDouble(txt_ymax.getText());
		}
	}

	public Frame() {
		setTitle("Fixed point iteration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_help = new JButton("?");
		btn_help.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_help.setBounds(232, 23, 45, 38);
		contentPane.add(btn_help);
		
		JLabel lbl_fx = new JLabel("f(x)=");
		lbl_fx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_fx.setBounds(10, 30, 51, 24);
		contentPane.add(lbl_fx);
		
		txt_fx = new JTextField();
		txt_fx.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_fx.setBounds(47, 28, 152, 29);
		contentPane.add(txt_fx);
		txt_fx.setColumns(10);
		
		JLabel lbl_0 = new JLabel("starting point x0=");
		lbl_0.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_0.setBounds(10, 86, 114, 24);
		contentPane.add(lbl_0);
		
		txt_x0 = new JTextField();
		txt_x0.setHorizontalAlignment(SwingConstants.CENTER);
		txt_x0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_x0.setColumns(10);
		txt_x0.setBounds(134, 84, 74, 29);
		contentPane.add(txt_x0);
		
		JLabel lbl_e = new JLabel("precision e=");
		lbl_e.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_e.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_e.setBounds(20, 120, 102, 24);
		contentPane.add(lbl_e);
		
		txt_prec = new JTextField();
		txt_prec.setHorizontalAlignment(SwingConstants.CENTER);
		txt_prec.setText("1e-3");
		txt_prec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_prec.setColumns(10);
		txt_prec.setBounds(134, 118, 74, 29);
		contentPane.add(txt_prec);
		
		JLabel lblNewLabel_1 = new JLabel("x_min=");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 177, 51, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("x_max=");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(154, 177, 54, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("y_min=");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 211, 61, 24);
		contentPane.add(lblNewLabel_1_2);
		
		txt_xmax = new JTextField();
		txt_xmax.setColumns(10);
		txt_xmax.setBounds(211, 182, 51, 19);
		contentPane.add(txt_xmax);
		
		txt_xmin = new JTextField();
		txt_xmin.setColumns(10);
		txt_xmin.setBounds(73, 182, 51, 19);
		contentPane.add(txt_xmin);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("y_max=");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(141, 211, 67, 24);
		contentPane.add(lblNewLabel_1_2_1);
		
		txt_ymin = new JTextField();
		txt_ymin.setColumns(10);
		txt_ymin.setBounds(73, 216, 51, 19);
		contentPane.add(txt_ymin);
		
		txt_ymax = new JTextField();
		txt_ymax.setColumns(10);
		txt_ymax.setBounds(211, 216, 51, 19);
		contentPane.add(txt_ymax);
		
		JButton btn_draw = new JButton("DRAW");
		btn_draw.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_draw.setBounds(10, 260, 267, 50);
		contentPane.add(btn_draw);
		
		
		
		btn_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "For help, please visit: https://github.com/mandresik/Fixed-point-iteration.");
			}
		});
		
		
		btn_draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_inputs();
				iteration=new Iteration(fx,x0,eps,x_min,x_max,y_min,y_max);
				iteration.calculate();
				graph=new Graph(iteration.get_x(),iteration.get_y(),fx,x_min,x_max,y_min,y_max);
				graph.create_fvalues();
				graph.draw_graph();
				graph.setVisible(true);
				graph.pack();
			}
		});
	}
}
