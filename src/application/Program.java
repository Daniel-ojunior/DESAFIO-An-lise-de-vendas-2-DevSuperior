package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			Set<Sale> set = new HashSet<>();

			String line = br.readLine();

			while (line != null) {

				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

				line = br.readLine();

			}

			set.addAll(list);

			System.out.println();
			System.out.println("Total de Vendas por vendedor:");

			for (Sale s : set) {

				Double tot = list.stream().filter(sel -> sel.getSeller().equals(s.getSeller())).map(t -> t.getTotal())
						.reduce(0.0, (x, y) -> x + y);

				System.out.println(s.getSeller() + " - R$ " + String.format("%.2f", tot));
			}

		} catch (IOException e) {

			System.out.println("Erro: " + path + "(O sistema não pode encontrar o arquivo especificado)");
		}

		sc.close();
	}

}
