package com.waiwaiwai.mydesign.composite.improve;

import java.io.File;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/4 16:04
 * @Description: 文件
 */
public class FileNode extends FileSystemNode {
    public FileNode(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        File file = new File(path);
        if (!file.exists()) return 0;
        return file.length();
    }
}
