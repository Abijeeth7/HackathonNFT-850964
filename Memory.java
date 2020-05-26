import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Memory {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		double a=0,m=0,s=0;
		int c=0,i=0;
		double n;
		String l;
		FileInputStream fis = new FileInputStream("Memory.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		while((l=br.readLine()) != null)
		{
			if((i%2) != 0)
			{
				c++;
				StringTokenizer st = new StringTokenizer(l, ": ");
				ArrayList<String> array = new ArrayList<>();
				while(st.hasMoreTokens())
				{
					array.add(st.nextToken());
				}
				n = Double.parseDouble(array.get(1));
				if(n>m)
				{
					m = n;
				}
				n = n/1024;
				System.out.println(n);
				s = s+n;
			}
			i++;
		}
		m = m/1024;
		a = s/c;
		System.out.println(a);
		System.out.println(m);
		sc.close();

	}

}
