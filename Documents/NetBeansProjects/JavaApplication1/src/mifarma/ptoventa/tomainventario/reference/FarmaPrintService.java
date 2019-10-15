package mifarma.ptoventa.tomainventario.reference;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;

import mifarma.common.FarmaPRNUtility;
import mifarma.common.FarmaSearch;
import mifarma.common.FarmaUtility;
import mifarma.common.FarmaVariables;

public class FarmaPrintService
{
  private char[] activateBold = { '\033', 'E' };
  private char[] deactivateBold = { '\033', 'F' };
  private char[] activateCondensed = { '\017' };
  private char[] deactivateCondensed = { '\022' };
  private char[] activateUnderline = { '\033', '-', '1' };
  private char[] deactivateUnderline = { '\033', '-', '0' };
  private char[] activateDoubleWidthMode = { '\033', 'W', '1' };
  private char[] deactivateDoubleWidthMode = { '\033', 'W', '0' };
  private char[] pageREMITOLines = { '\033', 'C', '\032' };
  private char[] page10Lines = { '\033', 'C', '\n' };
  private char[] page24Lines = { '\033', 'C', '\030' };
  private char[] page36Lines = { '\033', 'C', '$' };
  private char[] page48Lines = { '\033', 'C', '0' };
  private char[] page66Lines = { '\033', 'C', 'B' };
  private char[] page999Lines = { '\033', 'C', '?' };
  private char[] pageBreak = { '\f' };
  private char[] carriageReturn = { '\r' };
  private PrintStream ps;
  private char[] pageSize;
  private int linesPerPage = 0;
  private int printArea = 0;
  private int actualLine = 0;
  private int headerSize = 0;
  private int footerSize = 0;
  private String devicePort = "";
  private boolean includeDatePage = false;
  private String reportDate = "";
  public static int pageNumber = 1;
  public static String numeroPagina = "1";
  private ArrayList arrayHeader = new ArrayList();
  private boolean settingHeader = false;
  private ArrayList arrayFooter = new ArrayList();
  private boolean settingFooter = false;
  private String programName = "";
  
  public FarmaPrintService(int pLinesPerPage, String pDevicePort, boolean pIncludeDatePage)
  {
    this.linesPerPage = pLinesPerPage;
    this.printArea = (this.linesPerPage - 4);
    this.devicePort = pDevicePort;
    this.includeDatePage = pIncludeDatePage;
    
    char[] pageLines = { '\033', 'C', (char)pLinesPerPage };
    
    this.pageSize = pageLines;
  }
  
  public boolean startPrintService()
  {
    boolean valorRetorno = false;
    try
    {
      FileOutputStream fos = new FileOutputStream(this.devicePort);
      this.ps = new PrintStream(fos);
      this.ps.print(this.deactivateCondensed);
      this.ps.print(this.pageSize);
      this.ps.print(this.carriageReturn);
      this.ps.println(" ");
      this.ps.print(this.carriageReturn);
      if (this.includeDatePage)
      {
        this.printArea -= 3;
        this.reportDate = FarmaSearch.getFechaHoraBD(2);
        
        printPageNumber();
      }
      valorRetorno = true;
    }
    catch (Exception errException)
    {
      errException.printStackTrace();
      
      enviaErrorCorreoPorDB(errException, null);
    }
    return valorRetorno;
  }
  
  public boolean startPrintService_02(String corr, String asunto, String msg)
  {
    boolean valorRetorno = false;
    try
    {
      FileOutputStream fos = new FileOutputStream(this.devicePort);
      this.ps = new PrintStream(fos);
      this.ps.print(this.deactivateCondensed);
      this.ps.print(this.pageSize);
      this.ps.print(this.carriageReturn);
      this.ps.println(" ");
      this.ps.print(this.carriageReturn);
      if (this.includeDatePage)
      {
        this.printArea -= 3;
        this.reportDate = FarmaSearch.getFechaHoraBD(2);
        
        printPageNumber();
      }
      valorRetorno = true;
    }
    catch (Exception errException)
    {
      errException.printStackTrace();
      System.out.println("Hola");
      enviaErrorCorreoPorDB_02(errException, corr, asunto, msg);
      valorRetorno = false;
    }
    return valorRetorno;
  }
  
  public void endPrintService()
  {
    for (int i = 1; i < getRemainingLines() + 2; i++)
    {
      this.ps.println(" ");
      this.ps.print(this.carriageReturn);
    }
    printArray(this.arrayFooter);
    if (this.includeDatePage) {
      printDatePage();
    }
    this.ps.print("\f");
    this.ps.flush();
    this.ps.close();
  }
  
  public void endPrintServiceSinCompletar()
  {
    printArray(this.arrayFooter);
    if (this.includeDatePage) {
      printDatePage();
    }
    this.ps.print("\f");
    this.ps.flush();
    this.ps.close();
  }
  
  public void endPrintServiceSinCompletarDelivery()
  {
    if (this.includeDatePage) {
      printDatePage();
    }
    this.ps.close();
  }
  
  public void setStartHeader()
  {
    this.settingHeader = true;
  }
  
  public void setEndHeader()
  {
    this.settingHeader = false;
    this.headerSize += this.actualLine;
    this.actualLine = 0;
    printArray(this.arrayHeader);
  }
  
  public void setStartFooter()
  {
    this.settingFooter = true;
  }
  
  public void setEndFooter()
  {
    this.settingFooter = false;
    this.footerSize += this.actualLine;
    this.actualLine = 0;
  }
  
  public void activateBold()
  {
    setProperties(this.activateBold);
  }
  
  public void deactivateBold()
  {
    setProperties(this.deactivateBold);
  }
  
  public void printBold(String pText, boolean pChangeLine)
  {
    activateBold();
    printLine(pText, pChangeLine);
    deactivateBold();
  }
  
  public void activateCondensed()
  {
    setProperties(this.activateCondensed);
  }
  
  public void deactivateCondensed()
  {
    setProperties(this.deactivateCondensed);
  }
  
  public void printCondensed(String pText, boolean pChangeLine)
  {
    activateCondensed();
    printLine(pText, pChangeLine);
    deactivateCondensed();
  }
  
  public void activateDoubleWidthMode()
  {
    setProperties(this.activateDoubleWidthMode);
  }
  
  public void deactivateDoubleWidthMode()
  {
    setProperties(this.deactivateDoubleWidthMode);
  }
  
  public void printDoubleWidthMode(String pText, boolean pChangeLine)
  {
    activateDoubleWidthMode();
    printLine(pText, pChangeLine);
    deactivateDoubleWidthMode();
  }
  
  public void printLine(String pText, boolean pChangeLine)
  {
    pText = pText.replaceAll("\\?", "A");
    pText = pText.replaceAll("\\?", "E");
    pText = pText.replaceAll("\\?", "I");
    pText = pText.replaceAll("\\?", "O");
    pText = pText.replaceAll("\\?", "U");
    pText = pText.replaceAll("\\?", "a");
    pText = pText.replaceAll("\\?", "e");
    pText = pText.replaceAll("\\?", "i");
    pText = pText.replaceAll("\\?", "o");
    pText = pText.replaceAll("\\?", "u");
    pText = pText.replaceAll("\\?", "n");
    pText = pText.replaceAll("\\?", "N");
    pText = pText.replaceAll("\\?", "");
    setPrintLine(pText, pChangeLine);
    if (pChangeLine) {
      this.actualLine += 1;
    }
    if (totalLine() > this.printArea) {
      internalPageBreak(true);
    }
  }
  
  private void setPrintLine(String pText, boolean pChangeLine)
  {
    if ((this.settingHeader) && (pChangeLine))
    {
      this.arrayHeader.add("1" + pText);
    }
    else if ((this.settingHeader) && (!pChangeLine))
    {
      this.arrayHeader.add("0" + pText);
    }
    else if ((this.settingFooter) && (pChangeLine))
    {
      this.arrayFooter.add("1" + pText);
    }
    else if ((this.settingFooter) && (!pChangeLine))
    {
      this.arrayFooter.add("0" + pText);
    }
    else if (pChangeLine)
    {
      this.ps.println(pText);
      this.ps.print(this.carriageReturn);
    }
    else
    {
      this.ps.print(pText);
    }
  }
  
  public int getRemainingLines()
  {
    return this.printArea - totalLine();
  }
  
  public void printBlankLine(int pLineNumber)
  {
    for (int i = 0; i < pLineNumber; i++) {
      printLine(" ", true);
    }
  }
  
  public void pageBreak()
  {
    internalPageBreak(false);
  }
  
  public void setProgramName(String pProgramName)
  {
    this.programName = pProgramName;
  }
  
  private void setProperties(char[] pProperties)
  {
    if (this.settingHeader) {
      this.arrayHeader.add(pProperties);
    } else if (this.settingFooter) {
      this.arrayFooter.add(pProperties);
    } else {
      this.ps.print(pProperties);
    }
  }
  
  private void printArray(ArrayList pPrintArray)
  {
    String textToPrint = "";
    for (int i = 0; i < pPrintArray.size(); i++) {
      if ((pPrintArray.get(i) instanceof String))
      {
        textToPrint = ((String)pPrintArray.get(i)).trim();
        if (textToPrint.substring(0, 1).equalsIgnoreCase("1"))
        {
          this.ps.println(textToPrint.substring(1, textToPrint.length()));
          this.ps.print(this.carriageReturn);
        }
        else
        {
          this.ps.print(textToPrint.substring(1, textToPrint.length()));
        }
      }
      else
      {
        this.ps.print((char[])pPrintArray.get(i));
      }
    }
  }
  
  private void printPageNumber()
  {
    if (!this.includeDatePage) {
      return;
    }
    this.ps.println(FarmaPRNUtility.fillBlanks(72) + "Pag. " + this.numeroPagina);
    this.ps.print(this.carriageReturn);
   // this.pageNumber += 1;
  }
  
  private void printDatePage()
  {
    if (!this.includeDatePage) {
      return;
    }
    this.ps.println("");
    this.ps.print(this.carriageReturn);
    this.ps.println((this.programName.length() > 0 ? this.programName + " - " : "") + "Emision : " + this.reportDate);
    
    this.ps.print(this.carriageReturn);
  }
  
  private void internalPageBreak(boolean pIsNormalPageBreak)
  {
    if (!pIsNormalPageBreak)
    {
      for (int i = 1; i < getRemainingLines() + 2; i++) {
        this.ps.println(" ");
      }
      this.ps.print(this.carriageReturn);
    }
    this.actualLine = 0;
    printArray(this.arrayFooter);
    printDatePage();
    setProperties(this.pageBreak);
    if (this.pageNumber == 2) {
      this.printArea += 1;
    }
    printBlankLine(1);
    
    printPageNumber();
    printArray(this.arrayHeader);
  }
  
  private int totalLine()
  {
    return this.headerSize + this.footerSize + this.actualLine;
  }
  
  public static void main(String[] args)
  {
    FarmaPrintService mainPRN = new FarmaPrintService(36, "LPT1", false);
    
    mainPRN.startPrintService();
    
    mainPRN.activateCondensed();
    for (int i = 1; i < 90; i++) {
      mainPRN.printLine("            " + String.valueOf(i), true);
    }
    mainPRN.deactivateCondensed();
    
    mainPRN.endPrintService();
  }
  
  public boolean startPrintServiceArchivoTexto()
  {
    boolean valorRetorno = false;
    try
    {
      FileOutputStream fos = new FileOutputStream(this.devicePort);
      
      this.ps = new PrintStream(fos);
      this.ps.print(this.deactivateCondensed);
      if (this.includeDatePage)
      {
        this.printArea -= 3;
        this.reportDate = FarmaSearch.getFechaHoraBD(2);
        
        printPageNumber();
      }
      valorRetorno = true;
    }
    catch (FileNotFoundException errFileNotFound)
    {
      errFileNotFound.printStackTrace();
    }
    catch (IOException errIO)
    {
      errIO.printStackTrace();
    }
    catch (SQLException errGetDateTime)
    {
      errGetDateTime.printStackTrace();
    }
    catch (Exception errException)
    {
      errException.printStackTrace();
    }
    return valorRetorno;
  }
  
  public void endPrintServiceArchivoTexto()
  {
    if (this.includeDatePage) {
      printDatePage();
    }
    this.ps.print("\f");
    this.ps.flush();
    this.ps.close();
  }
  
  public void printLineSinEspacio(String pText, boolean pChangeLine)
  {
    setPrintLineSinEspacio(pText, pChangeLine);
    if (pChangeLine) {
      this.actualLine += 1;
    }
    if (totalLine() > this.printArea) {
      internalPageBreak(true);
    }
  }
  
  private void setPrintLineSinEspacio(String pText, boolean pChangeLine)
  {
    if ((this.settingHeader) && (pChangeLine))
    {
      this.arrayHeader.add("1" + pText);
    }
    else if ((this.settingHeader) && (!pChangeLine))
    {
      this.arrayHeader.add("0" + pText);
    }
    else if ((this.settingFooter) && (pChangeLine))
    {
      this.arrayFooter.add("1" + pText);
    }
    else if ((this.settingFooter) && (!pChangeLine))
    {
      this.arrayFooter.add("0" + pText);
    }
    else if (pChangeLine)
    {
      this.ps.print(pText);
      this.ps.print(this.carriageReturn);
    }
    else
    {
      this.ps.print(pText);
    }
  }
  
  public int getActualLine()
  {
    return this.actualLine;
  }
  
  public void pageBreakArchivo()
  {
    internalPageBreak(true);
  }
  
  public String printPageNumberArchivo()
  {
    return "Folio: " + this.pageNumber++;
  }
  
  public int getPageNumber()
  {
    return this.pageNumber;
  }
  
  public void printCutLine(String pText, int pCharPerLine, int pLeftWhiteSpace)
  {
    int lengthText = pText.length();
    int linesText = lengthText / pCharPerLine;
    for (int i = 0; i <= linesText; i++) {
      if (i == linesText) {
        printLine(FarmaPRNUtility.llenarBlancos(pLeftWhiteSpace) + pText.substring(i * pCharPerLine, lengthText), true);
      } else {
        printLine(FarmaPRNUtility.llenarBlancos(pLeftWhiteSpace) + pText.substring(i * pCharPerLine, (i + 1) * pCharPerLine), true);
      }
    }
  }
  
  public static void enviaErrorCorreoPorDB(Exception pMessage, String pCorrelativo)
  {
    if (FarmaVariables.vEmail_Destinatario_Error_Impresion.trim().equalsIgnoreCase(""))
    {
      FarmaVariables.vEmail_Destinatario_Error_Impresion = "dubilluz;operador;jmiranda;lpanduro";
      System.out.println("NO ENTRA IMPR: " + FarmaVariables.vEmail_Destinatario_Error_Impresion);
    }
    FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, FarmaVariables.vEmail_Destinatario_Error_Impresion, "Error al Imprimir Pedido Completo ", "Error de Impresi?n StartPrintService", "Se produjo un error al imprimir un pedido :<br>Correlativo : " + pCorrelativo + "<br>" + "IP : " + FarmaVariables.vIpPc + "<br>" + "Error: " + pMessage, "");
    
    System.out.println("Error Impresion: " + FarmaVariables.vEmail_Destinatario_Error_Impresion);
  }
  
  public static void enviaErrorCorreoPorDB_02(Exception objExc, String corr, String asunto, String msg)
  {
    if (FarmaVariables.vEmail_Destinatario_Error_Impresion.trim().equalsIgnoreCase("")) {
      FarmaVariables.vEmail_Destinatario_Error_Impresion = "dubilluz;operador;jmiranda;lpanduro";
    }
    FarmaUtility.enviaCorreoPorBD(FarmaVariables.vCodGrupoCia, FarmaVariables.vCodLocal, FarmaVariables.vEmail_Destinatario_Error_Impresion, asunto, "Error de Impresion StartPrintService", msg + ":<br>Correlativo : " + corr + "<br>IP : " + FarmaVariables.vIpPc + "<br>Error : " + objExc, "");
  }
  
    // DFLORES: 30/01/2019
    public void setPageNumber(int pNumber){
        this.pageNumber = pNumber; 
    }
}
