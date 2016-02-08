package com.oshippa.server.helper.FileMaker;

import com.oshippa.server.helper.FileReaderHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by steve on 1/15/16.
 */
public abstract  class FileMaker<T> {
    protected Configuration cfg;
    protected String templatePath;
    @Autowired
    protected FileReaderHelper fileReaderHelper;
    private static Logger log = LoggerFactory.getLogger(FileMaker.class);

    @Autowired
    public FileMaker(FreeMakerConfigurationProvider provider) {
        this.cfg = provider.getCfg();
        this.templatePath = provider.getTemplatePath();
    }

    public abstract  File createTemplateFile(T entity) throws IOException, TemplateException;

    protected File createFile(Map root,File file,Template temp)  {
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            temp.process(root, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            log.info(file.getName()+" created");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return file;
    }
}
