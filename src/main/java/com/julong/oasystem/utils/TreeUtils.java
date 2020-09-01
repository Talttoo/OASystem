package com.julong.oasystem.utils;

import com.julong.oasystem.entity.DepartmentVO;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    //把一个List转成树
    public static List<DepartmentVO> buidTree(List<DepartmentVO> list){
        List<DepartmentVO> tree=new ArrayList<>();
        for(DepartmentVO DepartmentVO :list){
            if(DepartmentVO.getParentid()== 0){
                tree.add(findChild(DepartmentVO,list));
            }
        }
        return tree;
    }

    static DepartmentVO findChild(DepartmentVO DepartmentVO, List<DepartmentVO> list){
        for(DepartmentVO n:list){
            if(n.getParentid() == DepartmentVO.getId()){
                if(DepartmentVO.getChildren() == null){
                    DepartmentVO.setChildren(new ArrayList<DepartmentVO>());
                }
                DepartmentVO.getChildren().add(findChild(n,list));
            }
        }
        return DepartmentVO;
    }

    public static  void main(String[] args) {


    }

}
