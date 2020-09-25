package view;

import controller.ThreadBD;

public class Main {

	public static void main ( String args[] ) {
		
		int amount = 21;
		
		for ( int i = 0; i < amount; i++ )
			new ThreadBD ( ).start ( );
	}
}
