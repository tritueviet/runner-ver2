/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintStream;
/*      */ import javax.microedition.io.Connector;
/*      */ import javax.microedition.io.HttpConnection;
/*      */ import javax.microedition.lcdui.Canvas;
/*      */ import javax.microedition.lcdui.Command;
/*      */ import javax.microedition.lcdui.CommandListener;
/*      */ import javax.microedition.lcdui.Display;
/*      */ import javax.microedition.lcdui.Displayable;
/*      */ import javax.microedition.lcdui.Graphics;
/*      */ import javax.microedition.lcdui.Image;
/*      */ import javax.microedition.media.Manager;
/*      */ import javax.microedition.media.MediaException;
/*      */ import javax.microedition.media.Player;
/*      */ import javax.microedition.midlet.MIDlet;
/*      */ import javax.microedition.rms.RecordStore;
/*      */ import javax.microedition.rms.RecordStoreNotOpenException;
/*      */
/*      */ public final class main extends Canvas
        /*      */ implements Runnable, CommandListener /*      */ {
    /*      */ MIDlet mid;
    /*   32 */ private RecordStore rs = null;
    /*      */ static final String WIN_REC_STORE1 = "Blur Runner";
    /*   35 */ boolean press_left = false;
    /*   36 */ boolean press_right = false;
    /*   37 */ boolean setValue = true;
    /*      */ static final int GameSplash = 1;
    /*      */ static final int GameMenu = 2;
    /*      */ static final int GamePlay = 3;
    /*      */ static final int GameStart = 4;
    /*      */ static final int GameLoading = 5;
    /*      */ static final int GameHightScore = 6;
    /*      */ static final int GameHelp4play = 7;
    /*      */ static final int GameHelp = 8;
    /*      */ static final int GameGoing2Over = 9;
    /*      */ static final int GamePause = 10;
    /*      */ static final int GameOver = 11;
    /*      */ static final int GameAbt = 12;
    /*      */ static final int GameADD1 = 13;
    /*      */ static final int GameADD2 = 14;
    /*      */ int GameScreen;
    /*      */ int splash_check;
    /*      */ int mScore;
    /*      */ int NewScore;
    /*      */ int mHScore;
    /*      */ int mMenuSel;
    /*      */ float Bg1;
    /*      */ float Bg2;
    /*   55 */ float BGSPEED = 0.04F;
    float OPPDIFF = -0.18F;
    float OPPD1 = 0.05F;
    float Jump = -0.042F;
    /*   56 */ float TX = 240.0F;
    float TY = getHeight()*1.F;
    /*      */ Bird[] moBird3;
    /*      */ Bird[] moKachra;
    /*      */ Building[] moBase;
    /*   60 */ Building moAir = new Building();
    /*      */ Opponent[] mOpponent;
    /*      */ game mGame;
    /*   63 */ Runner mPlayer = new Runner();
    /*      */ Display dis;
    /*      */ Image ImgFont;
    /*      */ Image[] mTex_Run;
    /*      */ Image[] mTex_Spider;
    /*      */ Image[] mTex_Font;
    /*      */ Image[] mTex_BG;
    /*      */ Image mTex_Base;
    /*      */ Image mTexMano;
    /*      */ Image mTex_splash;
    /*      */ Image mTex_sel;
    /*      */ Image mTex_soff;
    /*      */ Image mTex_helpscr;
    /*      */ Image[] mTex_Bird3;
    /*      */ Image[] mTex_Kachra;
    /*      */ Image[] mTex_Blastbg;
    /*      */ Image[] mTex_Opp;
    /*      */ Image[] mTex_Opp1;
    /*      */ Image[] mTex_Opp2;
    /*      */ Image[] mTex_Opp5;
    /*      */ Image[] mTex_Opp6;
    /*      */ Image[] mTex_Opp7;
    /*      */ Image[] mTex_Opp8;
    /*      */ Image[] mTex_Opp9;
    /*      */ Image[] mTex_Die;
    /*      */ Image mTex_Opp3;
    /*      */ Image mTex_Opp4;
    /*      */ Image mTex_back;
    /*      */ Image mTex_options;
    /*      */ Image mTex_Aboutus;
    /*      */ Image mTex_gamepaused;
    /*      */ Image mTex_gameover;
    /*      */ Image mTex_Score;
    /*      */ Image mTex_Pause;
    /*      */ private int score1;
    /*      */ public Player[] mp3player;
    /*      */ String ResMsg;
    /*   81 */ String Redirect = null;
    /*      */ String newCallbackId;
    /*   83 */ String ZONEID = "100";
    /*      */ String incomming;
    /*      */ String outgoing;
    /*      */ Image mTexAdd2;
    /*      */ Image mTexAdd1;
    /*      */ String URL;
    /*      */ Image download;
    /*      */ Image skip;
    /*      */ Image exit;
    /*      */ boolean Adlink;
    /*      */
    /*      */ public main(MIDlet midlet)
            
            /*      */ throws IOException, RecordStoreNotOpenException /*      */ {
        /*   91 */ this.mid = midlet;
        /*   92 */ setFullScreenMode(true);
        /*   93 */ openRecStore();
        /*   94 */ this.mHScore = readRecords();
        /*   95 */ this.GameScreen = 1;
        /*   96 */ this.TX = getWidth();
        /*   97 */ this.TY = getHeight();
        /*   98 */ load();
        /*      */ try {
            /*  100 */ loadSound();
        } catch (Exception e) {
            /*      */        }
        /*  102 */ this.dis = Display.getDisplay(this.mid);
        /*      */
        /*  104 */ Canvas canvas = new Canvas() /*      */ {
            /*      */ protected void paint(Graphics arg0) /*      */ {
                /*      */            }
            /*      */        };
        /*  110 */ boolean pointer = canvas.hasPointerEvents();
        /*      */    }
    /*      */
    /*      */ void load() throws IOException /*      */ {
        /*  115 */ loadbird();
        /*  116 */ loadHanuman();
        /*  117 */ loadopponent();
        Resize re = new Resize();
        /*  118 */ this.mTex_back = Image.createImage("/backde.png");
        /*  119 */ this.mTex_helpscr = re.CreateScaledImage(Image.createImage("/help240x320.jpg"), getWidth(), getHeight());
        /*  120 */ if (getHeight()>350 ) this.mTex_splash = Image.createImage("/splash.jpg");
        else this.mTex_splash = Image.createImage("/splash2.jpg");
        /*  121 */ this.mTex_sel = Image.createImage("/selection.png");
        /*  122 */ this.mTex_soff = Image.createImage("/soff.png");
        /*  123 */ this.mTexMano = Image.createImage("/mano.png");
        /*  124 */ this.ImgFont = Image.createImage("/font_strip.png");
        /*  125 */ this.mTex_gamepaused = add("/gamepaused.png");
        /*  126 */ this.mTex_gameover = add("/gameover.png");
        /*  127 */ this.mTex_Aboutus = add("/aboutus.png");
        /*  128 */ this.mTex_Score = add("/score.png");
        /*  129 */ this.mTex_BG = new Image[1];
        /*  130 */ this.mTex_BG[0] = add("/bg.jpg");
        this.mTex_BG[0] = re.CreateScaledImage(this.mTex_BG[0], getWidth(), getHeight());
        this.mTex_Base = add("/bridge.png");
        this.mTex_options = add("/options.png");
        this.mTex_Pause = add("/pausede.png");
        this.mTex_Blastbg = new Image[5];

        for (int i = 0; i < this.mTex_Blastbg.length; i++) /*      */ {
            this.mTex_Blastbg[i] = add("/blastbg" + i + ".png");
        }

        this.moBird3 = new Bird[10];
        for (int i = 0; i < this.moBird3.length; i++) {
            this.moBird3[i] = new Bird();
        }
        this.moKachra = new Bird[10];
        for (int i = 0; i < this.moKachra.length; i++) {
            this.moKachra[i] = new Bird();
        }

        this.moBase = new Building[5];
        for (int i = 0; i < this.moBase.length; i++) {
            this.moBase[i] = new Building();
        }
        this.mOpponent = new Opponent[10];

        for (int i = 0; i < this.mOpponent.length; i++) /*  164 */ {
            this.mOpponent[i] = new Opponent();
        }
        this.mMenuSel = 1;
        LoadADImg();
    }

    void loadbird() /*      */ {
        this.mTex_Bird3 = new Image[5];
        Image b = add("/birdwhite.png");
        for (int i = 0; i < this.mTex_Bird3.length; i++) {
            this.mTex_Bird3[i] = Image.createImage(b, i * b.getWidth() / this.mTex_Bird3.length, 0, b.getWidth() / this.mTex_Bird3.length, b.getHeight(), 0);
        }
        this.mTex_Kachra = new Image[4];
        for (int i = 0; i < this.mTex_Kachra.length; i++) /*      */ {
            this.mTex_Kachra[i] = add("/dust" + i + ".png");
        }
    }

    void loadSound() throws IOException, MediaException {
        this.mp3player = new Player[1];
        this.mp3player[0] = Manager.createPlayer(getClass().getResourceAsStream("/bg2.mid"), "audio/midi");
    }

    void loadopponent() {
        this.mTex_Opp = new Image[4];
        this.mTex_Opp1 = new Image[4];
        this.mTex_Opp2 = new Image[4];
        this.mTex_Opp5 = new Image[8];
        this.mTex_Opp6 = new Image[8];
        this.mTex_Opp7 = new Image[8];
        this.mTex_Opp8 = new Image[8];
        this.mTex_Opp9 = new Image[8];
        this.mTex_Die = new Image[4];
        Image opp = add("/gear0.png");
        for (int i = 0; i < this.mTex_Opp.length; i++) /*      */ {
            this.mTex_Opp[i] = Image.createImage(opp, i * opp.getWidth() / this.mTex_Opp.length, 0, opp.getWidth() / this.mTex_Opp.length, opp.getHeight(), 0);
        }
        opp = add("/gear1.png");
        for (int i = 0; i < this.mTex_Opp.length; i++) /*      */ {
            this.mTex_Opp1[i] = Image.createImage(opp, i * opp.getWidth() / this.mTex_Opp1.length, 0, opp.getWidth() / this.mTex_Opp1.length, opp.getHeight(), 0);
        }
        opp = add("/gear2.png");
        for (int i = 0; i < this.mTex_Opp.length; i++) /*      */ {
            this.mTex_Opp2[i] = Image.createImage(opp, i * opp.getWidth() / this.mTex_Opp2.length, 0, opp.getWidth() / this.mTex_Opp2.length, opp.getHeight(), 0);
        }
        this.mTex_Opp3 = add("/gear3.png");
        this.mTex_Opp4 = add("/gear4.png");

        Image opp5 = add("/gear5.png");
        for (int i = 0; i < this.mTex_Opp5.length; i++) /*      */ {
            this.mTex_Opp5[i] = Image.createImage(opp5, i * opp5.getWidth() / this.mTex_Opp5.length, 0, opp5.getWidth() / this.mTex_Opp5.length, opp5.getHeight(), 0);
        }
        opp5 = add("/gear6.png");
        for (int i = 0; i < this.mTex_Opp5.length; i++) /*      */ {
            this.mTex_Opp6[i] = Image.createImage(opp5, i * opp5.getWidth() / this.mTex_Opp6.length, 0, opp5.getWidth() / this.mTex_Opp6.length, opp5.getHeight(), 0);
        }
        opp5 = add("/gear7.png");
        for (int i = 0; i < this.mTex_Opp5.length; i++) /*      */ {
            this.mTex_Opp7[i] = Image.createImage(opp5, i * opp5.getWidth() / this.mTex_Opp7.length, 0, opp5.getWidth() / this.mTex_Opp7.length, opp5.getHeight(), 0);
        }
        /*  233 */ opp5 = add("/gear8.png");
        /*  234 */ for (int i = 0; i < this.mTex_Opp5.length; i++) /*      */ {
            /*  236 */ this.mTex_Opp8[i] = Image.createImage(opp5, i * opp5.getWidth() / this.mTex_Opp8.length, 0, opp5.getWidth() / this.mTex_Opp8.length, opp5.getHeight(), 0);
            /*      */        }
        /*  238 */ opp5 = add("/gear9.png");
        /*  239 */ for (int i = 0; i < this.mTex_Opp5.length; i++) /*      */ {
            /*  241 */ this.mTex_Opp9[i] = Image.createImage(opp5, i * opp5.getWidth() / this.mTex_Opp9.length, 0, opp5.getWidth() / this.mTex_Opp9.length, opp5.getHeight(), 0);
            /*      */        }
        /*  243 */ Image b = add("/fail.png");
        /*  244 */ for (int i = 0; i < this.mTex_Die.length; i++) /*      */ {
            /*  246 */ this.mTex_Die[i] = Image.createImage(b, i * b.getWidth() / this.mTex_Die.length, 0, b.getWidth() / this.mTex_Die.length, b.getHeight(), 0);
            /*      */        }
        /*      */    }
    /*      */
    /*      */ void loadHanuman() {
        /*  251 */ this.mTex_Run = new Image[14];
        /*  252 */ Image b = add("/player.png");
        /*  253 */ for (int i = 0; i < this.mTex_Run.length; i++) /*      */ {
            /*  255 */ this.mTex_Run[i] = Image.createImage(b, i * b.getWidth() / this.mTex_Run.length, 0, b.getWidth() / this.mTex_Run.length, b.getHeight(), 0);
            /*      */        }
        /*  257 */ this.mTex_Spider = new Image[6];
        /*  258 */ Image b1 = add("/spider.png");
        /*  259 */ for (int i = 0; i < this.mTex_Spider.length; i++) /*      */ {
            /*  261 */ this.mTex_Spider[i] = Image.createImage(b1, i * b1.getWidth() / this.mTex_Spider.length, 0, b1.getWidth() / this.mTex_Spider.length, b1.getHeight(), 0);
            /*      */        }
        /*      */    }
    /*      */
    /*      */ Image add(String ID) {
        /*      */ try {
            /*  267 */ return Image.createImage(ID);
        } catch (Exception e) {
            /*  268 */        }
        return null;
        /*      */    }
    /*      */
    /*      */ void reset() {
        /*  272 */ this.Bg1 = 0.0F;
        /*  273 */ this.Bg2 = 1.0F;
        /*  274 */ if (this.mHScore < this.mScore) {
            /*  275 */ this.mHScore = readRecords();
            /*      */        }
        /*  277 */ this.NewScore = (this.mScore = 0);
        /*  278 */ if (this.mHScore == 0) /*  279 */ {
            this.NewScore += 1;
        }
        /*  280 */ this.BGSPEED = -0.04F;
        /*  281 */ this.GameScreen = 3;
        /*  282 */ this.mPlayer.set(0.2F, 1.0F - this.mTex_Base.getHeight() / this.TY - this.mTex_Run[0].getHeight() / this.TY / 2.0F, 0.0F);
        /*  283 */ float y = 0.0F;
        /*  284 */ y = -1.0F + this.mTex_Base.getHeight() / this.TY / 2.0F;
        /*  285 */ for (int i = 0; i < this.moBase.length; i++) /*  286 */ {
            this.moBase[i].set(i * (this.mTex_Base.getWidth() / this.TX), y, i);
        }
        /*  287 */ this.moAir.set(1.0F, 0.2F, 0);
        /*      */
        /*  292 */ for (int i = 0; i < this.moBird3.length; i++) /*  293 */ {
            this.moBird3[i].set(10.0F, 0.0F, 0.0F, 0.0F, 0);
        }
        /*  294 */ long time = System.currentTimeMillis();
        /*  295 */ for (int i = 0; i < this.moKachra.length; i++) /*      */ {
            /*  297 */ this.moKachra[i].set(1.0F, 1.0F, -0.1F, -0.1F, (int) (time % 10L % 10L));
            /*  298 */ time /= 10L;
            /*      */        }
        /*  300 */ time = System.currentTimeMillis();
        /*  301 */ for (int i = 0; i < this.mOpponent.length; i++) /*      */ {
            /*  303 */ this.mOpponent[i].set((i + 3) * 1, 1.0F - this.mTex_Base.getHeight() / this.TY - this.mTex_Opp[0].getHeight() / this.TY / 2.0F, Math.abs(i % this.mOpponent.length), (int) (time % 2L));
            /*  304 */ time /= 10L;
            /*      */        }
        /*      */
        /*  307 */ this.mGame.cnt1 = 0;
        /*  308 */ this.mGame.newScr = 10.0F;
        /*      */    }
    /*      */
    /*      */ protected void pointerDragged(int x1, int y1) /*      */ {
        /*  313 */ switch (this.GameScreen) /*      */ {
            /*      */ case 3:
                /*  320 */ this.mGame.HandleGame(1, x1, y1);
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void pointerReleased(int x1, int y1) /*      */ {
        /*  345 */ switch (this.GameScreen) /*      */ {
            /*      */ case 10:
                /*  348 */ this.mGame.HandlePause(2, x1, y1);
                /*  349 */ break;
            /*      */ case 11:
                /*  351 */ this.mGame.HandleGameover(2, x1, y1);
                /*  352 */ break;
            /*      */ case 3:
                /*  354 */ this.mGame.HandleGame(2, x1, y1);
                /*  355 */ break;
            /*      */ case 2:
                /*  357 */ HandleMenu(2, x1, y1);
                /*  358 */ break;
            /*      */ case 6:
                /*  360 */ this.mGame.HandleOption(2, x1, y1);
                /*  361 */ break;
            /*      */ case 8:
                /*  363 */ this.mGame.HandleHelp(2, x1, y1);
                /*  364 */ break;
            /*      */ case 7:
                /*  367 */ break;
            /*      */ case 1:
                /*  369 */ this.GameScreen = 2;
                /*      */
                /*  371 */ break;
            /*      */ case 12:
                /*  373 */ this.mGame.HandleAboutus(2, x1, y1);
                /*  374 */ break;
            /*      */ case 13:
            /*      */ case 14:
                /*  377 */ backHandleAd(2, x1, y1);
            /*      */ case 4:
            /*      */ case 5:
            /*      */ case 9:
            /*      */        }
        /*      */    }
    /*      */
    /*  384 */ public void pointerPressed(int x1, int y1) {
        switch (this.GameScreen) /*      */ {
            /*      */ case 10:
                /*  387 */ this.mGame.HandlePause(0, x1, y1);
                 break;
             case 11:
                /*  390 */ this.mGame.HandleGameover(0, x1, y1);
                /*  391 */ break;
            /*      */ case 3:
                /*  393 */ this.mGame.HandleGame(0, x1, y1);
                /*  394 */ break;
            /*      */ case 2:
                /*  396 */ HandleMenu(0, x1, y1);
                /*  397 */ break;
            /*      */ case 6:
                /*  399 */ this.mGame.HandleOption(0, x1, y1);
                /*  400 */ break;
            /*      */ case 8:
                /*  402 */ this.mGame.HandleHelp(0, x1, y1);
                /*  403 */ break;
            /*      */ case 7:
                /*  405 */ reset();
                /*  406 */ break;
            /*      */ case 1:
                /*  408 */ this.GameScreen = 2;
                /*  409 */ break;
            /*      */ case 12:
                /*  411 */ this.mGame.HandleAboutus(0, x1, y1);
                /*  412 */ break;
            /*      */ case 13:
            /*      */ case 14:
                /*  415 */ backHandleAd(0, x1, y1);
            /*      */ case 4:
            /*      */ case 5:
            /*      */ case 9:
            /*      */        }
    }
    /*      */ public void public_late() throws InterruptedException {
        /*  421 */ repaint();
        /*  422 */ Thread.sleep(1000L);
        /*      */    }
    /*      */
    /*      */ public void handle_menu(int pointer, int x1, int y1) throws IOException /*      */ {
        /*      */    }
    /*      */
    /*      */ public boolean check_touch(int x1, int y1, int x, int y, int dx, int dy) {
        /*  430 */ if ((x1 > x) && (x1 < x + dx)) /*      */ {
            /*  432 */ if ((y1 > y) && (y1 < y + dy)) {
                /*  433 */ return true;
                /*      */            }
            /*  435 */ return false;
            /*      */        }
        /*      */
        /*  438 */ return false;
        /*      */    }
    /*      */
    /*      */ protected void keyReleased(int key) {
        /*  442 */ int gameKey = getGameAction(key);
        /*      */
        /*  449 */ switch (this.GameScreen) /*      */ {
            /*      */ case 1:
                /*  452 */ this.GameScreen = 2;
                /*  453 */ break;
            /*      */ case 3:
                /*  455 */ if (this.mPlayer.Onair == 2) /*  456 */ {
                    this.mPlayer.Onair = 0;
                }
                /*      */ break;
            /*      */        }
        /*      */    }
    /*      */
    /*      */ protected void keyPressed(int key) {
        /*  462 */ int gameKey = getGameAction(key);
        /*  463 */ if ((this.GameScreen == 7) && (key != -7)) {
            /*  464 */ reset();
            /*      */        }
        /*      */
        /*  469 */ if (key == -7) /*      */ {
            /*  471 */ if ((this.GameScreen == 13) || (this.GameScreen == 14)) {
                /*  472 */ HandleAD(key, gameKey, 0);
                /*      */            } else {
                /*  474 */ if ((this.GameScreen == 10) || (this.GameScreen == 11) || (this.GameScreen == 6) || (this.GameScreen == 12)) /*      */ {
                    /*  476 */ soundstop(0);
                    /*  477 */ this.GameScreen = 2;
                    /*      */                }
                /*  479 */ if (this.GameScreen == 8) /*  480 */ {
                    this.GameScreen = 6;
                }
                /*  481 */ if (this.GameScreen == 3) /*      */ {
                    /*  483 */ soundstop(0);
                    /*  484 */ this.GameScreen = 10;
                    /*      */                }
                /*  486 */ if (this.GameScreen == 7) /*  487 */ {
                    reset();
                }
                /*      */            }
            /*      */        }
        /*  490 */ if (key == -6) /*      */ {
            /*  492 */ if ((this.GameScreen == 13) || (this.GameScreen == 14)) {
                /*  493 */ HandleAD(key, gameKey, 0);
                /*      */            }
            /*      */        }
        /*  496 */ switch (gameKey) /*      */ {
            /*      */ case 5:
            /*      */ case 6:
            /*      */ case 54:
                /*  502 */ if (this.GameScreen == 3) /*      */ {
                    /*  504 */ if (this.mPlayer.Onair == 0) /*  505 */ {
                        this.mPlayer.Onair = 2;
                    } /*      */ else /*  507 */ {
                        this.mPlayer.Onair = 3;
                    }
                    /*      */                } /*      */ else /*      */ {
                    /*  511 */ this.mMenuSel += 1;
                    /*  512 */ if ((this.GameScreen == 6) || (this.GameScreen == 11)) /*      */ {
                        /*  514 */ if (this.mMenuSel > 2) /*  515 */ {
                            this.mMenuSel = 1;
                        }
                        /*      */                    }
                    /*  517 */ if (this.GameScreen == 10) /*      */ {
                        /*  519 */ if (this.mMenuSel > 3) {
                            /*  520 */ this.mMenuSel = 1;
                            /*      */                        }
                        /*      */                    }
                    /*  523 */ if (this.GameScreen == 2) /*      */ {
                        /*  525 */ if (this.mMenuSel > 4) /*  526 */ {
                            this.mMenuSel = 1;
                        }
                    }
                }
                break;
            /*      */ case 1:
            /*      */ case 50:
                /*  534 */ if (this.GameScreen == 3) /*      */ {
                    /*  536 */ if (this.mPlayer.Onair == 0) /*      */ {
                        /*  540 */ this.mPlayer.Onair = 1;
                        /*  541 */ this.mPlayer.vy = this.Jump;
                    }
                }
                break;
            /*      */ case 2:
                /*  547 */ this.mMenuSel -= 1;
                /*  548 */ if ((this.GameScreen == 11) || (this.GameScreen == 6)) /*      */ {
                    /*  550 */ if (this.mMenuSel < 1) /*  551 */ {
                        this.mMenuSel = 2;
                    }
                    /*      */                }
                /*  553 */ if (this.GameScreen == 10) /*      */ {
                    /*  555 */ if (this.mMenuSel < 1) /*  556 */ {
                        this.mMenuSel = 3;
                    }
                    /*      */                }
                /*  558 */ if (this.GameScreen == 2) /*      */ {
                    /*  560 */ if (this.mMenuSel < 1) /*  561 */ {
                        this.mMenuSel = 4;
                    }
                }
                break;
            /*      */ case 8:
            /*      */ case 53:
                /*  567 */ switch (this.mMenuSel) /*      */ {
                    /*      */ case 1:
                        /*  570 */ switch (this.GameScreen) /*      */ {
                            /*      */ case 2:
                                /*  573 */ this.GameScreen = 7;
                                /*  574 */ break;
                            /*      */ case 10:
                                /*  576 */ this.GameScreen = 3;
                                /*  577 */ break;
                            /*      */ case 11:
                                /*  579 */ reset();
                                /*  580 */ this.GameScreen = 3;
                                /*  581 */ break;
                            /*      */ case 6:
                                /*  583 */ this.setValue = (!this.setValue);
                            /*      */ case 3:
                            /*      */ case 4:
                            /*      */ case 5:
                            /*      */ case 7:
                            /*      */ case 8:
                            /*  585 */ case 9:
                        }
                        break;
                    /*      */ case 2:
                        /*  588 */ switch (this.GameScreen) /*      */ {
                            /*      */ case 2:
                                /*  591 */ this.GameScreen = 6;
                                /*  592 */ break;
                            /*      */ case 6:
                                /*  594 */ this.GameScreen = 8;
                                /*  595 */ break;
                            /*      */ case 10:
                            /*      */ case 11:
                                /*  598 */ this.GameScreen = 2;
                            /*      */ case 3:
                            /*      */ case 4:
                            /*      */ case 5:
                            /*      */ case 7:
                            /*      */ case 8:
                            /*  601 */ case 9:
                        }
                        break;
                    /*      */ case 3:
                        /*  603 */ switch (this.GameScreen) /*      */ {
                            /*      */ case 2:
                                /*  606 */ this.GameScreen = 12;
                                /*  607 */ break;
                            /*      */ case 6:
                                /*  609 */ this.GameScreen = 2;
                                /*  610 */ break;
                            /*      */ case 10:
                                /*  612 */ this.setValue = (!this.setValue);
                            /*      */                        }
                        /*      */
                        /*  616 */ break;
                    /*      */ case 4:
                        /*  619 */ switch (this.GameScreen) /*      */ {
                            /*      */ case 2:
                                /*  622 */ if (this.mHScore < this.mScore) /*      */ {
                                    /*  624 */ this.mHScore = this.mScore;
                                    /*      */
                                    /*  626 */ WinnerScore(this.mHScore + "");
                                    /*      */                                }
                                /*      */
                                /*  629 */ this.GameScreen = 14;
                            /*      */                        }
                        /*      */
                        /*      */ break;
                    /*      */                }
                /*      */
                /*  636 */ if (this.GameScreen == 10) /*      */ {
                    /*  638 */ if (this.mMenuSel == 3) /*  639 */ {
                        this.mMenuSel = 3;
                    }
                    /*      */                } /*      */ else /*  642 */ {
                    this.mMenuSel = 1;
                }
                /*      */ break;
            /*      */        }
        /*      */    }
    /*      */
    /*      */ void Draw_number(Graphics g, int str, int x, int y) /*      */ {
        /*  653 */ String strs = "" + str;
        /*  654 */ for (int i = 0; i < strs.length(); i++) /*      */ {
            /*  656 */ int k = strs.charAt(i);
            /*  657 */ g.drawRegion(this.ImgFont, (k - 48) * this.ImgFont.getWidth() / 11, 0, this.ImgFont.getWidth() / 11, this.ImgFont.getHeight(), 0, x + i * this.ImgFont.getWidth() / 11, y, 0);
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void paint(Graphics g) /*      */ {
        /*  663 */ g.setColor(0, 0, 0);
        /*  664 */ g.fillRect(0, 0, getWidth(), getHeight());
        /*  665 */ switch (this.GameScreen) /*      */ {
            /*      */ case 1:
                /*  668 */ drawSplash(g);
                /*  669 */ break;
            /*      */ case 3:
            /*      */ case 9:
                /*  672 */ this.mGame.DrawGamePlay(g);
                /*  673 */ break;
            /*      */ case 5:
            /*      */ case 10:
            /*      */ case 11:
                /*  677 */ this.mGame.DrawGameoverVPause(g);
                /*  678 */ break;
            /*      */ case 2:
                /*  680 */ this.mGame.DrawMenu(g);
                /*  681 */ break;
            /*      */ case 6:
                /*  683 */ this.mGame.DrawOption(g);
                /*  684 */ break;
            /*      */ case 7:
            /*      */ case 8:
                /*  687 */ this.mGame.DrawHelp(g);
                /*  688 */ break;
            /*      */ case 12:
                /*  690 */ this.mGame.DrawAbtus(g);
                /*  691 */ break;
            /*      */ case 13:
            /*      */ case 14:
                /*  694 */ GameAD(g);
            /*      */ case 4:
            /*      */        }
        /*      */ try /*      */ {
            /*  699 */ if (this.GameScreen == 1) /*      */ {
                /*  701 */ Thread.sleep(50L);
                /*      */ try /*      */ {
                    /*  704 */ this.mGame = new game(this);
                    /*      */                } catch (IOException ex) {
                    /*      */                }
                /*  707 */ this.splash_check += 1;
                /*  708 */ if (this.splash_check > 50) /*  709 */ {
                    this.GameScreen = 13;
                }
                /*      */            } /*      */ else /*      */ {
                /*  713 */ Thread.sleep(25L);
                /*      */            }
            /*      */        } /*      */ catch (InterruptedException ex) /*      */ {
            /*      */        }
        /*  719 */ repaint();
        /*      */    }
    /*      */
    /*      */ public void run() {
        /*      */ try {
            /*  724 */ Thread.sleep(0L);
            /*  725 */ repaint();
            /*      */        } /*      */ catch (InterruptedException ex) /*      */ {
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void drawSplash(Graphics g) {
        /*  733 */ g.setColor(0, 0, 0);
        /*  734 */ g.fillRect(0, 0, getWidth(), getHeight());
        /*  735 */ g.drawImage(this.mTexMano, getWidth() / 2, getHeight() / 2, 3);
        /*      */    }
    /*      */
    /*      */ public void move_up() /*      */ {
        /*  740 */ switch (this.GameScreen) /*      */ {
            /*      */ case 2:
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void move_down() /*      */ {
        /*  748 */ switch (this.GameScreen) /*      */ {
            /*      */ case 2:
            /*      */        }
        /*      */    }
    /*      */
    /*      */ boolean HandleMenu(int event, int x, int y) /*      */ {
        /*  757 */ if (event == 0) /*      */ {
            /*  759 */ this.mMenuSel = 0;
            /*  760 */ if (this.mGame.CircRectsOverlap(100.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                /*  762 */ this.mMenuSel = 1;
                /*      */            }
            /*  764 */ if (this.mGame.CircRectsOverlap(140.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                /*  766 */ this.mMenuSel = 2;
                /*      */            }
            /*  768 */ if (this.mGame.CircRectsOverlap(180.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                /*  770 */ this.mMenuSel = 3;
                /*      */            }
            /*  772 */ if (this.mGame.CircRectsOverlap(220.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                /*  774 */ this.mMenuSel = 4;
                /*      */            }
            /*      */
            /*      */        }
        /*      */
        /*  786 */ if (event == 2) /*      */ {
            /*  788 */ switch (this.mMenuSel) /*      */ {
                /*      */ case 1:
                    /*  792 */ if (this.mGame.CircRectsOverlap(100.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                        /*  794 */ this.GameScreen = 7;
                    }
                    break;
                /*      */ case 2:
                    /*  798 */ if (this.mGame.CircRectsOverlap(140.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                        /*  801 */ if (this.mHScore < this.mScore) /*      */ {
                            /*  803 */ this.mHScore = this.mScore;
                            /*      */
                            /*  805 */ WinnerScore(this.mScore + "");
                            /*      */                        }
                        /*  807 */ this.GameScreen = 6;
                    }
                    break;
                /*      */ case 3:
                    /*  811 */ if (this.mGame.CircRectsOverlap(180.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*  812 */ {
                        this.GameScreen = 12;
                    }
                    break;
                /*      */ case 4:
                    /*  815 */ if (this.mGame.CircRectsOverlap(220.0F, 240.0F, this.mTex_sel.getWidth() / 2, this.mTex_sel.getHeight() / 2, x, y, 2.0F)) /*      */ {
                        /*  817 */ if (this.mHScore < this.mScore) /*      */ {
                            /*  819 */ this.mHScore = this.mScore;
                            /*      */
                            /*  821 */ WinnerScore(this.mScore + "");
                            /*      */                        }
                        /*      */
                        /*  824 */ this.GameScreen = 14;
                        /*      */                    }
                    /*      */ break;
                /*      */            }
            /*      */
            /*  829 */ this.mMenuSel = 0;
            /*      */        }
        /*      */
        /*  832 */ return true;
        /*      */    }
    /*      */
    /*      */ public void Soundplay(int i) /*      */ {
        /*  837 */ i = 0;
        /*      */ try /*      */ {
            /*  840 */ if (this.setValue) /*      */ {
                /*  842 */ if (i == 0) /*      */ {
                    /*  844 */ this.mp3player[i].realize();
                    /*  845 */ this.mp3player[i].prefetch();
                    /*      */                }
                /*  847 */ this.mp3player[i].start();
                /*  848 */ if (i == 0) /*  849 */ {
                    this.mp3player[i].setLoopCount(-1);
                }
                /*      */            }
            /*      */        } catch (Exception e) {
            /*  852 */ System.out.println(this.setValue + "~~~~~~~*********~~~~~~~~" + i);
            /*      */        }
        /*      */    }
    /*  855 */ public void soundstop(int i) {
        i = 0;
        /*      */ try /*      */ {
            /*  858 */ this.mp3player[i].stop();
            /*      */        } /*      */ catch (Exception e) {
            /*      */        }
    }
    /*      */
    /*      */ public int readRecords() {
        /*      */ try {
            /*  865 */ byte[] recData = new byte[5];
            /*      */
            /*  867 */ for (int i = 1; i <= this.rs.getNumRecords(); i++) /*      */ {
                /*  869 */ if (this.rs.getRecordSize(i) > recData.length) {
                    /*  870 */ recData = new byte[this.rs.getRecordSize(i)];
                    /*      */                }
                /*  872 */ int len = this.rs.getRecord(i, recData, 0);
                /*  873 */ int highscore = Integer.parseInt(new String(recData, 0, len));
                /*  874 */ this.score1 = highscore;
                /*      */            }
            /*      */        } catch (Exception e) {
        }
        /*      */
        /*  877 */ return this.score1;
        /*      */    }
    /*      */
    /*      */ public void WinnerScore(String str) /*      */ {
        /*  882 */ if (this.mScore > readRecords()) /*      */ {
            /*  885 */ deleteRecStore();
            /*  886 */ writeRecord(this.mScore + "");
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void openRecStore() {
        /*      */ try {
            /*  892 */ this.rs = RecordStore.openRecordStore("Blur Runner", true);
            /*      */        } /*      */ catch (Exception e) /*      */ {
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void deleteRecStore() {
        /*  900 */ if (RecordStore.listRecordStores() != null) /*      */ {
            try /*      */ {
                /*  903 */ RecordStore.deleteRecordStore("Blur Runner");
                /*      */            } /*      */ catch (Exception e) {
                /*      */            }
        }
        /*      */    }
    /*      */
    /*      */ public void writeRecord(String str) {
        /*  910 */ byte[] rec = str.getBytes();
        /*      */ try {
            /*  912 */ this.rs.addRecord(rec, 0, rec.length);
            /*      */        } /*      */ catch (Exception e) {
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void commandAction(Command c, Displayable d) {
        /*      */    }
    /*      */
    /*      */ public void ADNetworkLoad() {
        /*  922 */ this.newCallbackId = Long.toString(System.currentTimeMillis(), 16);
        /*  923 */ if (this.newCallbackId.length() > 6) {
            /*  924 */ this.newCallbackId = this.newCallbackId.substring(this.newCallbackId.length() - 6);
            /*      */        }
        /*      */
        /*  927 */ this.URL = ("http://store.ovi.com/publisher/TRI_TUE_VIET/");
        /*      */ try /*      */ {
            /*  931 */ String string = getDataFromUrl(this.URL);
            /*      */
            /*  933 */ String[] parts = split(string, "'");
            /*  934 */ int i = 0;
            for (int k = 0; i < parts.length; i++) /*      */ {
                /*  938 */ if (parts[i].startsWith("http://www.aditute.com/openx-server/www/")) /*      */ {
                    /*  940 */ if (k == 0) {
                        /*  941 */ this.outgoing = parts[i];
                        /*      */                    }
                    /*      */
                    /*  944 */ if (k == 1) {
                        /*  945 */ this.incomming = parts[i];
                        /*      */                    }
                    /*      */
                    /*  948 */ k++;
                    /*      */                }
                /*      */            }
            /*  951 */ this.mTexAdd2 = loadImage(this.incomming);
            /*      */        } catch (Exception e) {
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public String[] split(String str, String delimiter) {
        /*  957 */ int delimiterCount = 0;
        /*      */
        /*  959 */ String tmpStr = str;
        /*      */ int index = 0;
        /*  961 */ while ((index = tmpStr.indexOf(delimiter)) != -1) {
            /*  962 */ tmpStr = tmpStr.substring(index + delimiter.length());
            /*  963 */ delimiterCount++;
            /*      */        }
        /*  965 */ String[] splittedList = new String[delimiterCount];
        /*  966 */     //int index = 0;
/*  967 */ int counter = 0;
        /*  968 */ tmpStr = str;
        /*  969 */ while ((index = tmpStr.indexOf(delimiter)) != -1) {
            /*  970 */ int nextIndex = tmpStr.indexOf(delimiter, index + 1);
            /*  971 */ if (nextIndex != -1) {
                /*  972 */ splittedList[(counter++)] = tmpStr.substring(index + delimiter.length(), nextIndex);
                /*  973 */ tmpStr = tmpStr.substring(nextIndex);
                /*      */            } else {
                /*  975 */ splittedList[(counter++)] = tmpStr.substring(index + delimiter.length());
                /*  976 */ tmpStr = tmpStr.substring(index + 1);
                /*      */            }
            /*      */        }
        /*  979 */ return splittedList;
        /*      */    }
    /*      */
    /*      */ public Image loadImage(String url) throws IOException {
        /*  983 */ HttpConnection hpc = null;
        /*  984 */ DataInputStream dis = null;
        /*      */ try {
            /*  986 */ hpc = (HttpConnection) Connector.open(url);
            /*  987 */ int length = (int) hpc.getLength();
            /*  988 */ byte[] data = new byte[length];
            /*  989 */ dis = new DataInputStream(hpc.openInputStream());
            /*  990 */ dis.readFully(data);
            /*  991 */ return Image.createImage(data, 0, data.length);
            /*      */        } finally {
            /*  993 */ if (hpc != null) {
                /*  994 */ hpc.close();
                /*      */            }
            /*  996 */ if (dis != null) /*  997 */ {
                dis.close();
            }
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public String getDataFromUrl(String url) throws IOException /*      */ {
        /* 1003 */ StringBuffer b = new StringBuffer();
        /* 1004 */ InputStream is = null;
        /* 1005 */ HttpConnection c = null;
        /* 1006 */ long len = 0L;
        /* 1007 */ int ch = 0;
        /* 1008 */ c = (HttpConnection) Connector.open(url);
        /* 1009 */ is = c.openInputStream();
        /* 1010 */ len = c.getLength();
        /* 1011 */ int rc = c.getResponseCode();
        /* 1012 */ if (rc != 200) {
            /* 1013 */ throw new IOException("HTTP response code: " + rc);
            /*      */        }
        /* 1015 */ if (len != -1L) {
            /* 1016 */ for (int i = 0; i < len; i++) {
                /* 1017 */ if ((ch = is.read()) != -1) /* 1018 */ {
                    b.append((char) ch);
                }
                /*      */            }
            /*      */        } /*      */ else {
            /* 1022 */ while ((ch = is.read()) != -1) {
                /* 1023 */ len = is.available();
                /* 1024 */ b.append((char) ch);
                /*      */            }
            /*      */        }
        /* 1027 */ is.close();
        /* 1028 */ c.close();
        /* 1029 */ return b.toString();
        /*      */    }
    /*      */ public void adlink() {
        /*      */ try {
            /* 1033 */ if (this.mTexAdd2 != null) /* 1034 */ {
                this.Adlink = this.mid.platformRequest(this.outgoing);
            } /*      */ else /* 1036 */ {
                this.Adlink = this.mid.platformRequest("http://store.ovi.com/publisher/TRI_TUE_VIET/");
            }
            /*      */        } /*      */ catch (Exception e) /*      */ {
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void LoadADImg() /*      */ {
        /*      */ try /*      */ {
            /* 1056 */ this.mGame = new game(this);
            /*      */        } /*      */ catch (Exception e) {
            /*      */        }
        /* 1060 */ //ADNetworkLoad();
        /* 1061 */ this.download = add("/downloadAD.png");
        /* 1062 */ this.skip = add("/skipAD.png");
        /* 1063 */ this.mTexAdd1 = add("/add0.jpg");
        /* 1064 */ this.exit = add("/ExitAD.png");
        /*      */    }
    /*      */ public void GameAD(Graphics g) {
        /* 1067 */ g.setColor(0, 0, 0);
        /* 1068 */ g.fillRect(0, 0, (int) this.TX, (int) this.TY);
        /*      */
        /* 1070 */ if (this.mTexAdd2 != null) /* 1071 */ {
            drawImgInt(g, this.mTexAdd2, (int) this.TX / 2, (int) this.TY / 2);
        } /*      */ else {
            /* 1073 */ drawImgInt(g, this.mTexAdd1, (int) this.TX / 2, (int) this.TY / 2);
            /*      */        }
        /*      */
        /* 1076 */ DrawImg(g, this.download, 0, (int) this.TY - this.download.getHeight());
        /* 1077 */ if (this.GameScreen == 13) {
            /* 1078 */ DrawImg(g, this.skip, (int) this.TX - this.skip.getWidth(), (int) this.TY - this.skip.getHeight());
            /*      */        }
        /* 1080 */ if (this.GameScreen == 14) /* 1081 */ {
            DrawImg(g, this.exit, (int) this.TX - this.skip.getWidth(), (int) this.TY - this.skip.getHeight());
        }
        /*      */    }
    /*      */
    /*      */ void drawImgInt(Graphics gl, Image img, int x, int y) /*      */ {
        /* 1086 */ gl.drawImage(img, x, y, 3);
        /*      */    }
    /*      */
    /*      */ void DrawImg(Graphics gl, Image img, int x, int y) {
        /* 1090 */ gl.drawImage(img, x, y, 0);
        /*      */    }
    /*      */
    /*      */ public void backHandleAd(int event, int x, int y) /*      */ {
        /* 1105 */ if ((event != 1)
                || /* 1107 */ (event == 2)) {
            /* 1108 */ if (check_touch(x, y, 0, (int) this.TY - this.download.getHeight(), this.download.getWidth(), this.download.getHeight())) {
                /* 1109 */ adlink();
                /*      */            }
            /* 1111 */ if (check_touch(x, y, (int) this.TX - this.skip.getWidth(), (int) this.TY - this.skip.getHeight(), this.skip.getWidth(), this.skip.getHeight())) {
                /* 1112 */ if (this.GameScreen == 13) {
                    /* 1113 */ this.GameScreen = 2;
                    /*      */                }
                /* 1115 */ if (this.GameScreen == 14) {
                    /* 1116 */ this.mid.notifyDestroyed();
                    /*      */                }
                /*      */
                /*      */            } /* 1120 */ else if (check_touch(x, y, 0, 0, (int) this.TX, (int) this.TY)) {
                /* 1121 */ adlink();
                /*      */            }
            /*      */        }
        /*      */    }
    /*      */
    /*      */ public void HandleAD(int KeyX, int KeyY, int Type) /*      */ {
        /* 1128 */ switch (KeyY) {
            /*      */ case 1:
                /* 1130 */ break;
            /*      */ case 6:
                /* 1132 */ break;
            /*      */ case 2:
                /* 1134 */ break;
            /*      */ case 5:
                /* 1136 */ break;
            /*      */ case 3:
            /*      */ case 4:
            /*      */ case 7:
            /* 1140 */ case 8:
        }
        if (KeyX == -6) {
            /* 1141 */ adlink();
            /*      */        }
        /* 1143 */ if (KeyX == -7) {
            /* 1144 */ if (this.GameScreen == 13) {
                /* 1145 */ this.GameScreen = 2;
                /*      */            }
            /* 1147 */ if (this.GameScreen == 14) /* 1148 */ {
                this.mid.notifyDestroyed();
            }
            /*      */        }
        /*      */    }
    /*      */ }

