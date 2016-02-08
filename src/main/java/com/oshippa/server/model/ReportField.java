package com.oshippa.server.model;

import com.oshippa.common.model.Element;

import javax.persistence.*;

/**
 * Created by steve on 1/20/16.
 */
@javax.persistence.Entity
@Table(name = "report_field")
public class ReportField extends Element {
    @ManyToOne
    private Report report;

    private String fieldType;

    private int length;

    private boolean ifNull;

    private boolean isRelatedField;

    @ManyToOne
    private Field relatedField;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Field getRelatedField() {
        return relatedField;
    }

    public void setRelatedField(Field relatedField) {
        this.relatedField = relatedField;
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
}
