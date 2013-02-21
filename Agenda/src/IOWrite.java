import java.io.*;

public class IOWrite {
	
	public void write(String dataStream, String fileName)
	{
		try{
			FileWriter fstream = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(dataStream);
			//Close the output stream
			out.close();
			System.out.println("schrijf");
		}
		catch (Exception e)
		{//Catch exception if any
			System.err.println("Error, bestand bestaat niet, map gemaakt!");
		    e.printStackTrace();
		}
	}
	
	public static String read(String fileName) {
		 
		BufferedReader br = null;
		String file = "";
 
		try {
 
			String sCurrentLine;
			
 
			br = new BufferedReader(new FileReader(fileName));
			boolean firstLine = true;
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				if (firstLine)
				{
					if (!sCurrentLine.equals("AGENDAFILE"))
					{
						break;
					}
					firstLine = false;
				}
				file += sCurrentLine + "\n";
			}
			
			
 
		} catch (IOException e) {
			System.err.println("Oei!, bestand bestaat niet, afbreken");
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return file;
 
	}
	public static boolean fileExists(String file)
	{
		BufferedReader br = null;
		String file2 = "";
 
		try {
 
			String sCurrentLine;
			
 
			br = new BufferedReader(new FileReader(file));
 
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				file2 += sCurrentLine + "\n";
			}
			
			return true;
			
 
		} catch (IOException e) {
			return false;
		}
		
	}
}