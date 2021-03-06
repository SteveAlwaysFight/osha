package com.oshippa.server.transfd.report;

import com.oshippa.server.model.file.ReportField;
import com.oshippa.server.transfd.ElementResponse;
import com.oshippa.server.transfd.entities.FieldResponse;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by steve on 1/22/16.
 */
@XmlRootElement
public class ReportFieldResponse extends ElementResponse {

    private String fieldType;

    private int length;

    private boolean ifNull;

    private boolean isRelatedField;

    private FieldResponse relatedField;

    public ReportFieldResponse(ReportField element) {
        super(element);
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isIfNull() {
        return ifNull;
    }

    public void setIfNull(boolean ifNull) {
        this.ifNull = ifNull;
    }

    public boolean isRelatedField() {
        return isRelatedField;
    }

    public void setIsRelatedField(boolean isRelatedField) {
        this.isRelatedField = isRelatedField;
    }

    public FieldResponse getRelatedField() {
        return relatedField;
    }

    public void setRelatedField(FieldResponse relatedField) {
        this.relatedField = relatedField;
    }
}
