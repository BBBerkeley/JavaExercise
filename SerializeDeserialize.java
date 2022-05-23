package nowcoder;

import java.util.LinkedList;
import java.util.Queue;

// 序列化二叉树
public class SerializeDeserialize {
    String serialize(TreeNode root) {
        if (root == null){
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");    // 可变字符串
        Queue<TreeNode> queue = new LinkedList<>();    // LinkedList对象（重写？执行LinkedList类方法），队列存结点
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();    // 删除并返回第一个元素
            if (node != null) {
//                res.append(Integer.toString(node.val) + ",");
                res.append(node.val).append(",");    // 不用类型转换，因为str是个可变字符串，int添加进去后是"1,null"
                queue.offer(node.left);
                queue.offer(node.right);
            }
            else {
                res.append("#,");    // 不能是"null",因为结果存在可变字符串StringBuilder（char数组）。deleteCharAt()删除的是char，如果是“null”需要删四次
            }
        }
        res.deleteCharAt(res.length() - 1);    // 删掉最后一个","
        while (String.valueOf(res.charAt(res.length()-1)).equals("#")){    // equals比较的是对象值，charAt返回值为char，所以转换成String；“#”也是双引号。
            res.deleteCharAt(res.length() - 1);    // 删"#"
            res.deleteCharAt(res.length() - 1);    // 删","
        }
        res.append("]");
        return res.toString();
    }

    TreeNode deserialize(String str) {
        if (str.equals("[]")) {
            return null;
        }
        String[] vals = str.substring(1, str.length() - 1).split(",");    // 去掉"[]"（返回的是新字符串），并以","拆分字符串
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.offer(root);

        int num = vals.length;
        if (num == 1) {
            return root;
        }
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!vals[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(node.left);
            }
            i ++;
            if (i >= num) {
                break;
            }
            if (!vals[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(node.right);
            }
            i ++;
            if (i >= num) {
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
//        String str = "[1,2,3,#,#,4,5]";
        String str = "[5]";
        SerializeDeserialize sd = new SerializeDeserialize();
        String str_result = sd.serialize(sd.deserialize(str));
        System.out.print(str_result);
    }
}
