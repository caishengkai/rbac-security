package com.csk.rbac.common.utils;

import com.csk.rbac.common.RbacTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:树结构操作工具类
 * @author: caishengkai
 * @time: 2020/1/10 16:53
 **/
public class TreeUtil {

    public static <T> RbacTree<T> build(List<RbacTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<RbacTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || "0".equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (RbacTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setChildren(true);
                    return;
                }
            }
        });

        RbacTree<T> root = new RbacTree<>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setChildren(true);
        root.setChecked(true);
        root.setChildren(topNodes);
        root.setText("根节点");
        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);
        root.setState(state);
        return root;
    }

}
