package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entites.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String enterFilePath = sc.nextLine();

		File path = new File(enterFilePath);
		String sourceFolderStr = path.getParent();

		boolean folderCreatFile = new File(sourceFolderStr + "\\out").mkdir();

		String targetFolderStr = sourceFolderStr + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(enterFilePath))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");
				String name = fields[0];
				Double prive = Double.parseDouble(fields[1]);
				Integer quantity = Integer.parseInt(fields[2]);

				list.add(new Product(name, prive, quantity));
				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFolderStr))) {

				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.totalValue()));
					bw.newLine();
				}
				
				System.out.println(targetFolderStr + "Created");

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
