/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileservice;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nagnello
 */
public class TextFileReader implements FileReaderStrategy {
    
    private String filePath;
    private FileFormatStrategy fileFormat;
    
    
    @Override
    public List<LinkedHashMap<String, String>> getRecords() throws IOException {
        
        String data = "";
       
        File file = new File(filePath);
       
        BufferedReader in = null;

	in = new BufferedReader(new FileReader(file));
	String line = in.readLine();
        
	while(line != null){
            data += (line + "\n");
            line = in.readLine();
        }
        in.close();
        
        
        return fileFormat.decodeAll(data);
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void setFilePath(String path) {
        filePath = path;
    }

    @Override
    public FileFormatStrategy getFileFormat() {
        return fileFormat;
    }

    @Override
    public void setFileFormat(FileFormatStrategy format) {
        fileFormat = format;
    }
    
}
