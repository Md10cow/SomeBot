package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * класс для чтения файлов
 */
public class FileReaderClass {
    private File file;
    private FileReader fr = null;
    private BufferedReader reader = null;
    /**
     * открывает файл на чтение
     *
     * @param pathname имя файла
     */
    public void openFile(String pathname){
        file = new File(pathname);
        fr = null;
        reader = null;
    }
    /**
     * читает одну строку
     */
    public String readLine(){
        String line="";
        try{
            if(fr==null)
                fr=new FileReader(file, StandardCharsets.UTF_8);
            if(reader==null)
                reader=new BufferedReader(fr);
            line = reader.readLine();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return line;
    }
    public void close(){
        try{
            reader.close();
            fr.close();
        }catch(IOException err){
            err.printStackTrace();
        }
    }
}
