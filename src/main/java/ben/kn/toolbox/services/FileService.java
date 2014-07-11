package ben.kn.toolbox.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ben.kn.toolbox.util.FileUtil;

/**
 * A collection of client-usable behavior focused on Files.
 * 
 * FOR FUNCTIONS TO DO STUFF WITH FILES, CHECK OUT THE {@link FileUtil}
 * 
 * @author Ben (bknear@gmail.com)
 * @since Dec 14, 2012
 */
public class FileService {

    /**
     * Create a CSV file based on all the names from the given Files
     * 
     * @param baseDir String of the base directory scanned
     * @param files List of {@link File}s
     * @param outputFile String of the filename to write to
     * @throws IOException
     */
    public void createCsvOfFiles(String baseDir, List<File> files, String outputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        String output;
        for ( File f : files ) {
            output = createCsvLine(f).replaceAll(baseDir, "");
            System.out.println(output);
            sb.append(output + "\n");
        }

        FileUtil.writeStringToFile(sb.toString(), outputFile);
    }

    private String createCsvLine(File f) throws IOException {
        return "\"" + f.getName() + "\", \"" + f.getParent() + "\", \"" + FileUtil.getExtension(f) + "\"";
    }

    public String createStringFromFileContents(String fileDir) throws IOException {
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(fileDir));
        StringBuilder sb = new StringBuilder();
        String in;
        while ( (in = br.readLine()) != null ) {
            sb.append('\"').append(in.replaceAll("\"", "\\\"").trim()).append("\" + \n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();

        if ( args != null && args.length > 0 ) {
            if ( args[0].equals("-csv") ) {
                if ( args.length != 3 ) {
                    System.out
                            .println("Usage: To create the csv file of files in a directory:\n -csv <files_directory> <output_filename>");
                    return;
                }

                String dir = args[1];
                String outputFile = args[2];

                System.out.println("Getting the files for " + dir);
                List<File> filesInDir = FileUtil.getFilesInDir(dir, true);

                System.out.println("Found " + filesInDir.size() + " files.");
                fileService.createCsvOfFiles(dir, filesInDir, outputFile);

                System.out.println("Finished.");
            }
        }
    }
}
