package Experiments;

public class DLLNode {

    public static void main(String[] args){
        //DLLNode myList= new DLLNode("This");
        //myList.append(new DLLNode("is"));
        //myList.append(new DLLNode("not"));
        //myList.append(new DLLNode("linked"));
        //myList.append(new DLLNode("list"));

        DLLNode myList = new DLLNode("This");
        myList.append(new DLLNode("_"));

        DLLNode myNode = new DLLNode("a");
        myList.append(myNode);

        myList.append(new DLLNode("linked"));
        myList.append(new DLLNode("list"));




        DLLNode myLastNode=new DLLNode("man!");
        myList.append(myLastNode);
        System.out.println(myNode.find("_"));

        //DLLNode foundNode = myList.find("not");
        //foundNode.setData("doubly");


        System.out.println(myLastNode);

    }

private Object data;
private DLLNode next;
private DLLNode previous;

public DLLNode(Object data) {this.data=data;}

public void setData(Object data) {this.data=data;}
public Object getData() {return this.data;}

public void append(DLLNode node)
{
    DLLNode tailNode = this;

    while (tailNode.next !=null)
    {
        tailNode=tailNode.next;
    }

    node.previous = tailNode;
    tailNode.next=node;
}
    public DLLNode find(Object searchNodeData)
    {
        DLLNode focusNode = this;
        while (focusNode != null)
        {
            if (focusNode.data ==searchNodeData)
            {
                return focusNode;
            } else {
                focusNode = focusNode.next;
            }
        }
        return null;
    }
public String toString()
{
    String str= "Doubly linked list: ";

    DLLNode focusNode=this;

    while (focusNode.previous !=null)
    {
        focusNode = focusNode.previous;
    }
    while (focusNode !=null)
    {
        str +=focusNode.data.toString();
        if (focusNode.next !=null)
        {
            str += " <=> ";
        }
        focusNode=focusNode.next;
    }
    return str;

}
}
