
import java.awt.geom.Line2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ni3
 */
public class Arrow {
    Line2D line,arr1,arr2;
    public Arrow(double x1,double y1,double x2,double y2)
    {
        line =new Line2D.Double(x1, y1, x2, y2);
        //arr1=new Line2D.Double(x2, y2, x2-4, y2-4);
        
    }
    
    
}
