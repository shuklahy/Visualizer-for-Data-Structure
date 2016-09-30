
import com.sun.istack.internal.logging.Logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import javax.swing.JComponent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ni3
 */
class DoublyCircular extends JComponent{
        SingleLinkedList list;
        int operation ;
        int temp_x, temp_y, cur_x, cur_y, final_x, final_y,data,last_cur_x, last_cur_y;
        int initial_x, initial_y;
        int width, height;
        private void drawArrow(Graphics g,int x,int y, int flag){
            // 1 - Right 2- bottom 3- left 4- Up
            if(flag == 1){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x-3, y+3);
            }else if(flag == 2){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x+3, y-3);
            }else if(flag == 3){
                g.drawLine(x, y, x+3, y-3);
                g.drawLine(x, y, x+3, y+3);
            }else if(flag == 4){
                g.drawLine(x, y, x+3, y+3);
                g.drawLine(x, y, x-3, y+3);
            }else{
                
            }
                
        }
       private void drawNode(Graphics g,int x, int y,String s){
             
           g.drawRect(x, y, 10, 30); 
           g.drawRect(x+10, y, 30, 30);
            g.drawRect(x+40, y, 10, 30);
            g.drawString(s, x+15, y+20);
     }
      private void drawHead(Graphics g, int x, int y){
          g.setColor(Color.RED);
          g.drawLine(x+25, y, x+25, y-15);
          drawArrow(g,x+25, y, 2);
          g.drawString("HEAD", x+10, y-20);
          g.setColor(Color.BLACK);
          
      }
      private void drawTail(Graphics g, int x, int y){
          g.setColor(Color.RED);
          g.drawLine(x+25, y+30, x+25, y+45);
          drawArrow(g,x+25, y+30, 4);
          g.drawString("TAIL", x+10, y+50);
          g.setColor(Color.BLACK);
      }
     private void drawNull(Graphics g,int x, int y, int flag){
         // 1 - rigth
         // 2 - left
         if(flag == 1)
         {
         g.drawLine(x, y+15, x+20, y+15);
         g.drawLine(x+20, y+15, x+20, y+30);
         g.drawLine(x+15, y+30, x+25, y+30);
         }
         else{
           g.drawLine(x,y+15,x-15,y+15);
           g.drawLine(x-15,y+15, x-15, y+30);
           g.drawLine(x-10, y+30, x-20, y+30);
         }
      }
        private void drawLastPointer(Graphics g,int x, int y){
            
                if(y == 70){
                    g.drawLine(x, y+15, x+20, y+15);
                    g.drawLine(x+20, y+15, x+20, y-10);
                    g.drawLine(x+20, y-10, initial_x+20, initial_y-10);
                    g.drawLine(initial_x+20, initial_y-10, initial_x+20, initial_y);
                    
                }else{
                    g.drawLine(x, y+15, x+20, y+15);
                    g.drawLine(x+20, y+15, x+20, y+40);
                    g.drawLine(x+20, y+40, 7, y+40);
                    g.drawLine(7, y+40, 7, 60);
                    g.drawLine(7, 60, initial_x+20, 60);
                    g.drawLine(initial_x+20, 60, initial_x+20, initial_y);
                }
                drawArrow(g, initial_x+20, initial_y, 2);
            
           }
        public void setValues(SingleLinkedList list,int operation)
       {
          this.list = list;
          this.operation = operation;
          repaint();
       }
        @Override
        public void paintComponent(Graphics g) 
        {
          
          drawList(g);
        }
         public void drawList(Graphics g){
          int i = 1;
          height = this.getHeight();
          width = this.getWidth();
          
         int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
           final_x = cur_x + 70;
           final_y = cur_y;
          Node temp = this.list.first;
          drawHead(g, 90, 70); 
          g.setColor(Color.BLACK);
          while(temp != null){
              
              if( cur_x+150 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 70;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(i == 1){
                 
                 
                 g.setColor(Color.BLACK);
                 
                drawNull(g, final_x+3, final_y, 2);
                i = 0;
              }
              if(changed == true){
                g.drawLine(cur_x+45, cur_y+10, cur_x+80, cur_y+10);
                g.drawLine(cur_x+80, cur_y+10, cur_x+80, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+80, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+10);
                g.drawLine(10, final_y+10, final_x, final_y+10);
                drawArrow(g, final_x, final_y+10, 1);
                
                g.drawLine(cur_x+50, cur_y+20, cur_x+70, cur_y+20);
                g.drawLine(cur_x+70, cur_y+20, cur_x+70, cur_y+(30+incr_distance/2)-5);
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2)-5, 7, cur_y+(30+incr_distance/2)-5);
                g.drawLine(7, cur_y+(30+incr_distance/2)-5, 7, final_y+20);
                g.drawLine(7, final_y+20, final_x+5, final_y+20);
                drawArrow(g, cur_x+50, cur_y+20, 3);
                
                changed = false;
              }
              else{
                  if(final_x != 90)
                  {
                  g.drawLine(cur_x+45, cur_y+10, final_x, final_y+10);
                  drawArrow(g, final_x, final_y+10, 1);
                  g.drawLine(cur_x+50, cur_y+20, final_x+5, final_y+20); 
                  drawArrow(g, cur_x+50, cur_y+20, 3);
                  }
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
              
          }
          drawTail(g, final_x, final_y);
          if(i != 1){
          drawNull(g, final_x+47, final_y, 1);
          }
    
         }
}
class DoublyNull extends JComponent
    {
       SingleLinkedList list ;
       int operation , interX, interY;
       int temp_x, temp_y, cur_x, cur_y, final_x, final_y,data,last_cur_x, last_cur_y;
       int width, height;
        
    private void drawArrow(Graphics g,int x,int y, int flag){
            // 1 - Right 2- bottom 3- left 4- Up
            if(flag == 1){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x-3, y+3);
            }else if(flag == 2){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x+3, y-3);
            }else if(flag == 3){
                g.drawLine(x, y, x+3, y-3);
                g.drawLine(x, y, x+3, y+3);
            }else if(flag == 4){
                g.drawLine(x, y, x+3, y+3);
                g.drawLine(x, y, x-3, y+3);
            }else{
                
            }
                
        }
      private void drawNode(Graphics g,int x, int y,String s){
             
           g.drawRect(x, y, 10, 30); 
           g.drawRect(x+10, y, 30, 30);
            g.drawRect(x+40, y, 10, 30);
            g.drawString(s, x+15, y+20);
     }
      private void drawHead(Graphics g, int x, int y){
          g.setColor(Color.RED);
          g.drawLine(x+25, y, x+25, y-15);
          drawArrow(g,x+25, y, 2);
          g.drawString("HEAD", x+10, y-20);
          g.setColor(Color.BLACK);
          
      }
      private void drawTail(Graphics g, int x, int y){
          g.setColor(Color.RED);
          g.drawLine(x+25, y+30, x+25, y+45);
          drawArrow(g,x+25, y+30, 4);
          g.drawString("TAIL", x+10, y+50);
          g.setColor(Color.BLACK);
      }
     private void drawNull(Graphics g,int x, int y, int flag){
         // 1 - rigth
         // 2 - left
         if(flag == 1)
         {
         g.drawLine(x, y+15, x+20, y+15);
         g.drawLine(x+20, y+15, x+20, y+30);
         g.drawLine(x+15, y+30, x+25, y+30);
         }
         else{
           g.drawLine(x,y+15,x-15,y+15);
           g.drawLine(x-15,y+15, x-15, y+30);
           g.drawLine(x-10, y+30, x-20, y+30);
         }
      }
private void drawAnimation(){
         // Finding intermediate points by breshnam line
         int i = 0;
         int dx, dy, p, x, y;
         int prevx , prevy;
         this.operation = 1;
         
         temp_x = 20;
         temp_y = 20;
         cur_x = last_cur_x;
         cur_y = last_cur_y;
         dx = 60 +final_x - temp_x;
         dy = final_y - temp_y;
         
         p = 2 * (dy) - (dx);
         x = temp_x;
         y = temp_y;
         while(x <= final_x || y <= final_y)
        {
              
            if(p < 0)
            {
                x=x+1;
                p = p + 2 * (dy);
            }
            else
            {
                x=x+1;
                y=y+1;
                p = p + 2 * (dy - dx);
            }
           System.out.println("x="+x+"    y="+y);
           if(x % 4 == 0)
           {         
                prevx  = x;
                prevy = y;
                interX = x;
           interY = y;
           
             try {
                 Thread.sleep(30);
             } catch (InterruptedException ex) {
                // Logger.getLogger(listPanel.class.getName()).log(Level.SEVERE, null, ex);
             }
             repaint();                      
           }     
           
       }
           this.operation = 0; 

     }
     
      void drawInterPath(Graphics g)
      {
          int i = 1;
          height = this.getHeight();
          width = this.getWidth();
          int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
          final_x = cur_x + 70;
          final_y = cur_y;
          Node temp = this.list.first;
          drawHead(g, 90, 70); 
          g.setColor(Color.BLACK);
          while(temp != null && temp.next != null){
              // remove null in list
                
                 
              if( cur_x+150 > width)
              {
                  final_x = 90;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 70;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(i == 1){
                 
                 
                 g.setColor(Color.BLACK);
                 
                drawNull(g, final_x+3, final_y, 2);
                i = 0;
              }
              if(changed == true){
                g.drawLine(cur_x+45, cur_y+10, cur_x+80, cur_y+10);
                g.drawLine(cur_x+80, cur_y+10, cur_x+80, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+80, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+10);
                g.drawLine(10, final_y+10, final_x, final_y+10);
                drawArrow(g, final_x, final_y+10, 1);
                
                g.drawLine(cur_x+50, cur_y+20, cur_x+70, cur_y+20);
                g.drawLine(cur_x+70, cur_y+20, cur_x+70, cur_y+(30+incr_distance/2)-5);
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2)-5, 7, cur_y+(30+incr_distance/2)-5);
                g.drawLine(7, cur_y+(30+incr_distance/2)-5, 7, final_y+20);
                g.drawLine(7, final_y+20, final_x+5, final_y+20);
                drawArrow(g, cur_x+50, cur_y+20, 3);
                
                changed = false;
              }
              else{
                  if(final_x != 90)
                  {
                    g.drawLine(cur_x+45, cur_y+10, final_x, final_y+10);
                    drawArrow(g, final_x, final_y+10, 1);
                    g.drawLine(cur_x+50, cur_y+20, final_x+5, final_y+20); 
                    drawArrow(g, cur_x+50, cur_y+20, 3);
                  }
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
              
          }
          drawTail(g, final_x, final_y);
          if(i != 1)
          drawNull(g, final_x+47, final_y, 1);
          
          drawNode(g, interX, interY, ""+temp.data);
          
          g.drawLine(cur_x+45, cur_y+10, interX, interY+10);
          drawArrow(g, interX, interY+10, 1);
          g.drawLine(cur_x+50, cur_y+20, interX+5, interY+20); 
          drawArrow(g, cur_x+50, cur_y+20, 3);
       
      } 
      public void drawList(Graphics g){
          int i = 1;
          height = this.getHeight();
          width = this.getWidth();
          
        //  int cur_x, cur_y, step_height, temp_x, temp_y, final_x, final_y;
         int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
           final_x = cur_x + 70;
                  final_y = cur_y;
          Node temp = this.list.first;
          //drawHead(g, cur_x, cur_y);
         // drawTail(g, cur_x, cur_y);
          drawHead(g, 90, 70); 
          g.setColor(Color.BLACK);
          while(temp != null){
             
              
              
              // remove null in list
                
              
              
              if( cur_x+150 > width)
              {
                  final_x = 90;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 70;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(i == 1){
                 
                 
                 g.setColor(Color.BLACK);
                 
                drawNull(g, final_x+3, final_y, 2);
                i = 0;
              }
              if(changed == true){
                g.drawLine(cur_x+45, cur_y+10, cur_x+80, cur_y+10);
                g.drawLine(cur_x+80, cur_y+10, cur_x+80, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+80, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+10);
                g.drawLine(10, final_y+10, final_x, final_y+10);
                drawArrow(g, final_x, final_y+10, 1);
                
                g.drawLine(cur_x+50, cur_y+20, cur_x+70, cur_y+20);
                g.drawLine(cur_x+70, cur_y+20, cur_x+70, cur_y+(30+incr_distance/2)-5);
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2)-5, 7, cur_y+(30+incr_distance/2)-5);
                g.drawLine(7, cur_y+(30+incr_distance/2)-5, 7, final_y+20);
                g.drawLine(7, final_y+20, final_x+5, final_y+20);
                drawArrow(g, cur_x+50, cur_y+20, 3);
                
                changed = false;
              }
              else{
                  if(final_x != 90)
                  {
                  g.drawLine(cur_x+45, cur_y+10, final_x, final_y+10);
                  drawArrow(g, final_x, final_y+10, 1);
                  g.drawLine(cur_x+50, cur_y+20, final_x+5, final_y+20); 
                  drawArrow(g, cur_x+50, cur_y+20, 3);
                  }
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
              
          }
          drawTail(g, final_x, final_y);
          if(i != 1)
          drawNull(g, final_x+47, final_y, 1);
              
          
      }
      
       public void setValues(SingleLinkedList list,int operation)
       {
          this.list = list;
          this.operation = operation;
          if(this.operation == 1)
          {
                Thread t1 = new Thread(new Runnable()
                {
                    public void run()
                    {
                              drawAnimation();              
                    }
                });
                t1.start();
       
          }
          repaint();
       }
       
     
        @Override
      public void paintComponent(Graphics g) 
      {
          System.out.println("Painted!!!!"+this.operation);
          
          
          if(this.operation == 1){
              System.out.println("Insert operation performed");
              drawInterPath(g);
          }
          else
          {
            drawList(g);
          }
      }
 
    }

class SinglyCircular extends JComponent{
        SingleLinkedList list;
        int operation ;
        int temp_x, temp_y, cur_x, cur_y, final_x, final_y,data,last_cur_x, last_cur_y;
        int initial_x, initial_y, interX, interY;
        int width, height;
        private void drawArrow(Graphics g,int x,int y, int flag){
            // 1 - Right 2- bottom 3- left 4- Up
            if(flag == 1){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x-3, y+3);
            }else if(flag == 2){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x+3, y-3);
            }else if(flag == 3){
                g.drawLine(x, y, x+3, y-3);
                g.drawLine(x, y, x+3, y+3);
            }else if(flag == 4){
                g.drawLine(x, y, x+3, y+3);
                g.drawLine(x, y, x-3, y+3);
            }else{
                
            }
                
        }
        private void drawFirstNode(Graphics g,int x, int y,String s){
             
            g.drawRect(x, y, 30, 30);
            g.drawRect(x+30, y, 10, 30);
            g.drawString(s, x+5, y+20);
            drawNull(g,x+37,y);
     }
        private void drawNode(Graphics g,int x, int y,String s){
             
            g.drawRect(x, y, 30, 30);
            g.drawRect(x+30, y, 10, 30);
            g.drawString(s, x+5, y+20);
            //drawLastPointer(g,x+37,y);
        }
        private void drawNull(Graphics g,int x, int y){
         g.drawLine(x, y+15, x+20, y+15);
         g.drawLine(x+20, y+15, x+20, y+30);
         g.drawLine(x+15, y+30, x+25, y+30);
     }
        private void drawLastPointer(Graphics g,int x, int y){
            
                if(y == 70){
                    g.drawLine(x, y+15, x+20, y+15);
                    g.drawLine(x+20, y+15, x+20, y-10);
                    g.drawLine(x+20, y-10, initial_x+20, initial_y-10);
                    g.drawLine(initial_x+20, initial_y-10, initial_x+20, initial_y);
                    
                }else{
                    g.drawLine(x, y+15, x+20, y+15);
                    g.drawLine(x+20, y+15, x+20, y+40);
                    g.drawLine(x+20, y+40, 7, y+40);
                    g.drawLine(7, y+40, 7, 60);
                    g.drawLine(7, 60, initial_x+20, 60);
                    g.drawLine(initial_x+20, 60, initial_x+20, initial_y);
                }
                drawArrow(g, initial_x+20, initial_y, 2);
            
           }
        
   
        
        
        
     private void drawAnimation(){
         // Finding intermediate points by breshnam line
         int i = 0;
         int dx, dy, p, x, y;
         int prevx , prevy;
         this.operation = 1;
         
         temp_x = 20;
         temp_y = 20;
         cur_x = last_cur_x;
         cur_y = last_cur_y;
         dx = 60 +final_x - temp_x;
         dy = final_y - temp_y;
         
         p = 2 * (dy) - (dx);
         x = temp_x;
         y = temp_y;
         while(x <= final_x || y <= final_y)
        {
              
            if(p < 0)
            {
                x=x+1;
                p = p + 2 * (dy);
            }
            else
            {
                x=x+1;
                y=y+1;
                p = p + 2 * (dy - dx);
            }
           System.out.println("x="+x+"    y="+y);
           if(x % 4 == 0)
           {         
                prevx  = x;
                prevy = y;
                interX = x;
           interY = y;
           
             try {
                 Thread.sleep(100);
             } catch (InterruptedException ex) {
                // Logger.getLogger(listPanel.class.getName()).log(Level.SEVERE, null, ex);
             }
             repaint();                      
           }     
           
       }
           this.operation = 0; 

     }
      
      void drawInterPath(Graphics g)
      {
          height = this.getHeight();
          width = this.getWidth();
          
        //  int cur_x, cur_y, step_height, temp_x, temp_y, final_x, final_y;
          int step_height, first = 1;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
          
          Node temp = this.list.first;
          // draw first node
          g.setColor(Color.RED);
          drawNode(g,cur_x,cur_y,"Start");
          
          g.setColor(Color.BLACK);
          while(temp != null && temp.next != null){
             if(first == 1){
               initial_x = cur_x + 60;
               initial_y = cur_y;
               g.setColor(Color.WHITE);
               drawNull(g,cur_x+37,cur_y);
                  first = 0;
             }else{
                    
              }
             
              if( cur_x+130 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 60;
                  final_y = cur_y;
              }
               
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(changed == true){
                g.drawLine(cur_x+35, cur_y+15, cur_x+70, cur_y+15);
                g.drawLine(cur_x+70, cur_y+15, cur_x+70, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+15);
                g.drawLine(10, final_y+15, final_x, final_y+15);
                drawArrow(g, final_x, final_y+15, 1);
                changed = false;
              }
              else{
                  g.drawLine(cur_x+35, cur_y+15, final_x, final_y+15);
                  drawArrow(g, final_x, final_y+15, 1);
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
          }
          if(temp != null)
          {
              g.drawLine(cur_x+35, cur_y+15,  interX, interY+15);
              drawNode(g, interX, interY, ""+temp.data);
              drawLastPointer(g, interX + 37, interY);
          }
      }
        
        
        
        
        
        
        
        
      
      
      public void drawList(Graphics g){
          height = this.getHeight();
          width = this.getWidth();
          int first = 1;
         int step_height;
         int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
         cur_x = 20;
         cur_y = 70;
          step_height = 70;
          
          Node temp = this.list.first;
          // draw first node
          g.setColor(Color.RED);
          drawFirstNode(g,cur_x,cur_y,"Start");
          
          g.setColor(Color.BLACK);
          while(temp != null){
             
               // remove null in list
               //g.clearRect(temp_x+3, temp_y+3, 26, 26);
              if(first == 1){
               initial_x = cur_x + 60;
               initial_y = cur_y;
               g.setColor(Color.WHITE);
               drawNull(g,cur_x+37,cur_y);
                  first = 0;
             }else{
                    
              }
             
              if( cur_x+130 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 60;
                  final_y = cur_y;
              }
               
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(changed == true){
                g.drawLine(cur_x+35, cur_y+15, cur_x+70, cur_y+15);
                g.drawLine(cur_x+70, cur_y+15, cur_x+70, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+15);
                g.drawLine(10, final_y+15, final_x, final_y+15);
                drawArrow(g, final_x, final_y+15, 1);
                changed = false;
              }
              else{
                  g.drawLine(cur_x+35, cur_y+15, final_x, final_y+15);
                  drawArrow(g, final_x, final_y+15, 1);
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
          }
          if(this.list.first != null)
            drawLastPointer(g, final_x + 37, final_y);
    
         }
       public void setValues(SingleLinkedList list,int operation)
       {
          this.list = list;
          this.operation = operation;
          if(this.operation == 1)
          {
                Thread t1 = new Thread(new Runnable()
                {
                    public void run()
                    {
                              drawAnimation();              
                    }
                });
                t1.start();
       
          }
          repaint();
       }
       
     
        @Override
      public void paintComponent(Graphics g) 
      {
          System.out.println("Painted!!!!"+this.operation);
          
          
          if(this.operation == 1){
              System.out.println("Insert operation performed");
              drawInterPath(g);
          }
          else
          {
            drawList(g);
          }
      }
 
}

class ListComponent extends JComponent
    {
       SingleLinkedList list ;
       int operation ;
       int temp_x, temp_y, cur_x, cur_y, final_x, final_y,data,last_cur_x, last_cur_y;
       
       // 1 - insert
       // 2 - delete
       int width, height;
       int interX, interY; 
    /* Drawing Generic Shapes like
     * Node
     * Null
     * first
     * Arrow
     */ListComponent()
     {
        // interX = 80;
         //interY = 70;
     }
     private void drawArrow(Graphics g,int x,int y, int flag){
            // 1 - Right 2- bottom 3- left 4- Up
            if(flag == 1){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x-3, y+3);
            }else if(flag == 2){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x+3, y-3);
            }else if(flag == 3){
                g.drawLine(x, y, x+3, y-3);
                g.drawLine(x, y, x+3, y+3);
            }else if(flag == 4){
                g.drawLine(x, y, x+3, y+3);
                g.drawLine(x, y, x-3, y+3);
            }else{
                
            }
                
        }
    
      private void drawNode(Graphics g,int x, int y,String s){
             
            g.drawRect(x, y, 30, 30);
            g.drawRect(x+30, y, 10, 30);
            g.drawString(s, x+5, y+20);
            drawNull(g,x+37,y);
     }
     private void drawNull(Graphics g,int x, int y){
         g.drawLine(x, y+15, x+20, y+15);
         g.drawLine(x+20, y+15, x+20, y+30);
         g.drawLine(x+15, y+30, x+25, y+30);
     }
     
     private void drawAnimation(){
         // Finding intermediate points by breshnam line
         int i = 0;
         int dx, dy, p, x, y;
         int prevx , prevy;
         this.operation = 1;
         
         temp_x = 20;
         temp_y = 20;
         cur_x = last_cur_x;
         cur_y = last_cur_y;
         dx = 60 +final_x - temp_x;
         dy = final_y - temp_y;
         
         p = 2 * (dy) - (dx);
         x = temp_x;
         y = temp_y;
         while(x <= final_x || y <= final_y)
        {
              
            if(p < 0)
            {
                x=x+1;
                p = p + 2 * (dy);
            }
            else
            {
                x=x+1;
                y=y+1;
                p = p + 2 * (dy - dx);
            }
           System.out.println("x="+x+"    y="+y);
           if(x % 4 == 0)
           {         
                prevx  = x;
                prevy = y;
                interX = x;
           interY = y;
           
             try {
                 Thread.sleep(10);
             } catch (InterruptedException ex) {
                // Logger.getLogger(listPanel.class.getName()).log(Level.SEVERE, null, ex);
             }
             repaint();                      
           }     
           
       }
           this.operation = 0; 

     }
      public void setValues(SingleLinkedList list,int operation)
       {
          this.list = list;
          this.operation = operation;
          if(this.operation == 1)
          {
                Thread t1 = new Thread(new Runnable()
                {
                    public void run()
                    {
                              drawAnimation();              
                    }
                });
                t1.start();
       
          }
          repaint();
       }
      
      void drawInterPath(Graphics g)
      {
          height = this.getHeight();
          width = this.getWidth();
          
        //  int cur_x, cur_y, step_height, temp_x, temp_y, final_x, final_y;
          int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
          
          Node temp = this.list.first;
          // draw first node
          g.setColor(Color.RED);
          drawNode(g,cur_x,cur_y,"Start");
          
          g.setColor(Color.BLACK);
          while(temp != null && temp.next != null){
                
             
               // remove null in list
                g.setColor(Color.WHITE);
                drawNull(g,cur_x+37,cur_y);
              // draw node
              //g.clearRect(temp_x+3, temp_y+3, 26, 26);
              
              
              if( cur_x+130 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 60;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(changed == true){
                g.drawLine(cur_x+35, cur_y+15, cur_x+70, cur_y+15);
                g.drawLine(cur_x+70, cur_y+15, cur_x+70, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+15);
                g.drawLine(10, final_y+15, final_x, final_y+15);
                drawArrow(g, final_x, final_y+15, 1);
                changed = false;
              }
              else{
                  g.drawLine(cur_x+35, cur_y+15, final_x, final_y+15);
                  drawArrow(g, final_x, final_y+15, 1);
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
          }
          drawNode(g, interX, interY, ""+temp.data);
      }
      
      
      public void drawList(Graphics g){
          height = this.getHeight();
          width = this.getWidth();
          
        //  int cur_x, cur_y, step_height, temp_x, temp_y, final_x, final_y;
         int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
          
          Node temp = this.list.first;
          // draw first node
          g.setColor(Color.RED);
          drawNode(g,cur_x,cur_y,"Start");
          
          g.setColor(Color.BLACK);
          while(temp != null){
             
               // remove null in list
                g.setColor(Color.WHITE);
                drawNull(g,cur_x+37,cur_y);
              // draw node
              //g.clearRect(temp_x+3, temp_y+3, 26, 26);
              
              
              if( cur_x+130 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 60;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(changed == true){
                g.drawLine(cur_x+35, cur_y+15, cur_x+70, cur_y+15);
                g.drawLine(cur_x+70, cur_y+15, cur_x+70, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+15);
                g.drawLine(10, final_y+15, final_x, final_y+15);
                drawArrow(g, final_x, final_y+15, 1);
                changed = false;
              }
              else{
                  g.drawLine(cur_x+35, cur_y+15, final_x, final_y+15);
                  drawArrow(g, final_x, final_y+15, 1);
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
          }
          
      }
        @Override
      public void paintComponent(Graphics g) 
      {
          System.out.println("Painted!!!!"+this.operation);
          
          
          if(this.operation == 1){
              System.out.println("Insert operation performed");
              drawInterPath(g);
          }
          else
          {
            drawList(g);
          }
      }
    }
    
/*     class ListComponent extends JComponent
    {
       SingleLinkedList list ;
       int operation ;
       int temp_x, temp_y, cur_x, cur_y, final_x, final_y,data,last_cur_x, last_cur_y;
       
       // 1 - insert
       // 2 - delete
       int width, height;
        
    private void drawArrow(Graphics g,int x,int y, int flag){
            // 1 - Right 2- bottom 3- left 4- Up
            if(flag == 1){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x-3, y+3);
            }else if(flag == 2){
                g.drawLine(x, y, x-3, y-3);
                g.drawLine(x, y, x+3, y-3);
            }else if(flag == 3){
                g.drawLine(x, y, x+3, y-3);
                g.drawLine(x, y, x+3, y+3);
            }else if(flag == 4){
                g.drawLine(x, y, x+3, y+3);
                g.drawLine(x, y, x-3, y+3);
            }else{
                
            }
                
        }
      private void drawNode(Graphics g,int x, int y,String s){
             
            g.drawRect(x, y, 30, 30);
            g.drawRect(x+30, y, 10, 30);
            g.drawString(s, x+5, y+20);
            drawNull(g,x+37,y);
     }
     private void drawNull(Graphics g,int x, int y){
         g.drawLine(x, y+15, x+20, y+15);
         g.drawLine(x+20, y+15, x+20, y+30);
         g.drawLine(x+15, y+30, x+25, y+30);
     }

      public void setValues(SingleLinkedList list,int operation)
       {
          this.list = list;
          this.operation = operation;
          repaint();
       }
      public void drawList(Graphics g){
          height = this.getHeight();
          width = this.getWidth();
          
        //  int cur_x, cur_y, step_height, temp_x, temp_y, final_x, final_y;
         int step_height;
          int incr_distance = 50;  // distance between vertical list in resize case
          boolean changed = false;
          cur_x = 20;
          cur_y = 70;
          step_height = 70;
          
          Node temp = this.list.first;
          // draw first node
          g.setColor(Color.RED);
          drawNode(g,cur_x,cur_y,"Start");
          
          g.setColor(Color.BLACK);
          while(temp != null){
             
               // remove null in list
                g.setColor(Color.WHITE);
                drawNull(g,cur_x+37,cur_y);
              // draw node
              //g.clearRect(temp_x+3, temp_y+3, 26, 26);
              
              
              if( cur_x+130 > width)
              {
                  final_x = 20;
                  step_height += (incr_distance + 30);
                  final_y = step_height;
                  
                  changed = true;
              }
              else{
                  final_x = cur_x + 60;
                  final_y = cur_y;
              }
           
              g.setColor(Color.black);
              drawNode(g, final_x, final_y, ""+temp.data);
              if(changed == true){
                g.drawLine(cur_x+35, cur_y+15, cur_x+70, cur_y+15);
                g.drawLine(cur_x+70, cur_y+15, cur_x+70, cur_y+(30+incr_distance/2));
                g.drawLine(cur_x+70, cur_y+(30+incr_distance/2), 10, cur_y+(30+incr_distance/2));
                g.drawLine(10, cur_y+(30+incr_distance/2), 10, final_y+15);
                g.drawLine(10, final_y+15, final_x, final_y+15);
                drawArrow(g, final_x, final_y+15, 1);
                changed = false;
              }
              else{
                  g.drawLine(cur_x+35, cur_y+15, final_x, final_y+15);
                   drawArrow(g, final_x, final_y+15, 1);
              }
              // update cur_x and cur_y
              last_cur_x = cur_x;
              last_cur_y = cur_y;
              cur_x = final_x;
              cur_y = final_y;
              data = temp.data;
              // draw remaining node 
              temp = temp.next;
          }
          
      }
      
        @Override
      public void paintComponent(Graphics g) 
      {
          drawList(g);
         
      }
    }
     */    
public class listPanel extends javax.swing.JPanel {

 
    /**
     * Creates new form listPanel
     */
    ListComponent lcomp;
    SinglyCircular scir;
    DoublyNull dnull;
    DoublyCircular dcir;
    
    SingleLinkedList listA, listCircular, dlistnull, dlistCircular;
    public listPanel() {
        initComponents();
        
        
        scir = new SinglyCircular();
        lcomp = new ListComponent();
        dnull = new DoublyNull();
        dcir = new DoublyCircular();
        
        singlyListPanel.add(lcomp,BorderLayout.CENTER);
        singlyCircularPanel.add(scir,BorderLayout.CENTER);
        doublyListPanel.add(dnull,BorderLayout.CENTER);
        doublyCircularPanel.add(dcir,BorderLayout.CENTER);
        
        listA = new SingleLinkedList();
        listCircular = new SingleLinkedList();
        dlistnull = new SingleLinkedList();
        dlistCircular = new SingleLinkedList();
        
        lcomp.setValues(listA,0);
        scir.setValues(listCircular,0);
        dnull.setValues(dlistnull,0);
        dcir.setValues(dlistCircular, 0);
        
        singlyListPanel.revalidate();
        singlyCircularPanel.revalidate();
        doublyListPanel.revalidate();
        doublyCircularPanel.revalidate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lsitPane = new javax.swing.JTabbedPane();
        singlyListPanel =  new javax.swing.JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);

            }

        };
        singlyListNorthPanel = new javax.swing.JPanel();
        singlyLengthButton = new javax.swing.JButton();
        singlyaddNodeText = new javax.swing.JTextField();
        singlyaddButton = new javax.swing.JButton();
        qinputText18 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        singlyListSouthPanel = new javax.swing.JPanel();
        singlySlider = new javax.swing.JSlider();
        singlyPauseButton = new javax.swing.JButton();
        singlyStepButton = new javax.swing.JButton();
        singlySkipButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        singlyCircularPanel = new javax.swing.JPanel();
        singlyListNorthPanel1 = new javax.swing.JPanel();
        singlyLengthButton1 = new javax.swing.JButton();
        singlyaddNodeText1 = new javax.swing.JTextField();
        singlyaddButton1 = new javax.swing.JButton();
        qinputText21 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        singlyListSouthPanel1 = new javax.swing.JPanel();
        singlySlider1 = new javax.swing.JSlider();
        singlyPauseButton1 = new javax.swing.JButton();
        singlyStepButton1 = new javax.swing.JButton();
        singlySkipButton1 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        doublyListPanel = new javax.swing.JPanel();
        doublyListNorthPanel = new javax.swing.JPanel();
        singlyLengthButton4 = new javax.swing.JButton();
        doublyListAddText = new javax.swing.JTextField();
        doublyListAddButton = new javax.swing.JButton();
        doublyListDelText = new javax.swing.JTextField();
        doublyListDelButton = new javax.swing.JButton();
        doublyListSouthPanel = new javax.swing.JPanel();
        singlySlider2 = new javax.swing.JSlider();
        singlyPauseButton2 = new javax.swing.JButton();
        singlyStepButton2 = new javax.swing.JButton();
        singlySkipButton2 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        doublyCircularPanel = new javax.swing.JPanel();
        doublyCircularListSouthPanel1 = new javax.swing.JPanel();
        singlySlider3 = new javax.swing.JSlider();
        singlyPauseButton3 = new javax.swing.JButton();
        singlyStepButton3 = new javax.swing.JButton();
        singlySkipButton3 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        doublyCirListNorthPanel = new javax.swing.JPanel();
        singlyLengthButton5 = new javax.swing.JButton();
        doublyCirListAddText = new javax.swing.JTextField();
        doublyCirListAddButton = new javax.swing.JButton();
        doublyCirListDelText = new javax.swing.JTextField();
        doublyCirListDelButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        singlyListPanel.setBackground(new java.awt.Color(254, 254, 254));
        singlyListPanel.setLayout(new java.awt.BorderLayout());

        singlyLengthButton.setText("Length");
        singlyLengthButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyLengthButtonActionPerformed(evt);
            }
        });

        singlyaddNodeText.setColumns(5);
        singlyaddNodeText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                singlyaddNodeTextKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                singlyaddNodeTextKeyTyped(evt);
            }
        });

        singlyaddButton.setText("Add");
        singlyaddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyaddButtonActionPerformed(evt);
            }
        });

        qinputText18.setColumns(5);
        qinputText18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qinputText18ActionPerformed(evt);
            }
        });
        qinputText18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qinputText18KeyPressed(evt);
            }
        });

        jButton31.setBackground(java.awt.Color.white);
        jButton31.setText("Delete");
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout singlyListNorthPanelLayout = new javax.swing.GroupLayout(singlyListNorthPanel);
        singlyListNorthPanel.setLayout(singlyListNorthPanelLayout);
        singlyListNorthPanelLayout.setHorizontalGroup(
            singlyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlyListNorthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyLengthButton)
                .addGap(18, 18, 18)
                .addComponent(singlyaddNodeText, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(singlyaddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(qinputText18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton31)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        singlyListNorthPanelLayout.setVerticalGroup(
            singlyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlyListNorthPanelLayout.createSequentialGroup()
                .addGroup(singlyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyaddButton)
                        .addComponent(singlyaddNodeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singlyLengthButton)
                        .addComponent(qinputText18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        singlyListPanel.add(singlyListNorthPanel, java.awt.BorderLayout.NORTH);

        singlySlider.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Animation Speed", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Charter", 0, 12), new java.awt.Color(222, 29, 29))); // NOI18N

        singlyPauseButton.setText("Pause");

        singlyStepButton.setText("Step");

        singlySkipButton.setText("Skip");
        singlySkipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlySkipButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout singlyListSouthPanelLayout = new javax.swing.GroupLayout(singlyListSouthPanel);
        singlyListSouthPanel.setLayout(singlyListSouthPanelLayout);
        singlyListSouthPanelLayout.setHorizontalGroup(
            singlyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, singlyListSouthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyPauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlySkipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlyStepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(singlySlider, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator3)
        );
        singlyListSouthPanelLayout.setVerticalGroup(
            singlyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlyListSouthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(singlyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyStepButton)
                        .addComponent(singlySkipButton)
                        .addComponent(singlyPauseButton))
                    .addComponent(singlySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        singlyListPanel.add(singlyListSouthPanel, java.awt.BorderLayout.SOUTH);

        lsitPane.addTab("Singly List", singlyListPanel);

        singlyCircularPanel.setBackground(new java.awt.Color(254, 254, 254));
        singlyCircularPanel.setLayout(new java.awt.BorderLayout());

        singlyLengthButton1.setText("Length");
        singlyLengthButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyLengthButton1ActionPerformed(evt);
            }
        });

        singlyaddNodeText1.setColumns(5);
        singlyaddNodeText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                singlyaddNodeText1KeyPressed(evt);
            }
        });

        singlyaddButton1.setText("Add");
        singlyaddButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyaddButton1ActionPerformed(evt);
            }
        });

        qinputText21.setColumns(5);
        qinputText21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qinputText21KeyPressed(evt);
            }
        });

        jButton37.setBackground(java.awt.Color.white);
        jButton37.setText("Delete");
        jButton37.setFocusable(false);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout singlyListNorthPanel1Layout = new javax.swing.GroupLayout(singlyListNorthPanel1);
        singlyListNorthPanel1.setLayout(singlyListNorthPanel1Layout);
        singlyListNorthPanel1Layout.setHorizontalGroup(
            singlyListNorthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlyListNorthPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyLengthButton1)
                .addGap(18, 18, 18)
                .addComponent(singlyaddNodeText1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(singlyaddButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(qinputText21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton37)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        singlyListNorthPanel1Layout.setVerticalGroup(
            singlyListNorthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, singlyListNorthPanel1Layout.createSequentialGroup()
                .addGroup(singlyListNorthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlyListNorthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyaddButton1)
                        .addComponent(singlyaddNodeText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singlyLengthButton1)
                        .addComponent(qinputText21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton37))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        singlyCircularPanel.add(singlyListNorthPanel1, java.awt.BorderLayout.NORTH);

        singlySlider1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Animation Speed", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Charter", 0, 12), new java.awt.Color(222, 29, 29))); // NOI18N

        singlyPauseButton1.setText("Pause");

        singlyStepButton1.setText("Step");

        singlySkipButton1.setText("Skip");
        singlySkipButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlySkipButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout singlyListSouthPanel1Layout = new javax.swing.GroupLayout(singlyListSouthPanel1);
        singlyListSouthPanel1.setLayout(singlyListSouthPanel1Layout);
        singlyListSouthPanel1Layout.setHorizontalGroup(
            singlyListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, singlyListSouthPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyPauseButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlySkipButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlyStepButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(singlySlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator7)
        );
        singlyListSouthPanel1Layout.setVerticalGroup(
            singlyListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlyListSouthPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(singlyListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlyListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyStepButton1)
                        .addComponent(singlySkipButton1)
                        .addComponent(singlyPauseButton1))
                    .addComponent(singlySlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        singlyCircularPanel.add(singlyListSouthPanel1, java.awt.BorderLayout.SOUTH);

        lsitPane.addTab("Singly Circular", singlyCircularPanel);

        doublyListPanel.setBackground(new java.awt.Color(255, 255, 255));
        doublyListPanel.setLayout(new java.awt.BorderLayout());

        singlyLengthButton4.setText("Length");
        singlyLengthButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyLengthButton4ActionPerformed(evt);
            }
        });

        doublyListAddText.setColumns(5);
        doublyListAddText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                doublyListAddTextKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                doublyListAddTextKeyTyped(evt);
            }
        });

        doublyListAddButton.setText("Add");
        doublyListAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doublyListAddButtonActionPerformed(evt);
            }
        });

        doublyListDelText.setColumns(5);
        doublyListDelText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                doublyListDelTextKeyPressed(evt);
            }
        });

        doublyListDelButton.setBackground(java.awt.Color.white);
        doublyListDelButton.setText("Delete");
        doublyListDelButton.setFocusable(false);
        doublyListDelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doublyListDelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doublyListDelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doublyListDelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doublyListNorthPanelLayout = new javax.swing.GroupLayout(doublyListNorthPanel);
        doublyListNorthPanel.setLayout(doublyListNorthPanelLayout);
        doublyListNorthPanelLayout.setHorizontalGroup(
            doublyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyListNorthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyLengthButton4)
                .addGap(18, 18, 18)
                .addComponent(doublyListAddText, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doublyListAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(doublyListDelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doublyListDelButton)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        doublyListNorthPanelLayout.setVerticalGroup(
            doublyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyListNorthPanelLayout.createSequentialGroup()
                .addGroup(doublyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doublyListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(doublyListAddButton)
                        .addComponent(doublyListAddText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singlyLengthButton4)
                        .addComponent(doublyListDelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(doublyListDelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        doublyListPanel.add(doublyListNorthPanel, java.awt.BorderLayout.NORTH);

        singlySlider2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Animation Speed", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Charter", 0, 12), new java.awt.Color(222, 29, 29))); // NOI18N

        singlyPauseButton2.setText("Pause");

        singlyStepButton2.setText("Step");

        singlySkipButton2.setText("Skip");
        singlySkipButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlySkipButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doublyListSouthPanelLayout = new javax.swing.GroupLayout(doublyListSouthPanel);
        doublyListSouthPanel.setLayout(doublyListSouthPanelLayout);
        doublyListSouthPanelLayout.setHorizontalGroup(
            doublyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doublyListSouthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyPauseButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlySkipButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlyStepButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(singlySlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator8)
        );
        doublyListSouthPanelLayout.setVerticalGroup(
            doublyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyListSouthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(doublyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doublyListSouthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyStepButton2)
                        .addComponent(singlySkipButton2)
                        .addComponent(singlyPauseButton2))
                    .addComponent(singlySlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        doublyListPanel.add(doublyListSouthPanel, java.awt.BorderLayout.SOUTH);

        lsitPane.addTab("Doubly List", doublyListPanel);

        doublyCircularPanel.setBackground(new java.awt.Color(255, 255, 255));
        doublyCircularPanel.setLayout(new java.awt.BorderLayout());

        singlySlider3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Animation Speed", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Charter", 0, 12), new java.awt.Color(222, 29, 29))); // NOI18N

        singlyPauseButton3.setText("Pause");

        singlyStepButton3.setText("Step");

        singlySkipButton3.setText("Skip");
        singlySkipButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlySkipButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doublyCircularListSouthPanel1Layout = new javax.swing.GroupLayout(doublyCircularListSouthPanel1);
        doublyCircularListSouthPanel1.setLayout(doublyCircularListSouthPanel1Layout);
        doublyCircularListSouthPanel1Layout.setHorizontalGroup(
            doublyCircularListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doublyCircularListSouthPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyPauseButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlySkipButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(singlyStepButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(singlySlider3, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator9)
        );
        doublyCircularListSouthPanel1Layout.setVerticalGroup(
            doublyCircularListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyCircularListSouthPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(doublyCircularListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doublyCircularListSouthPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(singlyStepButton3)
                        .addComponent(singlySkipButton3)
                        .addComponent(singlyPauseButton3))
                    .addComponent(singlySlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        doublyCircularPanel.add(doublyCircularListSouthPanel1, java.awt.BorderLayout.SOUTH);

        singlyLengthButton5.setText("Length");
        singlyLengthButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlyLengthButton5ActionPerformed(evt);
            }
        });

        doublyCirListAddText.setColumns(5);
        doublyCirListAddText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doublyCirListAddTextActionPerformed(evt);
            }
        });
        doublyCirListAddText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                doublyCirListAddTextKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                doublyCirListAddTextKeyTyped(evt);
            }
        });

        doublyCirListAddButton.setText("Add");
        doublyCirListAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doublyCirListAddButtonActionPerformed(evt);
            }
        });

        doublyCirListDelText.setColumns(5);

        doublyCirListDelButton.setBackground(java.awt.Color.white);
        doublyCirListDelButton.setText("Delete");
        doublyCirListDelButton.setFocusable(false);
        doublyCirListDelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doublyCirListDelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doublyCirListDelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doublyCirListDelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doublyCirListNorthPanelLayout = new javax.swing.GroupLayout(doublyCirListNorthPanel);
        doublyCirListNorthPanel.setLayout(doublyCirListNorthPanelLayout);
        doublyCirListNorthPanelLayout.setHorizontalGroup(
            doublyCirListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyCirListNorthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singlyLengthButton5)
                .addGap(18, 18, 18)
                .addComponent(doublyCirListAddText, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doublyCirListAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(doublyCirListDelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(doublyCirListDelButton)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        doublyCirListNorthPanelLayout.setVerticalGroup(
            doublyCirListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doublyCirListNorthPanelLayout.createSequentialGroup()
                .addGroup(doublyCirListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doublyCirListNorthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(doublyCirListAddButton)
                        .addComponent(doublyCirListAddText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singlyLengthButton5)
                        .addComponent(doublyCirListDelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(doublyCirListDelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        doublyCircularPanel.add(doublyCirListNorthPanel, java.awt.BorderLayout.NORTH);

        lsitPane.addTab("Doubly Circular", doublyCircularPanel);

        add(lsitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void singlySkipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlySkipButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlySkipButtonActionPerformed

    private void singlyLengthButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyLengthButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlyLengthButtonActionPerformed

    private void singlyaddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyaddButtonActionPerformed
        // TODO add your handling code here:
        listA.insert(Integer.parseInt(singlyaddNodeText.getText()));
        lcomp.setValues(listA,1);
        singlyaddNodeText.setText("");
    }//GEN-LAST:event_singlyaddButtonActionPerformed

    private void singlyLengthButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyLengthButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlyLengthButton1ActionPerformed

    private void singlyaddButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyaddButton1ActionPerformed
        // TODO add your handling code here:
        listCircular.insert(Integer.parseInt(singlyaddNodeText1.getText()));
        scir.setValues(listCircular,1);
        singlyaddNodeText1.setText("");
    }//GEN-LAST:event_singlyaddButton1ActionPerformed

    private void singlySkipButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlySkipButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlySkipButton1ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        if(listA.delete(Integer.parseInt(qinputText18.getText())) == null){
            qinputText18.setText("Not Found");
        }
        else
        {
            qinputText18.setText("");
        }
        lcomp.setValues(listA,0);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void singlyaddNodeTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_singlyaddNodeTextKeyPressed
 if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
        listA.insert(Integer.parseInt(singlyaddNodeText.getText()));
        lcomp.setValues(listA,1);
        singlyaddNodeText.setText("");
       }        // TODO add your handling code here:
    
    }//GEN-LAST:event_singlyaddNodeTextKeyPressed

    private void singlyaddNodeTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_singlyaddNodeTextKeyTyped
    }//GEN-LAST:event_singlyaddNodeTextKeyTyped

    private void singlySkipButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlySkipButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlySkipButton3ActionPerformed

    private void singlyLengthButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyLengthButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlyLengthButton4ActionPerformed

    private void doublyListAddTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doublyListAddTextKeyPressed
 if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
       dlistnull.insert(Integer.parseInt(doublyListAddText.getText()));
        dnull.setValues(dlistnull,1);
        doublyListAddText.setText("");
       }        // TODO add your handling code here:
    }//GEN-LAST:event_doublyListAddTextKeyPressed

    private void doublyListAddTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doublyListAddTextKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyListAddTextKeyTyped

    private void doublyListAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doublyListAddButtonActionPerformed
        // TODO add your handling code here:
        dlistnull.insert(Integer.parseInt(doublyListAddText.getText()));
        dnull.setValues(dlistnull,1);
        doublyListAddText.setText("");
    }//GEN-LAST:event_doublyListAddButtonActionPerformed

    private void doublyListDelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doublyListDelButtonActionPerformed
        // TODO add your handling code here:
        if(dlistnull.delete(Integer.parseInt(doublyListDelText.getText())) == null){
            doublyListDelText.setText("Not Found");
        }
        else
        {
            doublyListDelText.setText("");
        }
        dnull.setValues(dlistnull,0);
    }//GEN-LAST:event_doublyListDelButtonActionPerformed

    private void singlyLengthButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlyLengthButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlyLengthButton5ActionPerformed

    private void doublyCirListAddTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doublyCirListAddTextKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyCirListAddTextKeyPressed

    private void doublyCirListAddTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doublyCirListAddTextKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyCirListAddTextKeyTyped

    private void doublyCirListAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doublyCirListAddButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyCirListAddButtonActionPerformed

    private void doublyCirListDelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doublyCirListDelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyCirListDelButtonActionPerformed

    private void singlySkipButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singlySkipButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singlySkipButton2ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
         if(listCircular.delete(Integer.parseInt(qinputText21.getText())) == null){
            qinputText21.setText("Not Found");
        }
        else
        {
            qinputText21.setText("");
        }
        scir.setValues(listCircular,0);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void singlyaddNodeText1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_singlyaddNodeText1KeyPressed
 if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
            listCircular.insert(Integer.parseInt(singlyaddNodeText1.getText()));
        scir.setValues(listCircular,1);
        singlyaddNodeText1.setText("");
       }// TODO add your handling code here:
    }//GEN-LAST:event_singlyaddNodeText1KeyPressed

    private void qinputText21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qinputText21KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
          if(listCircular.delete(Integer.parseInt(qinputText21.getText())) == null){
            qinputText21.setText("Not Found");
        }
        else
        {
            qinputText21.setText("");
        }
        scir.setValues(listCircular,0);
       }// TODO add your handling code here:
    
    }//GEN-LAST:event_qinputText21KeyPressed

    private void qinputText18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qinputText18KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
           if(listA.delete(Integer.parseInt(qinputText18.getText())) == null){
            qinputText18.setText("Not Found");
        }
        else
        {
            qinputText18.setText("");
        }
        lcomp.setValues(listA,0);
       }
    }//GEN-LAST:event_qinputText18KeyPressed

    private void qinputText18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qinputText18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qinputText18ActionPerformed

    private void doublyListDelTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doublyListDelTextKeyPressed
 if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
            if(dlistnull.delete(Integer.parseInt(doublyListDelText.getText())) == null){
            doublyListDelText.setText("Not Found");
        }
        else
        {
            doublyListDelText.setText("");
        }
        dnull.setValues(dlistnull,0);
       }        // TODO add your handling code here:
    }//GEN-LAST:event_doublyListDelTextKeyPressed

    private void doublyCirListAddTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doublyCirListAddTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doublyCirListAddTextActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton doublyCirListAddButton;
    private javax.swing.JTextField doublyCirListAddText;
    private javax.swing.JButton doublyCirListDelButton;
    private javax.swing.JTextField doublyCirListDelText;
    private javax.swing.JPanel doublyCirListNorthPanel;
    private javax.swing.JPanel doublyCircularListSouthPanel1;
    private javax.swing.JPanel doublyCircularPanel;
    private javax.swing.JButton doublyListAddButton;
    private javax.swing.JTextField doublyListAddText;
    private javax.swing.JButton doublyListDelButton;
    private javax.swing.JTextField doublyListDelText;
    private javax.swing.JPanel doublyListNorthPanel;
    private javax.swing.JPanel doublyListPanel;
    private javax.swing.JPanel doublyListSouthPanel;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton37;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane lsitPane;
    private javax.swing.JTextField qinputText18;
    private javax.swing.JTextField qinputText21;
    private javax.swing.JPanel singlyCircularPanel;
    private javax.swing.JButton singlyLengthButton;
    private javax.swing.JButton singlyLengthButton1;
    private javax.swing.JButton singlyLengthButton4;
    private javax.swing.JButton singlyLengthButton5;
    private javax.swing.JPanel singlyListNorthPanel;
    private javax.swing.JPanel singlyListNorthPanel1;
    private javax.swing.JPanel singlyListPanel;
    private javax.swing.JPanel singlyListSouthPanel;
    private javax.swing.JPanel singlyListSouthPanel1;
    private javax.swing.JButton singlyPauseButton;
    private javax.swing.JButton singlyPauseButton1;
    private javax.swing.JButton singlyPauseButton2;
    private javax.swing.JButton singlyPauseButton3;
    private javax.swing.JButton singlySkipButton;
    private javax.swing.JButton singlySkipButton1;
    private javax.swing.JButton singlySkipButton2;
    private javax.swing.JButton singlySkipButton3;
    private javax.swing.JSlider singlySlider;
    private javax.swing.JSlider singlySlider1;
    private javax.swing.JSlider singlySlider2;
    private javax.swing.JSlider singlySlider3;
    private javax.swing.JButton singlyStepButton;
    private javax.swing.JButton singlyStepButton1;
    private javax.swing.JButton singlyStepButton2;
    private javax.swing.JButton singlyStepButton3;
    private javax.swing.JButton singlyaddButton;
    private javax.swing.JButton singlyaddButton1;
    private javax.swing.JTextField singlyaddNodeText;
    private javax.swing.JTextField singlyaddNodeText1;
    // End of variables declaration//GEN-END:variables
}
