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
public class TextFileWriter implements FileWriterStrategy{
    private FileFormatStrategy fileFormat;
    private String filePath;
    
    
    @Override
    public void writeRecords(List<LinkedHashMap<String, String>> data, boolean useHeader) throws IOException {

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
    public void setFormatStrategy(FileFormatStrategy format) {
        fileFormat = format;
    }
    
}
