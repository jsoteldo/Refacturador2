package com.gs.mifarma.componentes;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class JTextFieldValidate extends JTextField {

    UpperCaseDocument document;
    private Pattern pattern;
    private Border wrongBorder = BorderFactory.createLineBorder(new Color(214, 36, 0));
    private Border defaultBorder;
    private boolean isObligatorio;
    private String pCadFormato="";
    
    private ArrayList pSimbolosPermitidos = new ArrayList();

    public JTextFieldValidate(String regEx, boolean isObligatorio) {
        super();
        this.isObligatorio = isObligatorio;
        pCadFormato=regEx;
        
        if (!isObligatorio)
            this.defaultBorder = BorderFactory.createLineBorder(Color.BLACK);
        else
            this.defaultBorder = BorderFactory.createLineBorder(new Color(0, 128, 153));


            this.pattern = Pattern.compile(pCadFormato);
        
        
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                limpia();
                if(!pCadFormato.trim().equalsIgnoreCase(ExpressionValidate.EMAIL))
                if (e.getKeyChar() != KeyEvent.VK_ENTER)
                    validateText(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    //validateText(e,true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }


        });

        this.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validateText(e);
            }

            public void focusGained(FocusEvent e) {
                validateText(e);
            }
        });
    }


    protected Document createDefaultModel() {
        document = new UpperCaseDocument();
        return document;
    }

    private void limpia() {
        this.setBorder(defaultBorder);
    }

    private void validateText(KeyEvent e, boolean vPinta) {
        if (!((getText().trim() + e.getKeyChar()).equalsIgnoreCase(getText().trim()))) {
            Matcher matcher = pattern.matcher(pOperaInfo(getText().trim() + e.getKeyChar()));
            if (!matcher.matches()) {
                if (vPinta) {
                    this.setBorder(wrongBorder);
                    e.consume();
                }
            } else {
                this.setBorder(defaultBorder);
            }
        }
    }
    
    public String pOperaInfo(String pCadena){
        String pResultado = pCadena;
        if(pCadFormato.trim().equalsIgnoreCase(ExpressionValidate.LETRA_CON_ESPACIOS)){
            pResultado = pCadena.replace(" ", "A");
        }
        
        
        if(pSimbolosPermitidos.size()>0){
            for(int i=0;i<pSimbolosPermitidos.size();i++){
                String pCaracter = pSimbolosPermitidos.get(i).toString();
                if(pCadFormato.trim().equalsIgnoreCase(ExpressionValidate.ALFANUMERICO))
                    pResultado = pResultado.replace(pCaracter, "A");
            }
        }
        
        return pResultado;
        
    }

    private void validateText(FocusEvent e) {
        //System.out.println("aaqui el foco +++ " + getText().trim());
        
       validaCampo();
    }

    public void setLengthText(int max) {
        document.setLengthText(max);
        if (!this.isObligatorio)
            this.defaultBorder = BorderFactory.createLineBorder(Color.BLACK);
        else
               this.defaultBorder = BorderFactory.createLineBorder(new Color(0, 128, 153));
        //setText("");
    }
    
    public void setText(String pCadena){
        limpia();
        //this.setText(pCadena);

        try {
            Document doc = getDocument();
            if (doc instanceof AbstractDocument) {
                ((AbstractDocument)doc).replace(0, doc.getLength(), pCadena,null);
            }
            else {
                doc.remove(0, doc.getLength());
                doc.insertString(0, pCadena, null);
            }
        } catch (BadLocationException e) {
            //UIManager.getLookAndFeel().provideErrorFeedback(JTextComponent.this);
        }        
        
        validaCampo();
        
    }

    public void addCharacterValidate(String pCaracter){
        pSimbolosPermitidos.add(pCaracter);
    }
    
    private void validaCampo() {
        if(getText().trim().length()>0||isObligatorio){
            if(pCadFormato.trim().equalsIgnoreCase(ExpressionValidate.EMAIL)){
                Pattern pattern_D = Pattern.compile(ExpressionValidate.EMAIL);
                Matcher matcher = pattern_D.matcher(pOperaInfo(getText().trim()));
                if (!matcher.matches()) {
                    this.setBorder(wrongBorder);
                    //((JTextField)pObject).selectAll();
                    //this.requestFocus();
                } else {
                    this.setBorder(defaultBorder);
                }
            }
            else{
                Matcher matcher = pattern.matcher(pOperaInfo(getText().trim()));
                if (!matcher.matches()) {
                    this.setBorder(wrongBorder);
                    //((JTextField)pObject).selectAll();
                    //this.requestFocus();
                } else {
                    this.setBorder(defaultBorder);
                }    
            } 
        }
        else{
            limpia();
        }        
    }
}
