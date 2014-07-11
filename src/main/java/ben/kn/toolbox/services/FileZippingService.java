package ben.kn.toolbox.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZippingService {
	public void zipFilesInDirectory(String directoryPath, String zippedFileName) {
		// if the given path does not in a '/', then add it. This isn't
		// necessary until we append the file names to the path.
		directoryPath = cleanPath(directoryPath);

		// get the directory so we can access the files
		File dir = new File(directoryPath);

		// confirm it's a directory, and proceed
		if ( dir.isDirectory() ) {
			File[] filesWithinDirectory = dir.listFiles();
			
			FileOutputStream fos = null;
			ZipOutputStream zos = null;
			FileInputStream fis = null;
			try {
				fos = new FileOutputStream(directoryPath + zippedFileName);
				zos = new ZipOutputStream(fos);

				// this is our byte buffer, which is used in reading the files
				byte[] buffer = new byte[1028 * 32];
				int totalBufferFilled;

				// go through all files
				for ( File file : filesWithinDirectory ) {
					if ( !file.isDirectory() ) {
						fis = new FileInputStream(file);

						zos.putNextEntry(new ZipEntry(file.getName()));
						// read the file and write to the ZipOutputStream
						do {
							totalBufferFilled = fis.read(buffer);

							if ( totalBufferFilled == buffer.length ) {
								zos.write(buffer);
							} else {
								byte[] tempBuff = Arrays.copyOfRange(buffer, 0, totalBufferFilled);
								zos.write(tempBuff);
								break;
							}
						} while (totalBufferFilled > 0);

						zos.closeEntry();
					}
				}

				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Finally, of course, close our streams
				if ( fos != null ) {
					try {
						fos.close();
					} catch (IOException ignore) {
					}
				}
				if ( zos != null ) {
					try {
						zos.close();
					} catch (IOException ignore) {
					}
				}
			}
		}
	}

	/**
	 * Remove any whitespace on the path ends, and verify it ends with a forward
	 * slash ('/')
	 * 
	 * @param path String of a directory path
	 * @return formatted String
	 */
	private String cleanPath(String path) {
		path = path.trim();
		if ( !path.endsWith("/") ) {
			path = path + "/";
		}
		return path;
	}
}
