import exceptions.ElementNotFoundException;

import java.util.Iterator;

public class ArrayBinaryTreeDriver {

    public static void main(String[] args) {
        System.out.println("Creating empty tree...");
        ArrayBinaryTree<String> t0 = new ArrayBinaryTree<String>();
        System.out.println("Creating two trees with one element...");
        ArrayBinaryTree<String> t1 = new ArrayBinaryTree<String>("Element 1");
        ArrayBinaryTree<String> t2 = new ArrayBinaryTree<String>("Element 2");
        System.out.println("Creating new tree from two subtrees...");
        ArrayBinaryTree<String> t3 = new ArrayBinaryTree<String>("Element 3", t1, t2);

        // Empty tree
        System.out.println("Empty tree is empty? " + t0.isEmpty());
        System.out.println("Empty tree has size 0? " + t0.size());
        System.out.println("Print empty tree:\n" + t0.toString());

        // Tree with one element
        System.out.println("Tree with one element not empty? " + t1.isEmpty());
        System.out.println("Tree with one element has size 1? " + t1.size());
        System.out.println("print tree with one element:\n" + t1.toString());

        // Tree with 3 elements
        System.out.println("Tree with three elements not empty? " + t3.isEmpty());
        System.out.println("Tree with three elements has size 3? " + t3.size());
        System.out.println("Print tree with three elements:\n" + t3.toString());
        System.out.println("Root element is element 3? " + t3.getRootElement());
        System.out.println("Contains \"Element 2\"? " + t3.contains("Element 2"));
        System.out.println("Contains \"foo\"? " + t3.contains("foo"));
        System.out.println("Find \"Element\" 1? " + t3.find("Element 1"));
        try {
            System.out.println("Find \"foo\"? " + t3.find("foo"));
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        //Level order iterator
        System.out.println("Iterator for level order. Should print elements 3, 1, 2");
        Iterator iter = t3.iteratorLevelOrder();
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());

        System.out.println();

        //Inorder iterator
        System.out.println("Iterator for inorder. Should print elements 1, 3, 2");
        iter = t3.iteratorInOrder();
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());

        System.out.println();

        //Preorder iterator
        System.out.println("Iterator for preorder. Should print elements 3, 1, 2");
        iter = t3.iteratorPreOrder();
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());

        System.out.println();

        //Postorder iterator
        System.out.println("Iterator for preorder. Should print elements 1, 2, 3");
        iter = t3.iteratorPostOrder();
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());
        System.out.println(iter.next());
        System.out.println("hasNext? " + iter.hasNext());

    }
}
