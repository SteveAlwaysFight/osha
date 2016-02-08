package com.oshippa.server.helper.FileMaker;

import com.oshippa.server.helper.FileReaderHelper;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by steve on 1/14/16.
 */
@Component
public class FreeMakerConfigurationProvider {

    private Configuration cfg;

    private String templatePath;

    private String groovyPath;

    private String reportTemplatePath;

    private String mapFilePath;

    @Autowired
    private FileReaderHelper fileReaderHelper;

    private static Log logger = LogFactory.getLog(FreeMakerConfigurationProvider.class);

    @Value("${free_maker.generated.groovy}")
    private String defaultGroovyPath;

    @Value("${free_maker.template_path}")
    private String defaultTemplatePath;

    @Value("${free_maker.report_template_path}")
    private String defaultReportTemplatePath;
    @Value("${free_maker.map_file_path}")
    private String defaultMapFilePath;

//    public FreeMakerConfigurationProvider() {
//        URL templatePathFile_groovy = Thread.currentThread().getContextClassLoader().getResource(defaultGroovyPath);
//        if (templatePathFile_groovy == null) {
//            logger.error("can't locate groovy file folder, set to default");
//            File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile() + defaultGroovyPath);
//            file.mkdirs();
//            groovyPath = file.getAbsolutePath();
//        } else {
//            groovyPath = templatePathFile_groovy.getFile();
//        }
//
//
//    }

    public Configuration getCfg() {
        if (cfg == null) {
            cfg = new Configuration();
            try {
                File file = new File(fileReaderHelper.getBaseFolder()+"/"+getTemplatePath());
                cfg.setDirectoryForTemplateLoading(file);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            } catch (IOException e) {
//                e.printStackTrace();
                logger.error("loading template path failed");
            }
        }
        return cfg;
    }

    public String getTemplatePath() {
        if (templatePath == null) {
            logger.error("can't locate template file folder, set to default");
            File file = new File(fileReaderHelper.getBaseFolder() + "/" + defaultTemplatePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            templatePath = defaultTemplatePath;
        }

        return templatePath;
    }

    public String getGroovyPath() {
        if (groovyPath == null) {

            logger.error("can't locate groovy file folder, set to default");
            File file = new File(fileReaderHelper.getBaseFolder() + "/" + defaultGroovyPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            groovyPath = defaultGroovyPath;
        }
        return groovyPath;
    }

    public String getReportTemplatePath() {
        if (reportTemplatePath == null) {
            logger.error("can't locate report template file folder, set to default");
            File file = new File(fileReaderHelper.getBaseFolder() + "/" + defaultReportTemplatePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.reportTemplatePath = defaultReportTemplatePath;
        }

        return reportTemplatePath;
    }

    public String getMapFilePath() {
        if (mapFilePath == null) {
            logger.error("can't locate report mapping file folder, set to default");
            File file = new File(fileReaderHelper.getBaseFolder() + "/" + defaultMapFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.mapFilePath = defaultMapFilePath;
        }
        return mapFilePath;
    }
}
