import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CS6084BShelar {

	static char blob[][];
	static char copyBlob[][];
	static int countB = 0;
	static int blobCounter[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		blobCounter = new int[25*25];
		copyBlob = new char[25][25];
		blob=readFile();
		System.out.println("Blob Number : "+blobs());
		System.out.println("Number of Bs : "+countB);
	}

	static int BlobCount(int N, int r, int c){

		if (r < 0 || r >= N || c < 0 || c >= N)
			return 0;
		if ( copyBlob[r][c] == 'W')
			return 0;
		else
		{
			countB++;
			copyBlob[r][c] = 'W';
			return (1 + BlobCount(N, r-1, c-1) +
					BlobCount(N, r-1, c) +
					BlobCount(N, r-1, c+1) +
					BlobCount(N, r, c-1) +
					BlobCount(N, r, c+1) +
					BlobCount(N, r+1, c-1) +
					BlobCount(N, r+1, c) +
					BlobCount(N, r+1, c+1));
		}
	}	

	static int blobs()
	{
		//Initializing variables;
		int countBlob = 0; 		//Number of Bolbs.
		int size;
		copyMatrix(); 			//Copying the grid.

		for (int i = 0; i < 25*25; i++)	//Initalizing blobs count array.
			blobCounter[i] = 0;

		//Counting blobs and getting their size.
		for (int i = 0; i < 25; i++)
			for (int j = 0; j < 25; j++)
			{
				size = BlobCount(25, i, j);
				if (size > 0)
				{
					blobCounter[countBlob] = size;
					countBlob ++;
				}
			}
		return countBlob;
	}

	private static void copyMatrix() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 25; i++)
			for (int j = 0; j < 25; j++)
			{
				copyBlob[i][j]=blob[i][j];
			}
	}

	//**** read file of integers and return list of unsorted elements
	static char[][] readFile() {
		char blob[][] = new char[25][25];
		String line;
		try {

			FileInputStream istream = new FileInputStream("blob.txt");
			Scanner input = new Scanner(istream);
			int i=0;

			while(input.hasNext()) {
				line= input.next();
				for(int j=0;j<25;j++) {
					blob[i][j] = line.charAt(j);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return blob;
	}

}
