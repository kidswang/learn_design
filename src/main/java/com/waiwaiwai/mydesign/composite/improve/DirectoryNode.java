package com.waiwaiwai.mydesign.composite.improve;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/4 16:07
 * @Description: 目录
 */
public class DirectoryNode extends FileSystemNode {
    private final List<FileSystemNode> subNodes = new ArrayList<>();

    public DirectoryNode(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        int numOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            numOfFiles += subNode.countNumOfFiles();
        }
        return numOfFiles;
    }

    @Override
    public long countSizeOfFiles() {
        int sizeOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            sizeOfFiles += subNode.countSizeOfFiles();
        }
        return sizeOfFiles;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < subNodes.size(); i++) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }

        if (i < size) {
            subNodes.remove(i);
        }
    }

    public void getList() {
        subNodes.forEach(System.out::println);
        System.out.println("==");
    }

}
