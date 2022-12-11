package org.example;

import java.io.*;
/**
 * класс для чтения файлов
 */
public class FileSysObj {
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
                fr=new FileReader(file);
            if(reader==null)
                reader=new BufferedReader(fr);
            line = reader.readLine();
        } catch (FileNotFoundException err){
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return line;
    }
}
