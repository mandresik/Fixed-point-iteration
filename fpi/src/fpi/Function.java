package fpi;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {

	private String function; 
	
	public Function(String function) {
		this.function=function;
	}
	
	public double value(double point) {
		Expression e= new ExpressionBuilder(function)
				.variable("x")
				.build()
				.setVariable("x", point);
		return e.evaluate();
	}
	
}
