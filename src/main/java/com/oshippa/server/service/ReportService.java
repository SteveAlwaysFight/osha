package com.oshippa.server.service;

import com.oshippa.server.model.file.Report;
import com.oshippa.server.transfd.report.CreateReportRequest;
import com.oshippa.server.transfd.report.ReportResponse;

import java.util.List;

/**
 * Created by steve on 1/22/16.
 */
public interface ReportService {
    ReportResponse createReportFromRequest(CreateReportRequest reportRequest);
    Report updateReportFromRequest(Report report);
    ReportResponse getReport(String id);

    void deleteReport(String id);

    List<Report> listReports();

}
