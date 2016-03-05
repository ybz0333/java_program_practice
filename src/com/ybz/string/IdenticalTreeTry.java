package com.ybz.string;
/*
 * 2.2 拓扑结构相同子树练习题
 * 
 * 对于两棵彼此独立的二叉树A和B，请编写一个高效算法，检查A中是否存在一棵子树与B树的拓扑结构完全相同。
给定两棵二叉树的头结点A和B，请返回一个bool值，代表A中是否存在一棵同构于B的子树。
 */
/*
 * 最优解法为二叉树序列化+KMP算法 
 * 时间复杂度为O(M+N)
 * 将两棵二叉树分别序列化，再用KMP算法判断树A的字符串是否包含树B的字符串
 * 
 * 对于KMP算法：
 * （最坏的情况下？）时间复杂度为O(m+n)，n为原字符串长度，m为要查找的字符串长度
 * 最坏的情况下，简单模式匹配算法（蛮力法）的时间复杂度为O((n-m+1)*m) 
 * KMP的应用优势
 * 1. 快，O(m+n)的线性最坏时间复杂度；
 * 2. 无需回朔访问待匹配字符串S，所以对处理从外设输入的庞大文件很有效，可以边读入边匹配。
 */

/*
 * 知乎问题：
为什么二叉树的前后续遍历不一定能确定原树？什么特殊情况下可以？
为什么前+中可以？
因为前序遍历可以确定根节点，而确定根节点后可以根据中序遍历中根节点的位置确定左子树和右子树的边界。中序遍历中R的左边是左子树，R的右边就是右子树。
Rxxxxyyyy（前序） xxxxRyyyy（中序）

同理可得后+中。

为什么前+后不行？
因为前序遍历和后序遍历都只能确定根节点是什么，而不能区分左右子树的界限。
这是什么意思？
还是刚才的例子：
Rxxxxyyyy（前序） xxxxyyyyR（后序）

你有办法区分从哪里开始是右子树吗？

作者：天仇
链接：http://www.zhihu.com/question/34776293/answer/62190793
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

（前序+中序或者后序+中序能确定，前序+后序不能）
 */

class TreeNode {
	//类的成员变量可以这样初始化？
	int val = 0;
	TreeNode left = null;
	TreeNode right = null;
	public TreeNode(int val) {
		this.val = val;
	}
}

public class IdenticalTreeTry {
	
	public static boolean chkIdentical(TreeNode A, TreeNode B) {
		StringBuilder treeStrA = serializeTree(A);
		StringBuilder treeStrB = serializeTree(B);
		return (indexOf(treeStrA, treeStrB) != -1);
	}
	
	public static StringBuilder serializeTree(TreeNode tree) {
		StringBuilder treeStr = new StringBuilder();
		serializeTreeNode(tree, treeStr);
		return treeStr;
	}
	
	//先序遍历
	/*
	 * 注意子树的定义：
	 * 子树：在一棵非空树中，除根外其余所有结点分属于 m 个(m≥0)不相交的集合。每个集合又构成一棵树，称为根结点的子树。
	 * 这里treeStr.append("#")是必要的
	 */
	public static void serializeTreeNode(TreeNode treeNode,StringBuilder treeStr) {
		if(treeNode == null) {
			treeStr.append("#");
			return;
		}
		treeStr.append(treeNode.val);
		serializeTreeNode(treeNode.left, treeStr);
		serializeTreeNode(treeNode.right, treeStr);
	}
	
/*
	StringBuilder的用法：
	StringBuilder(String str) 构造一个字符串生成器，并初始化为指定的字符串内容。
	append方法的使用：
		StringBuilder append(boolean b) 将 boolean 参数的字符串表示形式追加到序列。 
		StringBuilder append(char c) 将 char 参数的字符串表示形式追加到此序列。 
		StringBuilder append(char[] str) 将 char 数组参数的字符串表示形式追加到此序列。 
		还可以：
		StringBuilder append(double d) 将 double 参数的字符串表示形式追加到此序列。 
		StringBuilder append(float f) 将 float 参数的字符串表示形式追加到此序列。 
	
	String用toCharArray()转换成char[]
	StringBuilder用toString()转换成String
	StringBuilder要先转换成String才能调用toCharArray()？
*/
	//蛮力法寻找字符串A是否包含字符串B
	public static int indexOf(StringBuilder treeStr, StringBuilder treeStrModel) {
		char[] gcTreeStr =treeStr.toString().toCharArray();
		char[] gcModel =treeStrModel.toString().toCharArray();

		int endPos = gcTreeStr.length-gcModel.length;
		for(int i=0; i<=endPos; i++) {
			boolean foundString = true;
			for(int j=0; j<treeStrModel.length(); j++) {
				if(gcTreeStr[i+j] != gcModel[j]) {
					foundString = false;
				}
			}
			if(foundString == true) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * TreeA:
	 *     1
	 *    / \
	 *   2   3
	 *  / \ / \
	 *  4 5 6 7
	 *  
	 *  TreeB:
	 *    3
	 *   / \
	 *  6   7
	 *  
	 *  TreeC:
	 *    3
	 *   / \
	 *  4   5
	 */
	public static void main(String[] args) {
		TreeNode treeA = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		treeA.left = node2;
		treeA.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		//node3就是TreeB
		
		TreeNode treeC = new TreeNode(3);
		treeC.left = new TreeNode(4);
		treeC.right = new TreeNode(5);
		
		TreeNode treeD = new TreeNode(2);
		TreeNode treeE = new TreeNode(4);
		
		System.out.println(serializeTree(treeA));
		System.out.println(serializeTree(node3));
		System.out.println(serializeTree(treeC));
		System.out.println(chkIdentical(treeA, node3));
		System.out.println(chkIdentical(treeA, treeC));
		System.out.println(chkIdentical(treeA, treeD));
		System.out.println(chkIdentical(treeA, treeE));
	}
}
