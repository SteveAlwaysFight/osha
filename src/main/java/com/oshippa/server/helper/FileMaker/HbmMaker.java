package com.oshippa.server.helper.FileMaker;

import com.oshippa.server.model.Entity;
import com.oshippa.server.model.TableElement;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve on 1/14/16.
 */
@Service
public class HbmMaker extends FileMaker<TableElement> {

    private String mapFilePath;
    @Autowired
    public HbmMaker(FreeMakerConfigurationProvider provider) {
        super(provider);
        this.mapFilePath = provider.getMapFilePath();
    }

    public File createTemplateFile(TableElement entity) throws IOException, TemplateException {
        Map root = new HashMap();
        root.put("name", entity.getName());
        root.put("tableName", entity.getTableName());
        root.put("fields", entity.getFields());
        Template temp = cfg.getTemplate("/entityMap.ftl");
        String path = fileReaderHelper.createDirs(mapFilePath + "/" + entity.getHbmPath());
        File file = new File(path + "/map.hbm.xml");
        return createFile(root, file, temp);
    }

    public String getMapFilePath() {
        return mapFilePath;
    }

    public void setMapFilePath(String mapFilePath) {
        this.mapFilePath = mapFilePath;
    }
}
