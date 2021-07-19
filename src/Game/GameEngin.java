package Game;
import try1Amit.*;
import Utillities.*;
import java.util.Scanner;

public class GameEngin {
	/*Game engin - manage board and players turn*/
	
	//attributes
	Board bGame;
	char move;
	int numPlayers;
	Player plArr[];
	
	//ctor
	public GameEngin() {
		bGame = new Board();
		numPlayers = 0;
	}
	
	//play turn
	private int turn(Player plar, char dir) {
		// TODO Auto-generated method stub
		//set direction
		int calc = 0;
		Point pt = new Point();
		if(dir == 'u')
			pt = new Point(plar.getX() - 1, plar.getY());
		else if(dir == 'd')
			pt = new Point(plar.getX() + 1, plar.getY());
		else if(dir == 'l')
			pt = new Point(plar.getX(), plar.getY() - 1);
		else if(dir == 'r')
			pt = new Point(plar.getX(), plar.getY() + 1);
		
		//check board limits
		if(bGame.isInBoard(pt)) {
			//empty area
			if(bGame.isEmpty(pt)) {
				bGame.setPosition(pt, plar);
				//i++;
				return 1;
			}
			
			//Dual
			else if(bGame.getEntity(pt) instanceof Player) {
				System.out.println("\t\t# - D u a l  ! ! !\n");//
				Player plr = (Player)bGame.getEntity(pt);
				Weapon w1 = plar.getWeapon();
				Weapon w2 = plr.getWeapon();
				
				//for save loose weapons
				Queue wpns1 = new Queue(6);
				Queue wpns2 = new Queue(6);
				
				//while there is weapons to fight
				while(w1 != null && w2 != null) {
					System.out.println("\t# - " + plar.Name + " use " + w1.Name);//
					System.out.println("\t# - " + plr.Name + " use " + w2.Name);//
					
					//calculate round winner
					calc = w1.Stronger(w2);
					if(calc == 1) {
						System.out.println("\t# - " + plar.Name + " win a round! ");
						wpns2.Enqueue(w2);
						w2 = plr.getWeapon();
					}
					else if(calc == -1) {
						System.out.println("\t# - " + plr.Name + " win a round! ");
						wpns1.Enqueue(w1);
						w1 = plar.getWeapon();
					}
					else {
						System.out.println("\t# - Even!!! ");
						wpns2.Enqueue(w2);
						wpns1.Enqueue(w1);
						w1 = plar.getWeapon();
						w2 = plr.getWeapon();
					}
					System.out.println("\n-------------------------------------------\n");
					
				};
				//draw
				if(w1 == null && w2 == null) {
					System.out.println("\t# - Its a Draw, 2 sides lose!");
					bGame.deleteEntity(new Point(plar.getX(), plar.getY()));
					bGame.deleteEntity(new Point(plr.getX(), plr.getY()));
					this.numPlayers = this.numPlayers - 2;
					Player[] tmpArr = new Player[this.numPlayers];
					int newI = 0;
					for(int el = 0; el < this.numPlayers + 2; el++) {
						if(this.plArr[el].Name == plar.Name || this.plArr[el].Name == plr.Name) {
							continue;
						}
						else {
							tmpArr[newI] = plArr[el];
							newI++;
						}
					}
					if(this.numPlayers == 0) {
						this.plArr = null;
					}
					else {
						this.plArr = tmpArr;
					}
					//i++;
					return 0;
				}
				else if(w1 == null) {
					System.out.println("\t# - " + plar.Name + " Has no weapons! ");
					System.out.println("\t# - " + plr.Name + " WIN!!! ");
					bGame.deleteEntity(new Point(plar.getX(), plar.getY()));
					this.numPlayers--;
					Player[] tmpArr = new Player[this.numPlayers];
					int newI = 0;
					for(int el = 0; el < this.numPlayers + 1; el++) {
						if(this.plArr[el].Name == plar.Name) {
							continue;
						}
						else {
							tmpArr[newI] = this.plArr[el];
							newI++;
						}
					}
					this.plArr = tmpArr;
					plr.addWeapon(w2);
					w2 = wpns1.Dequeue();
					while(w2 != null) {
						plr.addWeapon(w2);
						w2 = wpns1.Dequeue();
					}
					System.out.println("\t# - " + plar.Name + " weapons added to " + plr.Name);
					//i++;
					return 0;
				}
				else {//w2 is null
					System.out.println("\t# - " + plr.Name + " Has no weapons! ");
					System.out.println("\t# - " + plar.Name + " WIN!!! ");
					bGame.deleteEntity(new Point(plr.getX(), plr.getY()));
					bGame.setPosition(pt, plar);
					this.numPlayers--;
					Player[] tmpArr = new Player[this.numPlayers];
					int newI = 0;
					for(int el = 0; el < this.numPlayers + 1; el++) {
						if(this.plArr[el].Name == plr.Name) {
							continue;
						}
						else {
							tmpArr[newI] = this.plArr[el];
							newI++;
						}
					}
					Player tmplr = plar;
					this.plArr = tmpArr;
					tmplr.addWeapon(w1);
					w2 = wpns2.Dequeue();
					while(w2 != null) {
						tmplr.addWeapon(w2);
						w2 = wpns2.Dequeue();
					}
					System.out.println("\t# - " + plr.Name + " weapons added to " + tmplr.Name);
					//i++;
					return 1;
				}
			}
			
			//Tree area
			else if(bGame.getEntity(pt) instanceof Tree) {
				System.out.println("\t# - Cant stand on a tree, please try again");
				return 0;
			}
			
			//Weapon area
			else if(bGame.getEntity(pt) instanceof Weapon) {
				if(plar.addWeapon((Weapon)bGame.getEntity(pt))){
					System.out.println("\t# - Weapon added for " + plar.Name);
				}
				else {
					System.out.println("\t# - Queue is full!");
				}
				bGame.deleteEntity(pt);
				bGame.setPosition(pt, plar);
				//i++;
				return 1;
			}
			
		}
		else {//illegal area
			System.out.println("\t# - Cant walk out of the board!, please try again");
			return 0;
		}
		return 0;
	}
	
	//Set players, init game and choose direction
	public void play() {
		System.out.print("\t\t*** Welcome to Combat Game! ***\n\n\tInsert amount of players [2 - 6]: ");
		Scanner scan = new Scanner(System.in);
		int i = 0;
		numPlayers = scan.nextInt();
		
		while(numPlayers < 2 || numPlayers > 6) {
			System.out.print("\n\n\tPlease insert again, stay in the limits [2 - 6]: ");
			numPlayers = scan.nextInt();
			scan.close();
		}
		System.out.println("\n\n\t*** Start ***");
		plArr = new Player[numPlayers];
		plArr = bGame.initBoard(numPlayers, plArr);
		do {
			i = 0;
			while(i < numPlayers && numPlayers > 1) {
				bGame.printBoard();
				System.out.println("\n\n\t# - Its " + plArr[i].Name + " turn!");
				System.out.println("\t# - Move your player (U -up, D - down, R - right, L - left) ");
				move = scan.next().charAt(0);
				while(move != 'U' && move != 'u' && move != 'D' && move != 'd' && move != 'R' && move != 'r' && move != 'l' && move != 'L') {
					System.out.println("\t# - Error - choose again: ");
					System.out.println("\t# - Move your player (U -up, D - down, R - right, L - left) ");
					move = scan.next().charAt(0);
				}
				
				if(move == 'u' || move == 'U') {//up
					if(this.turn(plArr[i], 'u') == 1)
						i++;
					continue;
					
				}
				
				else if(move == 'd' || move == 'D') {//down
					if(this.turn(plArr[i], 'd') == 1)
						i++;
					continue;
					
				}
				
				else if(move == 'l' || move == 'L') {//left
					if(this.turn(plArr[i], 'l') == 1)
						i++;
					continue;
				}
				
				if(move == 'r' || move != 'R') {//right
					if(this.turn(plArr[i], 'r') == 1)
						i++;
					continue;
				}
				
			}
		}
		while(numPlayers > 1);
		if(plArr != null) {
			System.out.println("\t\t\n\n\n*** Game Over ***");
			System.out.println("\t# - " + plArr[0].Name + " WIN ! ! !\n\n\n");
		}
		else {
			System.out.println("\t\t*\n\n\n** Game Over ***");
			System.out.println("\t# - No Winner!\n\n\n");
		}
		bGame.printBoard();
	}

	

}
