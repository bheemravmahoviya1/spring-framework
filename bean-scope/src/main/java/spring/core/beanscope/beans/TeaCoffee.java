package spring.core.beanscope.beans;


/**
 * @author Bheemrav Mahoviya
 * This class bean will be created as Request scope.
 * */
public class TeaCoffee {
	public String tea = "Blank tea";

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}
	
}
