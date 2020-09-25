package controller;

import java.util.concurrent.Semaphore;

public class ThreadBD extends Thread {
	
	private static Semaphore sem_bd = new Semaphore ( 1 );
	private static int ThreadCount = 1;
	
	private int id;
	
	public ThreadBD ( ) { this.id = ThreadCount++; }
	
	@Override
	public void run() {
		
		int n = this.id % 3;
		
		switch ( n ) {
			case 0: this.classeC ( ); break;
			case 1: this.classeA ( ); break;
			case 2: this.classeB ( ); break;
		}
	}
	
	private void classeA ( ) {
		
		this.calcular ((long) (200 + ( 1000 - 200 ) * Math.random ( )));
		this.transBD ( 1000 );
		this.calcular ((long) (200 + ( 1000 - 200 ) * Math.random ( )));
		this.transBD ( 1000 );
	}
	
	private void classeB ( ) {
		
		this.calcular ((long) (500 + ( 1500 - 500 ) * Math.random ( )));
		this.transBD ( 1500 );
		this.calcular ((long) (500 + ( 1500 - 500 ) * Math.random ( )));
		this.transBD ( 1500 );
		this.calcular ((long) (500 + ( 1500 - 500 ) * Math.random ( )));
		this.transBD ( 1500 );
	}
	
	private void classeC ( ) {
		
		this.calcular ((long) (1000 + ( 2000 - 1000 ) * Math.random ( )));
		this.transBD ( 1500 );
		this.calcular ((long) (1000 + ( 2000 - 1000 ) * Math.random ( )));
		this.transBD ( 1500 );
		this.calcular ((long) (1000 + ( 2000 - 1000 ) * Math.random ( )));
		this.transBD ( 1500 );
	}
	
	private void transBD ( long trans_time ) {
		
		try {
			
			this.sem_bd.acquire ( );
			System.out.printf ( "Thread-%d está usando o banco de dados\n", this.id );
			sleep ( trans_time );
			System.out.printf ( "Thread-%d liberou o uso do banco de dados, tempo gasto: %dms.\n", this.id, trans_time );
		} catch ( Exception e ) {
		} finally {	this.sem_bd.release ( ); }
	}
	
	private void calcular ( long calc_time ) {
		
		try {
			System.out.printf ( "Thread-%d está calculando\n", this.id );
			sleep ( calc_time );
			System.out.printf ( "Thread-%d finalizou o calculo em %dms.\n", this.id, calc_time );
		} catch ( InterruptedException e ) {}
	}
}