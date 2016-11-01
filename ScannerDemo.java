import java.util.*;
import scanner.CommaScanner;
public class ScannerDemo{
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of elements: " );

		int n = in.nextInt();
		System.out.println("Enter comma seperated elements: " );
		in.nextLine();
		String buff = in.nextLine();
		CommaScanner cm = new CommaScanner(buff);
		Double avg = 0.0;
		for(int i = 0; i < n; i++){
			avg = avg + cm.nextFloat()/n;
		}
		System.out.println("Average = "+avg);
	}
}