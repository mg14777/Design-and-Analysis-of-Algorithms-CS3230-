import java.util.*;
import java.io.*;

public class Main {

	void optimalEmpire(int[][][] memo,int n) {
		// Compute score of all empires with one corner at origin
		for(int k=0; k < n; k++) {
			for(int j=0; j < n; j++)
				for(int i=0; i < n; i++) {
					if(i > 0)
						memo[i][j][k] += memo[i-1][j][k];
					if(j > 0)
						memo[i][j][k] += memo[i][j-1][k];
					if(k > 0)
						memo[i][j][k] += memo[i][j][k-1];
					if(i > 0 && j > 0)
						memo[i][j][k] -= memo[i-1][j-1][k];
					if(j > 0 && k > 0)
						memo[i][j][k] -= memo[i][j-1][k-1];
					if(i > 0 && k > 0)
						memo[i][j][k] -= memo[i-1][j][k-1];
					if(i > 0 && j > 0 && k > 0)
						memo[i][j][k] += memo[i-1][j-1][k-1];
				}
		}
		//Compute score of all possible empires using cumulative memo table just built
		int score,max_score;
		max_score = memo[0][0][0];
		for(int i=0; i < n; i++)
			for(int j=0; j < n;j++)
				for(int k=0; k < n;k++) {
					for(int i_s=0; i_s <= i;i_s++)
						for(int j_s=0; j_s <= j; j_s++)
							for(int k_s=0; k_s <= k; k_s++) {
								score = memo[i][j][k];
								if(i_s > 0)
									score -= memo[i_s-1][j][k];
								if(j_s > 0)
									score -= memo[i][j_s-1][k];
								if(k_s > 0)
									score -= memo[i][j][k_s-1];
								if(i_s > 0 && j_s > 0)
									score += memo[i_s-1][j_s-1][k];
								if(i_s > 0 && k_s > 0)
									score += memo[i_s-1][j][k_s-1];
								if(j_s > 0 && k_s > 0)
									score += memo[i][j_s-1][k_s-1];
								if(i_s > 0 && j_s > 0 && k_s > 0)
									score -= memo[i_s-1][j_s-1][k_s-1];
								// Recalculate maximum score encountered so far
								if(score > max_score) {
									max_score = score;
								}
							}
				}
		System.out.println(max_score);

	}
	void run() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String line = br.readLine();
			if(line == null)
				break;
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int[][][] memo = new int[n][n][n];
			for(int k=0; k < n; k++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j < n; j++)
					for(int i=0; i < n; i++)
						memo[i][j][k] = Integer.parseInt(st.nextToken());
			}
			optimalEmpire(memo,n);
		}
		
	}
	public static void main(String[] args) throws Exception {
		Main ex1 = new Main();
		ex1.run();
	}
}