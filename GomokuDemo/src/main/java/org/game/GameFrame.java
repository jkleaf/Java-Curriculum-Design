package org.game;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.gui.ContentPanel;
import org.tools.ImgUtil;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.cert.CertPathChecker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static GameFrame frame;
	private static ContentPanel contentPanel=new ContentPanel();
	public static final int BOARD_HGAP=85;
	public static final int BOARD_VGAP=155;
	public static final int TABLE_WIDTH=900;
	public static final int TABLE_HEIGHT=800;
	public static final int PANEL_HGAP=80;
	public static final int PANEL_VGAP=120;	
	private static Image bgImg=ImgUtil.getImage("bg.png");
	private ArrayList<JPanel> panels=new ArrayList<JPanel>();
	//
	private MyMouseEvent mEvent=new MyMouseEvent();
	public static boolean isBlack=false;//标志棋子的颜色
	public static int[][] chessBoard=new int[17][17]; //棋盘棋子的摆放情况：0无子，1黑子，－1白子
	private static HashSet<Point> toJudge=new HashSet<Point>(); // ai可能会下棋的点
	private static int dr[]=new int[]{-1,1,-1,1,0,0,-1,1}; // 方向向量
	private static int dc[]=new int[]{1,-1,-1,1,-1,1,0,0}; //方向向量
	public static final int MAXN=1<<28;
	public static final int MINN=-MAXN; 
	private static int searchDeep=4;	//搜索深度
	public static final int size=15;	//棋盘大小
	public static boolean isFinished=false;
	
	private GameFrame() {
//		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 100,TABLE_WIDTH, TABLE_HEIGHT);
//		setOpacity(0.9f);
		setResizable(false);
		setContentPane(contentPanel);
//		panels.add(contentPanel);
		// 实现鼠标事件接口
		contentPanel.addMouseListener(mEvent);
		initChessBoard();
	}
	
	public void initPanel(){
		Graphics g=contentPanel.getGraphics();
		if(g!=null){/////////////////////////
			g.drawImage(bgImg,8,68,714,690,null);
			g.setColor(Color.BLACK);
			Graphics2D g2=(Graphics2D)g;
			g2.setStroke(new BasicStroke(2.0f));
			for(int i=PANEL_VGAP;i<=PANEL_VGAP+40*14;i+=40)
				g2.drawLine(PANEL_HGAP, i, PANEL_HGAP+40*14, i);
			for(int i=PANEL_HGAP;i<=PANEL_HGAP+40*14;i+=40)
				g2.drawLine(i, PANEL_VGAP, i, PANEL_VGAP+40*14);
			g.fillOval(PANEL_HGAP+40*3-6, PANEL_VGAP+40*3-6, 12, 12);
			g.fillOval(PANEL_HGAP+40*11-6, PANEL_VGAP+40*3-6, 12, 12);
			g.fillOval(PANEL_HGAP+40*3-6, PANEL_VGAP+40*11-6, 12, 12);
			g.fillOval(PANEL_HGAP+40*11-6, PANEL_VGAP+40*11-6, 12, 12);
			g.fillOval(PANEL_HGAP+40*7-6, PANEL_VGAP+40*7-6, 12, 12);
		}
	}
	
	public void initChessBoard(){
		initPanel();
		isBlack=false;
		toJudge.clear();
		System.out.println("paint");///////////////////
		for(int i=1;i<=15;i++){
			for(int j=1;j<=15;j++)
				chessBoard[i][j]=0;
		}
	}
	
	public void initListener(){
		
	}
	
// 通过点击事件，得到棋子位置进行下棋
	public void putChess(int x,int y){
		Graphics g=contentPanel.getGraphics();
			if(!isBlack)////////////////////////////
				g.setColor(Color.BLACK);
			else 
				g.setColor(Color.WHITE);
//		initPanel();//////////////////////////////////////
//		if(!panels.isEmpty())
//			setContentPane(panels.get(panels.size()-1));
		g.fillOval((x-1)*40+PANEL_HGAP-15,(y-1)*40+PANEL_VGAP-15,30,30);
//		panels.add(contentPanel);
//		contentPanel.setVisible(true);
//		int xPos=(int)((x-PANEL_HGAP)/40)+1;
//		int yPos=(int)((y-PANEL_VGAP)/40)+1;
		System.out.print(x+" "+y+" ");//'+"==="+x+" "+y);
		chessBoard[y][x]=isBlack?1:-1;//////
		System.out.println(chessBoard[y][x]);
		if(isEnd(x,y)){
			String s=!GameFrame.isBlack?"黑子胜":"白子胜";
			JOptionPane.showMessageDialog(null,s);
			isBlack=true;
//			repaint();//initChessBoard
//			initChessBoard();
			ContentPanel.isStarted=false;
		}
		else{
			Point p=new Point(x,y);
			if(toJudge.contains(p))
				toJudge.remove(p);
			for(int i=0;i<8;++i){
				Point now=new Point(p.x+dc[i],p.y+dr[i]);
				if(1<=now.x && now.x<=size && 1<=now.y && now.y<=size && chessBoard[now.y][now.x]==0)
					toJudge.add(now);
			}
		}
	}
		
	// ai博弈入口函数
	public void myAI(){
		Node node=new Node();
		dfs(0,node,MINN,MAXN,null);
		Point now=node.bestChild.p;
		// toJudge.remove(now);
		putChess(now.x,now.y);
		isBlack=false;
	}
	
	// alpha beta dfs
	private static void dfs(int deep,Node root,int alpha,int beta,Point p){
		if(deep==searchDeep){
			root.mark=getMark();
			// System.out.printf("%d\t%d\t%d\n",p.x,p.y,root.mark);
			return;
		}
		ArrayList<Point> judgeSet=new ArrayList<Point>();
		Iterator it=toJudge.iterator();
		while(it.hasNext()){
			Point now=new Point((Point)it.next());
			judgeSet.add(now);
		}
		it=judgeSet.iterator();
		while(it.hasNext()){
			Point now=new Point((Point)it.next());
			Node node=new Node();
			node.setPoint(now);
			root.addChild(node);
			boolean flag=toJudge.contains(now);
			chessBoard[now.y][now.x]=((deep&1)==1)?-1:1;
			if(isEnd(now.x,now.y)){
				root.bestChild=node;
				root.mark=MAXN*chessBoard[now.y][now.x];
				chessBoard[now.y][now.x]=0;//
				return;
			}

			boolean flags[]=new boolean[8]; //标记回溯时要不要删掉
			Arrays.fill(flags,true);
			for(int i=0;i<8;++i){
				Point next=new Point(now.x+dc[i],now.y+dr[i]);
				if(1<=now.x+dc[i] && now.x+dc[i]<=size && 1<=now.y+dr[i] && now.y+dr[i]<=size && chessBoard[next.y][next.x]==0){
					if(!toJudge.contains(next)){
						toJudge.add(next);
					}
					else flags[i]=false;
				}
			}
			
			if(flag) 
				toJudge.remove(now);
			dfs(deep+1,root.getLastChild(),alpha,beta,now);
			chessBoard[now.y][now.x]=0;//
			if(flag)
				toJudge.add(now);
			for(int i=0;i<8;++i)
				if(flags[i])
					toJudge.remove(new Point(now.x+dc[i],now.y+dr[i]));
			// alpha beta剪枝
			// min层
			if((deep&1)==1){
				if(root.bestChild==null || root.getLastChild().mark<root.bestChild.mark){
					root.bestChild=root.getLastChild();
					root.mark=root.bestChild.mark;
					if(root.mark<=MINN)
						root.mark+=deep;
					beta=Math.min(root.mark,beta);
				}
				if(root.mark<=alpha)
					return;
			}
			// max层
			else{
				if(root.bestChild==null || root.getLastChild().mark>root.bestChild.mark){
					root.bestChild=root.getLastChild();
					root.mark=root.bestChild.mark;
					if(root.mark==MAXN)
						root.mark-=deep;
					alpha=Math.max(root.mark,alpha);
				}
				if(root.mark>=beta)
					return;
			}
		}
		// if(deep==0) System.out.printf("******************************************\n");
	}
	
	public static int getMark(){
		int res=0;
		for(int i=1;i<=size;++i){
			for(int j=1;j<=size;++j){
				if(chessBoard[i][j]!=0){
					// 行
					boolean flag1=false,flag2=false;
					int x=j,y=i;
					int cnt=1;
					int col=x,row=y;
					while(--col>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(col>0 && chessBoard[row][col]==0) flag1=true;
					col=x;row=y;
					while(++col<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(col<=size && chessBoard[row][col]==0) flag2=true;
					if(flag1 && flag2)
						res+=chessBoard[i][j]*cnt*cnt;
					else if(flag1 || flag2) res+=chessBoard[i][j]*cnt*cnt/4; 
					if(cnt>=5) res=MAXN*chessBoard[i][j];
					// 列
					col=x;row=y;
					cnt=1;flag1=false;flag2=false;
					while(--row>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(row>0 && chessBoard[row][col]==0) flag1=true;
					col=x;row=y;
					while(++row<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(row<=size && chessBoard[row][col]==0) flag2=true;
					if(flag1 && flag2)
						res+=chessBoard[i][j]*cnt*cnt;
					else if(flag1 || flag2)
						res+=chessBoard[i][j]*cnt*cnt/4;
					if(cnt>=5) res=MAXN*chessBoard[i][j];
					// 左对角线
					col=x;row=y;
					cnt=1;flag1=false;flag2=false;
					while(--col>0 && --row>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(col>0 && row>0 && chessBoard[row][col]==0) flag1=true;
					col=x;row=y;
					while(++col<=size && ++row<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(col<=size && row<=size && chessBoard[row][col]==0) flag2=true;
					if(flag1 && flag2) 	
						res+=chessBoard[i][j]*cnt*cnt;
					else if(flag1 || flag2) res+=chessBoard[i][j]*cnt*cnt/4;
					if(cnt>=5) res=MAXN*chessBoard[i][j];
					// 右对角线
					col=x;row=y;
					cnt=1;flag1=false;flag2=false;
					while(++row<=size && --col>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(row<=size && col>0 && chessBoard[row][col]==0) flag1=true;
					col=x;row=y;
					while(--row>0 && ++col<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
					if(row>0 && col<=size && chessBoard[i][j]==0) flag2=true;
					if(flag1 && flag2)
						res+=chessBoard[i][j]*cnt*cnt;
					else if(flag1 || flag2) res+=chessBoard[i][j]*cnt*cnt/4;
					if(cnt>=5) res=MAXN*chessBoard[i][j];
					
				}
			}
		}
		return res;
	}
		
	// 判断是否一方取胜
	public static boolean isEnd(int x,int y){
		// 判断一行是否五子连珠
		int cnt=1;
		int col=x,row=y;
		while(--col>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		col=x;row=y;
		while(++col<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		if(cnt>=5){
			isFinished=true;
			System.err.println(1+" "+cnt);
			return true;
		}
		// 判断一列是否五子连珠
		col=x;row=y;
		cnt=1;
		while(--row>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		col=x;row=y;
		while(++row<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		if(cnt>=5){
			isFinished=true;
			System.err.println(2+" "+cnt);
			return true;
		}
		// 判断左对角线是否五子连珠
		col=x;row=y;
		cnt=1;
		while(--col>0 && --row>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		col=x;row=y;
		while(++col<=size && ++row<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		if(cnt>=5){
			isFinished=true;
			System.err.println(3+" "+cnt);
			return true;
		}
		// 判断右对角线是否五子连珠
		col=x;row=y;
		cnt=1;
		while(++row<=size && --col>0 && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		col=x;row=y;
		while(--row>0 && ++col<=size && chessBoard[row][col]==chessBoard[y][x]) ++cnt;
		if(cnt>=5){
			isFinished=true;
			System.err.println(4+" "+cnt);
			return true;
		}
		return false;
	}
	
	public static GameFrame getFrame(){
		if(frame==null)
			frame=new GameFrame();
		return frame;
	}
	
}
	
// 树节点
class Node{
	public Node bestChild=null;
	public ArrayList<Node> child=new ArrayList<Node>();
	public Point p=new Point();
	public int mark;
	Node(){
		this.child.clear();
		bestChild=null;
		mark=0;
	}
	public void setPoint(Point r){
		p.x=r.x;
		p.y=r.y;
	}
	public void addChild(Node r){
		this.child.add(r);
	}
	public Node getLastChild(){
		return child.get(child.size()-1);
	}
}

class MyMouseEvent extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		if(ContentPanel.isStarted){
		int x=round(e.getX()),y=round(e.getY());
		int xPos=(int)((x-GameFrame.PANEL_HGAP)/40)+1;
		int yPos=(int)((y-GameFrame.PANEL_VGAP)/40)+1;
		System.out.println("("+xPos+","+yPos+")"+" "+GameFrame.chessBoard[yPos][xPos]);
		if(xPos>=1&& xPos<=GameFrame.size && yPos>=1 && yPos<=GameFrame.size 
				&&GameFrame.chessBoard[yPos][xPos]==0 && GameFrame.isBlack==false){
			GameFrame.getFrame().putChess(xPos,yPos);
			if(!GameFrame.isFinished){
				GameFrame.isBlack=true;
				GameFrame.getFrame().myAI();
			}
			GameFrame.isFinished=false;
		}
		}
	}

	// 得到鼠标点击点附近的棋盘精准点
	public int round(int x){
		return (x%40<15)?x/40*40:x/40*40+40;
	}
}
