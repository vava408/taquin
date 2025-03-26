public class Grille
{
	private char[][] grille;

	public Grille()
	{
		this.grille = new char[][] { { 'm', 'm', 'm', 'm', 'm', 'm' },
									 { 'b', 'b', 'b', 'b', 'b', 'b' },
									 { 'v', 'v', 'v', 'v', 'v', 'v' },
									 { 'j', 'j', 'j', 'j', 'j', 'j' },
									 { 'o', 'o', 'o', 'o', 'o', 'o' },
									 { 'r', 'r', 'r', 'r', 'r', 'r' } };
		this.melanger();

	}

	public char getVal(int lig, int col)
	{
		return this.grille[lig][col];
	}

	public int getNbLigne()
	{
		return this.grille.length;
	}

	public int getNbColone()
	{
		return this.grille[0].length;
	}

	public void permuter(char type, char sens, int indice)
	{
		char tmp;

		// Permutation des colonnes (déplacement vertical)
		if (type == 'L')
		{
			if (sens == '-')
			{ // Déplacement vers le haut
				tmp = this.grille[0][indice]; // Sauvegarde la première valeur

				for (int lig = 0; lig < this.getNbLigne() - 1; lig++)
				{
					this.grille[lig][indice] = this.grille[lig + 1][indice];
				}

				this.grille[this.getNbLigne() - 1][indice] = tmp; // Place la
																	// valeur
																	// sauvegardée
																	// en bas
			}

			if (sens == '+')
			{ // Déplacement vers le bas
				tmp = this.grille[this.getNbLigne() - 1][indice]; // Sauvegarde
																	// la
																	// dernière
																	// valeur

				for (int lig = this.getNbLigne() - 1; lig > 0; lig--)
				{
					this.grille[lig][indice] = this.grille[lig - 1][indice];
				}

				this.grille[0][indice] = tmp; // Place la valsens
			}
		}
		if (type == 'C')
		{
		//	System.out.println(indice);
			if (sens == '+')
			{ // Déplacement vers la droite
				tmp = this.grille[indice][this.getNbColone() - 1]; // Sauvegarde
																	// la
																	// dernière
																	// valeur

				for (int col = this.getNbColone() - 1; col > 0; col--)
				{
					this.grille[indice][col] = this.grille[indice][col - 1];
				}

				this.grille[indice][0] = tmp; // Place la valeur sauvegardée au
												// début
			}

			if (sens == '-')
			{ // Déplacement vers la gauche
				tmp = this.grille[indice][0]; // Sauvegarde la première valeur

				for (int col = 0; col < this.getNbColone() - 1; col++)
				{
					this.grille[indice][col] = this.grille[indice][col + 1];
				}

				this.grille[indice][this.getNbColone() - 1] = tmp; // Place la
																	// valeur
																	// sauvegardée
																	// à la fin
			}
		}
	}

	public void melanger()
	{
		char[] tabType = {'L', 'C'};
		char[] tabSens = {'+', '-'};
		int type, sens , indice;

		for(int cpt = 0; cpt< 10000; cpt++)
		{
			type = (int) (Math.random() *2);
			sens = (int) (Math.random() *2);
			if (tabType[type] == 'C')
				indice = (int)(Math.random() * this.getNbLigne());
			else 
				indice = (int)(Math.random() * this.getNbColone());
			
			this.permuter(tabType[type], tabSens[sens], indice);

		}
	}

}