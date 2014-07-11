package ben.kn.toolbox.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtil {
    /**
     * Read the file specified by the given filename
     * 
     * @param filename String
     * @return String
     * @throws IOException
     */
    public String readFile(String filename) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String input;
            StringBuilder sb = new StringBuilder();

            while ( (input = br.readLine()) != null ) {
                sb.append(input);
            }

            return sb.toString();
        } finally {
            try {
                br.close();
            } catch (Exception e) { /* gobble it */
            }
        }
    }

    /**
     * Get a {@link List} of all file names as Strings in the given directory.
     * 
     * @param dirName String
     * @return List of Strings, or an empty list if none are found.
     */
    public static List<String> getFileNamesInDir(String dirName) {
        List<String> files = new ArrayList<String>();
        File dir = new File(dirName);

        if ( dir.isDirectory() ) {
            // go through all files in the directory
            for ( String file : dir.list() ) {
                files.add(file);
            }
        }
        return files;
    }

    /**
     * Get a {@link List} of all {@link File}s found in the given directory. Use
     * the boolean <code>recursive</code> to go through child directories and
     * collect their files as well.
     * 
     * @param dirName String for name of the parent directory
     * @param recursive boolean
     * @return List of Files, or an empty list if none are found.
     * @throws IOException
     */
    public static List<File> getFilesInDir(String dirName, boolean recursive) throws IOException {
        List<File> files = new ArrayList<File>();
        File dir = new File(dirName);

        if ( dir.isDirectory() ) {
            // go through all files in the directory
            for ( File file : dir.listFiles() ) {
                if ( file.isDirectory() && recursive ) {
                    files.addAll(getFilesInDir(file.getAbsolutePath(), recursive));
                } else if ( !file.isDirectory() ) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * Write the given String output to the file given as outputFile.
     * 
     * @param output String
     * @param outputFile String
     */
    public static void writeStringToFile(String output, String outputFile) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(outputFile));
            writer.append(output);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Copy the given {@link Collection} of {@link File}s to the destination
     * directory.
     * 
     * @param files Collection of Files
     * @param destDir String
     * @throws IOException
     */
    public static void copyFilesToDestination(Collection<File> files, String destDir) throws IOException {
        for ( File f : files ) {
            File target = new File(destDir, f.getName());
            FileUtils.copyFile(f, target);
        }
    }

    /**
     * Create a directory with the given path, or do not create one if it
     * already exists.
     * 
     * @param path String
     */
    public static void createDirectory(String path) {
        File thumbnailDir = new File(path);
        thumbnailDir.mkdir();
    }

    /**
     * Get the name of the file without the file extension from the given
     * {@link File}
     * 
     * @param file File
     * @return String
     */
    public static String getNameWithoutExtension(File file) {
        return getNameWithoutExtension(file.getName());
    }

    /**
     * Get the name of the file without the file extension from the given
     * filename
     * 
     * @param filename String
     * @return String
     */
    public static String getNameWithoutExtension(String filename) {
        int cutIndex = filename.lastIndexOf(".");
        filename = filename.substring(0, cutIndex);
        return filename;
    }

    /**
     * Get the file extension of the given {@link File}
     * 
     * @param f File
     * @return String
     */
    public static String getExtension(File f) {
        return getExtension(f.getName());
    }

    /**
     * Get the file extension from the given filename
     * 
     * @param filename String
     * @return String
     */
    public static String getExtension(String filename) {
        int cutIndex = filename.lastIndexOf(".");
        String extension = filename.substring(cutIndex);
        return extension;
    }

    public static long getDirSize(File dir) {
        long size = 0;
        if ( dir.isFile() ) {
            size = dir.length();
        } else {
            File[] subFiles = dir.listFiles();

            if ( subFiles != null ) {
                for ( File file : subFiles ) {
                    if ( file.isFile() ) {
                        size += file.length();
                    } else { // is directory
                        size += getDirSize(file);
                    }
                }
            }
        }

        return size;
    }

}
