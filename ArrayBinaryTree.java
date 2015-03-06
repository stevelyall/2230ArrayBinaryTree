import exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBinaryTree<T> implements BinaryTreeADT {

    protected int size = 3;
    protected int root;
    protected T[] array;
    protected int modCount;


    /**
     * Constructs a new empty tree.
     */
    public ArrayBinaryTree() {
        array = (T[]) new Object[size];
        root = 0;
    }

    /**
     * Constructs a new tree with a singe element at the root.
     *
     * @param element the new root element
     */
    public ArrayBinaryTree(T element) {
        this();
        array[0] = element;
    }


    /**
     * Constructs a new ArrayBinaryTree from an element and two subtrees.
     *  Complexity: O(n)
     *  Precondition: None, other than having an element T and two ArrayBinaryTree objects instantiated.
     *  Postcondition: A new ArrayBinaryTree object has been created.
     * @author patmcgee stevelyall
     * @param element the new root element
     * @param leftChild left subtree
     * @param rightChild right subtree
     */
    public ArrayBinaryTree(T element, ArrayBinaryTree leftChild, ArrayBinaryTree rightChild) {
        array = (T[]) (new Object[(int) (Math.pow(2, size))]);

        int n = leftChild.size;
        int z = 1, t1 = 0, t2 = 0;
        array[0] = element;

        for (int i = 0; i < Math.log(n); i++) {

            for (int j = 0; j < Math.pow(2, i); j++) {
                array[z++] = (T) leftChild.array[t1++];

            }

            for (int k = 0; k < Math.pow(2, i); k++) {
                array[z++] = (T) rightChild.array[t2++];
            }
        }
    }

    /**
     * Returns the root element of the tree.
     *  Complexity: O(1)
     *  Precondition: The ArrayBinaryTree object exists, and the root is non-null.
     *  Postcondition: The structure is unchanged.
     * @author stevelyall patmcgee
     * @return the root element
     */
    @Override
    public Object getRootElement() {
        return array[root];
    }

    /**
     * Gets the left child of a given node.
     *
     * @param i the index of the current node
     * @return the left child of the node
     */
    public int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Gets the right child of a given node.
     *
     * @param i the index of the current node
     * @return the right child of the node
     */
    public int getRightChild(int i) {
        return 2 * (i + 1);
    }

    /**
     * Returns whether the tree is empty of not.
     *  Complexity: O(1)
     *  Precondition: The ArrayBinaryTree object has been instantiated.
     *  Postcondition: The structure is unchanged.
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Determines the number of nodes in the tree.
     *  Complexity: O(n)
     *  Precondition: The ArrayBinaryTree object has been instantiated.
     *  Postcondition: The structure is unchanged.
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        int i = 0;
        int count = 0;
        while (i < array.length) {
            if (array[i] != null) {
                count++;
            }
            i++;
        }
        return count;
    }

    /**
     * Determines whether or not an item exists in the tree. Calls find() and returns a boolean.
     *  Complexity: O(1)
     *  Precondition: An ArrayBinaryTree and Object to find have been instantiated.
     *  Postcondition: The structure is unchanged.
     * @param targetElement the element being sought in the tree
     * @return true if the element exists in the tree, false otherwise
     *
     */
    @Override
    public boolean contains(Object targetElement) {
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    /**
     * Attempts to locate an item in the tree.
     *  Complexity: O(n)
     *  Precondition: An ArrayBinaryTree and Object to find have been instantiated.
     *  Postcondition: The structure is unchanged.
     * @param targetElement the element to search for in the tree
     * @throws ElementNotFoundException if the element is not found
     * @return the element found in the array
     */
    @Override
    public Object find(Object targetElement) {
        for (T element : array) {
            if (element != null && element.equals(targetElement)) {
                return element;
            }
        }
        throw new ElementNotFoundException("tree");
    }


    /**
     * Builds a string representation of the tree.
     *  Complexity: O(n)
     *  Precondition: An ArrayBinaryTree has been instantiated.
     *  Postcondition: The structure itself is unchanged.
     * @author patmcgee stevelyall
     * @return a string representing the contents of the tree in level-order
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "<Tree is empty>";
        }
        String str = "";
        int i = 0;
        while (array[i] != null) {
            str += "[" + i + "] = " + array[i] + "\n";
            i++;
        }
        return str;
    }


    /**
     * Returns an iterator for the current tree.
     * @return a new iterator
     */
    @Override
    public Iterator iterator() {

        return iteratorLevelOrder();
    }

    @Override
    public Iterator iteratorInOrder() {
        return new ArrayBinaryTreeIteratorInorder();
    }


    @Override
    public Iterator iteratorPreOrder() {
        return new ArrayBinaryTreeIteratorPreorder();
    }

    @Override
    public Iterator iteratorPostOrder() {
        return new ArrayBinaryTreeIteratorPostorder();
    }

    @Override
    public Iterator iteratorLevelOrder() {
        return new ArrayBinaryTreeIteratorLevelOrder();
    }

    abstract class ArrayBinaryTreeIterator implements Iterator {
        protected int iteratorModCount;
        protected int currentIndex = 0;

        public ArrayBinaryTreeIterator() {
            iteratorModCount = modCount;

        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    class ArrayBinaryTreeIteratorLevelOrder extends ArrayBinaryTreeIterator {

        public ArrayBinaryTreeIteratorLevelOrder() {
            super();
            currentIndex = root;
        }

        @Override
        public boolean hasNext() {
            if (!(modCount == iteratorModCount)) {
                throw new ConcurrentModificationException();
            }
            return (array[currentIndex] != null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (array[currentIndex++]);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    class ArrayBinaryTreeIteratorInorder extends ArrayBinaryTreeIterator {
        public ArrayList<T> traversalOrder;

        public ArrayBinaryTreeIteratorInorder() {
            super();
            traversalOrder = new ArrayList<T>();
            traverseInorder(root);
        }

        @Override
        public boolean hasNext() {
            if (!(modCount == iteratorModCount)) {
                throw new ConcurrentModificationException();
            }
            if (currentIndex == traversalOrder.size()) {
                return false;
            }
            return (traversalOrder.get(currentIndex) != null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return traversalOrder.get(currentIndex++);
            } else {
                throw new NoSuchElementException();
            }
        }

        /**
         * Performs an inorder traversal recursively.
         *
         * @param i root index
         */
        public void traverseInorder(int i) {
            // go left
            if (array[getLeftChild(i)] != null) {
                traverseInorder(getLeftChild(i));
            }
            // parent
            if (array[i] != null) {
                traversalOrder.add(array[i]);
            }
            // go right
            if (array[getRightChild(i)] != null) {
                traverseInorder(getRightChild(i));
            }

        }
    }

    class ArrayBinaryTreeIteratorPreorder extends ArrayBinaryTreeIterator {
        public ArrayList<T> traversalOrder;

        public ArrayBinaryTreeIteratorPreorder() {
            super();
            traversalOrder = new ArrayList<T>();
            traversePreorder(root);
        }

        @Override
        public boolean hasNext() {
            if (!(modCount == iteratorModCount)) {
                throw new ConcurrentModificationException();
            }
            if (currentIndex == traversalOrder.size()) {
                return false;
            }
            return (traversalOrder.get(currentIndex) != null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return traversalOrder.get(currentIndex++);
            } else {
                throw new NoSuchElementException();
            }
        }


        /**
         * Performs a preorder traversal recursively
         *
         * @param i root index
         */
        public void traversePreorder(int i) {
            // parent
            if (array[i] != null) {
                traversalOrder.add(array[i]);
            }

            // go left
            if (array[getLeftChild(i)] != null) {
                traversePreorder(getLeftChild(i));
            }

            // go right
            if (array[getRightChild(i)] != null) {
                traversePreorder(getRightChild(i));
            }

        }
    }

    class ArrayBinaryTreeIteratorPostorder extends ArrayBinaryTreeIterator {
        public ArrayList<T> traversalOrder;

        public ArrayBinaryTreeIteratorPostorder() {
            super();
            traversalOrder = new ArrayList<T>();
            traversePostorder(root);
        }

        @Override
        public boolean hasNext() {
            if (!(modCount == iteratorModCount)) {
                throw new ConcurrentModificationException();
            }
            if (currentIndex == traversalOrder.size()) {
                return false;
            }
            return (traversalOrder.get(currentIndex) != null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return traversalOrder.get(currentIndex++);
            } else {
                throw new NoSuchElementException();
            }
        }

        /**
         * Performs a postorder traversal recursively
         *
         * @param i root index
         */
        public void traversePostorder(int i) {
            // go left
            if (array[getLeftChild(i)] != null) {
                traversePostorder(getLeftChild(i));
            }

            // go right
            if (array[getRightChild(i)] != null) {
                traversePostorder(getRightChild(i));
            }
            // parent
            if (array[i] != null) {
                traversalOrder.add(array[i]);
            }
        }
    }
}