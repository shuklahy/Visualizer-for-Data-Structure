import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.geom.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;;


class Sorter implements Runnable
{
   public Sorter(ArrayComponent comp,String array[],char flag)
   {

      System.out.println("Sorter()");
      values = new Double[array.length];
      for (int i = 0; i < values.length; i++) {
           values[i] = Double.parseDouble(array[i]);
       }
      this.component = comp;
      this.gate = new Semaphore(1);
      this.run = false;
      this.flag = flag;
   }

   public void setRun()
   {
      System.out.println("Sorter : setRun()");
      run = true;
      gate.release();
   }

   public void setStep()
   {
      System.out.println("Sorter : setStep()");
      run = false;
      gate.release();
   }

   
   void bubbleSort(Double values[])
   {
        int n = values.length;
        double temp = 0;
        int i = 0, j = 1;         
   
        for(i=0; i < n; i++)
        {
             for(j=1; j < (n-i); j++)
             {
                 component.setValues(values, j-1, j);
                 try
                 {
                     if (run) {
                         Thread.sleep(DELAY);
                     }
                     else {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 if(values[j-1] > values[j])
                 {
                     temp = values[j-1];
                     values[j-1] = values[j];
                     values[j] = temp;
                 }
             }
        }
   }
   void selectionSort(Double values[])
   {
       int n = values.length;
        double temp = 0;
        int min = 0;
        int i = 0, j = 0;         
        
        for(i=0; i < n; i++)
        {
             min = i;
             for(j=i; j < n; j++)
             {
                 component.setValues(values, min, j);
                 try
                 {
                     if (run) {
                         Thread.sleep(DELAY);
                     }
                     else {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 if(values[min] > values[j])
                 {
                     min = j;
                 }
             }
             temp = values[i];
             values[i] = values[min];
             values[min] = temp;
        }
   }
   void insertionSort(Double values[])
   {
        int n = values.length;
        double temp = 0;
        int i = 0, j = 1;         
   System.out.println("insertsort 3");
        for ( i = 1; i < n; i++)
        {
            j = i;
            double B = values[i];
            
            while ((j > 0) && (values[j-1] > B))
            {
                component.setValues(values, j-1, i);
                try
                 {
                     if (run)
                     {
                         Thread.sleep(DELAY);
                     }
                     else
                     {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 values[j] = values[j-1];
                
                j--;
            }
            values[j] = B;
        }
        
       
   }
   void quickSort(Double values[])
   {
       int n = values.length;
        double temp = 0;
        int i = 0, j = 1;         
   
        for(i=0; i < n; i++)
        {
             for(j=1; j < (n-i); j++)
             {
                 component.setValues(values, j-1, j);
                 try
                 {
                     if (run) {
                         Thread.sleep(DELAY);
                     }
                     else {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 if(values[j-1] > values[j])
                 {
                     temp = values[j-1];
                     values[j-1] = values[j];
                     values[j] = temp;
                 }
             }
        }
   }
   void mergeSort(Double values[])
   {
       int n = values.length;
        double temp = 0;
        int i = 0, j = 1;         
   
        for(i=0; i < n; i++)
        {
             for(j=1; j < (n-i); j++)
             {
                 component.setValues(values, j-1, j);
                 try
                 {
                     if (run) {
                         Thread.sleep(DELAY);
                     }
                     else {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 if(values[j-1] > values[j])
                 {
                     temp = values[j-1];
                     values[j-1] = values[j];
                     values[j] = temp;
                 }
             }
        }
   }
   void radixSort(Double values[])
   {
       int n = values.length;
        double temp = 0;
        int i = 0, j = 1;         
   
        System.out.println("Here");
        for(i=0; i < n; i++)
        {
             for(j=1; j < (n-i); j++)
             {
                 component.setValues(values, j-1, j);
                 try
                 {
                     if (run) {
                         Thread.sleep(DELAY);
                     }
                     else {
                         gate.acquire();
                     }
                 }
                 catch (InterruptedException exception)
                 {
                     System.out.println("problem!!!!");
                     Thread.currentThread().interrupt();
                 }
                 if(values[j-1] > values[j])
                 {
                     temp = values[j-1];
                     values[j-1] = values[j];
                     values[j] = temp;
                 }
             }
        }
   }
    @Override
       
   public void run()
   {

      System.out.println("Sorter : run()");
      
        try {
            gate.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sorter.class.getName()).log(Level.SEVERE, null, ex);
        }
      switch(flag)
      {
          case 'b':
              bubbleSort(values);
              break;
          case 's':
              selectionSort(values);
              break;
          case 'i':
              System.out.println("insertsort 2");
              insertionSort(values);
              break;
          case 'q':
              quickSort(values);
              break;
          case 'm':
              mergeSort(values);
              break;
          case 'r':
              radixSort(values);
          
      }
        
      System.out.println("Here");
      component.setValues(values, -1, -1);
                        System.out.println("problem!!!!");

   }

   private Double[] values;
   private ArrayComponent component;
   private Semaphore gate;
   private static final int DELAY = 100;
   private volatile boolean run;
   private static final int VALUES_LENGTH = 30;
   private char flag;
}

class ArrayComponent extends JComponent
{
   public synchronized void setValues(Double[] values, int marked1, int marked2)
   {
                         System.out.println("setValues");

      this.values = values.clone();
      this.marked1 = marked1;
      this.marked2 = marked2;
      repaint();
   }
    double get_Max()
    {
        double max = 0;
        for (int i = 0; i < values.length; i++)
        {
            if ( max < values[i])
            {
                max = values[i];
            }
        }
        return max;
    }
   public synchronized void paintComponent(Graphics g) 
   {
      if (values == null)
      {
           return;
      }
      Graphics2D bubbleG = (Graphics2D) g;
      double factor = (getHeight() * 0.7) / get_Max();
      
      for (int i = 0; i < values.length; i++)
      {
            int height = (int) (values[i] * factor);
            int x = 30+ (i * 35);
            int y = getHeight() - 40;
            
            Rectangle2D bar = new Rectangle2D.Double(x, y - 35 - height, 30, height);;
            Rectangle2D box = new Rectangle2D.Double(x, y - 30, 30, 30);
            
           // bubbleG.drawRect(x, y - 35 - height, 30, height);
           // bubbleG.drawRect(x, y - 30, 30, 30);
            
             
            

            if (i == marked1 || i == marked2)
            {
                 bubbleG.fill(bar);
                 bubbleG.fill(box);
                 bubbleG.setColor(Color.RED);
                 bubbleG.drawString(""+values[i],x + 5 , y + 19 - 30);
                 bubbleG.setColor(Color.BLUE);
                 bubbleG.drawString(""+i,x + 15 , y + 45 - 30);
                 bubbleG.setColor(Color.BLACK);
            }
            else
            {
                 bubbleG.draw(bar);
                 bubbleG.draw(box);
                 bubbleG.setColor(Color.RED);
                 bubbleG.drawString(""+values[i],x + 5 , y + 19 - 30);
                 bubbleG.setColor(Color.BLUE);
                 bubbleG.drawString(""+i,x + 15 , y + 45 - 30);
                 bubbleG.setColor(Color.BLACK);
            }
      }
   }

   private int marked1;
   private int marked2;
   private Double[] values;
}

public class sortingPanel extends javax.swing.JPanel {

  
    static Graphics bubbleG ,  selectG ;
    static Graphics quickG ,  mergeG ;
    static Graphics insertG ,  radixG ;
    static int  sortClicked;
    String arrayb[];
  
    
    public sortingPanel() {
        sortClicked = 0;
        initComponents();
        allElementsText.setText(" ");
      
    }
    
    int get_Max()
    {
        int max = 0;
        for (int i = 0; i < arrayb.length; i++)
        {
            if ( max < Integer.parseInt(arrayb[i]))
            {
                max = Integer.parseInt(arrayb[i]);
            }
        }
        return max;
    }
        
    void bubbleAnimation()
    {
                
        bubblePanel.removeAll();
        bubblePanel.revalidate();
        //bubblePanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
        bubblePanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'b');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
        bubblePanel.add(buttons, BorderLayout.NORTH);
        bubblePanel.setVisible(true);
     
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                sortButton.setEnabled(true);
            }
        });
        t1.start();
        bubblePanel.revalidate();
        //bubblePanel.repaint();
   }

        void selectionAnimation()
    {
                
        selectionPanel.removeAll();
        selectionPanel.revalidate();
        selectionPanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
        selectionPanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'s');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
        selectionPanel.add(buttons, BorderLayout.NORTH);
        selectionPanel.setVisible(true);
     
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                sortButton.setEnabled(true);
            }
        });
        t1.start();
        selectionPanel.revalidate();
        selectionPanel.repaint();
   }

    void insertionAnimation()
    {
                
        insertionPanel.removeAll();
        insertionPanel.revalidate();
        insertionPanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
        insertionPanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'i');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
        insertionPanel.add(buttons, BorderLayout.NORTH);
        insertionPanel.setVisible(true);
     
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                sortButton.setEnabled(true);
            }
        });
        t1.start();
        insertionPanel.revalidate();
        insertionPanel.repaint();
        System.out.println("insertsort 1");
   }
    void quickAnimation()
    {
                
//        quickPanel.removeAll();
 //       quickPanel.revalidate();
  //      quickPanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
    //    quickPanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'q');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
      //  quickPanel.add(buttons, BorderLayout.NORTH);
       // quickPanel.setVisible(true);
     
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                sortButton.setEnabled(true);
            }
        });
        t1.start();
    //    quickPanel.revalidate();
     //   quickPanel.repaint();
   }
    void mergeAnimation()
    {
                
       // mergePanel.removeAll();
        //mergePanel.revalidate();
        //mergePanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
        //mergePanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'m');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
        //mergePanel.add(buttons, BorderLayout.NORTH);
       //mergePanel.setVisible(true);
     //
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                sortButton.setEnabled(true);
            }
        });
        t1.start();
       // mergePanel.revalidate();
        //mergePanel.repaint();
   }
    void radixAnimation()
    {
                
       // radixPanel.removeAll();
       // radixPanel.revalidate();
       // radixPanel.repaint();
        sortClicked++;
        final ArrayComponent comp = new ArrayComponent();
       // radixPanel.add(comp,BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp,arrayb,'r');
        
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setRun();
            }
        });

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               sorter.setStep();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
       // radixPanel.add(buttons, BorderLayout.NORTH);
       // radixPanel.setVisible(true);
     
        final Thread t = new Thread(sorter);
        t.start();
        sortButton.setEnabled(false);
        comp.repaint();
        
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                  t.join();
                }
                catch (InterruptedException ex) 
                {
                  Logger.getLogger(sortingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                sortButton.setEnabled(true);
            }
        });
        t1.start();
     //   radixPanel.revalidate();
     //   radixPanel.repaint();
   }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        southPanel = new javax.swing.JPanel();
        addText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteText = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        allElementsText = new javax.swing.JTextField();
        centerPanel = new javax.swing.JPanel();
        sortingTabbedPane = new javax.swing.JTabbedPane();
        bubblePanel = new javax.swing.JPanel()
        {

            public void paint(Graphics g)
            {
                super.paint(g);
                if(sortClicked > 0)
                {
                    System.out.println("ajsgdjgasd");
                    bubbleG = g;

                }
            }

        };

        ;
        selectionPanel = new javax.swing.JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                selectG = g;
            }
        };
        ;
        insertionPanel = new javax.swing.JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                insertG = g;
            }
        };
        ;

        setPreferredSize(new java.awt.Dimension(1000, 377));
        setLayout(new java.awt.BorderLayout());

        addText.setColumns(10);
        addText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTextActionPerformed(evt);
            }
        });
        addText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addTextKeyPressed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        sortButton.setText("Sort");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear All");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        deleteText.setColumns(10);
        deleteText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTextActionPerformed(evt);
            }
        });
        deleteText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deleteTextKeyPressed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        allElementsText.setEditable(false);
        allElementsText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allElementsTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout southPanelLayout = new javax.swing.GroupLayout(southPanel);
        southPanel.setLayout(southPanelLayout);
        southPanelLayout.setHorizontalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(southPanelLayout.createSequentialGroup()
                        .addComponent(addText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(allElementsText, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        southPanelLayout.setVerticalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, southPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(allElementsText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton)
                    .addComponent(sortButton))
                .addContainerGap())
        );

        add(southPanel, java.awt.BorderLayout.SOUTH);

        centerPanel.setLayout(new javax.swing.BoxLayout(centerPanel, javax.swing.BoxLayout.LINE_AXIS));

        bubblePanel.setBackground(new java.awt.Color(254, 254, 254));
        bubblePanel.setLayout(new java.awt.BorderLayout());
        sortingTabbedPane.addTab("Bubble Sort", bubblePanel);

        selectionPanel.setBackground(new java.awt.Color(254, 254, 254));
        selectionPanel.setLayout(new java.awt.BorderLayout());
        sortingTabbedPane.addTab("Selection Sort", selectionPanel);

        insertionPanel.setBackground(new java.awt.Color(254, 254, 254));
        insertionPanel.setLayout(new java.awt.BorderLayout());
        sortingTabbedPane.addTab("Insertion Sort", insertionPanel);

        centerPanel.add(sortingTabbedPane);

        add(centerPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
       allElementsText.setText(" ");
       
    }//GEN-LAST:event_clearButtonActionPerformed

    private void allElementsTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allElementsTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allElementsTextActionPerformed

    private void addTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addTextActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
            allElementsText.setText(allElementsText.getText() + " " + addText.getText());
            addText.setText(null);
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String tmp = allElementsText.getText();
        tmp = tmp.replace(" "+deleteText.getText() +" ", " ");
        allElementsText.setText(tmp);
        deleteText.setText(null);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
       arrayb = allElementsText.getText().substring(2).split(" ");
       for(int  i = 0 ; i < arrayb.length ; i++)
       {
            System.out.println("|"+arrayb[i]+"|");
       }
       
       JPanel ch = (JPanel)sortingTabbedPane.getSelectedComponent();
 
       if(ch == bubblePanel)
       {
            System.out.println("bubblesort 1");
            bubbleAnimation();
       }
       if(ch == selectionPanel)
       {
            System.out.println("selectsort 1");
            selectionAnimation();
       }
       else if(ch == insertionPanel)
       {
           System.out.println("insertsort 1");
            insertionAnimation();
       }
      // else if(ch == quickPanel)
      // {
        //    quickAnimation();
      // }
      // else if(ch == mergePanel)
       //{
       //     mergeAnimation();
       //}
       //else if(ch == radixPanel)
       //{
       //     radixAnimation();
       //}
    }//GEN-LAST:event_sortButtonActionPerformed

    private void addTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addTextKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
            allElementsText.setText(allElementsText.getText() + " " + addText.getText());
            addText.setText(null);
       }
    }//GEN-LAST:event_addTextKeyPressed

    private void deleteTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteTextActionPerformed

    private void deleteTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deleteTextKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
           
            String tmp = allElementsText.getText();
            tmp = tmp + " ";
            System.out.println("|"+tmp+"|");
            tmp = tmp.replace(" "+deleteText.getText() +" ", " ");
            tmp.trim();
            allElementsText.setText(tmp);
            deleteText.setText(null);
       }
    }//GEN-LAST:event_deleteTextKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addText;
    private javax.swing.JTextField allElementsText;
    private javax.swing.JPanel bubblePanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField deleteText;
    private javax.swing.JPanel insertionPanel;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JButton sortButton;
    private javax.swing.JTabbedPane sortingTabbedPane;
    private javax.swing.JPanel southPanel;
    // End of variables declaration//GEN-END:variables
}
