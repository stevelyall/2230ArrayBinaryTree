import exceptions.ElementNotFoundException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBinaryTree<T> implements BinaryTreeADT {

    protected int size = 3;
    protected int root;
    protected T[] array;
    protected int modCount;


    public ArrayBinaryTree() {
        array = (T[]) new Object[size];
        root = 0;
    }


    public ArrayBinaryTree(T element) {
        this();
        array[0] = element;
    }


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

    @Override
    public Object getRootElement() {
        return array[root];
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

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
     * Complexity: O(1)
     * Precondition: An ArrayBinaryTree and Object to find have been instantiated.
     * Postcondition: The structure is unchanged.
     *
     * @param targetElement the element being sought in the tree
     *                      //     * @return true if the element exists in the tree, false otherwise
     * @author stevelyall patmcgee
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
     * @author stevelyall patmcgee
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
        throw new ElementNotFoundException("Element not found in tree");
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
        String str = "";
        int i = 0;
        while (array[i] != null) {
            str += "[" + i + "] = " + array[i] + "\n";
            i++;
        }
        return str;
    }

    @Override
    public Iterator iterator() {
        return iteratorLevelOrder();
    }

    @Override
    public Iterator iteratorInOrder() {
        return null;
    }

    @Override
    public Iterator iteratorPreOrder() {
        return null;
    }

    @Override
    public Iterator iteratorPostOrder() {
        return null;
    }

    @Override
    public Iterator iteratorLevelOrder() {
        return new ArrayBinaryTreeIterator();
    }

    class ArrayBinaryTreeIterator implements Iterator {
        private int iteratorModCount;
        private int currentIndex = 0;

        public ArrayBinaryTreeIterator() {
            iteratorModCount = modCount;
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
            if (hasNext())
                return (array[currentIndex++]);
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}