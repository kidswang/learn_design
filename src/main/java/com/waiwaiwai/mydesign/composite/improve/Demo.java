package com.waiwaiwai.mydesign.composite.improve;

public class Demo {
    public static void main(String[] args) {
        /**
         * /
         * /wz/
         * /wz/a.txt
         * /wz/b.txt
         * /wz/movies/
         * /wz/movies/c.avi
         * /xzg/
         * /xzg/docs/
         * /xzg/docs/d.txt
         */
        DirectoryNode FileNodeSystemTree = new DirectoryNode("/");
        DirectoryNode node_wz = new DirectoryNode("/wz/");
        DirectoryNode node_xzg = new DirectoryNode("/xzg/");
        FileNodeSystemTree.addSubNode(node_wz);
        FileNodeSystemTree.addSubNode(node_xzg);

        FileNode node_wz_a = new FileNode("/wz/a.txt");
        FileNode node_wz_b = new FileNode("/wz/b.txt");
        DirectoryNode node_wz_movies = new DirectoryNode("/wz/movies/");
        node_wz.addSubNode(node_wz_a);
        node_wz.addSubNode(node_wz_b);
        node_wz.addSubNode(node_wz_movies);

        FileNode node_wz_movies_c = new FileNode("wz/movies/c.avi");
        node_wz_movies.addSubNode(node_wz_movies_c);

        DirectoryNode node_xzg_docs = new DirectoryNode("/xzg/docs/");
        node_xzg.addSubNode(node_xzg_docs);

        FileNode node_xzg_docs_d = new FileNode("/xzg/docs/d.txt");
        node_xzg_docs.addSubNode(node_xzg_docs_d);

        System.out.println("/ FileNodes num:" + FileNodeSystemTree.countNumOfFiles());
        System.out.println("/wz/ FileNodes num:" + node_wz.countNumOfFiles());

        node_wz.getList();
    }
}
