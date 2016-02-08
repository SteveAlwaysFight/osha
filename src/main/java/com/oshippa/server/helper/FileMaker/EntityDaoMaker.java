package com.oshippa.server.helper.FileMaker;

import com.oshippa.server.model.Entity;
import com.oshippa.server.model.Field;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 1/15/16.
 */
@Service
public class EntityDaoMaker extends FileMaker<Entity> {
    protected Map root = new HashMap();
    private String groovyPath;


    @Autowired
    public EntityDaoMaker(FreeMakerConfigurationProvider provider) {
        super(provider);
        this.groovyPath = provider.getGroovyPath();
    }


    @SuppressWarnings("unchecked")
    Map getMapContext(Entity entity) {
        List<Field> fields = new ArrayList<>();
        for(Field field: entity.getFields()){
            fields.add(field);
        }
        root.put("name", entity.getName());
        root.put("tableName", entity.getTableName());
        root.put("fields", fields);
        return root;
    }

    @Override
    public File createTemplateFile(Entity entity) throws IOException, TemplateException {
        Map root = getMapContext(entity);
        Template temp = cfg.getTemplate("/entityDao.ftl");
        String path = fileReaderHelper.createDirs(groovyPath + "/" +entity.getHbmPath());
        File file = new File(path +  "/Dao.groovy");
        if(file.exists()){
            file.delete();
        }
        return createFile(root, file, temp);
    }
}
