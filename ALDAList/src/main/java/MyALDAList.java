// Author Nyamgarig Naranbaatar nyna2000
import java.util.*;

public class MyALDAList<E> implements ALDAList<E> {
    private int size;
    private Node<E> head;


    private static class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E element) {
            this.data = element;
        }

        public E getData() {
            return data;
        }
    }

    public void add(E element) {

        Node<E> tempNode = new Node<>(element);
        Node<E> currentNode = head;

        if (head == null) {
            head = tempNode;
        }

        if (currentNode != null) {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = tempNode;
        }
        size++;
    }

    public void add(int index, E element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (head == null) {
            add(element);
            return;
        } else {
            Node<E> tempNode = new Node<>(element);
            //tempNode.next = null;
            Node<E> temp = head;

            if (index == 0) {
                tempNode.next = temp;
                head = tempNode;
                size++;
                return;
            }

            for (int i = 0; i < index - 1 && temp.next != null; i++) {
                temp = temp.next;
            }

            tempNode.next = temp.next;
            temp.next = tempNode;
            size++;
        }
    }

    @Override
    public String toString() {
        if (head != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            Node<E> node = head;
            builder.append(node.getData());

            while (node.next != null) {
                builder.append(", ");
                node = node.next;
                if (node.next == null) {
                    builder.append(node.getData());
                    break;
                }
                builder.append(node.getData());
            }

            builder.append("]");
            return builder.toString();
        }

        return "[]";
    }

    public E remove(int index) {
        if (index < 0 || index > size - 1 || head == null) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = head;
        if (index == 0) {
            Node<E> removedNode = currentNode;
            head = head.next;
            size--;
            return removedNode.getData();

        } else {
            for (int i = 0; i < index - 1 && currentNode.next != null; i++) {
                currentNode = currentNode.next;

            }

        }

        Node<E> removedNode = currentNode.next;
        currentNode.next = currentNode.next.next;
        size--;
        return removedNode.getData();
    }

    public boolean remove(E element) {
        if (head == null)
            return false;

        Node<E> current = head;



        if (current.getData().equals(element)) {
            head = head.next;
            size--;
            return true;
        } else {
            for (int i = 0; i < size && current.next != null; i++) {
                if (current.next.getData().equals(element)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                current = current.next;
            }

        }

        return false;

    }

    public E get(int index) {
        if (index < 0 || index > size - 1 || size == 0 || head == null)
            throw new IndexOutOfBoundsException();


        Node<E> currentNode;
        currentNode = head;


        for (int i = 0; i < index; i++) {
            if (currentNode.next == null)
                return null;
            currentNode = currentNode.next;
        }

        return currentNode.getData();


    }

    public boolean contains(E element) {
        Node<E> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.getData().equals(element)) {
                return true;
            }

            temp = temp.next;
        }
        return false;
    }

    public int indexOf(E element) {
        Node<E> temp = head;
        int index = 0;
        for (int i = 0; i < size && temp.next != null; i++) {
            if (temp.getData().equals(element)) {
                return index;
            }
            index++;
            temp = temp.next;
        }

        if (!temp.getData().equals(element))
            return -1;
        return index;
    }

    public void clear() {
        size = 0;
        head = null;
    }

    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = null;
        private boolean okToRemove = false;

        public boolean hasNext() {
            if (current == null && head != null) {
                return true;
            } else if (current != null) {
                return current.next != null;
            }
            return false;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if (current == null && head != null) {
                current = head;
                okToRemove = true;
                return head.getData();
            } else if (current != null) {

                current = current.next;
                okToRemove = true;
                return current.getData();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (!okToRemove)
                throw new IllegalStateException();

            MyALDAList.this.remove(current.data);
            okToRemove = false;
        }
    }

}
