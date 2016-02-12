/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service;

import com.oshippa.common.db.service.IBaseGenericService;
import com.oshippa.server.model.file.Folder;
import com.oshippa.server.transfd.file_element.folder.CreateFolderToParentFolderRequest;
import com.oshippa.server.transfd.file_element.folder.CreateRootFolderRequest;
import com.oshippa.server.transfd.file_element.folder.FolderResponse;

import java.util.List;

/**
 * Created by steve on 2/12/16.
 */
public interface FolderService extends IBaseGenericService<Folder,String>{

    FolderResponse createRootFolderUnderModule(CreateRootFolderRequest createRootFolderRequest);
    List<FolderResponse> getChildFolder(String id);
    FolderResponse createFolderToParent(CreateFolderToParentFolderRequest createFolderToParentFolderRequest);



}
