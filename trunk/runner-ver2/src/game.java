/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import javax.microedition.lcdui.Canvas;
/*     */ import javax.microedition.lcdui.Graphics;
/*     */ import javax.microedition.lcdui.Image;
/*     */ 
/*     */ public class game extends Canvas
/*     */ {
/*     */   main mGR;
/*     */   int count;
/*     */   int BCount;
/*  11 */   int oppCnt = 0; int cnt1 = 0;
/*  12 */   float newScr = 10.0F;
/*     */   long time;
/*     */ 
/*     */   game(main m)
/*     */     throws IOException
/*     */   {
/*  19 */     this.mGR = m;
/*     */   }
/*     */ 
/*     */   protected void paint(Graphics g)
/*     */   {
/*     */   }
/*     */ 
/*     */   boolean HandleGame(int event, int x, int y)
/*     */   {
/*  51 */     switch (event)
/*     */     {
/*     */     case 0:
/*  55 */       this.mGR.mMenuSel = 0;
/*  56 */       if (CircRectsOverlap(0.9445F * this.mGR.TX, 0.935F * this.mGR.TY, this.mGR.mTex_Pause.getWidth() / 2, this.mGR.mTex_Pause.getHeight() / 2, x, y, 2.0F))
/*     */       {
/*  58 */         this.mGR.mMenuSel = 1;
/*     */       }
/*  60 */       if (x < getWidth() / 2)
/*     */       {
/*  62 */         if (this.mGR.mPlayer.Onair == 0)
/*     */         {
/*  66 */           this.mGR.mPlayer.Onair = 1;
/*  67 */           this.mGR.mPlayer.vy = this.mGR.Jump;
/*     */         }
/*     */ 
/*     */       }
/*  72 */       else if (this.mGR.mPlayer.Onair == 0)
/*  73 */         this.mGR.mPlayer.Onair = 2;
/*     */       else {
/*  75 */         this.mGR.mPlayer.Onair = 3;
/*     */       }
/*  77 */       break;
/*     */     case 2:
/*  86 */       if (CircRectsOverlap(0.9445F * this.mGR.TX, 0.915F * this.mGR.TY, this.mGR.mTex_Pause.getWidth() / 2, this.mGR.mTex_Pause.getHeight() / 2, x, y, 2.0F))
/*     */       {
/*  88 */         this.mGR.GameScreen = 10;
/*  89 */         this.mGR.soundstop(0);
/*  90 */         this.mGR.mMenuSel = 0;
/*     */       }
/*     */ 
/*  93 */       if (this.mGR.mPlayer.Onair == 2)
/*  94 */         this.mGR.mPlayer.Onair = 0;
/*     */       break;
/*     */     }
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   boolean HandlePause(int event, int x, int y)
/*     */   {
/* 102 */     if (event == 0)
/*     */     {
/* 104 */       this.mGR.mMenuSel = 0;
/* 105 */       if (CircRectsOverlap(0.285F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 107 */         this.mGR.mMenuSel = 1;
/*     */       }
/*     */ 
/* 110 */       if (CircRectsOverlap(0.5F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 112 */         this.mGR.mMenuSel = 2;
/*     */       }
/* 114 */       if (CircRectsOverlap(0.715F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 116 */         this.mGR.mMenuSel = 3;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 122 */     if (event == 2)
/*     */     {
/* 124 */       switch (this.mGR.mMenuSel)
/*     */       {
/*     */       case 1:
/* 127 */         if (CircRectsOverlap(0.285F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */         {
/* 129 */           this.mGR.GameScreen = 3; } break;
/*     */       case 2:
/* 135 */         if (CircRectsOverlap(0.5F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/* 136 */           this.mGR.GameScreen = 2; break;
/*     */       case 3:
/* 140 */         if (CircRectsOverlap(0.715F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/* 141 */           this.mGR.setValue = (!this.mGR.setValue); break;
/*     */       }
/* 143 */       this.mGR.mMenuSel = 0;
/*     */     }
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   boolean HandleGameover(int event, int x, int y)
/*     */   {
/* 150 */     if (event == 0)
/*     */     {
/* 152 */       this.mGR.mMenuSel = 0;
/* 153 */       if (CircRectsOverlap(0.4F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 155 */         this.mGR.mMenuSel = 1;
/*     */       }
/* 157 */       if (CircRectsOverlap(0.61F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 159 */         this.mGR.mMenuSel = 2;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 164 */     if (event == 2)
/*     */     {
/* 166 */       switch (this.mGR.mMenuSel)
/*     */       {
/*     */       case 1:
/* 169 */         if (CircRectsOverlap(0.4F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */         {
/* 172 */           this.mGR.GameScreen = 5; } break;
/*     */       case 2:
/* 176 */         if (CircRectsOverlap(0.61F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/* 177 */           this.mGR.GameScreen = 2;
/*     */         break;
/*     */       }
/* 180 */       this.mGR.mMenuSel = 0;
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */   boolean HandleOption(int event, int x, int y) {
/* 186 */     if (event == 0)
/*     */     {
/* 188 */       this.mGR.mMenuSel = 0;
/* 189 */       if (CircRectsOverlap(0.395F * this.mGR.TX,221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 191 */         this.mGR.mMenuSel = 1;
/*     */       }
/* 193 */       if (CircRectsOverlap(0.605F * this.mGR.TX, 221, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 195 */         this.mGR.mMenuSel = 2;
/*     */       }
/* 197 */       if (CircRectsOverlap(0.944F * this.mGR.TX, 0.935F * this.mGR.TY, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 199 */         this.mGR.mMenuSel = 3;
/*     */       }
/*     */     }
/* 202 */     if (event == 2)
/*     */     {
/* 204 */       switch (this.mGR.mMenuSel)
/*     */       {
/*     */       case 1:
/* 207 */         if (CircRectsOverlap(0.395F * this.mGR.TX, 0.69F * this.mGR.TY, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */         {
/* 209 */           this.mGR.setValue = (!this.mGR.setValue); } break;
/*     */       case 2:
/* 213 */         if (CircRectsOverlap(0.605F * this.mGR.TX, 0.69F * this.mGR.TY, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */         {
/* 215 */           this.mGR.GameScreen = 8; } break;
/*     */       case 3:
/* 219 */         if (CircRectsOverlap(0.944F * this.mGR.TX, 0.935F * this.mGR.TY, this.mGR.mTex_sel.getWidth() / 2, this.mGR.mTex_sel.getHeight() / 2, x, y, 2.0F))
/*     */         {
/* 221 */           this.mGR.GameScreen = 2;
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/* 226 */       this.mGR.mMenuSel = 0;
/*     */     }
/*     */ 
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   boolean HandleHelp(int event, int x, int y) {
/* 233 */     this.mGR.GameScreen = 6;
/* 234 */     return true;
/*     */   }
/*     */ 
/*     */   boolean HandleAboutus(int event, int x, int y) {
/* 238 */     if (event == 0)
/*     */     {
/* 240 */       if (CircRectsOverlap(0.944F * this.mGR.TX, 0.935F * this.mGR.TY, this.mGR.mTex_back.getWidth() / 2, this.mGR.mTex_back.getHeight() / 2, x, y, 2.0F))
/*     */       {
/* 242 */         this.mGR.mMenuSel = 1;
/*     */       }
/*     */     }
/* 245 */     if (event == 2)
/*     */     {
/* 247 */       if (CircRectsOverlap(0.944F * this.mGR.TX, 0.935F * this.mGR.TY, this.mGR.mTex_back.getWidth() / 2, this.mGR.mTex_back.getHeight() / 2, x, y, 2.0F)) {
/* 248 */         this.mGR.GameScreen = 2;
/*     */       }
/* 250 */       this.mGR.mMenuSel = 0;
/*     */     }
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */   void gameLogic()
/*     */   {
/* 259 */     this.count += 1;
/* 260 */     this.mGR.BGSPEED -= 1.0E-005F;
/* 261 */     if (this.mGR.GameScreen != 9)
/* 262 */       this.mGR.mScore += 1;
/* 263 */     if (((this.mGR.mPlayer.Onair == 0) && (this.mGR.GameScreen != 9)) || 
/* 268 */       (this.mGR.GameScreen != 9) || (
/* 274 */       (this.mGR.NewScore == 0) && (this.mGR.mScore > this.mGR.mHScore)))
/*     */     {
/* 277 */       this.newScr = 1.0F;
/* 278 */       this.mGR.NewScore += 1;
/*     */ 
/* 288 */       this.BCount = 0;
/*     */     }
/*     */ 
/* 291 */     for (int i = 0; i < this.mGR.moBase.length; i++)
/*     */     {
/* 293 */       if (this.mGR.moBase[i].x < -0.5F)
/*     */       {
/* 295 */         if (i > 0)
/*     */         {
/* 297 */           this.mGR.moBase[i].x = (this.mGR.moBase[(i - 1)].x + this.mGR.mTex_Base.getWidth() / this.mGR.TX);
/*     */         }
/*     */         else
/*     */         {
/* 301 */           this.mGR.moBase[i].x = (this.mGR.moBase[(this.mGR.moBase.length - 1)].x + this.mGR.mTex_Base.getWidth() / this.mGR.TX);
/*     */         }
/*     */       }
/* 304 */       this.mGR.moBase[i].x += this.mGR.BGSPEED;
/*     */     }
/* 306 */     this.mGR.mPlayer.y += this.mGR.mPlayer.vy;
/*     */     Runner tmp339_336 = this.mGR.mPlayer; tmp339_336.vy = ((float)(tmp339_336.vy + 0.01D));
/* 308 */     if (this.mGR.GameScreen == 3)
/*     */     {
/* 311 */       if ((this.mGR.mPlayer.Onair == 0) || (this.mGR.mPlayer.Onair == 1))
/*     */       {
/* 313 */         if (this.mGR.mPlayer.y > 1.0F - this.mGR.mTex_Base.getHeight() / this.mGR.TY - this.mGR.mTex_Run[0].getHeight() / this.mGR.TY / 2.0F)
/*     */         {
/* 316 */           this.mGR.mPlayer.vy = 0.0F;
/* 317 */           this.mGR.mPlayer.y = (1.0F - this.mGR.mTex_Base.getHeight() / this.mGR.TY - this.mGR.mTex_Run[0].getHeight() / this.mGR.TY / 2.0F);
/* 318 */           if (this.mGR.mPlayer.Onair == 1) {
/* 319 */             this.mGR.mPlayer.Onair = 0;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/* 325 */       else if (this.mGR.mPlayer.y > 1.0F - this.mGR.mTex_Base.getHeight() / this.mGR.TY - this.mGR.mTex_Spider[0].getHeight() / this.mGR.TY / 2.0F)
/*     */       {
/* 328 */         this.mGR.mPlayer.vy = 0.0F;
/*     */ 
/* 330 */         this.mGR.mPlayer.y = (1.0F - this.mGR.mTex_Base.getHeight() / this.mGR.TY - this.mGR.mTex_Spider[0].getHeight() / this.mGR.TY / 2.0F);
/* 331 */         if ((this.mGR.mPlayer.Onair == 1) || (this.mGR.mPlayer.Onair == 3)) {
/* 332 */           this.mGR.mPlayer.Onair = 0;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/* 339 */     else if (this.mGR.mPlayer.y > 1.2F)
/*     */     {
/* 341 */       this.mGR.soundstop(0);
/* 342 */       this.mGR.GameScreen = 11;
/*     */ 
/* 345 */       if (this.mGR.mHScore < this.mGR.mScore)
/*     */       {
/* 347 */         this.mGR.mHScore = this.mGR.mScore;
/* 348 */         this.mGR.WinnerScore(this.mGR.mScore + "");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 380 */     if (this.BCount >= 180)
/*     */     {
/* 382 */       long time = System.currentTimeMillis();
/* 383 */       for (int i = 0; i < this.mGR.moBird3.length; i++)
/*     */       {
/* 385 */         float vx = (float)(time % 10L + 1L);
/* 386 */         time /= 10L;
/* 387 */         float vy = (float)(time % 10L + 1L);
/* 388 */         if (time <= 0L)
/* 389 */           time = System.currentTimeMillis();
/* 390 */         this.mGR.moBird3[i].set(1.2F, randfloat() + 0.05F, -vx / 150.0F, -vy / 150.0F, (int)vx);
/*     */       }
/*     */ 
/* 394 */       this.BCount = 0;
/*     */     }
/*     */ 
/* 397 */     this.BCount += 1;
/*     */ 
/* 412 */     for (int i = 0; i < this.mGR.moBird3.length; i++)
/*     */     {
/* 414 */       this.mGR.moBird3[i].img += 1;
/* 415 */       this.mGR.moBird3[i].x += this.mGR.moBird3[i].vx;
/* 416 */       this.mGR.moBird3[i].y += this.mGR.moBird3[i].vy;
/*     */     }
/*     */ 
/* 419 */     long time = System.currentTimeMillis();
/* 420 */     for (int i = 0; i < this.mGR.moKachra.length; i++)
/*     */     {
/* 423 */       this.mGR.moKachra[i].x += this.mGR.moKachra[i].vx;
/* 424 */       this.mGR.moKachra[i].y += this.mGR.moKachra[i].vy;
/* 425 */       if ((this.mGR.moKachra[i].x < -1.5F) || (this.mGR.moKachra[i].y < 0.0F))
/*     */       {
/* 430 */         float vx = (float)(time % 10L + 1L);
/* 431 */         time /= 10L;
/* 432 */         float vy = (float)(time % 10L + 1L);
/* 433 */         if (time <= 0L)
/* 434 */           time = System.currentTimeMillis();
/* 435 */         this.mGR.moKachra[i].set(1.0F, randfloat() - 0.01F, -vx * 0.005F, -vy * 0.005F, Math.abs(get_rand()));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 440 */     float hieght = 0.0F;
/* 441 */     if ((this.mGR.mPlayer.Onair == 0) || (this.mGR.mPlayer.Onair == 1))
/* 442 */       hieght = this.mGR.mTex_Run[0].getHeight() / this.mGR.TY;
/*     */     else {
/* 444 */       hieght = this.mGR.mTex_Spider[0].getHeight() / this.mGR.TY;
/*     */     }
/* 446 */     for (int i = 0; i < this.mGR.mOpponent.length; i++)
/*     */     {
/* 448 */       this.mGR.mOpponent[i].x += this.mGR.BGSPEED;
/* 449 */       if (this.mGR.mOpponent[i].x < -1.3F)
/*     */       {
/* 451 */         if (i > 0)
/*     */         {
/* 453 */           this.mGR.mOpponent[i].x = (this.mGR.mOpponent[(i - 1)].x + 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 457 */           this.mGR.mOpponent[i].x = (this.mGR.mOpponent[(this.mGR.mOpponent.length - 1)].x + 1.0F);
/*     */         }
/* 459 */         float x = (float)(time % 10L + 1L);
/* 460 */         time /= 10L;
/* 461 */         if (time <= 0L) {
/* 462 */           time = System.currentTimeMillis();
/*     */         }
/*     */ 
/* 465 */         this.mGR.mOpponent[i].imgNo = ((int)(x % 10.0F));
/* 466 */         this.mGR.mOpponent[i].OppId = ((int)(x % 3.0F));
/*     */       }
/* 468 */       if (this.mGR.mOpponent[i].OppId == 0)
/*     */       {
/* 470 */         if (CircRectsOverlap(this.mGR.mOpponent[i].x, this.mGR.mOpponent[i].y, this.mGR.mTex_Opp[0].getWidth() / this.mGR.TX / 3.0F, this.mGR.mTex_Opp[0].getHeight() / this.mGR.TY / 2.0F, this.mGR.mPlayer.x, this.mGR.mPlayer.y, hieght / 2.0F))
/*     */         {
/* 473 */           if (this.mGR.GameScreen != 9);
/* 479 */           this.mGR.GameScreen = 9;
/* 480 */           this.mGR.mPlayer.vy = -0.07F;
/*     */         }
/*     */       }
/* 483 */       if (this.mGR.mOpponent[i].OppId == 1)
/*     */       {
/* 485 */         if (CircRectsOverlap(this.mGR.mOpponent[i].x, this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX, this.mGR.mTex_Opp[0].getWidth() / this.mGR.TX / 2.0F, this.mGR.mTex_Opp[0].getHeight() / this.mGR.TY / 2.0F, this.mGR.mPlayer.x, this.mGR.mPlayer.y, hieght / 2.0F))
/*     */         {
/* 488 */           if (this.mGR.GameScreen == 3)
/*     */           {
/* 490 */             this.mGR.mPlayer.vy = -0.07F;
/*     */           }
/*     */ 
/* 494 */           this.mGR.GameScreen = 9;
/*     */         }
/*     */       }
/* 497 */       if (this.mGR.mOpponent[i].OppId == 2)
/*     */       {
/* 499 */         if (CircRectsOverlap(this.mGR.mOpponent[i].x, this.mGR.mOpponent[i].y, this.mGR.mTex_Opp[0].getWidth() / this.mGR.TX / 2.0F, this.mGR.mTex_Opp[0].getHeight() / this.mGR.TY / 2.0F, this.mGR.mPlayer.x, this.mGR.mPlayer.y, hieght / 4.0F))
/*     */         {
/* 502 */           if (this.mGR.GameScreen == 3)
/*     */           {
/* 506 */             this.mGR.mPlayer.vy = -0.07F;
/*     */           }
/* 508 */           this.mGR.GameScreen = 9;
/*     */         }
/* 510 */         if (CircRectsOverlap(this.mGR.mOpponent[i].x, 0.01F + this.mGR.mOpponent[i].y + this.mGR.OPPDIFF, this.mGR.mTex_Opp[0].getWidth() / this.mGR.TX / 2.0F, this.mGR.mTex_Opp[0].getHeight() / this.mGR.TY / 2.0F, this.mGR.mPlayer.x, this.mGR.mPlayer.y, hieght / 4.0F))
/*     */         {
/* 513 */           if (this.mGR.GameScreen == 3)
/*     */           {
/* 517 */             this.mGR.mPlayer.vy = -0.07F;
/*     */           }
/* 519 */           this.mGR.GameScreen = 9;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   void DrawGamePlay(Graphics g)
/*     */   {
/* 539 */     g.setColor(115, 130, 150);
/* 540 */     g.fillRect(0, 0, getWidth(), getHeight());
/*     */ 
/* 543 */     gameLogic();
/* 544 */     if (this.mGR.GameScreen == 3)
/* 545 */       this.mGR.Soundplay(0);
/* 546 */     g.drawImage(this.mGR.mTex_BG[0], (int)(this.mGR.Bg1 * this.mGR.TX), 0, 20);
/* 547 */     g.drawImage(this.mGR.mTex_BG[0], (int)(this.mGR.Bg2 * this.mGR.TX), 0, 20);
/* 548 */     this.mGR.Bg1 += this.mGR.BGSPEED / 3.0F;
/* 549 */     this.mGR.Bg2 += this.mGR.BGSPEED / 3.0F;
/* 550 */     if (this.mGR.Bg1 < -1.0F)
/* 551 */       this.mGR.Bg1 = (this.mGR.Bg2 + 1.0F);
/* 552 */     if (this.mGR.Bg2 < -1.0F) {
/* 553 */       this.mGR.Bg2 = (this.mGR.Bg1 + 1.0F);
/*     */     }
/*     */ 
/* 569 */     for (int i = 0; i < this.mGR.moBase.length; i++)
/*     */     {
/* 571 */       g.drawImage(this.mGR.mTex_Base, (int)(this.mGR.moBase[i].x * this.mGR.TX), (int)((1.0F - this.mGR.mTex_Base.getHeight() / this.mGR.TY / 2.0F) * this.mGR.TY), 3);
/*     */     }
/*     */ 
/* 574 */     g.drawImage(this.mGR.mTex_Blastbg[(this.count % this.mGR.mTex_Blastbg.length)], (int)(this.mGR.mTex_Blastbg[0].getWidth() / this.mGR.TX / 2.0F * this.mGR.TX), (int)((1.0F - this.mGR.mTex_Blastbg[0].getHeight() / this.mGR.TY / 2.0F) * this.mGR.TY), 3);
/*     */ 
/* 578 */     for (int i = 0; i < this.mGR.moBird3.length; i++)
/*     */     {
/* 580 */       if ((this.mGR.moBird3[i].x > -1.1F) && (this.mGR.moBird3[i].x < 1.1F))
/*     */       {
/* 582 */         g.drawImage(this.mGR.mTex_Bird3[(this.mGR.moBird3[i].img % this.mGR.mTex_Bird3.length)], (int)(this.mGR.moBird3[i].x * this.mGR.TX), (int)(this.mGR.moBird3[i].y * this.mGR.TY), 3);
/*     */       }
/*     */     }
/*     */ 
/* 586 */     if (this.mGR.GameScreen == 9)
/*     */     {
/* 588 */       this.cnt1 += 1;
/* 589 */       if (this.cnt1 % 4 == 0)
/* 590 */         this.oppCnt += 1;
/* 591 */       g.drawImage(this.mGR.mTex_Die[(this.oppCnt % this.mGR.mTex_Die.length)], (int)(this.mGR.mPlayer.x * this.mGR.TX), (int)(this.mGR.mPlayer.y * this.mGR.TY), 3);
/*     */     }
/* 596 */     else if ((this.mGR.mPlayer.Onair == 0) || (this.mGR.mPlayer.Onair == 1))
/*     */     {
/* 598 */       g.drawImage(this.mGR.mTex_Run[(this.count % this.mGR.mTex_Run.length)], (int)(this.mGR.mPlayer.x * this.mGR.TX), (int)(this.mGR.mPlayer.y * this.mGR.TY), 3);
/*     */     }
/*     */     else
/*     */     {
/* 603 */       g.drawImage(this.mGR.mTex_Spider[(this.count % this.mGR.mTex_Spider.length)], (int)(this.mGR.mPlayer.x * this.mGR.TX), (int)(this.mGR.mPlayer.y * this.mGR.TY), 3);
/*     */     }
/*     */ 
/* 608 */     drawOpp(g);
/* 609 */     for (int i = 0; i < this.mGR.moKachra.length; i++)
/*     */     {
/* 611 */       g.drawImage(this.mGR.mTex_Kachra[(this.mGR.moKachra[i].img % this.mGR.mTex_Kachra.length)], (int)(this.mGR.moKachra[i].x * this.mGR.TX), (int)(this.mGR.moKachra[i].y * this.mGR.TY), 3);
/*     */     }
/*     */ 
/* 625 */     g.drawImage(this.mGR.mTex_Pause, (int)(0.9445F * this.mGR.TX), (int)(0.935F * this.mGR.TY), 3);
/* 626 */     if (this.mGR.mMenuSel == 1);
/* 631 */     this.mGR.Draw_number(g, this.mGR.mScore, (int)(0.0F * this.mGR.TX), (int)(0.0F * this.mGR.TY));
/*     */   }
/*     */ 
/*     */   public void DrawMenu(Graphics g)
/*     */   {
/* 642 */     g.drawImage(this.mGR.mTex_splash, 0, 0, 20);
/* 643 */     switch (this.mGR.mMenuSel)
/*     */     {
/*     */     case 1:
/* 646 */       g.drawImage(this.mGR.mTex_sel, 100, 220, 3);
/* 647 */       break;
/*     */     case 2:
/* 649 */       g.drawImage(this.mGR.mTex_sel, 140, 220, 3);
/* 650 */       break;
/*     */     case 3:
/* 652 */       g.drawImage(this.mGR.mTex_sel, 180, 220, 3);
/* 653 */       break;
/*     */     case 4:
/* 655 */       g.drawImage(this.mGR.mTex_sel, 220, 220, 3);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void DrawGameoverVPause(Graphics g)
/*     */   {
/* 663 */     g.drawImage(this.mGR.mTex_BG[0], 0, 0, 20);
/* 664 */     g.drawImage(this.mGR.mTex_Score, (int)(this.mGR.TX / 2.0F), 280, 3);
/*     */ 
/* 666 */     if (this.mGR.GameScreen == 10)
/*     */     {
/* 668 */       g.drawImage(this.mGR.mTex_gamepaused, (int)(this.mGR.TX / 2.0F - 3.0F), 207, 3);
/* 669 */       if (!this.mGR.setValue)
/* 670 */         g.drawImage(this.mGR.mTex_soff, (int)(0.715F * this.mGR.TX), 207+5, 3);
/*     */     }
/*     */     else
/*     */     {
/* 674 */       g.drawImage(this.mGR.mTex_gameover, (int)(this.mGR.TX / 2.0F + 1.0F), 207, 3);
/* 675 */       if (this.mGR.GameScreen == 5)
/*     */       {
/* 677 */         this.mGR.reset();
/*     */       }
/*     */     }
/* 680 */     switch (this.mGR.mMenuSel)
/*     */     {
/*     */     case 1:
/* 683 */       if (this.mGR.GameScreen == 10)
/* 684 */         g.drawImage(this.mGR.mTex_sel, (int)(0.285F * this.mGR.TX), 220, 3);
/*     */       else
/* 686 */         g.drawImage(this.mGR.mTex_sel, (int)(0.4F * this.mGR.TX), 220, 3);
/* 687 */       break;
/*     */     case 2:
/* 689 */       if (this.mGR.GameScreen == 10)
/* 690 */         g.drawImage(this.mGR.mTex_sel, (int)(0.5F * this.mGR.TX), 221, 3);
/*     */       else
/* 692 */         g.drawImage(this.mGR.mTex_sel, (int)(0.61F * this.mGR.TX), 221, 3);
/* 693 */       break;
/*     */     case 3:
/* 695 */       if (this.mGR.GameScreen == 10)
/*     */       {
/* 697 */         g.drawImage(this.mGR.mTex_sel, (int)(0.715F * this.mGR.TX), 221, 3);
/*     */       }break;
/*     */     }
/* 700 */     String strs = this.mGR.mScore + "";
/* 701 */     float dx = strs.length() * (this.mGR.ImgFont.getWidth() / this.mGR.TX / 2.0F);
/* 702 */     this.mGR.Draw_number(g, this.mGR.mScore, (int)(0.425F * this.mGR.TX) - (int)(dx / 2.0F), 269);
/*     */   }
/*     */ 
/*     */   public void DrawAbtus(Graphics g)
/*     */   {
/* 708 */     g.drawImage(this.mGR.mTex_BG[0], 0, 0, 20);
/* 709 */     g.drawImage(this.mGR.mTex_Aboutus, (int)this.mGR.TX / 2, (int)this.mGR.TY / 2, 3);
/* 710 */     g.drawImage(this.mGR.mTex_back, (int)(0.944F * this.mGR.TX), (int)(0.915F * this.mGR.TY), 3);
/*     */   }
/*     */ 
/*     */   public void DrawHelp(Graphics g) {
/* 714 */     g.drawImage(this.mGR.mTex_helpscr, 0, 0, 20);
/*     */   }
/*     */ 
/*     */   public void DrawOption(Graphics g) {
/* 718 */     g.drawImage(this.mGR.mTex_BG[0], 0, 0, 20);
/* 719 */     g.drawImage(this.mGR.mTex_options, (int)(this.mGR.TX / 2.0F), 220, 3);
/* 720 */     g.drawImage(this.mGR.mTex_Score, (int)(this.mGR.TX / 2.0F), 280, 3);
/* 721 */     g.drawImage(this.mGR.mTex_back, (int)(0.944F * this.mGR.TX), (int)(0.935F * this.mGR.TY), 3);
/*     */ 
/* 723 */     if (!this.mGR.setValue)
/* 724 */       g.drawImage(this.mGR.mTex_soff, (int)(0.395F * this.mGR.TX), 221, 3);
/* 725 */     switch (this.mGR.mMenuSel)
/*     */     {
/*     */     case 2:
/* 731 */       g.drawImage(this.mGR.mTex_sel, (int)(0.605F * this.mGR.TX), 221, 3);
/* 732 */       break;
/*     */     case 1:
/* 734 */       g.drawImage(this.mGR.mTex_sel, (int)(0.395F * this.mGR.TX), 221, 3);
/*     */     }
/* 736 */     String strs = this.mGR.mHScore + "";
/* 737 */     float dx = strs.length() * (this.mGR.ImgFont.getWidth() / this.mGR.TX / 2.0F);
/* 738 */     this.mGR.Draw_number(g, this.mGR.readRecords(), (int)(0.425F * this.mGR.TX) - (int)(dx / 2.0F), 269);
/*     */   }
/*     */ 
/*     */   public void drawOpp(Graphics g) {
/* 742 */     this.cnt1 += 1;
/* 743 */     if (this.cnt1 % 2 == 0) {
/* 744 */       this.oppCnt += 1;
/*     */     }
/* 746 */     for (int i = 0; i < this.mGR.mOpponent.length; i++)
/*     */     {
/* 748 */       if ((this.mGR.mOpponent[i].x > -1.1F) && (this.mGR.mOpponent[i].x < 1.1F))
/*     */       {
/* 750 */         if (this.mGR.mOpponent[i].OppId == 0)
/*     */         {
/* 752 */           switch (this.mGR.mOpponent[i].imgNo)
/*     */           {
/*     */           case 0:
/* 755 */             g.drawImage(this.mGR.mTex_Opp[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 756 */             break;
/*     */           case 1:
/* 758 */             g.drawImage(this.mGR.mTex_Opp1[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 759 */             break;
/*     */           case 2:
/* 761 */             g.drawImage(this.mGR.mTex_Opp2[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 762 */             break;
/*     */           case 3:
/* 764 */             g.drawImage(this.mGR.mTex_Opp3, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 765 */             break;
/*     */           case 4:
/* 767 */             g.drawImage(this.mGR.mTex_Opp4, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 768 */             break;
/*     */           case 5:
/* 770 */             g.drawImage(this.mGR.mTex_Opp5[(this.oppCnt % this.mGR.mTex_Opp5.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 771 */             break;
/*     */           case 6:
/* 773 */             g.drawImage(this.mGR.mTex_Opp6[(this.oppCnt % this.mGR.mTex_Opp6.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 774 */             break;
/*     */           case 7:
/* 776 */             g.drawImage(this.mGR.mTex_Opp7[(this.oppCnt % this.mGR.mTex_Opp7.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 777 */             break;
/*     */           case 8:
/* 779 */             g.drawImage(this.mGR.mTex_Opp8[(this.oppCnt % this.mGR.mTex_Opp8.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY - 0.05F), 3);
/* 780 */             break;
/*     */           case 9:
/* 782 */             g.drawImage(this.mGR.mTex_Opp9[(this.oppCnt % this.mGR.mTex_Opp9.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 788 */         if (this.mGR.mOpponent[i].OppId == 1)
/*     */         {
/* 790 */           switch (this.mGR.mOpponent[i].imgNo)
/*     */           {
/*     */           case 0:
/* 793 */             g.drawImage(this.mGR.mTex_Opp[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 794 */             break;
/*     */           case 1:
/* 796 */             g.drawImage(this.mGR.mTex_Opp1[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 797 */             break;
/*     */           case 2:
/* 799 */             g.drawImage(this.mGR.mTex_Opp2[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 800 */             break;
/*     */           case 3:
/* 802 */             g.drawImage(this.mGR.mTex_Opp3, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 803 */             break;
/*     */           case 4:
/* 805 */             g.drawImage(this.mGR.mTex_Opp4, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 806 */             break;
/*     */           case 5:
/* 808 */             g.drawImage(this.mGR.mTex_Opp5[(this.oppCnt % this.mGR.mTex_Opp5.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 809 */             break;
/*     */           case 6:
/* 811 */             g.drawImage(this.mGR.mTex_Opp6[(this.oppCnt % this.mGR.mTex_Opp6.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 812 */             break;
/*     */           case 7:
/* 814 */             g.drawImage(this.mGR.mTex_Opp7[(this.oppCnt % this.mGR.mTex_Opp7.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 815 */             break;
/*     */           case 8:
/* 817 */             g.drawImage(this.mGR.mTex_Opp8[(this.oppCnt % this.mGR.mTex_Opp8.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/* 818 */             break;
/*     */           case 9:
/* 820 */             g.drawImage(this.mGR.mTex_Opp9[(this.oppCnt % this.mGR.mTex_Opp9.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - this.mGR.OPPD1 - this.mGR.mTex_Run[0].getWidth() / 3 / this.mGR.TX) * this.mGR.TY), 3);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 825 */         if (this.mGR.mOpponent[i].OppId == 2)
/*     */         {
/* 827 */           switch (this.mGR.mOpponent[i].imgNo)
/*     */           {
/*     */           case 0:
/* 830 */             g.drawImage(this.mGR.mTex_Opp[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 831 */             g.drawImage(this.mGR.mTex_Opp1[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF) * this.mGR.TY), 3);
/* 832 */             break;
/*     */           case 1:
/* 834 */             g.drawImage(this.mGR.mTex_Opp1[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 835 */             g.drawImage(this.mGR.mTex_Opp2[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF) * this.mGR.TY), 3);
/* 836 */             break;
/*     */           case 2:
/* 838 */             g.drawImage(this.mGR.mTex_Opp2[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 839 */             g.drawImage(this.mGR.mTex_Opp3, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF) * this.mGR.TY), 3);
/* 840 */             break;
/*     */           case 3:
/* 842 */             g.drawImage(this.mGR.mTex_Opp3, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 843 */             g.drawImage(this.mGR.mTex_Opp4, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF) * this.mGR.TY), 3);
/* 844 */             break;
/*     */           case 4:
/* 846 */             g.drawImage(this.mGR.mTex_Opp4, (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)(this.mGR.mOpponent[i].y * this.mGR.TY), 3);
/* 847 */             g.drawImage(this.mGR.mTex_Opp5[(this.oppCnt % this.mGR.mTex_Opp5.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF - 0.03F) * this.mGR.TY), 3);
/* 848 */             break;
/*     */           case 5:
/* 850 */             g.drawImage(this.mGR.mTex_Opp5[(this.oppCnt % this.mGR.mTex_Opp5.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 851 */             g.drawImage(this.mGR.mTex_Opp6[(this.oppCnt % this.mGR.mTex_Opp6.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF - 0.03F) * this.mGR.TY), 3);
/* 852 */             break;
/*     */           case 6:
/* 854 */             g.drawImage(this.mGR.mTex_Opp6[(this.oppCnt % this.mGR.mTex_Opp6.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 855 */             g.drawImage(this.mGR.mTex_Opp7[(this.oppCnt % this.mGR.mTex_Opp7.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF - 0.03F) * this.mGR.TY), 3);
/* 856 */             break;
/*     */           case 7:
/* 858 */             g.drawImage(this.mGR.mTex_Opp7[(this.oppCnt % this.mGR.mTex_Opp7.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 859 */             g.drawImage(this.mGR.mTex_Opp8[(this.oppCnt % this.mGR.mTex_Opp8.length % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF - 0.03F) * this.mGR.TY), 3);
/* 860 */             break;
/*     */           case 8:
/* 862 */             g.drawImage(this.mGR.mTex_Opp8[(this.oppCnt % this.mGR.mTex_Opp8.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 863 */             g.drawImage(this.mGR.mTex_Opp9[(this.oppCnt % this.mGR.mTex_Opp9.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF - 0.03F) * this.mGR.TY), 3);
/* 864 */             break;
/*     */           case 9:
/* 866 */             g.drawImage(this.mGR.mTex_Opp9[(this.oppCnt % this.mGR.mTex_Opp9.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y - 0.015F) * this.mGR.TY), 3);
/* 867 */             g.drawImage(this.mGR.mTex_Opp[(this.oppCnt % this.mGR.mTex_Opp.length)], (int)(this.mGR.mOpponent[i].x * this.mGR.TX), (int)((this.mGR.mOpponent[i].y + this.mGR.OPPDIFF) * this.mGR.TY), 3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   boolean intersectCircleRect(int centerX, int centerY, int radius, int RectX, int RectY, int RectDX, int RectDY)
/*     */   {
/* 888 */     if ((abs(centerX - (RectX + RectDX / 2)) <= RectDX / 2 + radius) && (abs(centerY - (RectY + RectDY / 2)) <= RectDY / 2 + radius))
/* 889 */       return true;
/* 890 */     return false;
/*     */   }
/*     */ 
/*     */   int abs(int x)
/*     */   {
/* 895 */     return x > 0 ? x : -x;
/*     */   }
/*     */ 
/*     */   boolean cirInrSect(int x1, int y1, int r1, int x2, int y2, int r2) {
/* 899 */     int d = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
/* 900 */     if (d < r1 + r2)
/* 901 */       return true;
/* 902 */     return false;
/*     */   }
/*     */ 
/*     */   int get_rand() {
/* 906 */     Random r = new Random();
/*     */ 
/* 908 */     int myNum = (r.nextInt() & 0xFFFF) * 500 >> 16;
/* 909 */     return myNum;
/*     */   }
/*     */ 
/*     */   float randfloat() {
/* 913 */     Random r = new Random();
/* 914 */     return r.nextFloat();
/*     */   }
/*     */ 
/*     */   int sqrt(int x) {
/* 918 */     if (x < 3) {
/* 919 */       return 1;
/*     */     }
/*     */ 
/* 922 */     for (int i = 2; i <= x / 2 + 1; i++)
/*     */     {
/* 924 */       if (x == i * i)
/* 925 */         return i;
/* 926 */       if (x < i * i) {
/* 927 */         return i - 1;
/*     */       }
/*     */     }
/* 930 */     return x;
/*     */   }
/*     */ 
/*     */   boolean CircRectsOverlap(float CRX, float CRY, float CRDX, float CRDY, float centerX, float centerY, float radius)
/*     */   {
/* 935 */     if ((Math.abs(centerX - CRX) <= CRDX + radius) && (Math.abs(centerY - CRY) <= CRDY + radius))
/* 936 */       return true;
/* 937 */     return false;
/*     */   }
/*     */ 
/*     */   float screen2worldX(float a)
/*     */   {
/* 942 */     return a / getWidth();
/*     */   }
/*     */ 
/*     */   float screen2worldY(float a) {
/* 946 */     return a / getHeight();
/*     */   }
/*     */ }

/* Location:           C:\Users\TRITUEVIET\Desktop\Run_Till_Die_240x320_TS.jar
 * Qualified Name:     game
 * JD-Core Version:    0.6.2
 */