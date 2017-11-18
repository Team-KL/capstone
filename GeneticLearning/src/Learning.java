import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Learning {

	final int GENE_ELEMENT_NUM = 16;
	final int CHOICE_NUM = 9; // 행동 경우의 수

	int EVOLUTION_NUM; // 세대 수
	int ENTITY_NUM;
	double CROSSOVER_RATIO;
	double MUTATION_RATIO;
	int MUTATION_EFFECT;

	public Learning(int EVOLUTION_NUM, int ENTITY_NUM, double CROSSOVER_RATIO, double MUTATION_RATIO, int MUTATION_EFFECT) {
		this.EVOLUTION_NUM = EVOLUTION_NUM;
		this.ENTITY_NUM = ENTITY_NUM;
		this.CROSSOVER_RATIO = CROSSOVER_RATIO;
		this.MUTATION_RATIO = MUTATION_RATIO;
		this.MUTATION_EFFECT = MUTATION_EFFECT;

		learn();
	}

	public void learn() {

		Simulation_forLearning simulation = new Simulation_forLearning();
		Random random = new Random();

		Gene[] generation = new Gene[ENTITY_NUM];
		for(int i = 0; i < ENTITY_NUM; i++) {
			generation[i] = new Gene(GENE_ELEMENT_NUM);
			for(int j = 0; j < GENE_ELEMENT_NUM; j++)
				generation[i].g[j] = random.nextInt(CHOICE_NUM);
		}

		
		for(int e = 0; e <= EVOLUTION_NUM; e++) {

			double sum = 0;
			for(int i = 0; i < ENTITY_NUM; i++) {
				simulation.reset(generation[i]);
				simulation.run();

				int score = simulation.minorBullets.size() + 1;
				sum += score;

				generation[i].score = score;
				generation[i].majorDataList = simulation.majorDataList;
				generation[i].minorDataList = simulation.minorDataList;
			}

			double avg = sum/ENTITY_NUM;

			int min = Integer.MAX_VALUE;
			int pos = 0;
			for(int i = 0; i < ENTITY_NUM; i++) {
				if(generation[i].score > avg)
					if(generation[i].score < min) {
						min = generation[i].score;
						pos = i;
					}
			}

			Gene representation = generation[pos];
			representation.index = e;

			JLabel label = new JLabel();
			String text = String.format("%-40s", "  " + e + "세대 Score: " + representation.score);
			label.setText(text);
			label.setBorder(new LineBorder(new Color(200, 200, 200)));
			Screen.listPanel.add(label);
			Screen.listPanel.revalidate();
			Screen.listPanel.repaint();

			Screen.dots.add(new Point(e, representation.score));
			Screen.graphPanel.repaint();

			Screen.candidate.add(representation);



			Gene[] nextGeneration = new Gene[ENTITY_NUM];			


			double[] cumulativeNormed = new double[ENTITY_NUM]; 
			for(int i = 0; i < ENTITY_NUM; i++)
				if(i == 0)
					cumulativeNormed[i] = generation[i].score / sum;
				else
					cumulativeNormed[i] = cumulativeNormed[i-1] + generation[i].score / sum;

			for(int i = 0; i < ENTITY_NUM; i++) {
				double roulette = Math.random();
				for(int j = 0; j < ENTITY_NUM; j++)
					if(roulette < cumulativeNormed[j]) {
						nextGeneration[i] = copyGene(generation[j]);
						nextGeneration[i].index = i;
						break;
					}
			}

			List<Gene> nextGenerationList = Arrays.asList(nextGeneration);
			Collections.shuffle(nextGenerationList);
			
			int[] indexes = new int[(int)(ENTITY_NUM * CROSSOVER_RATIO)];
			for(int i = 0; i < indexes.length; i++)
				indexes[i] = nextGenerationList.get(i).index;
			
			for(int i = 0; i < indexes.length-1; i += 2) {
				int crossover_L = random.nextInt(GENE_ELEMENT_NUM);
				int crossover_R = random.nextInt(GENE_ELEMENT_NUM);
				if(crossover_L > crossover_R) {
					int temp = crossover_L;
					crossover_L = crossover_R;
					crossover_R = temp;
				}

				swap(nextGeneration[indexes[i]].g, nextGeneration[indexes[i+1]].g, crossover_L, crossover_R);

			}

			for(int i = 0; i < ENTITY_NUM; i++) {
				if(Math.random() < MUTATION_RATIO) {
					for(int j = 0; j < MUTATION_EFFECT; j++) {
						int mutateIndex = random.nextInt(GENE_ELEMENT_NUM);
						nextGeneration[i].g[mutateIndex] = random.nextInt(CHOICE_NUM);
					}
				}
			}

			generation = nextGeneration;

		}

	}

	public Gene copyGene(Gene gene) {
		Gene copy = new Gene(GENE_ELEMENT_NUM);
		for(int i = 0; i < GENE_ELEMENT_NUM; i++)
			copy.g[i] = gene.g[i];
		return copy;
	}

	public void swap(int[] a, int[] b, int L, int R) {
		int temp;
		for(int i = L; i <= R; i++) {
			temp = a[i];
			a[i] = b[i];
			b[i] = temp;
		}
	}
}