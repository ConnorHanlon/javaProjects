import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;


public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void setRoot(Node<K, V> newRoot) {
        root = newRoot;
    }

    public Node<K, V> getRoot() {
        return this.root;
    }

    /**
     * Determines the number of nodes in a tree with the input node
     * as the root of the tree.
     *
     * Pseudocode implemented from Data Structures and Abstractions with Java,
     * Chapter 24.10, written by Frank M. Carrano and Timothy M. Henry.
     *
     */
    public int getNumEntries(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        int leftNum = 0;
        int rightNum = 0;
        if (node.getLeft() != null) {
            leftNum = getNumEntries(node.getLeft());
        }
        if (node.getRight() != null) {
            rightNum = getNumEntries(node.getRight());
        }
        return 1 + leftNum + rightNum;
    }

    /**
     * Creates a node from the key and value inputs, and
     * adds the node to the tree by comparing the node's key
     * to the binary tree node keys.
     */
    public void add(K key, V value) {
        if (this.getRoot() == null) {
            setRoot(new Node(key, value));
        } else {
            boolean notFinished = true;
            Node<K, V> current = this.getRoot();
            while (notFinished) {
                if (key.compareTo(current.getKey()) == 0) {
                    current.setValue(value);
                    notFinished = false;
                } else if (key.compareTo(current.getKey()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(new Node(key, value));
                        notFinished = false;
                    } else {
                        current = current.getLeft();
                    }
                } else if (key.compareTo(current.getKey()) > 0) {
                    if (current.getRight() == null) {
                        current.setRight(new Node(key, value));
                        notFinished = false;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
    }

    /**
     * Finds a node within the tree
     * @param key the key of the node to be found.
     * @return the value associated with the node that was found.
     */
    public V find(K key) {
        Node<K, V> valNode = findNode(key)[0];
        if (valNode == null) {
            return null;
        } else {
            return valNode.getValue();
        }
    }

    /**
     * Finds a specific node within a tree.
     * @param key the key of the node to be found
     * @return if node is found, the node's parent and the node are returned in a Node list. Element zero of the
     * list is the found node, and element one is the parent node.
     */
    public Node<K, V>[] findNode(K key) {
        Node<K, V>[] ret = new Node[2];
        if (getRoot() == null) {
            return null;
        }
        boolean notFound = true;
        Node<K, V> current = getRoot();
        Node<K, V> previous = current;
        while (notFound) {
            if (key.compareTo(current.getKey()) == 0) {
                ret[0] = current;
                ret[1] = previous;
                notFound = false;
            } else if (key.compareTo(current.getKey()) < 0) {
                if (current.getLeft() == null) {
                    notFound = false;
                } else {
                    previous = current;
                    current = current.getLeft();
                }
            } else if (key.compareTo(current.getKey()) > 0) {
                if (current.getRight() == null) {
                    notFound = false;
                } else {
                    previous = current;
                    current = current.getRight();
                }
            }
        }
        return ret;
    }


    /**
     * Searches through the tree and finds the node to be removed. If found, the node to be removed is replaced with
     * another one in the tree in order to maintain the tree's ordering structure.
     * @param key the key of the node to be removed.
     */
    public void remove(K key) {
        Node<K, V>[] pR = findNode(key);
        Node<K, V> parent = pR[1];
        Node<K, V> toRemove = pR[0];
        if (parent != null && toRemove != null) {
            boolean right = false;
            if (parent.getRight() == toRemove) {
                right = true;
            }

            if (toRemove.getLeft() != null) {
                Node<K, V> current = toRemove.getLeft();
                if (current.getRight() == null) {
                    if (right) {
                        parent.setRight(current);
                        current.setRight(toRemove.getRight());
                    } else {
                        parent.setLeft(current);
                        current.setRight(toRemove.getRight());
                    }
                } else {
                    Node<K, V> previous = current;
                    while (current.getRight() != null) {
                        previous = current;
                        current = current.getRight();
                    }

                    current.setRight(toRemove.getRight());
                    current.setLeft(toRemove.getLeft());
                    previous.setRight(null);
                    if (toRemove == getRoot()) {
                        setRoot(current);
                    }else {
                        if (right) {
                            parent.setRight(current);

                        } else {
                            parent.setLeft(current);
                        }
                    }
                }
            } else {
                if (right) {
                    parent.setRight(toRemove.getRight());
                } else {
                    parent.setLeft(toRemove.getRight());
                }
            }
        }
    }


    //flatten must be revised, fails the remove test5 flatten and test2 contains subtree.
    @SuppressWarnings("unchecked")
    public V[] flatten() {
        BinaryTree<K, V> copy = this;
        V[] flat = (V[]) new Object[getNumEntries(copy.getRoot())];
        if (getRoot() == null) {
            return flat;
        }

        int counter = 0;
        while (copy.getRoot() != null) {
            Node<K, V> current = copy.getRoot();
            Node<K,V> previous = current;
            if (copy.getRoot().getLeft() != null) {
                while (current.getLeft().getLeft() != null) {
                    current = current.getLeft();
                }
                if (current.getLeft() != null) {
                    flat[counter] = current.getLeft().getValue();
                    current.setLeft(null);
                    counter++;
                }
                if (current.getRight() != null) {
                    while (current.getRight().getRight() != null) {
                        flat[counter] = current.getValue();
                        previous.setLeft(null);
                        previous = current;
                        current = current.getRight();
                    }

                    flat[counter] = current.getValue();
                    counter++;
                    if (current.getRight() != null) {
                        flat[counter] = current.getRight().getValue();
                        counter++;
                        current.setRight(null);
                    }
                    if (previous.getLeft() == current) {
                        previous.setLeft(null);
                    } else if (previous.getRight() == current) {
                        previous.setRight(null);
                    }
                    if (current == copy.getRoot()) {
                        copy.setRoot(null);
                    }
                }
            } else if(copy.getRoot().getLeft()==null) {
                if (copy.getRoot().getRight() != null) {
                    flat[counter] = copy.getRoot().getValue();
                    counter++;
                    this.setRoot(copy.getRoot().getRight());
                } else {
                    flat[counter] = copy.getRoot().getValue();
                    this.setRoot(null);
                }
            }
        }
        return flat;
    }


    /**
     * Creates a subtree of the main tree with a root of the other tree. Both trees are flattened and
     * compared to see whether the values in both are the same.
     *
     * @param other the subtree being checked whether it is in the current tree.
     * @return true if the main tree contains the subtree other.
     */
    public boolean containsSubtree(BinaryTree<K, V> other) {
        if (other.getRoot() == null) {
            return true;
        }
        BinaryTree<K, V> subTree = new BinaryTree<>(findNode(other.getRoot().getKey())[0]);
        boolean doesItContain = true;
        if (subTree.getRoot() != null) {
            V[] myTreeFlat = subTree.flatten();
            V[] otherTreeFlat = other.flatten();
            if (myTreeFlat.length == otherTreeFlat.length) {
                for (int i = 0; i < myTreeFlat.length; i++) {
                    if (myTreeFlat[i] != otherTreeFlat[i]) {
                        doesItContain = false;
                    }
                }
            } else {
                doesItContain = false;
            }
        } else {
            doesItContain = false;
        }
        return doesItContain;
    }
}
