package com.zhong.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树形选项列表
 */
@Data
public class TreeOptionItemVO<T> {

    private T value;
    private String label;

    private List<TreeOptionItemVO<T>> children;

    public TreeOptionItemVO(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public TreeOptionItemVO() {
    }

    @Data
    public static class TreeOptionItemDTO {

        private Integer parentId;
        private Integer value;
        private String label;
    }

    /**
     * 1. 返回树形
     */
    public static List<TreeOptionItemVO<Integer>> ofTree(List<TreeOptionItemDTO> parentList, List<TreeOptionItemDTO> childRenList) {
        return parentList.stream().map(it -> {
            TreeOptionItemVO<Integer> optionItemVO = new TreeOptionItemVO<>();
            optionItemVO.setValue(it.value);
            optionItemVO.setLabel(it.label);
            optionItemVO.setChildren(children(it.value, childRenList));
            return optionItemVO;
        }).collect(Collectors.toList());
    }

    /**
     * 2. 返回树形
     */
    public static <T, E> List<TreeOptionItemVO<T>> ofTree(List<E> list, Function<E, T> idFunction, Function<E, T> pidFunction,
                                                          Function<E, String> labelFunction, T parent) {

        List<TreeOptionItemVO<T>> treeOptionItemVOList = new ArrayList<>();
        tree(list, idFunction, labelFunction, pidFunction, treeOptionItemVOList, parent);
        return treeOptionItemVOList;
    }


    private static <T, E> void tree(List<E> list, Function<E, T> valueSupplier,
                                    Function<E, String> labelSupplier,
                                    Function<E, T> parentSupplier,
                                    List<TreeOptionItemVO<T>> chlList, T parent) {
        if (list != null) {
            for (E item : list) {
                if (item != null && parentSupplier.apply(item).equals(parent)) {
                    TreeOptionItemVO<T> optionItemVO = new TreeOptionItemVO<>(valueSupplier.apply(item), labelSupplier.apply(item));
                    optionItemVO.setChildren(new ArrayList<>());
                    chlList.add(optionItemVO);
                    tree(list, valueSupplier, labelSupplier, parentSupplier, optionItemVO.getChildren(), optionItemVO.value);
                }
            }
        }
    }

    private static List<TreeOptionItemVO<Integer>> children(Integer parentId, List<TreeOptionItemDTO> childRenList) {
        return childRenList.stream()
                .filter(child -> child.getParentId().equals(parentId))
                .map(it -> {
                    TreeOptionItemVO<Integer> optionItemVO = new TreeOptionItemVO<>();
                    optionItemVO.setValue(it.value);
                    optionItemVO.setLabel(it.label);
                    return optionItemVO;
                }).collect(Collectors.toList());
    }


}