/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author huy
 */
public class GiaoDien extends javax.swing.JFrame {

    private DefaultListModel modelList;
    private SpinnerNumberModel modelSpinnerIndex;
    private ActionListener eInterchange, eSelection, eBubble, eInsertion, eShell, eHeap, eQuick, eMerge;
    private ActionListener eIncrease, eDecrease;
    private ActionListener eSpeed; //eSpeedSlow, eSpeedNormal, eSpeedFast;
    private boolean isIncrease = true;
    private int length;
    private JLabel[] lbArrays;
    private int[] array;
    private int[] arrayCopy;
    private Thread[] threads = new Thread[1000000];
    private int curT = -1;
    private int time = 50;
    private int step = 0;
    private int[] lbI = new int[50];
    private int[] lbJ = new int[50];
    private int[] oriLocat = new int[15];
    private int[] lbL = new int[50];
    private int[] lbR = new int[50];
    private boolean isDone = true;
    private JLabel lbPoint1 = new JLabel();
    private JLabel lbPoint2 = new JLabel();
    private JLabel lbPointM = new JLabel();

    /**
     * Creates new form GiaoDien
     */
    public GiaoDien() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        modelList = new DefaultListModel<>();
        lsCode.setModel(modelList);
        JFormattedTextField txt = ((JSpinner.NumberEditor) spLength.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
        JFormattedTextField txt1 = ((JSpinner.NumberEditor) spIndex.getEditor()).getTextField();
        ((NumberFormatter) txt1.getFormatter()).setAllowsInvalid(false);
        modelSpinnerIndex = (SpinnerNumberModel)spIndex.getModel();
        
        lbPoint1.setSize(50, 25);
        lbPoint1.setOpaque(true);
        lbPoint1.setLocation(50, 50);
        lbPoint1.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPoint1.setBackground(SystemColor.menu);
        lbPoint1.setHorizontalAlignment(JLabel.CENTER);
        lbPoint1.setVerticalAlignment(JLabel.CENTER);
        pnSimulation.add(lbPoint1);
        
        lbPoint2.setSize(50, 25);
        lbPoint2.setOpaque(true);
        lbPoint2.setLocation(50, 50);
        lbPoint2.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPoint2.setBackground(SystemColor.menu);
        lbPoint2.setHorizontalAlignment(JLabel.CENTER);
        lbPoint2.setVerticalAlignment(JLabel.CENTER);
        pnSimulation.add(lbPoint2);
        
        lbPointM.setSize(50, 25);
        lbPointM.setOpaque(true);
        lbPointM.setLocation(50, 50);
        lbPointM.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPointM.setBackground(SystemColor.menu);
        lbPointM.setHorizontalAlignment(JLabel.CENTER);
        lbPointM.setVerticalAlignment(JLabel.CENTER);
        pnSimulation.add(lbPointM);
        
        /**
         * event ActionListener all sorts
         */
        eInterchange = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeInterchangeSort();
                addIdeaInterchangeSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eSelection = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeSelectionSort();
                addIdeaSelectionSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eBubble = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeBubbleSort();
                addIdeaBubbleSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eInsertion = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeInsertionSort();
                addIdeaInsertionSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eShell = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeShellSort();
                addIdeaShellSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eHeap = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeHeapSort();
                addIdeaHeapSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eQuick = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeQuickSort();
                addIdeaQuickSort();
                lsCode.setSelectedIndex(0);
            }
        };

        eMerge = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modelList.removeAllElements();
                addCodeMergeSort();
                addIdeaMergeSort();
                lsCode.setSelectedIndex(0);
            }
        };

        //add ActionListener to JRadioButtons sort
        rdInterchange.addActionListener(eInterchange);
        rdSelection.addActionListener(eSelection);
        rdBubble.addActionListener(eBubble);
        rdInsertion.addActionListener(eInsertion);
        rdShell.addActionListener(eShell);
        rdHeap.addActionListener(eHeap);
        rdQuick.addActionListener(eQuick);
        rdMerge.addActionListener(eMerge);
        
        modelList.removeAllElements();
        addCodeInterchangeSort();
        addIdeaInterchangeSort();
        lsCode.setSelectedIndex(0);
        
        eIncrease = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //set Increase or Decrease
                isIncrease = true;

                //update element sort
                if (rdInterchange.isSelected()) {
                    modelList.set(4, "                    if(a[j] < a[i])");
                }
                if (rdSelection.isSelected()) {
                    modelList.set(5, "               if (a[j] < a[pos])");
                }
                if (rdBubble.isSelected()) {
                    modelList.set(4, "               if(a[j] < a[j-1])");
                }
                if (rdInsertion.isSelected()) {
                    modelList.set(6, "          while ((pos >= 0) && (a[pos] > x)) {");
                }
            }
        };

        eDecrease = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                isIncrease = false;

                if (rdInterchange.isSelected()) {
                    modelList.set(4, "                    if(a[j] > a[i])");
                }
                if (rdSelection.isSelected()) {
                    modelList.set(5, "               if (a[j] > a[pos])");
                }
                if (rdBubble.isSelected()) {
                    modelList.set(4, "               if(a[j] > a[j-1])");
                }
                if (rdInsertion.isSelected()) {
                    modelList.set(6, "          while ((pos >= 0) && (a[pos] < x)) {");
                }
            }
        };

        rdIncrease.addActionListener(eIncrease);
        rdDecrease.addActionListener(eDecrease);
        
        eSpeed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(rdSlow))
                    time = 300;
                if(e.getSource().equals(rdNormal))
                    time = 100;
                if(e.getSource().equals(rdFast))
                    time = 30;
            }
        };
        rdSlow.addActionListener(eSpeed);
        rdNormal.addActionListener(eSpeed);
        rdFast.addActionListener(eSpeed);
        
        setState(0);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Add text to JList and JTextArea">
    //Add text sort to JList
    public void addCodeInterchangeSort() {
        modelList.addElement("void InterchangeSort(int a[], int N) {");
        modelList.addElement("     int i, j;");
        modelList.addElement("          for (i = 0 ; i<N-1 ; i++)");
        modelList.addElement("               for (j =i+1; j < N ; j++)");
        modelList.addElement("                    if(a[j ]< a[i])");
        modelList.addElement("                         Swap(a[i], a[j])");
        modelList.addElement("}");
    }

    public void addCodeSelectionSort() {
        modelList.addElement("void SelectionSort(int a[],int n ) {");
        modelList.addElement("     int pos,i,j;");
        modelList.addElement("     for (i=0; i<n-1 ; i++) {");
        modelList.addElement("          pos = i;");
        modelList.addElement("          for(j = i+1; j <N ; j++)");
        modelList.addElement("               if (a[j] < a[pos])");
        modelList.addElement("                    pos = j;");
        modelList.addElement("               Swap(a[pos],a[i]);");
        modelList.addElement("     }");
        modelList.addElement("}");
    }

    public void addCodeBubbleSort() {
        modelList.addElement("void BubbleSort(int a[],int n) {");
        modelList.addElement("     int i, j;");
        modelList.addElement("     for (i = 0 ; i<n-1 ; i++)");
        modelList.addElement("          for (j =n-1; j >i ; j --)");
        modelList.addElement("               if(a[j] < a[j-1])");
        modelList.addElement("                    Swap(a[j], a[j-1]");
        modelList.addElement("}");
    }

    public void addCodeInsertionSort() {
        modelList.addElement("void InsertionSort(int a[], int n ) {");
        modelList.addElement("     int pos, i;");
        modelList.addElement("     int x;");
        modelList.addElement("     for(i = 1; i < n; i++) {");
        modelList.addElement("          x = a[i];");
        modelList.addElement("          pos = i - 1;");
        modelList.addElement("          while ((pos >= 0) && (a[pos] > x)) {");
        modelList.addElement("               a[pos+1] = a[pos];");
        modelList.addElement("               pos--;");
        modelList.addElement("          }");
        modelList.addElement("     a[pos+1] = x];");
        modelList.addElement("     }");
        modelList.addElement("}");
    }

    public void addCodeShellSort() {
        modelList.addElement("void ShellSort(int a[], int n) {");
        modelList.addElement("    int len, i, j, x;");
        modelList.addElement("    for (len = 11; len > 0; len /= 2) {");
        modelList.addElement("        for (i = len; i < n; i++) {");
        modelList.addElement("            x = a[i];");
        modelList.addElement("            j = i - len;");
        modelList.addElement("            while (j >= 0 && x < a[i]) {");
        modelList.addElement("                a[j + len] = a[j];");
        modelList.addElement("            }");
        modelList.addElement("            j -= len;");
        modelList.addElement("        }");
        modelList.addElement("        a[j + len] = x;");
        modelList.addElement("    }");
        modelList.addElement("}");
    }

    public void addCodeHeapSort() {
        modelList.addElement("void HeapSort(int a[],int n) {");
        modelList.addElement("     int r;");
        modelList.addElement("     CreateHeap(a,n);");
        modelList.addElement("     r=n-1;");
        modelList.addElement("     while(r>0) {");
        modelList.addElement("          Swap(a[0],a[r]);");
        modelList.addElement("          r--;");
        modelList.addElement("          if(r>0)");
        modelList.addElement("               shift(a,0,r);");
        modelList.addElement("     }");
        modelList.addElement("}");
        modelList.addElement("");
        modelList.addElement("void CreateHeap(int a[],int n) {");
        modelList.addElement("     int l;");
        modelList.addElement("     l=n/2-1;");
        modelList.addElement("     while(l>=0) {");
        modelList.addElement("          shift(a,l,n-1);");
        modelList.addElement("          l=l-1;");
        modelList.addElement("     }");
        modelList.addElement("}");
        modelList.addElement("");
        modelList.addElement("void shift(int a[],int l,int r) {");
        modelList.addElement("     int x,i,j;");
        modelList.addElement("     i=l;");
        modelList.addElement("     j=2*i+1;");
        modelList.addElement("     x=a[i];");
        modelList.addElement("     while(j<=r) {");
        modelList.addElement("          if(j<r)");
        modelList.addElement("          if(a[j]<a[j+1])");
        modelList.addElement("          j++;");
        modelList.addElement("          if(a[j]<=x)");
        modelList.addElement("               return;");
        modelList.addElement("          else {");
        modelList.addElement("               a[i]=a[j]");
        modelList.addElement("               a[j]=x;");
        modelList.addElement("               i=j;");
        modelList.addElement("               j=2*i+1;");
        modelList.addElement("               x=a[i];");
        modelList.addElement("          }");
        modelList.addElement("     }");
        modelList.addElement("}");
    }

    public void addCodeQuickSort() {
        modelList.addElement("void QuickSort(int a[], int left, int right) {");
        modelList.addElement("     int i, j, x;");
        modelList.addElement("     x = a[(left+right)/2];");
        modelList.addElement("     i = left; j = right;");
        modelList.addElement("     while(i < j) {");
        modelList.addElement("          while(a[i] < x) i++;");
        modelList.addElement("          while(a[j] > x) j--;");
        modelList.addElement("          if(i <= j) {");
        modelList.addElement("               Swap(a[i],a[j]);");
        modelList.addElement("               i++ ; j--;");
        modelList.addElement("          }");
        modelList.addElement("     }");
        modelList.addElement("     if(left<j)");
        modelList.addElement("          QuickSort(a, left, j)");
        modelList.addElement("     if(i<right)");
        modelList.addElement("          QuickSort(a, i, right);");
        modelList.addElement("}");
    }

    public void addCodeMergeSort() {
        modelList.addElement("void MergeSort(int left, int right) {");
        modelList.addElement("    if (left < right) {");
        modelList.addElement("        int mid = (left + right) / 2;");
        modelList.addElement("        MergeSort(left, mid);");
        modelList.addElement("        MergeSort(mid + 1, right);");
        modelList.addElement("        Merge(left, mid, right);");
        modelList.addElement("    }");
        modelList.addElement("}");
        modelList.addElement("");
        modelList.addElement("public void Merge(int left, int mid, int right) {");
        modelList.addElement("    int n1 = mid - left + 1;");
        modelList.addElement("    int n2 = right - mid;");
        modelList.addElement("    int[] T = new int[n1 + n2];");
        modelList.addElement("    int[] L = new int[n1];");
        modelList.addElement("    int[] R = new int[n2];");
        modelList.addElement("    int i, j, k;");
        modelList.addElement("    for (i = 0; i < n1; i ++)");
        modelList.addElement("        L[i] = array[left + i];");
        modelList.addElement("    for (j = 0; j < n2; j ++)");
        modelList.addElement("        R[j] = array[mid + 1 + j];");
        modelList.addElement("    i = 0; j = 0;");
        modelList.addElement("    k = left;");
        modelList.addElement("    while (i < n1 && j < n2) {");
        modelList.addElement("        if (L[i] <= R[j]) {");
        modelList.addElement("            array[k] = L[i];");
        modelList.addElement("            i ++;");
        modelList.addElement("        } else {");
        modelList.addElement("            array[k] = R[j];");
        modelList.addElement("            j ++;");
        modelList.addElement("        }");
        modelList.addElement("        k ++;");
        modelList.addElement("    }");
        modelList.addElement("    while (i < n1) {");
        modelList.addElement("        array[k] = L[i];");
        modelList.addElement("        i ++;");
        modelList.addElement("        k ++;");
        modelList.addElement("    }");
        modelList.addElement("    while (j < n2) {");
        modelList.addElement("        array[k] = R[j];");
        modelList.addElement("        j ++;");
        modelList.addElement("        k ++;");
        modelList.addElement("    }");
        modelList.addElement("}");
    }
    
    // Add text idea to JTextArea
    public void addIdeaInterchangeSort() {
        txtIdea.setText("Xuất phát từ đầu dãy,tìm tất cả các cặp nghịch thế chứa phần tử này, "
                + "triệt tiêu chúng bằng cách đổi phần tử này với phần tử tương ứng trong cặp nghịch thế ."
                + "Lặp lại xử lý trên với các phần tử tiếp theo");
    }
    
    public void addIdeaSelectionSort() {
        txtIdea.setText("Chọn phần tử nhỏ nhất hoặc lớn nhất trong N phần tử trong dãy hiện hành. "
                + "Đưa phần tử này về vị trí đầu dãy hiện hành. "
                + "Xem dãy hiện hành chỉ còn N-1 phần tử của dãy hiện hành ban đầu. Bắt đầu từ vị trí thứ 2. "
                + "Lặp lại quá trình trên cho dãy hiện hành... đến khi dãy hiện hành chỉ còn 1 phần tử");
    }
    
    public void addIdeaBubbleSort() {
        txtIdea.setText("Xuất phát từ cuối dãy,đổi chỗ các cặp phần tử kế cận để đưa phần tử nhỏ hơn hoặc lớn hơn trong cặp phần tử đó "
                + "về vị trí đúng đầu dãy hiện hành, sau đó sẽ không xét đến nó ở bước tiếp theo,"
                + "do vậy ở lần xử lý thứ i sẽ có vị trí đầu dãy là i. Lặp lại xử lý trên cho đến khi không còn cặp phần tử nào để xét");
    }
    
    public void addIdeaInsertionSort() {
        txtIdea.setText("Giả sử có một dãy a(0),a(1),...,a(n-1) trong đó i phần tử đầu tiên a(0),a(1),...,a(i-1) đã có thứ tự. "
                + "Tìm cách chèn phần tử a(i) vào vị trí thích hợp của đoạn đã được sắp để có dãy mới a(0),a(1),...,a(i) trở nên có thứ tự. "
                + "Vị trí này chính là vị trí giữa hai phần tử a(k-1) và a(k) thỏa a(k-1)<a(i)<a(k) (1<=k<=i)");
    }
    
    public void addIdeaShellSort() {
        txtIdea.setText("Cải tiến phương pháp InsertionSort. Phân hoạch dãy thành các dãy con. "
                + "Sắp xếp các dãy con theo phương pháp InsertionSort. Dùng phương pháp InsertionSort sắp xếp lại cả dãy . "
                + "Tìm k bước với các khoảng cách chọn theo công thức h(i) = (h(i-1) - 1)/2 và h(k) = 1, k = lg(n)/lg(2) - 1.");
    }
    
    public void addIdeaHeapSort() {
        txtIdea.setText("Khi tìm phần tử nhỏ nhất(lớn nhất) ở bước i, "
                + "phương pháp SelectionSort không tận dụng được các thông tin đã có được do các phép so sánh ở bước i. "
                + "Giải thuật HeapSort khắc phục nhược điểm này bằng cách chọn ra được một cấu trúc dữ liệu "
                + "cho phép tích lũy các thông tin về sự so sánh giá trị các phần tử trong quá trình sắp xếp");
    }
    
    public void addIdeaQuickSort() {
        txtIdea.setText("Sắp xếp dãy a(1),a(2),...,a(n) dựa trên việc phân hoạch dãy ban đầu thành 3 phần :\n"
                + "-Phần 1 : Gồm các phần tử có giá trị bé hơn x.\n"
                + "-Phần 2 : Gồm các phần tử có giá trị bằng x.\n"
                + "-Phần 3 : Gồm các phần tử có giá trị lớn hơn x\n(Với x là giá trị của một phần tử tùy ý trong dãy ban đầu)");
    }
    
    public void addIdeaMergeSort() {
        txtIdea.setText("Sắp xếp dãy a(1),a(2),...,a(n) dựa trên nhận xét sau:\n"
                + "-Mỗi dãy a(1),a(2),...,a(n) bất kỳ là một tập hợp các dãy con liên tiếp mà mỗi dãy con đều đã có thứ tự.\n"
                + "-Dãy đã có thứ tự coi như có 1 dãy con.\n"
                + "Hướng tiếp cận : tìm cách làm giảm số dãy con không giảm của dãy ban đầu");
    }
    //</editor-fold>
    
    @Override
    public void setState(int state){
        switch(state){
            case 0: //first state, haven't created arrays.
                btnCreateRandom.setEnabled(true);
                btnCreateHandmade.setEnabled(true);
                
                btnResetRandom.setEnabled(false);
                btnResetHandmade.setEnabled(false);
                spIndex.setEnabled(false);
                txtValue.setEnabled(false);
                
                rdInterchange.setEnabled(true);
                rdSelection.setEnabled(true);
                rdBubble.setEnabled(true);
                rdInsertion.setEnabled(true);
                rdShell.setEnabled(true);
                rdHeap.setEnabled(true);
                rdQuick.setEnabled(true);
                rdMerge.setEnabled(true);

                rdIncrease.setEnabled(true);
                rdDecrease.setEnabled(true);
                
                rdSlow.setEnabled(false);
                rdNormal.setEnabled(false);
                rdFast.setEnabled(false);

                btnStart.setEnabled(false);
                btnPause.setEnabled(false);
                btnDistroy.setEnabled(false);
                break;
            case 1: //created arrays, ready to sort.
                btnCreateRandom.setEnabled(true);
                btnCreateHandmade.setEnabled(true);
                
                btnResetRandom.setEnabled(true);
                btnResetHandmade.setEnabled(true);
                spIndex.setEnabled(true);
                txtValue.setEnabled(true);
                
                rdInterchange.setEnabled(true);
                rdSelection.setEnabled(true);
                rdBubble.setEnabled(true);
                rdInsertion.setEnabled(true);
                rdShell.setEnabled(true);
                rdHeap.setEnabled(true);
                rdQuick.setEnabled(true);
                rdMerge.setEnabled(true);

                rdIncrease.setEnabled(true);
                rdDecrease.setEnabled(true);
                
                rdSlow.setEnabled(true);
                rdNormal.setEnabled(true);
                rdFast.setEnabled(true);

                btnStart.setEnabled(true);
                btnPause.setEnabled(false);
                btnDistroy.setEnabled(false);
                break;
            case 2: //sorting
                btnCreateRandom.setEnabled(false);
                btnCreateHandmade.setEnabled(false);
                
                btnResetRandom.setEnabled(false);
                btnResetHandmade.setEnabled(false);
                spIndex.setEnabled(false);
                txtValue.setEnabled(false);
                
                rdInterchange.setEnabled(false);
                rdSelection.setEnabled(false);
                rdBubble.setEnabled(false);
                rdInsertion.setEnabled(false);
                rdShell.setEnabled(false);
                rdHeap.setEnabled(false);
                rdQuick.setEnabled(false);
                rdMerge.setEnabled(false);

                rdIncrease.setEnabled(false);
                rdDecrease.setEnabled(false);
                
                rdSlow.setEnabled(true);
                rdNormal.setEnabled(true);
                rdFast.setEnabled(true);

                btnStart.setEnabled(false);
                btnPause.setEnabled(true);
                btnDistroy.setEnabled(true);
                break;
            case 3: //sort done -- case 1
                btnCreateRandom.setEnabled(true);
                btnCreateHandmade.setEnabled(true);
                
                btnResetRandom.setEnabled(true);
                btnResetHandmade.setEnabled(true);
                spIndex.setEnabled(true);
                txtValue.setEnabled(true);
                
                rdInterchange.setEnabled(true);
                rdSelection.setEnabled(true);
                rdBubble.setEnabled(true);
                rdInsertion.setEnabled(true);
                rdShell.setEnabled(true);
                rdHeap.setEnabled(true);
                rdQuick.setEnabled(true);
                rdMerge.setEnabled(true);

                rdIncrease.setEnabled(true);
                rdDecrease.setEnabled(true);
                
                rdSlow.setEnabled(true);
                rdNormal.setEnabled(true);
                rdFast.setEnabled(true);

                btnStart.setEnabled(true);
                btnPause.setEnabled(false);
                btnDistroy.setEnabled(false);
                break;  
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Doing with arrays">
    
    public void createRandomArrays() {

        deleteArrays();
        length = (Integer) spLength.getValue();

        lbArrays = new JLabel[length];
        array = new int[length];
        Random rand = new Random();
        String str = "";

        for (int i = 0; i < length; i++) {

            int ranNum = rand.nextInt(101) + 0;
            str += String.valueOf(ranNum) + " ";
            lbArrays[i] = new JLabel(String.valueOf(ranNum));
            array[i] = ranNum;
            pnSimulation.add(lbArrays[i]);

            //set size label
            lbArrays[i].setSize(50, 50);
            lbArrays[i].setOpaque(true);
            lbArrays[i].setForeground(Color.white);

            //set location label
            if (i == 0) {
                lbArrays[i].setLocation(((int) ((18 - length) * 0.5) * 70) + 100, 150);
            } else {
                lbArrays[i].setLocation(lbArrays[i - 1].getX() + 70, 150);
            }

            //set fonts
            lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));

            //set background color
            lbArrays[i].setBackground(Color.red);
            //set text alignment center
            lbArrays[i].setHorizontalAlignment(JLabel.CENTER);
            lbArrays[i].setVerticalAlignment(JLabel.CENTER);
        }

        pnSimulation.add(lbPoint1);
        pnSimulation.add(lbPoint2);
        pnSimulation.add(lbPointM);
        
        str = str.trim();
        lbNotSort.setText(str);
        arrayCopy = Arrays.copyOf(array, array.length);
        modelSpinnerIndex.setMaximum(array.length-1);

        pnSimulation.validate();
        pnSimulation.repaint();
        setState(1);
    }
    
    public void createHandmadeArrays(){
        deleteArrays();
        lbNotSort.setText("");
        length = (Integer) spLength.getValue();

        lbArrays = new JLabel[length];
        array = new int[length];
        int num = 0;
        boolean kt = true;
        String str = "";
        String input = "";
        
        for(int i = 0; i<length; i++){
            do{
                kt = true;
                input = JOptionPane.showInputDialog(rootPane, "Nhập phần tử thứ " + i + ": ", 
                        "Nhập dữ liệu", JOptionPane.QUESTION_MESSAGE);
                try {
                    num = Integer.parseInt(input);
                    if(num<0 || num>100){
                        kt = false;
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập vào số nguyên từ 0 đến 100");
                    }
                }catch(Exception e) {
                    kt = false;
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập vào số nguyên từ 0 đến 100");
                }
                
            }while(!kt);
            
            str += String.valueOf(num) + " ";
            lbArrays[i] = new JLabel(String.valueOf(num));
            array[i] = num;
            pnSimulation.add(lbArrays[i]);

            //set size label
            lbArrays[i].setSize(50, 50);
            lbArrays[i].setOpaque(true);
            lbArrays[i].setForeground(Color.white);

            //set location label
            if (i == 0) {
                lbArrays[i].setLocation(((int) ((18 - length) * 0.5) * 70) + 100, 150);
            } else {
                lbArrays[i].setLocation(lbArrays[i - 1].getX() + 70, 150);
            }

            //set fonts
            lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));

            //set background color
            lbArrays[i].setBackground(Color.red);
            //set text alignment center
            lbArrays[i].setHorizontalAlignment(JLabel.CENTER);
            lbArrays[i].setVerticalAlignment(JLabel.CENTER); 
        }
        pnSimulation.add(lbPoint1);
        pnSimulation.add(lbPoint2);
        pnSimulation.add(lbPointM);
        
        str = str.trim();
        lbNotSort.setText(str);
        arrayCopy = Arrays.copyOf(array, array.length);
        modelSpinnerIndex.setMaximum(array.length-1);

        pnSimulation.validate();
        pnSimulation.repaint();
        setState(1);
    }

    public void deleteArrays() {
        for (int i = 0; i < length; i++) {
            lbArrays[i].setText("0");
            array[i] = 0;
            lbArrays[i].setVisible(false);
            pnSimulation.remove(lbArrays[i]);
        }

        lbPoint1.setText("");
        lbPoint2.setText("");
        lbPointM.setText("");
        pnSimulation.remove(lbPoint1);
        pnSimulation.remove(lbPoint2);
        pnSimulation.remove(lbPointM);

        for (int i = 0; i < curT; i++) {
            try {
                threads[i].interrupt();
            } catch (Exception e) {

            }
        }
        curT = -1;

        pnSimulation.revalidate();
        pnSimulation.repaint();
        //setState(0);
    }
    
    // </editor-fold>
    
    /**
     * Simulation sorts
     */
    
    public void highLight(int line) {
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lsCode.setSelectedIndex(line);
                    lsCode.ensureIndexIsVisible(line);
                    Thread.sleep(time);
                } catch (Exception e) {

                }
            }
        });
        threads[cur].start();
    }
    
    public void Swap(JLabel lb1, JLabel lb2) {
        int x1 = lb1.getX();
        int x2 = lb2.getX();
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }

                    lb1.setBackground(Color.orange);
                    lb2.setBackground(Color.orange);
                    while (lb1.getY() > 100) {
                        lb1.setLocation(lb1.getX(), lb1.getY() - 10);
                        lb2.setLocation(lb2.getX(), lb2.getY() + 10);
                        lbPointM.setLocation(x2, lbPointM.getY() + 10);
                        Thread.sleep(time);
                    }
                    while (lb1.getX() < x2) {
                        lb1.setLocation(lb1.getX() + 10, lb1.getY());
                        lb2.setLocation(lb2.getX() - 10, lb2.getY());
                        lbPointM.setLocation(lb2.getX(), 250);
                        Thread.sleep(time);
                    }
                    while (lb1.getY() < 140) {
                        lb1.setLocation(lb1.getX(), lb1.getY() + 10);
                        lb2.setLocation(lb2.getX(), lb2.getY() - 10);
                        lbPointM.setLocation(x1, lbPointM.getY() - 10);
                        Thread.sleep(time);
                    }
                    String txtLb1 = lb1.getText();
                    lb1.setText(lb2.getText());
                    lb2.setText(txtLb1);
                    lb1.setLocation(x1, 150);
                    lb2.setLocation(x2, 150);
                    lb1.setBackground(Color.blue);
                    lb2.setBackground(Color.blue);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void setlbPoint(JLabel lbPoint, int i, String s) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    if (i == -1) {
                        lbPoint.setText("");
                        return;
                    }
                    if (s.charAt(0) == 'm') {
                        lbPoint.setLocation(lbArrays[i].getX(), 200);
                        lbPoint.setText(s);
                    } else if (s.charAt(0) == 'k') {
                        lbPoint.setLocation(oriLocat[i], 200);
                        lbPoint.setText(s + i);
                    } else {
                        lbPoint.setLocation(lbArrays[i].getX(), 275);
                        lbPoint.setText(s + i);
                    }
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }
    
    //Waiting to end sorting
    public void waitEnd() {
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    
                    setState(3);
                    if (isDone) {
                        for (int i = 0; i < length; i++) {
                            lbArrays[i].setForeground(Color.white);
                            lbArrays[i].setBackground(Color.blue);
                        }
                        lbPoint1.setText("");
                        lbPoint2.setText("");
                        lbPointM.setText("");
                        JOptionPane.showMessageDialog(null, "Sắp xếp xong!");
                    } else {
                        for (int i = 0; i < length; i++) {
                            lbArrays[i] = new JLabel(String.valueOf(arrayCopy[i]));
                            array[i] = arrayCopy[i];
                            pnSimulation.add(lbArrays[i]);

                            //set size label
                            lbArrays[i].setSize(50, 50);
                            lbArrays[i].setOpaque(true);
                            lbArrays[i].setForeground(Color.white);

                            //set location label
                            if (i == 0) {
                                lbArrays[i].setLocation(((int) ((18 - length) * 0.5) * 70) + 100, 150);
                            } else {
                                lbArrays[i].setLocation(lbArrays[i - 1].getX() + 70, 150);
                            }

                            //set fonts
                            lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));

                            //set background color
                            lbArrays[i].setBackground(Color.red);
                            //set text alignment center
                            lbArrays[i].setHorizontalAlignment(JLabel.CENTER);
                            lbArrays[i].setVerticalAlignment(JLabel.CENTER);
                        }

                        pnSimulation.validate();
                        pnSimulation.repaint();
                        isDone = true;
                    }
                } catch (Exception e) {

                }
            }
        });
        threads[cur].start();
    }
    
    public void stopAllThreads() {
        for (int i = 0; i < curT; i++) {
            try {
                threads[i].interrupt();
            } catch (Exception e) {

            }
        }
        curT = -1;
    }

    // <editor-fold defaultstate="collapsed" desc="InterchangeSort">
    public void InterchangeSort() {
        if (isIncrease) {
            highLight(1);
            int i, j;
            for (i = 0; i < length; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                for (j = i + 1; j < length; j++) {
                    setlbPoint(lbPoint2, j, "j = ");
                    highLight(3);
                    highLight(4);
                    if (array[j] < array[i]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        highLight(5);
                        Swap(lbArrays[i], lbArrays[j]);
                    }
                }
            }
            highLight(0);
        } else {
            highLight(1);
            int i, j;
            for (i = 0; i < length; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                for (j = i + 1; j < length; j++) {
                    setlbPoint(lbPoint2, j, "j = ");
                    highLight(3);
                    highLight(4);
                    if (array[j] > array[i]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        highLight(5);
                        Swap(lbArrays[i], lbArrays[j]);
                    }
                }
            }
            highLight(0);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="SelectionSort">
    public void SelectionSort() {
        if (isIncrease) {
            highLight(1);
            int min, i, j;
            for (i = 0; i < length - 1; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                min = i;
                setlbPoint(lbPointM, i, "min");
                highLight(3);
                for (j = i + 1; j < length; j++) {
                    highLight(4);
                    highLight(5);
                    setlbPoint(lbPoint2, j, "j = ");
                    if (array[j] < array[min]) {
                        highLight(6);
                        min = j;
                        setlbPoint(lbPointM, j, "min");
                    }
                }
                if (min > i) {
                    int temp = array[min];
                    array[min] = array[i];
                    array[i] = temp;
                    highLight(7);
                    Swap(lbArrays[i], lbArrays[min]);
                }
            }
        } else {
            highLight(1);
            int min, i, j;
            for (i = 0; i < length - 1; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                min = i;
                setlbPoint(lbPointM, i, "max");
                highLight(3);
                for (j = i + 1; j < length; j++) {
                    highLight(4);
                    highLight(5);
                    setlbPoint(lbPoint2, j, "j = ");
                    if (array[j] > array[min]) {
                        highLight(6);
                        min = j;
                        setlbPoint(lbPointM, j, "max");
                    }
                }
                if (min > i) {
                    int temp = array[min];
                    array[min] = array[i];
                    array[i] = temp;
                    highLight(7);
                    Swap(lbArrays[i], lbArrays[min]);
                }
            }
        }
        highLight(0);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="BubbleSort">
    public void BubbleSort() {
        if (isIncrease) {
            highLight(1);
            int i, j;
            for (i = 0; i < length; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                for (j = length - 1; j > i; j--) {
                    highLight(3);
                    highLight(4);
                    setlbPoint(lbPoint2, j, "j = ");
                    if (array[j] < array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                        highLight(5);
                        Swap(lbArrays[j - 1], lbArrays[j]);
                    }
                }
            }
            highLight(0);
        } else {
            highLight(1);
            int i, j;
            for (i = 0; i < length; i++) {
                highLight(2);
                setlbPoint(lbPoint1, i, "i = ");
                for (j = length - 1; j > i; j--) {
                    highLight(3);
                    highLight(4);
                    setlbPoint(lbPoint2, j, "j = ");
                    if (array[j] > array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                        highLight(5);
                        Swap(lbArrays[j - 1], lbArrays[j]);
                    }
                }
            }
            highLight(0);
        }
    }
    
    public void Move(JLabel lb1, JLabel lb2, int pos) {
        int x1 = lb1.getX();
        int x2 = lb2.getX();
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lb1.setOpaque(true);
                    lb2.setOpaque(true);
                    lb1.setBackground(Color.orange);
                    lb2.setBackground(Color.orange);
                    while (lb1.getY() > 100) {
                        lb1.setLocation(lb1.getX(), lb1.getY() - 10);
                        Thread.sleep(time);
                    }
                    while (lb1.getX() > x2) {
                        lb2.setLocation(lb2.getX() + 10, lb2.getY());
                        lb1.setLocation(lb1.getX() - 10, lb1.getY());
                        Thread.sleep(time);
                    }
                    while (pos == 0 && lb1.getY() < 150) {
                        lb1.setLocation(lb1.getX(), lb1.getY() + 10);
                        Thread.sleep(time);
                    }
                    String txtLb1 = lb1.getText();
                    lb1.setText(lb2.getText());
                    lb2.setText(txtLb1);
                    int y1 = lb1.getY();
                    lb1.setLocation(x1, lb2.getY());
                    lb2.setLocation(x2, y1);
                    lb1.setBackground(Color.blue);
                    if (pos == 0) {
                        lb2.setBackground(Color.blue);
                    }
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="InsertionSort">
    public void InsertionSort() {
        if (isIncrease) {
            int pos, i;
            highLight(1);
            int x;
            highLight(2);
            for (i = 1; i < length; i++) {
                highLight(3);
                setlbPoint(lbPoint1, i, "i = ");
                x = array[i];
                highLight(4);
                pos = i - 1;
                highLight(5);
                while ((pos >= 0) && (array[pos] > x)) {
                    highLight(6);
                    setlbPoint(lbPoint2, pos, "j = ");
                    array[pos + 1] = array[pos];
                    highLight(7);
                    if (pos > 0 && array[pos - 1] <= x) {
                        Move(lbArrays[pos + 1], lbArrays[pos], 0);
                    } else {
                        Move(lbArrays[pos + 1], lbArrays[pos], pos);
                    }
                    pos--;
                    highLight(8);
                }
                highLight(9);
                array[pos + 1] = x;
                setlbPoint(lbPoint2, -1, null);
            }
            highLight(0);
        } else {
            int pos, i;
            highLight(1);
            int x;
            highLight(2);
            for (i = 1; i < length; i++) {
                highLight(3);
                setlbPoint(lbPoint1, i, "i = ");
                x = array[i];
                highLight(4);
                pos = i - 1;
                highLight(5);
                while ((pos >= 0) && (array[pos] < x)) {
                    highLight(6);
                    setlbPoint(lbPoint2, pos, "j = ");
                    array[pos + 1] = array[pos];
                    highLight(7);
                    if (pos > 0 && array[pos - 1] >= x) {
                        Move(lbArrays[pos + 1], lbArrays[pos], 0);
                    } else {
                        Move(lbArrays[pos + 1], lbArrays[pos], pos);
                    }
                    pos--;
                    highLight(8);
                }
                array[pos + 1] = x;
                highLight(9);
                setlbPoint(lbPoint2, -1, null);
            }
            highLight(0);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ShellSort">
    public void MoveShell(JLabel lb1, JLabel lb2, int next) {
        int x1 = lb1.getX();
        int x2 = lb2.getX();
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lb1.setBackground(Color.orange);
                    lb2.setBackground(Color.orange);
                    while (lb2.getY() < 200) {
                        if (lb1.getY() > 100) {
                            lb1.setLocation(lb1.getX(), lb1.getY() - 10);
                        }
                        lb2.setLocation(lb2.getX(), lb2.getY() + 10);
                        Thread.sleep(time);
                    }
                    while (lb1.getX() > x2) {
                        lb2.setLocation(lb2.getX() + 10, lb2.getY());
                        lb1.setLocation(lb1.getX() - 10, lb1.getY());
                        Thread.sleep(time);
                    }
                    while (lb2.getY() > 150) {
                        if (next == 0) {
                            lb1.setLocation(lb1.getX(), lb1.getY() + 10);
                        }
                        lb2.setLocation(lb2.getX(), lb2.getY() - 10);
                        Thread.sleep(time);
                    }
                    String txtLb1 = lb1.getText();
                    lb1.setText(lb2.getText());
                    lb2.setText(txtLb1);
                    int y1 = lb1.getY();
                    lb1.setLocation(x1, lb2.getY());
                    lb2.setLocation(x2, y1);
                    if (next == 0) {
                        lb1.setBackground(Color.blue);
                        lb2.setBackground(Color.blue);
                    } else {
                        lb1.setBackground(Color.blue);
                    }
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void ShellSort() {
        if (isIncrease) {
            int len, i, j, x;
            highLight(1);
            for (len = 11; len > 0; len /= 2) {
                highLight(2);
                for (i = len; i < length; i++) {
                    highLight(3);
                    setlbPoint(lbPoint1, i, "i = ");
                    x = array[i];
                    highLight(4);
                    j = i - len;
                    highLight(5);
                    while (j >= 0 && x < array[j]) {
                        highLight(6);
                        setlbPoint(lbPoint2, j, "j = ");
                        array[j + len] = array[j];
                        highLight(7);
                        if (len > 1) {
                            if (j < len) {
                                MoveShell(lbArrays[j + len], lbArrays[j], 0);
                            } else {
                                if (x >= array[j - len]) {
                                    MoveShell(lbArrays[j + len], lbArrays[j], 0);
                                } else {
                                    MoveShell(lbArrays[j + len], lbArrays[j], 1);
                                }
                            }
                        } else {
                            if (j < len) {
                                Move(lbArrays[j + len], lbArrays[j], 0);
                            } else {
                                if (x >= array[j - len]) {
                                    Move(lbArrays[j + len], lbArrays[j], 0);
                                } else {
                                    Move(lbArrays[j + len], lbArrays[j], 1);
                                }
                            }
                        }
                        j -= len;
                        highLight(8);
                    }
                    array[j + len] = x;
                    highLight(9);
                    setlbPoint(lbPoint2, -1, null);
                }
            }
            highLight(0);
        } else {
            int len, i, j, x;
            highLight(1);
            for (len = 11; len > 0; len /= 2) {
                highLight(2);
                for (i = len; i < length; i++) {
                    highLight(3);
                    setlbPoint(lbPoint1, i, "i = ");
                    x = array[i];
                    highLight(4);
                    j = i - len;
                    highLight(5);
                    while (j >= 0 && x > array[j]) {
                        highLight(6);
                        setlbPoint(lbPoint2, j, "j = ");
                        array[j + len] = array[j];
                        highLight(7);
                        if (len > 1) {
                            if (j < len) {
                                MoveShell(lbArrays[j + len], lbArrays[j], 0);
                            } else {
                                if (x <= array[j - len]) {
                                    MoveShell(lbArrays[j + len], lbArrays[j], 0);
                                } else {
                                    MoveShell(lbArrays[j + len], lbArrays[j], 1);
                                }
                            }
                        } else {
                            if (j < len) {
                                Move(lbArrays[j + len], lbArrays[j], 0);
                            } else {
                                if (x <= array[j - len]) {
                                    Move(lbArrays[j + len], lbArrays[j], 0);
                                } else {
                                    Move(lbArrays[j + len], lbArrays[j], 1);
                                }
                            }
                        }
                        j -= len;
                        highLight(8);
                    }
                    array[j + len] = x;
                    highLight(9);
                    setlbPoint(lbPoint2, -1, null);
                }
            }
        }
        highLight(0);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="HeapSort">
    public void MovetoLocation(JLabel lb1, int x, int y) {
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lb1.setBackground(Color.orange);
                    int x1 = lb1.getX();
                    int y1 = lb1.getY();
                    if (x1 < x && y1 < y) {
                        while (lb1.getX() < x) {
                            lb1.setLocation(lb1.getX() + 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    } else if (x1 >= x && y1 < y) {
                        while (lb1.getX() > x) {
                            lb1.setLocation(lb1.getX() - 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    } else if (x1 < x && y1 >= y) {
                        while (lb1.getY() > y) {
                            lb1.setLocation(x1, lb1.getY() - 10);
                            Thread.sleep(time);
                        }
                        while (lb1.getX() < x) {
                            lb1.setLocation(lb1.getX() + 10, y);
                            Thread.sleep(time);
                        }
                    } else if (x1 >= x && y1 >= y) {
                        while (lb1.getY() > y) {
                            lb1.setLocation(x1, lb1.getY() - 10);
                            Thread.sleep(time);
                        }
                        while (lb1.getX() > x) {
                            lb1.setLocation(lb1.getX() - 10, y);
                            Thread.sleep(time);
                        }
                    }
                    lb1.setBackground(Color.blue);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void HeapLocationInit() {
        int i, j = 0;
        int row = 1;
        int maxirow = 0;
        int[] xi = {600, 480, 720, 420, 540, 660, 780, 390, 450, 510, 570, 630, 690, 750, 810};
        int[] yi = {60, 110, 160, 210};
        MovetoLocation(lbArrays[0], xi[0], yi[0]);
        for (i = 0; i <= (length - 1) / 2; i++) {
            if (i > maxirow) {
                row++;
                maxirow = maxirow * 2 + 2;
            }
            j = i * 2 + 1;
            while (j <= i * 2 + 2 && j < length) {
                if (j == i * 2 + 1) {
                    MovetoLocation(lbArrays[j], xi[j], yi[row]);
                } else {
                    MovetoLocation(lbArrays[j], xi[j], yi[row]);
                }
                j++;
            }
        }
    }

    public void SwapinHeap(JLabel lb1, JLabel lb2) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lb1.setBackground(Color.orange);
                    lb2.setBackground(Color.orange);
                    int x1 = lb1.getX();
                    int x2 = lb2.getX();
                    int y1 = lb1.getY();
                    int y2 = lb2.getY();
                    while (lb2.getY() > y1) {
                        if (lb1.getY() > y1 - 50) {
                            lb1.setLocation(lb1.getX(), lb1.getY() - 10);
                        }
                        lb2.setLocation(lb2.getX(), lb2.getY() - 10);
                        Thread.sleep(time);
                    }
                    if (x2 < x1) {
                        while (lb2.getX() < x1) {
                            lb1.setLocation(lb1.getX() - 10, lb1.getY());
                            lb2.setLocation(lb2.getX() + 10, lb2.getY());
                            Thread.sleep(time);
                        }
                    } else {
                        while (lb2.getX() > x1) {
                            lb1.setLocation(lb1.getX() + 10, lb1.getY());
                            lb2.setLocation(lb2.getX() - 10, lb2.getY());
                            Thread.sleep(time);
                        }
                    }
                    while (lb1.getY() < y2) {
                        lb1.setLocation(lb1.getX(), lb1.getY() + 10);
                        Thread.sleep(time);
                    }
                    String txtLb1 = lb1.getText();
                    lb1.setText(lb2.getText());
                    lb2.setText(txtLb1);
                    lb1.setLocation(x1, y1);
                    lb2.setLocation(x2, y2);
                    lb1.setBackground(Color.blue);
                    lb2.setBackground(Color.blue);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void SwapHeapEnd(JLabel lb1, JLabel lb2, int xend) {
        MovetoLocation(lb1, xend, 260);
        MovetoLocation(lb2, 600, 60);
        SwapwithoutMoving(lb1, lb2);
    }

    public void SwapwithoutMoving(JLabel lb1, JLabel lb2) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    String txtLb1 = lb1.getText();
                    lb1.setText(lb2.getText());
                    lb2.setText(txtLb1);
                    int x = lb1.getX();
                    int y = lb1.getY();
                    lb1.setLocation(lb2.getX(), lb2.getY());
                    lb2.setLocation(x, y);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void Shift(int l, int r) {
        int x, i, j;
        highLight(23);
        i = l;
        highLight(24);
        j = i * 2 + 1;
        highLight(25);
        x = array[i];
        if (isIncrease) {
            while (j <= r) {
                highLight(26);
                highLight(27);
                if (j < r) {
                    highLight(28);
                    if (array[j] < array[j + 1]) {
                        highLight(29);
                        j++;
                    }
                }
                highLight(30);
                if (array[j] <= x) {
                    highLight(31);
                    return;
                } else {
                    highLight(33);
                    array[i] = array[j];
                    highLight(34);
                    array[j] = x;
                    SwapinHeap(lbArrays[i], lbArrays[j]);
                    highLight(35);
                    i = j;
                    highLight(36);
                    j = i * 2 + 1;
                    highLight(37);
                    x = array[i];
                }
            }
        } else {
            while (j <= r) {
                highLight(26);
                highLight(27);
                if (j < r) {
                    highLight(28);
                    if (array[j] > array[j + 1]) {
                        highLight(29);
                        j++;
                    }
                }
                highLight(30);
                if (array[j] >= x) {
                    highLight(31);
                    return;
                } else {
                    highLight(33);
                    array[i] = array[j];
                    highLight(34);
                    array[j] = x;
                    SwapinHeap(lbArrays[i], lbArrays[j]);
                    highLight(35);
                    i = j;
                    highLight(36);
                    j = i * 2 + 1;
                    highLight(37);
                    x = array[i];
                }
            }
        }
    }

    public void CreateHeap() {
        int l;
        highLight(14);
        l = length / 2 - 1;
        while (l >= 0) {
            highLight(15);
            highLight(16);
            Shift(l, length - 1);
            highLight(17);
            l--;
        }
    }

    public void HeapSort() {
        int r;
        int xend = ((int) ((18 - length) * 0.5) * 70) + 100 + (length - 1) * 70;
        HeapLocationInit();
        highLight(2);
        CreateHeap();
        highLight(3);
        r = length - 1;
        while (r > 0) {
            highLight(4);
            highLight(5);
            int x = array[0];
            array[0] = array[r];
            array[r] = x;
            SwapHeapEnd(lbArrays[0], lbArrays[r], xend);
            xend -= 70;
            highLight(6);
            r--;
            highLight(7);
            if (r > 0) {
                highLight(8);
                Shift(0, r);
            }
        }
        SwapHeapEnd(lbArrays[0], null, xend);
    }
    
    public void threadReLocate() {
        curT++;

        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    reLocate();
                } catch (Exception e) {

                }
            }
        });
        threads[cur].start();
    }

    public void reLocate() {
        for (int i = 0; i < length; i++) {
            //set location label
            if (i == 0) {
                lbArrays[i].setLocation(((int) ((18 - length) * 0.5) * 70) + 100, 150);
            } else {
                lbArrays[i].setLocation(lbArrays[i - 1].getX() + 70, 150);
            }
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="QuickSort">
    public void QuickSort() {
        QuickSortAl(0, length - 1);
        QuickSortAnimation();
        step = 0;
    }

    public void Coloring(JLabel lb1, Color c) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    lb1.setBackground(c);
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void Coloring(int left, int right, Color c) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    int i = left;
                    while (i <= right) {
                        if (i != (left + right) / 2) {
                            lbArrays[i].setBackground(c);
                        }
                        i++;
                    }
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void QuickSortAnimation() {
        int s, i, j;
        for (s = 0; s < step; s++) {
            i = lbI[s];
            j = lbJ[s];
            setlbPoint(lbPoint1, i, "i = ");
            setlbPoint(lbPoint2, j, "j = ");
            if (i != j) {
                Coloring(lbArrays[(lbL[s] + lbR[s]) / 2], Color.green);
                Coloring(lbL[s], lbR[s], Color.darkGray);
                Swap(lbArrays[i], lbArrays[j]);
            }
            if (lbL[s + 1] + lbR[s + 1] != lbL[s] + lbR[s]) {
                Coloring(lbArrays[(lbL[s] + lbR[s]) / 2], Color.blue);
                Coloring(lbL[s], lbR[s], Color.blue);
            }
        }
    }

    public void QuickSortAl(int left, int right) {
        if (isIncrease) {
            int i, j, x;
            x = array[(left + right) / 2];
            i = left;
            j = right;
            while (i < j) {
                while (array[i] < x) {
                    i++;
                }
                while (array[j] > x) {
                    j--;
                }
                if (i <= j) {
                    if (array[i] != array[j]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        if (i != j) {
                            lbL[step] = left;
                            lbR[step] = right;
                            lbI[step] = i;
                            lbJ[step] = j;
                            step++;
                        }
                    }
                    i++;
                    j--;
                }
            }
            if (left < j) {
                QuickSortAl(left, j);
            }
            if (i < right) {
                QuickSortAl(i, right);
            }
        } else {
            int i, j, x;
            x = array[(left + right) / 2];
            i = left;
            j = right;
            while (i < j) {
                while (array[i] > x) {
                    i++;
                }
                while (array[j] < x) {
                    j--;
                }
                if (i <= j) {
                    if (array[i] != array[j]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        if (i != j) {
                            lbL[step] = left;
                            lbR[step] = right;
                            lbI[step] = i;
                            lbJ[step] = j;
                            step++;
                        }
                    }
                    i++;
                    j--;
                }
            }
            if (left < j) {
                QuickSortAl(left, j);
            }
            if (i < right) {
                QuickSortAl(i, right);
            }
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MergeSort">
    public void PutUp(int left, int right) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    int mid = (left + right) / 2;
                    for (int i = left; i <= right; i++) {
                        if (i < mid + 1) {
                            lbArrays[i].setBackground(Color.green);
                        } else {
                            lbArrays[i].setBackground(Color.pink);
                        }
                    }
                    while (lbArrays[right].getY() > 50) {
                        for (int i = left; i <= right; i++) {
                            if (lbArrays[i].getY() > 50) {
                                lbArrays[i].setLocation(lbArrays[i].getX(), lbArrays[i].getY() - 10);
                            }
                        }
                        Thread.sleep(time);
                    }
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void PutDown(JLabel lb1, int x, int y) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    int x1 = lb1.getX();
                    lb1.setBackground(Color.orange);
                    while (lb1.getY() < 100) {
                        lb1.setLocation(x1, lb1.getY() + 10);
                        Thread.sleep(time);
                    }
                    int y1 = lb1.getY();
                    if (x1 < x) {
                        while (lb1.getX() < x) {
                            lb1.setLocation(lb1.getX() + 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    } else {
                        while (lb1.getX() > x) {
                            lb1.setLocation(lb1.getX() - 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    }
                    lb1.setBackground(Color.red);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void Relocat(int left, int right, int[] T) {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    for (int i = left; i <= right; i++) {
                        if (lbArrays[i].getX() != oriLocat[i]) {
                            lbArrays[i].setLocation(oriLocat[i], 150);
                            lbArrays[i].setText(T[i - left] + "");
                        }
                    }
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }

    public void Merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] T = new int[n1 + n2];
        int[] L = new int[n1];
        int[] R = new int[n2];
        int i, j, k;
        highLight(16);
        for (i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        highLight(18);
        for (j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
        }
        setlbPoint(lbPoint1, left, "i = ");
        setlbPoint(lbPoint2, mid + 1, "j = ");
        PutUp(left, right);
        if (isIncrease) {
            i = 0;
            j = 0;
            k = left;
            while (i < n1 && j < n2) {
                highLight(22);
                setlbPoint(lbPointM, k, "k = ");
                highLight(23);
                if (L[i] <= R[j]) {
                    setlbPoint(lbPoint1, left + i, "i = ");
                    highLight(24);
                    array[k] = L[i];
                    PutDown(lbArrays[left + i], oriLocat[k], 150);
                    highLight(25);
                    i++;
                } else {
                    setlbPoint(lbPoint2, mid + 1 + j, "j = ");
                    highLight(27);
                    array[k] = R[j];
                    PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                    highLight(28);
                    j++;
                }
                highLight(30);
                k++;
            }
            while (i < n1) {
                highLight(32);
                setlbPoint(lbPointM, k, "k = ");
                setlbPoint(lbPoint1, left + i, "i = ");
                highLight(33);
                array[k] = L[i];
                PutDown(lbArrays[left + i], oriLocat[k], 150);
                i++;
                k++;
            }
            while (j < n2) {
                highLight(37);
                setlbPoint(lbPointM, k, "k = ");
                setlbPoint(lbPoint2, mid + 1 + j, "j = ");
                highLight(38);
                array[k] = R[j];
                PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                j++;
                k++;
            }
        } else {
            i = 0;
            j = 0;
            k = left;
            while (i < n1 && j < n2) {
                highLight(22);
                setlbPoint(lbPointM, k, "k = ");
                highLight(23);
                if (L[i] >= R[j]) {
                    setlbPoint(lbPoint1, left + i, "i = ");
                    highLight(24);
                    array[k] = L[i];
                    PutDown(lbArrays[left + i], oriLocat[k], 150);
                    highLight(25);
                    i++;
                } else {
                    setlbPoint(lbPoint2, mid + 1 + j, "j = ");
                    highLight(27);
                    array[k] = R[j];
                    PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                    highLight(28);
                    j++;
                }
                highLight(30);
                k++;
            }
            while (i < n1) {
                highLight(32);
                setlbPoint(lbPointM, k, "k = ");
                setlbPoint(lbPoint1, left + i, "i = ");
                highLight(33);
                array[k] = L[i];
                PutDown(lbArrays[left + i], oriLocat[k], 150);
                i++;
                k++;
            }
            while (j < n2) {
                highLight(37);
                setlbPoint(lbPointM, k, "k = ");
                setlbPoint(lbPoint2, mid + 1 + j, "j = ");
                highLight(38);
                array[k] = R[j];
                PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                j++;
                k++;
            }
        }
        for (i = 0; i < n1 + n2; i++) {
            T[i] = array[left + i];
        }
        Relocat(left, right, T);
    }

    public void MergeSortAl(int left, int right) {
        highLight(1);
        if (left < right) {
            highLight(2);
            int mid = (left + right) / 2;
            MergeSortAl(left, mid);
            MergeSortAl(mid + 1, right);
            Merge(left, mid, right);
        }
    }

    public void MergeSort() {
        for (int i = 0; i < length; i++) {
            oriLocat[i] = lbArrays[i].getX();
        }
        MergeSortAl(0, length - 1);
    }
    // </editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnSimulation = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCreateRandom = new javax.swing.JButton();
        btnCreateHandmade = new javax.swing.JButton();
        spLength = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        btnResetRandom = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();
        btnResetHandmade = new javax.swing.JButton();
        spIndex = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsCode = new javax.swing.JList<>();
        pnIdea = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIdea = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        rdInterchange = new javax.swing.JRadioButton();
        rdSelection = new javax.swing.JRadioButton();
        rdBubble = new javax.swing.JRadioButton();
        rdInsertion = new javax.swing.JRadioButton();
        rdShell = new javax.swing.JRadioButton();
        rdHeap = new javax.swing.JRadioButton();
        rdQuick = new javax.swing.JRadioButton();
        rdMerge = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        rdIncrease = new javax.swing.JRadioButton();
        rdDecrease = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnDistroy = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        rdSlow = new javax.swing.JRadioButton();
        rdNormal = new javax.swing.JRadioButton();
        rdFast = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        lbNotSort = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mô Phỏng Thuật Toán Sắp Xếp");

        pnSimulation.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Khung chạy mô phỏng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        javax.swing.GroupLayout pnSimulationLayout = new javax.swing.GroupLayout(pnSimulation);
        pnSimulation.setLayout(pnSimulationLayout);
        pnSimulationLayout.setHorizontalGroup(
            pnSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnSimulationLayout.setVerticalGroup(
            pnSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dữ liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khởi tạo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel1.setText("Số phần tử:");

        btnCreateRandom.setText("Tạo ngẫu nhiên");
        btnCreateRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRandomActionPerformed(evt);
            }
        });

        btnCreateHandmade.setText("Tạo bằng tay");
        btnCreateHandmade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateHandmadeActionPerformed(evt);
            }
        });

        spLength.setModel(new javax.swing.SpinnerNumberModel(2, 2, 15, 1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spLength, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreateRandom)
                            .addComponent(btnCreateHandmade))))
                .addGap(37, 37, 37))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCreateHandmade, btnCreateRandom});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnCreateRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateHandmade)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đặt lại dữ liệu mảng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        btnResetRandom.setText("Ngẫu nhiên");
        btnResetRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetRandomActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ bàn phím"));

        jLabel3.setText("Phần tử thứ");

        jLabel4.setText("bằng");

        btnResetHandmade.setText("Nhập");
        btnResetHandmade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetHandmadeActionPerformed(evt);
            }
        });

        spIndex.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spIndex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetHandmade)
                        .addGap(30, 30, 30))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetHandmade))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnResetRandom)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResetRandom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code C/C++", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        lsCode.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        lsCode.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lsCode);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pnIdea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ý tưởng thuật toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        txtIdea.setEditable(false);
        txtIdea.setColumns(20);
        txtIdea.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtIdea.setLineWrap(true);
        txtIdea.setRows(5);
        txtIdea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtIdea);

        javax.swing.GroupLayout pnIdeaLayout = new javax.swing.GroupLayout(pnIdea);
        pnIdea.setLayout(pnIdeaLayout);
        pnIdeaLayout.setHorizontalGroup(
            pnIdeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnIdeaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );
        pnIdeaLayout.setVerticalGroup(
            pnIdeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnIdeaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điều khiển", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lựa chọn thuật toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        buttonGroup1.add(rdInterchange);
        rdInterchange.setSelected(true);
        rdInterchange.setText("Interchange Sort");

        buttonGroup1.add(rdSelection);
        rdSelection.setText("Selection Sort");

        buttonGroup1.add(rdBubble);
        rdBubble.setText("Bubble Sort");

        buttonGroup1.add(rdInsertion);
        rdInsertion.setText("Insertion Sort");

        buttonGroup1.add(rdShell);
        rdShell.setText("Shell Sort");

        buttonGroup1.add(rdHeap);
        rdHeap.setText("Heap Sort");

        buttonGroup1.add(rdQuick);
        rdQuick.setText("Quick Sort");

        buttonGroup1.add(rdMerge);
        rdMerge.setText("Merge Sort");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(rdInterchange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdSelection))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdBubble)
                            .addComponent(rdQuick)
                            .addComponent(rdShell))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdMerge)
                            .addComponent(rdHeap)
                            .addComponent(rdInsertion))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdInterchange)
                    .addComponent(rdSelection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdBubble)
                    .addComponent(rdInsertion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdShell)
                    .addComponent(rdHeap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdQuick)
                    .addComponent(rdMerge))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hướng sắp xếp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        buttonGroup2.add(rdIncrease);
        rdIncrease.setSelected(true);
        rdIncrease.setText("Tăng dần");

        buttonGroup2.add(rdDecrease);
        rdDecrease.setText("Giảm dần");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(rdIncrease)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdDecrease)
                .addGap(26, 26, 26))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdIncrease)
                .addComponent(rdDecrease))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quá trình", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        btnStart.setText("Bắt đầu");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnPause.setText("Tạm dừng");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnDistroy.setText("Hủy bỏ");
        btnDistroy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistroyActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tốc độ"));

        buttonGroup3.add(rdSlow);
        rdSlow.setText("Chậm");

        buttonGroup3.add(rdNormal);
        rdNormal.setSelected(true);
        rdNormal.setText("Trung bình");

        buttonGroup3.add(rdFast);
        rdFast.setText("Nhanh");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdSlow)
                .addGap(18, 18, 18)
                .addComponent(rdNormal)
                .addGap(18, 18, 18)
                .addComponent(rdFast)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdSlow)
                .addComponent(rdNormal)
                .addComponent(rdFast))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPause)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDistroy))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnPause)
                    .addComponent(btnDistroy)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnIdea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(pnIdea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dãy số chưa sắp xếp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        lbNotSort.setBackground(new java.awt.Color(255, 255, 255));
        lbNotSort.setFont(new java.awt.Font("Tahoma", 0, 34)); // NOI18N
        lbNotSort.setForeground(new java.awt.Color(0, 153, 204));
        lbNotSort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNotSort.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNotSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbNotSort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRandomActionPerformed
        createRandomArrays();
    }//GEN-LAST:event_btnCreateRandomActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        setState(2);
        for (int i = 0; i < length; i++) {
            lbArrays[i].setForeground(Color.white);
            lbArrays[i].setBackground(Color.red);
        }
        
        if (rdInterchange.isSelected()) {
            InterchangeSort();
        }
        if (rdSelection.isSelected()) {
            SelectionSort();
        }
        if(rdBubble.isSelected()){
            BubbleSort();
        }
        if(rdInsertion.isSelected()){
            InsertionSort();
        }
        if(rdShell.isSelected()){
            ShellSort();
        }
        if(rdHeap.isSelected()){
            HeapSort();
            threadReLocate();
        }
        if(rdQuick.isSelected()){
            QuickSort();
        }
        if(rdMerge.isSelected()){
            MergeSort();
        }

        waitEnd();
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnCreateHandmadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateHandmadeActionPerformed
        createHandmadeArrays();
    }//GEN-LAST:event_btnCreateHandmadeActionPerformed

    private void btnResetRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetRandomActionPerformed
        Random rand = new Random();
        String str = "";
        for (int i = 0; i < length; i++) {
            int ranNum = rand.nextInt(101) + 0;
            str += String.valueOf(ranNum) + " ";
            lbArrays[i].setText(String.valueOf(ranNum));
            lbArrays[i].setBackground(Color.red);
            lbArrays[i].setForeground(Color.white);
            array[i] = ranNum;
        }
        
        str = str.trim();
        lbNotSort.setText(str);
        arrayCopy = Arrays.copyOf(array, array.length);
        
    }//GEN-LAST:event_btnResetRandomActionPerformed

    private void btnResetHandmadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetHandmadeActionPerformed
        int index = (Integer)spIndex.getValue();
        boolean kt = true;
        int num = 0;
        
        try {
            num = Integer.parseInt(txtValue.getText());
            if (num < 0 || num > 100) {
                kt = false;
            }
        } catch (Exception e) {
            kt = false;
        }

        if (kt) {
            array[index] = num;
            arrayCopy[index] = num;
            lbArrays[index].setText(String.valueOf(num));

            //reset lbNotSort
            String str = "";
            for (int x : array) {
                str += x + " ";
            }
            str = str.trim();
            lbNotSort.setText(str);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập giá trị từ 0 đến 100");
        }
        
    }//GEN-LAST:event_btnResetHandmadeActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnDistroyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistroyActionPerformed
        isDone = false;
        deleteArrays();
    }//GEN-LAST:event_btnDistroyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GiaoDien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(GiaoDien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(GiaoDien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(GiaoDien.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                new GiaoDien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateHandmade;
    private javax.swing.JButton btnCreateRandom;
    private javax.swing.JButton btnDistroy;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnResetHandmade;
    private javax.swing.JButton btnResetRandom;
    private javax.swing.JButton btnStart;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNotSort;
    private javax.swing.JList<String> lsCode;
    private javax.swing.JPanel pnIdea;
    private javax.swing.JPanel pnSimulation;
    private javax.swing.JRadioButton rdBubble;
    private javax.swing.JRadioButton rdDecrease;
    private javax.swing.JRadioButton rdFast;
    private javax.swing.JRadioButton rdHeap;
    private javax.swing.JRadioButton rdIncrease;
    private javax.swing.JRadioButton rdInsertion;
    private javax.swing.JRadioButton rdInterchange;
    private javax.swing.JRadioButton rdMerge;
    private javax.swing.JRadioButton rdNormal;
    private javax.swing.JRadioButton rdQuick;
    private javax.swing.JRadioButton rdSelection;
    private javax.swing.JRadioButton rdShell;
    private javax.swing.JRadioButton rdSlow;
    private javax.swing.JSpinner spIndex;
    private javax.swing.JSpinner spLength;
    private javax.swing.JTextArea txtIdea;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
