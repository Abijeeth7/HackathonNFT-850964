import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.math.*;
import org.json.JSONException;
import org.json.JSONObject;
public class Battery
{ 
	public static void main(String[] args)  throws IOException
	{
		JSONObject obj=new JSONObject();
		String Line;
		String foreground;
		String fgval = "";
		String drain;
		double drained=0;
		int i=0;
		int count=0;
		int n=1000;
		double[] valueArray=new double[n];
		FileInputStream fis = new FileInputStream("battery.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

			while((Line=br.readLine())!=null)
			{
					count++;
					StringTokenizer str=new StringTokenizer(Line,":");
					ArrayList<String> token=new ArrayList<>();	
					while(str.hasMoreTokens())
					{
						token.add(str.nextToken());
					}
					for(String x:token)
					{
						if(x.contains("Foreground activities"))
						{
							foreground=Line;
							StringTokenizer strt=new StringTokenizer(foreground,":");
							ArrayList<String> fgtoken=new ArrayList<>();	
							while(strt.hasMoreTokens())
							{
								fgtoken.add(strt.nextToken());
							}
							fgval=token.get(1);
						}
					}
					for(String y:token)
					{
						if(y.contains("Uid u0a202"))
						{
							drain=Line;
							StringTokenizer strt=new StringTokenizer(drain,":(");
							ArrayList<String> draintoken=new ArrayList<>();	
							while(strt.hasMoreTokens())
							{
								draintoken.add(strt.nextToken());
							}
							drained=Double.parseDouble(draintoken.get(1));
						}
					}	

			}
		
			br.close();
			FileWriter fwr = new FileWriter("battery.json");
			PrintWriter prwt = new PrintWriter(fwr);
			try
			{
				obj.put("", fgval);
				obj.put("", drained);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			prwt.format("\nforeground_time:%s",fgval);
			prwt.format("\nBattery drain:%s",drained);
			double batt_perc=drained/1000;
			DecimalFormat d=new DecimalFormat("#.####");
			prwt.format("\nBatterypercentage:%s", d.format(batt_perc));
			
			prwt.close();
			
			
		}
	
	}