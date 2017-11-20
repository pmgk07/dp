import java.io.*;

public class Exporter
{
	public static void export(String filepath, Object obj)
	{
		FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        String dir_path=filepath+".chser";

		try{
			if(obj instanceof Exportable)
			{
				ChannelIterator it=((ChannelCollection)obj).iterator(ChannelTypeEnum.ALL);
				int i=0;
				while (it.hasNext())
				{
					Object o=it.next();
					File file = new File(dir_path+"/"+i++);
					file.getParentFile().mkdirs();
					fos = new FileOutputStream(file);

		            oos = new ObjectOutputStream(fos);
		            oos.writeObject(o);
		            oos.flush();
		            oos.close();
		        }
	 
	            System.out.println("\nSerialization: All your channels saved to "+filepath+".chser\n");
			}
			else
				throw new UnexportableException("Unexportable Object attempted to be serialized at "+filepath);
		}
		catch (UnexportableException ueex) {
			ueex.printStackTrace();
		}
		catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}