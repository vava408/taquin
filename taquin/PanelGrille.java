import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;


import java.awt.event.*;

public class PanelGrille extends JPanel implements ActionListener
{
	Controleur ctrl;

	JLabel[][] tabLblCase;
	JButton[]  tabButton;


	public PanelGrille(Controleur ctrl)
	{
		String[][] modele;

		int cas;
		int cptBtn=0;


		this.ctrl = ctrl;
		this.setLayout ( new GridLayout ( this.ctrl.getNbLigne()+2, this.ctrl.getNbColonne()+2, 1, 1 ) );

		modele = this.getModele();


		/*------------------------------*/
		/* Création des composants      */
		/*------------------------------*/

		// Création des Labels
		this.tabLblCase  = new JLabel [ this.ctrl.getNbLigne() ] [ this.ctrl.getNbColonne() ];


		for (int lig = 0; lig < tabLblCase.length; lig++)
			for (int col = 0; col < tabLblCase[lig].length; col++)
			{
				this.tabLblCase[lig][col] = new JLabel();
				this.tabLblCase[lig][col].setIcon(new ImageIcon(ctrl.getImage(lig, col)));
				this.tabLblCase[lig][col].setOpaque(true);
			}


		// Création des Boutons
		this.tabButton = new JButton[ 2*this.ctrl.getNbLigne() + 2*this.ctrl.getNbColonne() ];

		cptBtn = 0;

		for (int lig=0;lig<modele.length; lig++ )

		{
			for (int col=0;col<modele[lig].length; col++ )
			{
				if ( modele[lig][col] != null && modele[lig][col].startsWith ("fl_" ) )
				{
					String direction = modele[lig][col] + ".png";
					ImageIcon icon = new ImageIcon(direction);
					this.tabButton[cptBtn++]  = new JButton(icon);
				}
			}
		}


		/*------------------------------*/
		/* Postionnement des composants */
		/*------------------------------*/
		cptBtn = 0;

		for (int lig=0; lig<modele.length; lig++ )
			for (int col=0; col<modele[lig].length; col++ )
			{
					  if ( modele[lig][col] == null            ) cas=0;
				else if ( modele[lig][col].startsWith ("fl_" )) cas=1;
				else                                            cas=2;

				switch ( cas )
				{
					case 0 -> this.add(new JLabel());
					case 1 -> this.add(this.tabButton[cptBtn++]);
					case 2 -> this.add(this.tabLblCase[lig - 1][col - 1]);
				}
		}


		/*------------------------------*/
		/* Activation des composants    */
		/*------------------------------*/
		for (JButton button : this.tabButton)
		{
			if (button != null)
			{
				button.addActionListener(this);
			}
		}
	}

	public void majIHM()
	{
		for (int lig = 0; lig < this.tabLblCase.length; lig++)
		{
			for (int col = 0; col < this.tabLblCase[lig].length; col++)
			{
				this.tabLblCase[lig][col].setIcon(new ImageIcon(ctrl.getImage(lig, col)));
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();

		for (int i = 0; i < this.tabButton.length; i++)
		{
			if (this.tabButton[i] == source)
			{
				// On détermine la direction et l'indice du bouton cliqué
				String action = ((JButton) source).getIcon().toString(); // Récupère
																			// le
																			// nom
																			// de
																			// l’image
																			// associée

				char type = 0;
				char sens = 0;
				int indice = 0;
				if (action.contains("fl_haut"))
				{
					type = 'L'; // Ligne
					sens = '-'; // Up (Haut)
					indice = i % this.ctrl.getNbColonne(); // Colonne concernée
				}
				if (action.contains("fl_bas"))
				{
					type = 'L'; // Ligne
					sens = '+'; // Down (Bas)
					indice = i % this.ctrl.getNbColonne();
				}
				if (action.contains("fl_gauche"))
				{
					type = 'C'; // Ligne
					sens = '-'; // Left (Gauche)
					indice = (i - this.ctrl.getNbColonne()) / 2; // Ligne concernée
				}
				if (action.contains("fl_droit"))
				{ // fl_droite
					type = 'C'; // Ligne
					sens = '+'; // Right (Droite)
					indice = (i - this.ctrl.getNbColonne()) / 2; // Ligne concernée
				}
				// Appel du contrôleur pour permuter les cases
				this.ctrl.permuter(type, sens, indice);
				
			}
		}

		// Mise à jour de l'interface graphique
		this.majIHM();
	}

	private String[][] getModele()
	{
		/* Voici un exemple de Modele généré pour une grille de 6 x 6

		{ {null,        "fl_haut", "fl_haut", "fl_haut", "fl_haut", "fl_haut", "fl_haut", null        },
	     {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {"fl_gauche", "val",     "val",     "val",     "val",     "val",     "val",     "fl_droite" },
		  {null,        "fl_bas",  "fl_bas",  "fl_bas",  "fl_bas",  "fl_bas",  "fl_bas",   null       }  };
		*/


		// Construction du Modele correspondant à la taille de notre Grille.
		String[][] tabModele = new String[ctrl.getNbLigne()+2][ctrl.getNbColonne()+2];

		for (int lig = 1; lig < tabModele.length-1; lig++ )
		{
			tabModele[lig][0]                          = "fl_gauche";
			tabModele[lig][tabModele[lig].length - 1 ] = "fl_droite";
		}

		for (int col = 1; col < tabModele[0].length-1; col++ )
		{
			tabModele[0]                     [col] = "fl_haut";
			tabModele[tabModele.length - 1 ] [col] = "fl_bas";
		}

		for (int lig=1; lig < tabModele.length-1; lig++ )
			for (int col = 1; col < tabModele[0].length-1; col++ )
				tabModele[lig][col] = "val";

		return tabModele;
	}
}
