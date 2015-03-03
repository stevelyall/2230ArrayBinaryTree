import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayBinaryTreeTest {
    ArrayBinaryTree<String> t0 = new ArrayBinaryTree<String>();
    ArrayBinaryTree<String> t1 = new ArrayBinaryTree<String>("Element 1");
    ArrayBinaryTree<String> t2 = new ArrayBinaryTree<String>("Element 2");
    ArrayBinaryTree<String> t3 = new ArrayBinaryTree<String>("Element 3", t1, t2);

    @Test
    public void testSizeWithEmptyTree() throws Exception {
        assertEquals(0, t0.size());
    }

    @Test
    public void testSizeWithOneElement() throws Exception {
        assertEquals(1, t1.size());
    }

    @Test
    public void testSizeWithThreeElements() throws Exception {
        assertEquals(3, t3.size());
    }

    @Test
    public void testTreeContains() throws Exception {
        assertEquals(false, t2.contains("Hello World"));
        assertEquals(true, t1.contains("Element 1"));
        assertEquals(true, t3.contains("Element 2"));
    }

    @Test
    public void testFind() throws Exception {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<String>("Hello");
        assertEquals("Hello", tree.find("Hello"));
    }

    @Test
    public void testToStringEmptyTree() throws Exception {
        assertEquals("", t0.toString());
        System.out.println("testToStringEmptyTree " + t0.toString());
    }

    @Test
    public void testToStringSize1Tree() throws Exception {
        assertEquals("[0] = Element 1\n", t1.toString());

    }

    @Test
    public void testToStringSize3Tree() throws Exception {
        assertEquals("[0] = Element 3\n[1] = Element 1\n[2] = Element 2\n", t3.toString());

    }

    @Test
    public void testIsEmptyEmptyTree() throws Exception {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<String>();
        assertEquals(true, tree.isEmpty());
    }

    @Test
    public void testIsEmptyNonEmptyTree() throws Exception {
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<String>("Hello");
        assertEquals(false, tree.isEmpty());
    }

}