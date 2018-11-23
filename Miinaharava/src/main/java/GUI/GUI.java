/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mycompany.miinaharava.Grid;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import static java.awt.SystemColor.text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Sade-Tuuli
 */
public class GUI implements ActionListener{
    
    JFrame frame = new JFrame("MineSweeper");
    JButton reset = new JButton("Reset");
    JButton[][] buttons = new JButton[20][20];
    Container buttonField = new Container();
    Grid grid;
    
    public GUI(Grid grid) {
        this.grid = grid;
        frame.setSize(1000,500);
        frame.setLayout(new BorderLayout());
        
        reset.addActionListener(this);
        //frame.setResizable(false); Ruutujenteksti ei näy jos liian pienenä ruutuna
        
        //Buttons
        buttonField.setLayout(new GridLayout(20,20));
        for(int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setText("");
                buttons[x][y].addActionListener(this);
                buttonField.add(buttons[x][y]);
                
            }
        }
        frame.add(reset,BorderLayout.EAST);
        frame.add(buttonField,BorderLayout.CENTER);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //resetille metodi
        if(e.getSource().equals(reset)) {
            grid.resetGrid();
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    buttons[x][y].setText("");
                }
            }
        } else {
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    if(e.getSource().equals(buttons[x][y])) {
                        if(grid.getNeighbors(x, y) == 10) {
                            showAllNumbers();
                        }
                        buttons[x][y].setText("" + grid.getNeighbors(x, y));
                    }
                }
            }
        }   
    }
    public void showAllNumbers() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                buttons[x][y].setText("" + grid.getNeighbors(x, y));
            }
        }
    }
}
