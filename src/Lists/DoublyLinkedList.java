package Lists;



public class DoublyLinkedList
{

    public static void main(String[] args)
    {

        DoublyLinkedList myList=new DoublyLinkedList();


        myList.append(new DoublyLinkedList.Node("c"));
        myList.append(new DoublyLinkedList.Node("d"));
        myList.append(new DoublyLinkedList.Node("e"));
        myList.append(new DoublyLinkedList.Node("f"));
        myList.append(new DoublyLinkedList.Node("g"));

        myList.prepend(new DoublyLinkedList.Node("b"));
        myList.prepend(new DoublyLinkedList.Node("a"));


        System.out.println(myList.toString());
        myList.insertbefore(myList.find("d"), new DoublyLinkedList.Node("XX"));
        System.out.println(myList.toStringReverse());



        myList.insertbefore(myList.find("e"), new DoublyLinkedList.Node("+"));
        System.out.println(myList.toString());



        System.out.println(myList.toStringReverse());
        myList.insertbefore(myList.find("f"), new DoublyLinkedList.Node("p"));
        System.out.println(myList.toString());
        //myList.insertbefore(myList.find("d"), new DoublyLinkedList.Node("o"));
        //System.out.println(myList.toString());

        myList.Remove(myList.find("p"));
        System.out.println(myList.toString());
        System.out.println(myList.toStringReverse());
        myList.insertafter(myList.find("e"), new DoublyLinkedList.Node("Q"));
        System.out.println(myList.toString());
        myList.Remove(myList.find("f"));
        System.out.println(myList.toStringReverse());
//        myList.insertFirst(myList.find("a"), new DoublyLinkedList.Node("M"));
//        System.out.println(myList.toString());
    }
    Node head, tail;

    public static class Node{
        Object data;
        Node next;
        Node previous;

        public Node(Object data) { this.data=data;}

        public Object getData() {return data;}

        public void setData(Object data){this.data=data;}

        public Node getNext() {return next;}

        public Node getPrevious() {return previous;}


    }

    public void prepend(Node node)
    {
        if (this.head == null || this.tail ==null)
        {
            this.head = node;
            this.tail = node;
        } else {
            node.next =this.head;
            this.head.previous = node;
            node.previous=null;
            this.head = node;

        }


    }

    public void append(Node node)
    {

        if (this.head == null || this.tail ==null)
        {
            this.head = node;
            this.tail = node;
        } else  {
            this.tail.next = node;
            node.previous = this.tail;
            this.tail = node;
        }
    }

    public Node find(Object data)
    {
        Node focusNode = this.head;

        while (focusNode != null)
        {
            if (focusNode.data == data)
            {
                return focusNode;
            }
            focusNode = focusNode.next;

        }
        return null;
    }

    public void insertafter (Node nodeBefore, Node nodeAfter)
    {   if (nodeBefore == null)
    {
        append(nodeBefore);
        return;
    }

    if (this.tail == nodeBefore || this.head==nodeAfter)
        {
            this.tail = nodeAfter;
            this.head = nodeBefore;
        }

        nodeBefore.previous.next = nodeAfter;
        nodeAfter.previous= nodeBefore.previous;

        nodeAfter.next=nodeBefore;
        nodeBefore.previous=nodeAfter;



    //nodeAfter.next = nodeBefore.next;
        //nodeBefore.next = nodeAfter;
        //nodeBefore.previous = nodeAfter.previous;
        //nodeAfter.previous = nodeBefore;
    }

//    public void insertFirst (Node nodeBefore, Node nodeAfter)
//    {
//        if (nodeAfter ==null) {
//            prepend(nodeAfter);
//
//            return;
//        }
//
//        if (this.head == nodeAfter)
//        {
//            this.head=nodeBefore;
//        }
//        nodeAfter.previous=nodeBefore;
//
//    }

    public void insertbefore (Node nodeAfter, Node nodeBefore)
    {
       if (nodeAfter == null)
       {
           prepend(nodeAfter);
           return;
       }
       if (this.tail == nodeBefore)
       {
           this.tail = nodeAfter;
       }

       nodeAfter.previous.next = nodeBefore;
       nodeBefore.previous= nodeAfter.previous;

       nodeBefore.next=nodeAfter;
       nodeAfter.previous=nodeBefore;



        //nodeBefore.next = nodeAfter.next;
        //nodeAfter.next = nodeBefore;
        //nodeAfter.previous = nodeBefore.previous;
        //nodeBefore.previous = nodeAfter;
    }


    public void Remove (Node node)
    {
        //if (node == this.head){
            //this.head = node.next;
            //this.head.previous.next=null;
            //this.head.previous=null;
        //}

        //if (node == this.tail){
            //this.tail = node;
            //this.tail.next.previous=null;
            //this.tail.next=null;
        //}

        if (this.head == null || node == null) {
            return;
        }

        if (this.tail == null || node == null) {
            return;
        }

        // If node to be deleted is head node
        if (this.head == node) {
            this.head = node.next;
        }
        if (this.tail == node) {
            this.tail = node.previous;
        }

        // Change next only if node to be deleted
        // is NOT the last node
        if (node.next != null) {
            node.next.previous = node.previous;
        }

        // Change prev only if node to be deleted
        // is NOT the first node
        if (node.previous != null) {
            node.previous.next = node.next;
        }

        // Finally, free the memory occupied by del
        return;



        //node.previous.next=node.next;
        //node.next.previous=node.previous;
        //node.previous=null;
        //node.next=null;

        //Node focusNode =this.head;
        //Node previousNode=null;




        }


    public String toString()
    {
        Node focusNode = this.head;
        String str = "Linked List ";
        while (focusNode.previous !=null)
        {
            focusNode = focusNode.previous;
        }

        while (focusNode!= null) {
            str += focusNode.data.toString();
            if (focusNode.next !=null)
            {
                str += "<=>";
            }
            focusNode = focusNode.next;
        }
        return str;
    }
public String toStringReverse()
{
    Node focusNode = this.tail;
    String strr = "Reversed Linked List ";
    while (focusNode.next !=null)
    {
        focusNode = focusNode.next;
    }

    while (focusNode!= null) {
        strr += focusNode.data.toString();
        if (focusNode.previous !=null)
        {
            strr += "<=>";
        }
        focusNode = focusNode.previous;
    }
    return strr;


}

}
