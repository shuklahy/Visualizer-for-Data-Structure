import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sagar
 */
public class GraphicalArrayStack 
{
    Rectangle2D sA[];
    String val[];
    Line2D t;
    int top,count,size;
    StackPanel s;
    public GraphicalArrayStack(int size,int width,int height)
    {
            this.size = size;
            sA=new Rectangle2D[size];
            val=new String[size];
            for(int i=0;i<size;i++)
            {
                val[i]=" ";
                sA[i]=new Rectangle2D.Double((50+(i*60)),(height/2)-30,60,60);
                top=0;
                count=0;
                
            }
            //h=new Line2D.Double(80,(height/2)-30, 80,(height/2)-50 );
            t=new Line2D.Double(80,(height/2)+30, 80,(height/2)+50 );
    }
    public String pop()
    {
        String temp;
        if(top==0)
        {
            System.out.println("Stack Empty");
            return null;
        }
        temp=val[top-1];
        val[top-1]=" ";
        top=top-1;
        count=count-1;
        return temp;
    }
    public int push(String obj)
    {
           if(top==size)
           {
               System.out.println("Stack Full");
               return -1;
           }
           val[top]=obj;
           top= top+1;
           count=count + 1;
           return 0;
    }
  
}