package BST;




import java.util.*;
        import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//import javax.swing.tree.Node;

//import com.sun.source.tree.BinaryTree;

class Node{
    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BST
{
    Node root;
    Node IterativeSearch(Node root,int val){
        while(root!=null && root.data!=val){
            root=(root.data<val)?root.right:root.left;
        }
        return root;
    }
    Node RecursiveSearch(Node temp,int val){
        if(temp==null || temp.data==val){
            return temp;
        }
        if(temp.data<val){
            return RecursiveSearch(temp.right,val);
        }
        else {
            return RecursiveSearch(temp.left, val);
        }
    }
    void preOrder(Node node){
        if(node==null) return;
        preOrder(node.left);
        System.out.print(node.data+" ");
        preOrder(node.right);
    }
    void inOrder(Node node){
        if(node==null) return;
        inOrder(node.left);
        System.out.print(node.data+" ");
        inOrder(node.right);
    }
    Node buildBST(int[] arr,int start,int end){
        if(start>end) return null;
        int mid=(start+end)/2;
        Node node;
        if(arr[mid]==-100){
            return null;
        }
        else{
            node=new Node(arr[mid]);
        }

        node.left=buildBST(arr,start,mid-1);
        node.right=buildBST(arr,mid+1,end);
        return node;
    }
    int minValueIterative(Node root) {
        while(root.left!=null){
            root=root.left;
        }
        return root.data;
    }
    int maxValueIterative(Node root) {
        while(root.right!=null){
            root=root.right;
        }
        return root.data;
    }
    int minRecursive(Node root){
        if(root.left==null){
            return root.data;
        }
        return minRecursive(root.left);
    }
    int maxRecursive(Node root){
        if(root.right==null){
            return root.data;
        }
        return maxRecursive(root.right);
    }
    public Node insertIntoBST(Node root, int val) {
        Node temp=root;
        if(temp==null) return new Node(val);
        while(true){
            if(val>root.data){
                if(root.right==null){
                    root.right=new Node(val);
                    return temp;
                }
                else{
                    root=root.right;
                }
            }
            else{
                if(root.left==null){
                    root.left=new Node(val);
                    return temp;
                }
                else{
                    root=root.left;
                }
            }
        }
    }
    //wrong
    public static Node insertRecursive(Node node,int val){
        if(node==null) return new Node(val);
        if(node.data>val){
            node.left=insertRecursive(node.left,val);
        }
        else if(node.data<val ){
            node.right=insertRecursive(node.right,val);
        }
        return node;
    }
    public Node deleteNode(Node root, int key) {
        Node temp=root;
        if(temp!=null && temp.data==key){
            if(temp.right==null && temp.left==null) return null;
            else if(temp.right==null){
                return temp.left;
            }
            else if(temp.left==null){
                return temp.right;
            }
            else{
                Node node=temp.right;
                while(node.left!=null){
                    node=node.left;
                }
                node.left=temp.left;
                return temp.right;
            }

        }

        while(temp!=null){
            if(temp.left!=null && temp.left.data==key){
                if(temp.left.left==null && temp.left.right==null){
                    temp.left=null;
                    return root;
                }
                else if(temp.left.left==null){
                    temp.left=temp.left.right;
                    return root;
                }
                else if(temp.left.right==null){
                    temp.left=temp.left.left;
                    return root;
                }
                else{
                    Node node=temp.left.right;
                    while(node.left!=null){
                        node=node.left;
                    }
                    node.left=temp.left.left;
                    temp.left=temp.left.right;
                    return root;
                }
            }
            else if(temp.right!=null && temp.right.data==key){
                if(temp.right.left==null && temp.right.right==null){
                    temp.right=null;
                    return root;
                }
                else if(temp.right.left==null){
                    temp.right=temp.right.right;
                    return root;
                }
                else if(temp.right.right==null){
                    temp.right=temp.right.left;
                    return root;
                }
                else{
                    Node node=temp.right.right;
                    while(node.left!=null){
                        node=node.left;
                    }
                    node.left=temp.right.left;
                    temp.right=temp.right.right;
                    return root;
                }
            }
            else if(temp.data>key){
                temp=temp.left;
            }
            else{
                temp=temp.right;
            }
        }

        return root;
    }
    void delete(Node root,int val){
        if(root == null) return;
        if(root.data == val){
            this.root=helper(root);
        }
        while(true){
            if(root.data > val){
                if(root.left != null && root.left.data == val){
                    root.left = helper(root.left);
                    break;
                }
                else{
                    root = root.left;
                }
            }
            else{
                if(root.right != null && root.right.data == val){
                    root.right = helper(root.right);
                    break;
                }
                else{
                    root = root.right;
                }
            }
        }
    }

    public static Node helper(Node root){
        if(root.left == null) return root.right;
        if(root.right == null) return root.left;
        Node rightmost = rightMost(root.left);
        rightmost.right = root.right;
        return root.left;
    }

    public static Node rightMost(Node root){
        while(root.right != null){
            root = root.right;
        }
        return root;
    }
    public int kthSmallest(Node root, int k) {
        int[] arr=new int[1];
        int[] count=new int[1];
        inOrder(root,k,count,arr);
        return arr[0];
    }
    public static void inOrder(Node root,int k,int[] count,int[] arr){
        if(root==null){
            return;
        }
        inOrder(root.left,k,count,arr);
        count[0]++;
        if(count[0]==k){
            arr[0]=root.data;
            return;
        }
        inOrder(root.right,k,count,arr);

    }
    public static int kthLargest(Node node,int k){
        int[] count=new int[1];
        int[] min=new int[1];
        reverseInOrder(node,k,count,min);
        return min[0];
    }
    public static void reverseInOrder(Node node,int k,int[] count,int[] min){
        if(node==null){
            return;
        }
        reverseInOrder(node.right,k,count,min);
        count[0]++;
        if(count[0]==k){
            min[0]=node.data;
            return;
        }
        reverseInOrder(node.left,k,count,min);
    }

    public boolean isValidBST(Node root) {
        long[] range={Long.MIN_VALUE,Long.MAX_VALUE};
        return check(root,range);
    }
    public static boolean check(Node node,long[] range){
        if(node==null){
            return true;
        }
        if(node.data<=range[0] || node.data>=range[1]){
            return false;
        }
        return check(node.left,new long[]{range[0],node.data}) && check(node.right,new long[]{node.data,range[1]});

    }
    public static void secondSmallest(Node node,int[] count){
        if(node==null){
            return;
        }
        secondSmallest(node.left,count);
        count[0]++;
        if(count[0]==2){
            System.out.println(node.data+" is the second smallest");
            return;
        }
        secondSmallest(node.right,count);
    }
    public static void secondSGreatest(Node node,int[] count){
        if(node==null){
            return;
        }
        secondSGreatest(node.right,count);
        count[0]++;
        if(count[0]==2){
            System.out.println(node.data+" is the second Greatest element");
            return;
        }
        secondSGreatest(node.left,count);
    }
    public static void levelOftheKey(Node node,int key){
        Queue<Node> queue=new LinkedList<>();
        queue.add(node);
        int level=0;
        while(!queue.isEmpty()){
            int n= queue.size();
            for(int i=0;i<n;i++){
                Node temp=queue.poll();
                if(temp.data==key){
                    System.out.println(level);
                }
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
            level++;
        }
    }
    public static void postOrder(Node node){
        if(node==null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data+" ");
    }
    public static int heightOftheTree(Node node){
        if(node==null) return -1;
        int left=heightOftheTree(node.left);
        int right=heightOftheTree(node.right);
        return Math.max(left,right)+1;
    }
    public static int depth(Node node,Node root){
        int rootHeight=heightOftheTree(root);
        int nodeHeight=heightOftheTree(node);
        return rootHeight-nodeHeight;
    }

    static class tuple{
        Node node;
        int ver;
        int lev;
        tuple(Node node,int ver,int lev){
            this.node=node;
            this.ver=ver;
            this.lev=lev;
        }
    }
    public List<List<Integer>> verticalTraversal(Node root) {
        Queue<tuple> queue = new LinkedList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        queue.add(new tuple(root, 0, 0));
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                tuple temp = queue.poll();
                int x = temp.ver;
                int y = temp.lev;
                if (!map.containsKey(x)) {
                    map.put(x, new TreeMap<>());
                }
                if (!map.get(x).containsKey(y)) {
                    map.get(x).put(y, new PriorityQueue<>());
                }
                map.get(x).get(y).add(temp.node.data);
                if (temp.node.left != null) {
                    tuple node = new tuple(temp.node.left, x - 1, y + 1);
                    queue.add(node);
                }
                if (temp.node.right != null) {
                    tuple node = new tuple(temp.node.right, x + 1, y + 1);
                    queue.add(node);
                }
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> i : map.values()) {
            List<Integer> list1 = new ArrayList<>();
            for (PriorityQueue<Integer> j : i.values()) {
                int n1 = j.size();
                for (int k = 0; k < n1; k++) {
                    int a = j.poll();
                    list1.add(a);
                }
            }
            list.add(list1);
        }
        return list;
    }

    static ArrayList<Integer> topView(Node root) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        Queue<tuple> queue=new LinkedList<>();
        queue.add(new tuple(root,0,0));
        while(!queue.isEmpty()){
            tuple temp=queue.poll();
            if(!map.containsKey(temp.ver)){
                map.put(temp.ver,temp.node.data);
            }
            if(temp.node.left!=null){
                queue.add(new tuple(temp.node.left,temp.ver-1,temp.lev+1));
            }
            if(temp.node.right!=null){
                queue.add(new tuple(temp.node.right,temp.ver+1,temp.lev+1));
            }
        }
        ArrayList<Integer> arr=new ArrayList<>();
        for(Integer i : map.values()){
            arr.add(i);
        }
        return arr;
    }
    class pair{
        Node node;
        int lev;
        pair(Node node,int lev){
            this.node=node;
            this.lev=lev;
        }
    }
    public List<Integer> rightSideView(Node root) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        Queue<pair> queue=new LinkedList<>();
        if(root==null){
            return new ArrayList<>();
        }
        queue.add(new pair(root,0));
        while(!queue.isEmpty()){
            pair temp=queue.poll();
            map.put(temp.lev,temp.node.data);
            if(temp.node.left!=null){
                queue.add(new pair(temp.node.left,temp.lev+1));
            }
            if(temp.node.right!=null){
                queue.add(new pair(temp.node.right,temp.lev+1));
            }
        }
        List<Integer> list=new ArrayList<>();
        list.addAll(map.values());
        return list;
    }
    Queue<Integer> queue=new LinkedList<>();
    public List<Integer> rightSideViewRecursive(Node root) {
        reversePreOrder(root,0);
        return new ArrayList<>(queue);
    }
    public  void reversePreOrder(Node node,int level){
        if(node==null){
            return;
        }
        if(level==queue.size()){
            queue.add(node.data);
        }
        reversePreOrder(node.right,level+1);
        reversePreOrder(node.left,level+1);

    }
    public List<Integer> LeftSideViewRecursive(Node root) {
        PreOrder1(root,0);
        return new ArrayList<>(queue);
    }
    public  void PreOrder1(Node node,int level){
        if(node==null){
            return;
        }
        if(level==queue.size()){
            queue.add(node.data);
        }
        PreOrder1(node.left,level+1);
        PreOrder1(node.right,level+1);
    }
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root.data==p.data || root.data==q.data){
            return root;
        }
        else if(root.data < p.data && root.data > q.data || root.data > p.data && root.data < q.data){
            return root;
        }
        else if(root.data < p.data && root.data < q.data){
            return lowestCommonAncestor(root.right,p,q);
        }
        else{
            return lowestCommonAncestor(root.left,p,q);
        }
    }
    public Node bstFromPreorder(int[] preorder) {
        int ub=Integer.MAX_VALUE;
        return preOrder2(preorder,ub,new int[1]);
    }
    public static Node preOrder2(int[] preorder,int ub,int[] i){
        if(i[0]>=preorder.length || preorder[i[0]]>ub ) return null;
        Node root=new Node(preorder[i[0]++]);
        root.left=preOrder2(preorder,root.data,i);
        root.right=preOrder2(preorder,ub,i);
        return root;
    }
    public static void inOrderSuccessor(Node root,int k,int[] suc){
        if(root==null) return;
        if(root.data>k){
            suc[0]=root.data;
            inOrderSuccessor(root.left,k,suc);
        }
        else{
            inOrderSuccessor(root.right,k,suc);
        }
    }
    public static void inOrderPredecessor(Node root,int k,int[] pre){
        if(root==null) return;
        if(root.data<k){
            pre[0]=root.data;
            inOrderPredecessor(root.right,k,pre);
        }
        else{
            inOrderPredecessor(root.left,k,pre);
        }
    }
//    public static void main(String[] args) {
//        BinarySearchTree tree=new BinarySearchTree();
//        int[] arr={2,3,4,5,6,7};
//        //ArrayList<Integer> arr=new ArrayList<>(Arrays.asList());
//        tree.root=tree.buildBST(arr,0,arr.length-1);
//        tree.inOrder(tree.root);
//        System.out.println();
//        //System.out.println();
//        //System.out.println(tree.minValueIterative(tree.root));
//        //System.out.println(tree.maxValueIterative(tree.root));
//        //System.out.println(tree.minRecursive(tree.root));
//        //System.out.println(tree.maxRecursive(tree.root));
//
////        System.out.println();
////        Node node=tree.deleteNode(tree.root,7);
////        tree.inOrder(node);
//        //System.out.println(kthLargest(tree.root,1));
//        //secondSmallest(tree.root,new int[1]);
//        //secondSGreatest(tree.root,new int[1]);
//        //insertRecursive(tree.root,1);
//        //levelOftheKey(tree.root,5);
//        //tree.inOrder(tree.root);
//        //System.out.println(tree.LeftSideViewRecursive(tree.root));
//        //System.out.println(heightOftheTree(tree.root));
////        int[] suc=new int[1];
////        int[] pre=new int[1];
////        inOrderSuccessor(tree.root,5,suc);
////        inOrderPredecessor(tree.root,5,pre);
////        System.out.println(suc[0]);
////        System.out.println(pre[0]);
//        //System.out.println(depth(tree.root.left.right,tree.root));
//        tree.delete(tree.root,5);
//        tree.inOrder(tree.root);
//
//    }
}
