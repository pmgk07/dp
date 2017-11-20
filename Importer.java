import java.io.*;

public class Importer {

	private static Object importObject(String filepath)
	{
		FileInputStream fis = null;
        ObjectInputStream ois = null;

        Object obj=null;

        try{
			fis = new FileInputStream(filepath);
 
            ois = new ObjectInputStream(fis);
 
            obj = ois.readObject();

            ois.close();
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
        finally{
        	return obj;
        }
	}

    public static ChannelIteratorImpl lazyImport(String fp){
        File[] files = new File(fp).listFiles();

        return new ChannelIteratorImpl(files);

    }

    private static class ChannelIteratorImpl implements ChannelIterator {

        private File[] files;
        private int position;

        public ChannelIteratorImpl(File[] files) {
            this.files=files;
            position=0;
        }

        @Override
        public boolean hasNext() {
            return position<files.length;
        }

        @Override
        public Channel next() {
            Channel c = (Channel) importObject(files[position].toPath().toString());
            position++;
            return c;
        }

    }
}