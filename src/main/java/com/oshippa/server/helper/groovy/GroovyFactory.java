package com.oshippa.server.helper.groovy;

/**
 * Created by steve on 1/16/16.
 */

import com.oshippa.server.helper.FileReaderHelper;
import com.oshippa.server.model.Entity;
import com.oshippa.server.service.EntityService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GroovyFactory implements ApplicationContextAware {

    @Value("${free_maker.generated.groovy}")
    private String directory;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    private ApplicationContext applicationContext;

    @Autowired
    private FileReaderHelper fileReaderHelper;


    @Autowired
    EntityService entityService;
    private static final String REFRESH_CHECK_DELAY_STR = "org.springframework.scripting.support.ScriptFactoryPostProcessor.refreshCheckDelay";
    private static final String LANGUAGE = "org.springframework.scripting.support.ScriptFactoryPostProcessor.language";
    private static final String BEAN_CLASS_NAME = "org.springframework.scripting.groovy.GroovyScriptFactory";

    private DefaultListableBeanFactory beanFactory = null;
    private String realDirectory = null;

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.applicationContext = context;
        this.beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        this.realDirectory = fileReaderHelper.getBaseFolder()+"/"+directory;
    }


    /**
     * add or update one groovy dao bean to context
     * */
    public void addGroovyFile(Entity entity) {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClassName(BEAN_CLASS_NAME);
        // 刷新时间
        bd.setAttribute(REFRESH_CHECK_DELAY_STR, 500);
        // 语言脚本
        bd.setAttribute(LANGUAGE, "groovy");
        File file = new File(realDirectory + "/" + entity.getHbmPath() + "/Dao.groovy");
        //delete the old one and create new one
        if(file.exists()){
            file.delete();
        }
        if (!file.exists()) {
            entity = entityService.getEntity(entity.getId());
            entityService.createTableFiles(entity);
        }
        System.out.println(file.exists());
        // 文件目录
        bd.getConstructorArgumentValues().addIndexedArgumentValue(0, "classpath:"+directory+"/" + entity.getHbmPath() + "/Dao.groovy");
        // 注册到spring容器
        beanFactory.registerBeanDefinition(entity.getTableName() + "_Dao", bd);
    }

    /**
     * add or update some groovy dao beans to context
     * */
    public void addAllGroovys(List<Entity> entities) {
        for (Entity entity : entities) {
            addGroovyFile(entity);
        }
    }
}
