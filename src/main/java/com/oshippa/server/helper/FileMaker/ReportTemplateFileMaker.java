package com.oshippa.server.helper.FileMaker;

import com.oshippa.server.model.file.Report;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve on 1/22/16.
 */
@Service
public class ReportTemplateFileMaker extends FileMaker<Report> {
    private String reportTemplatePath;

    @Autowired
    public ReportTemplateFileMaker(FreeMakerConfigurationProvider provider) {
        super(provider);
        this.reportTemplatePath = provider.getReportTemplatePath();
    }

    @Override
    public File createTemplateFile(Report report) throws IOException, TemplateException {
        String content = report.getContent();
        Map<String,String> map = new HashMap<>();
        map.put("content",content);
        String folder = fileReaderHelper.createDirs(reportTemplatePath + "/" +  report.getHbmPath() + "/");
        File file = new File(folder+ "/" + report.getCurrentVersion() + ".ftl");
        Template temp = cfg.getTemplate("/report_temp.ftl");
        super.createFile(map,file,temp);
        return file;
    }


    public String getGeneratedReportTemplatePath(Report report){
        String path =reportTemplatePath+ "/"+ report.getHbmPath() + "/" + report.getCurrentVersion() + ".ftl";
        return fileReaderHelper.readFileAsString(path);
    }
}
