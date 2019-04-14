package com.demo.v2g.adapters;

import com.demo.v2g.model.objectsOnMap.ObjectsContent;

import java.util.List;

public interface onMapObjectsCallback {
    void objectsContentsResult(List<ObjectsContent> objectsContents);
}
