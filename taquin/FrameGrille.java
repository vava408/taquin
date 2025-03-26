import javax.swing.*;


public class FrameGrille extends JFrame
{

	private PanelGrille panelGrille;

	public FrameGrille(Controleur ctrl)
	{
		this.panelGrille = new PanelGrille ( ctrl );

		this.setTitle   ( "Permuter" );
		this.setLocation( 100, 100 );
		//this.setSize    ( 700, 700 );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add ( this.panelGrille );

		this.setVisible ( true );
		this.pack();
	}

	public void majIHM()
	{
		this.panelGrille.majIHM();
	}
}