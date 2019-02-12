package com.study.arithmetic.testgit;

import com.study.arithmetic.binarytree.SearchBinaryTree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGit() {
        // Gitflow 学习：
        // 1、git commit --amend： 命令确实起作用，一定不要试图去修改线上的提交日志,否则会产生冲突！！！
        // 2、git rebase 线上修改
        // 2、git rebase 01
        // 2、git rebase 02
        // 2、git rebase 03
    }

    public void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("\n---------");
    }
}
