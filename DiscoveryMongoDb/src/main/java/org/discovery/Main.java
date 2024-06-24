package org.discovery;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.discovery.dao.TelemetryDaoImpl;
import org.discovery.entities.LocalizacaoEntity;
import org.discovery.entities.TelemetryEntity;
import org.discovery.repository.TelemetryRepository;
import org.discovery.services.TelemetryDatabaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    static TelemetryRepository repository;

    public static void main(String[] args) {
        TelemetryDatabaseService.startClient();
        repository = createRepository();
        showUserOptions();
        TelemetryDatabaseService.closeClient();
    }

    private static void showUserOptions() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<>();
        boolean exit = false;
        while (!exit) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Buscar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Editar");
            System.out.println("6 - Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha
            switch (choice) {
                case 1:
                    repository.query().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Adicionando um exemplo!");
                    insertExample();
                    break;
                case 3:
                    System.out.println("Buscando o primeiro resultado da query!");
                    findExample();
                    break;
                case 4:
                    System.out.println("Removendo o primeiro resultado da query!");
                    removeExample();
                    break;
                case 5:
                    System.out.println("Editando o primeiro resultado da query!");
                    replaceExample();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void updateLocalizacaoExample() {
        var query = printAndReturnQueryAntes();
        repository.updateLocalizacao(query.getFirst().getSensorId(), 100, 200);
        printAndReturnQueryDepois();
    }

    private static void insertExample() {
        printAndReturnQueryAntes();
        repository.insert(createEntity());
        printAndReturnQueryDepois();
    }

    private static void replaceExample() {
        var query = printAndReturnQueryAntes();
        var toBeReplaced = alterEntityFields(query);
        repository.replace(toBeReplaced);
        printAndReturnQueryDepois();
    }

    private static void removeExample() {
        var query = printAndReturnQueryAntes();
        repository.remove(query.getFirst().getSensorId());
        printAndReturnQueryDepois();
    }

    private static void findExample() {
        var query = printAndReturnQueryAntes();
        var document = repository.find(query.getFirst().getSensorId());
        System.out.println(document);
    }

    private static TelemetryEntity alterEntityFields(List<TelemetryEntity> query) {
        var toBeReplaced = query.getFirst();
        toBeReplaced.setPassos(9999);
        toBeReplaced.setFrequenciaCardiaca(99.99f);
        toBeReplaced.setUmidade(100.1f);
        return toBeReplaced;
    }

    private static TelemetryEntity createEntity() {
        return new TelemetryEntity(UUID.randomUUID().toString(), 20f, 60f, 1200, 84f, new LocalizacaoEntity(-27.7131, -52.4316));
    }

    private static TelemetryRepository createRepository() {
        return new TelemetryRepository(new TelemetryDaoImpl(TelemetryDatabaseService.getCollection()));
    }

    private static List<TelemetryEntity> printAndReturnQueryAntes() {
        return printAndReturnQuery("Antes:");
    }

    private static List<TelemetryEntity> printAndReturnQueryDepois() {
        return printAndReturnQuery("Depois:");
    }

    private static List<TelemetryEntity> printAndReturnQuery(String message) {
        var query = repository.query();
        System.out.println(message);
        query.forEach(System.out::println);
        return query;
    }
}