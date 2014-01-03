/*    */ import java.io.PrintStream;
/*    */ import javax.microedition.lcdui.Display;
/*    */ import javax.microedition.media.Player;
/*    */ import javax.microedition.midlet.MIDlet;
/*    */ 
/*    */ public class RunTilldie extends MIDlet
/*    */ {
/* 12 */   main g = null;
/*    */   public boolean gamestart;
/*    */ 
/*    */   public void startApp()
/*    */   {
/* 16 */     System.out.println("   ~~~~~~~~  ");
/*    */     try {
/* 18 */       System.out.println("   !!!!!!!!!!!!!!!!  ");
/* 19 */       if (!this.gamestart)
/*    */       {
/* 21 */         this.g = new main(this);
/* 22 */         Display.getDisplay(this).setCurrent(this.g);
/* 23 */         Thread myThread = new Thread(this.g);
/* 24 */         myThread.start();
/* 25 */         this.gamestart = true;
/*    */       }
/*    */       else
/*    */       {
/* 29 */         if (this.g.GameScreen == 10)
/* 30 */           this.g.GameScreen = 10;
/* 31 */         if (this.g.GameScreen == 3)
/* 32 */           this.g.GameScreen = 10;
/* 33 */         if (this.g.GameScreen == 11)
/* 34 */           this.g.GameScreen = 11;
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 38 */       System.out.println("   !!!!!!!!!!!!!!!!  " + e.toString());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void pauseApp()
/*    */   {
/*    */     try {
/* 45 */       this.g.mp3player[0].stop();
/* 46 */       if (this.g.GameScreen == 3)
/* 47 */         this.g.GameScreen = 10;
/* 48 */       if (this.g.GameScreen == 10)
/* 49 */         this.g.GameScreen = 10;
/* 50 */       if (this.g.GameScreen == 11)
/* 51 */         this.g.GameScreen = 11;
/*    */     }
/*    */     catch (Exception e) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void destroyApp(boolean unconditional) {
/*    */     try {
/* 59 */       this.g.mp3player[0].stop();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\TRITUEVIET\Desktop\Run_Till_Die_240x320_TS.jar
 * Qualified Name:     RunTilldie
 * JD-Core Version:    0.6.2
 */