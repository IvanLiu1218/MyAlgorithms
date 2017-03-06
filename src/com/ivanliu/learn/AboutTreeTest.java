package com.ivanliu.learn;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ivanliu.learn.AboutTree.TreeNode;

public class AboutTreeTest {

	private TreeNode<Character> root = null;
	
	@Before
	public void before() {
		/*
		 *         A
		 *       /   \
		 *      B     E
		 *     / \   / \
		 *    C   D F   G
		 */
		root = new TreeNode<Character>('A');
		root.left = new TreeNode<Character>('B');
		root.left.left = new TreeNode<Character>('C');
		root.left.right = new TreeNode<Character>('D');
		root.right = new TreeNode<Character>('E');
		root.right.left = new TreeNode<Character>('F');
		root.right.right = new TreeNode<Character>('G');
	}
	
	@Test
	public void testToStringByDFS() {
		assertEquals("A,B,C,D,E,F,G", AboutTree.toStringByDFS(root));
	}
	
	@Test
	public void testToStringByBFS() {
		assertEquals("A,B,E,C,D,F,G", AboutTree.toStringByBFS(root));
	}
	
	@Test
	public void testGetAllPaths() {
		List<String> paths = AboutTree.getAllPaths(root);
		assertEquals(4, paths.size());
		assertEquals("ABC", paths.get(0));
		assertEquals("ABD", paths.get(1));
		assertEquals("AEF", paths.get(2));
		assertEquals("AEG", paths.get(3));
		
		root = new TreeNode<Character>('A');
		root.left = new TreeNode<Character>('B');
		root.left.left = new TreeNode<Character>('C');
		root.right = new TreeNode<Character>('E');
		root.right.left = new TreeNode<Character>('F');
		root.right.right = new TreeNode<Character>('G');
		paths = AboutTree.getAllPaths(root);
		assertEquals(3, paths.size());
		assertEquals("ABC", paths.get(0));
		assertEquals("AEF", paths.get(1));
		assertEquals("AEG", paths.get(2));
		
		/*
		 *         1
		 *       /   \
		 *      0     7
		 *     / \   / \
		 *    2   0 5   4
		 *    
		 *    102
		 *    100
		 *    175
		 *  + 174
		 *  ------
		 *    551
		 */
		root = new TreeNode<Character>('1');
		root.left = new TreeNode<Character>('0');
		root.left.left = new TreeNode<Character>('2');
		root.left.right = new TreeNode<Character>('0');
		root.right = new TreeNode<Character>('7');
		root.right.left = new TreeNode<Character>('5');
		root.right.right = new TreeNode<Character>('4');
		paths = AboutTree.getAllPaths(root);
		int sum = 0;
		for (int i = 0; i < paths.size(); ++i) {
			sum += Integer.parseInt(paths.get(i));
		}
		assertEquals(551, sum);
		
		/*
		 *         1
		 *       /   \
		 *      0     7
		 *     /
		 *    2
		 *    
		 *    102
		 *  +  17
		 *  ------
		 *    119
		 */
		root = new TreeNode<Character>('1');
		root.left = new TreeNode<Character>('0');
		root.left.left = new TreeNode<Character>('2');
		root.right = new TreeNode<Character>('7');
		paths = AboutTree.getAllPaths(root);
		sum = 0;
		for (int i = 0; i < paths.size(); ++i) {
			sum += Integer.parseInt(paths.get(i));
		}
		assertEquals(119, sum);
	}
	
	@Test
	public void testGetTotal() {
		/*
		 *         1
		 *       /   \
		 *      0     7
		 *     / \   / \
		 *    2   0 5   4
		 */    
		TreeNode<String> node = new TreeNode<>("1");
		node.left = new TreeNode<>("0");
		node.left.left = new TreeNode<>("2");
		node.left.right = new TreeNode<>("0");
		node.right = new TreeNode<>("7");
		node.right.left = new TreeNode<>("5");
		node.right.right = new TreeNode<>("4");
		assertEquals(551, AboutTree.getTotal(node));
	}
}
