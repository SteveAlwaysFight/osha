package com.oshippa.server.dao;

import com.oshippa.common.db.dao.IBaseGenericDao;
import com.oshippa.server.model.file.Report;

/**
 * Created by steve on 1/20/16.
 */
public interface ReportDao  extends IBaseGenericDao<Report,String> {
    void deleteReport(String id);
    String getReportTableName(String id);

    void setFolder(String folderId,String reportId);
}
