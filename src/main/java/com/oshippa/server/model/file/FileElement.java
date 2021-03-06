/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.model.file;

import com.oshippa.common.model.Element;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by steve on 2/12/16.
 */
@Entity
@Table(name = "file_element")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FileElement extends Element{


    private String parentFoldId;

    public String getParentFoldId() {
        return parentFoldId;
    }

    public void setParentFoldId(String parentFoldId) {
        this.parentFoldId = parentFoldId;
    }
}
