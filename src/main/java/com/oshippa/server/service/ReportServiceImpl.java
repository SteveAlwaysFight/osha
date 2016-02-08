package com.oshippa.server.service;

import com.oshippa.common.db.service.BaseGenericServiceImpl;
import com.oshippa.common.exception.ApplicationRuntimeException;
import com.oshippa.server.dao.FieldDao;
import com.oshippa.server.dao.ReportDao;
import com.oshippa.server.dao.ReportFieldDao;
import com.oshippa.server.exception.ElementNotFoundException;
import com.oshippa.server.helper.FileMaker.HbmMaker;
import com.oshippa.server.helper.FileMaker.ReportTemplateFileMaker;
import com.oshippa.server.helper.TableCreator;
import com.oshippa.server.model.Field;
import com.oshippa.server.model.Report;
import com.oshippa.server.model.ReportField;
import com.oshippa.server.transfd.report.CreateReportRequest;
import com.oshippa.server.transfd.report.ReportFieldRequest;
import com.oshippa.server.transfd.report.ReportResponse;
import freemarker.template.TemplateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 1/22/16.
 */
@Service
public class ReportServiceImpl extends BaseGenericServiceImpl<Report, String> implements ReportService {

    private static Log logger = LogFactory.getLog(ReportServiceImpl.class);

    @Autowired
    public ReportServiceImpl(Validator validator) {
        super(validator);
    }

    @Autowired
    ReportDao reportDao;
    @Autowired
    ReportFieldDao reportFieldDao;
    @Autowired
    FieldDao fieldDao;
    @Autowired
    ReportTemplateFileMaker reportTemplateFileMaker;
    @Autowired
    HbmMaker hbmMaker;
    @Autowired
    TableCreator tableCreator;


    private void createTableFiles(Report entity) {
        try {
            reportTemplateFileMaker.createTemplateFile(entity);
            hbmMaker.createTemplateFile(entity);
            tableCreator.createTable(entity);
        } catch (IOException e) {
            logger.error(e);
            throw new ApplicationRuntimeException("error while creating table " + entity.getId() + " files");
        } catch (TemplateException e) {
            logger.error(e);
            throw new ApplicationRuntimeException("error while creating table  " + entity.getId() + "files");
        } catch (SQLException e) {
            logger.error(e);
            throw new ApplicationRuntimeException("error while creating table  " + entity.getId() + "table");
        }
    }


    @Override
    public ReportResponse createReportFromRequest(CreateReportRequest reportRequest) {
        validate(reportRequest);
        Long now = new Date().getTime();
        Report report = new Report();
        String tableName = "report_" + reportRequest.getName() + "_" + now;
        report.setTableName(tableName);
        report.setHbmPath(report.getName() + "/" + report.getTableName());
        report.setName(reportRequest.getName());
        report.setDescription(reportRequest.getDescription());
        report.setDeleted(false);

        report.setContent(reportRequest.getContent());


        for (ReportFieldRequest fieldRequest : reportRequest.getFields()) {
            ReportField field = new ReportField();
            field.setName(fieldRequest.getName());
            field.setDescription(fieldRequest.getDescription());
            field.setLength(fieldRequest.getLength());
            field.setFieldType(fieldRequest.getFieldType());
            field.setIfNull(fieldRequest.isIfNull());
            field.setIsRelatedField(fieldRequest.isRelatedField());
            if (fieldRequest.isRelatedField() ) {
                Field relatedField = null;
                if(fieldRequest.getRelatedField() == null || (relatedField =fieldDao.get(fieldRequest.getRelatedField())) == null){
                    throw new ApplicationRuntimeException("related field can not be null or not found");
                }
                field.setRelatedField(relatedField);
            }
            report.addField(field);
        }
        reportDao.save(report);
        for (ReportField field : report.getFields()) {
            field.setDeleted(false);
            field.setReport(report);
            reportFieldDao.save(field);
        }

        createTableFiles(report);
        ReportResponse reportResponse = new ReportResponse(report);
        reportResponse.setContent(reportTemplateFileMaker.getGeneratedReportTemplatePath(report));
        return reportResponse;
    }

    @Override
    public Report updateReportFromRequest(Report report) {
        return null;
    }

    @Override
    public ReportResponse getReport(String id) {
        Report report = reportDao.get(id);
        if (report == null) {
            logger.error("can't find report id=" + id);
            throw new ElementNotFoundException("report");
        }
        logger.info("found report id=" + report.getId() + " name=" + report.getName());
        ReportResponse reportResponse = new ReportResponse(report);
        reportResponse.setContent(reportTemplateFileMaker.getGeneratedReportTemplatePath(report));
        return reportResponse;
    }

    @Override
    public List<Report> listReports() {
        Map<String, Object> map = new HashMap<>();
        map.put("deleted", false);
        String[] fields = new String[]{"id", "name", "createTime", "updateTime", "description", "hbmPath", "tableName"};
        List reports = reportDao.getListbyFieldAndParams(fields, map);
        return reports;
    }

    @Override
    public void deleteReport(String id) {
        reportDao.deleteReport(id);
    }
}
