package main;

public class StatisticsMain {

	public static void main(String[] args) throws InterruptedException {

//		Main.stats_collect(args, 10, 1, 10000);

		for (int i = 25; i <= 500; i += 25) {
			Main.stats_collect(args, i, 1, 1000000);

		}
	}

}