package scanner;

public class CommaScanner {
	String[] elements;
	int pos,max;
	public CommaScanner(String buffer){
		this.elements = buffer.split(",");
		this.pos = 0;
		this.max = this.elements.length;
	}
	public String nextString() throws Exception{
		Exception e = new Exception("end of buffer");
		if(pos == max)
				throw e;
		else
			{
				String temp =(elements[pos]);
				pos++;
				return temp;
			}
	}
	public int nextInt() throws Exception{
		Exception e = new Exception("end of buffer");
		if(pos == max)
				throw e;
		else
			{
				int temp = Integer.parseInt(elements[pos].trim());
				pos++;
				return temp;
			}
	}
	public Double nextFloat() throws Exception{
		Exception e = new Exception("end of buffer");
		if(pos == max)
				throw e;
		else
			{
				Double temp = Double.parseDouble(elements[pos].trim());
				pos++;
				return temp;
			}
	}
}