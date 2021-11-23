import java.util.*;

public class Node {
    
    Node parent;
    ArrayList<Node> children = new ArrayList<>();
    int value;

    public Node(int value,Node parent){
        this.parent = parent;
        this.value = value;
    }

    public void generateChildren(Node node){
       children.add(node);
    }
    
    public int getValue(){
        return value;
    }

    public Node getChildren(int index){
        if(children.get(index)==null)
            return null;
        return children.get(index);
    }

    public Node getParent(){
        return parent;
    }

    @Override
    public String toString(){
        return "Value is " + value;
    }
    
    @Override
    public boolean equals(Object o){
        if(this.value==((Node)o).getValue())
            return true;
        else
            return false;
    }

    public static void main(String args[]){
        Node a = new Node (80,null);
        Node temp=a;
        Queue<Node> queue = new LinkedList<Node>();
        boolean state=false;
        queue.add(temp);
        while(state!=true){
            temp = queue.poll();
            System.out.println(temp.getValue());
            temp.generateChildren(new Node(temp.getValue()+1,temp));
            temp.generateChildren(new Node(temp.getValue()+2,temp));
            temp.generateChildren(new Node(temp.getValue()+3,temp));
            for(int i=0;i<3;i++){
                queue.add(temp.getChildren(i));
            }
            if(temp.getValue()==100){
                state = true;
                System.out.println("SUCCESS");
            }
        }
        System.out.println("Path is: ");
        while(temp.getParent()!=null){
            System.out.print(temp.getValue() + " ") ;
            temp = temp.getParent();
        }
        System.out.print(temp.getValue() + " ") ;
        

    }
}
