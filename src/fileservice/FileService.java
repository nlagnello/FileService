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
public class FileService {

    private FileReaderStrategy readerStrategy;
    private FileWriterStrategy writerStrategy;
    private FileFormatStrategy readerFormat;
    private FileFormatStrategy writerFormat;
    private String readFilePath;
    private String writeFilePath;
    
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer){
        readerStrategy = reader;
        readerFormat = readerStrategy.getFileFormat();
        readFilePath = readerStrategy.getFilePath();
        writerStrategy = writer;
        writerFormat = writerStrategy.getFileFormat();
        writeFilePath = writerStrategy.getFilePath();
    }
    
    
    public List<LinkedHashMap<String,String>> getRecords() throws IOException{
        return readerStrategy.getRecords();
    }
    
    public void writeRecords(List<LinkedHashMap<String,String>> writeData, boolean useHeader) throws IOException{
        writerStrategy.writeRecords(writeData, useHeader);
    }
    
    
    
}
