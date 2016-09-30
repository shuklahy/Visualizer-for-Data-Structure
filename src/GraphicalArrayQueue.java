
import java.awt.geom.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ni3
 */
public class GraphicalArrayQueue {
    
    Rectangle2D qA[];
    String val[];
    Line2D h,t;
    int head,tail,count,size;
    public GraphicalArrayQueue(int size,int width,int height)
    {
            this.size = size;
            qA=new Rectangle2D[size];
            val=new String[size];
            for(int i=0;i<size;i++)
            {
                val[i]=" ";
                qA[i]=new Rectangle2D.Double((50+(i*60)),(height/2)-30,60,60);
                head=0;
                tail=0;
                count=0;
                
            }
            h=new Line2D.Double(80,(height/2)-30, 80,(height/2)-50 );
            t=new Line2D.Double(80,(height/2)+30, 80,(height/2)+50 );
            
    }
    public String dequeue()
    {
        String temp;
        if(count==0)
        {
            System.out.println("Queue Empty");
            return null;
        }
        temp=val[head];
        val[head]=" ";
        head=(head+1)%size;
        count=count-1;
        return temp;
     }
    public int enqueue(String obj)
    {
           if(count==size)
           {
               System.out.println("Queue Full");
               return -1;
           }
           val[tail]=obj;
           tail= (tail+1) % size;
           count=count + 1;
           return 0;
    }
}
