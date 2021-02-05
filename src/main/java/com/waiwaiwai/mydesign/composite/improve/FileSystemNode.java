package com.waiwaiwai.mydesign.composite.improve;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/4 16:01
 * @Description: 抽象文件类
 */
@Getter
@ToString
public abstract class FileSystemNode {

    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

}
