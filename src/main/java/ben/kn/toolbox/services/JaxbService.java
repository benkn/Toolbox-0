package ben.kn.toolbox.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * This service will save an object to an XML file.
 * 
 * @author BKnear
 */
public class JaxbService {
	
	/**
	 * Save the given object to the given XML file.
	 * @param <T> defines the type of the object
	 * @param object T to serialize
	 * @param filename String for the filename to save to
	 * @throws JAXBException
	 */
	public <T> void marshal(T object, String filename) throws JAXBException {
		FileOutputStream output = null;
	
		try {
			output = new FileOutputStream(filename);
			marshal(object, output);
		} catch (IOException ioe) {
			throw new JAXBException(ioe.getMessage());
		}
		finally {
			try { output.close(); }
			catch (Exception e ) {}
		}
	}
	
	/**
	 * Get the object serialized in the file with the given filename
	 * @param <T> Object type
	 * @param docClass Class for the returning object (same as T)
	 * @param filename String for the filename of the file
	 * @return T found in the file
	 * @throws JAXBException
	 */
	public <T> T unmarshal( Class<T> docClass, String filename) throws JAXBException {
		FileInputStream inputStream = null;
		T results;
		
		try {
			inputStream = new FileInputStream(filename);
			results = unmarshal(docClass, inputStream);
		}
		catch (FileNotFoundException fnfe ) {
			throw new JAXBException(fnfe.getMessage());
		}
		finally {
			try { inputStream.close(); }
			catch (Exception e) {}
		}
		
		return results;
	}
	
	/**
	 * Save the given object to the given outputstream
	 * @param <T> Object Type
	 * @param object Object to serialize
	 * @param outputStream OutputStream, which may be a FileOutputStream
	 * @throws JAXBException
	 */
	public <T> void marshal(T object, OutputStream outputStream) throws JAXBException {
	    JAXBContext jc = JAXBContext.newInstance( object.getClass() );
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(object, outputStream); 
	}

	/**
	 * Get the serialized object through the given InputStream
	 * @param <T> Object type
	 * @param docClass Class for the returning object (same as T)
	 * @param inputStream InputStream, which may be a FileInputStream
	 * @return T found in the file
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshal( Class<T> docClass, InputStream inputStream ) throws JAXBException {
	    String packageName = docClass.getPackage().getName();
	    JAXBContext jc = JAXBContext.newInstance( packageName );
	    Unmarshaller u = jc.createUnmarshaller();
	    T doc = (T) u.unmarshal( inputStream );
	    return doc;
	}

}