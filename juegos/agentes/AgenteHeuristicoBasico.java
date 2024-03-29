package juegos.agentes;


import java.io.PrintStream;


import juegos.Reversi.Tablero;
import juegos.Reversi.Reversi.EstadoReversi;
import juegos.base.*;


public class AgenteHeuristicoBasico extends _AgenteHeuristico {
	private Jugador jugador;
	private PrintStream output = null;
	private int niveles;
	

	/**
	 * Constructor de AgenteHeuristico1
	 * @param niveles: niveles de recursión
	 */
	public AgenteHeuristicoBasico(int niveles) 
	{
		super();
		this.niveles = niveles;
	}
	
	public AgenteHeuristicoBasico(int niveles, PrintStream output) 
	{
		super();
		this.niveles = niveles;
		this.output = output;
	}
	
	@Override public Jugador jugador() {
		return jugador;
	}
	
	@Override public void comienzo(Jugador jugador, Estado estado) {
		this.jugador = jugador;	
	}
	
	@Override public void fin(Estado estado) {
		// No hay implementación.
	}
	
	@Override public void movimiento(Movimiento movimiento, Estado estado) {
		if(output != null){
			output.println(String.format("Jugador %s mueve %s.", movimiento.jugador(), movimiento));
			printEstado(estado);
		}
	}
	
	protected void printEstado(Estado estado) {
		if(output != null){
			output.println("\t"+ estado.toString().replace("\n", "\n\t"));
		}
	}
	
	@Override public String toString() {
		return String.format("Agente Heuristico Basico " + jugador.toString());
	}

	@Override
	public double darHeuristica(Estado estado) {
		char miFicha = (jugador().toString() == "JugadorNegras" ? 'N' :'B');
		char fichaContrario = (jugador().toString() == "JugadorNegras" ? 'B' :'N');
		
		Tablero tablero = ((EstadoReversi)(estado)).getTablero();
		int misFichas = tablero.cantidadFichas(miFicha);
		int fichasContrario = tablero.cantidadFichas(fichaContrario);
		return misFichas - fichasContrario;
	}

	@Override
	public int niveles() {
		return niveles;
	}
}
