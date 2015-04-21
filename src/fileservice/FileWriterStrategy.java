/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileservice;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author nagnello
 */
public interface FileWriterStrategy {
    
    
    void writeRecords(List<LinkedHashMap<String,String>> data, boolean useHeader) throws IOException;
    
    String getFilePath();
    
    void setFilePath(String path);
    
    FileFormatStrategy getFileFormat();
    
    void setFormatStrategy(FileFormatStrategy format);
}
