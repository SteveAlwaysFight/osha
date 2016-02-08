package com.oshippa.server.helper;

import com.oshippa.server.helper.FileMaker.FreeMakerConfigurationProvider;
import com.oshippa.server.model.TableElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by steve on 1/15/16.
 */
@Service
public class TableCreator {
    private static Log logger = LogFactory.getLog(TableCreator.class);

    @Autowired
    DataSource dataSource;
    String mapFilePath;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    FileReaderHelper fileReaderHelper;

    @Autowired
    public TableCreator(FreeMakerConfigurationProvider provider) {
        this.mapFilePath = provider.getMapFilePath();
    }

    public void createTable(TableElement element) throws SQLException {
        org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration();
//        conf.configure("/META-INF/springHibernate.xml");
//        Properties extraProperties = new Properties();
//        extraProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        extraProperties.put("hibernate.hbm2ddl.auto", "update");
//        extraProperties.put("hibernate.show_sql", "true");
//        extraProperties.put("hibernate.format_sql", "true");
//        conf.addProperties(extraProperties);

        conf.addFile(fileReaderHelper.getBaseFolder() + "/"+ mapFilePath + "/" + element.getHbmPath() + "/map.hbm.xml");

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(Thread.currentThread().getContextClassLoader().getResource("/properties/hibernate.properties").getFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        org.hibernate.service.ServiceRegistry serviceRegistry = sessionFactory.getSessionFactoryOptions().getServiceRegistry();

        conf.addProperties(properties);
        String tableName = element.getTableName();
        // if the table is not exists then create one
        if (!checkTableExist(tableName)) {
            SchemaExport export = new SchemaExport(conf, dataSource.getConnection());
            export.setOutputFile(fileReaderHelper.getBaseFolder() + "/"+ mapFilePath + "/" + element.getHbmPath()+"/"+element.getTableName());
            export.create(false, true);
            logger.info(element.getTableName() + " table created " + element.getHbmPath());
        } else {
            SchemaUpdate dbExport = new SchemaUpdate(serviceRegistry, conf);
            dbExport.setOutputFile(fileReaderHelper.getBaseFolder() + "/"+ mapFilePath + "/" + element.getHbmPath()+"/"+element.getTableName());
            dbExport.execute(false, true);
            logger.info(element.getTableName() + " table updated " + element.getHbmPath());
        }
    }

    private boolean checkTableExist(String tableName) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
            if (rs.next()) {
                logger.info("table " + tableName + " existing");
                return true;
            } else {
                logger.info("table " + tableName +" is not existing");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
