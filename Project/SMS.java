import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Items.Controllers.ItemController;
import Items.Objects.Item;
import Response.Response;
import Rules.Controllers.RuleController;
import Rules.Objects.Rule;
import Sweepstakes.Controllers.SweepstakeController;
import Sweepstakes.Objects.Sweepstake;
import Sweepstakes.Objects.Sweepstake.Status;
import Users.Controllers.CommonController;
import Users.Controllers.ManagerController;
import Users.Controllers.UserController;
import Users.Objects.Common;
import Users.Objects.Manager;
import Users.Objects.User;

public class SMS {
    //#region VARIABLES
    private UserController userController;
    private ManagerController managerController;
    private CommonController commonController;
    private SweepstakeController sweepstakeController;
    private ItemController itemController;
    private RuleController ruleController;
    private  Scanner scanner;
    //#endregion

    //#region CONSTRUCTORS
    public SMS() {
        userController = new UserController();

        sweepstakeController = new SweepstakeController();
        itemController = new ItemController();
        ruleController = new RuleController();
        scanner = new Scanner(System.in);
    }
    //#region

    //#region MAIN
    public void index() {
        String option;
        
        do {
            System.out.println("----- SMS - Sistema de Gerenciamento de Sorteios -----");
            System.out.println("1. Login/Cadastro de Usuarios");
            System.out.println("2. Sair");

            option = scanner.next();

            switch (option) {
                case "1":
                    auth();
                break;
                default:
                    System.exit(0);
                break;
            }
        } while (!option.equals("2"));
    }
    //#endregion

    //#region AUTHENTICATION
    public void auth() {
        String option;
        Response response = Response.NOT_FOUND;

        System.out.println("----- SMS - Sistema de Gerenciamento de Sorteios -----");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("0. Pagina Inicial");

        option = scanner.next();
        scanner.nextLine();

        if(option.equals("1")) {
            response = login();
        } else if(option.equals("2")) {
            response = singnup();
        } else {
            index();
        }

        panel(response);
    }

    public Response login() {  
              
        System.out.println("----- SMS - Tela de Login -----");

        System.out.println("Digite o Usuario:");
        String cpf = scanner.next();

        System.out.println("Digite a Senha:");
        String password = scanner.next();

        return userController.logIn(cpf, password);
    }

    public Response singnup() {
        System.out.println("----- SMS - Cadastro de Usuario -----");
        System.out.println("Digite o Nome:");
        String name = scanner.nextLine();

        System.out.println("Digite o Usuario:");
        String cpf = scanner.next();

        System.out.println("Digite a Senha:");
        String password = scanner.next();

        System.out.println("Digite o Hash em caso de Usuario Admin:");
        String hashValidate = scanner.next();

        return userController.singnUp(cpf, name, password, hashValidate);
    }
    //#endregion

    //#region PANEL ACCESS
    private void panel(Response response) {
        System.out.println(response.getMessage() + "\n");

        switch (response) {
            case MANAGER_ACCESS:
                managerController = new ManagerController((Manager) userController.getSessionUser());
                panelManager();
            break;
            case COMMON_ACCESS:
                commonController = new CommonController((Common) userController.getSessionUser());
                panelCommon();
            break;
            default:
                auth();
            break;
        }
    }

    private void panelManager() {
        String option;
        do {
            System.out.println("----- SMS - Painel de Administrador -----");
            System.out.println("1. Adicionar Regra");
            System.out.println("2. Listar Sorteios");
            System.out.println("3. Listar Itens");
            System.out.println("4. Listar Regras");
            System.out.println("5. Remover Regra");
            System.out.println("6. Analisar Sorteio");
            System.out.println("7. Analisar Item");
            System.out.println("0. Sair");

            option = scanner.next();

            switch(option) {
                case "1":
                    registerRule();
                break;
                case "2":
                    listSweepstakes();
                break;
                case "3":
                    listItems();
                break;
                case "4":
                    listRules();
                break;
                case "5":
                    removeRule();
                break;
                case "6":
                    analyseSweepstake();
                break;
                case "7":
                    analyseItem();
                break;
            }
        } while(!option.equals("0"));
    }

    private void panelCommon() {
        String option;
        do {
            System.out.println("----- SMS - Painel de Usuario -----");
            System.out.println("1. Cadastrar Sorteio");
            System.out.println("2. Cadastrar Item");
            System.out.println("3. Listar Meus Sorteios");
            System.out.println("4. Listar Meus Itens");
            System.out.println("5. Listar Sorteios Participante");
            System.out.println("6. Listar Sorteios Disponiveis");
            System.out.println("7. Participar de Sorteio");
            System.out.println("8. Sortear");
            System.out.println("0. Sair");

            option = scanner.next();

            switch(option) {
                case "1":
                    registerSweepstake();
                break;
                case "2":
                    registerItem();
                break;
                case "3":
                    listSweepstakesPerUser();
                break;
                case "4":
                    listItemsPerUser();;
                break;
                case "5":
                    listSweepstakesPerEntrant();
                break;
                case "6":
                    listSweepstakes();
                break;
                case "7":
                    joinSweepstake();
                break;
                case "8":
                    makeSweepstake();
                break;
            }
        } while(!option.equals("0"));
    }
    //#endregion

    //#region FUNCTIONS SWEEPSTAKES
    private void listSweepstakesPerUser() {
        commonController.listSweepstakes();
    }

    private void listSweepstakesPerEntrant() {
        sweepstakeController.listSweepstakesPerEntrant(userController.getSessionUser().getId());
    }

    private void listSweepstakes() {
        System.out.println("----- SMS - Filtro de Listagem -----");
        System.out.println("1. Pendentes");
        System.out.println("2. Em Progresso");
        System.out.println("3. Finalizados");
        System.out.println("4. Cancelados");
        System.out.println("0. Todos");

        switch(scanner.next()) {
            case "1":
                sweepstakeController.listSweepstakes(Status.PENDING);
            break;
            case "2":
                sweepstakeController.listSweepstakes(Status.IN_PROGRESS);
            break;
            case "3":
                sweepstakeController.listSweepstakes(Status.FINISHED);
            break;
            case "4":
                sweepstakeController.listSweepstakes(Status.CANCELLED);
            break;
            default:
                sweepstakeController.listSweepstakes();
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS ITEMS
    private void listItemsPerUser() {
        commonController.listItems();;
    }

    private void listItems() {
        System.out.println("----- SMS - Filtro de Listagem -----");
        System.out.println("1. Pendentes");
        System.out.println("2. Autorizados");
        System.out.println("3. Reservados");
        System.out.println("4. Rejeitados");
        System.out.println("0. Todos");

        switch(scanner.next()) {
            case "1":
                itemController.listItems(Items.Objects.Item.Status.PENDING);
            break;
            case "2":
                itemController.listItems(Items.Objects.Item.Status.AUTHORIZED);
            break;
            case "4":
                itemController.listItems(Items.Objects.Item.Status.RESERVED);
            break;
            case "3":
                itemController.listItems(Items.Objects.Item.Status.UNAUTHORIZED);
            break;
            default:
                itemController.listItems();
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS RULES
    private void listRules() {
        ruleController.listRules();
    }
    //#endregion

    //#region FUNCTIONS MANAGER
    private void registerRule() {
        scanner.nextLine();

        if(userController.getSessionUser().getType().equals(User.Type.MANAGER)) {
            System.out.println("----- SMS - Registrar Regra -----");
            System.out.println("Digite o Nome:");
            String name = scanner.nextLine();

            System.out.println("Digite a Descricao:");
            String description = scanner.nextLine();

            Rule rule = new Rule(name, description);

            ruleController.registerRule(rule);
        }
    }
    private void removeRule() {
        scanner.nextLine();

        if(userController.getSessionUser().getType().equals(User.Type.MANAGER)) {
            System.out.println("----- SMS - Remover Regra -----");
            System.out.println("Digite o Id:");
            String id = scanner.next();

            ruleController.removeRule(id);
        }
    }

    private void analyseSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Analise de Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
        spweepstake = sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            spweepstake.displayInformation();
        } else {
            analyseSweepstake();
        }
        
        System.out.println("Deseja Autorizar (1) ou Rejeitar (2) o Sorteio?");
        switch(scanner.next()) {
            case "1":
                managerController.validateSweepstake(spweepstake, true);
            break;
            case "2":
                managerController.validateSweepstake(spweepstake, false);
            break;
        }
    }

    private void analyseItem() {
        Item item;

        System.out.println("----- SMS - Analise de Item -----");
        System.out.println("Digite o Id do Sorteio:");
        item = itemController.findItem(scanner.next());

        if(item != null) {
            item.displayInformation();
        } else {
            analyseItem();
        }
        
        System.out.println("Deseja Autorizar (1) ou Rejeitar (2) o Sorteio?");
        switch(scanner.next()) {
            case "1":
                managerController.validateItem(item, true);
            break;
            case "2":
                managerController.validateItem(item, false);
            break;
        }
    }
    //#endregion

    //#region FUNCTIONS COMMON
    private void registerSweepstake() {
        Map<String, Rule> rules = new HashMap<>();
        Map<String, Item> items = new HashMap<>();

        scanner.nextLine();
        if(commonController.checkForItems().equals(Response.OK) && commonController.checkForItemsAuthorized().equals(Response.OK)) {
            System.out.println("----- SMS - Rgistrar Sorteio -----");
            System.out.println("Digite a Resumo:");
            String overview = scanner.nextLine();

            System.out.println("Digite a Descricao:");
            String description = scanner.nextLine();


            if(ruleController.checkForRule().equals(Response.OK)) {
                System.out.println("Deseja Adicionar Regras(1)?");
                rules = addRulesToSweepstake();
            }

            items = addItemsToSweepstake();

            Sweepstake sweepstake = new Sweepstake(userController.getSessionUser(), overview, description, rules, items);

            sweepstakeController.registerSweepstake(sweepstake);
            commonController.registerSweepstake(sweepstake);
        }
    }

    private void registerItem() {
        scanner.nextLine();
        System.out.println("----- SMS - Rgistrar Item -----");
        System.out.println("Digite o Nome:");
        String name = scanner.nextLine();

        System.out.println("Digite a Descricao:");
        String description = scanner.nextLine();

        System.out.println("Digite o Tipo:");
        String type = scanner.nextLine();
        

        Item item = new Item(userController.getSessionUser(), name, description, type);

        itemController.registerItem(item);
        commonController.registerItem(item);
    }

    private void joinSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Participar de Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
        spweepstake = sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            spweepstake.displayInformation();
        } else {
            joinSweepstake();
        }

        Response response = sweepstakeController.registerEntrant(spweepstake, userController.getSessionUser());

        if(response == Response.OK) {
            commonController.joinSweepstake(spweepstake);
        }
    }

    private void makeSweepstake() {
        Sweepstake spweepstake;

        System.out.println("----- SMS - Realizar Sorteio -----");
        System.out.println("Digite o Id do Sorteio:");
       
        spweepstake = sweepstakeController.findSweepstake(scanner.next());

        if(spweepstake != null) {
            Response response = commonController.makeSweepstake(spweepstake.getId());
            if(response == Response.OK) {
                spweepstake.displayWinners();
            }
        } else {
            makeSweepstake();
        }
    }
    
    private Map<String, Item> addItemsToSweepstake() {
        Map<String, Item> items = new HashMap<>();

        commonController.listItemsAuthorized();

        do {
            System.out.println("Digite o Id do Item a Ser Adicionado:");
            String id = scanner.next();
            Item item = itemController.findItem(id);

            if(item.getStatus().equals(Item.Status.AUTHORIZED)) {
                item.setStatus(Item.Status.RESERVED);

                items.put(item.getId(), item);
            }
        
            System.out.println("Deseja Adicionar um Novo Item(1)?");
        } while(scanner.next().equals("1"));

        if(items.isEmpty()) {
            System.out.println("Lista de Itens Vazia");

            addItemsToSweepstake();
        }

        return items;
    }

    private Map<String, Rule> addRulesToSweepstake() {
        Map<String, Rule> rules = new HashMap<>();

        listRules();

        while(scanner.next().equals("1")) {
            System.out.println("Digite o Id da Regra a Ser Adicionada:");
            String id = scanner.next();
            Rule rule = ruleController.findRule(id);

            rules.put(rule.getId(), rule);

            System.out.println("Deseja Adicionar uma Nova Regra(1)?");
        }

        return rules;
    }
    //#endregion
}
