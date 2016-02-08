/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.helper;

import com.oshippa.common.exception.ApplicationRuntimeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by steve on 1/22/16.
 */
@Component
public class FileReaderHelper {
    private String baseFolder;
    Log log = LogFactory.getLog(FileReaderHelper.class);

    public String getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public FileReaderHelper(){
        this.baseFolder = Thread.currentThread().getContextClassLoader().getResource("").getFile();
    }

    public String readFileAsString(String path){
        File file = new File(this.baseFolder+"/"+path);
        if(!file.exists()){
            log.error("reading file error, file is not exist. path="+file.getAbsolutePath());
            throw new ApplicationRuntimeException("reading file error, file is not exist");
        }
        if (file.isDirectory()){
            log.error("reading file error, path is a folder. path="+file.getAbsolutePath());
            throw new ApplicationRuntimeException("reading file error, path is a folder");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tmp=null;
            StringBuffer stringBuffer = new StringBuffer();
            while((tmp = bufferedReader.readLine())!=null){
                stringBuffer.append(tmp);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            throw new ApplicationRuntimeException("reading file error, file is not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createDirs(String path){
        String finalPath = this.baseFolder + path;
        File dir = new File(finalPath);
        dir.mkdirs();
        return dir.getAbsolutePath();
    }

}
