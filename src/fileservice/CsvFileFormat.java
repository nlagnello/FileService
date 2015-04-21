/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * I had a lot of trouble understanding and inevitably implementing this 
 * class on my own. This is the only reason why  I had copied this class
 * into my program.
 * 
 * 
 * 
 * @author nagnello
 */
public class CsvFileFormat implements FileFormatStrategy {
    
    /**
     * Transforms a CSV-formatted String representation of the entire contents
     * of a file, which must included a header row, into a generic List of
     * records where each record is a LinkedHashMap. This Map implementation
     * is used to maintain insertion order of key/value pairs in the Map.
     * 
     * @return a generic List of records where each record is a LinkedHashMap. 
     * This Map implementation is used to maintain insertion order of key/value 
     * pairs in the Map.
     */
    @Override
    public List<LinkedHashMap<String,String>> decodeAll(String data) {
        List<LinkedHashMap<String,String>> records = 
                new ArrayList<LinkedHashMap<String,String>>();
        
        String[] lines = data.split("\\n");
        String[] header = lines[0].split(",");
        
        for(int i=1; i < lines.length; i++) {
            LinkedHashMap<String,String> record = new LinkedHashMap<String,String>();
            String[] rowData = lines[i].split(",");
            for(int j=0;j < rowData.length; j++) {
                record.put(header[j], rowData[j]);
            }
            records.add(record);
        }
        
        return records;
    }

    @Override
    public String encodeAll(List<LinkedHashMap<String, String>> updatedFileContent, boolean useHeader) {
        StringBuilder encodedData = new StringBuilder();
        
        // Builder Header row
        LinkedHashMap<String, String> headerRec = updatedFileContent.get(0);
        Set<String> fields = headerRec.keySet();

        for(Iterator i = fields.iterator(); i.hasNext(); ) {
            encodedData.append("\"").append(i.next()).append("\"").append(",");
        }

        int lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, "\n");
        
        if(!useHeader) {
            encodedData = new StringBuilder(); // clear header info
        }
        
        // Now add data rows
        for(LinkedHashMap<String, String> dataRow : updatedFileContent) {
             for(Iterator i = fields.iterator(); i.hasNext(); ) {
                 encodedData.append("\"").append(dataRow.get(i.next().toString()))
                         .append("\"").append(",");
             }
             lastChar = encodedData.length()-1;
             encodedData.replace(lastChar, lastChar+1, "\n");             
        }
        
        lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, "\n");
        
        return encodedData.toString();        
    }

    @Override
    public String encodeRecord(LinkedHashMap<String, String> newData) {
        StringBuilder encodedData = new StringBuilder();
        
        // Add data row (header already exists)
        Set<String> fields = newData.keySet();
        for(Iterator i = fields.iterator(); i.hasNext(); ) {
            encodedData.append("\"").append(newData.get(i.next()))
                    .append("\"").append(",");
        }
        int lastChar = encodedData.length()-1;
        encodedData.replace(lastChar, lastChar+1, "\n");             

        
        return encodedData.toString();        
    }
    
    /**
     * Test harness only - remove from production version
     * @param args - not used
     */
    public static void main(String[] args) {
        FileFormatStrategy fmt = new CsvFileFormat();
        String s = "\"firstName\",\"lastName\",\"age\"" + "\n"
            + "\"John\",\"Smith\",22" + "\n"
            + "\"Doe\",\"Bob\",44" + "\n";
        List<LinkedHashMap<String,String>> formattedData = fmt.decodeAll(s);
        System.out.println("All done");
    }
}
