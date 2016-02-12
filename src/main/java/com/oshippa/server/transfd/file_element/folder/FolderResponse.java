/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.transfd.file_element.folder;

import com.oshippa.common.model.Element;
import com.oshippa.server.model.file.Folder;
import com.oshippa.server.transfd.ElementResponse;

/**
 * Created by steve on 2/12/16.
 */
public class FolderResponse extends ElementResponse {

    private int depth;
    private String parentFolderId;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public FolderResponse(Folder element) {
        super(element);
        this.setDepth(element.getDepth());
        this.setParentFolderId(element.getParentFoldId());

    }
}
