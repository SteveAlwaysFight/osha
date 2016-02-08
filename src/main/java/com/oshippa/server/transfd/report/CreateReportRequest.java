package com.oshippa.server.transfd.report;

import com.oshippa.server.transfd.ElementRequest;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by steve on 1/22/16.
 */
@XmlRootElement
public class CreateReportRequest extends ElementRequest {

    @NotNull
    @Valid
    @Size(min = 1, max = 16)
    List<ReportFieldRequest> fields;

    @NotNull
    @Valid
    @Length(min = 32)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReportFieldRequest> getFields() {
        return fields;
    }

    public void setFields(List<ReportFieldRequest> fields) {
        this.fields = fields;
    }

}
